package NormalTest;

import java.util.*;

public class testOne {
    static List<Integer> path = new ArrayList<>();
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
//        System.out.println(numSubarraysWithSum(nums,2));
//        int[] res = topKFrequent(nums,2);
//        for (int n:res){
//            System.out.println(n);
//        }
        System.out.println(search(nums,8));
    }
    //雪糕最大数量,贪心算法
    public static int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int maxNum = 0;
        for (int cost : costs) {
            if (cost <= coins) {
                maxNum++;
                coins = coins - cost;
            }
        }
        return maxNum;
    }
    static int count = 0;
    public static int numSubarraysWithSum(int[] nums, int goal) {
        backTracking(nums,goal,0,0);
        return count;
    }
    public static String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c:s.toCharArray()){
            if(stack.isEmpty() || c != stack.peek()){
                stack.push(c);
            }else {
                stack.pop();
            }
        }
        String str = "";
        while (!stack.isEmpty()) {
            str = stack.pop() + str;
        }
        return str;
    }
    //组合求总和问题
    public static void backTracking(int[] nums,int target,int sum,int startIndex){
        if (sum > target){
            return;
        }
        if (sum == target){
            count++;
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            sum += nums[i];
            backTracking(nums,target,sum,i);
            sum -= nums[i];
        }
    }
    //前k个频率出现的元素
    public static int[] topKFrequent(int[] nums,int k){
        int[] result = new int[k];
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        Set<Map.Entry<Integer,Integer>> entries = map.entrySet();
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
        for (Map.Entry<Integer, Integer> entry : entries) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while(left < right){
            int mid = (right + left)/2;
            if(target == nums[mid]){
                count++;
                left = mid + 1;
            }else if(target < nums[mid]){
                right = mid + 1;
            }else{
                left = mid;
            }
        }
        return count;
    }
}
