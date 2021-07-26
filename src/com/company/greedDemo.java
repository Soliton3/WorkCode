package com.company;

public class greedDemo {
    public static void main(String[] args) {
        int[] nums = {1,7,4,9,2,5};
        int[] coins = {1,2,5};
        System.out.println(change(4,coins));
    }
    //贪心算法解摆动序列
    public static int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1)
            return nums.length;
        int curDiff = 0;
        int preDiff = 0;
        int result = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            curDiff = nums[i+1] - nums[i];
            if ((curDiff > 0 && preDiff <=0)||(preDiff >= 0 && curDiff < 0)){
                result++;
                preDiff = curDiff;
            }
        }
        return result;
    }
    //零钱兑换
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
//        for(int i = 0;i < coins.length;i++){
//            for(int j = coins[i];j <= amount;j++){
//                dp[j] += dp[j - coins[i]];
//                System.out.print(dp[j]+" ");
//            }
//            System.out.println();
//        }
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]){
                    dp[i] += dp[i - coins[j]];
                    System.out.print(dp[i]+" ");
                }
            }
            System.out.println();
        }
        return dp[amount];
    }

}
