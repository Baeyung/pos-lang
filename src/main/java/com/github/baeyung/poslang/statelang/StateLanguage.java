package com.github.baeyung.poslang.statelang;

import com.intellij.lang.Language;

public class StateLanguage extends Language {

    public static final StateLanguage INSTANCE = new StateLanguage();

    private StateLanguage() {
        super("StateLang");
    }
}
