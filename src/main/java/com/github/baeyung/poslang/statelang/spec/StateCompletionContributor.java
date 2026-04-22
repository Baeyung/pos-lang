package com.github.baeyung.poslang.statelang.spec;

import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.github.baeyung.poslang.statelang.psi.StartTag;
import com.github.baeyung.poslang.statelang.psi.SelfClosingTag;
import com.github.baeyung.poslang.statelang.psi.TagNameEl;

public class StateCompletionContributor extends CompletionContributor {
    public StateCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(StateTypes.TAG_NAME),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                        for (String tag : StateLanguageSpec.ALLOWED_FULL_TAGS) {
                            result.addElement(LookupElementBuilder.create(tag));
                        }
                        for (String tag : StateLanguageSpec.ALLOWED_SELF_CLOSING_TAGS) {
                            result.addElement(LookupElementBuilder.create(tag));
                        }
                    }
                });

        extend(CompletionType.BASIC, PlatformPatterns.psiElement(StateTypes.IDENTIFIER),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                        PsiElement position = parameters.getPosition();

                        StartTag startTag = PsiTreeUtil.getParentOfType(position, StartTag.class);
                        SelfClosingTag selfClosingTag = PsiTreeUtil.getParentOfType(position, SelfClosingTag.class);

                        String tagName = null;
                        if (startTag != null) {
                            TagNameEl tagNameEl = startTag.getTagNameEl();
                            if (tagNameEl != null) tagName = tagNameEl.getText();
                        } else if (selfClosingTag != null) {
                            TagNameEl tagNameEl = selfClosingTag.getTagNameEl();
                            if (tagNameEl != null) tagName = tagNameEl.getText();
                        }

                        if (tagName != null && StateLanguageSpec.TAG_ATTRIBUTES.containsKey(tagName)) {
                            for (String attr : StateLanguageSpec.TAG_ATTRIBUTES.get(tagName).getAttributes()) {
                                result.addElement(LookupElementBuilder.create(attr));
                            }
                        }
                    }
                });
    }
}
