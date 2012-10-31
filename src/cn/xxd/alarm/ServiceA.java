package cn.xxd.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.SystemClock;

public class ServiceA extends Service {
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("onService");
		PowerManager pm = (PowerManager)getSystemService(POWER_SERVICE);
		pm.goToSleep(SystemClock.uptimeMillis()+1);
		/*PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
		wl.acquire();*/
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
