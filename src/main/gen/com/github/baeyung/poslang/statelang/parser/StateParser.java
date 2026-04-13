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
  // IDENTIFIER EQ STRING
  public static boolean attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, EQ, STRING);
    exit_section_(b, m, ATTRIBUTE, r);
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
  // tag | commentTag
  public static boolean element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "element")) return false;
    if (!nextTokenIs(b, "<element>", COMMENT, OB)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ELEMENT, "<element>");
    r = tag(b, l + 1);
    if (!r) r = commentTag(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // OB_SLASH TAG_NAME CB
  public static boolean endTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "endTag")) return false;
    if (!nextTokenIs(b, OB_SLASH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OB_SLASH, TAG_NAME, CB);
    exit_section_(b, m, END_TAG, r);
    return r;
  }

  /* ********************************************************** */
  // element*
  static boolean file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file")) return false;
    while (true) {
      int c = current_position_(b);
      if (!element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "file", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // OB TAG_NAME attribute* SLASH_CB
  public static boolean selfClosingTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selfClosingTag")) return false;
    if (!nextTokenIs(b, OB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OB, TAG_NAME);
    r = r && selfClosingTag_2(b, l + 1);
    r = r && consumeToken(b, SLASH_CB);
    exit_section_(b, m, SELF_CLOSING_TAG, r);
    return r;
  }

  // attribute*
  private static boolean selfClosingTag_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "selfClosingTag_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "selfClosingTag_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // OB TAG_NAME attribute* CB
  public static boolean startTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "startTag")) return false;
    if (!nextTokenIs(b, OB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OB, TAG_NAME);
    r = r && startTag_2(b, l + 1);
    r = r && consumeToken(b, CB);
    exit_section_(b, m, START_TAG, r);
    return r;
  }

  // attribute*
  private static boolean startTag_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "startTag_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "startTag_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // startTag content* endTag | selfClosingTag
  public static boolean tag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag")) return false;
    if (!nextTokenIs(b, OB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tag_0(b, l + 1);
    if (!r) r = selfClosingTag(b, l + 1);
    exit_section_(b, m, TAG, r);
    return r;
  }

  // startTag content* endTag
  private static boolean tag_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = startTag(b, l + 1);
    r = r && tag_0_1(b, l + 1);
    r = r && endTag(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // content*
  private static boolean tag_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!content(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "tag_0_1", c)) break;
    }
    return true;
  }

}
