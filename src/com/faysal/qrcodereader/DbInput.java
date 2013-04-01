package com.faysal.qrcodereader;

import java.util.regex.Pattern;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qrreader.localdatabase.UserDB;


public class DbInput extends Activity {
	private Button btnDone;
	private SQLiteDatabase sqLiteDatabase;
	private UserDB userdb;
	public final Pattern EMAIL_ADDRESS_PATTERN = Pattern
			.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
					+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
					+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

	private long dbInsertResult;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		final EditText edtUsername = (EditText) findViewById(R.id.edtUsername);
		final EditText edtEmail = (EditText) findViewById(R.id.edtEmail);
		Log.e("input:", edtEmail.isInputMethodTarget() + "");

		btnDone = (Button) findViewById(R.id.btnDone);
		btnDone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username, email;
				username = edtUsername.getText().toString();
				email = edtEmail.getText().toString();

				if (!username.isEmpty() && !email.isEmpty() && checkEmail(email)) {

					userdb = new UserDB(getApplicationContext());
					userdb.open();
					dbInsertResult = userdb.createUserEntry(username, email);
					Log.e("username", username);
					userdb.close();
					finish();
				} else {
					Toast.makeText(getApplicationContext(),
							"Please Enter The query in the Right format",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}
	private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
}

}