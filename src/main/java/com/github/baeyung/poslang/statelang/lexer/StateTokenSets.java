package com.github.baeyung.poslang.statelang.lexer;

import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.intellij.psi.tree.TokenSet;

public interface StateTokenSets {

    // -------------------------
    // IDENTIFIERS (attributes + keys)
    // -------------------------
    TokenSet IDENTIFIERS = TokenSet.create(
            StateTypes.IDENTIFIER,
            StateTypes.TAG_NAME
    );

    // -------------------------
    // COMMENTS
    // -------------------------
    TokenSet COMMENTS = TokenSet.create(
            StateTypes.COMMENT
    );

    // -------------------------
    // STRINGS
    // -------------------------
    TokenSet STRINGS = TokenSet.create(
            StateTypes.STRING
    );

    // -------------------------
    // BRACKETS / STRUCTURE
    // -------------------------
    TokenSet BRACKETS = TokenSet.create(
            StateTypes.OB,
            StateTypes.CB,
            StateTypes.OB_SLASH,
            StateTypes.SLASH_CB
    );

    // -------------------------
    // ATTRIBUTES OPERATORS
    // -------------------------
    TokenSet OPERATORS = TokenSet.create(
            StateTypes.EQ
    );
}