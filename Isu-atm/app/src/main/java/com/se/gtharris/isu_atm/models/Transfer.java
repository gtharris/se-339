package com.se.gtharris.isu_atm.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transfer {

    @SerializedName("target_account_id")
    @Expose
    private long targetAccountId;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    /**
     *
     * @return
     * The targetAccountId
     */
    public long getTargetAccountId() {
        return targetAccountId;
    }

    /**
     *
     * @param targetAccountId
     * The target_account_id
     */
    public void setTargetAccountId(long targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

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