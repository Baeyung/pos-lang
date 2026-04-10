package com.github.baeyung.poslang.statelang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.intellij.psi.TokenType;

%%

%class StatefileLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType

WHITE_SPACE=\s+
STRING=\"([^\"\\]|\\.)*\"

%%

{WHITE_SPACE} { return TokenType.WHITE_SPACE; }

"</" { return StateTypes.LT_SLASH; }
"<"  { return StateTypes.LT; }
"/>" { return StateTypes.SLASH_GT; }
">"  { return StateTypes.GT; }

"=" { return StateTypes.EQ; }

{STRING} { return StateTypes.STRING; }

"statefile" { return StateTypes.STATEFILE_KEYWORD; }
"state"     { return StateTypes.STATE_KEYWORD; }
"event"     { return StateTypes.EVENT_KEYWORD; }
"include"   { return StateTypes.INCLUDE_KEYWORD; }
"data"      { return StateTypes.DATA_KEYWORD; }
"exit"      { return StateTypes.EXIT_KEYWORD; }

"loader" { return StateTypes.LOADER_ATTR; }
"name" { return StateTypes.NAME_ATTR; }
"frame" { return StateTypes.FRAME_ATTR; }
"helpRef" { return StateTypes.HELPREF_ATTR; }
"like" { return StateTypes.LIKE_ATTR; }
"sound" { return StateTypes.SOUND_ATTR; }
"file" { return StateTypes.FILE_ATTR; }
"exclude" { return StateTypes.EXCLUDE_ATTR; }
"calculate" { return StateTypes.CALCULATE_ATTR; }
"value" { return StateTypes.VALUE_ATTR; }
"mainState" { return StateTypes.MAINSTATE_ATTR; }
"rootStart" { return StateTypes.ROOTSTART_ATTR; }

. { return TokenType.BAD_CHARACTER; }