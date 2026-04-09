package com.github.baeyung.poslang.phtmlang;

import com.intellij.lang.Language;
import com.intellij.lang.html.HTMLLanguage;

public class PhtmLanguage extends Language {

    public static final PhtmLanguage INSTANCE = new PhtmLanguage();

    private PhtmLanguage() {
        super(HTMLLanguage.INSTANCE, "PhtmLang");
    }
}
