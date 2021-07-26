package com.company;

import java.util.*;

public class TreeDemo {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        //二叉搜索树
        TreeNode root2 = new TreeNode(8);
        root2.left = new TreeNode(10);
        root2.right = new TreeNode(4);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(7);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(20);
        root2.left.right.left = new TreeNode(6);
        root2.left.right.right = new TreeNode(5);
        TreeNode p = new TreeNode(6);
        TreeNode q = new TreeNode(5);

//        levelOrder(root);
//        Preorder(root);
//        System.out.println("****中序******");
//        Inorder(root);
//        System.out.println("*****后序******");
//        postorderTraversal(root);
        System.out.println(lowestCommonAncestor(root2,p,q).val);
    }
    //非递归前序遍历
    public static void Preorder(TreeNode root){
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
    }
    //非递归中序遍历
    public static void Inorder(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.println(root.val);
            root = root.right;
        }
    }
    //非递归后序遍历
    public static void postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode prev = null;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                //访问左子树
                stack.push(node);
                node = node.left;
            }
            //判断栈顶元素(根)
            node = stack.peek();
            //1.如果此时的根的右子树为空node.right == null
            //2.如果此时的根的右子树已经访问过了node.right == prev(prev记录的是上次访问打印的节点)
            if(node.right == null || node.right == prev){
                //打印根节点，并出栈，将打印过的节点从栈中删除
                stack.pop();
                System.out.println(node.val);
                //记录prev，表示以当前prev为根的子树已经访问过了
                prev = node;
                //node置null就不会再次访问以node为根节点的左右子树，这里的node既然已经打印，说明它的左右子树早已访问完毕
                node = null;
            }else{
                //访问右子树
                node = node.right;
            }
        }
    }
    //层次遍历
    public static List<Integer> levelOrder(TreeNode root){
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null)
            queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
        return list;
    }
    //翻转二叉树
    public static TreeNode reverseTree(TreeNode root){
        if (root == null)
            return root;
        swap(root);
        reverseTree(root.left);
        reverseTree(root.right);
        return root;
    }
    public static void swap(TreeNode a){
        TreeNode temp;
        temp = a.left;
        a.left = a.right;
        a.right = temp;
    }
    //对称二叉树
    public static boolean isSymmetric(TreeNode root){
        if (root == null)
            return true;
        return compareTree(root.left,root.right);
    }
    public static boolean compareTree(TreeNode left, TreeNode right){
        if (left != null && right == null)
            return false;
        else if (left == null && right != null)
            return false;
        else if (left == null && right == null)
            return true;
        else if (left.val != right.val)
            return false;
        return compareTree(left.left,right.right) && compareTree(left.right,right.left);
    }
    //二叉树的最大深度
    public static int getDepath(TreeNode root){
        if (root == null)
            return 0;
        return Math.max(getDepath(root.left),getDepath(root.right))+1;
    }
    //二叉树的最小深度
    public static int getMinDepath(TreeNode root){
        if (root == null)
            return 0;
        if (root.left == null && root.right != null)
            return getMinDepath(root.right) + 1;
        if (root.left != null && root.right ==null)
            return getMinDepath(root.left) + 1;
        return Math.min(getMinDepath(root.left),getMinDepath(root.right)) + 1;
    }
    //二叉树的节点
    public static int getNodeNum(TreeNode root){
        if (root == null)
            return 0;
        return getNodeNum(root.left) + getNodeNum(root.right) + 1;
    }
    //判断是否是平衡二叉树
    public static boolean isBalanced(TreeNode root){
        return getDepath2(root) == -1 ?  false :true;
    }
    public static int getDepath2(TreeNode root){
        if (root == null)
            return 0;
        int leftDepth = getDepath2(root.left);
        if (leftDepth == -1)
            return -1;
        int rightDepth = getDepath2(root.right);
        if (rightDepth == -1)
            return -1;
        return Math.abs(leftDepth - rightDepth) > 1 ? -1 : 1 + Math.max(leftDepth,rightDepth);
    }
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> paths = new ArrayList<>();
        traversal(root, paths, res);
        return res;
    }
    //二叉树的所有路径
    private static void traversal(TreeNode root, List<Integer> paths, List<String> res) {
        paths.add(root.val);
        // 叶子结点
        if (root.left == null && root.right == null) {
            // 输出
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));
            res.add(sb.toString());
            return;
        }
        if (root.left != null) {
            traversal(root.left, paths, res);
            paths.remove(paths.size() - 1);// 回溯
        }
        if (root.right != null) {
            traversal(root.right, paths, res);
            paths.remove(paths.size() - 1);// 回溯
        }
    }
    //左叶子节点的和
    public static int sumOfLeftnode(TreeNode root){
        if (root == null)
            return 0;
        int value = 0;
        if (root.left != null && root.left.left == null && root.left.right == null){
            value =  root.left.val;
        }
        return value + sumOfLeftnode(root.left) + sumOfLeftnode(root.right);
    }
    //找树左下角的值，迭代法
    public static int traversal(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null)
            queue.offer(root);
        int result = 0;
        while (!queue.isEmpty()){
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll();
                if (i == 0)
                    result = node.val;
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
        return result;
    }
    //递归法
    private int Deep = -1;
    private int value = 0;
    public int findBottomLeftValue(TreeNode root) {
        value = root.val;
        findLeftValue(root,0);
        return value;
    }

    private void findLeftValue (TreeNode root,int deep) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (deep > Deep) {
                value = root.val;
                Deep = deep;
            }
        }
        if (root.left != null) findLeftValue(root.left,deep + 1);
        if (root.right != null) findLeftValue(root.right,deep + 1);
    }
    //路径总和
    public static boolean traversal(TreeNode root,int sum){
        if (root.left == null && root.right == null && sum == 0)
            return true;
        if (root.left == null && root.right == null)
            return false;
        if (root.left != null){
            if (traversal(root.left,sum - root.left.val)){
                return true;
            }
        }
        if (root.right != null){
            if (traversal(root.right,sum - root.right.val)){
                return true;
            }
        }
        return false;
    }
    //从中序与后序遍历序列构造二叉树
    public static TreeNode buildTree(int[] inorder, int inLeft, int inRight,
                                     int[] postorder, int postLeft, int postRight){
        // 没有元素了
        if (inRight - inLeft < 1) {
            return null;
        }
        // 只有一个元素了
        if (inRight - inLeft == 1) {
            return new TreeNode(inorder[inLeft]);
        }
        // 后序数组postorder里最后一个即为根结点
        int rootVal = postorder[postRight - 1];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = 0;
        // 根据根结点的值找到该值在中序数组inorder里的位置
        for (int i = inLeft; i < inRight; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
            }
        }
        // 根据rootIndex划分左右子树
        root.left = buildTree(inorder, inLeft, rootIndex,
                postorder, postLeft, postLeft + (rootIndex - inLeft));
        root.right = buildTree(inorder, rootIndex + 1, inRight,
                postorder, postLeft + (rootIndex - inLeft), postRight - 1);
        return root;
    }
    //合并两个二叉树
    public static TreeNode mergeTrees(TreeNode root1,TreeNode root2){
        if (root1 == null)
            return root2;
        if (root2 == null)
            return root1;
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left,root2.left);
        root1.right = mergeTrees(root1.right,root2.right);
        return root1;
    }
    //二叉搜索树
    public static TreeNode searchBST(TreeNode root, int val) {
        if (root.val == val || root == null)
            return root;
        if (root.val < val)
            return searchBST(root.right,val);
        else
            return searchBST(root.left,val);
    }
    //判断搜索二叉树
    static TreeNode pre = null;
    public static boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        boolean left = isValidBST(root.left);

        if (pre != null && pre.val >= root.val)
            return false;
        pre = root;

        boolean right = isValidBST(root.right);
        return left && right;
    }
    //二叉搜索树中的众数
    static List<Integer> res = new ArrayList<>();
    static int count;
    static int maxCount;
    static TreeNode preNode;
    public static int[] findMode(TreeNode root){
        count = 0;
        maxCount = 0;
        TreeNode preNode = null;
        res.clear();

        searchBST_findMode(root);
        int[] result = new int[res.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = res.get(i);
        }
        return result;
    }
    public static void searchBST_findMode(TreeNode root){
        if (root == null)
            return;
        searchBST_findMode(root.left);
        //中间节点处理
        if (preNode == null)
            count = 1;
        else if (preNode.val == root.val)
            count++;
        else
            count = 1;
        preNode = root;

        if (count == maxCount)
            res.add(root.val);
        //如果计数大于最大频率
        if (count > maxCount){
            maxCount = count;
            res.clear();//记得清空结果集
            res.add(root.val);
        }
        searchBST_findMode(root.right);
    }
    //寻找二叉树的最近公共祖先
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || root == null)
            return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        if (left != null && right != null)
            return root;
        if (left != null && right == null)
            return left;
        else if (left == null && right != null)
            return right;
        else
            return null;
    }
    //删除二叉搜索树中的节点
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)// 第一种情况：没找到删除的节点，遍历到空节点直接返回了
            return root;
        if (root.val == key){
            // 第二种情况：左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点
            // 第三种情况：其左孩子为空，右孩子不为空，删除节点，右孩子补位 ，返回右孩子为根节点
            if (root.left == null)
                return root.right;
            // 第四种情况：其右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
            else if (root.right == null)
                return root.left;
            // 第五种情况：左右孩子节点都不为空，则将删除节点的左子树放到删除节点的右子树的最左面节点的左孩子的位置
            // 并返回删除节点右孩子为新的根节点。
            else{
                TreeNode cur = root.right;//找到右子树中最左面的节点
                while (cur.left != null){
                    cur = cur.left;
                }
                cur.left = root.left;// 把要删除的节点（root）左子树放在cur的左孩子的位置
                root = root.right;// 返回旧root的右孩子作为新root
                return root;
            }
        }
        if (root.val > key)
            root.left = deleteNode(root.left,key);
        if (root.val < key)
            root.right = deleteNode(root.right,key);
        return root;
    }
    //修建搜索二叉树
    public static TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null)
            return null;
        if (root.val < low)
            return trimBST(root.right,low,high);
        if (root.val > high)
            return trimBST(root.left,low,high);
        root.left = trimBST(root.left,low,high);
        root.right = trimBST(root.right,low,high);
        return  root;
    }
}
