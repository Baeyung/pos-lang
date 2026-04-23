package com.github.baeyung.poslang.statelang.reference;

import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.AttributeValue;
import com.github.baeyung.poslang.statelang.spec.StateLanguageSpec;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class StateReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(AttributeValue.class),
                new PsiReferenceProvider() {
                    @Override
                    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element,
                                                                           @NotNull ProcessingContext context) {
                        AttributeValue attrValue = (AttributeValue) element;
                        PsiElement parent = attrValue.getParent();
                        if (parent instanceof Attribute) {
                            Attribute attr = (Attribute) parent;
                            String key = attr.getKey();
                            if (key != null && StateLanguageSpec.REFERENCEABLE_ATTRIBUTES.contains(key)) {
                                return new PsiReference[]{new StateReference(attrValue)};
                            }
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });
    }
}
