package com.github.baeyung.poslang.statelang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.intellij.psi.TokenType;

%%

%public
%class StateLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType

/* -----------------------
   STATES
------------------------ */
%state AFTER_OB
%state INSIDE_TAG
%state AFTER_OB_SLASH
%state INSIDE_END_TAG

/* -----------------------
   PATTERNS
------------------------ */
WHITE_SPACE = [ \t\r\n]+
TAG_NAME = [a-zA-Z_][a-zA-Z0-9_-]*
IDENTIFIER = [a-zA-Z_][a-zA-Z0-9_-]*
STRING = \"([^\\\"\r\n]|\\.)*\"
COMMENT = "<!--"([^-]|("-"[^-])|("--"[^/>]))*"-->"

%%

/* =======================
   YYINITIAL (Tag Body State)
   Expects: Text, Comments, or new Tags
======================= */
<YYINITIAL> {
  {COMMENT}      { return StateTypes.COMMENT; }
  "</"           { yybegin(AFTER_OB_SLASH); return StateTypes.OB_SLASH; }
  "<"            { yybegin(AFTER_OB); return StateTypes.OB; }
  {WHITE_SPACE}  { return TokenType.WHITE_SPACE; }
  [^]            { return TokenType.BAD_CHARACTER; }
}

/* =======================
   AFTER "<"
   Expects: Tag Name
======================= */
<AFTER_OB> {
  {TAG_NAME}     { yybegin(INSIDE_TAG); return StateTypes.TAG_NAME; }
  {WHITE_SPACE}  { return TokenType.WHITE_SPACE; }
  [^]            { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }
}

/* =======================
   INSIDE START TAG
   Expects: Attributes, Equals, Strings, or closing > />
======================= */
<INSIDE_TAG> {
  {IDENTIFIER}  { return StateTypes.IDENTIFIER; }
  {STRING}      { return StateTypes.STRING; }
  "="           { return StateTypes.EQ; }
  ">"           { yybegin(YYINITIAL); return StateTypes.CB; }
  "/>"          { yybegin(YYINITIAL); return StateTypes.SLASH_CB; }
  {WHITE_SPACE} { return TokenType.WHITE_SPACE; }
  [^]           { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }
}

/* =======================
   AFTER "</"
   Expects: Tag Name
======================= */
<AFTER_OB_SLASH> {
  {TAG_NAME}     { yybegin(INSIDE_END_TAG); return StateTypes.TAG_NAME; }
  {WHITE_SPACE}  { return TokenType.WHITE_SPACE; }
  [^]            { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }
}

/* =======================
   INSIDE END TAG
   Expects: closing >
======================= */
<INSIDE_END_TAG> {
  ">"            { yybegin(YYINITIAL); return StateTypes.CB; }
  {WHITE_SPACE}  { return TokenType.WHITE_SPACE; }
  [^]            { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }
}

/* =======================
   EOF
======================= */
<<EOF>> { return null; }