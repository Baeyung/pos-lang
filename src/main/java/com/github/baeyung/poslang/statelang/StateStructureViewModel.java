package com.github.baeyung.poslang.statelang;

import com.github.baeyung.poslang.statelang.psi.StateDataElement;
import com.github.baeyung.poslang.statelang.psi.StateExitElement;
import com.github.baeyung.poslang.statelang.psi.StateIncludeElement;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;

/**
 * Structure view model for StateLang files.
 * Displays hierarchical outline of states, events, and data elements.
 */
public class StateStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider {

    public StateStructureViewModel(@NotNull StateFile file, Editor editor) {
        super(file, editor, new StateStructureViewElement(file));
    }

    @NotNull
    @Override
    public Sorter @NotNull [] getSorters() {
        return new Sorter[]{Sorter.ALPHA_SORTER};
    }

    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return element.getValue() instanceof StateDataElement ||
               element.getValue() instanceof StateIncludeElement ||
               element.getValue() instanceof StateExitElement;
    }
}
