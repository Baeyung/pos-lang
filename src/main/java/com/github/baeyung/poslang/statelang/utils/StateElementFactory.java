package com.github.baeyung.poslang.statelang.utils;

import com.github.baeyung.poslang.statelang.StateFile;
import com.github.baeyung.poslang.statelang.StateFileType;
import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;

public class StateElementFactory
{
    public static Attribute createProperty(Project project, String name)
    {
        StateFile file = createFile(project, name);
        return (Attribute) file.getFirstChild();
    }

    public static StateFile createFile(Project project, String text)
    {
        String name = "dummy.simple";
        return (StateFile) PsiFileFactory
                .getInstance(project).
                createFileFromText(name, StateFileType.INSTANCE, text);
    }
}