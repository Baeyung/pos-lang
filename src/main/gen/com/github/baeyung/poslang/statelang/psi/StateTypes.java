// This is a generated file. Not intended for manual editing.
package com.github.baeyung.poslang.statelang.psi;

import com.github.baeyung.poslang.statelang.type.StateElementType;
import com.github.baeyung.poslang.statelang.type.StateTokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.baeyung.poslang.statelang.psi.impl.*;

public interface StateTypes {

  IElementType DATA_ATTR = new StateElementType("DATA_ATTR");
  IElementType DATA_ATTRS = new StateElementType("DATA_ATTRS");
  IElementType DATA_ELEMENT = new StateElementType("DATA_ELEMENT");
  IElementType EVENT_ATTR = new StateElementType("EVENT_ATTR");
  IElementType EVENT_ATTRS = new StateElementType("EVENT_ATTRS");
  IElementType EVENT_ELEMENT = new StateElementType("EVENT_ELEMENT");
  IElementType EXIT_ATTRS = new StateElementType("EXIT_ATTRS");
  IElementType EXIT_ELEMENT = new StateElementType("EXIT_ELEMENT");
  IElementType INCLUDE_ATTR = new StateElementType("INCLUDE_ATTR");
  IElementType INCLUDE_ATTRS = new StateElementType("INCLUDE_ATTRS");
  IElementType INCLUDE_ELEMENT = new StateElementType("INCLUDE_ELEMENT");
  IElementType LOADER_ATTR = new StateElementType("LOADER_ATTR");
  IElementType STATEFILE_ATTRS = new StateElementType("STATEFILE_ATTRS");
  IElementType STATEFILE_BODY = new StateElementType("STATEFILE_BODY");
  IElementType STATEFILE_ELEMENT = new StateElementType("STATEFILE_ELEMENT");
  IElementType STATE_ATTR = new StateElementType("STATE_ATTR");
  IElementType STATE_ATTRS = new StateElementType("STATE_ATTRS");
  IElementType STATE_BODY = new StateElementType("STATE_BODY");
  IElementType STATE_ELEMENT = new StateElementType("STATE_ELEMENT");

  IElementType CALCULATE_ATTR = new StateTokenType("CALCULATE_ATTR");
  IElementType CALLSUBSTATE_ATTR = new StateTokenType("CALLSUBSTATE_ATTR");
  IElementType DATA_KEYWORD = new StateTokenType("DATA_KEYWORD");
  IElementType EQ = new StateTokenType("EQ");
  IElementType EVENT_KEYWORD = new StateTokenType("EVENT_KEYWORD");
  IElementType EXCLUDE_ATTR = new StateTokenType("EXCLUDE_ATTR");
  IElementType EXITATTR = new StateTokenType("exitAttr");
  IElementType EXIT_KEYWORD = new StateTokenType("EXIT_KEYWORD");
  IElementType FILE_ATTR = new StateTokenType("FILE_ATTR");
  IElementType FRAME_ATTR = new StateTokenType("FRAME_ATTR");
  IElementType GOTSUBSTATE_ATTR = new StateTokenType("GOTSUBSTATE_ATTR");
  IElementType GT = new StateTokenType("GT");
  IElementType HELPREF_ATTR = new StateTokenType("HELPREF_ATTR");
  IElementType INCLUDE_KEYWORD = new StateTokenType("INCLUDE_KEYWORD");
  IElementType LIKE_ATTR = new StateTokenType("LIKE_ATTR");
  IElementType LT = new StateTokenType("LT");
  IElementType LT_SLASH = new StateTokenType("LT_SLASH");
  IElementType NAME_ATTR = new StateTokenType("NAME_ATTR");
  IElementType NEXT_ATTR = new StateTokenType("NEXT_ATTR");
  IElementType SLASH_GT = new StateTokenType("SLASH_GT");
  IElementType SOUND_ATTR = new StateTokenType("SOUND_ATTR");
  IElementType STATEFILE_KEYWORD = new StateTokenType("STATEFILE_KEYWORD");
  IElementType STATE_KEYWORD = new StateTokenType("STATE_KEYWORD");
  IElementType STRING = new StateTokenType("STRING");
  IElementType VALUE_ATTR = new StateTokenType("VALUE_ATTR");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == DATA_ATTR) {
        return new StateDataAttrImpl(node);
      }
      else if (type == DATA_ATTRS) {
        return new StateDataAttrsImpl(node);
      }
      else if (type == DATA_ELEMENT) {
        return new StateDataElementImpl(node);
      }
      else if (type == EVENT_ATTR) {
        return new StateEventAttrImpl(node);
      }
      else if (type == EVENT_ATTRS) {
        return new StateEventAttrsImpl(node);
      }
      else if (type == EVENT_ELEMENT) {
        return new StateEventElementImpl(node);
      }
      else if (type == EXIT_ATTRS) {
        return new StateExitAttrsImpl(node);
      }
      else if (type == EXIT_ELEMENT) {
        return new StateExitElementImpl(node);
      }
      else if (type == INCLUDE_ATTR) {
        return new StateIncludeAttrImpl(node);
      }
      else if (type == INCLUDE_ATTRS) {
        return new StateIncludeAttrsImpl(node);
      }
      else if (type == INCLUDE_ELEMENT) {
        return new StateIncludeElementImpl(node);
      }
      else if (type == LOADER_ATTR) {
        return new StateLoaderAttrImpl(node);
      }
      else if (type == STATEFILE_ATTRS) {
        return new StateStatefileAttrsImpl(node);
      }
      else if (type == STATEFILE_BODY) {
        return new StateStatefileBodyImpl(node);
      }
      else if (type == STATEFILE_ELEMENT) {
        return new StateStatefileElementImpl(node);
      }
      else if (type == STATE_ATTR) {
        return new StateStateAttrImpl(node);
      }
      else if (type == STATE_ATTRS) {
        return new StateStateAttrsImpl(node);
      }
      else if (type == STATE_BODY) {
        return new StateStateBodyImpl(node);
      }
      else if (type == STATE_ELEMENT) {
        return new StateStateElementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
