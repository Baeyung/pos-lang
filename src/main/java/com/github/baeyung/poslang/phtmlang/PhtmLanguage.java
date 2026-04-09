package com.github.baeyung.poslang.phtmlang;

import com.intellij.lang.Language;

public class PhtmLanguage extends Language {

    public static final PhtmLanguage INSTANCE = new PhtmLanguage();

    private PhtmLanguage() {
        super("PhtmLang");
    }
}
