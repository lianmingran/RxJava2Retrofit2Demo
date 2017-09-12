package com.lian.rxjava2retrofit2demo.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lian.rxjava2retrofit2demo.R;
import com.lian.rxjava2retrofit2demo.entity.BaseEntity;
import com.lian.rxjava2retrofit2demo.entity.BaseEntity1;
import com.lian.rxjava2retrofit2demo.presenter.TestPresenter;
import com.lian.rxjava2retrofit2demo.rxhttp.OnReceiveListener;
import com.lian.rxjava2retrofit2demo.entity.TestEntity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public class MainActivity extends RxAppCompatActivity {
    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) this.findViewById(R.id.button1);
        button2 = (Button) this.findViewById(R.id.button2);
        button3 = (Button) this.findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test1();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test2();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test3();
            }
        });
    }



    public void test1() {
        TestPresenter.getInstance().test1(this, new OnReceiveListener<BaseEntity>() {
            @Override
            public void onSucceed(BaseEntity baseEntity) {
                Log.e("MainActivity", baseEntity.result.city);
                button1.setText("成功");
            }

            @Override
            public void onError(int errorCode, String msg) {
                super.onError(errorCode, msg);
                Log.e("MainActivity", msg);
                button1.setText("失败");
            }
        });
    }

    public void test2() {
        TestPresenter.getInstance().test2(this, new OnReceiveListener<BaseEntity>() {
            @Override
            public void onSucceed(BaseEntity baseEntity) {
                Log.e("MainActivity", baseEntity.result.city);
                button2.setText("成功");
            }

            @Override
            public void onError(int errorCode, String msg) {
                super.onError(errorCode, msg);
                Log.e("MainActivity", msg);
                button2.setText("失败");
            }
        });
    }

    public void test3() {
        TestPresenter.getInstance().getweixinjingxuan(this, new OnReceiveListener<BaseEntity1>() {
            @Override
            public void onSucceed(BaseEntity1 baseEntity1) {
                if(baseEntity1.result!=null){
                    Log.e("MainActivity", baseEntity1.result.list.get(0).title+"");
                }
                button3.setText("成功");
            }

            @Override
            public void onError(int errorCode, String msg) {
                super.onError(errorCode, msg);
                Log.e("MainActivity", msg);
                button2.setText("失败");
            }
        });
    }


}
