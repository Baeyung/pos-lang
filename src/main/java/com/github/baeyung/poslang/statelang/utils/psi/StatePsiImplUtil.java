//package com.github.baeyung.poslang.statelang.utils.psi;
//
//import com.github.baeyung.poslang.statelang.psi.Attribute;
//import com.github.baeyung.poslang.statelang.psi.EndTag;
//import com.github.baeyung.poslang.statelang.psi.SelfClosingTag;
//import com.github.baeyung.poslang.statelang.psi.StartTag;
//import com.github.baeyung.poslang.statelang.psi.StateTypes;
//import com.intellij.lang.ASTNode;
//import com.intellij.openapi.util.text.StringUtil;
//
//public class StatePsiImplUtil
//{
//    public static String getKey(Attribute element)
//    {
//        ASTNode keyNode = element
//                .getNode()
//                .findChildByType(StateTypes.IDENTIFIER);
//
//        if (keyNode != null && StringUtil.isNotEmpty(keyNode.getText()))
//        {
//            return keyNode.getText().toLowerCase();
//        }
//        else
//        {
//            return null;
//        }
//    }
//
//    public static String getValue(Attribute element)
//    {
//        ASTNode valueNode = element
//                .getNode()
//                .findChildByType(StateTypes.STRING);
//
//        if (valueNode != null && StringUtil.isNotEmpty(valueNode.getText()))
//        {
//            return valueNode.getText().toLowerCase();
//        }
//        else
//        {
//            return null;
//        }
//    }
//
//    public static String getTagName(StartTag element)
//    {
//        ASTNode nameNode = element
//                .getNode()
//                .findChildByType(StateTypes.TAG_NAME);
//
//        if (nameNode != null && StringUtil.isNotEmpty(nameNode.getText()))
//        {
//            return nameNode.getText().toLowerCase();
//        }
//        else
//        {
//            return null;
//        }
//    }
//
//    public static String getTagName(EndTag element)
//    {
//        ASTNode nameNode = element
//                .getNode()
//                .findChildByType(StateTypes.TAG_NAME);
//
//        if (nameNode != null && StringUtil.isNotEmpty(nameNode.getText()))
//        {
//            return nameNode.getText().toLowerCase();
//        }
//        else
//        {
//            return null;
//        }
//    }
//
//    public static String getTagName(SelfClosingTag element)
//    {
//        ASTNode nameNode = element
//                .getNode()
//                .findChildByType(StateTypes.TAG_NAME);
//
//        if (nameNode != null && StringUtil.isNotEmpty(nameNode.getText()))
//        {
//            return nameNode.getText().toLowerCase();
//        }
//        else
//        {
//            return null;
//        }
//    }
//}