package com.github.baeyung.poslang.statelang.spec;

import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.Content;
import com.github.baeyung.poslang.statelang.psi.EndTag;
import com.github.baeyung.poslang.statelang.psi.SelfClosingTag;
import com.github.baeyung.poslang.statelang.psi.StartTag;
import com.github.baeyung.poslang.statelang.psi.Tag;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class StateAnnotator implements Annotator
{

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder)
    {
        if (element instanceof Tag tag)
        {
            if (tag.getStartTag() != null && tag.getEndTag() != null)
            {
                if (tag.getStartTag().getTagName().equals(tag.getEndTag().getTagName()))
                {
                    validateStartTag(tag.getStartTag(), holder);
                    validateEndTag(tag.getEndTag(), holder);
                    validateContent(tag.getContentList(), holder, tag.getStartTag());
                }
                else
                {
                    holder
                            .newAnnotation(
                                    HighlightSeverity.ERROR,
                                    "Mismatched closing tag. Expected </" + tag.getStartTag().getTagName() + ">"
                            )
                            .range(tag.getEndTag())
                            .create();
                }

            }

            if (tag.getSelfClosingTag() != null)
            {
                validateSelfClosingTag(tag.getSelfClosingTag(), holder);
            }
        }

        if (element instanceof Attribute attribute)
        {
            validateAttribute(attribute, holder);
        }
    }

    private void validateStartTag(StartTag tag, AnnotationHolder holder)
    {
        String tagName = tag.getTagName();

        if (!StateLanguageSpec.ALLOWED_FULL_TAGS.contains(tagName))
        {
            holder
                    .newAnnotation(HighlightSeverity.ERROR, "Unknown Full start tag: " + tagName)
                    .range(tag) // highlight TAG_NAME ideally
                    .create();
        }
    }

    private void validateSelfClosingTag(SelfClosingTag tag, AnnotationHolder holder)
    {
        String tagName = tag.getTagName();

        if (!StateLanguageSpec.ALLOWED_SELF_CLOSING_TAGS.contains(tagName))
        {
            holder
                    .newAnnotation(HighlightSeverity.ERROR, "Unknown Self Closing tag: " + tagName)
                    .range(tag) // highlight TAG_NAME ideally
                    .create();
        }
    }

    private void validateEndTag(EndTag endTag, AnnotationHolder holder)
    {
        String endName = endTag.getTagName();

        if (!StateLanguageSpec.ALLOWED_FULL_TAGS.contains(endName))
        {
            holder
                    .newAnnotation(HighlightSeverity.ERROR, "Unknown Full closing tag: " + endName)
                    .range(endTag) // highlight TAG_NAME ideally
                    .create();
        }
    }

    private void validateAttribute(Attribute attr, AnnotationHolder holder)
    {

        String key = attr.getKey();
        String tagName;
        StartTag fullParentTag = PsiTreeUtil.getParentOfType(attr, StartTag.class);
        if (fullParentTag == null)
        {
            SelfClosingTag selfClosingParentTag = PsiTreeUtil.getParentOfType(attr, SelfClosingTag.class);
            if (selfClosingParentTag == null)
            {
                return;
            }
            tagName = selfClosingParentTag.getTagName();
        }
        else
        {
            tagName = fullParentTag.getTagName();
        }

        TagDefinition tagDefinition = StateLanguageSpec.TAG_ATTRIBUTES.get(tagName);
        if (tagDefinition == null)
        {
            return;
        }

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

    private void validateContent(List<Content> contents, AnnotationHolder holder, StartTag startTag)
    {
        if (contents.isEmpty()) {
            holder
                    .newAnnotation(
                            HighlightSeverity.WARNING,
                            "No children given for Tag: " + startTag.getTagName()
                    )
                    .range(startTag)
                    .create();
            return;
        }

        Set<String> uniqueChildrenTagNames = new HashSet<>(
                contents
                        .stream()
                        .map(content -> {
                            Tag childTag = content.getTag();

                            if (childTag != null && childTag.getSelfClosingTag() != null) {
                                return childTag.getSelfClosingTag().getTagName();
                            }

                            if (childTag != null && childTag.getStartTag() != null) {
                                return childTag.getStartTag().getTagName();
                            }

                            return null;

                        })
                        .filter(Objects::nonNull)
                        .toList()
        );

        TagDefinition tagDefinition = StateLanguageSpec.TAG_ATTRIBUTES.get(startTag.getTagName());

        if (tagDefinition == null) {
            holder
                    .newAnnotation(
                            HighlightSeverity.ERROR,
                            "No children found for Tag: " + startTag.getTagName() + " in specs"
                    )
                    .range(startTag)
                    .create();
            return;
        }

        if (!tagDefinition.getChildren().containsAll(uniqueChildrenTagNames)) {
            Set<String> unknownChildrenTagNames = new HashSet<>();

            uniqueChildrenTagNames.forEach(childTag -> {
                if (!tagDefinition.getChildren().contains(childTag)) {
                    unknownChildrenTagNames.add(childTag);
                }
            });

            contents.forEach(content -> {
                Tag childTag = content.getTag();

                if (
                        childTag != null &&
                        childTag.getSelfClosingTag() != null &&
                        unknownChildrenTagNames.contains(childTag.getSelfClosingTag().getTagName())
                )
                {
                    holder
                            .newAnnotation(
                                    HighlightSeverity.ERROR,
                                    "Unknown child " + childTag.getSelfClosingTag().getTagName() + " in " + startTag.getTagName()
                            )
                            .range(childTag)
                            .create();
                }

                if (
                        childTag != null &&
                        childTag.getStartTag() != null &&
                        unknownChildrenTagNames.contains(childTag.getStartTag().getTagName())
                )
                {
                    holder
                            .newAnnotation(
                                    HighlightSeverity.ERROR,
                                    "Unknown child " + childTag.getStartTag().getTagName() + " in " + startTag.getTagName()
                            )
                            .range(childTag)
                            .create();
                }
            });
        }
    }
}