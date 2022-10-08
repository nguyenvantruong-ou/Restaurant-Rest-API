package com.ou.restaurantmanagement.DTO.Response;

import java.util.List;

public class CommentStatisticResponse implements IBaseResponse{
    private double avgStar;
    private int amountTotalComment;
    private int amount1Star;
    private int amount2Star;
    private int amount3Star;
    private int amount4Star;
    private int amount5Star;
    private List<CommentResponse> listComment;

    public double getAvgStar() {
        return avgStar;
    }

    public void setAvgStar(double avgStar) {
        this.avgStar = avgStar;
    }

    public int getAmountTotalComment() {
        return amountTotalComment;
    }

    public void setAmountTotalComment(int amountTotalComment) {
        this.amountTotalComment = amountTotalComment;
    }

    public int getAmount1Star() {
        return amount1Star;
    }

    public void setAmount1Star(int amount1Star) {
        this.amount1Star = amount1Star;
    }

    public int getAmount2Star() {
        return amount2Star;
    }

    public void setAmount2Star(int amount2Star) {
        this.amount2Star = amount2Star;
    }

    public int getAmount3Star() {
        return amount3Star;
    }

    public void setAmount3Star(int amount3Star) {
        this.amount3Star = amount3Star;
    }

    public int getAmount4Star() {
        return amount4Star;
    }

    public void setAmount4Star(int amount4Star) {
        this.amount4Star = amount4Star;
    }

    public int getAmount5Star() {
        return amount5Star;
    }

    public void setAmount5Star(int amount5Star) {
        this.amount5Star = amount5Star;
    }

    public List<CommentResponse> getListComment() {
        return listComment;
    }

    public void setListComment(List<CommentResponse> listComment) {
        this.listComment = listComment;
    }
}
