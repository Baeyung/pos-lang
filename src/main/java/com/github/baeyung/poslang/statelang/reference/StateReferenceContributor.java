package com.github.baeyung.poslang.statelang.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
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
                PlatformPatterns.psiElement(PsiLiteralExpression.class),
                new PsiReferenceProvider()
                {
                    @Override
                    public PsiReference @NotNull [] getReferencesByElement(
                            @NotNull PsiElement element,
                            @NotNull ProcessingContext context
                    )
                    {
                        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
                        String value = literalExpression.getValue() instanceof String ?
                                       (String) literalExpression.getValue() : null;
                        if (value != null)
                        {
                            TextRange property = new TextRange(
                                    1,
                                    value.length() + 1
                            );
                            return new PsiReference[]{new StateReference(element, property)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                }
        );
    }

}