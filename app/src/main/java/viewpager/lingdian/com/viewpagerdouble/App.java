package viewpager.lingdian.com.viewpagerdouble;

import android.app.Application;

import org.xutils.x;



public class App extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//初始化XUtils3,设置debug模式
		x.Ext.init(this);
		x.Ext.setDebug(false);

	}

}


