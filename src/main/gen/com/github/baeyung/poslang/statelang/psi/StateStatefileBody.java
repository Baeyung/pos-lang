// This is a generated file. Not intended for manual editing.
package com.github.baeyung.poslang.statelang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface StateStatefileBody extends PsiElement {

  @NotNull
  List<StateDataElement> getDataElementList();

  @NotNull
  List<StateEventElement> getEventElementList();

  @NotNull
  List<StateExitElement> getExitElementList();

  @NotNull
  List<StateHtmlCommentElement> getHtmlCommentElementList();

  @NotNull
  List<StateIncludeElement> getIncludeElementList();

  @NotNull
  List<StateStateElement> getStateElementList();

}
