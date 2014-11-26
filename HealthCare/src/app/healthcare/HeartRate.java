package app.healthcare;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HeartRate extends Fragment {
	
	private static final String TAG = "HeartRateMonitor";
	private static final AtomicBoolean processing = new AtomicBoolean(false);
	private static final int RATE_CYCLE = 10;

	private static SurfaceView preview = null;
	private static SurfaceHolder previewHolder = null;
	private static Camera camera = null;
	private static View image = null;
	private static TextView text = null;

	private static WakeLock wakeLock = null;

	private static int averageIndex = 0;
	private static final int averageArraySize = 4;
	private static final int[] averageArray = new int[averageArraySize];

	public static enum TYPE {
		GREEN, RED
	};

	private static TYPE currentType = TYPE.GREEN;

	public static TYPE getCurrent() {
		return currentType;
	}

	private static int beatsIndex = 0;
	private static final int beatsArraySize = 3;
	private static final int[] beatsArray = new int[beatsArraySize];
	private static double beats = 0;
	private static long startTime = 0;
	static int beatsAvg = 30;

	// for graph
	static ArrayList<GraphViewData> listData = new ArrayList<GraphView.GraphViewData>();
	static GraphView graphView;
	static GraphViewSeries graphViewSeries;

	
	public HeartRate() {
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_heart_rate, container, false);
		/*initGraph();

		preview = (SurfaceView) this.getActivity().findViewById(R.id.preview);
		previewHolder = preview.getHolder();
		previewHolder.addCallback(surfaceCallback);
		previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		image = this.getActivity().findViewById(R.id.image);
		text = (TextView) this.getActivity().findViewById(R.id.text);

		PowerManager pm = (PowerManager) this.getActivity().getSystemService(Context.POWER_SERVICE);
		wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen");*/
		return rootView;
	}

	/*
	private void initGraph() {

		graphView = new LineGraphView(this.getActivity(), "Graph");

		listData = new ArrayList<GraphView.GraphViewData>();
		GraphViewData[] data = new GraphViewData[listData.size()];
		listData.toArray(data);
		graphViewSeries = new GraphViewSeries("", new GraphViewSeriesStyle(Color.RED, 7), data);

		// add data
		graphView.addSeries(graphViewSeries);
		// set view port, start=2, size=40
		graphView.setViewPort(0, 200);
		graphView.setManualYMaxBound(250);
		graphView.setManualYMinBound(230);
		graphView.setScrollable(true);
		// optional - activate scaling / zooming
		graphView.getGraphViewStyle().setGridColor(Color.TRANSPARENT);
		graphView.setScalable(true);
		
		// graphView.setShowLegend(true);
		graphView.setShowHorizontalLabels(false);
		graphView.setShowVerticalLabels(false);

		LinearLayout layout = (LinearLayout) this.getActivity().findViewById(R.id.graphLayout);
		layout.addView(graphView);
	}
	
	private static PreviewCallback previewCallback = new PreviewCallback() {

		@Override
		public void onPreviewFrame(byte[] data, Camera cam) {
			if (data == null)
				throw new NullPointerException();
			Camera.Size size = cam.getParameters().getPreviewSize();
			if (size == null)
				throw new NullPointerException();

			if (!processing.compareAndSet(false, true))
				return;

			int width = size.width;
			int height = size.height;

			int imgAvg = ImageProcessing.decodeYUV420SPtoRedAvg(data.clone(),
					height, width);
			Log.i(TAG, "imgAvg=" + imgAvg);
			if (imgAvg == 0 || imgAvg == 255) {
				processing.set(false);
				return;
			}

			if (imgAvg >= 230 && imgAvg <= 250) {
				updateGraph(true, imgAvg);
			} else if (imgAvg < 230) {
				updateGraph(true, 230);
			} else
				updateGraph(true, 250);

			int averageArrayAvg = 0;
			int averageArrayCnt = 0;
			for (int i = 0; i < averageArray.length; i++) {
				if (averageArray[i] > 0) {
					averageArrayAvg += averageArray[i];
					averageArrayCnt++;
				}
			}

			int rollingAverage = (averageArrayCnt > 0) ? (averageArrayAvg / averageArrayCnt)
					: 0;
			TYPE newType = currentType;
			if (imgAvg < rollingAverage) {
				newType = TYPE.RED;

				if (newType != currentType) {
					beats++;
					Log.d(TAG, "BEAT!! beats=" + beats);
				}
			} else if (imgAvg > rollingAverage) {
				newType = TYPE.GREEN;
			}

			if (averageIndex == averageArraySize)
				averageIndex = 0;
			averageArray[averageIndex] = imgAvg;
			averageIndex++;

			// Transitioned from one state to another to the same
			if (newType != currentType) {
				currentType = newType;
				image.postInvalidate();
			} else {
			}

			long endTime = System.currentTimeMillis();
			double totalTimeInSecs = (endTime - startTime) / 1000d;
			if (totalTimeInSecs >= RATE_CYCLE) {
				double bps = (beats / totalTimeInSecs);
				int dpm = (int) (bps * 60d);
				if (dpm < 30 || dpm > 180) {
					startTime = System.currentTimeMillis();
					beats = 0;
					processing.set(false);
					return;
				}

				if (beatsIndex == beatsArraySize)
					beatsIndex = 0;
				beatsArray[beatsIndex] = dpm;
				beatsIndex++;

				int beatsArrayAvg = 0;
				int beatsArrayCnt = 0;
				for (int i = 0; i < beatsArray.length; i++) {
					if (beatsArray[i] > 0) {
						beatsArrayAvg += beatsArray[i];
						beatsArrayCnt++;
					}
				}
				beatsAvg = (beatsArrayAvg / beatsArrayCnt);

				text.setText(String.valueOf(beatsAvg));
				startTime = System.currentTimeMillis();
				beats = 0;
			}

			processing.set(false);
			// jijh

		}
	};

	private static double tempValue = 0d;

	@SuppressWarnings("deprecation")
	private static void updateGraph(boolean isBeated, int beatsAvg) {
		if (!isBeated && listData.size() <= 0) {
			return;
		}

		if (isBeated) {
			tempValue = 0;
			listData.add(new GraphViewData(listData.size(), beatsAvg));
			Log.i(TAG, "New data: " + beatsAvg);
		} else {
			if (((listData.get(listData.size() - 1).getY()) - tempValue) > 30)
				tempValue += 1;
			listData.add(new GraphViewData(listData.size(), (listData
					.get(listData.size() - 1).getY()) - tempValue));

		}

		GraphViewData[] data = new GraphViewData[listData.size()];
		listData.toArray(data);

		graphViewSeries.resetData(data);
		graphViewSeries.appendData(listData.get(listData.size() - 1), true);
		Log.i(TAG, "Beated: " + isBeated + " - data: "
						+ listData.get(listData.size() - 1).getY() + " - size "
						+ data.length);

	}

	private static SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			try {
				camera.setPreviewDisplay(previewHolder);
				camera.setPreviewCallback(previewCallback);
			} catch (Throwable t) {
				Log.e("PreviewDemo-surfaceCallback",
						"Exception in setPreviewDisplay()", t);
			}
		}


		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			Camera.Parameters parameters = camera.getParameters();
			parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
			Camera.Size size = getSmallestPreviewSize(width, height, parameters);
			if (size != null) {
				parameters.setPreviewSize(size.width, size.height);
				Log.d(TAG, "Using width=" + size.width + " height=" + size.height);
			}
			camera.setParameters(parameters);
			camera.startPreview();
		}

	
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// Ignore
		}
	};

	private static Camera.Size getSmallestPreviewSize(int width, int height,
			Camera.Parameters parameters) {
		Camera.Size result = null;

		for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
			if (size.width <= width && size.height <= height) {
				if (result == null) {
					result = size;
				} else {
					int resultArea = result.width * result.height;
					int newArea = size.width * size.height;

					if (newArea < resultArea)
						result = size;
				}
			}
		}

		return result;
	}*/
}