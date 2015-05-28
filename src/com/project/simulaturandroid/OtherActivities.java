package com.project.simulaturandroid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

/**
 * Classe qui gére les activité
 * 
 * @author ProBook 450g2
 *
 */
public class OtherActivities extends FragmentActivity {
	private FragmentTabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_other_layout);
		tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(),
				android.R.id.tabcontent);

		tabHost.addTab(
				tabHost.newTabSpec("Evenement").setIndicator("Evenement", null),
				FragmentTab.class, null);
		tabHost.addTab(
				tabHost.newTabSpec("Social").setIndicator("Social ", null),
				FragmentTab2.class, null);
	
	}
}
