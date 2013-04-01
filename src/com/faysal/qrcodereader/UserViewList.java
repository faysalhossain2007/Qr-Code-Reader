package com.faysal.qrcodereader;

import java.util.ArrayList;

import android.app.Activity;
import android.content.CursorLoader;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qrreader.adapter.UserAdapter;
import com.example.qrreader.bean.User;
import com.example.qrreader.localdatabase.UserDB;


public class UserViewList extends Activity {

	private UserDB userdb;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userviewlist);

		ArrayList<User> user = new ArrayList<User>();
		userdb = new UserDB(getApplicationContext());

		userdb.open();
		user = userdb.queryAllEntry();
		userdb.close();
		
		

		ListView userList = (ListView) findViewById(R.id.listview);
////		userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////
////			public void onItemClick(AdapterView<?> arg0, View contactItem,
////					int arg2, long arg3) {
////				TextView contactName = (TextView) contactItem
////						.findViewById(R.id.user_name);
////				contactName.setOnClickListener(new View.OnClickListener() {
////
////					public void onClick(View v) {
////
////					}
////				});
////			}
//
//		});
		UserAdapter adapter = new UserAdapter(getApplicationContext(),
				R.id.listview, user);
		userList.setAdapter(adapter);
		// ArrayAdapter<User> arrayAdapter = new ArrayAdapter<User>(this,
		// android.R.layout.simple_list_item_1, user);
		// userList.setAdapter(arrayAdapter);

	}

}
