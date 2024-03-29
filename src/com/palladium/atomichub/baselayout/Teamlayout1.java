package com.palladium.atomichub.baselayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.android.settings.R;
import com.palladium.atomichub.*;

public class Teamlayout1 extends LinearLayout {


    public Teamlayout1(Context context) {
        super(context);
        abrir(context, null,0,0);
    }

    public Teamlayout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        abrir(context, attrs, 0,0);
    }

    public Teamlayout1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        abrir(context, attrs, defStyleAttr,0);
        Log.d("Geo","Super");
    }

    public Teamlayout1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        abrir(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void abrir(Context c, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        setElevation(50);
        LinearLayout lj = new LinearLayout(c);
        addView(lj);
        lj.setGravity(Gravity.CENTER);
        lj.setElevation(50);
        LayoutParams plj = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lj.setOrientation(VERTICAL);
        plj.weight = 1;
        plj.setMargins(0,50,0,0);
        plj.setMarginStart(50);
        plj.setMarginEnd(50);

        TextView k = new TextView(c);
        k.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        Drawable kp = getResources().getDrawable(R.drawable.team_base);
        lj.setBackground(kp);
        lj.setLayoutParams(plj);
        //Start of Action...
        LinearLayout lj1 = new LinearLayout(c);
        lj.addView(lj1);
        lj1.setOrientation(HORIZONTAL);
        lj1.setWeightSum(3);
        ImageView pot = new ImageView(c);
        Drawable potd = getResources().getDrawable(R.mipmap.ic_loke);
        pot.setImageDrawable(potd);
        LayoutParams ppot = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
            ppot.gravity = Gravity.CENTER_VERTICAL;
        ppot.weight = 1;
        pot.setPadding(50,50,50,50);
        lj1.addView(pot);
        ImageView tj = new ImageView(c);
        Drawable d = getResources().getDrawable(R.drawable.ic_loke_head);
        tj.setImageDrawable(d);
        LinearLayout.LayoutParams ptj = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        ptj.weight = 1;
        ptj.gravity = Gravity.CENTER;
        lj1.addView(tj);
        tj.setLayoutParams(ptj);
        LinearLayout km = new LinearLayout(c);
        lj1.addView(km);
        LayoutParams im = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
im.setMargins(0,0,0,2);
        im.weight = 1;
        km.setLayoutParams(im);
        ImageButton ghj = new ImageButton(c);
        km.addView(ghj);
        ghj.setBackgroundColor(Color.TRANSPARENT);
        Drawable ghjl = getResources().getDrawable(R.drawable.ic_github);
        ghj.setImageDrawable(ghjl);
        LayoutParams imv = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        imv.setMarginStart(50);
        imv.weight = 1;
        ghj.setLayoutParams(imv);
        ImageButton tmj = new ImageButton(c);
        km.addView(tmj);
        Drawable tmjl = getResources().getDrawable(R.drawable.ic_telegram);
        tmj.setBackgroundColor(Color.TRANSPARENT);
        tmj.setImageDrawable(tmjl);
        tmj.setLayoutParams(imv);

        ghj.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://github.com/Lokesh773";
                Intent Getintent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                c.startActivity(Getintent);
            }
        });

        tmj.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://t.me/l_o_k_e";
                Intent Getintent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                c.startActivity(Getintent);
            }
        });

        // End of Action



    }

}