import org.w3c.dom.Node;

import java.util.Arrays;

/**
 * 栈（Stack）功能演示
 *
 * 概念来源：《漫画算法：小灰的算法之旅》第 2 章 2.3 栈和队列
 *  - 栈是线性数据结构，元素「先入后出」FILO（First In Last Out）
 *  - 最早进入的位置叫「栈底 bottom」，最后进入的位置叫「栈顶 top」
 *  - 栈是逻辑结构，物理上既可用「数组」也可用「链表」实现
 *  - 基本操作 push(入栈) / pop(出栈) 只影响栈顶元素，
 *    不涉及其他元素移动，所以无论哪种实现，时间复杂度都是 O(1)
 */

public class StackDemo{
    /**构建栈类**/
    static class ArrayStack{
        private int[] arr;
        private int top;

        public ArrayStack(int n){
            arr = new int[n];
            top = 0;
        }
        /**入栈**/
        public void push(int data){
            if (top  == arr.length ) {
                arr = Arrays.copyOf(arr, arr.length+5);
            }
            arr[top] = data;
            top++;
        }
        /**弹栈**/
        public int pop() throws Exception{
            if (top == 0){
                throw new RuntimeException("栈已空，无法出栈");
            }
            return arr[top-1];
        }

        public int peek() throws Exception{
            if (top == 0){
                throw new RuntimeException("栈已空");
            }
            return arr[top-1];
        }
        public int size(){
            return top;
        }
        public boolean isEmpty(){
            return top == 0;
        }
    }

    static class LinkedStack{
        private Node head;
        private int size;

        static class Node{
            int data;
            Node next;
            Node(int data){
                this.data = data;
            }
        }

        public void push(int data){
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
            size++;
        }

        public int pop() throws Exception{
            if (size == 0){
                throw new RuntimeException("栈已空，无法出栈");
            }
            int data = head.data;
            head = head.next;
            size--;
            return data;
        }
        //return head.data;
        public int peek() throws Exception{
            if (size == 0){
                throw new RuntimeException("栈已空");
            }
            return head.data;
        }

        public boolean isEmpty(){
            return head == null;
        }

        public int size(){
            return size;
        }
    }
    static boolean isBalanced(String s) throws Exception {
        ArrayStack stack = new ArrayStack(16);
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;   // 右括号多余
                char top = (char) stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == ']' && top != '[') ||
                        (c == '}' && top != '{')) {
                    return false;                     // 配对错误
                }
            }
        }
        return stack.isEmpty();                       // 左括号是否多余
    }

}

