package com.company;

import java.util.LinkedList;
import java.util.List;

public class LinkDemo {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode l = new ListNode(2);
        ListNode l2 = new ListNode(0);
        ListNode l3 = new ListNode(-4);
//        ListNode l4 = new ListNode(4);
//        ListNode l5 = new ListNode(5);
//        ListNode l6 = new ListNode(6);

        head.next = l;
        l.next = l2;
        l2.next = l3;
        l3.next = l;
//        l4.next = l5;
//        l5.next = l6;
//        l6.next = null;
//        ListNode ls = head;
//        while (ls != null){
//            System.out.println(ls.val);
//            ls = ls.next;
//        }
//        System.out.println("删除后：");
//        ListNode list = removeElements(head,6);
//        while (list != null){
//            System.out.println(list.val);
//            list = list.next;
//        }
//        System.out.println("反转后：");
//        ListNode s = reverseList(removeElements(head,6));
//        while (s != null){
//            System.out.println(s.val);
//            s = s.next;
//        }
        System.out.println(detectCycle(head));
    }
    //移除链表元素
    public static ListNode removeElements(ListNode list,int val){
        ListNode newhead = new ListNode(0);
        newhead.next = list;
        ListNode p = newhead;
        while (p.next != null){
            if (p.next.val == val){
                p.next = p.next.next;
            }else {
                p = p.next;
            }
        }
        return newhead.next;
    }
    //反转链表
    public static ListNode reverseList(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        ListNode tmp = null;
        while (cur !=null){
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
    //环形链表
    public static int detectCycle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {// 有环
                ListNode index1 = fast;
                ListNode index2 = head;
                // 两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1.val;
            }
        }
        return 0;
    }
}
