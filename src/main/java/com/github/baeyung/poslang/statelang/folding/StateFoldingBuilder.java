package com.github.baeyung.poslang.statelang.folding;

import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.AttributeList;
import com.github.baeyung.poslang.statelang.psi.PairedTag;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.github.baeyung.poslang.statelang.spec.StateLanguageSpec.getNameAttr;

public final class StateFoldingBuilder extends FoldingBuilderEx
{
    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(
            @NotNull PsiElement root,
            @NotNull Document document,
            boolean quick
    )
    {
        List<FoldingDescriptor> descriptors = new ArrayList<>();
        Collection<PairedTag> pairedTags = PsiTreeUtil.findChildrenOfType(root, PairedTag.class);
        for (PairedTag pairedTag : pairedTags)
        {
            TextRange textRange = pairedTag.getTextRange();
            if (spansMultipleLines(textRange, document))
            {
                descriptors.add(new FoldingDescriptor(pairedTag.getNode(), textRange));
            }
        }

        return descriptors.toArray(new FoldingDescriptor[0]);
    }

    @Override
    public @NonNull String getPlaceholderText(@NotNull ASTNode node)
    {
        PsiElement element = node.getPsi();
        if (element instanceof PairedTag pairedTag)
        {
            String tagName = pairedTag.getStartTagName();
            if (tagName != null && !tagName.isEmpty())
            {
                AttributeList tagAttrs = pairedTag.getStartTag().getAttributeList();
                if  (tagAttrs != null)
                {
                    Optional<Attribute> filteredAttr = tagAttrs
                            .getAttributeList()
                            .stream()
                            .filter(attr -> attr.getKey().equals(getNameAttr()))
                            .findFirst();

                    if (filteredAttr.isPresent())
                    {
                        String nameAttrValue = filteredAttr.get().getValue();
                        return "<" + tagName + " name=\"" + nameAttrValue + "\" " + ">...</" + tagName + ">";
                    }
                }
                return "<" + tagName + ">...</" + tagName + ">";
            }
        }

        return "<...>...</...>";
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node)
    {
        return false;
    }

    private static boolean spansMultipleLines(TextRange textRange, Document document)
    {
        int startLine = document.getLineNumber(textRange.getStartOffset());
        int endLine = document.getLineNumber(textRange.getEndOffset());

        return startLine < endLine;
    }
}
