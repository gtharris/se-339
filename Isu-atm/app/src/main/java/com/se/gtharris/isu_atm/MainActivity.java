package com.se.gtharris.isu_atm;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.stetho.Stetho;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.se.gtharris.isu_atm.models.Account;
import com.se.gtharris.isu_atm.models.AccountResults;
import com.se.gtharris.isu_atm.models.CreditCard;
import com.se.gtharris.isu_atm.rest.APIClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    EditText cc_number;
    EditText pin;
    EditText expiration;
    EditText security;
    Button buttonSubmit;
    AccountResults results = new AccountResults();
    CreditCard creditcard = new CreditCard(4111111111111111L,415,"12/18",4567);

    Boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);
        cc_number   = (EditText)findViewById(R.id.cc_number);
        pin   = (EditText)findViewById(R.id.pin);
        expiration   = (EditText)findViewById(R.id.expiration);
        security   = (EditText)findViewById(R.id.security);

        buttonSubmit = (Button)findViewById(R.id.buttonSubmit);


    }
    public void buttonSubmit (View view) {
        /*if(cc_number.getText().toString()!="")
        creditcard.setCredit_number(Long.parseLong(cc_number.getText().toString()));
        creditcard.setSecurity_code(Integer.parseInt(security.getText().toString()));
        creditcard.setPin(Integer.parseInt(pin.getText().toString()));
        creditcard.setExpiration_date(expiration.getText().toString());
*/
        authenticate();


        Log.d("APIPlug", status.toString());





    }
    public void connectToPush(){

        if(status) {

            Intent intent = new Intent(this, BaseActivity.class);
            startActivity(intent);
        }

    }

    private boolean authenticate(){

        Call<ResponseBody> call = APIClient.get().startSession(creditcard);

        call.enqueue(new Callback<ResponseBody>() {


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("APIPlug", "Error Occured: " + t.getMessage());


            }

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("APIPlug", "Successfully response fetched" );

                //loading.dismiss();
                if(response.code()==200){
                    status = true;

                }


                if(response!= null&&response.code() ==200) {
                   String json = "";
                    AccountResults accountResponse= new AccountResults();
                    ArrayList<Account> accountList = new ArrayList<Account>();


                    try {

                        json = response.body().string();
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            JSONArray accounts = obj.getJSONArray("accounts");
                            accountResponse.setToken(obj.getString("token"));
                            final int n = accounts.length();
                            for (int i = 0; i < n; ++i) {
                                JSONObject account = accounts.getJSONObject(i);
                                Account acct = new Account(account.getString("bank"),account.getString("interest"),account.getString("owner"), account.getString("nickname"), account.getDouble("total_money"),account.getString("account_type"),account.getLong("id"));

                                accountList.add(acct);
                                Log.d("APIPlug", "Response " +accountList.size() );

                            }
                            accountResponse.setAccounts(accountList);
                            ATM.getInstance().setAccountResults(accountResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    connectToPush();
                    Log.d("APIPlug", "Response " +response.code() );
                    status = true;

                }
                if(response.code()==400) {

                        if (!isFinishing()) {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Failed")
                                    .setMessage("Card Already in use at another atm!")
                                    .setCancelable(true)
                                    .show();
                        }
                    status = false;

                }
                if(response.code()==404) {
                    if (!isFinishing()) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Failed")
                                .setMessage("Card not found!")
                                .setCancelable(true)
                                .show();
                    }
                    status = false;

                }
                if(response.code()==500) {
                    if (!isFinishing()) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Failed")
                                .setMessage("Internal Server Error")
                                .setCancelable(true)
                                .show();
                    }
                    status = false;

                }


            }
        });
        return status;
    }

    public  void logout(){
        ATM.getInstance().logout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
