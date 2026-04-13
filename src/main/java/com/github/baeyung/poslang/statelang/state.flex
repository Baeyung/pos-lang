package com.github.baeyung.poslang.statelang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.intellij.psi.TokenType;

%%

%class StateLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType

WHITE_SPACE=\s+
STRING=\"([^\"\\]|\\.)*\"
HTML_COMMENT="<!--"([^-]|("-"[^-])|("--"[^/>]))*"-->"

%%
{HTML_COMMENT} { return StateTypes.HTML_COMMENT; }
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
"next" { return StateTypes.NEXT_ATTR; }
"Next" { return StateTypes.NEXT_ATTR; }
"audit" { return StateTypes.AUDIT_ATTR; }
"page" { return StateTypes.PAGE_ATTR; }
"prompt" { return StateTypes.PROMPT_ATTR; }
"picture" { return StateTypes.PICTURE_ATTR; }
"keyboard" { return StateTypes.KEYBOARD_ATTR; }
"pnp" { return StateTypes.PNP_ATTR; }
"callSubstate" { return StateTypes.CALLSUBSTATE_ATTR; }
"callSubState" { return StateTypes.CALLSUBSTATE_ATTR; }
"callsubstate" { return StateTypes.CALLSUBSTATE_ATTR; }
"substateNext" { return StateTypes.SUBSTATE_NEXT_ATTR; }
"substatenext" { return StateTypes.SUBSTATE_NEXT_ATTR; }
"subStateNext" { return StateTypes.SUBSTATE_NEXT_ATTR; }
"comment" { return StateTypes.COMMENT_ATTR; }
"ppi" { return StateTypes.PPI_ATTR; }
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
"permission" { return StateTypes.PERMISSION_ATTR; }
"Permission" { return StateTypes.PERMISSION_ATTR; }
"permissionFail" { return StateTypes.PERMISSION_FAIL_ATTR; }
"permissionfail" { return StateTypes.PERMISSION_FAIL_ATTR; }

. { return TokenType.BAD_CHARACTER; }