package com.palladium.atomichub.categories;

import android.content.ContentResolver;
import android.os.Bundle;
import android.os.Handler;
import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.*;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.settings.Utils;
import android.os.ServiceManager;
import com.palladium.atomichub.*;
import android.app.ActionBar;
import android.provider.Settings;
import com.android.internal.util.custom.FodUtils;
import com.android.internal.util.palladium.PalladiumUtils;

public class frag_misc extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    private static final String NAVBAR_VISIBILITY = "navbar_visibility";
    private static final String KEY_FOD_RECOGNIZING_ANIM = "fod_recognizing_animation";

    private IOverlayManager mOverlayService;
    private boolean mHasFod;
    private Context mContext;
    private SwitchPreference mNavbarVisibility;

    private boolean mIsNavSwitchingMode = false;
    private Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ps_misc);
        mOverlayService = IOverlayManager.Stub
                .asInterface(ServiceManager.getService(Context.OVERLAY_SERVICE));
        //Feature Additon!
        mContext = getContext();
        mHasFod = FodUtils.hasFodSupport(mContext);
        mNavbarVisibility = (SwitchPreference) findPreference(NAVBAR_VISIBILITY);
        PreferenceScreen prefScreen = getPreferenceScreen();

        if (!mHasFod) {
            prefScreen.removePreference(findPreference(KEY_FOD_RECOGNIZING_ANIM));
        }

        boolean defaultToNavigationBar = PalladiumUtils.deviceSupportNavigationBar(getActivity());
        boolean showing = Settings.System.getInt(getContentResolver(),
                Settings.System.FORCE_SHOW_NAVBAR,
                defaultToNavigationBar ? 1 : 0) != 0;
        updateBarVisibleAndUpdatePrefs(showing);

        mNavbarVisibility.setOnPreferenceChangeListener(this);

        mHandler = new Handler();

    }
    @Override
    public int getMetricsCategory() {
        return MetricsEvent.PALLADIUM;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();
        if (preference.equals(mNavbarVisibility)) {
            if (mIsNavSwitchingMode) {
                return false;
            }
            mIsNavSwitchingMode = true;
            boolean showing = ((Boolean)newValue);
            Settings.System.putInt(getContentResolver(), Settings.System.FORCE_SHOW_NAVBAR,
                    showing ? 1 : 0);
            updateBarVisibleAndUpdatePrefs(showing);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIsNavSwitchingMode = false;
                }
            }, 1500);
            return true;
        }
        return true;
    }

    private void updateBarVisibleAndUpdatePrefs(boolean showing) {
        mNavbarVisibility.setChecked(showing);
    }

}