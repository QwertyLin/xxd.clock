package cn.xxd.clock;

import java.util.List;

import q.util.AdapterBase;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;
import cn.xxd.clock.bean.Clock;

public class AdapterWeek extends AdapterBase<Clock.Week, AdapterWeek.Holder> implements OnClickListener {
	
	private Clock clock;
	
	public AdapterWeek(Context ctx, List<Clock.Week> datas, Clock clock) {
		this(ctx, datas);
		this.clock = clock;
	}

	public AdapterWeek(Context ctx, List<Clock.Week> datas) {
		super(ctx, datas);
	}

	class Holder {
		CheckedTextView ctv;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.simple_list_item_multiple_choice;
	}

	@Override
	protected Holder getHolder(View v) {
		Holder h = new Holder();
		h.ctv = (CheckedTextView)v.findViewById(R.id.list_item_checkedtext);
		h.ctv.setOnClickListener(this);
		return h;
	}

	@Override
	protected void initItem(int position, Clock.Week data, Holder holder) {
		if(clock.getWeek().contains(data)){
			holder.ctv.setChecked(true);
		}else {
			holder.ctv.setChecked(false);
		}
		holder.ctv.setText("周" + data.getName());
		holder.ctv.setTag(data);
	}

	@Override
	public void onClick(View v) {
		CheckedTextView ctv = (CheckedTextView)v;
		Clock.Week week = (Clock.Week)v.getTag();
		if(ctv.isChecked()){
			ctv.setChecked(false);
			clock.getWeek().remove(week);
		}else{
			ctv.setChecked(true);
			clock.getWeek().add(week);
		}
	}
	
	public Clock getClock(){
		return clock;
	}

}