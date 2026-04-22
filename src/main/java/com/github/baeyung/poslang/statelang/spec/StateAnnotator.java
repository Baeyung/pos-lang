package com.github.baeyung.poslang.statelang.spec;

import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.AttributeList;
import com.github.baeyung.poslang.statelang.psi.Content;
import com.github.baeyung.poslang.statelang.psi.PairedTag;
import com.github.baeyung.poslang.statelang.psi.SelfClosingTag;
import com.github.baeyung.poslang.statelang.psi.Tag;
import com.github.baeyung.poslang.statelang.psi.TagBody;
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
            SelfClosingTag selfClosingTag = tag.getSelfClosingTag();

            if (pairedTag != null)
            {
                if (pairedTag
                        .getStartTagName()
                        .equals(pairedTag.getEndTagName()))
                {
                    validatePairedTags(pairedTag, holder);
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
            else if (selfClosingTag != null)
            {
                validateSelfClosingTags(selfClosingTag, holder);
            }
        }
    }

    private void validatePairedTags(PairedTag pairedTag, AnnotationHolder holder)
    {
        String tagName = pairedTag.getStartTagName();
        if (!StateLanguageSpec.ALLOWED_FULL_TAGS.contains(tagName))
        {
            holder
                    .newAnnotation(
                            HighlightSeverity.ERROR,
                            "Invalid paired tag: <" + tagName + ">"
                    )
                    .range(pairedTag
                                   .getStartTag()
                                   .getTagNameEl())
                    .create();
        }

        AttributeList attributeList = pairedTag
                .getStartTag()
                .getAttributeList();
        if (attributeList != null)
        {
            validateAttributes(tagName, attributeList, holder);
        }

        validateChildElements(tagName, pairedTag.getTagBody(), holder);
    }

    private void validateSelfClosingTags(SelfClosingTag selfClosingTag, AnnotationHolder holder)
    {
        String tagName = selfClosingTag.getTagName();
        if (!StateLanguageSpec.ALLOWED_SELF_CLOSING_TAGS.contains(tagName))
        {
            holder
                    .newAnnotation(
                            HighlightSeverity.ERROR,
                            "Invalid self-closing tag: <" + tagName + "/>"
                    )
                    .range(selfClosingTag.getTagNameEl())
                    .create();
        }

        AttributeList attributeList = selfClosingTag.getAttributeList();
        if (attributeList != null)
        {
            validateAttributes(tagName, attributeList, holder);
        }
    }

    private void validateAttributes(String tagName, AttributeList attributeList, AnnotationHolder holder)
    {
        TagDefinition tagDefinition = StateLanguageSpec.TAG_ATTRIBUTES.get(tagName);
        if (tagDefinition != null)
        {
            for (Attribute attribute : attributeList.getAttributeList())
            {
                String key = attribute.getKey();
                if (!tagDefinition.getAttributes().contains(key))
                {
                    holder
                            .newAnnotation(
                                    HighlightSeverity.ERROR,
                                    "Invalid attribute '" + key + "' for tag <" + tagName + ">"
                            )
                            .range(attribute.getAttributeName())
                            .create();
                }
            }
        }
    }

    private void validateChildElements(String tagName, TagBody tagBody, AnnotationHolder holder)
    {
        TagDefinition tagDefinition = StateLanguageSpec.TAG_ATTRIBUTES.get(tagName);
        if (tagDefinition != null)
        {
            for (Content content : tagBody.getContentList())
            {
                Tag childTag = content.getTag();
                if (childTag != null)
                {
                    String childTagName = childTag.getPairedTag() != null ?
                                            childTag.getPairedTag().getStartTagName() :
                                            childTag.getSelfClosingTag() != null ?
                                                childTag.getSelfClosingTag().getTagName() :
                                                null;

                    if (childTagName != null && !tagDefinition.getChildren().contains(childTagName))
                    {
                        holder
                                .newAnnotation(
                                        HighlightSeverity.ERROR,
                                        "Invalid child tag <" + childTagName + "> for tag <" + tagName + ">"
                                )
                                .range(childTag)
                                .create();
                    }
                }
            }
        }
    }
}
