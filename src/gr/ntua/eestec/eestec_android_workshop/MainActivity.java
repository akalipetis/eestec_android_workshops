package gr.ntua.eestec.eestec_android_workshop;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView = (ListView) findViewById(R.id.listView1);

		ArrayList<FlickrPhotoset> list = new ArrayList<FlickrPhotoset>();

		for (int i = 0; i < 100000; ++i) {

			list.add(new FlickrPhotoset(i, "Hello, " + i));
		}

		FlickrPhotosetAdapter adapter = new FlickrPhotosetAdapter(list, this);
		listView.setAdapter(adapter);
	}

}
