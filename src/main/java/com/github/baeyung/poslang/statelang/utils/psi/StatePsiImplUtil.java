package com.github.baeyung.poslang.statelang.utils.psi;

import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.AttributeValue;
import com.github.baeyung.poslang.statelang.psi.EndTag;
import com.github.baeyung.poslang.statelang.psi.PairedTag;
import com.github.baeyung.poslang.statelang.psi.SelfClosingTag;
import com.github.baeyung.poslang.statelang.psi.StartTag;
import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.github.baeyung.poslang.statelang.utils.StateElementFactory;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;

public class StatePsiImplUtil
{
    public static String getKey(Attribute element)
    {
        ASTNode keyNode = element
                .getAttributeName()
                .getNode()
                .findChildByType(StateTypes.IDENTIFIER);

        if (keyNode != null && StringUtil.isNotEmpty(keyNode.getText()))
        {
            return keyNode
                    .getText();
        }
        else
        {
            return null;
        }
    }

    public static String getValue(Attribute element)
    {
        AttributeValue attributeValue = element.getAttributeValue();
        if (attributeValue != null)
        {
            ASTNode valueNode = attributeValue
                    .getNode()
                    .findChildByType(StateTypes.STRING);

            if (valueNode != null && StringUtil.isNotEmpty(valueNode.getText()))
            {
                String text = valueNode.getText();
                if (text.length() >= 2 && text.startsWith("\"") && text.endsWith("\"")) {
                    return text.substring(1, text.length() - 1);
                } else {
                    return text;
                }
            }
        }
        return null;
    }

    public static String getTagName(StartTag element)
    {
        ASTNode nameNode = element
                .getTagNameEl()
                .getNode()
                .findChildByType(StateTypes.TAG_NAME);

        if (nameNode != null && StringUtil.isNotEmpty(nameNode.getText()))
        {
            return nameNode
                    .getText();
        }
        else
        {
            return null;
        }
    }

    public static String getStartTagName(PairedTag pairedTag)
    {
        if (pairedTag != null)
        {
            return getTagName(pairedTag.getStartTag());
        }

        return null;
    }

    public static String getEndTagName(PairedTag pairedTag)
    {
        if (pairedTag != null)
        {
            return getTagName(pairedTag.getEndTag());
        }

        return null;
    }

    public static String getTagName(EndTag element)
    {
        ASTNode nameNode = element
                .getTagNameEl()
                .getNode()
                .findChildByType(StateTypes.TAG_NAME);

        if (nameNode != null && StringUtil.isNotEmpty(nameNode.getText()))
        {
            return nameNode
                    .getText();
        }
        else
        {
            return null;
        }
    }

    public static String getTagName(SelfClosingTag element)
    {
        ASTNode nameNode = element
                .getTagNameEl()
                .getNode()
                .findChildByType(StateTypes.TAG_NAME);

        if (nameNode != null && StringUtil.isNotEmpty(nameNode.getText()))
        {
            return nameNode
                    .getText();
        }
        else
        {
            return null;
        }
    }

    public static String getName(Attribute element)
    {
        return getValue(element);
    }

    public static PsiElement setName(Attribute element, String newName)
    {
        AttributeValue attributeValue = element.getAttributeValue();
        if (attributeValue != null)
        {
            ASTNode elementValueNode = attributeValue.getNode().findChildByType(StateTypes.STRING);
            if (elementValueNode != null)
            {
                Attribute property = StateElementFactory.createProperty(element.getProject(), newName);

                AttributeValue newPropertyValue = property.getAttributeValue();
                if (newPropertyValue != null)
                {
                    ASTNode newKeyNode = newPropertyValue.getNode().findChildByType(StateTypes.STRING);
                    elementValueNode.replaceChild(elementValueNode, newKeyNode);
                }
            }
        }
        return element;
    }

    public static PsiElement getNameIdentifier(Attribute element)
    {
        AttributeValue attributeValue = element.getAttributeValue();
        if (attributeValue != null)
        {
            ASTNode valueNode = attributeValue
                    .getNode()
                    .findChildByType(StateTypes.STRING);

            if (valueNode != null)
            {
                return valueNode.getPsi();
            }
        }

        return null;
    }
}