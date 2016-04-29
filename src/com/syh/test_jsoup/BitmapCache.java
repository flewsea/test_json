package com.syh.test_jsoup;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapCache implements ImageCache {
	 private LruCache<String, Bitmap> mCache;  
	  
	    public BitmapCache() {  
	        int maxSize = 10 * 1024 * 1024;  
	        mCache = new LruCache<String, Bitmap>(maxSize) {  
	            @Override  
	            protected int sizeOf(String key, Bitmap bitmap) {  
	                return bitmap.getRowBytes() * bitmap.getHeight();  
	            }  
	        };  
	    }  
	@Override
	public Bitmap getBitmap(String url) {
		// TODO 自动生成的方法存根
		return  mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		// TODO 自动生成的方法存根
		 mCache.put(url, bitmap); 
	}

}
