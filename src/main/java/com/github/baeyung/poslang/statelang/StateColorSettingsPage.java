package com.github.baeyung.poslang.statelang;

import java.util.Map;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;

public final class StateColorSettingsPage implements ColorSettingsPage
{

    // These labels will appear in the Settings menu on the right-hand side
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Keyword", StateSyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("Attribute name", StateSyntaxHighlighter.ATTRIBUTE),
            new AttributesDescriptor("String value", StateSyntaxHighlighter.VALUE),
            new AttributesDescriptor("Brackets", StateSyntaxHighlighter.BRACKET),
            new AttributesDescriptor("Operators", StateSyntaxHighlighter.OPERATOR),
            new AttributesDescriptor("Bad character", StateSyntaxHighlighter.BAD_CHAR)
    };

    @Override
    public Icon getIcon()
    {
        return AllIcons.FileTypes.Xhtml;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter()
    {
        return new StateSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText()
    {
        return """
        <!-- This is a block comment demonstrating comments -->
        <statefile loader="posapp.phtm">
            <include file="someother.state" exclude="stateName2" />
            <exit name="exitState" mainstate="mainState" />
        
            <state name="stateName" frame="frameName" sound="soundFile">
                <data name="dataName" calculate="calcExpression" value="123" />
                <event name="eventName" next="nextState" permission="permCondition" />
            </state>
        </statefile>
        """;
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap()
    {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors()
    {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors()
    {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName()
    {
        return "PosLang-StateFile";
    }
}