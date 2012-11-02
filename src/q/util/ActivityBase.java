package q.util;

import com.umeng.analytics.MobclickAgent;

import android.app.Activity;

public class ActivityBase extends Activity {
	    
    @Override
    protected void onResume() {
    	super.onResume();
    	MobclickAgent.onResume(this);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	MobclickAgent.onPause(this);
    }
    
}
