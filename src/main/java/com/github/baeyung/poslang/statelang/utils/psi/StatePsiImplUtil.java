package com.github.baeyung.poslang.statelang.utils.psi;

import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.AttributeValue;
import com.github.baeyung.poslang.statelang.psi.EndTag;
import com.github.baeyung.poslang.statelang.psi.PairedTag;
import com.github.baeyung.poslang.statelang.psi.SelfClosingTag;
import com.github.baeyung.poslang.statelang.psi.StartTag;
import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.github.baeyung.poslang.statelang.spec.StateLanguageSpec;
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
                    .getText()
                    .toLowerCase();
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
                return unquote(valueNode.getText());
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
                    .getText()
                    .toLowerCase();
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
                    .getText()
                    .toLowerCase();
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
                    .getText()
                    .toLowerCase();
        }
        else
        {
            return null;
        }
    }

    public static String getName(Attribute element)
    {
        if (StateLanguageSpec.isNameDeclarationAttribute(element.getKey()))
        {
            return getValue(element);
        }

        return null;
    }

    public static PsiElement setName(Attribute element, String newName)
    {
        if (!StateLanguageSpec.isNameDeclarationAttribute(element.getKey()))
        {
            return element;
        }

        AttributeValue attributeValue = element.getAttributeValue();
        if (attributeValue != null)
        {
            ASTNode elementValueNode = attributeValue.getNode().findChildByType(StateTypes.STRING);
            if (elementValueNode != null)
            {
                Attribute property = StateElementFactory.createNameAttribute(element.getProject(), newName);

                if (property != null)
                {
                    AttributeValue newPropertyValue = property.getAttributeValue();
                    if (newPropertyValue != null)
                    {
                        ASTNode newValueNode = newPropertyValue.getNode().findChildByType(StateTypes.STRING);
                        if (newValueNode != null)
                        {
                            attributeValue
                                    .getNode()
                                    .replaceChild(elementValueNode, newValueNode);
                        }
                    }
                }
            }
        }
        return element;
    }

    public static PsiElement getNameIdentifier(Attribute element)
    {
        if (!StateLanguageSpec.isNameDeclarationAttribute(element.getKey()))
        {
            return null;
        }

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

    private static String unquote(String text)
    {
        if (text.length() >= 2 && text.charAt(0) == '"' && text.charAt(text.length() - 1) == '"')
        {
            return text.substring(1, text.length() - 1);
        }

        return text;
    }
}
