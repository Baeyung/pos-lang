package com.github.baeyung.poslang.statelang;

import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.intellij.psi.tree.TokenSet;

public interface StateTokenSets
{
    TokenSet ATTRIBUTES = TokenSet.create(
            StateTypes.NAME_ATTR,
            StateTypes.FRAME_ATTR,
            StateTypes.HELPREF_ATTR,
            StateTypes.LIKE_ATTR,
            StateTypes.SOUND_ATTR,
            StateTypes.FILE_ATTR,
            StateTypes.EXCLUDE_ATTR,
            StateTypes.CALCULATE_ATTR,
            StateTypes.VALUE_ATTR,
            StateTypes.MAINSTATE_ATTR,
            StateTypes.ROOTSTART_ATTR,
            StateTypes.NEXT_ATTR,
            StateTypes.CALLSUBSTATE_ATTR,
            StateTypes.GOTSUBSTATE_ATTR,
            StateTypes.LOADER_ATTR
    );

    TokenSet KEYWORDS = TokenSet.create(
            StateTypes.STATEFILE_KEYWORD,
            StateTypes.STATE_KEYWORD,
            StateTypes.DATA_KEYWORD,
            StateTypes.EVENT_KEYWORD,
            StateTypes.INCLUDE_KEYWORD,
            StateTypes.EXIT_KEYWORD
    );

    TokenSet BRACKETS = TokenSet.create(
            StateTypes.LT,
            StateTypes.GT,
            StateTypes.LT_SLASH,
            StateTypes.SLASH_GT
    );

    TokenSet STRINGS = TokenSet.create(
            StateTypes.STRING
    );

    TokenSet COMMENTS = TokenSet.create(

    );
}