package com.github.baeyung.poslang.statelang.dictionary;

import com.github.baeyung.poslang.descriptor.ElementDescriptor;
import com.github.baeyung.poslang.descriptor.TagDefinition;
import com.intellij.psi.impl.source.xml.XmlElementDescriptorProvider;
import com.intellij.psi.xml.XmlTag;
import com.intellij.xml.XmlElementDescriptor;
import org.jetbrains.annotations.Nullable;

public class StateTagDescriptorProvider implements XmlElementDescriptorProvider
{

    @Override
    public @Nullable XmlElementDescriptor getDescriptor(XmlTag tag) {

        TagDefinition def = StateLanguageSchema.TAGS.get(tag.getName());

        if (def != null) {
            return new ElementDescriptor(def);
        }

        return null;
    }
}