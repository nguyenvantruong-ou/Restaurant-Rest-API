package com.ou.restaurantmanagement.DTO.Response;

import com.ou.restaurantmanagement.Pojos.Staff;
import com.ou.restaurantmanagement.Pojos.User;

import java.util.Date;

public class UserDetailResponse {
    private Integer id;
    private String userIdCard;
    private String userPhoneNumber;
    private Boolean userSex;
    private String userLastName;
    private String userFirstName;
    private Date userDateOfBirth;
    private Date userJoinedDate;
    private String userUsername;
    private String userRole;
    private String userEmail;
    private String userAddress;
    private String userImage;
    private String branchName = null;
    private String branchAddress = null;
    private String typeStaff = null;

    public UserDetailResponse(User user, Staff staff){
        id = user.getId();
        userIdCard = user.getUserIdCard();
        userPhoneNumber = user.getUserPhoneNumber();
        userSex = user.getUserSex();
        userLastName = user.getUserLastName();
        userFirstName = user.getUserFirstName();
        userDateOfBirth = user.getUserDateOfBirth();
        userJoinedDate = user.getUserJoinedDate();
        userUsername = user.getUserUsename();
        userRole = user.getUserRole();
        userEmail = user.getUserEmail();
        userAddress = user.getUserAddress();
        userImage = user.getUserImage();
        if(staff.getId() != null) {
            branchName = staff.getBranch().getBranchName();
            branchAddress = staff.getBranch().getBranchAddress();
            typeStaff = staff.getTypeStaff().getTypeStaffName();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getRole() {
        return userRole;
    }

    public void setRole(String role) {
        userRole = role;
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getTypeStaff() {
        return typeStaff;
    }

    public void setTypeStaff(String typeStaff) {
        this.typeStaff = typeStaff;
    }
}
