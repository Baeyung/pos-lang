package com.github.baeyung.poslang.statelang;

import com.intellij.lang.Language;
import com.intellij.lang.xml.XMLLanguage;

public class StateLanguage extends Language {

    public static final StateLanguage INSTANCE = new StateLanguage();

    private StateLanguage() {
        super("StateLang");
    }
}
