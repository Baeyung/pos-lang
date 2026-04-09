package com.github.baeyung.poslang.statelang;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class StateFileType extends LanguageFileType {

    public static final StateFileType INSTANCE = new StateFileType();

    private StateFileType() {
        super(StateLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "State File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "State language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "state";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return null;
    }
}