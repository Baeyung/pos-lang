package com.github.baeyung.poslang.statelang.reference;

import com.github.baeyung.poslang.statelang.StateFileType;
import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.utils.StateUtil;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiPolyVariantReferenceBase;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

final class StateReference extends PsiPolyVariantReferenceBase<PsiElement>
{

    private final String key;

    StateReference(@NotNull PsiElement element, TextRange textRange)
    {
        super(element, textRange);
        key = element
                .getText()
                .substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode)
    {
        Project project = myElement.getProject();
        List<Attribute> properties = StateUtil.findProperties(project, key);
        List<ResolveResult> results = new ArrayList<>();
        for (Attribute property : properties)
        {
            results.add(new PsiElementResolveResult(property));
        }
        return results.toArray(new ResolveResult[0]);
    }

    @Override
    public Object @NotNull [] getVariants()
    {
        Project project = myElement.getProject();
        List<Attribute> properties = StateUtil.findProperties(project);
        List<LookupElement> variants = new ArrayList<>();
        for (Attribute property : properties)
        {
            if (property.getKey() != null && !property.getKey().isEmpty())
            {
                variants.add(
                        LookupElementBuilder
                                .create(property)
                                .withIcon(StateFileType.INSTANCE.getIcon())
                                .withTypeText(property.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}