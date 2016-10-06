package com;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.api.req.ApiReq;
import com.api.rsp.GetIpInfoRsp;
import com.micky.retrofit.R;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Project retrofitrxandroiddagger2
 * @Packate com.micky.retrofitrxandroiddagger2
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-21 17:35
 * @Version 0.1
 */
public class MainActivity extends AppCompatActivity {

    private static final String ENDPOINT = "http://ip.taobao.com";
    private TextView mTvContent;
    private ProgressBar mProgressBar;
    private  static final String TAG ="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                ApiReq apiReq = retrofit.create(ApiReq.class);
                apiReq.getIpInfo("111.23.45.118")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<GetIpInfoRsp>() {
                            @Override
                            public void onStart() {
                                mProgressBar.setVisibility(View.VISIBLE);
                                mTvContent.setText("正在查找，請稍後...");
                                Log.e(TAG,"onStart");
                            }

                            @Override
                            public void onCompleted() {
                                mProgressBar.setVisibility(View.GONE);
                                Log.e(TAG,"onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG,"onError");
                                mProgressBar.setVisibility(View.GONE);
                                if(e.getMessage()!=null){
                                    mTvContent.setText("error:"+e.getMessage());

                                }
                            }

                            @Override
                            public void onNext(GetIpInfoRsp getIpInfoResponse) {
                                Log.e(TAG,"onNext");
                                mTvContent.setText(getIpInfoResponse.data.country+","+getIpInfoResponse.data.area);
                            }
                        });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
