package com.faysal.qrcodereader;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ZxingExampleActivity extends Activity {
	/** Called when the activity is first created. */
	public static final int REQUEST_CODE = 0x0000c0de;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		Button btnCheck = (Button) findViewById(R.id.btnCheck);
		btnCheck.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(getApplicationContext(),
				// CaptureActivity.class);
				// intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				// // startActivityForResult(intent, 0);
				// // Intent intent = new Intent(
				// // "com.google.zxing.client.android.SCAN");
				// // intent.putExtra(
				// // "com.google.zxing.client.android.SCAN.SCAN_MODE",
				// // "QR_CODE_MODE");
				// startActivityForResult(intent, 0);

				// IntentIntegrator integrator = new IntentIntegrator(
				// ZxingExampleActivity.this);
				// integrator.initiateScan();
				String packageName = "com.faysal.qrcodereader";
				Intent intent = new Intent(
						"com.google.zxing.client.android.SCAN");
				intent.setPackage(packageName);
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				startActivityForResult(intent, 0);

			}
		});

		Button btnInsert = (Button) findViewById(R.id.btnInsert);
		btnInsert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						DbInput.class);
				startActivity(intent);
			}
		});

		Button btnView = (Button) findViewById(R.id.btnView);
		btnView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						UserViewList.class);
				startActivity(intent);
			}
		});
	}

	public IntentResult parseActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			// if (resultCode == RESULT_OK) {
			// String contents = intent.getStringExtra("SCAN_RESULT");
			// String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
			// Log.i("xZing", "contents: " + contents + " format: " + format);
			// // Handle successful scan
			// Toast.makeText(getApplicationContext(),
			// "Scan Result:" + contents, Toast.LENGTH_SHORT).show();
			// } else if (resultCode == RESULT_CANCELED) {
			// // Handle cancel
			// Log.i("xZing", "Cancelled");
			// }

			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				String formatName = intent.getStringExtra("SCAN_RESULT_FORMAT");
				byte[] rawBytes = intent.getByteArrayExtra("SCAN_RESULT_BYTES");
				int intentOrientation = intent.getIntExtra(
						"SCAN_RESULT_ORIENTATION", Integer.MIN_VALUE);
				Integer orientation = intentOrientation == Integer.MIN_VALUE ? null
						: intentOrientation;
				String errorCorrectionLevel = intent
						.getStringExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL");
				return new IntentResult(contents, formatName, rawBytes,
					orientation, errorCorrectionLevel);
			}
			return new IntentResult();
		}
		return null;
	}

	// public void onActivityResult(int requestCode, int resultCode, Intent
	// intent) {
	// IntentResult scanResult = IntentIntegrator.parseActivityResult(
	// requestCode, resultCode, intent);
	// if (resultCode == RESULT_OK) {
	// if (scanResult != null) {
	// // handle scan result
	// String scanresult = intent.getStringExtra("SCAN_RESULT");
	// try{
	// UserDB userdb = new UserDB(getApplicationContext());
	// userdb.open();
	// String[] result = scanresult.split(" ");
	// String email = result[1];
	// String username = result[0];
	//
	// boolean queryresult = userdb.queryEntry(username, email);
	// if (queryresult) {
	// Toast.makeText(getApplicationContext(), "Valid",
	// Toast.LENGTH_SHORT).show();
	// } else {
	// Toast.makeText(getApplicationContext(), "Invalid",
	// Toast.LENGTH_SHORT).show();
	// }
	//
	// userdb.close();
	// }
	// catch(Exception e){
	//
	// }
	//
	// }
	// }
	// else continue with any other code you need in the method

	// }

	// public void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// String contents = null;
	// super.onActivityResult(requestCode, resultCode, data);
	// if (requestCode == 0) {
	// if (resultCode == RESULT_OK) {
	// contents = data.getStringExtra("SCAN_RESULT");
	// String format = data.getStringExtra("SCAN_RESULT_FORMAT");
	// //moved here
	// Log.e("my format ",contents);
	// // Handle successful scan
	// } else if (resultCode == RESULT_CANCELED) {
	// // Handle cancel
	// }
	// }
	//
	// }
}
