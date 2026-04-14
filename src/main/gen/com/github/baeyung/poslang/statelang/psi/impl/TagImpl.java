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

public class TagImpl extends ASTWrapperPsiElement implements Tag {

  public TagImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull Visitor visitor) {
    visitor.visitTag(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) accept((Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<Content> getContentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, Content.class);
  }

  @Override
  @Nullable
  public EndTag getEndTag() {
    return findChildByClass(EndTag.class);
  }

  @Override
  @Nullable
  public SelfClosingTag getSelfClosingTag() {
    return findChildByClass(SelfClosingTag.class);
  }

  @Override
  @Nullable
  public StartTag getStartTag() {
    return findChildByClass(StartTag.class);
  }

}
