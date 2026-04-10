package com.github.baeyung.poslang.statelang;

import com.intellij.lexer.FlexAdapter;

public class StateLexerAdapter extends FlexAdapter
{
    public StateLexerAdapter()
    {
        super(new StateLexer(null));
    }
}