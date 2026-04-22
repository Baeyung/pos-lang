package com.github.baeyung.poslang.statelang.highlighting;

import com.github.baeyung.poslang.statelang.lexer.StateLexerAdapter;
import com.github.baeyung.poslang.statelang.lexer.StateTokenSets;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class StateSyntaxHighlighter extends SyntaxHighlighterBase
{

    public static final TextAttributesKey BRACKETS =
            createTextAttributesKey("STATE_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey TAG_NAME =
            createTextAttributesKey("STATE_TAG_NAME", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey IDENTIFIER =
            createTextAttributesKey("STATE_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("STATE_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("STATE_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey OPERATOR =
            createTextAttributesKey("STATE_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);

    private static final TextAttributesKey[] BRACKETS_KEYS = new TextAttributesKey[]{BRACKETS};
    private static final TextAttributesKey[] TAG_NAME_KEYS = new TextAttributesKey[]{TAG_NAME};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
    private static final TextAttributesKey[] VALUES = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer()
    {
        return new StateLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType)
    {
        if (StateTokenSets.BRACKETS.contains(tokenType))
        {
            return BRACKETS_KEYS;
        }

        if (StateTokenSets.TAG_NAME.contains(tokenType))
        {
            return TAG_NAME_KEYS;
        }

        if (StateTokenSets.IDENTIFIER.contains(tokenType))
        {
            return IDENTIFIER_KEYS;
        }

        if (StateTokenSets.VALUE.contains(tokenType))
        {
            return VALUES;
        }

        if (StateTokenSets.COMMENTS.contains(tokenType))
        {
            return COMMENT_KEYS;
        }

        if (StateTokenSets.OPERATORS.contains(tokenType))
        {
            return OPERATOR_KEYS;
        }

        return EMPTY_KEYS;
    }
}
