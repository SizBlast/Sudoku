package hogent.sudoku;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class Prefs extends PreferenceFragment{
    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
