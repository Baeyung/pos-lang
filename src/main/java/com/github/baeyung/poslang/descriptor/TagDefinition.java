package com.github.baeyung.poslang.descriptor;

import java.util.List;

public class TagDefinition {

    private final String name;
    private final List<String> attributes;
    private final List<String> children;

    public TagDefinition(String name, List<String> attributes, List<String> children) {
        this.name = name;
        this.attributes = attributes;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public List<String> getChildren() {
        return children;
    }
}