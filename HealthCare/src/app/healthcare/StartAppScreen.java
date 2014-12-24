package app.healthcare;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class StartAppScreen extends Fragment {
	ImageView background;
	int i = 0;
	final Handler mHandler = new Handler();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.hello_app, container, false);

		
		this.background = (ImageView) rootView.findViewById(R.id.backGround);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				if (i == 0)
					background.setBackgroundResource(R.drawable.backgroundapp0);
				else if (i == 1)
					background.setBackgroundResource(R.drawable.backgroundapp1);
				else if (i == 2)
					background.setBackgroundResource(R.drawable.backgroundapp2);
				else if (i == 3)
					background.setBackgroundResource(R.drawable.backgroundapp3);
				else if (i == 4)
					background.setBackgroundResource(R.drawable.backgroundapp4);
				mHandler.postDelayed(this, 500);
				i++;
				if (i > 4)
					i = 1;
			}
		};

		// start handler
		mHandler.post(runnable);
		
		
		return rootView;
	}
}
