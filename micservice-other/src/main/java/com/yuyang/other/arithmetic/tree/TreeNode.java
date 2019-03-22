package com.yuyang.other.arithmetic.tree;

/**
 * @author yuyang
 * @create 2019/3/21 10:18
 * @desc 二叉树定义
 **/
public class TreeNode {
    public TreeNode(TreeNode left, TreeNode right, Integer value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public TreeNode(Integer value) {
        this.value = value;
    }

    public TreeNode() {
    }

    /**
     * 左子树
     */
    TreeNode left;
    /**
     * 右子树
     */
    TreeNode right;
    /**
     * 当前节点的值
     */
    Integer value;
}
