package com.github.baeyung.poslang.statelang.reference;

import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.AttributeValue;
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

public class StateReference extends PsiPolyVariantReferenceBase<AttributeValue>
{
    private final String key;

    public StateReference(@NotNull AttributeValue element)
    {
        // Attribute value includes the quotes, so we strip them for the key
        // But TextRange for the reference should be relative to the element
        super(element, new TextRange(1, element.getTextLength() - 1));
        this.key = element
                .getText()
                .substring(1, element.getTextLength() - 1);
        System.out.println(this.key);
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode)
    {
        Project project = myElement.getProject();
        List<Attribute> properties = StateUtil.findAttributes(project, "name", key);
        List<ResolveResult> results = new ArrayList<>();
        for (Attribute property : properties)
        {
            results.add(new PsiElementResolveResult(property.getAttributeValue()));
        }
        return results.toArray(new ResolveResult[0]);
    }

    @Override
    public Object @NotNull [] getVariants()
    {
        Project project = myElement.getProject();
        List<Attribute> attributes = StateUtil.findAttributes(project, "name");
        List<LookupElement> variants = new ArrayList<>();
        for (Attribute attr : attributes)
        {
            String value = attr.getValue();
            if (value != null && !value.isEmpty())
            {
                variants.add(LookupElementBuilder.create(value));
            }
        }
        return variants.toArray();
    }
}
