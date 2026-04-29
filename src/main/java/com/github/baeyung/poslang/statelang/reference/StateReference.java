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
import com.intellij.psi.PsiFile;
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
        List<ResolveResult> results = new ArrayList<>();

        PsiElement parent = myElement.getParent();
        if (parent instanceof Attribute) {
            Attribute attribute = (Attribute) parent;
            String parentKey = attribute.getKey();

            if ("file".equals(parentKey) || "loader".equals(parentKey)) {
                List<PsiFile> files = StateUtil.findFiles(project, key);
                for (PsiFile file : files) {
                    results.add(new PsiElementResolveResult(file));
                }
                return results.toArray(new ResolveResult[0]);
            } else {
                List<Attribute> properties = StateUtil.findPropertiesByNameValue(project, key);
                for (Attribute property : properties)
                {
                    results.add(new PsiElementResolveResult(property));
                }
                return results.toArray(new ResolveResult[0]);
            }
        }

        List<Attribute> properties = StateUtil.findPropertiesByNameValue(project, key);
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
        List<LookupElement> variants = new ArrayList<>();

        PsiElement parent = myElement.getParent();
        if (parent instanceof Attribute) {
            Attribute attribute = (Attribute) parent;
            String parentKey = attribute.getKey();

            if ("file".equals(parentKey) || "loader".equals(parentKey)) {
                List<PsiFile> files = StateUtil.findFiles(project);
                for (PsiFile file : files) {
                    variants.add(
                            LookupElementBuilder
                                    .create(file)
                                    .withIcon(StateFileType.INSTANCE.getIcon())
                                    .withTypeText(file.getName())
                    );
                }
                return variants.toArray();
            }
        }

        List<Attribute> properties = StateUtil.findNameAttributes(project);
        for (Attribute property : properties)
        {
            if (property.getValue() != null && !property.getValue().isEmpty())
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
