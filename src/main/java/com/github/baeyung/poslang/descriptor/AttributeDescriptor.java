package com.github.baeyung.poslang.descriptor;

import com.intellij.openapi.util.NlsContexts;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlElement;
import com.intellij.xml.XmlAttributeDescriptor;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

public class AttributeDescriptor implements XmlAttributeDescriptor {

    private final String name;

    public AttributeDescriptor(String name) {
        this.name = name;
    }

    @Override
    public PsiElement getDeclaration() {
        return null;
    }

    @Override
    public @NonNls String getName(PsiElement context) {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void init(PsiElement element) {}

    @Override
    public boolean isRequired() {
        return false;
    }

    @Override
    public boolean isFixed() {
        return false;
    }

    @Override
    public boolean hasIdType() {
        return false;
    }

    @Override
    public boolean hasIdRefType() {
        return false;
    }

    @Override
    public @Nullable String getDefaultValue() {
        return null;
    }

    @Override
    public boolean isEnumerated() {
        return false;
    }

    @Override
    public String @Nullable [] getEnumeratedValues() {
        return null;
    }

    @Override
    public @Nullable @NlsContexts.DetailedDescription String validateValue(XmlElement context, String value) {
        return null; // null means "value is valid"
    }
}