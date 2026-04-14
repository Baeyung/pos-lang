package com.github.baeyung.poslang.statelang.utils;

// This file contains the boilerPlate code of example which is not used for now, lets come back to it later
public class StateUtil
{

//  /**
//   * Searches the entire project for State language files with instances of the Simple property with the given key.
//   *
//   * @param project current project
//   * @param key     to check
//   * @return matching properties
//   */
//  public static List<SimpleProperty> findProperties(Project project, String key) {
//    List<SimpleProperty> result = new ArrayList<>();
//    Collection<VirtualFile> virtualFiles =
//        FileTypeIndex.getFiles(StateFileType.INSTANCE, GlobalSearchScope.allScope(project));
//    for (VirtualFile virtualFile : virtualFiles) {
//        StateFile simpleFile = (StateFile) PsiManager.getInstance(project).findFile(virtualFile);
//      if (simpleFile != null) {
//        SimpleProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, SimpleProperty.class);
//        if (properties != null) {
//          for (SimpleProperty property : properties) {
//            if (key.equals(property.getKey())) {
//              result.add(property);
//            }
//          }
//        }
//      }
//    }
//    return result;
//  }
//
//  public static List<SimpleProperty> findProperties(Project project) {
//    List<SimpleProperty> result = new ArrayList<>();
//    Collection<VirtualFile> virtualFiles =
//        FileTypeIndex.getFiles(SimpleFileType.INSTANCE, GlobalSearchScope.allScope(project));
//    for (VirtualFile virtualFile : virtualFiles) {
//        StateFile simpleFile = (StateFile) PsiManager.getInstance(project).findFile(virtualFile);
//      if (simpleFile != null) {
//        SimpleProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, SimpleProperty.class);
//        if (properties != null) {
//          Collections.addAll(result, properties);
//        }
//      }
//    }
//    return result;
//  }
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