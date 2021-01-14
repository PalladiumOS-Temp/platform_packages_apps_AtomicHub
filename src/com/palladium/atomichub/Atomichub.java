package com.palladium.atomichub;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.android.internal.logging.nano.MetricsProto;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;
import com.palladium.atomichub.categories.frag_ui;
import com.palladium.atomichub.categories.frag_theme;
import com.palladium.atomichub.categories.frag_keys;
import com.palladium.atomichub.categories.frag_misc;
import com.palladium.atomichub.categories.frag_team;
import com.palladium.atomichub.categories.frag_statusbar;

public class Atomichub extends SettingsPreferenceFragment implements  View.OnClickListener{

    final String[] target = new String[1];
    FrameLayout c1,c2,c3,c4,c5,c6;
    LinearLayout c0;
    ImageView btnicon;
    TextView title,summary;
    HorizontalScrollView horizontalScrollView;
    ImageButton btntransistion;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.atomichub, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        
        super.onViewCreated(view, savedInstanceState);
		getActivity().getActionBar().hide();        
        horizontalScrollView = view.findViewById(R.id.hsv_card);
        btntransistion = view.findViewById(R.id.btn_trans);
        btntransistion.setOnClickListener(this);
        btnicon = view.findViewById(R.id.img1_icon);
        title = view.findViewById(R.id.tv_title_big);
        summary = view.findViewById(R.id.tv_summary_big);
        c0 = view.findViewById(R.id.card);
        c1 = view.findViewById(R.id.card1);
        c1.setOnClickListener(this);
        c2 = view.findViewById(R.id.card2);
        c2.setOnClickListener(this);
        c3 = view.findViewById(R.id.card3);
        c3.setOnClickListener(this);
        c4 = view.findViewById(R.id.card4);
        c4.setOnClickListener(this);
        c5 = view.findViewById(R.id.card5);
        c5.setOnClickListener(this);
        c6 = view.findViewById(R.id.card6);
        c6.setOnClickListener(this);
        target[0] = "UI";


    }


    @Override
    public void onClick(View view) {

        int id = view.getId();

        if(id == R.id.card1){
            // UI
            Drawable d,e;
            d = view.getResources().getDrawable(R.drawable.ic_ui_big_card);
            e = view.getResources().getDrawable(R.drawable.ic_ui_logo);
            btnicon.setImageDrawable(e);
            c0.setBackground(d);
            title.setText(R.string.UI_Title);
            summary.setText(R.string.UI_Summary);
            target[0] = "UI";
        }

        if(id == R.id.card2){
            // Theme
            Drawable d,e;
            d = view.getResources().getDrawable(R.drawable.ic_theme_big_card);
            c0.setBackground(d);
            e = view.getResources().getDrawable(R.drawable.ic_theme_card_logo);
            btnicon.setImageDrawable(e);
            title.setText(R.string.Theme_Title);
            summary.setText(R.string.Theme_summary);
            target[0] = "Theme";
        }

        if(id == R.id.card3){
            // Status Bar
            Drawable d,e;
            d = view.getResources().getDrawable(R.drawable.ic_status_big_card);
            c0.setBackground(d);
            e = view.getResources().getDrawable(R.drawable.ic_status_card_logo );
            btnicon.setImageDrawable(e);
            title.setText(R.string.Status_Title);
            summary.setText(R.string.Status_summary);
            target[0] = "Statusbar";
        }

        if(id == R.id.card4){
            // Button
            Drawable d,e;
            d = view.getResources().getDrawable(R.drawable.ic_button_big_card);
            c0.setBackground(d);
            e = view.getResources().getDrawable(R.drawable.ic_button_logo);
            btnicon.setImageDrawable(e);
            title.setText(R.string.Button_Title);
            summary.setText(R.string.Button_Summary);
            target[0] = "Button";
        }

        if(id == R.id.card5){
            //  Misc
            Drawable d,e;
            d = view.getResources().getDrawable(R.drawable.ic_misc_big_card);
            c0.setBackground(d);
            e = view.getResources().getDrawable(R.drawable.ic_misc_card_logo);
            btnicon.setImageDrawable(e);
            title.setText(R.string.Misc_Title);
            summary.setText(R.string.Misc_Summary);
            target[0] = "Misc";
        }

        if(id == R.id.card6){
            // Team
            Drawable d,e;
            d = view.getResources().getDrawable(R.drawable.ic_team_big_card);
            c0.setBackground(d);
            e = view.getResources().getDrawable(R.drawable.ic_team_card_logo);
            btnicon.setImageDrawable(e);
            title.setText(R.string.Team_Title);
            summary.setText(R.string.Team_summary);
            target[0] = "Team";
        }

        if(id == R.id.btn_trans){
            if(target[0].equals("UI")){
                frag_ui destf = new frag_ui();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(this.getId(), destf);
                transaction.addToBackStack(null);
                transaction.commit();
            }
            if(target[0].equals("Theme")){
                frag_theme destf = new frag_theme();
                FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                transaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction1.replace(this.getId(), destf);
                transaction1.addToBackStack(null);
                transaction1.commit();

            }
            if(target[0].equals("Statusbar")){
                frag_statusbar destf = new frag_statusbar();
                FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                transaction2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction2.replace(this.getId(), destf);
                transaction2.addToBackStack(null);
                transaction2.commit();
            }
            if(target[0].equals("Button")){
                frag_keys destf = new frag_keys();
                FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
                transaction3.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction3.replace(this.getId(), destf);
                transaction3.addToBackStack(null);
                transaction3.commit();
            }
            if(target[0].equals("Misc")){
                frag_misc destf = new frag_misc();
                FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
                transaction4.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction4.replace(this.getId(), destf);
                transaction4.addToBackStack(null);
                transaction4.commit();
            }
            if(target[0].equals("Team")){
                frag_team destf = new frag_team();
                FragmentTransaction transaction5 = getFragmentManager().beginTransaction();
                transaction5.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction5.replace(this.getId(), destf);
                transaction5.addToBackStack(null);
                transaction5.commit();
            }
        }

    }



    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.PALLADIUM;
    }



}