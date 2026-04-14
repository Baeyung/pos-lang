package com.github.baeyung.poslang.statelang.utils.psi;

import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.EndTag;
import com.github.baeyung.poslang.statelang.psi.SelfClosingTag;
import com.github.baeyung.poslang.statelang.psi.StartTag;
import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.intellij.lang.ASTNode;

public class StatePsiImplUtil
{
    public static String getKey(Attribute element)
    {
        ASTNode keyNode = element
                .getNode()
                .findChildByType(StateTypes.IDENTIFIER);

        if (keyNode != null)
        {
            return keyNode.getText();
        }
        else
        {
            return null;
        }
    }

    public static String getValue(Attribute element)
    {
        ASTNode valueNode = element
                .getNode()
                .findChildByType(StateTypes.STRING);

        if (valueNode != null)
        {
            return valueNode.getText();
        }
        else
        {
            return null;
        }
    }

    public static String getTagName(StartTag element)
    {
        ASTNode nameNode = element
                .getNode()
                .findChildByType(StateTypes.TAG_NAME);

        if (nameNode != null)
        {
            return nameNode.getText();
        }
        else
        {
            return null;
        }
    }

    public static String getTagName(EndTag element)
    {
        ASTNode nameNode = element
                .getNode()
                .findChildByType(StateTypes.TAG_NAME);

        if (nameNode != null)
        {
            return nameNode.getText();
        }
        else
        {
            return null;
        }
    }

    public static String getTagName(SelfClosingTag element)
    {
        ASTNode nameNode = element
                .getNode()
                .findChildByType(StateTypes.TAG_NAME);

        if (nameNode != null)
        {
            return nameNode.getText();
        }
        else
        {
            return null;
        }
    }
}