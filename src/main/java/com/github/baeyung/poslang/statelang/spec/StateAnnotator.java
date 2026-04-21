package com.github.baeyung.poslang.statelang.spec;

import com.github.baeyung.poslang.statelang.psi.PairedTag;
import com.github.baeyung.poslang.statelang.psi.Tag;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class StateAnnotator implements Annotator
{

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder)
    {
        if (element instanceof Tag tag)
        {
            PairedTag pairedTag = tag.getPairedTag();

            if (pairedTag != null)
            {
                if (pairedTag.getStartTagName().equals(pairedTag.getEndTagName()))
                {
                    // validate paired tags
                }
                // paired tags dont match
                else
                {
                    holder
                            .newAnnotation(
                                    HighlightSeverity.ERROR,
                                    "Mismatched closing tag. Expected </" + pairedTag.getStartTagName() + ">"
                            )
                            .range(pairedTag.getEndTag())
                            .create();
                }
            }
        }
    }
}