// This is a generated file. Not intended for manual editing.
package com.github.baeyung.poslang.statelang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static generated.GeneratedTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.baeyung.poslang.statelang.psi.*;

public class StateStateAttrImpl extends ASTWrapperPsiElement implements StateStateAttr {

  public StateStateAttrImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StateVisitor visitor) {
    visitor.visitStateAttr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StateVisitor) accept((StateVisitor)visitor);
    else super.accept(visitor);
  }

}
