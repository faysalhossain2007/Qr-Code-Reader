package com.example.qrreader.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.qrreader.bean.User;
import com.faysal.qrcodereader.R;


public class UserAdapter extends ArrayAdapter<User> {

	private ArrayList<User> entries;
	private Context context;

	public UserAdapter(Context a, int textViewResourceId,
			ArrayList<User> entries) {
		super(a, textViewResourceId, entries);
		this.entries = entries;
		this.context = a;
	}

	public static class ViewHolder {
		public TextView item1;
		public TextView item2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ViewHolder holder;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.grid_item, null);
			holder = new ViewHolder();
			holder.item1 = (TextView) v.findViewById(R.id.big);
			holder.item2 = (TextView) v.findViewById(R.id.small);
			v.setTag(holder);
		} else
			holder = (ViewHolder) v.getTag();

		final User user = entries.get(position);
		if (user != null) {
			holder.item1.setText(user.user_name);
			holder.item2.setText(user.email);
		}
		return v;
	}
}
