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
                throw new RuntimeException("栈已空，无法出栈");//栈为空
            }
            top--;              // 关键：栈顶下移一位，元素才算真正出栈
            return arr[top];
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
                throw new RuntimeException("栈已空，无法出栈");//栈为空
            }
            int data = head.data;
            head = head.next;
            size--;
            return data;
        }
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
    /**
     * 判断字符串中的括号是否成对且嵌套正确
     *
     * 思路：
     *  - 遇到左括号就入栈，表示「还没被配对的左括号」
     *  - 遇到右括号就弹出栈顶，看它是不是同类型的左括号
     *  - 因为栈是后入先出，弹出的恰好是最近一个未配对的左括号，
     *    这正好对应括号「就近嵌套」的规则
     *
     * 三种非法情况：
     *  1. 弹栈时栈已空 —— 右括号多余，如 "())"
     *  2. 弹出的左括号类型不一致 —— 配对错误，如 "(]"
     *  3. 遍历结束后栈非空 —— 左括号多余，如 "(()"
     *
     * 时间复杂度 O(n)，空间复杂度 O(n)
     *
     * @param s 待检测的字符串，非括号字符会被直接忽略
     * @return true 表示所有括号均正确配对
     * @throws Exception 栈操作抛出的异常（ArrayStack 内部用异常表示栈空）
     */
    static boolean isBalanced(String s) throws Exception {
        ArrayStack stack = new ArrayStack(16);        // 初始容量 16，溢满时自动扩容
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);                        // 左括号：入栈，等待与右括号配对
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;    // 右括号多余：栈里没有左括号可与它配对
                char top = (char) stack.pop();        // 取出最近一个未配对的左括号
                if ((c == ')' && top != '(') ||
                        (c == ']' && top != '[') ||
                        (c == '}' && top != '{')) {
                    return false;                     // 配对错误：左右括号类型不一致
                }
            }
            // 其他字符（字母、数字、空格等）直接跳过，不影响括号配对
        }
        return stack.isEmpty();                       // 遍历结束栈必须为空，否则说明左括号多余
    }

    static String reverse(String s) throws Exception{
        ArrayStack stack = new ArrayStack(16);
        for (char c : s.toCharArray()) {
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append((char) stack.pop());            // 栈里存的是 char 的 int 值，拼回字符串要强转
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== 1.数组栈的基本操作 ===");
        ArrayStack stack = new ArrayStack(16);
        stack.push(3);//入栈
        stack.push(5);
        stack.push(7);
        System.out.println("入栈3，5，7后栈顶 = " + stack.peek());//栈顶元素
        stack.push(9);
        System.out.println("入栈3，5，7，9后栈顶 = " + stack.peek());//栈顶元素
        System.out.println("栈的大小 = " + stack.size());
        System.out.println("栈是否为空 = " + stack.isEmpty());
        System.out.println("出栈元素（先进后出）");
        while (!stack.isEmpty()) {
            System.out.printf("%d ", stack.pop());
        }
        System.out.println();

        System.out.println("=== 2.链表栈的基本操作 ===");
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push(3);
        linkedStack.push(5);
        linkedStack.push(7);
        System.out.println("入栈3，5，7后栈顶 = " + linkedStack.peek());//栈顶元素
        linkedStack.push(9);
        System.out.println("入栈3，5，7，9后栈顶 = " + linkedStack.peek());//栈顶元素
        System.out.println("栈的大小 = " + linkedStack.size());
        System.out.println("栈是否为空 = " + linkedStack.isEmpty());
        System.out.println("出栈元素（先进后出）");
        while (!linkedStack.isEmpty()) {
            System.out.printf("%d ", linkedStack.pop());
        }
        System.out.println();

        System.out.println("=== 3.栈的应用: 判断字符串中的括号是否成对且嵌套正确 ===");
        System.out.println("判断字符串中的括号是否成对且嵌套正确");
        System.out.println(isBalanced("()[]{}")); // true
        System.out.println(isBalanced("([{}])")); // true
        System.out.println(isBalanced("(]"));     // false
        System.out.println(isBalanced("([)]"));   // false
        System.out.println(isBalanced(""));       // true
        System.out.println(isBalanced("(()"));      // false

        System.out.println("=== 4.栈的应用: 字符串反转 ===");
        System.out.println("字符串反转");
        System.out.println(reverse("hello")); // olleh
        System.out.println(reverse("world")); // dlrow
    }


}


