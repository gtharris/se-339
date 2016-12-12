package com.se.gtharris.isu_atm;



import android.util.Log;

import com.se.gtharris.isu_atm.models.AccountResults;
import com.se.gtharris.isu_atm.rest.APIClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ATM {
    private static final ATM data = new ATM();

    private AccountResults accountResults;

    public static ATM getInstance(){
        return data;
    }
    public void setAccountResults(AccountResults accountResults) {
        this.accountResults = accountResults;
    }
    public AccountResults getAccountResults() {
        return accountResults;
    }
    public void logout(){
        Call<ResponseBody> call = APIClient.get().endSession(accountResults.getToken());

        call.enqueue(new Callback<ResponseBody>() {


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("APIPlug", "Error Occured: " + t.getMessage());

                //loading.dismiss();
            }

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("APIPlug", "Successfully logout");
            }
        });
    }
}