package com.github.baeyung.poslang.statelang.lexer;

import com.github.baeyung.poslang.statelang.StateLanguage;
import com.github.baeyung.poslang.statelang.parser.StateParser;
import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

final class StateParserDefinition implements ParserDefinition
{
  public static final IFileElementType FILE = new IFileElementType(StateLanguage.INSTANCE);

  @NotNull
  @Override
  public Lexer createLexer(Project project) {
    return new StateLexerAdapter();
  }

  @NotNull
  @Override
  public TokenSet getCommentTokens() {
    return StateTokenSets.COMMENTS;
  }

  @NotNull
  @Override
  public TokenSet getStringLiteralElements() {
    return TokenSet.EMPTY;
  }

  @NotNull
  @Override
  public PsiParser createParser(final Project project) {
    return new StateParser();
  }

  @NotNull
  @Override
  public IFileElementType getFileNodeType() {
    return FILE;
  }

  @NotNull
  @Override
  public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
    return new StateFile(viewProvider);
  }

  @NotNull
  @Override
  public PsiElement createElement(ASTNode node) {
    return StateTypes.Factory.createElement(node);
  }
}