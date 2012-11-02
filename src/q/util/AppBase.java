package q.util;

import com.umeng.analytics.MobclickAgent;

import android.app.Application;

public class AppBase extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		MobclickAgent.onError(this);
	}
	

}
