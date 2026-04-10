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

public class StateStatefileBodyImpl extends ASTWrapperPsiElement implements StateStatefileBody {

  public StateStatefileBodyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StateVisitor visitor) {
    visitor.visitStatefileBody(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StateVisitor) accept((StateVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<StateDataElement> getDataElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StateDataElement.class);
  }

  @Override
  @NotNull
  public List<StateEventElement> getEventElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StateEventElement.class);
  }

  @Override
  @NotNull
  public List<StateExitElement> getExitElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StateExitElement.class);
  }

  @Override
  @NotNull
  public List<StateIncludeElement> getIncludeElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StateIncludeElement.class);
  }

  @Override
  @NotNull
  public List<StateStateElement> getStateElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StateStateElement.class);
  }

}
