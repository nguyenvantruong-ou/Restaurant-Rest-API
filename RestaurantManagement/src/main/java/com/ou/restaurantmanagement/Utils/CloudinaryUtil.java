package com.ou.restaurantmanagement.Utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

//@PropertySource("classpath:cloudinary.properties")
public class CloudinaryUtil{
//    @Autowired
//    private static Environment env;

    private static final String CLOUDINARY_CLOUD_NAME = "nguyenvantruong";
    private static final String CLOUDINARY_API_KEY = "916527917943554";
    private static final String CLOUDINARY_API_SECRET = "DiAnTXiMDDJ1JQ3dubTXH6jjNdg";

    public static Cloudinary getCloudinaryClient() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUDINARY_CLOUD_NAME,
                "api_key", CLOUDINARY_API_KEY,
                "api_secret", CLOUDINARY_API_SECRET,
                "secure", true));
    }

}
