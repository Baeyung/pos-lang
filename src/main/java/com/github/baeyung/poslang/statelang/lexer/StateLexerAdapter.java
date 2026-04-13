package com.github.baeyung.poslang.statelang.lexer;

import com.intellij.lexer.FlexAdapter;

public class StateLexerAdapter extends FlexAdapter
{
  public StateLexerAdapter() {
    super(new StateLexer(null));
  }
}