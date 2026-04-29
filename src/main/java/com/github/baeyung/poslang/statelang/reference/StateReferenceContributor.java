package com.github.baeyung.poslang.statelang.reference;

import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.AttributeValue;
import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.github.baeyung.poslang.statelang.spec.StateLanguageSpec;
import com.github.baeyung.poslang.statelang.utils.StateUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceContributor;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.PsiReferenceRegistrar;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public final class StateReferenceContributor extends PsiReferenceContributor
{

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar)
    {
        registrar.registerReferenceProvider(
                PlatformPatterns
                        .psiElement(StateTypes.STRING)
                        .withParent(AttributeValue.class),
                new PsiReferenceProvider()
                {
                    @Override
                    public PsiReference @NotNull [] getReferencesByElement(
                            @NotNull PsiElement element,
                            @NotNull ProcessingContext context
                    )
                    {
                        Attribute attribute = PsiTreeUtil.getParentOfType(element, Attribute.class);
                        if (attribute == null)
                        {
                            return PsiReference.EMPTY_ARRAY;
                        }

                        String attributeName = attribute.getKey();
                        String tagName = StateUtil.getContainingTagName(attribute);
                        if (!StateLanguageSpec.isStateFileReference(tagName, attributeName) &&
                            !StateLanguageSpec.isNameReferenceAttribute(attributeName))
                        {
                            return PsiReference.EMPTY_ARRAY;
                        }

                        TextRange valueRange = getStringValueRange(element);
                        if (valueRange == null)
                        {
                            return PsiReference.EMPTY_ARRAY;
                        }

                        return new PsiReference[]{new StateReference(element, valueRange)};
                    }
                }
        );
    }

    private static TextRange getStringValueRange(PsiElement element)
    {
        String text = element.getText();
        int startOffset = text.startsWith("\"") ? 1 : 0;
        int endOffset = text.endsWith("\"") && text.length() > startOffset ? text.length() - 1 : text.length();

        if (endOffset <= startOffset)
        {
            return null;
        }

        return new TextRange(startOffset, endOffset);
    }
}
