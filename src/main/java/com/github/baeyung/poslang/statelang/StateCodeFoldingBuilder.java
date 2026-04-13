package com.github.baeyung.poslang.statelang;

import com.github.baeyung.poslang.statelang.psi.StateTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides code folding support for StateLang files.
 * Enables users to fold/unfold multi-line elements like state definitions.
 */
public class StateCodeFoldingBuilder implements FoldingBuilder {

    @NotNull
    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull ASTNode node, @NotNull Document document) {
        List<FoldingDescriptor> descriptors = new ArrayList<>();
        collectFoldingRegions(node, descriptors);
        return descriptors.toArray(new FoldingDescriptor[0]);
    }

    private void collectFoldingRegions(ASTNode node, List<FoldingDescriptor> descriptors) {
        // Fold statefileElement blocks (entire file structure)
        if (node.getElementType() == StateTypes.STATEFILE_ELEMENT) {
            addFoldingDescriptor(node, descriptors, "...");
        }
        // Fold state blocks
        else if (node.getElementType() == StateTypes.STATE_ELEMENT) {
            addFoldingDescriptor(node, descriptors, "...");
        }
        // Fold include blocks
        else if (node.getElementType() == StateTypes.INCLUDE_ELEMENT) {
            addFoldingDescriptor(node, descriptors, "...");
        }
        // Fold exit blocks
        else if (node.getElementType() == StateTypes.EXIT_ELEMENT) {
            addFoldingDescriptor(node, descriptors, "...");
        }
        // Fold HTML comments
        else if (node.getElementType() == StateTypes.HTML_COMMENT) {
            addFoldingDescriptor(node, descriptors, "<!-- ... -->");
        }

        // Recursively process child nodes
        for (ASTNode child : node.getChildren(null)) {
            collectFoldingRegions(child, descriptors);
        }
    }

    private void addFoldingDescriptor(ASTNode node, List<FoldingDescriptor> descriptors, String placeholder) {
        TextRange range = node.getTextRange();
        // Only add folding for multi-line regions
        if (range.getLength() > 20) {
            descriptors.add(new FoldingDescriptor(node, range));
        }
    }

    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        if (node.getElementType() == StateTypes.STATEFILE_ELEMENT) {
            return "<statefile>";
        } else if (node.getElementType() == StateTypes.STATE_ELEMENT) {
            return "<state>";
        } else if (node.getElementType() == StateTypes.INCLUDE_ELEMENT) {
            return "<include>";
        } else if (node.getElementType() == StateTypes.EXIT_ELEMENT) {
            return "<exit>";
        } else if (node.getElementType() == StateTypes.HTML_COMMENT) {
            return "<!-- ... -->";
        }
        return "...";
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        // Don't collapse by default, let user decide
        return false;
    }
}
