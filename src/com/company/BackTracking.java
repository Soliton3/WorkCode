package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BackTracking {

    static List<Integer> path = new ArrayList<>();
    static List<List<Integer>> res = new ArrayList<>();

    static List<String> list = new ArrayList<>();
    static List<List<String>> ans = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = {2,5,3};
        String s = "aab";
        getPartition("aab",0);
        System.out.println(ans);
//        backTracking3(nums,4,0,0);
//        System.out.println(res);
//        backTracking2(4,2,1);
//        System.out.println(res);
//        System.out.println(backTracking2(4,2,1));
    }

    //组合问题
    public static void backTracking(int n,int k,int sindex){
        if (path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = sindex; i <= n; i++) {
            path.add(i);
            backTracking(n,k,i+1);
            path.remove(path.size()-1);
        }
    }
    //组合问题带剪枝
    public static void backTracking2(int n,int k,int sindex){
        if (path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = sindex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backTracking2(n,k,i+1);
            path.remove(path.size()-1);
        }
    }
    //组合求总和问题
    public static void backTracking3(int[] nums,int target,int sum,int startindex){
        if (sum > target){
            return;
        }
        if (sum == target){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startindex; i < nums.length; i++) {
            sum += nums[i];
            path.add(nums[i]);
            backTracking3(nums,target,sum,i);
            sum -= nums[i];
            path.remove(path.size()-1);
        }
    }
    //分割回文串
    public static void getPartition(String s,int startIndex){
        //收集结果
        if (startIndex >= s.length()){
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrome(s,startIndex,i)){
                //获取子串
                String subStr = s.substring(startIndex,i + 1);
                list.add(subStr);
            }else {
                continue;
            }
            getPartition(s,i+1);
            list.remove(list.size()-1);
        }
    }
    public static boolean isPalindrome(String str,int m,int n){
        for (int i = m,j = n; i < j; i++,j--) {
            if (str.charAt(i) != str.charAt(j)){
                return false;
            }
        }
        return true;
    }
    //组合问题3
    public static List<List<Integer>> combinationSum3(int k, int n) {
        Backtrace_combinationSum3(k,n,1,0);
        return res;
    }
    public static void Backtrace_combinationSum3(int k, int n,int startIndex,int sum) {
        if (path.size() == k){
            if (sum == n)
                res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= 9; i++) {
            sum += i;
            path.add(i);
            Backtrace_combinationSum3(k,n,i+1,sum);
            sum -= i;
            path.remove(path.size() -1);
        }
    }
    //字符串排列
//    public String[] permutation(String s) {
//        boolean[] used = new boolean[s.length()];
//        getString(s,0,used);
//        return ans;
//    }
    public static void getString(String s,int startIndex,boolean[] used){
        //收集结果
        if (list.size() == s.length()){
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) == s.charAt(i -1) && used[i] == true){
                continue;
            }
            String subStr = s.substring(startIndex,i);
            list.add(subStr);
            used[i] = true;
            getString(s,0,used);
            used[i] = false;
            list.remove(list.size()-1);
        }
    }
}
