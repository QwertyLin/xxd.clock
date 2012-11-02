package cn.xxd.clock;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class MainA extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + getPackageName()));
        startActivity(intent);*/
        startActivity(new Intent(this, EditA.class));
        finish();
    }
    
}
