package com.se.gtharris.isu_atm;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.se.gtharris.isu_atm.models.AccountResults;

public class BalanceActivity extends AppCompatActivity {
    TextView balanceAMT1;
    TextView balanceAMT2;
    TextView account1;
    TextView account2;

    AccountResults results = ATM.getInstance().getAccountResults() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_balance);
        balanceAMT1 = (TextView) findViewById(R.id.balanceAmt1);
        account1 = (TextView) findViewById(R.id.account1);
        balanceAMT1.setText("$" +ATM.getInstance().getAccountResults().getAccounts().get(0).getTotalMoney());
        account1.setText(ATM.getInstance().getAccountResults().getAccounts().get(0).getAccount_type());

        account2 = (TextView) findViewById(R.id.account2);
        balanceAMT2 = (TextView) findViewById(R.id.balanceAmt2);
        account2.setText( ATM.getInstance().getAccountResults().getAccounts().get(1).getAccount_type());
        balanceAMT2.setText("$" +ATM.getInstance().getAccountResults().getAccounts().get(1).getTotalMoney());

    }


    public void baseButtonCheck (View view) {
        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
    }
    public void logoutButton (View view) {
        ATM.getInstance().logout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
