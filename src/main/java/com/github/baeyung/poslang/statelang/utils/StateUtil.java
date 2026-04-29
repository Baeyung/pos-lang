package com.github.baeyung.poslang.statelang.utils;

import com.github.baeyung.poslang.statelang.StateFile;
import com.github.baeyung.poslang.statelang.StateFileType;
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
     * Searches the entire project for State language files with instances of the Simple property with the given key.
     *
     * @param project current project
     * @param key     to check
     * @return matching properties
     */
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
                Attribute[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, Attribute.class);
                if (properties != null)
                {
                    for (Attribute property : properties)
                    {
                        if (key.equals(property.getKey()))
                        {
                            result.add(property);
                        }
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
                Attribute[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, Attribute.class);
                if (properties != null)
                {
                    Collections.addAll(result, properties);
                }
            }
        }
        return result;
    }
    //
    //  /**
    //   * Attempts to collect any comment elements above the Simple key/value pair.
    //   */
    //  public static @NotNull String findDocumentationComment(StateFile property) {
    //    List<String> result = new LinkedList<>();
    //    PsiElement element = property.getPrevSibling();
    //    while (element instanceof PsiComment || element instanceof PsiWhiteSpace) {
    //      if (element instanceof PsiComment) {
    //        String commentText = element.getText().replaceFirst("[!# ]+", "");
    //        result.add(commentText);
    //      }
    //      element = element.getPrevSibling();
    //    }
    //    return StringUtil.join(Lists.reverse(result), "\n ");
    //  }
}