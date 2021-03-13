package com.palladium.atomichub.categories;

import android.content.ContentResolver;
import android.os.Bundle;
import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.settings.Utils;
import android.os.ServiceManager;
import androidx.preference.*;
import com.palladium.atomichub.*;
import android.app.ActionBar;

import com.palladium.atomichub.preference.SecureSettingListPreference;
import android.os.SystemProperties;
public class frag_misc extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    private IOverlayManager mOverlayService;
    private Context mContext;
    private static final String SCROLLINGCACHE_PREF = "pref_scrollingcache";
    private static final String SCROLLINGCACHE_PERSIST_PROP = "persist.sys.scrollingcache";
    private static final String SCROLLINGCACHE_DEFAULT = "2";

    private ListPreference mScrollingCachePref;


    private static final String KEY_FOD_RECOGNIZING_ANIM = "fod_recognizing_animation";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ps_misc);
        mOverlayService = IOverlayManager.Stub
                .asInterface(ServiceManager.getService(Context.OVERLAY_SERVICE));
        //Feature Additon!
        mContext = getContext();
	    mScrollingCachePref = (ListPreference) findPreference(SCROLLINGCACHE_PREF);
        mScrollingCachePref.setValue(SystemProperties.get(SCROLLINGCACHE_PERSIST_PROP,
        SystemProperties.get(SCROLLINGCACHE_PERSIST_PROP, SCROLLINGCACHE_DEFAULT)));
        mScrollingCachePref.setOnPreferenceChangeListener(this);

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
        if (preference == mScrollingCachePref) {
            if (newValue != null) {
                SystemProperties.set(SCROLLINGCACHE_PERSIST_PROP, (String) newValue);
            }
            return true;
	}
        return true;
    }
}