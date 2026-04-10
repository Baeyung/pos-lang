// This is a generated file. Not intended for manual editing.
package generated;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.baeyung.poslang.statelang.psi.impl.*;

public interface GeneratedTypes {

  IElementType DATA_ATTR = new IElementType("DATA_ATTR", null);
  IElementType DATA_ATTRS = new IElementType("DATA_ATTRS", null);
  IElementType DATA_ELEMENT = new IElementType("DATA_ELEMENT", null);
  IElementType EVENT_ATTR = new IElementType("EVENT_ATTR", null);
  IElementType EVENT_ATTRS = new IElementType("EVENT_ATTRS", null);
  IElementType EVENT_ELEMENT = new IElementType("EVENT_ELEMENT", null);
  IElementType EXIT_ATTRS = new IElementType("EXIT_ATTRS", null);
  IElementType EXIT_ELEMENT = new IElementType("EXIT_ELEMENT", null);
  IElementType INCLUDE_ATTR = new IElementType("INCLUDE_ATTR", null);
  IElementType INCLUDE_ATTRS = new IElementType("INCLUDE_ATTRS", null);
  IElementType INCLUDE_ELEMENT = new IElementType("INCLUDE_ELEMENT", null);
  IElementType LOADER_ATTR = new IElementType("LOADER_ATTR", null);
  IElementType STATEFILE_ATTRS = new IElementType("STATEFILE_ATTRS", null);
  IElementType STATEFILE_BODY = new IElementType("STATEFILE_BODY", null);
  IElementType STATEFILE_ELEMENT = new IElementType("STATEFILE_ELEMENT", null);
  IElementType STATE_ATTR = new IElementType("STATE_ATTR", null);
  IElementType STATE_ATTRS = new IElementType("STATE_ATTRS", null);
  IElementType STATE_BODY = new IElementType("STATE_BODY", null);
  IElementType STATE_ELEMENT = new IElementType("STATE_ELEMENT", null);

  IElementType CALCULATE_ATTR = new IElementType("CALCULATE_ATTR", null);
  IElementType CALLSUBSTATE_ATTR = new IElementType("CALLSUBSTATE_ATTR", null);
  IElementType DATA_KEYWORD = new IElementType("DATA_KEYWORD", null);
  IElementType EQ = new IElementType("EQ", null);
  IElementType EVENT_KEYWORD = new IElementType("EVENT_KEYWORD", null);
  IElementType EXCLUDE_ATTR = new IElementType("EXCLUDE_ATTR", null);
  IElementType EXITATTR = new IElementType("exitAttr", null);
  IElementType EXIT_KEYWORD = new IElementType("EXIT_KEYWORD", null);
  IElementType FILE_ATTR = new IElementType("FILE_ATTR", null);
  IElementType FRAME_ATTR = new IElementType("FRAME_ATTR", null);
  IElementType GOTSUBSTATE_ATTR = new IElementType("GOTSUBSTATE_ATTR", null);
  IElementType GT = new IElementType("GT", null);
  IElementType HELPREF_ATTR = new IElementType("HELPREF_ATTR", null);
  IElementType INCLUDE_KEYWORD = new IElementType("INCLUDE_KEYWORD", null);
  IElementType LIKE_ATTR = new IElementType("LIKE_ATTR", null);
  IElementType LT = new IElementType("LT", null);
  IElementType LT_SLASH = new IElementType("LT_SLASH", null);
  IElementType NAME_ATTR = new IElementType("NAME_ATTR", null);
  IElementType NEXT_ATTR = new IElementType("NEXT_ATTR", null);
  IElementType SLASH_GT = new IElementType("SLASH_GT", null);
  IElementType SOUND_ATTR = new IElementType("SOUND_ATTR", null);
  IElementType STATEFILE_KEYWORD = new IElementType("STATEFILE_KEYWORD", null);
  IElementType STATE_KEYWORD = new IElementType("STATE_KEYWORD", null);
  IElementType STRING = new IElementType("STRING", null);
  IElementType VALUE_ATTR = new IElementType("VALUE_ATTR", null);

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
