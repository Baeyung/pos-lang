package com.github.baeyung.poslang.statelang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.psi.SimpleTypes;
import com.intellij.psi.TokenType;

%%

%class StatefileLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType

WHITE_SPACE=\s+
IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
STRING=\"([^\"\\]|\\.)*\"

%%

{WHITE_SPACE} { return TokenType.WHITE_SPACE; }

"</" { return StatefileTypes.LT_SLASH; }
"<"  { return StatefileTypes.LT; }
"/>" { return StatefileTypes.SLASH_GT; }
">"  { return StatefileTypes.GT; }

"=" { return StatefileTypes.EQ; }

{STRING} { return StatefileTypes.STRING; }

"statefile" { return StatefileTypes.STATEFILE_KEYWORD; }
"state"     { return StatefileTypes.STATE_KEYWORD; }
"event"     { return StatefileTypes.EVENT_KEYWORD; }
"include"   { return StatefileTypes.INCLUDE_KEYWORD; }
"data"      { return StatefileTypes.DATA_KEYWORD; }
"exit"      { return StatefileTypes.EXIT_KEYWORD; }

"loader" { return StatefileTypes.LOADER_ATTR; }
"name" { return StatefileTypes.NAME_ATTR; }
"frame" { return StatefileTypes.FRAME_ATTR; }
"helpRef" { return StatefileTypes.HELPREF_ATTR; }
"like" { return StatefileTypes.LIKE_ATTR; }
"sound" { return StatefileTypes.SOUND_ATTR; }
"file" { return StatefileTypes.FILE_ATTR; }
"exclude" { return StatefileTypes.EXCLUDE_ATTR; }
"calculate" { return StatefileTypes.CALCULATE_ATTR; }
"value" { return StatefileTypes.VALUE_ATTR; }
"mainState" { return StatefileTypes.MAINSTATE_ATTR; }
"rootStart" { return StatefileTypes.ROOTSTART_ATTR; }

{IDENTIFIER} { return StatefileTypes.IDENTIFIER; }

. { return TokenType.BAD_CHARACTER; }