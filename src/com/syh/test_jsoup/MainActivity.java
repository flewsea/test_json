package com.syh.test_jsoup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn01=(Button) findViewById(R.id.btn01);
        Button btn02=(Button) findViewById(R.id.btn02);
        Button btn03=(Button) findViewById(R.id.btn03);
        Button btn04=(Button) findViewById(R.id.btn04);
        Button btn05=(Button) findViewById(R.id.btn05);
        btn01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(MainActivity.this,second_Activity.class);
				startActivity(intent);
			}
		});
        btn02.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(MainActivity.this,third.class);
				startActivity(intent);
			}
		});
        btn03.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(MainActivity.this,four.class);
				startActivity(intent);
			}
		});
        btn04.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent();
				 intent.putExtra("num", "197");
				 intent.putExtra("fenlei", "productx");
				intent.setClass(MainActivity.this,five.class);
				startActivity(intent);
			}
		});
        btn05.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent();
				 intent.putExtra("num", "1");
				 intent.putExtra("fenlei", "about");
				intent.setClass(MainActivity.this,five.class);
				startActivity(intent);
			}
		});
    }


  

}
