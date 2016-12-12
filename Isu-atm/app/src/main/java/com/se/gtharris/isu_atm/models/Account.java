package com.se.gtharris.isu_atm.models;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("bank")
    @Expose
    private String bank;
    @SerializedName("interest")
    @Expose
    private String interest;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("pin")
    @Expose
    private Integer pin;
    @SerializedName("nickname")
    @Expose
    private String nickname;

    public Double getTotalMoney() {
        return totalMoney;
    }

    @SerializedName("total_money")
    @Expose
    private Double totalMoney;

    public Account(String bank, String interest, String owner,  String nickname, Double totalMoney, String account_type, Long id) {
        this.bank = bank;
        this.interest = interest;
        this.owner = owner;

        this.nickname = nickname;
        this.totalMoney = totalMoney;
        this.account_type = account_type;
        this.id = id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    private String account_type;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    /**
     *
     * @return
     * The bank
     */
    public String getBank() {
        return bank;
    }

    /**
     *
     * @param bank
     * The bank
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     *
     * @return
     * The interest
     */
    public String getInterest() {
        return interest;
    }

    /**
     *
     * @param interest
     * The interest
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }

    /**
     *
     * @return
     * The owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     * The owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     *
     * @return
     * The pin
     */
    public Integer getPin() {
        return pin;
    }

    /**
     *
     * @param pin
     * The pin
     */
    public void setPin(Integer pin) {
        this.pin = pin;
    }

    /**
     *
     * @return
     * The nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @param nickname
     * The nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


}
