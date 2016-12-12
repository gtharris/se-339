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
import com.se.gtharris.isu_atm.models.Transfer;
import com.se.gtharris.isu_atm.rest.APIClient;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferActivity extends AppCompatActivity {

    AccountResults results = ATM.getInstance().getAccountResults() ;

    EditText transferAmt;
    EditText accountNumber;
    ToggleButton toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer);
        transferAmt  = (EditText)findViewById(R.id.transferAmt);
        accountNumber   = (EditText)findViewById(R.id.accountNumber);
        toggle = (ToggleButton) findViewById(R.id.toggleButton);

    }
    public void confirmButtonTransfer (View view) {
        int ammt = Integer.parseInt(transferAmt.getText().toString());
        long acct = Long.parseLong(accountNumber.getText().toString());
        Transfer transaction = new Transfer();
        transaction.setAmount(ammt);
        transaction.setTargetAccountId(acct);
        transferCall(transaction);

    }



    public void baseButtonTransfer (View view) {
        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
    }
    public void logoutButtonTransfer (View view) {
        ATM.getInstance().logout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void transferCall(final Transfer transaction){
        Call<ResponseBody> call;
        if(toggle.isChecked()) {
            call = APIClient.put().getTransfer(results.getAccounts().get(0).getId(), results.getToken(),transaction);
        }
        else{
            call = APIClient.put().getTransfer(results.getAccounts().get(1).getId(), results.getToken(),transaction);
        }


        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("APIPlug", "Error Occured: " + t.getMessage());
            }

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("APIPlug", "Successfully response fetched" );
                if (response.code() == 200) {

                    if (!isFinishing()) {
                        new AlertDialog.Builder(TransferActivity.this)
                                .setTitle("Success")
                                .setMessage(transaction.getAmount() + "$\nHas been withdrawn")
                                .setCancelable(true)
                                .show();
                    }

                } else {
                    if (!isFinishing()) {
                        new AlertDialog.Builder(TransferActivity.this)
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
