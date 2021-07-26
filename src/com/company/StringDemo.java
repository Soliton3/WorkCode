package com.company;

public class StringDemo {
    public static void main(String[] args) {
        char[] str = {'h','e','l','l','o'};
//        reverseString(str);
//        for (char c:str){
//            System.out.println(c);
//        }
        String s = "aabaaf";
        String strs ="aabaacaabbdaabaaf";
        System.out.println(strStr(strs,s));
    }
    //反转字符
    public static void reverseString(char[] s){
        int i=0,j=s.length-1;
        char temp = '\0';
        while (i<j){
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
    //替换空格
    public static String replaceSpace(String str){
        if (str == null) {
            return null;
        }
        //选用 StringBuilder 单线程使用，比较快，选不选都行
        StringBuilder sb = new StringBuilder();
        //使用 sb 逐个复制 str ，碰到空格则替换，否则直接复制
        for (int i = 0; i < str.length(); i++) {
            //str.charAt(i) 为 char 类型，为了比较需要将其转为和 " " 相同的字符串类型
            if (" ".equals(String.valueOf(str.charAt(i)))){
                sb.append("%20");
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
    //左旋转字符串
    /*
    反转区间为前n的子串
    反转区间为n到末尾的子串
    反转整个字符串
    */
    public static String reverseLeftWords(String s,int n){
        StringBuilder sb = new StringBuilder(s);
        reverseString(sb,0,n-1);
        reverseString(sb,n,sb.length()-1);
        reverseString(sb,0,sb.length()-1);
        return sb.toString();
    }
    /**
     * 反转字符串指定区间[start, end]的字符
     */
    public static void reverseString(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }
    /*
      KMP代码实现,next数组不减一
     */
    public static void getNext(int[] next,String str){
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < str.length(); i++) {
            while (str.charAt(i) != str.charAt(j) && j > 0){
                j = next[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)){
                j++;
                next[i] = j;
            }
        }
    }
    public static int strStr(String str,String subStr){
        if (subStr.length() == 0)
            return 0;
        int[] next = new int[subStr.length()];
        getNext(next,subStr);
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != subStr.charAt(j)){
                j = next[j -1];
            }
            if (str.charAt(i) == subStr.charAt(j)){
                j++;
            }
            if (j == subStr.length()){
                return (i - subStr.length() + 1);
            }
        }
        return -1;
    }
    //重复的子字符串
    public static boolean repeatedSubstringPattern(String s) {
        if (s.equals("")) return false;

        int len = s.length();
        // 原串加个空格(哨兵)，使下标从1开始，这样j从0开始，也不用初始化了
        s = " " + s;
        char[] chars = s.toCharArray();
        int[] next = new int[len + 1];

        // 构造 next 数组过程，j从0开始(空格)，i从2开始
        for (int i = 2, j = 0; i <= len; i++) {
            // 匹配不成功，j回到前一位置 next 数组所对应的值
            while (j > 0 && chars[i] != chars[j + 1]) j = next[j];
            // 匹配成功，j往后移
            if (chars[i] == chars[j + 1]) j++;
            // 更新 next 数组的值
            next[i] = j;
        }

        // 最后判断是否是重复的子字符串，这里 next[len] 即代表next数组末尾的值
        if (next[len] > 0 && len % (len - next[len]) == 0) {
            return true;
        }
        return false;
    }
}
