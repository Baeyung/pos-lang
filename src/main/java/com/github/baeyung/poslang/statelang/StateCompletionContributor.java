package com.github.baeyung.poslang.statelang;

import com.github.baeyung.poslang.statelang.psi.StateEventAttr;
import com.github.baeyung.poslang.statelang.psi.StateEventElement;
import com.github.baeyung.poslang.statelang.psi.StateStateElement;
import com.github.baeyung.poslang.statelang.psi.StateStatefileBody;
import com.github.baeyung.poslang.statelang.psi.StateStatefileElement;
import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class StateCompletionContributor extends CompletionContributor
{

    public StateCompletionContributor()
    {

        // ==========================================
        // FEATURE 1: Complete Attribute Names for <state>
        // ==========================================
        extend(
                CompletionType.BASIC,
                PlatformPatterns
                        .psiElement()
                        .inside(StateStateElement.class),
                new CompletionProvider<>()
                {
                    @Override
                    protected void addCompletions(
                            @NotNull CompletionParameters parameters,
                            @NotNull ProcessingContext context,
                            @NotNull CompletionResultSet resultSet
                    )
                    {
                        resultSet.addElement(LookupElementBuilder.create("name"));
                        resultSet.addElement(LookupElementBuilder.create("frame"));
                        resultSet.addElement(LookupElementBuilder.create("helpRef"));
                        resultSet.addElement(LookupElementBuilder.create("like"));
                        resultSet.addElement(LookupElementBuilder.create("sound"));
                    }
                }
        );

        // ==========================================
        // FEATURE 2: Complete Attribute Names for <event>
        // ==========================================
        extend(
                CompletionType.BASIC,
                PlatformPatterns
                        .psiElement()
                        .inside(StateEventElement.class),
                new CompletionProvider<>()
                {
                    @Override
                    protected void addCompletions(
                            @NotNull CompletionParameters parameters,
                            @NotNull ProcessingContext context,
                            @NotNull CompletionResultSet resultSet
                    )
                    {
                        resultSet.addElement(LookupElementBuilder.create("name"));
                        resultSet.addElement(LookupElementBuilder.create("next"));
                        resultSet.addElement(LookupElementBuilder.create("callSubstate"));
                        resultSet.addElement(LookupElementBuilder.create("gotoSubstate"));
                        resultSet.addElement(LookupElementBuilder.create("permission"));
                        resultSet.addElement(LookupElementBuilder.create("permissionFail"));
                        resultSet.addElement(LookupElementBuilder.create("sound"));
                    }
                }
        );


        // ==========================================
        // FEATURE 3: Complete "next=" values (Comma Separated)
        // ==========================================
        // We trigger when we are inside ANY EventAttr, and then we check the tokens in Java
        extend(
                CompletionType.BASIC,
                PlatformPatterns
                        .psiElement()
                        .inside(StateEventAttr.class),
                new CompletionProvider<>()
                {
                    @Override
                    protected void addCompletions(
                            @NotNull CompletionParameters parameters,
                            @NotNull ProcessingContext context,
                            @NotNull CompletionResultSet resultSet
                    )
                    {

                        PsiElement currentElement = parameters.getOriginalPosition();
                        if (currentElement == null)
                        {
                            return;
                        }

                        // The parent of the string is the attribute (StateEventAttr)
                        PsiElement attrParent = currentElement.getParent();
                        if (!(attrParent instanceof StateEventAttr))
                        {
                            return;
                        }

                        // Check the FIRST child of this attribute. Is it the NEXT_ATTR token?
                        PsiElement attrNameToken = attrParent.getFirstChild();
                        if (attrNameToken == null ||
                            attrNameToken
                                    .getNode()
                                    .getElementType() != StateTypes.NEXT_ATTR)
                        {
                            return; // It's not a "next" attribute, so don't show state suggestions!
                        }

                        // Get root file safely
                        PsiElement rootFile = currentElement
                                .getContainingFile()
                                .getFirstChild();
                        if (!(rootFile instanceof StateStatefileElement))
                        {
                            return;
                        }

                        // 1. Get what the user typed
                        String fullText = currentElement
                                .getText()
                                .replaceFirst("^\"", "");
                        String prefix = "";
                        String usedText = "";

                        // 2. Handle comma separation
                        int lastCommaIndex = fullText.lastIndexOf(',');
                        if (lastCommaIndex >= 0)
                        {
                            usedText = fullText.substring(0, lastCommaIndex);
                            prefix = fullText
                                    .substring(lastCommaIndex + 1)
                                    .trim();
                        }
                        else
                        {
                            prefix = fullText.trim();
                        }

                        resultSet = resultSet.withPrefixMatcher(prefix);

                        // 3. Build list of already used states
                        java.util.Set<String> usedStates = new java.util.HashSet<>();
                        if (!usedText.isEmpty())
                        {
                            for (String part : usedText.split(","))
                            {
                                usedStates.add(part.trim());
                            }
                        }

                        // 4. Get all states
                        StateStatefileBody body = ((StateStatefileElement) rootFile).getStatefileBody();

                        for (PsiElement child : body.getChildren())
                        {
                            if (child instanceof StateStateElement state)
                            {
                                String stateName = StatePsiUtil.getAttributeValue(state, "name");

                                if (stateName != null && !stateName.isEmpty() && !usedStates.contains(stateName))
                                {
                                    resultSet.addElement(
                                            LookupElementBuilder
                                                    .create(stateName)
                                                    .withTypeText("State")
                                    );
                                }
                            }
                        }
                    }
                }
        );
    }
}