package com.github.baeyung.poslang.statelang.reference;

import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.AttributeValue;
import com.github.baeyung.poslang.statelang.utils.StateUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Processor;
import com.intellij.util.QueryExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public final class StateReferencesSearch implements QueryExecutor<PsiReference, ReferencesSearch.SearchParameters>
{
    @Override
    public boolean execute(
            @NotNull ReferencesSearch.SearchParameters queryParameters,
            @NotNull Processor<? super PsiReference> consumer
    )
    {
        Attribute target = getNameAttribute(queryParameters.getElementToSearch());
        if (target == null)
        {
            return true;
        }

        for (PsiFile stateFile : StateUtil.findStateFiles(target.getProject()))
        {
            Collection<AttributeValue> values = PsiTreeUtil.findChildrenOfType(stateFile, AttributeValue.class);
            for (AttributeValue value : values)
            {
                for (PsiReference reference : value.getReferences())
                {
                    if (reference.isReferenceTo(target) && !consumer.process(reference))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static Attribute getNameAttribute(PsiElement element)
    {
        Attribute attribute = element instanceof Attribute
                              ? (Attribute) element
                              : PsiTreeUtil.getParentOfType(element, Attribute.class, false);

        if (attribute != null && attribute.getName() != null)
        {
            return attribute;
        }

        return null;
    }
}