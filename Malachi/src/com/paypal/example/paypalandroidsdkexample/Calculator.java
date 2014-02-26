package com.paypal.example.paypalandroidsdkexample;

import com.paypal.android.sdk.payments.PaymentActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Calculator extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculator, menu);
		return true;
	}

	public void calculate(View pressed){
		EditText salaryText = (EditText) findViewById(R.id.calc_pymt);
	    String salary = salaryText.getText().toString();
	    if (salary == null || salary.trim().equals("")){
	    	salary = "0.00";
	    }
	    
	    EditText percentText = (EditText) findViewById(R.id.percent);
	    String percent = percentText.getText().toString();
	    if (percent == null || percent.trim().equals("")){
	    	percent = "0.00";
	    }
	    
	    double interest = Double.parseDouble(salary) * (Double.parseDouble(percent) / 100);
	    double amount = Double.parseDouble(salary) + interest;
	    
	    String amountStr = String.valueOf(amount);
	    Intent intent = new Intent(this, MyCalculatorActivity.class);
	    Log.i("AMOUNT_INPUTTED", ""+amount);
	    intent.putExtra("CALCULATED_AMOUNT", amountStr);
	    
	    startActivity(intent);
	}
}
