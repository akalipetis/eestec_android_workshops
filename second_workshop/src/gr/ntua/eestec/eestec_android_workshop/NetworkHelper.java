package gr.ntua.eestec.eestec_android_workshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.http.AndroidHttpClient;
import android.util.Log;

public class NetworkHelper {

	private static final String BASE_FLICKR_URL = "http://api.flickr.com/services/rest/";

	private static final String KEY_FORMAT = "format";
	private static final String KEY_METHOD = "method";
	private static final String KEY_NO_JSON_CALLBACK = "nojsoncallback";
	private static final String KEY_API_KEY = "api_key";
	private static final String KEY_USER_ID = "user_id";

	public static ArrayList<FlickrPhotoset> getPhotosets() {

		ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
		data.add(new BasicNameValuePair(KEY_FORMAT, "json"));
		data.add(new BasicNameValuePair(KEY_METHOD, "flickr.photosets.getList"));
		data.add(new BasicNameValuePair(KEY_NO_JSON_CALLBACK, "1"));
		data.add(new BasicNameValuePair(KEY_API_KEY,
				"4f1629def199298b6d7b16b686390e01"));
		data.add(new BasicNameValuePair(KEY_USER_ID, "89405227@N05"));

		JSONObject jObj = executePostRequest(BASE_FLICKR_URL, data);

		if (jObj == null)
			return null;
		
		Log.d("ANTONIS", "Passed initial");

		jObj = jObj.optJSONObject("photosets");

		if (jObj == null)
			return null;
		
		Log.d("ANTONIS", "Passed photosets");

		JSONArray jArray = jObj.optJSONArray("photoset");

		if (jArray == null)
			return null;
		
		Log.d("ANTONIS", "Passed");

		ArrayList<FlickrPhotoset> photosets = new ArrayList<FlickrPhotoset>();

		for (int i = 0, lim = jArray.length(); i < lim; ++i) {

			jObj = jArray.optJSONObject(i);

			if (jObj == null)
				break;
			
			String title = jObj.optJSONObject("title").optString("_content");
			String description = jObj.optJSONObject("description").optString(
					"_content");
			long id = jObj.optLong("id");

			photosets.add(new FlickrPhotoset(id, title, description));
		}

		return photosets;
	}

	private static JSONObject executePostRequest(String url,
			ArrayList<NameValuePair> data) {

		HttpPost post = new HttpPost(url);
		HttpResponse response;

		try {

			DefaultHttpClient client = new DefaultHttpClient();

			post.setEntity(new UrlEncodedFormEntity(data));
			response = client.execute(post);

		} catch (IOException e) {

			Log.d("PIC_VIEW",
					"IO Exception thrown when executing post, possibly due to Internet connection",
					e);
			return null;
		}

		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			StringBuffer strBuffer = new StringBuffer(reader.readLine());
			String str = reader.readLine();

			while (str != null) {

				strBuffer.append("\n").append(str);
				str = reader.readLine();
			}

			JSONObject jObj = new JSONObject(strBuffer.toString());
			return jObj;

		} catch (IOException e) {

			Log.d("PIC_VIEW", "IO Exception thrown when parsing response", e);
			return null;

		} catch (JSONException e) {

			Log.d("PIC_VIEW",
					"JSON exception thrown when parsing response into JSON", e);
			return null;

		}
	}
}
