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
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class five extends Activity {

    protected ImageLoader imageLoader = ImageLoader.getInstance();  
	public static final int response_str = 0;
	private ArrayList<String> imgsUrl = new ArrayList<String>();
	private String Jsoup_url = "http://www.zhengshangci.com/productx.asp?id=@num",jsoup_tmp_url,urlimg;
	private TextView tv_sec_01, tv_sec_02;
	private ImageView imageview01;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			Bundle bundle = msg.getData();
			String UI_title = bundle.getString("title");
			ArrayList<String> tupianurl = bundle.getStringArrayList("picurl");
			imageview01=(ImageView) findViewById(R.id.download);

			String UI_author = bundle.getString("author");
			String UI_content = bundle.getString("xxcontent");
			System.out.println(tupianurl + "233");
			switch (msg.what) {
			case response_str:
				// ���������UI�������������ʾ��������
				System.out.println(UI_title);

				tv_sec_01.setText(UI_title + "\n" + UI_author);

				// ����loadPicture����ͼƬ

			
				Log.i("tupianurl", tupianurl.toString());
			

				System.out.println("1" + UI_content);
				tv_sec_02.setText("    " + UI_content);

				break;
			default:
				System.out.println("ִ�е�����");

				break;
			}

		}
	};
	
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.four_acitvity);
		tv_sec_01 = (TextView) findViewById(R.id.tv01);
		tv_sec_02 = (TextView) findViewById(R.id.tv_02);
		imageview01 = (ImageView) findViewById(R.id.imageview01);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
		 Intent intent=getIntent();
		 String StringE=intent.getStringExtra("num").toString();
			jsoup_tmp_url="http://www.zhengshangci.com/newsx.asp?id="+StringE;

		JsoupNetConnect();

	}

	public void JsoupNetConnect() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO �Զ����ɵķ������
				
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
					Log.d("nimeide", "����"); // Elements
					Elements divs = doc.select("div.nboxc");
					Elements divs_1 = divs.select("#xxbiaoti");
					String title = divs_1.text();
					// ������ͼƬ ���ӵĻ�ȡ
					int i=0;
					if (divs != null) {
						Elements imgurls = divs.select("img[src]");
						System.out.println(imgurls.toString()+"<<<<<<ͼƬ��ַ");
						if (null != imgurls) {

							for (Element imgurl : imgurls) {
								
								urlimg = (imgurl.attr("abs:src"));

								imgsUrl.add(urlimg);
							}
						}
					}
					Elements authors=divs.select("#xxjiben").parents();
					String author = divs.select("#xxjiben").text().replace(Jsoup.parse("&nbsp;").text(), " ");
					Elements content1=divs.select("#xxcontent > p");
					String xxcontent="";
					if(null != content1){
						for(Element content2:content1){
							 xxcontent+=(content2.text()+"\n");
							 System.out.println(xxcontent+"<<<<<");
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
					Log.d("123", title + "m");
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
		}).start();

	}

}
