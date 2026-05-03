package com.github.baeyung.poslang.statelang.utils;

import com.github.baeyung.poslang.statelang.StateFileType;
import com.github.baeyung.poslang.statelang.psi.Attribute;
import com.github.baeyung.poslang.statelang.psi.SelfClosingTag;
import com.github.baeyung.poslang.statelang.psi.StartTag;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StateUtil
{
    public static List<Attribute> findNameAttributes(Project project, String name)
    {
        List<Attribute> result = new ArrayList<>();
        for (Attribute attribute : findNameAttributes(project))
        {
            if (name.equals(attribute.getName()))
            {
                result.add(attribute);
            }
        }
        return result;
    }

    public static List<Attribute> findNameAttributes(Project project)
    {
        List<Attribute> result = new ArrayList<>();
        for (PsiFile stateFile : findStateFiles(project))
        {
            Collection<Attribute> attributes = PsiTreeUtil.findChildrenOfType(stateFile, Attribute.class);
            for (Attribute attribute : attributes)
            {
                if (attribute.getName() != null)
                {
                    result.add(attribute);
                }
            }
        }
        return result;
    }

    public static List<PsiFile> findStateFiles(Project project, PsiElement context, String referenceText)
    {
        List<PsiFile> result = new ArrayList<>();
        Set<VirtualFile> seen = new HashSet<>();
        String normalizedReference = normalizePath(referenceText);

        if (normalizedReference.isEmpty())
        {
            return result;
        }

        PsiFile relativeFile = findRelativeStateFile(project, context, normalizedReference);
        if (relativeFile != null)
        {
            VirtualFile virtualFile = relativeFile.getVirtualFile();
            if (virtualFile != null)
            {
                seen.add(virtualFile);
            }
            result.add(relativeFile);
        }

        for (VirtualFile virtualFile : getStateVirtualFiles(project))
        {
            if (!matchesStateFile(virtualFile, normalizedReference) || !seen.add(virtualFile))
            {
                continue;
            }

            PsiFile stateFile = PsiManager
                    .getInstance(project)
                    .findFile(virtualFile);
            if (stateFile != null)
            {
                result.add(stateFile);
            }
        }

        return result;
    }

    public static String getContainingTagName(Attribute attribute)
    {
        PsiElement parent = attribute.getParent();
        while (parent != null)
        {
            if (parent instanceof StartTag startTag)
            {
                return startTag.getTagName();
            }

            if (parent instanceof SelfClosingTag selfClosingTag)
            {
                return selfClosingTag.getTagName();
            }

            parent = parent.getParent();
        }

        return null;
    }

    public static List<PsiFile> findStateFiles(Project project)
    {
        List<PsiFile> result = new ArrayList<>();
        for (VirtualFile virtualFile : getStateVirtualFiles(project))
        {
            PsiFile stateFile = PsiManager
                    .getInstance(project)
                    .findFile(virtualFile);
            if (stateFile != null)
            {
                result.add(stateFile);
            }
        }

        return result;
    }

    private static PsiFile findRelativeStateFile(Project project, PsiElement context, String normalizedReference)
    {
        PsiFile containingFile = context.getContainingFile();
        if (containingFile == null || containingFile.getVirtualFile() == null)
        {
            return null;
        }

        VirtualFile directory = containingFile
                .getVirtualFile()
                .getParent();
        VirtualFile target = directory != null ? directory.findFileByRelativePath(normalizedReference) : null;
        if (target == null || !StateFileType.INSTANCE.equals(target.getFileType()))
        {
            return null;
        }

        return PsiManager
                .getInstance(project)
                .findFile(target);
    }

    private static Collection<VirtualFile> getStateVirtualFiles(Project project)
    {
        return FileTypeIndex.getFiles(StateFileType.INSTANCE, GlobalSearchScope.projectScope(project));
    }

    private static boolean matchesStateFile(VirtualFile virtualFile, String normalizedReference)
    {
        String normalizedName = normalizePath(virtualFile.getName());
        String normalizedPath = normalizePath(virtualFile.getPath());

        return normalizedName.equals(normalizedReference) ||
               normalizedPath.endsWith("/" + normalizedReference);
    }

    private static String normalizePath(String path)
    {
        return path
                .trim()
                .replace('\\', '/');
    }
}
