package com.se.gtharris.isu_atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.se.gtharris.isu_atm.models.AccountResults;


public class BaseActivity extends AppCompatActivity {

    AccountResults results = ATM.getInstance().getAccountResults() ;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);

            username = (TextView) findViewById(R.id.username);
            username.setText(ATM.getInstance().getAccountResults().getAccounts().get(0).getOwner());


    }

    public void transferButton (View view) {
        Intent intent = new Intent(this, TransferActivity.class);
        startActivity(intent);
    }
    public void depositButton (View view) {
        Intent intent = new Intent(this, DepositActivity.class);
        startActivity(intent);
    }
    public void withdrawalButton (View view) {
        Intent intent = new Intent(this, WithdrawalActivity.class);
        startActivity(intent);
    }

    public void checkBalanceButton (View view) {
        Intent intent = new Intent(this, BalanceActivity.class);
        startActivity(intent);
    }
    public void logoutButton (View view) {

        ATM.getInstance().logout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
