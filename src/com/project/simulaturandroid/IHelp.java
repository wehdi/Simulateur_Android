package com.project.simulaturandroid;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.project.metier.Beans;

/**
 * 
 * @author ProBook 450g2
 *
 */

public class IHelp extends ListActivity {
	private ArrayList<String> content;
	private ArrayList<String> content2;
	private ArrayList<String> title;
	private ArrayAdapter<String> adapter;
	private Beans bean;

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		bean = new Beans();
		content = new ArrayList<String>();
		content2 = new ArrayList<String>();
		title = new ArrayList<String>();
		content.addAll(bean.getContetnHelp());
		for (int i = 0; i < bean.getContetnHelp().size(); i++) {

			title.add(bean.getContetnHelp().get(i)
					.substring(0, bean.getContetnHelp().get(i).indexOf("|")));

			// bleme ici !!!!!!!!!!!!!
			content2.add(bean
					.getContetnHelp()
					.get(i)
					.substring(bean.getContetnHelp().get(i).indexOf("|")+1,
							bean.getContetnHelp().get(i).length()));
		}
		;
		// creer un adapter avec mon propre layout
		adapter = new ArrayAdapter<String>(this,
				R.layout.activity_ihelp_layout, R.id.label, title);
		setListAdapter(adapter);
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		new AlertDialog.Builder(this)
				.setTitle(item)
				.setMessage(content2.get(position))
				.setCancelable(true)
				.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
								dialog.cancel();
							}
						})

				.setIcon(android.R.drawable.ic_dialog_info).show();
	}
}
