package com.github.baeyung.poslang.statelang.reference;

import com.github.baeyung.poslang.statelang.psi.AttributeValue;
import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceContributor;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.PsiReferenceRegistrar;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

final class StateReferenceContributor extends PsiReferenceContributor
{

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar)
    {
        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(AttributeValue.class),
                new PsiReferenceProvider()
                {
                    @Override
                    public PsiReference @NotNull [] getReferencesByElement(
                            @NotNull PsiElement element,
                            @NotNull ProcessingContext context
                    )
                    {
                        AttributeValue attributeValue = (AttributeValue) element;
                        String text = attributeValue.getText();
                        // text includes quotes, like "someValue"
                        if (text != null && text.length() >= 2)
                        {
                            TextRange property = new TextRange(
                                    1,
                                    text.length() - 1
                            );
                            return new PsiReference[]{new StateReference(element, property)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                }
        );
    }

}