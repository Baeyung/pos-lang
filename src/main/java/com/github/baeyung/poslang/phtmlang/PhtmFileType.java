package com.github.baeyung.poslang.phtmlang;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PhtmFileType extends LanguageFileType {

    public static final PhtmFileType INSTANCE = new PhtmFileType();

    private PhtmFileType() {
        super(PhtmLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Phtm File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Phtm language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "phtm";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return AllIcons.FileTypes.Html;
    }
}