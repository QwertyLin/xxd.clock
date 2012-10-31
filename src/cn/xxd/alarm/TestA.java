package cn.xxd.alarm;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.WindowManager;

public class TestA extends Activity {
	
	private boolean mIsStop;
	private boolean mIsMute;
	WakeLock wakeLock;
	Vibrator vibrator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//每次都是single task 模式打开，可以多个闹钟重叠
		//先是红色按钮，按一下静音，然后按钮变成蓝色关闭，按关闭才finish
		
		super.onCreate(savedInstanceState);
		System.out.println("onService");
		PowerManager pm = (PowerManager)getSystemService(POWER_SERVICE);
		//pm.goToSleep(SystemClock.uptimeMillis()+1);
		wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, this.getClass().getCanonicalName());
        wakeLock.acquire();
        		
		int flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
		getWindow().addFlags(flags); 
		
		vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);  
		//long [] pattern = {100,400,100,400};   // 停止 开启 停止 开启   
       // vibrator.vibrate(pattern,20);           //重复两次上面的pattern 如果只想震动一次，index设为-1   
        vibrator.vibrate(5000);
        
		new Thread(){
			public void run() {
				SystemClock.sleep(300000);//无操作时，5分钟后关闭屏幕、停止震动，可设置，默认5分钟。
				if(wakeLock != null && wakeLock.isHeld()){
					wakeLock.release();
				}
				if(vibrator != null){
					vibrator.cancel();
				}
			};
		}.start();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode){
		case KeyEvent.KEYCODE_VOLUME_DOWN:
		case KeyEvent.KEYCODE_VOLUME_UP:
			if(!mIsStop){
				mIsStop = true;
				wakeLock.release();
				vibrator.cancel();
			}
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
