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

%state AFTER_OB
%state AFTER_OB_SLASH
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
   DEFAULT STATE
======================= */
<YYINITIAL> {
  /* ---- COMMENTS ---- */
  {COMMENT} { return StateTypes.COMMENT; }
  /* ---- STRUCTURAL TOKENS ---- */
  "</" { yybegin(AFTER_OB_SLASH); return StateTypes.OB_SLASH; }
  "<"  { yybegin(AFTER_OB); return StateTypes.OB; }
  "/>" { return StateTypes.SLASH_CB; }
  ">"  { return StateTypes.CB; }
  "="  { return StateTypes.EQ; }
  /* ---- DATA TOKENS ---- */
  {STRING} { return StateTypes.STRING; }
  {IDENTIFIER} { return StateTypes.IDENTIFIER; }
  {WHITE_SPACE} { return TokenType.WHITE_SPACE; }
  /* ---- FALLBACK (CRITICAL) ---- */
  [^] { return TokenType.BAD_CHARACTER; }
}
/* =======================
   AFTER "<"
   EXPECT TAG_NAME
======================= */
<AFTER_OB> {
  {TAG_NAME} { yybegin(YYINITIAL); return StateTypes.TAG_NAME; }
  {WHITE_SPACE} { return TokenType.WHITE_SPACE; }
  /* ---- FALLBACK (CRITICAL) ---- */
  [^] { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }
}
/* =======================
   AFTER "</"
   EXPECT TAG_NAME
======================= */
<AFTER_OB_SLASH> {
  {TAG_NAME} { yybegin(YYINITIAL); return StateTypes.TAG_NAME; }
  {WHITE_SPACE} { return TokenType.WHITE_SPACE; }
  /* ---- FALLBACK (CRITICAL) ---- */
  [^] { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }
}
/* =======================
   EOF
======================= */
<<EOF>> { return null; }