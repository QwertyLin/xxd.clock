package cn.xxd.clock;

import java.util.List;

import q.util.AdapterBase;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;
import cn.xxd.clock.bean.Clock;

public class AdapterCycle extends AdapterBase<Clock.Cycle, AdapterCycle.Holder> implements OnClickListener {
	
	private Activity mAct = (Activity)mCtx;
	private Clock clock;
	
	public AdapterCycle(Context ctx, List<Clock.Cycle> datas, Clock clock) {
		this(ctx, datas);
		this.clock = clock;
	}

	public AdapterCycle(Context ctx, List<Clock.Cycle> datas) {
		super(ctx, datas);
	}

	class Holder {
		CheckedTextView ctv;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.simple_list_item_single_choice;
	}

	@Override
	protected Holder getHolder(View v) {
		Holder h = new Holder();
		h.ctv = (CheckedTextView)v.findViewById(R.id.list_item_checkedtext);
		h.ctv.setOnClickListener(this);
		return h;
	}

	@Override
	protected void initItem(int position, Clock.Cycle data, Holder holder) {
		if(data == clock.getCycle()){
			holder.ctv.setChecked(true);
		}else {
			holder.ctv.setChecked(false);
		}
		holder.ctv.setText(data.getName());
		holder.ctv.setTag(data);
	}

	@Override
	public void onClick(View v) {
		clock.setCycle((Clock.Cycle)v.getTag());
		mAct.setResult(0, new Intent().putExtra(EditA.EXTRA_KEY, clock));
		mAct.finish();
	}

}