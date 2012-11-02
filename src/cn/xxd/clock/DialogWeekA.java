package cn.xxd.clock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.xxd.clock.bean.Clock;
import cn.xxd.clock.bean.Clock.Week;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class DialogWeekA extends Activity implements OnClickListener {

	private AdapterWeek adapterWeek;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_list_btn);
		//
		List<Clock.Week> datas = new ArrayList<Clock.Week>();
		for(Clock.Week t : Clock.Week.values()){
			datas.add(t);
		}
		//
		adapterWeek = new AdapterWeek(this, datas, (Clock) getIntent().getSerializableExtra(EditA.EXTRA_KEY));
		((ListView)findViewById(R.id.dialog_list_lv))
		.setAdapter(adapterWeek);
		//
		findViewById(R.id.dialog_btn_1_confirm).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_btn_1_confirm:
			Clock clock = adapterWeek.getClock();
			List<Clock.Week> list = clock.getWeek();
			Collections.sort(list);
			setResult(0, new Intent().putExtra(EditA.EXTRA_KEY, clock));
			finish();
			break;
		}
	}

}

