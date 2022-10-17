package com.ou.restaurantmanagement.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MomoUtil {
    public Map<String, String> createOrder(BigDecimal price, String orderInfo, String notifyURL, String returnURL)
            throws InvalidKeyException, NoSuchAlgorithmException, JsonProcessingException, ExecutionException,
            InterruptedException {
        String requestId = UUID.randomUUID().toString();
        String orderId = UUID.randomUUID().toString();
        boolean autoCapture = true;
        String requestType = "captureWallet";
        String extraData = "";
        String rawSignature = "accessKey=sBPOW0h21oiBpNLf" + "&amount=" + price.toString()
                + "&extraData="
                + "&ipnUrl=" + notifyURL
                + "&orderId=" + orderId
                + "&orderInfo=" + orderInfo
                + "&partnerCode=MOMOQSLC20220507"
                + "&redirectUrl=" + returnURL
                + "&requestId=" + requestId
                + "&requestType=" + requestType;

        SecretKeySpec secretKeySpec = new SecretKeySpec(
                Objects.requireNonNull("VwLtlNmH1ghPpNaOhvz9KHbytexGY4qL").getBytes(),
                "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        String signature = bytesToHex(mac.doFinal(rawSignature.getBytes()));
        Map<String, String> map = new HashMap<>();
        map.put("partnerCode", "MOMOQSLC20220507");
        map.put("partnerName", "Restaurant Thanh Van");
        map.put("storeId", "MOMOQSLC20220507");
        map.put("requestType", requestType);
        map.put("ipnUrl", notifyURL);
        map.put("redirectUrl", returnURL);
        map.put("orderId", orderId);
        map.put("amount", String.valueOf(price));
        map.put("autoCapture", String.valueOf(autoCapture));
        map.put("orderInfo", orderInfo);
        map.put("requestId", requestId);
        map.put("extraData", extraData);
        map.put("signature", signature);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        String path = "https://test-payment.momo.vn/v2/gateway/api/create";
        HttpRequest request = HttpRequest.newBuilder(URI.create(Objects.requireNonNull(path)))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        CompletableFuture<Map<String, String>> future = HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).thenApply(content -> {
                    try {
                        return objectMapper.readValue(content, new TypeReference<>() {
                        });
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
        return future.get();
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte h : hash) {
            String hex = Integer.toHexString(0xff & h);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
