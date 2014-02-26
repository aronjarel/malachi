package com.paypal.example.paypalandroidsdkexample;

import java.math.BigDecimal;
import java.util.Map;

import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

@SuppressLint("NewApi")
public class MyCalculatorActivity extends Activity {

	private static final String CONFIG_ENVIRONMENT = PaymentActivity.ENVIRONMENT_NO_NETWORK;
    
    // note that these credentials will differ between live & sandbox environments.
    private static final String CONFIG_CLIENT_ID = "credential from developer.paypal.com";
    // when testing in sandbox, this is likely the -facilitator email address. 
    private static final String CONFIG_RECEIVER_EMAIL = "matching paypal email address"; 
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_calculator);
		
		Intent intent2 = getIntent();
	    String calc_amount = intent2.getStringExtra("CALCULATED_AMOUNT");
	    
	    Log.d("TESTING", "Amount is " + calc_amount);
	    
	    EditText editText = (EditText) findViewById(R.id.make_pymt);
	    if (calc_amount != null){
	    	editText.setText(calc_amount);
	    }
		
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        */
		/*
        Intent intent = new Intent(this, PayPalService.class);
        
        intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT, CONFIG_ENVIRONMENT);
        intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, CONFIG_CLIENT_ID);
        intent.putExtra(PaymentActivity.EXTRA_RECEIVER_EMAIL, CONFIG_RECEIVER_EMAIL);
        
        startService(intent);
        */
	}
	
	 public void makePayment(View pressed) {
		 	EditText editText = (EditText) findViewById(R.id.make_pymt);
		    String amount = editText.getText().toString();
		    if (amount == null || amount.trim().equals("")){
		    	amount = "0.00";
		    }
		    
		    
		    
		 
		    PayPalPayment thingToBuy;
		    
		    Intent intent2 = getIntent();
		    String calc_amount = intent2.getStringExtra("CALCULATED_AMOUNT");
	        
	        Intent intent = new Intent(this, PaymentActivity.class);
	       // Bundle extras = intent.getExtras();
	        //String calc_amount = (String)extras.get("CALCULATED_AMOUNT");
	        Log.i("AMOUNT FROM INTENT", ""+calc_amount);
	        
	        if (calc_amount == null){
	        	thingToBuy = new PayPalPayment(new BigDecimal(amount), "USD", "Pay tithe");
	        }
	        else {
	        	thingToBuy = new PayPalPayment(new BigDecimal(Double.parseDouble(calc_amount)), "USD", "Pay tithe");
	        }
	        intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT, CONFIG_ENVIRONMENT);
	        intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, CONFIG_CLIENT_ID);
	        intent.putExtra(PaymentActivity.EXTRA_RECEIVER_EMAIL, CONFIG_RECEIVER_EMAIL);
	        
	        // It's important to repeat the clientId here so that the SDK has it if Android restarts your 
	        // app midway through the payment UI flow.
	        intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, CONFIG_CLIENT_ID);
	        intent.putExtra(PaymentActivity.EXTRA_PAYER_ID, "your-customer-id-in-your-system");
	        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
	        
	        startActivityForResult(intent, 0);
	    }
	 
	 public void intent_calc_pymt(View pressed){
		 Intent intent = new Intent(this, Calculator.class);
		 
		 startActivity(intent);
	 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_calculator, menu);
		return true;
	}

}
