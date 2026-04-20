package com.github.baeyung.poslang.statelang.utils.psi;

import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.EndTag;
import com.github.baeyung.poslang.statelang.psi.EmptyTag;
import com.github.baeyung.poslang.statelang.psi.StartTag;
import com.github.baeyung.poslang.statelang.psi.TagName;
import com.github.baeyung.poslang.statelang.psi.AttributeName;
import com.github.baeyung.poslang.statelang.psi.AttributeValue;
import com.intellij.openapi.util.text.StringUtil;

public class StatePsiImplUtil {

    public static String getKey(Attribute element) {
        AttributeName name = element.getAttributeName();
        if (name != null && StringUtil.isNotEmpty(name.getText())) {
            return name.getText().toLowerCase();
        }
        return null;
    }

    public static String getValue(Attribute element) {
        AttributeValue value = element.getAttributeValue();
        if (value != null && StringUtil.isNotEmpty(value.getText())) {
            // Unquote string
            String text = value.getText();
            if (text.length() >= 2 && text.startsWith("\"") && text.endsWith("\"")) {
                text = text.substring(1, text.length() - 1);
            }
            return text.toLowerCase();
        }
        return null;
    }

    public static String getName(StartTag element) {
        TagName tagName = element.getTagName();
        if (tagName != null && StringUtil.isNotEmpty(tagName.getText())) {
            return tagName.getText().toLowerCase();
        }
        return null;
    }

    public static String getName(EndTag element) {
        TagName tagName = element.getTagName();
        if (tagName != null && StringUtil.isNotEmpty(tagName.getText())) {
            return tagName.getText().toLowerCase();
        }
        return null;
    }

    public static String getName(EmptyTag element) {
        TagName tagName = element.getTagName();
        if (tagName != null && StringUtil.isNotEmpty(tagName.getText())) {
            return tagName.getText().toLowerCase();
        }
        return null;
    }
}
