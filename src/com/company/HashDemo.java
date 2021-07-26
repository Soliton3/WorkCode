package com.company;

import java.util.*;

public class HashDemo {
    public static void main(String[] args) {
        int[] nums1 = {-1,0,1,2,-1,-4};
        int[] nums2 = {1,2,2};
        System.out.println(threeSum(nums1));
//        int[] res = twoSum(nums1,20);
//        for (int n:res){
//            System.out.println(n);
//        }
    }
    //两个数组的交集
    public static int[] intersection(int[] nums1,int[] nums2){
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int num:nums1){
            set1.add(num);
        }

        for (int n:nums2){
            if (set1.contains(n)){
                set2.add(n);
            }
        }

        int[] res = new int[set2.size()];
        int i = 0;
        for (int n:set2){
            res[i] = n;
            i++;
        }
        return res;
    }
    //快乐数
    public static boolean isHappy(int n){
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n);
            n = getSum(n);
        }
        return n == 1;
    }
    //得到每个位上的总和
    public static int getSum(int n){
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
    //两数之和
    public static int[] twoSum(int[] nums,int target){
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
             return res;
        }
        HashMap<Integer,Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int tmp = target - nums[i];
            if (hash.containsKey(tmp)){
                res[0] = hash.get(tmp);
                res[1] = i;
            }
            hash.put(nums[i],i);
        }
        return res;
    }
    //三数之和
    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;

                    right--;
                    left++;
                }
            }
        }
        return result;
    }

}
