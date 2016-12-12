package com.se.gtharris.isu_atm.models;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditCard {
    @SerializedName("card_number")
    private long credit_number;
    @SerializedName("security_code")
    private int security_code;
    @SerializedName("expiration_date")
    private String expiration_date;
    @SerializedName("pin")
    private int pin;

    public CreditCard(long credit_number, int security_code, String expiration_date, int pin) {
        this.credit_number = credit_number;
        this.security_code = security_code;
        this.expiration_date = expiration_date;
        this.pin = pin;
    }

    public long getCredit_number() {
        return credit_number;
    }

    public void setCredit_number(long credit_number) {
        this.credit_number = credit_number;
    }

    public int getSecurity_code() {
        return security_code;
    }

    public void setSecurity_code(int security_code) {
        this.security_code = security_code;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }



}
