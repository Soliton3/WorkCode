package com.company;

import java.util.*;

public class dpDemo {

    public static void main(String[] args) {
	// write your code here
        int[] height = {2,3,6,7};

        int[] weights = {1,2,3,4};
        int[] values = {2,4,4,5};
        int[] nums = {3,1,3,2};
        System.out.println("The result is:"+maxValue(weights,values,5));
//        System.out.println(combinationSum(height,7));
    }
    //01背包问题
    public static int maxValue(int[] weight,int[] values,int m){
        int[] dp = new int[m + 1];
        int n = weight.length;
        for (int i = 0;i < n;i++) {
            for (int j = m; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j],dp[j - weight[i]]+values[i]);
            }
        }
        return dp[m];
    }
    //完全背包问题
    public static int maxValue2(int[] weight,int[] values,int m){
        int[] dp = new int[m + 1];
        int n = weight.length;
        for (int i = 0;i < n;i++) {
            for (int j = 1; j <= m; j++) {
                for(int k = 0; k * weight[i] <= j; k++){
                    dp[j] = Math.max(dp[j],dp[j - k*weight[i]] + k*values[i]);
                }
            }
        }
        return dp[m];
    }
    //多重背包问题
    public static int maxValue3(int[] weight,int[] values,int[] nums,int m){
        int[] dp = new int[m + 1];
        int n = weight.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k < nums[i] && k*weight[i] <= j ; k++) {
                    dp[j] = Math.max(dp[j],dp[j - k*weight[i]] + k*values[i]);
                }
            }
        }

        return dp[m];
    }
    public static int maxArea(int[] height){
        int i = 0,j = height.length - 1;
        int maxV = Integer.MIN_VALUE;
        while (i < j){
            maxV = Math.max(Math.min(height[i],height[j])*(j - i),maxV);
            if (height[j] > height[i]){
                i++;
            }else {
                j--;
            }
        }
//        System.out.println("i = "+height[i]+"j = "+height[j]);
        return maxV;
    }
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public static void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }

}
