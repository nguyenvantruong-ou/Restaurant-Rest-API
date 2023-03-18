package com.ou.restaurantmanagement.DTO.Request;

public class CreateTypeCustomerRequestDTO {
    private String typeCusName;
    private int typeCusPoints;
    private double typeCusDiscount;

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

}
