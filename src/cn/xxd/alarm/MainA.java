package cn.xxd.alarm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.xxd.clock.R;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SlidingDrawer;

public class MainA extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /*PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, TestA.class), 0);
		AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 5000, pi);*/
		
		
        SlidingDrawer sd = (SlidingDrawer)findViewById(R.id.slidingDrawer1);
        
        Button b = new Button(this);
        b.setText("22222222222");
        ArrayList<View> vs = new ArrayList<View>();
        vs.add(b);
        sd.addTouchables(vs);
		
		
		findViewById(R.id.btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		/*Date date = new Date();
		
		Calendar c = Calendar.getInstance();
		c.set(2012, 9, 23, 11, 22, 0);
		long time = c.getTimeInMillis();
		System.out.println(time);
		System.out.println(Calendar.getInstance().getTimeInMillis());
		System.out.println(time - Calendar.getInstance().getTimeInMillis());*/
		//am.set(AlarmManager.RTC_WAKEUP, time , pi);
		//finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
