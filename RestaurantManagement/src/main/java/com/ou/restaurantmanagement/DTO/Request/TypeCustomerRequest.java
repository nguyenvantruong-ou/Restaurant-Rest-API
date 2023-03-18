package com.ou.restaurantmanagement.DTO.Request;

public class TypeCustomerRequest {
    private int id;
    private String typeCusName;
    private int typeCusPoints;
    private double typeCusDiscount;
    private Boolean typeCusStatus;

    public String getTypeCusName() {
        return typeCusName;
    }

    public void setTypeCusName(String typeCusName) {
        this.typeCusName = typeCusName;
    }

    public int getTypeCusPoints() {
        return typeCusPoints;
    }

    public void setTypeCusPoints(int typeCusPoints) {
        this.typeCusPoints = typeCusPoints;
    }

    public double getTypeCusDiscount() {
        return typeCusDiscount;
    }

    public void setTypeCusDiscount(double typeCusDiscount) {
        this.typeCusDiscount = typeCusDiscount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getTypeCusStatus() {
        return typeCusStatus;
    }

    public void setTypeCusStatus(Boolean typeCusStatus) {
        this.typeCusStatus = typeCusStatus;
    }
}
