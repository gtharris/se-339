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

public class DepositActivity extends AppCompatActivity {

    AccountResults results = ATM.getInstance().getAccountResults() ;
    EditText depositAMT;
    ToggleButton toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deposit);
        depositAMT   = (EditText)findViewById(R.id.depositAMT);
        toggle = (ToggleButton) findViewById(R.id.toggleButton);
    }
    public void confirmButtonDeposit (View view) {

        int ammount = Integer.parseInt(depositAMT.getText().toString());
        Deposit deposit = new Deposit();
        deposit.setAmount(ammount);
        depositCall(deposit);

    }

    public void baseButtonDeposit (View view) {
        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
    }
    public void logoutButtonDeposit (View view) {
        ATM.getInstance().logout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void depositCall(final Deposit deposit){
        Call<ResponseBody> call;
        if(toggle.isChecked()) {
            call = APIClient.put().putDeposit(results.getAccounts().get(0).getId(), results.getToken(), deposit);
        }
        else{
             call = APIClient.put().putDeposit(results.getAccounts().get(1).getId(), results.getToken(), deposit);
        }
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("APIPlug", "Error Occured: " + t.getMessage());
            }

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("APIPlug", "Successfully deposited" );
                if(response.code()==200) {

                    if (!isFinishing()) {
                        new AlertDialog.Builder(DepositActivity.this)
                                .setTitle("Success")
                                .setMessage(deposit.getAmount() +"$\nHas been deposited")
                                .setCancelable(true)
                                .show();
                    }

                }
                else {
                    if (!isFinishing()) {
                        new AlertDialog.Builder(DepositActivity.this)
                                .setTitle("Failed")
                                .setMessage("Error!"+ response.code())
                                .setCancelable(true)
                                .show();
                    }


                }

            }
        });

    }
}
