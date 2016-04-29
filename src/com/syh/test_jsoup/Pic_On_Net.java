package com.syh.test_jsoup;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
public class Pic_On_Net {
	public static Bitmap getHttpBitmap(String picurl) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
		Log.d("123", picurl);
		myFileUrl = new URL(picurl);
		} catch (MalformedURLException e) {
		e.printStackTrace();
		}
		try {
		HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
		conn.setConnectTimeout(0);
		conn.setDoInput(true);
		conn.connect();
		InputStream is = conn.getInputStream();
		bitmap = BitmapFactory.decodeStream(is);
		is.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		return bitmap;
		}

}
