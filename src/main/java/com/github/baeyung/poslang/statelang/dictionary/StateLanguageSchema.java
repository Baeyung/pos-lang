package com.github.baeyung.poslang.statelang.dictionary;

import com.github.baeyung.poslang.descriptor.TagDefinition;

import java.util.Map;
import java.util.List;

public class StateLanguageSchema
{
    public static final Map<String, TagDefinition> TAGS = Map.of(
            "statefile", new TagDefinition(
                    "statefile",
                    List.of("loader"),
                    List.of("include", "exit", "data", "event", "state")
            ),
            "state", new TagDefinition(
                    "state",
                    List.of("name", "frame", "helpRef", "like", "page picture prompt", "sound"),
                    List.of("data", "event")
            ),
            "event", new TagDefinition(
                    "event",
                    List.of(
                            "name",
                            "callSubstate",
                            "gotoSubstate",
                            "next",
                            "overType",
                            "permission",
                            "permissionFail",
                            "pnp",
                            "setValue",
                            "sound",
                            "spawnSubstate",
                            "value"
                    ),
                    List.of()
            ),
            "include", new TagDefinition(
                    "include",
                    List.of("file", "include", "exclude"),
                    List.of()
            ),
            "data", new TagDefinition(
                    "data",
                    List.of("name", "calculate", "value"),
                    List.of()
            ),
            "exit", new TagDefinition(
                    "exit",
                    List.of("name", "mainState", "rootStart "),
                    List.of()
            )
    );
}