package com.github.baeyung.poslang.descriptor;

import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.intellij.xml.XmlAttributeDescriptor;
import com.intellij.xml.XmlElementDescriptor;
import com.intellij.xml.XmlElementsGroup;
import com.intellij.xml.XmlNSDescriptor;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

public class ElementDescriptor implements XmlElementDescriptor {

    private final TagDefinition definition;

    public ElementDescriptor(TagDefinition definition) {
        this.definition = definition;
    }

    @Override
    public PsiElement getDeclaration()
    {
        return null;
    }

    @Override
    public @NonNls String getName(PsiElement psiElement)
    {
        return "";
    }

    @Override
    public String getName() {
        return definition.getName();
    }

    @Override
    public void init(PsiElement psiElement)
    {

    }

    @Override
    public @NonNls String getQualifiedName()
    {
        return "";
    }

    @Override
    public @NonNls String getDefaultName()
    {
        return "";
    }

    @Override
    public XmlElementDescriptor[] getElementsDescriptors(XmlTag xmlTag)
    {
        return new XmlElementDescriptor[0];
    }

    @Override
    public @Nullable XmlElementDescriptor getElementDescriptor(XmlTag xmlTag, XmlTag xmlTag1)
    {
        return null;
    }

    @Override
    public XmlAttributeDescriptor[] getAttributesDescriptors(XmlTag context) {
        return definition.getAttributes()
                .stream()
                .map(AttributeDescriptor::new)
                .toArray(XmlAttributeDescriptor[]::new);
    }

    @Override
    public XmlAttributeDescriptor getAttributeDescriptor(String name, XmlTag context) {
        if (definition.getAttributes().contains(name)) {
            return new AttributeDescriptor(name);
        }
        return null;
    }

    @Override
    public @Nullable XmlAttributeDescriptor getAttributeDescriptor(XmlAttribute xmlAttribute)
    {
        return null;
    }

    @Override
    public @Nullable XmlNSDescriptor getNSDescriptor()
    {
        return null;
    }

    @Override
    public @Nullable XmlElementsGroup getTopGroup()
    {
        return null;
    }

    @Override
    public int getContentType()
    {
        return 0;
    }

    @Override
    public @Nullable String getDefaultValue()
    {
        return "";
    }
}