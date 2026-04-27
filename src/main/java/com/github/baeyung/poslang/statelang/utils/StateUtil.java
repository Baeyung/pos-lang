package com.github.baeyung.poslang.statelang.utils;

import com.github.baeyung.poslang.statelang.StateFileType;
import com.github.baeyung.poslang.statelang.StateFile;
import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StateUtil
{
    /**
     * Searches the entire project for State language files with instances of the Attribute with the given key and value.
     */
    public static List<Attribute> findAttributes(Project project, String key, String value)
    {
        List<Attribute> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(StateFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles)
        {
            StateFile stateFile = (StateFile) PsiManager
                    .getInstance(project)
                    .findFile(virtualFile);

            if (stateFile != null)
            {
                Collection<Attribute> attributes = PsiTreeUtil.findChildrenOfType(stateFile, Attribute.class);
                for (Attribute attribute : attributes)
                {
                    if (key.equals(attribute.getKey()) && value.equals(attribute.getValue()))
                    {
                        result.add(attribute);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Searches the entire project for State language files with instances of the Attribute with the given key.
     */
    public static List<Attribute> findAttributes(Project project, String key)
    {
        List<Attribute> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(StateFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles)
        {
            StateFile stateFile = (StateFile) PsiManager
                    .getInstance(project)
                    .findFile(virtualFile);
            if (stateFile != null)
            {
                Collection<Attribute> attributes = PsiTreeUtil.findChildrenOfType(stateFile, Attribute.class);
                for (Attribute attribute : attributes)
                {
                    if (key.equals(attribute.getKey()))
                    {
                        result.add(attribute);
                    }
                }
            }
        }
        return result;
    }
}
