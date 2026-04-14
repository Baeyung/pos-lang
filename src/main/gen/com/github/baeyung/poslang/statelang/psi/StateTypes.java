// This is a generated file. Not intended for manual editing.
package com.github.baeyung.poslang.statelang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.baeyung.poslang.statelang.type.StateElementType;
import com.github.baeyung.poslang.statelang.type.StateTokenType;
import com.github.baeyung.poslang.statelang.psi.impl.*;

public interface StateTypes {

  IElementType ATTRIBUTE = new StateElementType("ATTRIBUTE");
  IElementType COMMENT_TAG = new StateElementType("COMMENT_TAG");
  IElementType CONTENT = new StateElementType("CONTENT");
  IElementType ELEMENT = new StateElementType("ELEMENT");
  IElementType END_TAG = new StateElementType("END_TAG");
  IElementType SELF_CLOSING_TAG = new StateElementType("SELF_CLOSING_TAG");
  IElementType START_TAG = new StateElementType("START_TAG");
  IElementType TAG = new StateElementType("TAG");

  IElementType CB = new StateTokenType("CB");
  IElementType COMMENT = new StateTokenType("COMMENT");
  IElementType EQ = new StateTokenType("EQ");
  IElementType IDENTIFIER = new StateTokenType("IDENTIFIER");
  IElementType OB = new StateTokenType("OB");
  IElementType OB_SLASH = new StateTokenType("OB_SLASH");
  IElementType SLASH_CB = new StateTokenType("SLASH_CB");
  IElementType STRING = new StateTokenType("STRING");
  IElementType TAG_NAME = new StateTokenType("TAG_NAME");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ATTRIBUTE) {
        return new AttributeImpl(node);
      }
      else if (type == COMMENT_TAG) {
        return new CommentTagImpl(node);
      }
      else if (type == CONTENT) {
        return new ContentImpl(node);
      }
      else if (type == ELEMENT) {
        return new ElementImpl(node);
      }
      else if (type == END_TAG) {
        return new EndTagImpl(node);
      }
      else if (type == SELF_CLOSING_TAG) {
        return new SelfClosingTagImpl(node);
      }
      else if (type == START_TAG) {
        return new StartTagImpl(node);
      }
      else if (type == TAG) {
        return new TagImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
