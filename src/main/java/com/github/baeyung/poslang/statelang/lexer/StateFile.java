package com.github.baeyung.poslang.statelang.lexer;

import com.github.baeyung.poslang.statelang.StateFileType;
import com.github.baeyung.poslang.statelang.StateLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class StateFile extends PsiFileBase
{

  public StateFile(@NotNull FileViewProvider viewProvider) {
    super(viewProvider, StateLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return StateFileType.INSTANCE;
  }

  @Override
  public String toString() {
    return "State File";
  }

}