package com.github.baeyung.poslang.statelang;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StatePsiUtil {

    @Nullable
    public static String getStringText(@Nullable PsiElement stringElement) {
        if (stringElement == null || stringElement.getText() == null) return null;
        String text = stringElement.getText();
        if (text.length() >= 2) {
            return text.substring(1, text.length() - 1);
        }
        return text;
    }

    @Nullable
    public static String getAttributeValue(@NotNull PsiElement element, @NotNull String targetAttrName) {
        for (PsiElement child : element.getChildren()) {
            
            PsiElement attrNameToken = child.getFirstChild();
            
            if (attrNameToken != null) {
                if (targetAttrName.equalsIgnoreCase(attrNameToken.getFirstChild().getText())) {

                    return getStringText(child.getChildren()[0].getLastChild());
                }
            }
        }
        return null;
    }
}