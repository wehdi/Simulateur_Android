package com.project.simulaturandroid;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.project.agent.Agent_Gestion;
import com.project.metier.Beans;

/**
 * 
 * @author ProBook 450g2
 *
 */

public class CreatGroupe extends Activity implements View.OnClickListener {

	private Spinner spinner;
	private ArrayAdapter<CharSequence> adapter;
	private Button buttonCreer;
	private Button buttonSelectDate;
	private TimePicker timePicker;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int min;

	private Calendar calendar;

	static final int DATE_PICKER_ID = 999;
	private Agent_Gestion agentGestion;

	/**
 * 
 */
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_creatgroupe_layout);
		super.onCreate(savedInstanceState);
		/**
		 * Buttons
		 */
		buttonCreer = (Button) findViewById(R.id.buttonCreer);
		buttonCreer.setOnClickListener(this);
		buttonSelectDate = (Button) findViewById(R.id.buttonSelect);
		buttonSelectDate.setOnClickListener(this);
		/**
		 * Spinner
		 */
		spinner = (Spinner) findViewById(R.id.spinner);

		adapter = ArrayAdapter.createFromResource(this, R.array.module_array,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		// /setOnItemSelectedListener(this);
		spinner.setOnItemSelectedListener(new SpinnerActivity());

		/**
		 * TimePicker pour la gestion des heurs
		 */
		timePicker = (TimePicker) findViewById(R.id.timePicker);
		timePicker.setIs24HourView(true);
		timePicker.setCurrentHour(Calendar.getInstance().get(
				Calendar.HOUR_OF_DAY));

		/**
		 * Recupere la date actuelle
		 */
		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);

		this.agentGestion = Beans.getAgentGestion();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		/**
		 * Button qui afficher le datePicker
		 */
		case R.id.buttonSelect:
			showDialog(DATE_PICKER_ID);
			break;
		/**
		 * Boutton envoi la creatin de groupe
		 */
		case R.id.buttonCreer:
			hour = timePicker.getCurrentHour();
			min = timePicker.getCurrentMinute();
			this.agentGestion.creatGroupe(
					String.valueOf(spinner.getSelectedItem()), year + " " + " "
							+ month + " " + day, hour + ":" + min,
					Beans.getLogin());
			Toast.makeText(getApplicationContext(),
					"Le groupe a bien été créer " + Beans.getLogin(),
					Toast.LENGTH_SHORT).show();
			finish();
			break;
		default:
		}
	}

	/**
	 * Actiellement ne fait rien
	 * 
	 * @author ProBook 450g2
	 *
	 */
	private class SpinnerActivity implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			/*
			 * Toast.makeText(parent.getContext(), "On Item Select : \n" +
			 * parent.getItemAtPosition(pos).toString(),
			 * Toast.LENGTH_LONG).show();
			 */
		}

		public void onNothingSelected(AdapterView<?> parent) {
			// Another interface callback
		}
	}

	/**
	 * 
	 */
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_PICKER_ID:

			// open datepicker dialog.
			// set date picker for current date
			// add pickerListener listner to date picker
			return new DatePickerDialog(this, pickerListener, year, month, day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

		}
	};

}
