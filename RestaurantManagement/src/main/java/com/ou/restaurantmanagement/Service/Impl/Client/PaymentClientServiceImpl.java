package com.ou.restaurantmanagement.Service.Impl.Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Request.IBaseRequest;
import com.ou.restaurantmanagement.DTO.Request.Order.BillRequestDTO;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.DTO.Response.IBaseResponse;
import com.ou.restaurantmanagement.Repository.Client.PaymentClientRepository;
import com.ou.restaurantmanagement.Repository.Impl.Client.OrderClientRepositoryImpl;
import com.ou.restaurantmanagement.Service.Client.PaymentClientService;
import com.ou.restaurantmanagement.Utils.MomoUtil;
import com.ou.restaurantmanagement.Utils.TwilioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

@Service
public class PaymentClientServiceImpl implements PaymentClientService {
    @Autowired
    private PaymentClientRepository _paymentRepository;

    @Override
    public IBaseResponse paymentMomo(IBaseRequest input) {
        try {
            BillRequestDTO req = (BillRequestDTO) input;
            MomoUtil momoUtil = new MomoUtil();
            String url = String.format("http://localhost:8989/api/client/add-bill-for-momo?user-id=%d&order-id=%d",
                    req.getUserID(), req.getOrderID());
            BigDecimal money = _paymentRepository.totalMoney(req.getOrderID()).divide(new BigDecimal(1000));
            return new Common(Code.OK,
                    momoUtil.createOrder(money,"Thanh toan hoa don nha hang Thanh Van", url, url)
                            .get("payUrl"),
                    "Thành công");
        } catch (Exception e){
            return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
        }
    }

    @Override
    public IBaseResponse confirmPayment(IBaseRequest input) {
        try{
            if(_paymentRepository.addBill(input)) {
//                Tạm đóng gửi SMS
//                sendTwilioSMS();
                return new Common(Code.OK, null, "Xác nhận thanh toán thành công");
            }
            return new Common(Code.INVALID_REQUEST, null, "Vui lòng kiểm tra lại!");
        } catch (Exception e){
            System.err.println("ERROR in confirmPayment(): " + e);
            return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
        }
    }

    @Override
    public IBaseResponse paymentSuccess() {
        try {
//            Tạm đóng gửi SMS
//            sendTwilioSMS();
            return new Common(Code.OK, null, "Đã gửi SMS thành công");
        }catch (Exception e){
            System.err.println("ERROR in SMS: " + e);
            return new Common(Code.INVALID, null, "Vui lòng kiểm tra lại!");
        }
    }

    private void sendTwilioSMS(){
        TwilioUtil sms = new TwilioUtil();
        String message = "Bạn đã thanh toán thành công! Nhà hàng Thành Văn xin trân thành cảm ơn!";
        sms.sendSMS("+84356371760", message);
    }
}
