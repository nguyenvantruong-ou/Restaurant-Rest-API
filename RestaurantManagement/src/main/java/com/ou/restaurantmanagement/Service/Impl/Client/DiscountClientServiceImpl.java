package com.ou.restaurantmanagement.Service.Impl.Client;

import com.ou.restaurantmanagement.DTO.Constant.Code;
import com.ou.restaurantmanagement.DTO.Response.Common;
import com.ou.restaurantmanagement.Pojos.Discount;
import com.ou.restaurantmanagement.Repository.Client.DiscountClientRepository;
import com.ou.restaurantmanagement.Service.Client.DiscountClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class DiscountClientServiceImpl implements DiscountClientService {
    @Autowired
    private DiscountClientRepository _discountRepository;

    @Override
    public List<Discount> ReadDiscounts() {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        Calendar calendar = Calendar.getInstance(timeZone);
        Date now = calendar.getTime();
        return _discountRepository.ReadDiscounts(now);
    }
}
