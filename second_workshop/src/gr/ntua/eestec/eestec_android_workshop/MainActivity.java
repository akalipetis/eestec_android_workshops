package gr.ntua.eestec.eestec_android_workshop;

import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

	private FlickrPhotosetAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<FlickrPhotoset> list = new ArrayList<FlickrPhotoset>();
		mAdapter = new FlickrPhotosetAdapter(list, MainActivity.this);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(mAdapter);
		
		FlickrPhotosetTask task = new FlickrPhotosetTask();
		task.execute();
	}

	private class FlickrPhotosetTask extends
			AsyncTask<Void, Void, ArrayList<FlickrPhotoset>> {

		@Override
		protected ArrayList<FlickrPhotoset> doInBackground(Void... params) {

			return NetworkHelper.getPhotosets();
		}

		@Override
		protected void onPostExecute(ArrayList<FlickrPhotoset> result) {
			super.onPostExecute(result);

			mAdapter.setPhotosets(result);
		}
	}
}
