// This is a generated file. Not intended for manual editing.
package com.github.baeyung.poslang.statelang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.baeyung.poslang.statelang.psi.StateTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.baeyung.poslang.statelang.psi.*;
import com.github.baeyung.poslang.statelang.utils.psi.StatePsiImplUtil;

public class AttributeImpl extends ASTWrapperPsiElement implements Attribute {

  public AttributeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull Visitor visitor) {
    visitor.visitAttribute(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) accept((Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public AttributeName getAttributeName() {
    return findNotNullChildByClass(AttributeName.class);
  }

  @Override
  @Nullable
  public AttributeValue getAttributeValue() {
    return findChildByClass(AttributeValue.class);
  }

  @Override
  public String getKey() {
    return StatePsiImplUtil.getKey(this);
  }

  @Override
  public String getValue() {
    return StatePsiImplUtil.getValue(this);
  }

}
