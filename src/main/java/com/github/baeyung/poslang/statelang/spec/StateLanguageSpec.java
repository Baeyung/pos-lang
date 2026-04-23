package com.github.baeyung.poslang.statelang.spec;

import java.util.Map;
import java.util.Set;

public class StateLanguageSpec
{
    // region tags
    private static final String STATE_FILE_TAG = "statefile";
    private static final String INCLUDE_TAG = "include";
    private static final String EXIT_TAG = "exit";
    private static final String STATE_TAG = "state";
    private static final String DATA_TAG = "data";
    private static final String EVENT_TAG = "event";

    public static final Set<String> ALLOWED_FULL_TAGS = Set.of(
            STATE_FILE_TAG,
            STATE_TAG
    );

    public static final Set<String> ALLOWED_SELF_CLOSING_TAGS = Set.of(
            INCLUDE_TAG,
            EXIT_TAG,
            DATA_TAG,
            EVENT_TAG
    );
    // endregion

    // region attributes
    private static final String LOADER_ATTR = "loader";
    private static final String NAME_ATTR = "name";
    private static final String INCLUDE_ATTR = "include";
    private static final String EXCLUDE_ATTR = "exclude";
    private static final String NEXT_ATTR = "next";
    private static final String AUDIT_ATTR = "audit";
    private static final String PAGE_ATTR = "page";
    private static final String PROMPT_ATTR = "prompt";
    private static final String PICTURE_ATTR = "picture";
    private static final String KEYBOARD_ATTR = "keyboard";
    private static final String PNP_ATTR = "pnp";
    private static final String CALL_SUBSTATE_ATTR = "callsubstate";
    private static final String SUBSTATE_NEXT_ATTR = "substatenext";
    private static final String COMMENT_ATTR = "comment";
    private static final String PPI_ATTR = "ppi";
    private static final String FRAME_ATTR = "frame";
    private static final String VALUE_ATTR = "value";
    private static final String CALCULATE_ATTR = "calculate";
    private static final String MAIN_STATE_ATTR = "mainstate";
    private static final String ROOT_START_ATTR = "rootstart";
    private static final String PERMISSION_ATTR = "permission";
    private static final String PERMISSION_FAIL_ATTR = "permissionfail";
    private static final String HELP_REF_ATTR = "helpref";
    private static final String LIKE_ATTR = "like";
    private static final String SOUND_ATTR = "sound";
    private static final String GOTO_SUBSTATE_ATTR = "gotosubstate";
    private static final String FILE_ATTR = "file";

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
                    Set.of(INCLUDE_ATTR, EXCLUDE_ATTR, FILE_ATTR),
                    Set.of()
            ),
            EXIT_TAG, new TagDefinition(
                    Set.of(NAME_ATTR, ROOT_START_ATTR, MAIN_STATE_ATTR),
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
                            HELP_REF_ATTR,
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
                            CALL_SUBSTATE_ATTR,
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

    public static final Set<String> REFERENCEABLE_ATTRIBUTES = Set.of(
            LOADER_ATTR,
            NAME_ATTR,
            INCLUDE_ATTR,
            EXCLUDE_ATTR,
            NEXT_ATTR,
            PAGE_ATTR,
            PICTURE_ATTR,
            PNP_ATTR,
            CALL_SUBSTATE_ATTR,
            SUBSTATE_NEXT_ATTR,
            PPI_ATTR,
            CALCULATE_ATTR,
            MAIN_STATE_ATTR,
            ROOT_START_ATTR,
            PERMISSION_ATTR,
            PERMISSION_FAIL_ATTR,
            HELP_REF_ATTR,
            LIKE_ATTR,
            GOTO_SUBSTATE_ATTR,
            FILE_ATTR
    );
    // endregion
}
