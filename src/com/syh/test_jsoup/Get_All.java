package com.syh.test_jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Get_All {
	public void JsoupGetImgUrl() {

		try {
			Document doc = Jsoup
					.connect("http://www.zhengshangci.com/productx.asp?id=205")
					.timeout(5000).get();
			Connection connect = Jsoup
					.connect("http://www.zhengshangci.com/productx.asp?id=205");

			Map<String, String> header = new HashMap<String, String>();

			header.put("Host",
					"http://www.zhengshangci.com/productx.asp?id=205");
			header.put("User-Agent",
					"	Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
			header.put("Accept",
					"	text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			header.put("Accept-Language", "zh-cn,zh;q=0.5");
			header.put("Accept-Charset", "	GB2312,utf-8;q=0.7,*;q=0.7");
			header.put("Connection", "keep-alive");
			Connection data = connect.data(header);
			System.out.println("共有超链接：" + doc.getElementsByTag("a").size());
			Log.d("nimeide", "还错？"); // Elements
			Elements divs = doc.select("div.nboxc");
			String linkBuilder = new String();
			linkBuilder = divs.toString();
			String urlimg = new String();
			Elements imgurls = doc.select("img[src]");
			if (divs != null) {
				for (Element imgurl : imgurls) {
					urlimg += (imgurl.attr("abs:src"));
					urlimg += ("");
					urlimg += (imgurl.text());
					System.out.println(urlimg);
				}
			}

		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
