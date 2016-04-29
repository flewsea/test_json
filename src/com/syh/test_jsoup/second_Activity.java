package com.syh.test_jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;



public class second_Activity extends Activity implements OnClickListener {
	public static final int response_str = 0;
	private Button btn_sec_01;
	private TextView tv_sec_01;
	private Message message;

private	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case response_str:
			String response = (String) msg.obj;
			// 在这里进行UI操作，将结果显示到界面上
			System.out.println(response);
			tv_sec_01.setText(response);
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
		setContentView(R.layout.second_acitvity);
		tv_sec_01 = (TextView) findViewById(R.id.tv_sec_01);
		btn_sec_01 = (Button) findViewById(R.id.btn_sec_01);
		btn_sec_01.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		if (v.getId() == R.id.btn_sec_01) {
			JsoupNetConnect();
		}
	}

	public void JsoupNetConnect() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO 自动生成的方法存根

				try {
					Document doc = Jsoup.connect(
							"http://www.zhengshangci.com/productx.asp?id=205").timeout(5000).get();
					Connection connect = Jsoup.connect("http://www.zhengshangci.com/productx.asp?id=205");
					
					Map<String, String> header = new HashMap<String, String>();
					
					header.put("Host", "http://www.zhengshangci.com/productx.asp?id=205");
					header.put("User-Agent", "	Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
					header.put("Accept", "	text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
					header.put("Accept-Language", "zh-cn,zh;q=0.5");
					header.put("Accept-Charset", "	GB2312,utf-8;q=0.7,*;q=0.7");
					header.put("Connection", "keep-alive");
					Connection data = connect.data(header);
				       System.out.println("共有超链接："+ doc.getElementsByTag("a").size());
				       					Log.d("nimeide", "还错？"); // Elements
					Elements divs = doc.select("div.nboxc");
					String linkBuilder = new String();
					String urlimg=new String();
					Log.d("safe", "到这里还没报错，有志气");
					if (divs != null) {
						for (Element div:divs) {
							Elements links = div.select("a[href]");
							if (null != links) {
								for (Element link : links) {
									linkBuilder+=(link.attr("abs:href"));
									linkBuilder+=("");
									linkBuilder+=(link.text());
	
								}

			
							}
							Elements imgurls=div.select("img[src]");
							for(Element imgurl:imgurls){
								urlimg+=(imgurl.attr("abs:src"));
								urlimg+=("");
								urlimg+=(imgurl.text());
								
							}
							
							System.out.println(urlimg);
						}
						}
					
					Message message=new Message();
					message.what = response_str;
					message.obj = linkBuilder;
					handler.sendMessage(message);
					System.out.println(message);
					Log.d("123", message.toString());
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}).start();

	}
}
