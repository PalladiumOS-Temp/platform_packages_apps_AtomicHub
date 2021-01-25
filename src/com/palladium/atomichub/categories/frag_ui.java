package com.palladium.atomichub.categories;

import android.content.ContentResolver;
import android.os.Bundle;
import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.*;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.provider.Settings;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import android.os.ServiceManager;
import android.content.res.Resources;
import android.app.ActionBar;
import androidx.preference.*;
import com.palladium.atomichub.*;
import java.util.Objects;
import java.util.List;
import android.content.pm.PackageManager;
import static android.os.UserHandle.USER_SYSTEM;
import android.app.UiModeManager;
import android.os.RemoteException;
import com.android.internal.util.palladium.ThemesUtils;
import com.android.internal.util.palladium.PalladiumUtils;

public class frag_ui extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    private static final String POCKET_JUDGE = "pocket_judge";
    private static final String PREF_ROUNDED_CORNER = "rounded_ui";
    private static final String PREF_SB_HEIGHT = "statusbar_height";
    private static final String SWITCH_STYLE = "switch_style";
    private ListPreference mRoundedUi;
    private ListPreference mSbHeight;
    private IOverlayManager mOverlayService;
    private Preference mPocketJudge;
    private IOverlayManager mOverlayManager;
    private ListPreference mSwitchStyle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ps_ui);
        mOverlayService = IOverlayManager.Stub
                .asInterface(ServiceManager.getService(Context.OVERLAY_SERVICE));
        
        PreferenceScreen prefScreen = getPreferenceScreen();
        //Feature Additon!
        final Resources res = getResources();
        mPocketJudge = (Preference) prefScreen.findPreference(POCKET_JUDGE);
        boolean mPocketJudgeSupported = res.getBoolean(
                com.android.internal.R.bool.config_pocketModeSupported);
        if (!mPocketJudgeSupported)
            prefScreen.removePreference(mPocketJudge);

        mRoundedUi = (ListPreference) findPreference(PREF_ROUNDED_CORNER);
        int roundedValue = getOverlayPosition(ThemesUtils.UI_RADIUS);
        if (roundedValue != -1) {
            mRoundedUi.setValue(String.valueOf(roundedValue + 2));
        } else {
            mRoundedUi.setValue("1");
        }
        mRoundedUi.setSummary(mRoundedUi.getEntry());
        mRoundedUi.setOnPreferenceChangeListener(this);

        mSbHeight = (ListPreference) findPreference(PREF_SB_HEIGHT);
        int sbHeightValue = getOverlayPosition(ThemesUtils.STATUSBAR_HEIGHT);
        if (sbHeightValue != -1) {
            mSbHeight.setValue(String.valueOf(sbHeightValue + 2));
        } else {
            mSbHeight.setValue("1");
        }
        mSbHeight.setSummary(mSbHeight.getEntry());
        mSbHeight.setOnPreferenceChangeListener(this);

        mSwitchStyle = (ListPreference) findPreference(SWITCH_STYLE);
        int switchStyle = Settings.System.getInt(getContext().getContentResolver(),
                Settings.System.SWITCH_STYLE, 1);
        int valueIndex = mSwitchStyle.findIndexOfValue(String.valueOf(switchStyle));
        mSwitchStyle.setValueIndex(valueIndex >= 0 ? valueIndex : 0);
        mSwitchStyle.setSummary(mSwitchStyle.getEntry());
        mSwitchStyle.setOnPreferenceChangeListener(this);


    }

    public void handleOverlays(String packagename, Boolean state, IOverlayManager mOverlayManager) {
        try {
            mOverlayService.setEnabled(packagename, state, USER_SYSTEM);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
        if (preference == mRoundedUi) {
            String rounded = (String) newValue;
            int roundedValue = Integer.parseInt(rounded);
            mRoundedUi.setValue(String.valueOf(roundedValue));
            String overlayName = getOverlayName(ThemesUtils.UI_RADIUS);
                if (overlayName != null) {
                    handleOverlays(overlayName, false, mOverlayManager);
                }
                if (roundedValue > 1) {
                    handleOverlays(ThemesUtils.UI_RADIUS[roundedValue -2],
                            true, mOverlayManager);
            }
            mRoundedUi.setSummary(mRoundedUi.getEntry());
            return true;
            } 
            else if (preference == mSbHeight) {
            String sbheight = (String) newValue;
            int sbheightValue = Integer.parseInt(sbheight);
            mSbHeight.setValue(String.valueOf(sbheightValue));
            String overlayName = getOverlayName(ThemesUtils.STATUSBAR_HEIGHT);
                if (overlayName != null) {
                    handleOverlays(overlayName, false, mOverlayManager);
                }
                if (sbheightValue > 1) {
                    handleOverlays(ThemesUtils.STATUSBAR_HEIGHT[sbheightValue -2],
                            true, mOverlayManager);
            }
            mSbHeight.setSummary(mSbHeight.getEntry());
            return true;
        }
        else if (preference == mSwitchStyle) {
                String value = (String) newValue;
                Settings.System.putInt(getContext().getContentResolver(), Settings.System.SWITCH_STYLE, Integer.valueOf(value));
                int valueIndex = mSwitchStyle.findIndexOfValue(value);
                mSwitchStyle.setSummary(mSwitchStyle.getEntries()[valueIndex]);
	}
        return false;
    }
    private int getOverlayPosition(String[] overlays) {
        int position = -1;
        for (int i = 0; i < overlays.length; i++) {
            String overlay = overlays[i];
            if (PalladiumUtils.isThemeEnabled(overlay)) {
                position = i;
            }
        }
        return position;
    }
    private String getOverlayName(String[] overlays) {
        String overlayName = null;
        for (int i = 0; i < overlays.length; i++) {
            String overlay = overlays[i];
            if (PalladiumUtils.isThemeEnabled(overlay)) {
                overlayName = overlay;
            }
        }
        return overlayName;
    }

}