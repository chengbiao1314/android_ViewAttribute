package com.chengbiao.viewattribute;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    TextView tv_clickPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testClickPartView();
    }

    private void testClickPartView(){
        tv_clickPart = (TextView) findViewById(R.id.tv_clickPart);
        tv_clickPart.setClickable(true);
        tv_clickPart.setText(getClickableSpan());
        tv_clickPart.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private SpannableString getClickableSpan() {
//        OnClickListener l = new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Click Success", Toast.LENGTH_SHORT).show();
//            }
//        };

        SpannableString spanableInfo = new SpannableString("This is a test, Click Me");
        int start = 16;
        int end = spanableInfo.length();
        spanableInfo.setSpan(new Clickable(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        spanableInfo.setSpan(new NoUnderlineSpan(),start, end, Spanned.SPAN_MARK_MARK);
        return spanableInfo;
    }

    class Clickable extends ClickableSpan implements OnClickListener{
//        private final View.OnClickListener mListener;

//        public Clickable(OnClickListener l){
//            mListener = l;
//        }

        @Override
        public void onClick(View v){
//            mListener.onClick(v);
            tv_clickPart.setText(getClickableSpan());
            Toast.makeText(MainActivity.this, "Click Success", Toast.LENGTH_SHORT).show();
        }
    }

    public class NoUnderlineSpan extends UnderlineSpan {

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.RED);
            ds.setUnderlineText(false);
        }
    }

}
