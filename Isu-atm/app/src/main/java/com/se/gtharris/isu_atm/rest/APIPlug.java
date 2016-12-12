package com.se.gtharris.isu_atm.rest;


import com.se.gtharris.isu_atm.models.CreditCard;
import com.se.gtharris.isu_atm.models.Deposit;
import com.se.gtharris.isu_atm.models.Transfer;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.PUT;
import retrofit2.http.Headers;


public interface APIPlug {


    /*
    These methods defines our API endpoints.
    All REST methods such as GET,POST,UPDATE,DELETE can be stated in here.
    */
    @Headers({"Content-Type: application/json"})
    @POST("/api/atm/USBank/start_session/")
    Call<ResponseBody> startSession(@Body CreditCard user);

    @Headers({"Content-Type: application/json"})
    @POST("api/atm/{token}/end/")
    Call<ResponseBody> endSession(@Path("token") String token);

    @Headers({"Content-Type: application/json"})
    @GET("/api/account/{customer_id}/")
    Call<ResponseBody> getAccount(@Path("customer_id") int customer_id, @Header("X-Api-ATM-Key") String token);

    @Headers({"Content-Type: application/json"})
    @PUT("/api/account/{account_id}/transfer/")
    Call<ResponseBody> getTransfer(@Path("account_id") long account_id, @Header("X-Api-ATM-Key") String token, @Body Transfer transfer);

    @Headers({"Content-Type: application/json"})
    @PUT("/api/account/{account_id}/withdraw/")
    Call<ResponseBody> putWithdraw(@Path("account_id") long account_id,@Header("X-Api-ATM-Key") String token, @Body Deposit withdrawAmt);

    @Headers({"Content-Type: application/json"})
    @PUT("/api/account/{account_id}/deposit/")
    Call<ResponseBody> putDeposit(@Path("account_id") long account_id, @Header("X-Api-ATM-Key") String token, @Body Deposit depositAmt);


}