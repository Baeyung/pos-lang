package com.github.baeyung.poslang.statelang;

import com.github.baeyung.poslang.statelang.psi.StateDataElement;
import com.github.baeyung.poslang.statelang.psi.StateEventElement;
import com.github.baeyung.poslang.statelang.psi.StateIncludeElement;
import com.github.baeyung.poslang.statelang.psi.StateStateElement;
import com.github.baeyung.poslang.statelang.psi.StateStatefileBody;
import com.github.baeyung.poslang.statelang.psi.StateStatefileElement;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Provides error and warning annotations for StateLang files.
 * Features:
 * - Validates required attributes for elements
 * - Warns about duplicate state names
 * - Warns about missing referenced states
 * - Validates attribute values
 */
public class StateAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        switch (element)
        {
            case StateStatefileElement stateFile -> validateStatefileElement(stateFile, holder);
            case StateStateElement state -> validateStateElement(state, holder);
            case StateEventElement event -> validateEventElement(event, holder);
            case StateDataElement data -> validateDataElement(data, holder);
            case StateIncludeElement include -> validateIncludeElement(include, holder);
            default -> {}
        }
    }

    private void validateStatefileElement(StateStatefileElement element, AnnotationHolder holder) {
        String loader = StatePsiUtil.getAttributeValue(element, "loader");
        if (loader == null || loader.trim().isEmpty()) {
            holder.newAnnotation(HighlightSeverity.WARNING,
                "State file should have a 'loader' attribute")
                .range(element)
                .create();
        }
    }

    private void validateStateElement(StateStateElement state, AnnotationHolder holder) {
        String name = StatePsiUtil.getAttributeValue(state, "name");
        if (name == null || name.trim().isEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR,
                "State must have a 'name' attribute")
                .range(state)
                .create();
        }

        checkDuplicateStateName(state, holder);
    }

    private void validateEventElement(StateEventElement event, AnnotationHolder holder) {
        // Event must have a "name" attribute
        String name = StatePsiUtil.getAttributeValue(event, "name");
        if (name == null || name.trim().isEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR,
                "Event must have a 'name' attribute")
                .range(event)
                .create();
        }

        // Validate "next" attribute references valid states
        String next = StatePsiUtil.getAttributeValue(event, "next");
        if (next != null && !next.trim().isEmpty()) {
            validateStateReferences(next, event, holder);
        }
    }

    private void validateDataElement(StateDataElement data, AnnotationHolder holder) {
        // Data must have a "name" attribute
        String name = StatePsiUtil.getAttributeValue(data, "name");
        if (name == null || name.trim().isEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR,
                "Data element must have a 'name' attribute")
                .range(data)
                .create();
        }
    }

    private void validateIncludeElement(StateIncludeElement include, AnnotationHolder holder) {
        // Include must have a "file" attribute
        String file = StatePsiUtil.getAttributeValue(include, "file");
        if (file == null || file.trim().isEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR,
                "Include element must have a 'file' attribute")
                .range(include)
                .create();
        }
    }

    private void checkDuplicateStateName(StateStateElement state, AnnotationHolder holder) {
        String name = StatePsiUtil.getAttributeValue(state, "name");
        if (name == null || name.isEmpty()) return;

        // Get parent statefile
        PsiElement stateFile = state.getContainingFile().getFirstChild();
        if (!(stateFile instanceof StateStatefileElement)) return;

        StateStatefileBody body = ((StateStatefileElement) stateFile).getStatefileBody();

        int count = 0;
        for (PsiElement child : body.getChildren()) {
            if (child instanceof StateStateElement stateElement) {
                String childName = StatePsiUtil.getAttributeValue(stateElement, "name");
                if (name.equals(childName)) {
                    count++;
                }
            }
        }

        if (count > 1) {
            holder.newAnnotation(HighlightSeverity.WARNING,
                "Duplicate state name: '" + name + "'")
                .range(state)
                .create();
        }
    }

    private void validateStateReferences(String nextValue, PsiElement context, AnnotationHolder holder) {
        // Split comma-separated values
        String[] stateNames = nextValue.split(",");

        // Get all available state names
        Set<String> availableStates = new HashSet<>();
        PsiElement stateFile = context.getContainingFile().getFirstChild();
        if (stateFile instanceof StateStatefileElement) {
            StateStatefileBody body = ((StateStatefileElement) stateFile).getStatefileBody();
            for (PsiElement child : body.getChildren())
            {
                if (child instanceof StateStateElement state)
                {
                    String name = StatePsiUtil.getAttributeValue(state, "name");
                    if (name != null && !name.isEmpty())
                    {
                        availableStates.add(name);
                    }
                }
            }
        }

        // Validate each reference
        for (String stateName : stateNames) {
            String trimmed = stateName.trim();
            if (!trimmed.isEmpty() && !availableStates.contains(trimmed)) {
                holder.newAnnotation(HighlightSeverity.WARNING,
                    "Undefined state reference: '" + trimmed + "'")
                    .range(context)
                    .create();
            }
        }
    }
}
