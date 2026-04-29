package com.github.baeyung.poslang.statelang.utils;

import com.github.baeyung.poslang.statelang.StateFile;
import com.github.baeyung.poslang.statelang.StateFileType;
import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StateUtil
{
    public static List<Attribute> findProperties(Project project, String key)
    {
        List<Attribute> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(StateFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles)
        {
            StateFile simpleFile = (StateFile) PsiManager
                    .getInstance(project)
                    .findFile(virtualFile);
            if (simpleFile != null)
            {
                Collection<Attribute> properties = PsiTreeUtil.findChildrenOfType(simpleFile, Attribute.class);
                for (Attribute property : properties)
                {
                    if (key.equals(property.getKey()))
                    {
                        result.add(property);
                    }
                }
            }
        }
        return result;
    }

    public static List<Attribute> findProperties(Project project)
    {
        List<Attribute> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(StateFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles)
        {
            StateFile simpleFile = (StateFile) PsiManager
                    .getInstance(project)
                    .findFile(virtualFile);
            if (simpleFile != null)
            {
                Collection<Attribute> properties = PsiTreeUtil.findChildrenOfType(simpleFile, Attribute.class);
                result.addAll(properties);
            }
        }
        return result;
    }

    public static List<Attribute> findPropertiesByNameValue(Project project, String value)
    {
        List<Attribute> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(StateFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles)
        {
            StateFile simpleFile = (StateFile) PsiManager
                    .getInstance(project)
                    .findFile(virtualFile);
            if (simpleFile != null)
            {
                Collection<Attribute> properties = PsiTreeUtil.findChildrenOfType(simpleFile, Attribute.class);
                for (Attribute property : properties)
                {
                    if ("name".equals(property.getKey()) && value.equals(property.getValue()))
                    {
                        result.add(property);
                    }
                }
            }
        }
        return result;
    }

    public static List<Attribute> findNameAttributes(Project project)
    {
        List<Attribute> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(StateFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles)
        {
            StateFile simpleFile = (StateFile) PsiManager
                    .getInstance(project)
                    .findFile(virtualFile);
            if (simpleFile != null)
            {
                Collection<Attribute> properties = PsiTreeUtil.findChildrenOfType(simpleFile, Attribute.class);
                for (Attribute property : properties)
                {
                    if ("name".equals(property.getKey()))
                    {
                        result.add(property);
                    }
                }
            }
        }
        return result;
    }

    public static List<PsiFile> findFiles(Project project, String filename)
    {
        List<PsiFile> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(StateFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles)
        {
            if (filename.equals(virtualFile.getName()))
            {
                StateFile simpleFile = (StateFile) PsiManager
                        .getInstance(project)
                        .findFile(virtualFile);
                if (simpleFile != null)
                {
                    result.add(simpleFile);
                }
            }
        }
        return result;
    }

    public static List<PsiFile> findFiles(Project project)
    {
        List<PsiFile> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(StateFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles)
        {
            StateFile simpleFile = (StateFile) PsiManager
                    .getInstance(project)
                    .findFile(virtualFile);
            if (simpleFile != null)
            {
                result.add(simpleFile);
            }
        }
        return result;
    }
}
