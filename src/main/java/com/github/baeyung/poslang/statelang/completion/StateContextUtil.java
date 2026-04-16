package com.github.baeyung.poslang.statelang.completion;

import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.SelfClosingTag;
import com.github.baeyung.poslang.statelang.psi.StartTag;
import com.github.baeyung.poslang.statelang.psi.EndTag;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;

public class StateContextUtil {

    public static boolean isInsideAttributeName(PsiElement element) {
        Attribute attr = PsiTreeUtil.getParentOfType(element, Attribute.class);
        return attr != null && attr.getFirstChild() == element;
    }

    public static String getEnclosingTagNameForAttribute(PsiElement element) {
        StartTag startTag = PsiTreeUtil.getParentOfType(element, StartTag.class);
        if (startTag != null) {
            return startTag.getTagName();
        }
        SelfClosingTag selfClosingTag = PsiTreeUtil.getParentOfType(element, SelfClosingTag.class);
        if (selfClosingTag != null) {
            return selfClosingTag.getTagName();
        }
        return null;
    }

    public static String getParentTagName(PsiElement element) {
        StartTag parentTag = PsiTreeUtil.getParentOfType(element, StartTag.class);
        if (parentTag != null) {
            return parentTag.getTagName();
        }
        return null;
    }

    public static boolean isInsideStartTag(PsiElement element) {
        return PsiTreeUtil.getParentOfType(element, StartTag.class) != null;
    }

    public static boolean isInsideSelfClosingTag(PsiElement element) {
        return PsiTreeUtil.getParentOfType(element, SelfClosingTag.class) != null;
    }

    public static boolean isInsideEndTag(PsiElement element) {
        return PsiTreeUtil.getParentOfType(element, EndTag.class) != null;
    }

    public static String getMatchingStartTagName(PsiElement element) {
        EndTag endTag = PsiTreeUtil.getParentOfType(element, EndTag.class);
        if (endTag != null && endTag.getParent() instanceof com.github.baeyung.poslang.statelang.psi.Tag tag) {
            StartTag startTag = tag.getStartTag();
            if (startTag != null) {
                return startTag.getTagName();
            }
        }
        return null;
    }
}
