package com.company;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] arrays = {1,2,3,4,2,3};

        System.out.println(minSubArrayLen(7,arrays));
    }
    //删除数组指定元素，利用快慢指针
    /*
    int[] nums = {1,2,3,4,5，6} ,删除 3
     */
    public static int deleteArray(int[] nums,int target){
        int slowindex = 0;
        for (int faseindex = 0; faseindex < nums.length; faseindex++) {
            if (target != nums[faseindex]){
                nums[slowindex++] = nums[faseindex];
            }
        }
        return slowindex;
    }
    //有序数组的平方
    public static int[] sortedSquares(int[] A){
        int i = 0,j = A.length - 1,k = A.length - 1;
        int[] res = new int[A.length];
        while (i <= j){
            if (A[i]*A[i] < A[j]*A[j]){
                res[k] = A[j]*A[j];
                j--;
            }else {
                res[k] = A[i]*A[i];
                i++;
            }
            k--;
        }
        return res;
    }
    //长度最小的子数组
    public static int minSubArrayLen(int s,int[] nums){
        int result = Integer.MAX_VALUE;
        int sum = 0, i = 0;
        int subLen = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= s){
                subLen = (j - i + 1);
                result = result < subLen ? result:subLen;
                sum -= nums[i++];
            }
        }
        return result == Integer.MAX_VALUE?0:result;
    }

}
