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
    return statefileFile(b, l + 1);
  }

  /* ********************************************************** */
  // COMMENT_ATTR EQ STRING
  public static boolean commentAttribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "commentAttribute")) return false;
    if (!nextTokenIs(b, COMMENT_ATTR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMENT_ATTR, EQ, STRING);
    exit_section_(b, m, COMMENT_ATTRIBUTE, r);
    return r;
  }

  /* ********************************************************** */
  // NAME_ATTR EQ STRING
  //            | CALCULATE_ATTR EQ STRING
  //            | VALUE_ATTR EQ STRING
  //            | commentAttribute
  public static boolean dataAttr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataAttr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DATA_ATTR, "<data attr>");
    r = parseTokens(b, 0, NAME_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, CALCULATE_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, VALUE_ATTR, EQ, STRING);
    if (!r) r = commentAttribute(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // dataAttr*
  public static boolean dataAttrs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataAttrs")) return false;
    Marker m = enter_section_(b, l, _NONE_, DATA_ATTRS, "<data attrs>");
    while (true) {
      int c = current_position_(b);
      if (!dataAttr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dataAttrs", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // LT DATA_KEYWORD dataAttrs SLASH_GT
  public static boolean dataElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dataElement")) return false;
    if (!nextTokenIs(b, LT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LT, DATA_KEYWORD);
    r = r && dataAttrs(b, l + 1);
    r = r && consumeToken(b, SLASH_GT);
    exit_section_(b, m, DATA_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // NAME_ATTR EQ STRING
  //             | NEXT_ATTR EQ STRING
  //             | CALLSUBSTATE_ATTR EQ STRING
  //             | GOTSUBSTATE_ATTR EQ STRING
  //             | PERMISSION_ATTR EQ STRING
  //             | PERMISSION_FAIL_ATTR EQ STRING
  //             | PPI_ATTR EQ STRING
  //             | SUBSTATE_NEXT_ATTR EQ STRING
  //             | PNP_ATTR EQ STRING
  //             | AUDIT_ATTR EQ STRING
  //             | commentAttribute
  public static boolean eventAttr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "eventAttr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EVENT_ATTR, "<event attr>");
    r = parseTokens(b, 0, NAME_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, NEXT_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, CALLSUBSTATE_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, GOTSUBSTATE_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, PERMISSION_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, PERMISSION_FAIL_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, PPI_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, SUBSTATE_NEXT_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, PNP_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, AUDIT_ATTR, EQ, STRING);
    if (!r) r = commentAttribute(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // eventAttr*
  public static boolean eventAttrs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "eventAttrs")) return false;
    Marker m = enter_section_(b, l, _NONE_, EVENT_ATTRS, "<event attrs>");
    while (true) {
      int c = current_position_(b);
      if (!eventAttr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "eventAttrs", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // LT EVENT_KEYWORD eventAttrs SLASH_GT
  public static boolean eventElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "eventElement")) return false;
    if (!nextTokenIs(b, LT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LT, EVENT_KEYWORD);
    r = r && eventAttrs(b, l + 1);
    r = r && consumeToken(b, SLASH_GT);
    exit_section_(b, m, EVENT_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // NAME_ATTR EQ STRING
  //            | MAINSTATE_ATTR EQ STRING
  //            | ROOTSTART_ATTR EQ STRING
  public static boolean exitAttr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exitAttr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXIT_ATTR, "<exit attr>");
    r = parseTokens(b, 0, NAME_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, MAINSTATE_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, ROOTSTART_ATTR, EQ, STRING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // exitAttr*
  public static boolean exitAttrs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exitAttrs")) return false;
    Marker m = enter_section_(b, l, _NONE_, EXIT_ATTRS, "<exit attrs>");
    while (true) {
      int c = current_position_(b);
      if (!exitAttr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "exitAttrs", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // LT EXIT_KEYWORD exitAttrs SLASH_GT
  public static boolean exitElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exitElement")) return false;
    if (!nextTokenIs(b, LT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LT, EXIT_KEYWORD);
    r = r && exitAttrs(b, l + 1);
    r = r && consumeToken(b, SLASH_GT);
    exit_section_(b, m, EXIT_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // FILE_ATTR EQ STRING
  //               | INCLUDE_KEYWORD EQ STRING
  //               | EXCLUDE_ATTR EQ STRING
  public static boolean includeAttr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "includeAttr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INCLUDE_ATTR, "<include attr>");
    r = parseTokens(b, 0, FILE_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, INCLUDE_KEYWORD, EQ, STRING);
    if (!r) r = parseTokens(b, 0, EXCLUDE_ATTR, EQ, STRING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // includeAttr*
  public static boolean includeAttrs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "includeAttrs")) return false;
    Marker m = enter_section_(b, l, _NONE_, INCLUDE_ATTRS, "<include attrs>");
    while (true) {
      int c = current_position_(b);
      if (!includeAttr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "includeAttrs", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // LT INCLUDE_KEYWORD includeAttrs SLASH_GT
  public static boolean includeElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "includeElement")) return false;
    if (!nextTokenIs(b, LT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LT, INCLUDE_KEYWORD);
    r = r && includeAttrs(b, l + 1);
    r = r && consumeToken(b, SLASH_GT);
    exit_section_(b, m, INCLUDE_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // LOADER_ATTR EQ STRING
  public static boolean loaderAttribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "loaderAttribute")) return false;
    if (!nextTokenIs(b, LOADER_ATTR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LOADER_ATTR, EQ, STRING);
    exit_section_(b, m, LOADER_ATTRIBUTE, r);
    return r;
  }

  /* ********************************************************** */
  // NAME_ATTR EQ STRING
  //             | FRAME_ATTR EQ STRING
  //             | HELPREF_ATTR EQ STRING
  //             | LIKE_ATTR EQ STRING
  //             | SOUND_ATTR EQ STRING
  //             | PAGE_ATTR EQ STRING
  //             | PROMPT_ATTR EQ STRING
  //             | PICTURE_ATTR EQ STRING
  //             | KEYBOARD_ATTR EQ STRING
  //             | commentAttribute
  public static boolean stateAttr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stateAttr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATE_ATTR, "<state attr>");
    r = parseTokens(b, 0, NAME_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, FRAME_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, HELPREF_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, LIKE_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, SOUND_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, PAGE_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, PROMPT_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, PICTURE_ATTR, EQ, STRING);
    if (!r) r = parseTokens(b, 0, KEYBOARD_ATTR, EQ, STRING);
    if (!r) r = commentAttribute(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // stateAttr*
  public static boolean stateAttrs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stateAttrs")) return false;
    Marker m = enter_section_(b, l, _NONE_, STATE_ATTRS, "<state attrs>");
    while (true) {
      int c = current_position_(b);
      if (!stateAttr(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "stateAttrs", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // (dataElement | eventElement | includeElement)*
  public static boolean stateBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stateBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, STATE_BODY, "<state body>");
    while (true) {
      int c = current_position_(b);
      if (!stateBody_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "stateBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // dataElement | eventElement | includeElement
  private static boolean stateBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stateBody_0")) return false;
    boolean r;
    r = dataElement(b, l + 1);
    if (!r) r = eventElement(b, l + 1);
    if (!r) r = includeElement(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LT STATE_KEYWORD stateAttrs GT stateBody LT_SLASH STATE_KEYWORD GT
  public static boolean stateElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stateElement")) return false;
    if (!nextTokenIs(b, LT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LT, STATE_KEYWORD);
    r = r && stateAttrs(b, l + 1);
    r = r && consumeToken(b, GT);
    r = r && stateBody(b, l + 1);
    r = r && consumeTokens(b, 0, LT_SLASH, STATE_KEYWORD, GT);
    exit_section_(b, m, STATE_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // loaderAttribute*
  public static boolean statefileAttrs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statefileAttrs")) return false;
    Marker m = enter_section_(b, l, _NONE_, STATEFILE_ATTRS, "<statefile attrs>");
    while (true) {
      int c = current_position_(b);
      if (!loaderAttribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "statefileAttrs", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // (includeElement | exitElement | stateElement | dataElement | eventElement)*
  public static boolean statefileBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statefileBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, STATEFILE_BODY, "<statefile body>");
    while (true) {
      int c = current_position_(b);
      if (!statefileBody_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "statefileBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // includeElement | exitElement | stateElement | dataElement | eventElement
  private static boolean statefileBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statefileBody_0")) return false;
    boolean r;
    r = includeElement(b, l + 1);
    if (!r) r = exitElement(b, l + 1);
    if (!r) r = stateElement(b, l + 1);
    if (!r) r = dataElement(b, l + 1);
    if (!r) r = eventElement(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LT STATEFILE_KEYWORD statefileAttrs GT statefileBody LT_SLASH STATEFILE_KEYWORD GT
  public static boolean statefileElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statefileElement")) return false;
    if (!nextTokenIs(b, LT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LT, STATEFILE_KEYWORD);
    r = r && statefileAttrs(b, l + 1);
    r = r && consumeToken(b, GT);
    r = r && statefileBody(b, l + 1);
    r = r && consumeTokens(b, 0, LT_SLASH, STATEFILE_KEYWORD, GT);
    exit_section_(b, m, STATEFILE_ELEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // statefileElement
  static boolean statefileFile(PsiBuilder b, int l) {
    return statefileElement(b, l + 1);
  }

}
