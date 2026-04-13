package com.github.baeyung.poslang.statelang;

import com.github.baeyung.poslang.statelang.psi.StateDataElement;
import com.github.baeyung.poslang.statelang.psi.StateEventElement;
import com.github.baeyung.poslang.statelang.psi.StateExitElement;
import com.github.baeyung.poslang.statelang.psi.StateIncludeElement;
import com.github.baeyung.poslang.statelang.psi.StateStateBody;
import com.github.baeyung.poslang.statelang.psi.StateStateElement;
import com.github.baeyung.poslang.statelang.psi.StateStatefileBody;
import com.github.baeyung.poslang.statelang.psi.StateStatefileElement;
import com.intellij.icons.AllIcons;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Structure view element for StateLang PSI elements.
 * Handles display and hierarchy of states, events, and data elements in the outline.
 */
public class StateStructureViewElement implements StructureViewTreeElement {

    private final PsiElement element;

    public StateStructureViewElement(PsiElement element) {
        this.element = element;
    }

    @Override
    public Object getValue() {
        return element;
    }

    @Override
    public void navigate(boolean requestFocus) {
        if (element instanceof com.intellij.psi.NavigatablePsiElement) {
            ((com.intellij.psi.NavigatablePsiElement) element).navigate(requestFocus);
        }
    }

    @Override
    public boolean canNavigate() {
        return element instanceof com.intellij.psi.NavigatablePsiElement;
    }

    @Override
    public boolean canNavigateToSource() {
        return canNavigate();
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
        return new ItemPresentation() {
            @Override
            public String getPresentableText() {
                if (element instanceof StateStatefileElement) {
                    return "[statefile]";
                } else if (element instanceof StateStateElement) {
                    String name = StatePsiUtil.getAttributeValue(element, "name");
                    return name != null ? name : "[state]";
                } else if (element instanceof StateEventElement) {
                    String name = StatePsiUtil.getAttributeValue(element, "name");
                    return name != null ? name : "[event]";
                } else if (element instanceof StateDataElement) {
                    String name = StatePsiUtil.getAttributeValue(element, "name");
                    return name != null ? name : "[data]";
                } else if (element instanceof StateIncludeElement) {
                    String file = StatePsiUtil.getAttributeValue(element, "file");
                    return file != null ? "include: " + file : "[include]";
                } else if (element instanceof StateExitElement) {
                    String name = StatePsiUtil.getAttributeValue(element, "name");
                    return name != null ? "exit: " + name : "[exit]";
                }
                return element.getClass().getSimpleName();
            }

            @Override
            public String getLocationString() {
                return null;
            }

            @Override
            public Icon getIcon(boolean unused) {
                if (element instanceof StateStatefileElement) {
                    return AllIcons.FileTypes.Xhtml;
                } else if (element instanceof StateStateElement) {
                    return AllIcons.Nodes.Class;
                } else if (element instanceof StateEventElement) {
                    return AllIcons.Nodes.Method;
                } else if (element instanceof StateDataElement) {
                    return AllIcons.Nodes.Field;
                } else if (element instanceof StateIncludeElement) {
                    return AllIcons.Nodes.Include;
                } else if (element instanceof StateExitElement) {
                    return AllIcons.Nodes.Artifact;
                }
                return null;
            }
        };
    }

    @NotNull
    @Override
    public TreeElement @NotNull [] getChildren() {
        List<TreeElement> children = new ArrayList<>();

        if (element instanceof StateStatefileElement stateFile) {
            StateStatefileBody body = stateFile.getStatefileBody();
            for (PsiElement child : body.getChildren())
            {
                if (child instanceof StateStateElement ||
                    child instanceof StateIncludeElement ||
                    child instanceof StateExitElement)
                {
                    children.add(new StateStructureViewElement(child));
                }
            }
        } else if (element instanceof StateStateElement state) {
            // Show events and data elements inside state
            StateStateBody body = state.getStateBody();
            for (PsiElement child : body.getChildren())
            {
                if (child instanceof StateEventElement ||
                    child instanceof StateDataElement ||
                    child instanceof StateIncludeElement)
                {
                    children.add(new StateStructureViewElement(child));
                }
            }
        }

        return children.toArray(new TreeElement[0]);
    }
}
