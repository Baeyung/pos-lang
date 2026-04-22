// This is a generated file. Not intended for manual editing.
package com.github.baeyung.poslang.statelang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.github.baeyung.poslang.statelang.psi.StateTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class StateParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return file(b, l + 1);
  }

  /* ********************************************************** */
  // attributeName EQ attributeValue
  public static boolean attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ATTRIBUTE, null);
    r = attributeName(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, EQ));
    r = p && attributeValue(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // attribute+
  public static boolean attributeList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributeList")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attribute(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "attributeList", c)) break;
    }
    exit_section_(b, m, ATTRIBUTE_LIST, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean attributeName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributeName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, ATTRIBUTE_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // STRING
  public static boolean attributeValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributeValue")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, ATTRIBUTE_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // COMMENT
  public static boolean commentTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "commentTag")) return false;
    if (!nextTokenIs(b, COMMENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT);
    exit_section_(b, m, COMMENT_TAG, r);
    return r;
  }

  /* ********************************************************** */
  // tag | commentTag
  public static boolean content(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "content")) return false;
    if (!nextTokenIs(b, "<content>", COMMENT, OB)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTENT, "<content>");
    r = tag(b, l + 1);
    if (!r) r = commentTag(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // OB_SLASH tagNameEl CB
  public static boolean endTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "endTag")) return false;
    if (!nextTokenIs(b, OB_SLASH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, END_TAG, null);
    r = consumeToken(b, OB_SLASH);
    r = r && tagNameEl(b, l + 1);
    p = r; // pin = 2
    r = r && consumeToken(b, CB);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // rootElement*
  static boolean file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file")) return false;
    while (true) {
      int c = current_position_(b);
      if (!rootElement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "file", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // !(OB | OB_SLASH | COMMENT)
  static boolean file_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !file_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // OB | OB_SLASH | COMMENT
  private static boolean file_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_recover_0")) return false;
    boolean r;
    r = consumeToken(b, OB);
    if (!r) r = consumeToken(b, OB_SLASH);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  /* ********************************************************** */
  // startTag tagBody endTag
  public static boolean pairedTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "pairedTag")) return false;
    if (!nextTokenIs(b, OB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = startTag(b, l + 1);
    r = r && tagBody(b, l + 1);
    r = r && endTag(b, l + 1);
    exit_section_(b, m, PAIRED_TAG, r);
    return r;
  }

  /* ********************************************************** */
  // tag | commentTag
  static boolean rootElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rootElement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = tag(b, l + 1);
    if (!r) r = commentTag(b, l + 1);
    exit_section_(b, l, m, r, false, StateParser::file_recover);
    return r;
  }

  /* ********************************************************** */
  // OB tagNameEl attributeList? SLASH_CB
  public static boolean selfClosingTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selfClosingTag")) return false;
    if (!nextTokenIs(b, OB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OB);
    r = r && tagNameEl(b, l + 1);
    r = r && selfClosingTag_2(b, l + 1);
    r = r && consumeToken(b, SLASH_CB);
    exit_section_(b, m, SELF_CLOSING_TAG, r);
    return r;
  }

  // attributeList?
  private static boolean selfClosingTag_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selfClosingTag_2")) return false;
    attributeList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // OB tagNameEl attributeList? CB
  public static boolean startTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "startTag")) return false;
    if (!nextTokenIs(b, OB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OB);
    r = r && tagNameEl(b, l + 1);
    r = r && startTag_2(b, l + 1);
    r = r && consumeToken(b, CB);
    exit_section_(b, m, START_TAG, r);
    return r;
  }

  // attributeList?
  private static boolean startTag_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "startTag_2")) return false;
    attributeList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // pairedTag | selfClosingTag
  public static boolean tag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag")) return false;
    if (!nextTokenIs(b, OB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = pairedTag(b, l + 1);
    if (!r) r = selfClosingTag(b, l + 1);
    exit_section_(b, m, TAG, r);
    return r;
  }

  /* ********************************************************** */
  // content*
  public static boolean tagBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tagBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, TAG_BODY, "<tag body>");
    while (true) {
      int c = current_position_(b);
      if (!content(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "tagBody", c)) break;
    }
    exit_section_(b, l, m, true, false, StateParser::tagBody_recover);
    return true;
  }

  /* ********************************************************** */
  // !(OB_SLASH | OB | COMMENT)
  static boolean tagBody_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tagBody_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !tagBody_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // OB_SLASH | OB | COMMENT
  private static boolean tagBody_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tagBody_recover_0")) return false;
    boolean r;
    r = consumeToken(b, OB_SLASH);
    if (!r) r = consumeToken(b, OB);
    if (!r) r = consumeToken(b, COMMENT);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME
  public static boolean tagNameEl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tagNameEl")) return false;
    if (!nextTokenIs(b, TAG_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAG_NAME);
    exit_section_(b, m, TAG_NAME_EL, r);
    return r;
  }

}
