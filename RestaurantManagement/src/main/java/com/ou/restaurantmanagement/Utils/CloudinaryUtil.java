package com.ou.restaurantmanagement.Utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

//@PropertySource("classpath:cloudinary.properties")
public class CloudinaryUtil{
//    @Autowired
//    private static Environment env;

    private static final String CLOUDINARY_CLOUD_NAME = "dfgdkopg4";
    private static final String CLOUDINARY_API_KEY = "813944693655736";
    private static final String CLOUDINARY_API_SECRET = "eA4yMUxXxo_s-RhTwLaLrcsit2c";

    public static Cloudinary getCloudinaryClient() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUDINARY_CLOUD_NAME,
                "api_key", CLOUDINARY_API_KEY,
                "api_secret", CLOUDINARY_API_SECRET,
                "secure", true));
    }

    public static String upImage(MultipartFile f){
        Cloudinary cloudinary = CloudinaryUtil.getCloudinaryClient();
        try {
            Map r = cloudinary.uploader().upload(f.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            return (String) r.get("secure_url");
        } catch (IOException e) {
            return null;
        }
    }
}
