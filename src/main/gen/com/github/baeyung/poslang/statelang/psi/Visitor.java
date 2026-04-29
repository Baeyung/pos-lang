// This is a generated file. Not intended for manual editing.
package com.github.baeyung.poslang.statelang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.github.baeyung.poslang.statelang.type.StateNamedElement;

public class Visitor extends PsiElementVisitor {

  public void visitAttribute(@NotNull Attribute o) {
    visitStateNamedElement(o);
  }

  public void visitAttributeList(@NotNull AttributeList o) {
    visitPsiElement(o);
  }

  public void visitAttributeName(@NotNull AttributeName o) {
    visitPsiElement(o);
  }

  public void visitAttributeValue(@NotNull AttributeValue o) {
    visitPsiElement(o);
  }

  public void visitCommentTag(@NotNull CommentTag o) {
    visitPsiElement(o);
  }

  public void visitContent(@NotNull Content o) {
    visitPsiElement(o);
  }

  public void visitEndTag(@NotNull EndTag o) {
    visitPsiElement(o);
  }

  public void visitPairedTag(@NotNull PairedTag o) {
    visitPsiElement(o);
  }

  public void visitSelfClosingTag(@NotNull SelfClosingTag o) {
    visitPsiElement(o);
  }

  public void visitStartTag(@NotNull StartTag o) {
    visitPsiElement(o);
  }

  public void visitTag(@NotNull Tag o) {
    visitPsiElement(o);
  }

  public void visitTagBody(@NotNull TagBody o) {
    visitPsiElement(o);
  }

  public void visitTagNameEl(@NotNull TagNameEl o) {
    visitPsiElement(o);
  }

  public void visitStateNamedElement(@NotNull StateNamedElement o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
