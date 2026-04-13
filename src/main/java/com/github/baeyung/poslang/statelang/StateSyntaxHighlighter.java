package com.github.baeyung.poslang.statelang;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class StateSyntaxHighlighter extends SyntaxHighlighterBase
{

    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("STATE_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);

    public static final TextAttributesKey ATTRIBUTE =
            createTextAttributesKey("STATE_ATTRIBUTE", DefaultLanguageHighlighterColors.INSTANCE_FIELD);

    public static final TextAttributesKey VALUE =
            createTextAttributesKey("STATE_VALUE", DefaultLanguageHighlighterColors.STRING);

    public static final TextAttributesKey BRACKET =
            createTextAttributesKey("STATE_BRACKET", DefaultLanguageHighlighterColors.BRACKETS);

    public static final TextAttributesKey OPERATOR =
            createTextAttributesKey("STATE_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);

    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("STATE_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    public static final TextAttributesKey BAD_CHAR =
            createTextAttributesKey("STATE_BAD_CHAR", HighlighterColors.BAD_CHARACTER);


    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] ATTRIBUTE_KEYS = new TextAttributesKey[]{ATTRIBUTE};
    private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
    private static final TextAttributesKey[] BRACKET_KEYS = new TextAttributesKey[]{BRACKET};
    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHAR};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];


    @NotNull
    @Override
    public Lexer getHighlightingLexer()
    {
        return new StateLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType)
    {

        if (StateTokenSets.KEYWORDS.contains(tokenType))
        {
            return KEYWORD_KEYS;
        }

        if (StateTokenSets.ATTRIBUTES.contains(tokenType))
        {
            return ATTRIBUTE_KEYS;
        }

        if (StateTokenSets.STRINGS.contains(tokenType))
        {
            return VALUE_KEYS;
        }

        if (StateTokenSets.BRACKETS.contains(tokenType))
        {
            return BRACKET_KEYS;
        }

        if (StateTokenSets.OPERATORS.contains(tokenType))
        {
            return OPERATOR_KEYS;
        }

        if (tokenType.equals(TokenType.BAD_CHARACTER))
        {
            return BAD_CHAR_KEYS;
        }

        return EMPTY_KEYS;
    }
}