package com.project.simulaturandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 
 * @author ProBook 450g2
 *
 */
public class FragmentTab extends Fragment implements View.OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_layout, container, false);
		TextView tv = (TextView) v.findViewById(R.id.text);
		TextView tv2 = (TextView) v.findViewById(R.id.text2);
		TextView tv3 = (TextView) v.findViewById(R.id.text3);
		TextView tv4 = (TextView) v.findViewById(R.id.text4);

		tv.setText("Conférence national sur le climat");
		tv2.setText("Atelier de formation");
		tv3.setText("Journée portes ouvertes");
		tv4.setText("Conférence national sur le autre chose");
		tv.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv3.setOnClickListener(this);
		tv4.setOnClickListener(this);

		return v;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text:
			Intent browserIntent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.google.com"));
			startActivity(browserIntent);
			break;
		case R.id.text2:
			Intent browserIntent2 = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.google.com"));
			startActivity(browserIntent2);
			break;
		case R.id.text3:
			Intent browserIntent3 = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.google.com"));
			startActivity(browserIntent3);
			break;
		case R.id.text4:
			Intent browserIntent4 = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.google.com"));
			startActivity(browserIntent4);
			break;
		}
	}
}
