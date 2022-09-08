package com.ou.restaurantmanagement.Pojos;


import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "user_id_card", nullable = false, length = 12)
    private String userIdCard;

    @Column(name = "user_phone_number", nullable = false, length = 10)
    private String userPhoneNumber;

    @Column(name = "user_sex")
    private Boolean userSex;

    @Column(name = "user_last_name", nullable = false, length = 50)
    private String userLastName;

    @Column(name = "user_first_name", nullable = false, length = 20)
    private String userFirstName;

    @Column(name = "user_date_of_birth", nullable = false)
    private Date userDateOfBirth;

    @Column(name = "user_joined_date")
    private Date userJoinedDate;

    @Column(name = "user_username", nullable = false, length = 20)
    private String userUsername;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_is_active")
    private Boolean userIsActive;

    @Column(name = "user_role", length = 20)
    private String userRole;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_image", nullable = false)
    private String userImage;


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

    public String getUserUsename() {
        return userUsername;
    }

    public void setUserUsename(String userUsename) {
        this.userUsername = userUsename;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean getUserIsActive() {
        return userIsActive;
    }

    public void setUserIsActive(Boolean userIsActive) {
        this.userIsActive = userIsActive;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    /**
     * @return the userDateOfBirth
     */
    public Date getUserDateOfBirth() {
        return userDateOfBirth;
    }

    /**
     * @param userDateOfBirth the userDateOfBirth to set
     */
    public void setUserDateOfBirth(Date userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    /**
     * @return the userJoinedDate
     */
    public Date getUserJoinedDate() {
        return userJoinedDate;
    }

    /**
     * @param userJoinedDate the userJoinedDate to set
     */
    public void setUserJoinedDate(Date userJoinedDate) {
        this.userJoinedDate = userJoinedDate;
    }

    /**
     * @return the userAddress
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * @param userAddress the userAddress to set
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
