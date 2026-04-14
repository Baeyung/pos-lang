package com.github.baeyung.poslang.statelang.highlighting;

import com.github.baeyung.poslang.statelang.StateFileType;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class StateColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Brackets", StateSyntaxHighlighter.BRACKETS),
            new AttributesDescriptor("Tag Name", StateSyntaxHighlighter.TAG_NAME),
            new AttributesDescriptor("Attribute Name", StateSyntaxHighlighter.IDENTIFIER),
            new AttributesDescriptor("Attribute Value", StateSyntaxHighlighter.STRING),
            new AttributesDescriptor("Comment", StateSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("Operator", StateSyntaxHighlighter.OPERATOR),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return StateFileType.INSTANCE.getIcon();
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new StateSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "<!-- Example of State File -->\n" +
                "<Button class=\"primary\" disabled=\"false\">\n" +
                "    <Text value=\"Click Me\" />\n" +
                "</Button>\n";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "StateLang";
    }
}
