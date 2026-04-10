package com.github.baeyung.poslang.statelang.type;

import com.github.baeyung.poslang.statelang.StateLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class StateTokenType extends IElementType
{

    public StateTokenType(@NotNull @NonNls String debugName)
    {
        super(debugName, StateLanguage.INSTANCE);
    }

    @Override
    public String toString()
    {
        return "StateTokenType." + super.toString();
    }
}