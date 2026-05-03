package com.github.baeyung.poslang.statelang.utils;

import com.github.baeyung.poslang.statelang.StateFileType;
import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;

public class StateElementFactory
{
    public static Attribute createNameAttribute(Project project, String name)
    {
        PsiFile file = createFile(project, "<state name=\"" + escapeAttributeValue(name) + "\"/>");
        return PsiTreeUtil.findChildOfType(file, Attribute.class);
    }

    public static PsiFile createFile(Project project, String text)
    {
        String name = "dummy.state";
        return PsiFileFactory
                .getInstance(project).
                createFileFromText(name, StateFileType.INSTANCE, text);
    }

    private static String escapeAttributeValue(String value)
    {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}
