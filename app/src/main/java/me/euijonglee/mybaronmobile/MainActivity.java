package me.euijonglee.mybaronmobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {

                view.loadUrl(request);

                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {



            //alert 처리
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {

                new AlertDialog.Builder(view.getContext())

                        .setTitle("알림")

                        .setMessage(message)

                        .setPositiveButton(android.R.string.ok,

                                new AlertDialog.OnClickListener(){

                                    public void onClick(DialogInterface dialog, int which) {

                                        result.confirm();

                                    }

                                })

                        .setCancelable(false)

                        .create()

                        .show();

                return true;

            }





            //confirm 처리
            @Override
            public boolean onJsConfirm(WebView view, String url, String message,

                                       final JsResult result) {

                new AlertDialog.Builder(view.getContext())

                        .setTitle("알림")

                        .setMessage(message)

                        .setPositiveButton("Yes",

                                new AlertDialog.OnClickListener(){

                                    public void onClick(DialogInterface dialog, int which) {

                                        result.confirm();

                                    }

                                })

                        .setNegativeButton("No",

                                new AlertDialog.OnClickListener(){

                                    public void onClick(DialogInterface dialog, int which) {

                                        result.cancel();

                                    }

                                })

                        .setCancelable(false)

                        .create()

                        .show();

                return true;

            }

        });


        mWebView.loadUrl("http://baron.hlight.tk/");



    }


    //웹뷰에서는 뒤로버튼 눌렀을 때 웹뷰가 꺼지는 현상이 발생함
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (mWebView.canGoBack()) {

                mWebView.goBack();

                return false;

            }

        }



        return super.onKeyDown(keyCode, event);

    }

}
