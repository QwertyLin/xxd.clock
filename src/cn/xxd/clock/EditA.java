package cn.xxd.clock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.xxd.clock.bean.Clock;
import cn.xxd.clock.bean.Clock.Week;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

public class EditA extends Activity implements OnClickListener {
	
	public static final String EXTRA_KEY = "key";
	
	private LayoutInflater inflater;
	
	Clock mClock;
	TextView vCycleValue, vTimeValue;
	FrameLayout layoutTime;
	
	private static final int 
		ID_CYCLE = 1, ID_DATE = 2, ID_TIME = 3, ID_DAY = 4, ID_WEEK = 5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println(Calendar.getInstance().getTimeInMillis());
		setContentView(R.layout.edit);
		inflater = getLayoutInflater();
		initData();
		//周期
		View layoutCycle = inflater.inflate(R.layout.form_select_1, (FrameLayout)findViewById(R.id.edit_cycle));
		View vCycle = layoutCycle.findViewById(R.id.form_select_1);
		vCycle.setId(ID_CYCLE);
		vCycle.setOnClickListener(this);
		((TextView)layoutCycle.findViewById(R.id.form_select_1_key)).setText("周期");	
		vCycleValue = (TextView)layoutCycle.findViewById(R.id.form_select_1_value);
		//时间
		layoutTime = (FrameLayout)findViewById(R.id.edit_time);
		//
	}
	
	private void initData(){
		Calendar calendar = Calendar.getInstance();
		mClock = new Clock();
		mClock.setCycle(Clock.Cycle.DAY);
		mClock.setYear(calendar.get(Calendar.YEAR));
		mClock.setMonth(calendar.get(Calendar.MONTH) + 1);
		mClock.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		mClock.setHour(calendar.get(Calendar.HOUR_OF_DAY) + 1);
		List<Clock.Week> weeks = new ArrayList<Clock.Week>();
		weeks.add(Clock.Week.MON);weeks.add(Clock.Week.TUE);weeks.add(Clock.Week.WED);weeks.add(Clock.Week.THU);weeks.add(Clock.Week.FRI);
		mClock.setWeek(weeks);
	}
		
	@Override
	protected void onResume() {
		super.onResume();
		//周期
		vCycleValue.setText(mClock.getCycle().getName());
		//时间
		layoutTime.removeAllViews();
		View layoutTimeChild;
		View layoutClick;
		switch(mClock.getCycle()){
		case SINGLE:
			layoutTimeChild = inflater.inflate(R.layout.form_select_2, layoutTime);
			layoutClick = layoutTimeChild.findViewById(R.id.form_select_2_1);
			layoutClick.setId(ID_DATE);
			layoutClick.setOnClickListener(this);
			((TextView)layoutTimeChild.findViewById(R.id.form_select_2_1_key)).setText("日期");
			vTimeValue = (TextView)layoutTimeChild.findViewById(R.id.form_select_2_1_value);
			vTimeValue.setText(mClock.formatYearMonthDay());
			//
			layoutClick = layoutTimeChild.findViewById(R.id.form_select_2_2);
			layoutClick.setId(ID_TIME);
			layoutClick.setOnClickListener(this);
			((TextView)layoutTimeChild.findViewById(R.id.form_select_2_2_key)).setText("时间");
			vTimeValue = (TextView)layoutTimeChild.findViewById(R.id.form_select_2_2_value);
			vTimeValue.setText(mClock.formatHourMin());
			break;
		case DAY:
			layoutTimeChild = inflater.inflate(R.layout.form_select_1, layoutTime);
			layoutClick = layoutTimeChild.findViewById(R.id.form_select_1);
			layoutClick.setId(ID_TIME);
			layoutClick.setOnClickListener(this);
			((TextView)layoutTimeChild.findViewById(R.id.form_select_1_key)).setText("时间");
			vTimeValue = (TextView)layoutTimeChild.findViewById(R.id.form_select_1_value);
			vTimeValue.setText(mClock.formatHourMin());
			break;
		case WEEK:
			layoutTimeChild = inflater.inflate(R.layout.form_select_2, layoutTime);
			layoutClick = layoutTimeChild.findViewById(R.id.form_select_2_1);
			layoutClick.setId(ID_WEEK);
			layoutClick.setOnClickListener(this);
			((TextView)layoutTimeChild.findViewById(R.id.form_select_2_1_key)).setText("星期");
			vTimeValue = (TextView)layoutTimeChild.findViewById(R.id.form_select_2_1_value);
			vTimeValue.setText(mClock.formatWeek());
			//
			layoutClick = layoutTimeChild.findViewById(R.id.form_select_2_2);
			layoutClick.setId(ID_TIME);
			layoutClick.setOnClickListener(this);
			((TextView)layoutTimeChild.findViewById(R.id.form_select_2_2_key)).setText("时间");
			vTimeValue = (TextView)layoutTimeChild.findViewById(R.id.form_select_2_2_value);
			vTimeValue.setText(mClock.formatHourMin());
			break;
		case MONTH:
			layoutTimeChild = inflater.inflate(R.layout.form_select_2, layoutTime);
			layoutClick = layoutTimeChild.findViewById(R.id.form_select_2_1);
			layoutClick.setId(ID_DAY);
			layoutClick.setOnClickListener(this);
			((TextView)layoutTimeChild.findViewById(R.id.form_select_2_1_key)).setText("日期");
			vTimeValue = (TextView)layoutTimeChild.findViewById(R.id.form_select_2_1_value);
			vTimeValue.setText(mClock.formatDay());
			//
			layoutClick = layoutTimeChild.findViewById(R.id.form_select_2_2);
			layoutClick.setId(ID_TIME);
			layoutClick.setOnClickListener(this);
			((TextView)layoutTimeChild.findViewById(R.id.form_select_2_2_key)).setText("时间");
			vTimeValue = (TextView)layoutTimeChild.findViewById(R.id.form_select_2_2_value);
			vTimeValue.setText(mClock.formatHourMin());
			break;
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch(v.getId()){
		case ID_CYCLE:
			intent = new Intent(this, DialogCycleA.class);
			break;
		case ID_DATE:
			intent = new Intent(this, DialogPickerYearMonthDayA.class);
			break;
		case ID_TIME:
			intent = new Intent(this, DialogPickerHourMinA.class);
			break;
		case ID_DAY:
			intent = new Intent(this, DialogPickerDayA.class);
			break;
		case ID_WEEK:
			intent = new Intent(this, DialogWeekA.class);
			break;
		}
		intent.putExtra(EXTRA_KEY, mClock);
		startActivityForResult(intent, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(data != null){
			mClock = (Clock)data.getSerializableExtra(EXTRA_KEY);
		}
	}

}
