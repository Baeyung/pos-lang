package com.github.baeyung.poslang.statelang.spec;

import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.EndTag;
import com.github.baeyung.poslang.statelang.psi.SelfClosingTag;
import com.github.baeyung.poslang.statelang.psi.StartTag;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class StateAnnotator implements Annotator
{

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {

        if (element instanceof StartTag startTag) {
            validateStartTag(startTag, holder);
        }

        if (element instanceof SelfClosingTag selfClosingTag) {
            validateSelfClosingTag(selfClosingTag, holder);
        }

        if (element instanceof EndTag endTag) {
            validateEndTag(endTag, holder);
        }
        if (element instanceof Attribute attribute) {
            validateAttribute(attribute, holder);
        }
    }

    private void validateStartTag(StartTag tag, AnnotationHolder holder) {
        String tagName = tag.getTagName();

        if (!StateLanguageSpec.ALLOWED_FULL_TAGS.contains(tagName)) {
            holder.newAnnotation(HighlightSeverity.ERROR,"Unknown Full tag: " + tagName)
                  .range(tag) // highlight TAG_NAME ideally
                  .create();
        }
    }

    private void validateSelfClosingTag(SelfClosingTag tag, AnnotationHolder holder) {
        String tagName = tag.getTagName();

        if (!StateLanguageSpec.ALLOWED_SELF_CLOSING_TAGS.contains(tagName)) {
            holder.newAnnotation(HighlightSeverity.ERROR,"Unknown Self Closing tag: " + tagName)
                  .range(tag) // highlight TAG_NAME ideally
                  .create();
        }
    }

    private void validateEndTag(EndTag endTag, AnnotationHolder holder)
    {
        String endName = endTag.getTagName();
        PsiElement prev = endTag.getPrevSibling();

        if (!StateLanguageSpec.ALLOWED_FULL_TAGS.contains(endName)) {
            holder.newAnnotation(HighlightSeverity.ERROR,"Unknown Full tag: " + endName)
                  .range(endTag.getFirstChild()) // highlight TAG_NAME ideally
                  .create();
        }

        while (prev != null)
        {
            if (prev instanceof StartTag startTag)
            {
                String startName = startTag.getTagName();

                if (!startName.equals(endName))
                {
                    holder
                            .newAnnotation(
                                    HighlightSeverity.ERROR,
                                    "Mismatched closing tag. Expected </" + startName + ">"
                            )
                            .range(endTag)
                            .create();
                }
                return;
            }
            prev = prev.getPrevSibling();
        }
    }

    private void validateAttribute(Attribute attr, AnnotationHolder holder) {

        String key = attr.getKey().toLowerCase(); // make it part of psiImplUtil
        String tagName;
        StartTag fullParentTag = PsiTreeUtil.getParentOfType(attr, StartTag.class);
        if (fullParentTag == null)
        {
            SelfClosingTag selfClosingParentTag = PsiTreeUtil.getParentOfType(attr, SelfClosingTag.class);
            if (selfClosingParentTag == null) return;
            tagName = selfClosingParentTag.getTagName();
        }
        else
        {
            tagName = fullParentTag.getTagName();
        }

        TagDefinition tagDefinition = StateLanguageSpec.TAG_ATTRIBUTES.get(tagName.toLowerCase()); // make it part of psiImplUtil
        if (tagDefinition == null) return;

        Set<String> allowedAttrs = tagDefinition.getAttributes();

        if (allowedAttrs == null || !allowedAttrs.contains(key))
        {
            holder
                    .newAnnotation(
                            HighlightSeverity.ERROR,
                            "Attribute '" + key + "' not allowed in <" + tagName + ">"
                    )
                    .range(attr.getFirstChild())
                    .create();
        }
    }
}