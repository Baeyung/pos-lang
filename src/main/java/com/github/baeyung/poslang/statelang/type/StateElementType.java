package com.github.baeyung.poslang.statelang.type;

import com.github.baeyung.poslang.statelang.StateLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class StateElementType extends IElementType
{
  public StateElementType(@NotNull @NonNls String debugName) {
    super(debugName, StateLanguage.INSTANCE);
  }
}