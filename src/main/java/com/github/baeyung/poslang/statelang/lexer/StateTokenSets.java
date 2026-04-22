package com.github.baeyung.poslang.statelang.lexer;

import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.TokenSet;

public interface StateTokenSets {

    // -------------------------
    // WHITESPACE
    // -------------------------
    TokenSet WHITESPACE = TokenSet.create(TokenType.WHITE_SPACE);

    // -------------------------
    // IDENTIFIERS (Tag names + Attribute keys)
    // -------------------------
    TokenSet IDENTIFIER = TokenSet.create(
            StateTypes.IDENTIFIER
    );

    TokenSet TAG_NAME = TokenSet.create(
            StateTypes.TAG_NAME
    );

    // -------------------------
    // COMMENTS
    // -------------------------
    TokenSet COMMENTS = TokenSet.create(
            StateTypes.COMMENT
    );

    // -------------------------
    // LITERALS (Strings)
    // -------------------------
    TokenSet VALUE = TokenSet.create(
            StateTypes.STRING
    );

    // -------------------------
    // BRACKETS / DELIMITERS (Angle brackets)
    // -------------------------
    TokenSet BRACKETS = TokenSet.create(
            StateTypes.OB,        // <
            StateTypes.CB,        // >
            StateTypes.OB_SLASH,  // </
            StateTypes.SLASH_CB   // />
    );

    // -------------------------
    // OPERATORS (Attribute assignment)
    // -------------------------
    TokenSet OPERATORS = TokenSet.create(
            StateTypes.EQ         // =
    );
}