package com.ou.restaurantmanagement.Utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

public class TwilioUtil {
    private final static String Account_SID = "ACe99d5799034744861dac49ceab073157";
    private final static String Auth_Token = "c53d73bc80c50bdf63487139d07e84cf";
    private final static String FROM = "+19362462444";

    public TwilioUtil(){
        Twilio.init(Account_SID, Auth_Token);
    }

    public void sendSMS(String toPhoneNumber, String message){
        PhoneNumber to = new PhoneNumber(toPhoneNumber);
        PhoneNumber from = new PhoneNumber(FROM);
        MessageCreator creator =  Message.creator(to, from , message);
        creator.create();
    }
}
