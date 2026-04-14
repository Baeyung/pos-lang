// This is a generated file. Not intended for manual editing.
package com.github.baeyung.poslang.statelang.psi.impl;

import java.util.List;

import com.github.baeyung.poslang.statelang.utils.psi.StatePsiImplUtil;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.baeyung.poslang.statelang.psi.*;

public class SelfClosingTagImpl extends ASTWrapperPsiElement implements SelfClosingTag {

  public SelfClosingTagImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull Visitor visitor) {
    visitor.visitSelfClosingTag(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) accept((Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<Attribute> getAttributeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, Attribute.class);
  }

  @Override
  public String getTagName() {
    return StatePsiImplUtil.getTagName(this);
  }

}
