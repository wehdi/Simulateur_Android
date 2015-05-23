package com.project.simulaturandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.project.agent.Agent_Gestion;
import com.project.metier.Beans;

/**
 * 
 * @author ProBook 450g2
 *
 */
public class HelpMe extends Activity implements View.OnClickListener {
	private Button buttonHelpMe;
	private EditText editHelpMe;
	private Spinner spinner;
	private Agent_Gestion agentGestion;
	private ArrayAdapter<CharSequence> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_helpeme_layout);
		super.onCreate(savedInstanceState);
		buttonHelpMe = (Button) findViewById(R.id.buttonSendHelpMe);
		buttonHelpMe.setOnClickListener(this);
		//
		editHelpMe = (EditText) findViewById(R.id.editTextHelpMe);
		//
		spinner = (Spinner) findViewById(R.id.spinner);
		adapter = ArrayAdapter.createFromResource(this, R.array.module_array,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		this.agentGestion = Beans.getAgentGestion();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonSendHelpMe:
			this.agentGestion
					.sendHelpMe(editHelpMe.getText().toString(),
							String.valueOf(spinner.getSelectedItem()),
							Beans.getLogin());
			break;
		default:
		}

	}

}
