package gr.ntua.eestec.eestec_android_workshop;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FlickrPhotosetAdapter extends BaseAdapter {

	private ArrayList<FlickrPhotoset> mPhotosets;
	private final Context mContext;

	public FlickrPhotosetAdapter(ArrayList<FlickrPhotoset> photosets,
			Context context) {

		mPhotosets = photosets;
		mContext = context;
	}

	@Override
	public int getCount() {
		return mPhotosets.size();
	}

	@Override
	public Object getItem(int position) {
		return mPhotosets.get(position);
	}

	@Override
	public long getItemId(int position) {
		return mPhotosets.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Replace code with this one to improve speed
		// TextView tv;
		// if (convertView == null) {
		//
		// LayoutInflater infalter = (LayoutInflater) mContext
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// tv = (TextView) infalter.inflate(R.layout.row_view, parent, false);
		//
		// } else {
		//
		// tv = (TextView) convertView;
		// }
		// tv.setText(mPhotosets.get(position).getTitle());
		//
		// return tv;

		LayoutInflater infalter = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		TextView tv = (TextView) infalter.inflate(R.layout.row_view, parent,
				false);

		tv.setText(mPhotosets.get(position).getTitle());

		return tv;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
