package com.ou.restaurantmanagement.Pojos;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_token")
public class UserToken {
    @Id
    @Column(name = "user_token_id", nullable = false)
    private Integer id;

    @Column(name = "user_token", nullable = false)
    private String userToken;

    @Column(name ="user_token_created_date", nullable = false)
    private Date userTokenCreatedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public Date getUserTokenCreatedDate() {
        return userTokenCreatedDate;
    }

    public void setUserTokenCreatedDate(Date userTokenCreatedDate) {
        this.userTokenCreatedDate = userTokenCreatedDate;
    }
}
