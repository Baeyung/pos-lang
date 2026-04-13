package com.github.baeyung.poslang.statelang;

import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Factory for creating structure views for StateLang files.
 * Enables the Structure View panel showing file outline with states, events, and data.
 */
public class StateStructureViewFactory implements PsiStructureViewFactory {

    @Nullable
    @Override
    public StructureViewBuilder getStructureViewBuilder(@NotNull PsiFile psiFile) {
        return new TreeBasedStructureViewBuilder() {
            @NotNull
            @Override
            public StructureViewModel createStructureViewModel(@Nullable com.intellij.openapi.editor.Editor editor) {
                return new StateStructureViewModel((StateFile) psiFile, editor);
            }

            @Override
            public boolean isRootNodeShown() {
                return false;  // Hide the root statefile node
            }
        };
    }
}
