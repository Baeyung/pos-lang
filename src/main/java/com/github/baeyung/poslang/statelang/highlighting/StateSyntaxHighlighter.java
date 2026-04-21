package com.github.baeyung.poslang.statelang.highlighting;

import com.github.baeyung.poslang.statelang.lexer.StateLexerAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class StateSyntaxHighlighter extends SyntaxHighlighterBase {

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
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new StateLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
//        if (tokenType.equals(StateTypes.OB) || tokenType.equals(StateTypes.OB_SLASH) ||
//            tokenType.equals(StateTypes.CB) || tokenType.equals(StateTypes.SLASH_CB)) {
//            return BRACKETS_KEYS;
//        }
//        if (tokenType.equals(StateTypes.TAG_NAME)) {
//            return TAG_NAME_KEYS;
//        }
//        if (tokenType.equals(StateTypes.IDENTIFIER)) {
//            return IDENTIFIER_KEYS;
//        }
//        if (tokenType.equals(StateTypes.STRING)) {
//            return STRING_KEYS;
//        }
//        if (tokenType.equals(StateTypes.COMMENT)) {
//            return COMMENT_KEYS;
//        }
//        if (tokenType.equals(StateTypes.EQ)) {
//            return OPERATOR_KEYS;
//        }
        return EMPTY_KEYS;
    }
}
