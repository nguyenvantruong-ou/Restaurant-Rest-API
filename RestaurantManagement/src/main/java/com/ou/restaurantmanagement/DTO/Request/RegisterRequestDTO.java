package com.ou.restaurantmanagement.DTO.Request;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class RegisterRequestDTO implements IBaseRequest{
    private MultipartFile file;

    private String userIdCard;

    private String userPhoneNumber;

    private Boolean userSex;

    private String userLastName;

    private String userFirstName;

    private Date userDateOfBirth;

    private Date userJoinedDate = new Date();

    private String userUsename;

    private String userPassword;

    private String userEmail;

    private String userAddress;

    private String userImage = "https://res.cloudinary.com/dfgdkopg4/image/upload/v1664987305/default_avatar_uhg35a.png";

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Boolean getUserSex() {
        return userSex;
    }

    public void setUserSex(Boolean userSex) {
        this.userSex = userSex;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public Date getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(Date userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public Date getUserJoinedDate() {
        return userJoinedDate;
    }

    public void setUserJoinedDate(Date userJoinedDate) {
        this.userJoinedDate = userJoinedDate;
    }

    public String getUserUsename() {
        return userUsename;
    }

    public void setUserUsename(String userUsename) {
        this.userUsename = userUsename;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
