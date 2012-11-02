package cn.xxd.clock;

import java.util.ArrayList;
import java.util.List;

import cn.xxd.clock.bean.Clock;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class DialogCycleA extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_list);
		//
		List<Clock.Cycle> datas = new ArrayList<Clock.Cycle>();
		for(Clock.Cycle t : Clock.Cycle.values()){
			datas.add(t);
		}
		//
		((ListView)findViewById(R.id.dialog_list_lv))
		.setAdapter(new AdapterCycle(this, datas, (Clock) getIntent().getSerializableExtra(EditA.EXTRA_KEY)));
		//
	}

}

