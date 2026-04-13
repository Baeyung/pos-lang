package com.github.baeyung.poslang.statelang;

import com.github.baeyung.poslang.statelang.psi.*;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

/**
 * Provides intelligent code completion for StateLang files.
 * Features:
 * - Element keyword completion (statefile, state, event, data, include, exit)
 * - Context-aware attribute completion
 * - State name suggestions for "next" attributes
 * - Value suggestions based on an attribute type
 */
public class StateCompletionContributor extends CompletionContributor {

    public StateCompletionContributor() {
        // Global keyword completion
        registerKeywordCompletion();

        // State attributes
        registerStateAttributes();

        // Event attributes and smart state suggestions
        registerEventAttributes();

        // Data attributes
        registerDataAttributes();

        // Include attributes
        registerIncludeAttributes();
    }

    /**
     * Complete the main keywords (statefile, state, event, data, include, exit)
     */
    private void registerKeywordCompletion() {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(),
            new CompletionProvider<>() {
                @Override
                protected void addCompletions(
                    @NotNull CompletionParameters parameters,
                    @NotNull ProcessingContext context,
                    @NotNull CompletionResultSet resultSet
                ) {
                    // Only suggest keywords early in typing
                    String prefix = resultSet.getPrefixMatcher().getPrefix();
                    if (prefix.length() <= 1) {
                        resultSet.addElement(createKeywordLookup("statefile", "Root element for state definitions"));
                        resultSet.addElement(createKeywordLookup("state", "Define a state"));
                        resultSet.addElement(createKeywordLookup("event", "Define an event"));
                        resultSet.addElement(createKeywordLookup("data", "Define data element"));
                        resultSet.addElement(createKeywordLookup("include", "Include another state file"));
                        resultSet.addElement(createKeywordLookup("exit", "Define exit element"));
                    }
                }
            }
        );
    }

    /**
     * Complete attribute names for &lt;state&gt; elements
     */
    private void registerStateAttributes() {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement().inside(StateStateElement.class),
            new CompletionProvider<>() {
                @Override
                protected void addCompletions(
                    @NotNull CompletionParameters parameters,
                    @NotNull ProcessingContext context,
                    @NotNull CompletionResultSet resultSet
                ) {
                    resultSet.addElement(createAttributeLookup("name", "Unique state identifier (required)"));
                    resultSet.addElement(createAttributeLookup("frame", "Frame reference"));
                    resultSet.addElement(createAttributeLookup("helpRef", "Help reference"));
                    resultSet.addElement(createAttributeLookup("like", "Inheritance/template reference"));
                    resultSet.addElement(createAttributeLookup("sound", "Sound file reference"));
                    resultSet.addElement(createAttributeLookup("page", "Page reference"));
                    resultSet.addElement(createAttributeLookup("prompt", "Prompt text"));
                    resultSet.addElement(createAttributeLookup("picture", "Picture reference"));
                    resultSet.addElement(createAttributeLookup("keyboard", "Keyboard type"));
                    resultSet.addElement(createAttributeLookup("comment", "Comment/documentation"));
                }
            }
        );
    }

    /**
     * Complete attribute names and values for &lt;event&gt; elements
     */
    private void registerEventAttributes() {
        // Event attribute names
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement().inside(StateEventElement.class),
            new CompletionProvider<>() {
                @Override
                protected void addCompletions(
                    @NotNull CompletionParameters parameters,
                    @NotNull ProcessingContext context,
                    @NotNull CompletionResultSet resultSet
                ) {
                    resultSet.addElement(createAttributeLookup("name", "Event identifier (required)"));
                    resultSet.addElement(createAttributeLookup("next", "Next state (comma-separated)"));
                    resultSet.addElement(createAttributeLookup("callSubstate", "Substate call"));
                    resultSet.addElement(createAttributeLookup("permission", "Permission condition"));
                    resultSet.addElement(createAttributeLookup("permissionFail", "Failure action"));
                    resultSet.addElement(createAttributeLookup("sound", "Sound file"));
                    resultSet.addElement(createAttributeLookup("ppi", "PPI identifier"));
                    resultSet.addElement(createAttributeLookup("substateNext", "Substate next target"));
                    resultSet.addElement(createAttributeLookup("pnp", "PnP identifier"));
                    resultSet.addElement(createAttributeLookup("audit", "Audit flag"));
                    resultSet.addElement(createAttributeLookup("comment", "Comment/documentation"));
                }
            }
        );

        // Smart state name suggestions for "next" attributes
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement().inside(StateEventAttr.class),
            new CompletionProvider<>() {
                @Override
                protected void addCompletions(
                    @NotNull CompletionParameters parameters,
                    @NotNull ProcessingContext context,
                    @NotNull CompletionResultSet resultSet
                ) {
                    PsiElement currentElement = parameters.getOriginalPosition();
                    if (currentElement == null) return;

                    PsiElement attrParent = currentElement.getParent();
                    if (!(attrParent instanceof StateEventAttr)) return;

                    PsiElement attrNameToken = attrParent.getFirstChild();
                    if (attrNameToken == null ||
                        attrNameToken.getNode().getElementType() != StateTypes.NEXT_ATTR) {
                        return;
                    }

                    PsiElement rootFile = currentElement.getContainingFile().getFirstChild();
                    if (!(rootFile instanceof StateStatefileElement)) return;

                    // Parse comma-separated values
                    String fullText = currentElement.getText().replaceFirst("^\"", "");
                    String prefix = "";
                    String usedText = "";

                    int lastCommaIndex = fullText.lastIndexOf(',');
                    if (lastCommaIndex >= 0) {
                        usedText = fullText.substring(0, lastCommaIndex);
                        prefix = fullText.substring(lastCommaIndex + 1).trim();
                    } else {
                        prefix = fullText.trim();
                    }

                    resultSet = resultSet.withPrefixMatcher(prefix);

                    // Build list of already used states
                    java.util.Set<String> usedStates = new java.util.HashSet<>();
                    if (!usedText.isEmpty()) {
                        for (String part : usedText.split(",")) {
                            usedStates.add(part.trim());
                        }
                    }

                    // Get all available states
                    StateStatefileBody body = ((StateStatefileElement) rootFile).getStatefileBody();
                    for (PsiElement child : body.getChildren())
                    {
                        if (child instanceof StateStateElement state)
                        {
                            String stateName = StatePsiUtil.getAttributeValue(state, "name");
                            if (stateName != null && !stateName.isEmpty() && !usedStates.contains(stateName))
                            {
                                resultSet.addElement(LookupElementBuilder
                                                             .create(stateName)
                                                             .withTypeText("State")
                                                             .withIcon(AllIcons.Nodes.Class));
                            }
                        }
                    }
                }
            }
        );
    }

    /**
     * Complete attribute names for &lt;data&gt; elements
     */
    private void registerDataAttributes() {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement().inside(StateDataAttr.class),
            new CompletionProvider<>() {
                @Override
                protected void addCompletions(
                    @NotNull CompletionParameters parameters,
                    @NotNull ProcessingContext context,
                    @NotNull CompletionResultSet resultSet
                ) {
                    resultSet.addElement(createAttributeLookup("name", "Data identifier (required)"));
                    resultSet.addElement(createAttributeLookup("calculate", "Calculation expression"));
                    resultSet.addElement(createAttributeLookup("value", "Default value"));
                    resultSet.addElement(createAttributeLookup("comment", "Comment/documentation"));
                }
            }
        );
    }

    /**
     * Complete attribute names for &lt;include&gt; elements
     */
    private void registerIncludeAttributes() {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement().inside(StateIncludeAttr.class),
            new CompletionProvider<>() {
                @Override
                protected void addCompletions(
                    @NotNull CompletionParameters parameters,
                    @NotNull ProcessingContext context,
                    @NotNull CompletionResultSet resultSet
                ) {
                    resultSet.addElement(createAttributeLookup("file", "File path to include (required)"));
                    resultSet.addElement(createAttributeLookup("exclude", "States to exclude (comma-separated)"));
                }
            }
        );
    }

    /**
     * Helper to create a keyword lookup element with description
     */
    private LookupElementBuilder createKeywordLookup(String keyword, String description) {
        return LookupElementBuilder.create(keyword)
            .withTypeText(description, true)
            .bold()
            .withIcon(AllIcons.Nodes.Bookmark);
    }

    /**
     * Helper to create an attribute lookup element with description
     */
    private LookupElementBuilder createAttributeLookup(String attribute, String description) {
        return LookupElementBuilder.create(attribute)
            .withTypeText(description, true)
            .withIcon(AllIcons.Nodes.Console);
    }
}