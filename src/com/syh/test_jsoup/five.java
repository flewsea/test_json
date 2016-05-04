package com.syh.test_jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class five extends Activity {

	public ImageLoader imageLoader = ImageLoader.getInstance();

	public static final int response_str = 0;
	private final ArrayList<String> imgsUrl = new ArrayList<String>();
	private final ArrayList<String> tupianurl = new ArrayList<String>();
	// pager part
	DisplayImageOptions options;
	ViewPager pager;

	private String Jsoup_url = "http://www.zhengshangci.com/productx.asp?id=@num",
			jsoup_tmp_url, urlimg;
	private TextView tv_sec_01, tv_sec_02, tv_sec_03;
	private ImageView imageview01;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			Bundle bundle = msg.getData();
			String UI_title = bundle.getString("title");
			ArrayList<String> tupianurl = bundle.getStringArrayList("picurl");
			imageview01 = (ImageView) findViewById(R.id.imageview01);

			String UI_author = bundle.getString("author");
			String UI_content = bundle.getString("xxcontent");
			System.out.println(tupianurl + "233");
			switch (msg.what) {
			case response_str:
				// 在这里进行UI操作，将结果显示到界面上
				System.out.println(UI_title);

				tv_sec_01.setText(UI_title);
				tv_sec_03.setText(UI_author);
				int count = 0;
				String tupianurl_string = tupianurl.get(count);
				// 调用loadPicture加载图片
				if (null == tupianurl_string) {
					imageview01.setBackgroundResource(R.drawable.pic404);
				} else {
					new LoadPicture().getPicture(tupianurl_string, imageview01);
				}

				System.out.println("1" + UI_content);
				tv_sec_02.setText("    " + UI_content);

				break;
			default:
				System.out.println("执行的是我");

				break;
			}

		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.five_acitvity);
		tv_sec_01 = (TextView) findViewById(R.id.tv01);
		tv_sec_02 = (TextView) findViewById(R.id.tv_02);
		tv_sec_03 = (TextView) findViewById(R.id.tv03);
		imageview01 = (ImageView) findViewById(R.id.imageview01);

		Intent intent = getIntent();
		String StringF = intent.getStringExtra("fenlei");
		String StringE = intent.getStringExtra("num").toString();
		jsoup_tmp_url = "http://www.zhengshangci.com/" + StringF + ".asp?id="
				+ StringE;
		JsoupNetConnect();
		imageview01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				int size = imgsUrl.size();
				String[] arr = (String[]) imgsUrl.toArray(new String[size]);
				System.out.println(arr + "222111");
				Intent intent = new Intent();
				intent.putExtra("imgsUrl", arr);
				intent.setClass(five.this, ImagePagerActivity.class);
				startActivity(intent);
			}
		});

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_clear_memory_cache:
			imageLoader.clearMemoryCache();
			return true;
		case R.id.item_clear_disc_cache:
			imageLoader.clearDiscCache();
			return true;
		default:
			return false;
		}
	}

	public void JsoupNetConnect() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO 自动生成的方法存根

				try {

					Log.e("233", jsoup_tmp_url + " ");
					Document doc = Jsoup.connect(jsoup_tmp_url).timeout(5000)
							.get();
					Connection connect = Jsoup.connect(jsoup_tmp_url);

					Map<String, String> header = new HashMap<String, String>();

					header.put("Host", jsoup_tmp_url);
					header.put("User-Agent",
							"	Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
					header.put("Accept",
							"	text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
					header.put("Accept-Language", "zh-cn,zh;q=0.5");
					header.put("Accept-Charset", "	GB2312,utf-8;q=0.7,*;q=0.7");
					header.put("Connection", "keep-alive");
					Connection data = connect.data(header);
					Log.d("nimeide", "还错？"); // Elements
					Elements divs = doc.select("div.nboxc");
					Elements divs_1 = divs.select("#xxbiaoti");
					String title = divs_1.text();
					// 下面是图片 链接的获取
					if (divs != null) {
						Elements imgurls = divs.select("img[src]");
						System.out.println(imgurls.toString() + "<<<<<<图片地址");
						if (null != imgurls) {

							for (Element imgurl : imgurls) {

								urlimg = (imgurl.attr("abs:src"));

								imgsUrl.add(urlimg);
							}
						}
					}
					String author = divs.select("#xxjiben").text()
							.replace(Jsoup.parse("&nbsp;").text(), " ");
					System.out.println("divs>>>>>" + divs);
					Elements content3 = divs.select("div.xxcontent");
					System.out.println("content3>>>>>" + content3);
					Elements content1 = divs.select("#xxcontent > p");
					System.out.println("content1>>>>>" + content1);
					String xxcontent="";
					if (content1.toString() == "") {
						xxcontent += divs.select("#xxcontent").text();
						System.out.println("执行的是我(1)");

					} else {
						for (Element content2 : content1) {
							xxcontent += (content2.text() + "\n");
							System.out.println(xxcontent + "<<<<<");
							System.out.println("执行的是我");
						}
					}
					Message msg = handler.obtainMessage();
					Bundle bundle = new Bundle();
					bundle.putStringArrayList("picurl", imgsUrl);
					bundle.putString("title", title);
					bundle.putString("author", author);
					bundle.putString("xxcontent", xxcontent);

					msg.setData(bundle);
					// message.what=response_str;
					msg.sendToTarget();
					Log.d("123", xxcontent + "m");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}).start();

	}
	// tupian
}
