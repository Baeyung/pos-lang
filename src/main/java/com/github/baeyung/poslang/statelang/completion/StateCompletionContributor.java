package com.github.baeyung.poslang.statelang.completion;

import com.github.baeyung.poslang.statelang.spec.StateLanguageSpec;
import com.github.baeyung.poslang.statelang.spec.TagDefinition;
import com.github.baeyung.poslang.statelang.type.StateTokenType;
import com.github.baeyung.poslang.statelang.type.StateElementType;
import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class StateCompletionContributor extends CompletionContributor {

    public StateCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(StateTypes.IDENTIFIER),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                                  @NotNull ProcessingContext context,
                                                  @NotNull CompletionResultSet resultSet) {

                        String tagName = StateContextUtil.getEnclosingTagNameForAttribute(parameters.getPosition());
                        if (tagName != null) {
                            TagDefinition tagDef = StateLanguageSpec.TAG_ATTRIBUTES.get(tagName);
                            if (tagDef != null) {
                                Set<String> attributes = tagDef.getAttributes();
                                if (attributes != null) {
                                    for (String attr : attributes) {
                                        resultSet.addElement(LookupElementBuilder.create(attr));
                                    }
                                }
                            }
                        }
                    }
                }
        );

        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(StateTypes.TAG_NAME),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                                  @NotNull ProcessingContext context,
                                                  @NotNull CompletionResultSet resultSet) {

                        if (StateContextUtil.isInsideEndTag(parameters.getPosition())) {
                            String matchingStartTag = StateContextUtil.getMatchingStartTagName(parameters.getPosition());
                            if (matchingStartTag != null) {
                                resultSet.addElement(LookupElementBuilder.create(matchingStartTag));
                            }
                            return;
                        }

                        String parentTagName = StateContextUtil.getParentTagName(parameters.getPosition());
                        if (parentTagName != null) {
                            TagDefinition tagDef = StateLanguageSpec.TAG_ATTRIBUTES.get(parentTagName);
                            if (tagDef != null) {
                                Set<String> children = tagDef.getChildren();
                                if (children != null) {
                                    for (String child : children) {
                                        resultSet.addElement(LookupElementBuilder.create(child));
                                    }
                                }
                            }
                        } else {
                            // Root tags
                            for (String tag : StateLanguageSpec.ALLOWED_FULL_TAGS) {
                                resultSet.addElement(LookupElementBuilder.create(tag));
                            }
                            for (String tag : StateLanguageSpec.ALLOWED_SELF_CLOSING_TAGS) {
                                resultSet.addElement(LookupElementBuilder.create(tag));
                            }
                        }
                    }
                }
        );
    }
}
