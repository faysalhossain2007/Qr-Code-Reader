package com.example.qrreader.localdatabase;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.qrreader.bean.User;

public class UserDB {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_USER_NAME = "username";
	public static final String KEY_EMAIL = "email";

	private static final String DATABASE_NAME = "localdatabase";
	private static final String DATABASE_TABLE = "qrreader";
	private static final int DATABASE_VERSION = 1;

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	private SQLiteDatabase sqLiteDatabase;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " ("
					+ KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_USER_NAME + " TEXT NOT NULL, " + KEY_EMAIL
					+ " TEXT NOT NULL);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}

	public UserDB(Context c) {
		ourContext = c;
	}

	public UserDB open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}

	public long createUserEntry(String username, String email) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();

		cv.put(KEY_USER_NAME, username);

		cv.put(KEY_EMAIL, email);

		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public boolean queryEntry(String username, String email)
			throws SQLException {

		String[] columns = new String[] { KEY_ROWID, KEY_USER_NAME, KEY_EMAIL };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_EMAIL + "=?",
				new String[] { email }, null, null, null);

		if (c.moveToFirst() && c != null) {
			if (username.equals(c.getString(1)) && email.equals(c.getString(2))) {
				return true;

			}

		}
		return false;
	}

	
	public ArrayList<User> queryAllEntry() {
		String[] columns = new String[] { KEY_ROWID, KEY_USER_NAME, KEY_EMAIL };
		String[] getAllData = null;
		ArrayList<User>user=new ArrayList<User>();
		
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			User us=new User();
			us.user_name=c.getString(1);
			us.email=c.getString(2);
			user.add(us);				
		}
		return user;
	}

	public void deleteEntry(long lRow1) throws SQLException {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
	}

}
