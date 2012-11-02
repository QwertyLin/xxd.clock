package cn.xxd.clock;

import cn.xxd.clock.bean.Clock;
import kankan.wheel.widget.OnWheelClickedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class DialogPickerDayA extends Activity implements OnClickListener {
	
	private Clock mClock;
	private WheelView wvDay;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_btn_1);
		mClock = (Clock)getIntent().getSerializableExtra(EditA.EXTRA_KEY);
		//
		findViewById(R.id.dialog_btn_1_confirm).setOnClickListener(this);
		//
		FrameLayout layoutMain = (FrameLayout)findViewById(R.id.dialog_btn_1_main);
		LinearLayout line = new LinearLayout(this);
		line.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.FILL_PARENT));
		line.setOrientation(LinearLayout.HORIZONTAL);
		layoutMain.addView(line);
		//
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.FILL_PARENT, 1);
		wvDay = new WheelView(this);
		wvDay.setLayoutParams(llp);
		NumericWheelAdapter adapterHour = new NumericWheelAdapter(this, 1, 31);
		wvDay.setViewAdapter(adapterHour);
		line.addView(wvDay);
		//
		OnWheelClickedListener click = new OnWheelClickedListener() {
            public void onItemClicked(WheelView wheel, int itemIndex) {
                wheel.setCurrentItem(itemIndex, true);
            }
        };
		//
		wvDay.setCurrentItem(mClock.getDay() - 1);
		wvDay.addClickingListener(click);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.dialog_btn_1_confirm:
			mClock.setDay(wvDay.getCurrentItem() + 1);
			setResult(0, new Intent().putExtra(EditA.EXTRA_KEY, mClock));
			finish();
			break;
		}
	}

}
