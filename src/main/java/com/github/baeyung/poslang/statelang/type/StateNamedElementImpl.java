package com.github.baeyung.poslang.statelang.type;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class StateNamedElementImpl extends ASTWrapperPsiElement implements StateNamedElement
{
    public StateNamedElementImpl(@NotNull ASTNode node)
    {
        super(node);
    }
}