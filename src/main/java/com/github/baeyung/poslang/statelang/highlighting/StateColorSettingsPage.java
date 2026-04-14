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
            new AttributesDescriptor("Tag name", StateSyntaxHighlighter.TAG_NAME),
            new AttributesDescriptor("Attribute name", StateSyntaxHighlighter.IDENTIFIER),
            new AttributesDescriptor("Attribute value", StateSyntaxHighlighter.STRING),
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
        return """
                <!-- Root level comment -->
                <statefile loader="mainLoader.phtm">
                    <!-- Include variations -->
                    <include file="common.states" exclude="debug"/>

                    <!-- Exit definition -->
                    <exit name="endState" mainState="true" rootStart="yes"/>

                    <!-- Global event (allowed in statefileBody) -->
                    <event name="globalEvent" next="initState" audit="true" pnp="yes"/>

                    <!-- Global data -->
                    <data name="globalVar" value="123" calculate="false" comment="global data"/>

                    <!-- First state -->
                    <state name="initState" frame="mainFrame" helpRef="help1" like="base"
                           sound="start.wav" page="home" prompt="Welcome"
                           picture="img.png" keyboard="default" comment="initial state">

                        <!-- State level comment -->
                        <!-- This is inside state -->

                        <!-- State data -->
                        <data name="counter" value="0" calculate="true" comment="counter data"/>

                        <!-- State event -->
                        <event name="onStart"
                               next="processingState"
                               callSubstate="subFlow"
                               substateNext="resumeHere"
                               permission="admin"
                               permissionFail="deniedState"
                               ppi="ppiValue"
                               audit="true"
                               pnp="yes"
                               comment="event comment"/>

                        <!-- Include inside state -->
                        <include file="nested.states"/>

                    </state>

                    <!-- Second state -->
                    <state name="processingState" frame="procFrame" page="processPage">

                        <data name="temp" value="42"/>

                        <event name="onProcess" Next="endState"/>

                    </state>

                    <!-- Third state with minimal attrs -->
                    <state name="endState">

                        <event name="finish"/>

                    </state>

                </statefile>
        """;
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
