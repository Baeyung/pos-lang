package com.github.baeyung.poslang.statelang.reference;

import com.github.baeyung.poslang.statelang.StateFileType;
import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.spec.StateLanguageSpec;
import com.github.baeyung.poslang.statelang.utils.StateUtil;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.PsiPolyVariantReferenceBase;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class StateReference extends PsiPolyVariantReferenceBase<PsiElement>
{

    private final String value;

    public StateReference(@NotNull PsiElement element, TextRange textRange)
    {
        super(element, textRange);
        value = element
                .getText()
                .substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode)
    {
        Project project = myElement.getProject();
        List<ResolveResult> results = new ArrayList<>();

        Attribute attribute = PsiTreeUtil.getParentOfType(myElement, Attribute.class);
        if (attribute == null)
        {
            return ResolveResult.EMPTY_ARRAY;
        }

        String attributeName = attribute.getKey();
        String tagName = StateUtil.getContainingTagName(attribute);

        if (StateLanguageSpec.isStateFileReference(tagName, attributeName))
        {
            for (PsiFile stateFile : StateUtil.findStateFiles(project, myElement, value))
            {
                results.add(new PsiElementResolveResult(stateFile));
            }
        }
        else if (StateLanguageSpec.isNameReferenceAttribute(attributeName))
        {
            for (Attribute nameAttribute : StateUtil.findNameAttributes(project, value))
            {
                results.add(new PsiElementResolveResult(nameAttribute));
            }
        }

        return results.toArray(new ResolveResult[0]);
    }

    @Override
    public Object @NotNull [] getVariants()
    {
        Project project = myElement.getProject();
        Attribute attribute = PsiTreeUtil.getParentOfType(myElement, Attribute.class);
        if (attribute == null)
        {
            return new Object[0];
        }

        List<LookupElement> variants = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        String attributeName = attribute.getKey();
        String tagName = StateUtil.getContainingTagName(attribute);

        if (StateLanguageSpec.isStateFileReference(tagName, attributeName))
        {
            for (PsiFile stateFile : StateUtil.findStateFiles(project))
            {
                String fileName = stateFile.getName();
                if (seen.add(fileName))
                {
                    variants.add(
                            LookupElementBuilder
                                    .create(fileName)
                                    .withIcon(StateFileType.INSTANCE.getIcon())
                    );
                }
            }
        }
        else if (StateLanguageSpec.isNameReferenceAttribute(attributeName))
        {
            for (Attribute nameAttribute : StateUtil.findNameAttributes(project))
            {
                String name = nameAttribute.getName();
                if (name != null && !name.isEmpty() && seen.add(name))
                {
                    variants.add(
                            LookupElementBuilder
                                    .create(name)
                                    .withIcon(StateFileType.INSTANCE.getIcon())
                                    .withTypeText(nameAttribute.getContainingFile().getName())
                    );
                }
            }
        }

        return variants.toArray();
    }
}
