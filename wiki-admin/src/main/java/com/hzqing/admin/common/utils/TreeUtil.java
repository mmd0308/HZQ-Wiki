package com.hzqing.admin.common.utils;


import com.hzqing.admin.domain.base.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeUtil {

  private TreeUtil() {
    throw new IllegalStateException("错误：不能被实例化");
  }


  public static <T extends TreeNode> List<T> build(List<T> treeNodes, Integer root) {
    if (root == null){
      return null;
    }
    List<T> trees = new ArrayList();
    Iterator<T> iterator = treeNodes.iterator();
    while(iterator.hasNext()){
      T treeNode = iterator.next();
      if (treeNode.getParentId() != null) {
        if (root == treeNode.getParentId()) {
          trees.add(treeNode);
          iterator.remove();
        }
        // 根据索引依次赋值
        for (T it : treeNodes) {
          if (it.getParentId() == treeNode.getId()) {
            if (treeNode.getChildren() == null) {
              treeNode.setChildren(new ArrayList());
            }
            treeNode.getChildren().add(it);
          }
        }
      }
    }
    return trees;
  }
}