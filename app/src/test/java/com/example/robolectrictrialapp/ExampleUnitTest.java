package com.example.robolectrictrialapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowWebView;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

import android.content.Intent;
import android.os.Build;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@RunWith(RobolectricTestRunner.class)
@Config(shadows = ShadowActivity.class)
public class ExampleUnitTest {

    private MainActivity activity;
    private EditText et_input;
    private Button btn_calculation;
    private Button btn_next;
    private TextView tv_length;
    private ShadowActivity shadowActivity;
    private Intent intent;

    @Before
    public void setUp() throws Exception{

        //MainActivity生成
        activity = Robolectric.buildActivity(MainActivity.class).create().get();

        //View生成
        et_input = activity.findViewById(R.id.et_input);
        btn_calculation = activity.findViewById(R.id.btn_calculation);
        btn_next = activity.findViewById(R.id.btn_next);
        tv_length = activity.findViewById(R.id.tv_length);

        //共通のShadow生成
        shadowActivity = Shadows.shadowOf(activity);
    }

    @Test
    public void calculationInputJapan() {

        //EditTextに"Japan"を設定
        et_input.setText("Japan");

        //計算ボタンを押し、シュミレート
        btn_calculation.performClick();

        //期待値と実際の結果が同じかチェック
        assertEquals("5文字",tv_length.getText().toString());
    }

    @Test
    public void calculationInputAndroid() {

        //EditTextに"Android"を設定
        et_input.setText("Android");

        //計算ボタンを押し、シュミレート
        btn_calculation.performClick();

        //期待値と実際の結果が同じかチェック
        assertEquals("7文字",tv_length.getText().toString());
    }

    @Test
    public void confirmIntentContent() {

        //次へボタンを押し、シュミレート
        btn_next.performClick();

        //Intent取得
        intent = shadowActivity.peekNextStartedActivity();

        //期待値と実際の結果が同じかチェック
        String actual = intent.getStringExtra("url");
        assertEquals("https://qiita.com/leb397",actual);
    }

    @Test
    public void confirmNextActivityName() {

        //次へボタンを押し、シュミレート
        btn_next.performClick();

        //Intent取得
        intent = shadowActivity.peekNextStartedActivity();

        //次に起動するActivityのクラス名を取得
        ShadowIntent shadowIntent = Shadows.shadowOf(intent);
        String actualClassName = shadowIntent.getIntentClass().getName();
        assertEquals(WebViewActivity.class.getName(),actualClassName);
    }

    @Test
    public void confirmWebViewData() {

        //次へボタンを押し、シュミレート
        btn_next.performClick();

        //Intent取得
        intent = shadowActivity.peekNextStartedActivity();

        //WebView生成
        WebViewActivity webViewActivity = Robolectric.buildActivity(WebViewActivity.class,intent).create().get();
        WebView webView = webViewActivity.findViewById(R.id.webView);

        //WebViewに読み込まれたURLを取得
        ShadowWebView shadowWebView = Shadows.shadowOf(webView);
        String url = shadowWebView.getLastLoadedUrl();

        //取得したURLがNullでないかチェック
        assertNotNull(url);
    }
}