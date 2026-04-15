package com.github.baeyung.poslang.statelang.spec;

import java.util.HashSet;
import java.util.Set;

public class TagDefinition
{
    Set<String> attributes = new HashSet<>();
    Set<String> children = new HashSet<>();

    public TagDefinition() {}

    public TagDefinition(final Set<String> attributes, final Set<String> children)
    {
        this.attributes = attributes;
        this.children = children;
    }

    public Set<String> getAttributes()
    {
        return attributes;
    }

    public Set<String> getChildren()
    {
        return children;
    }
}
