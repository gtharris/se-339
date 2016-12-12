package com.se.gtharris.isu_atm.models;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deposit {

    @SerializedName("amount")
    private Integer amount;

    /**
     *
     * @return
     * The amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     * The amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
