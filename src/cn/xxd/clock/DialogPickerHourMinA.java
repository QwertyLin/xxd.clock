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

public class DialogPickerHourMinA extends Activity implements OnClickListener {
	
	private Clock mClock;
	private WheelView wvHour, wvMin;
		
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
		wvHour = new WheelView(this);
		wvHour.setLayoutParams(llp);
		NumericWheelAdapter adapterHour = new NumericWheelAdapter(this, 0, 23);
		wvHour.setViewAdapter(adapterHour);
		line.addView(wvHour);
		wvMin = new WheelView(this);
		wvMin.setLayoutParams(llp);
		NumericWheelAdapter adapterMin = new NumericWheelAdapter(this, 0, 59);
		wvMin.setViewAdapter(adapterMin);
		line.addView(wvMin);
		//
		OnWheelClickedListener click = new OnWheelClickedListener() {
            public void onItemClicked(WheelView wheel, int itemIndex) {
                wheel.setCurrentItem(itemIndex, true);
            }
        };
        //
		wvHour.setCurrentItem(mClock.getHour());
		wvHour.addClickingListener(click);
		wvMin.setCurrentItem(mClock.getMin());
		wvMin.addClickingListener(click);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.dialog_btn_1_confirm:
			mClock.setHour(wvHour.getCurrentItem());
			mClock.setMin(wvMin.getCurrentItem());
			setResult(0, new Intent().putExtra(EditA.EXTRA_KEY, mClock));
			finish();
			break;
		}
	}

}
