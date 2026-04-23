package com.github.baeyung.poslang.statelang.completion;

import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.github.baeyung.poslang.statelang.spec.StateLanguageSpec;
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
import org.jspecify.annotations.Nullable;

public class StateCompletionContributor extends CompletionContributor
{
    public StateCompletionContributor()
    {
        extend(
                CompletionType.BASIC, PlatformPatterns.psiElement(StateTypes.TAG_NAME),
                new CompletionProvider<>()
                {
                    @Override
                    protected void addCompletions(
                            @NotNull CompletionParameters parameters,
                            @NotNull ProcessingContext context,
                            @NotNull CompletionResultSet result
                    )
                    {
                        for (String tag : StateLanguageSpec.ALLOWED_FULL_TAGS)
                        {
                            result.addElement(LookupElementBuilder.create(tag));
                        }

                        for (String tag : StateLanguageSpec.ALLOWED_SELF_CLOSING_TAGS)
                        {
                            result.addElement(LookupElementBuilder.create(tag));
                        }
                    }
                }
        );

        extend(
                CompletionType.BASIC, PlatformPatterns.psiElement(StateTypes.IDENTIFIER),
                new CompletionProvider<>()
                {
                    @Override
                    protected void addCompletions(
                            @NotNull CompletionParameters parameters,
                            @NotNull ProcessingContext context,
                            @NotNull CompletionResultSet result
                    )
                    {
                        PsiElement position = parameters.getPosition();

                        String tagName = getTagName(position);

                        if (tagName != null && StateLanguageSpec.TAG_ATTRIBUTES.containsKey(tagName))
                        {
                            for (String attr : StateLanguageSpec.TAG_ATTRIBUTES.get(tagName).getAttributes())
                            {
                                result.addElement(LookupElementBuilder.create(attr));
                            }
                        }
                    }

                    private static @Nullable String getTagName(PsiElement position)
                    {
                        StartTag startTag = PsiTreeUtil.getParentOfType(position, StartTag.class);
                        SelfClosingTag selfClosingTag = PsiTreeUtil.getParentOfType(position, SelfClosingTag.class);

                        String tagName = null;
                        if (startTag != null)
                        {
                            TagNameEl tagNameEl = startTag.getTagNameEl();
                            tagName = tagNameEl.getText();
                        }
                        else if (selfClosingTag != null)
                        {
                            TagNameEl tagNameEl = selfClosingTag.getTagNameEl();
                            tagName = tagNameEl.getText();
                        }
                        return tagName;
                    }
                }
        );
    }
}
