package com.github.vikramhalder.taglayout;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RelativeLayout simpleRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout simpleRelativeLayout = findViewById(R.id.simpleRelativeLayout);

        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(layoutParams);

        List<TextView> list=new ArrayList<>();
        int count=0;
        int countL=0;
        for(int i=0;i<30;i++) {
            int x=0;
            RelativeLayout.LayoutParams params1=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);;
            TextView TV = new TextView(this);
            TV.setId(i);
            TV.setText("bla"+i+"  ");
            TV.measure(0, 0);       //must call measure!
            TV.getMeasuredHeight();
            TV.getMeasuredWidth();
            list.add(TV);
            count+=TV.getMeasuredWidth() + 10;
            if(i==0) {
                x=0;
            }else if(i==1) {
                x=1;
                params1.addRule(RelativeLayout.RIGHT_OF, 0);
            }
            else if(count>=getScreenWidth()) {
                params1.addRule(RelativeLayout.BELOW,i-1);
                count=0;
                countL++;
                x=3;
            }else if(count<getScreenWidth()) {
                if(countL>0) {
                    x=2;
                    params1.addRule(RelativeLayout.RIGHT_OF, i-1);
                    params1.addRule(RelativeLayout.ALIGN_BOTTOM,i-1);
                }else {
                    x=1;
                    params1.addRule(RelativeLayout.RIGHT_OF, i-1);
                }
            }
            Log.i("ff",countL+"/"+x);
            layout.addView(TV, params1);
        }

        simpleRelativeLayout.addView(layout);


    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

}