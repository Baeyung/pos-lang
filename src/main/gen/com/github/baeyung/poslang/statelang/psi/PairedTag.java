// This is a generated file. Not intended for manual editing.
package com.github.baeyung.poslang.statelang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface PairedTag extends PsiElement {

  @NotNull
  EndTag getEndTag();

  @NotNull
  StartTag getStartTag();

  @NotNull
  TagBody getTagBody();

  //WARNING: getTagName(...) is skipped
  //matching getTagName(PairedTag, ...)
  //methods are not found in StatePsiImplUtil

}
