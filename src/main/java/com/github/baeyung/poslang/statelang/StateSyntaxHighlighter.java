package com.github.baeyung.poslang.statelang;

import com.github.baeyung.poslang.statelang.psi.StateTypes;
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
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
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

        if (tokenType.equals(StateTypes.STATEFILE_KEYWORD) ||
            tokenType.equals(StateTypes.STATE_KEYWORD) ||
            tokenType.equals(StateTypes.EVENT_KEYWORD) ||
            tokenType.equals(StateTypes.INCLUDE_KEYWORD) ||
            tokenType.equals(StateTypes.DATA_KEYWORD) ||
            tokenType.equals(StateTypes.EXIT_KEYWORD))
        {
            return KEYWORD_KEYS;
        }

        if (tokenType.equals(StateTypes.LOADER_ATTR) ||
            tokenType.equals(StateTypes.NAME_ATTR) ||
            tokenType.equals(StateTypes.NEXT_ATTR) ||
            tokenType.equals(StateTypes.AUDIT_ATTR) ||
            tokenType.equals(StateTypes.PAGE_ATTR) ||
            tokenType.equals(StateTypes.PROMPT_ATTR) ||
            tokenType.equals(StateTypes.PICTURE_ATTR) ||
            tokenType.equals(StateTypes.KEYBOARD_ATTR) ||
            tokenType.equals(StateTypes.PNP_ATTR) ||
            tokenType.equals(StateTypes.CALLSUBSTATE_ATTR) ||
            tokenType.equals(StateTypes.SUBSTATE_NEXT_ATTR) ||
            tokenType.equals(StateTypes.COMMENT_ATTR) ||
            tokenType.equals(StateTypes.PPI_ATTR) ||
            tokenType.equals(StateTypes.FRAME_ATTR) ||
            tokenType.equals(StateTypes.HELPREF_ATTR) ||
            tokenType.equals(StateTypes.LIKE_ATTR) ||
            tokenType.equals(StateTypes.SOUND_ATTR) ||
            tokenType.equals(StateTypes.FILE_ATTR) ||
            tokenType.equals(StateTypes.EXCLUDE_ATTR) ||
            tokenType.equals(StateTypes.CALCULATE_ATTR) ||
            tokenType.equals(StateTypes.VALUE_ATTR) ||
            tokenType.equals(StateTypes.MAINSTATE_ATTR) ||
            tokenType.equals(StateTypes.ROOTSTART_ATTR) ||
            tokenType.equals(StateTypes.PERMISSION_ATTR) ||
            tokenType.equals(StateTypes.PERMISSION_FAIL_ATTR))
        {
            return ATTRIBUTE_KEYS;
        }

        if (tokenType.equals(StateTypes.STRING))
        {
            return VALUE_KEYS;
        }

        if (tokenType.equals(StateTypes.LT) ||
            tokenType.equals(StateTypes.GT) ||
            tokenType.equals(StateTypes.LT_SLASH) ||
            tokenType.equals(StateTypes.SLASH_GT))
        {
            return BRACKET_KEYS;
        }

        if (tokenType.equals(StateTypes.EQ))
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