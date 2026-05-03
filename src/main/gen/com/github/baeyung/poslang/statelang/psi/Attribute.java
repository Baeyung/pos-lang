// This is a generated file. Not intended for manual editing.
package com.github.baeyung.poslang.statelang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.github.baeyung.poslang.statelang.type.StateNamedElement;

public interface Attribute extends StateNamedElement {

  @NotNull
  AttributeName getAttributeName();

  @Nullable
  AttributeValue getAttributeValue();

  String getKey();

  String getValue();

  PsiElement getNameIdentifier();

  String getName();

  PsiElement setName(String newName);

}
