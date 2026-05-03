package com.github.baeyung.poslang.statelang.usage;

import com.github.baeyung.poslang.statelang.lexer.StateLexerAdapter;
import com.github.baeyung.poslang.statelang.lexer.StateTokenSets;
import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.utils.StateUtil;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class StateFindUsagesProvider implements FindUsagesProvider
{

    @Override
    public WordsScanner getWordsScanner()
    {
        return new DefaultWordsScanner(
                new StateLexerAdapter(),
                StateTokenSets.VALUE,
                StateTokenSets.COMMENTS,
                TokenSet.EMPTY
        );
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement)
    {
        return psiElement instanceof Attribute attribute && attribute.getName() != null;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement)
    {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element)
    {
        if (element instanceof Attribute attribute && attribute.getName() != null)
        {
            String tagName = StateUtil.getContainingTagName(attribute);
            if (tagName != null)
            {
                return tagName + " name";
            }

            return "name attribute";
        }
        return "";
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element)
    {
        if (element instanceof Attribute attribute && attribute.getName() != null)
        {
            return attribute.getName();
        }
        return "";
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName)
    {
        if (element instanceof Attribute attribute && attribute.getName() != null)
        {
            String tagName = StateUtil.getContainingTagName(attribute);
            if (tagName != null)
            {
                return "<" + tagName + " name=\"" + attribute.getName() + "\">";
            }

            return "name=\"" + attribute.getName() + "\"";
        }
        return "";
    }

}
