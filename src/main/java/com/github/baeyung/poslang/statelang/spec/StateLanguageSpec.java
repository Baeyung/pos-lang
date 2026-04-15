package com.github.baeyung.poslang.statelang.spec;

import java.util.Map;
import java.util.Set;

public class StateLanguageSpec
{
    // region tags
    private static String STATE_FILE_TAG = "include";
    private static String INCLUDE_TAG = "include";
    private static String EXIT_TAG = "exit";
    private static String STATE_TAG = "state";
    private static String DATA_TAG = "data";
    private static String EVENT_TAG = "event";

    private static final Set<String> ALLOWED_TAGS = Set.of(
            STATE_FILE_TAG,
            INCLUDE_TAG,
            EXIT_TAG,
            STATE_TAG,
            DATA_TAG,
            EVENT_TAG
    );
    // endregion

    // region attributes
    private static String LOADER_ATTR = "loader";
    private static String NAME_ATTR = "name";
    private static String INCLUDE_ATTR = "include";
    private static String EXCLUDE_ATTR = "exclude";
    private static String NEXT_ATTR = "next";
    private static String AUDIT_ATTR = "audit";
    private static String PAGE_ATTR = "page";
    private static String PROMPT_ATTR = "prompt";
    private static String PICTURE_ATTR = "picture";
    private static String KEYBOARD_ATTR = "keyboard";
    private static String PNP_ATTR = "pnp";
    private static String CALLSUBSTATE_ATTR = "callSubstate";
    private static String SUBSTATE_NEXT_ATTR = "substateNext";
    private static String COMMENT_ATTR = "comment";
    private static String PPI_ATTR = "ppi";
    private static String FRAME_ATTR = "frame";
    private static String VALUE_ATTR = "value";
    private static String CALCULATE_ATTR = "calculate";
    private static String MAINSTATE_ATTR = "mainState";
    private static String ROOTSTART_ATTR = "rootStart";
    private static String PERMISSION_ATTR = "permission";
    private static String PERMISSION_FAIL_ATTR = "permissionFail";
    private static String HELPREF_ATTR = "helpRef";
    private static String LIKE_ATTR = "like";
    private static String SOUND_ATTR = "sound";
    private static String GOTO_SUBSTATE_ATTR = "gotoSubstate";

    public static final Map<String, TagDefinition> TAG_ATTRIBUTES = Map.of(
            STATE_FILE_TAG, new TagDefinition(
                    Set.of(LOADER_ATTR),
                    Set.of(
                            INCLUDE_TAG,
                            EXIT_TAG,
                            STATE_TAG,
                            DATA_TAG,
                            EVENT_TAG
                    )
            ),
            INCLUDE_TAG, new TagDefinition(
                    Set.of(INCLUDE_ATTR, EXCLUDE_ATTR),
                    Set.of()
            ),
            EXIT_TAG, new TagDefinition(
                    Set.of(NAME_ATTR, ROOTSTART_ATTR, MAINSTATE_ATTR),
                    Set.of()
            ),
            DATA_TAG, new TagDefinition(
                    Set.of(NAME_ATTR, CALCULATE_ATTR, VALUE_ATTR, COMMENT_ATTR),
                    Set.of()
            ),
            STATE_TAG, new TagDefinition(
                    Set.of(
                            NAME_ATTR,
                            FRAME_ATTR,
                            HELPREF_ATTR,
                            LIKE_ATTR,
                            SOUND_ATTR,
                            PAGE_ATTR,
                            PROMPT_ATTR,
                            PICTURE_ATTR,
                            KEYBOARD_ATTR,
                            COMMENT_ATTR
                    ),
                    Set.of(
                            DATA_TAG,
                            EVENT_TAG,
                            INCLUDE_TAG
                    )
            ),
            EVENT_TAG, new TagDefinition(
                    Set.of(
                            NAME_ATTR,
                            NEXT_ATTR,
                            CALLSUBSTATE_ATTR,
                            GOTO_SUBSTATE_ATTR,
                            PERMISSION_ATTR,
                            PERMISSION_FAIL_ATTR,
                            PPI_ATTR,
                            SUBSTATE_NEXT_ATTR,
                            PNP_ATTR,
                            AUDIT_ATTR,
                            COMMENT_ATTR
                    ),
                    Set.of()
            )
    );
    // endregion
}
