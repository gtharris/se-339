package com.se.gtharris.isu_atm;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.se.gtharris.isu_atm.models.AccountResults;
import com.se.gtharris.isu_atm.models.Deposit;
import com.se.gtharris.isu_atm.rest.APIClient;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WithdrawalActivity extends AppCompatActivity {

    AccountResults results = ATM.getInstance().getAccountResults() ;
    EditText withdrawalAmt;
    ToggleButton toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withdrawal);
        withdrawalAmt   = (EditText)findViewById(R.id.withdrawalAmt);
        toggle = (ToggleButton) findViewById(R.id.toggleButton);

    }
    public void confirmButtonWith (View view) {
        int amount = Integer.parseInt(withdrawalAmt.getText().toString());
        Deposit withdrawalAmount = new Deposit();
        withdrawalAmount.setAmount(amount);
        withdrawalCall(withdrawalAmount);
    }



    public void baseButtonWith (View view) {
        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
    }
    public void logoutButtonWith (View view) {
        ATM.getInstance().logout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void withdrawalCall(final Deposit withdrawalAmount){
        Call<ResponseBody> call;
        if(toggle.isChecked()) {
            call = APIClient.put().putWithdraw(results.getAccounts().get(0).getId(), results.getToken(), withdrawalAmount);
        }
        else{
            call = APIClient.put().putWithdraw(results.getAccounts().get(1).getId(), results.getToken(), withdrawalAmount);
        }

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("APIPlug", "Error Occured: " + t.getMessage());
            }

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("APIPlug", "Successfully withdrawn");
                if (response.code() == 200) {

                    if (!isFinishing()) {
                        new AlertDialog.Builder(WithdrawalActivity.this)
                                .setTitle("Success")
                                .setMessage(withdrawalAmount.getAmount() + "$\nHas been withdrawn")
                                .setCancelable(true)
                                .show();
                    }

                } else {
                    if (!isFinishing()) {
                        new AlertDialog.Builder(WithdrawalActivity.this)
                                .setTitle("Failed")
                                .setMessage("Error!" + response.code())
                                .setCancelable(true)
                                .show();
                    }


                }
            }
        });


    }
}

