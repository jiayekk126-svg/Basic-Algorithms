 package com.company;

public class MyLinkedList {
    private static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
        }
    }
    private Node last;
    private Node head;
    private int size;

    public Node get(int index){
        Node temp = head;
        for (int i=0; i<index; i++){
            temp = temp.next;
        }
        return temp;
    }

    public void output(){
        Node temp = head;
        while (temp!= null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public void insert(int data, int index) throws Exception{
        if (index<0 || index>size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }

        Node insertedNode = new Node(data);
        if(size == 0){
            head = insertedNode;
            last = insertedNode;
        }else if(index == 0){
            insertedNode.next = head;
            head = insertedNode;
        }else if (size == index){
            last.next = insertedNode;
            last = insertedNode;
        }else {
            Node prevNode = get(index-1);
            insertedNode.next = prevNode.next;
            prevNode.next = insertedNode;
        }
        size++;
    }

    public Node remove(int index) throws Exception{
        if(index<0 || index>size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node removeNode = null;
        if(index == 0){
            removeNode = head;
            head = head.next;
        }else if (index == size-1){
            Node prevNode = get(index-1);
            removeNode = prevNode.next;
            prevNode.next = null;
            last = prevNode;
        }
        size--;
        return removeNode;
    }


    public static void main(String[] args) throws Exception{
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.insert(3,0);
        myLinkedList.insert(7,1);
        myLinkedList.insert(9,2);
        myLinkedList.insert(5,3);
        myLinkedList.insert(6,1);
        myLinkedList.remove(0);
        myLinkedList.output();




    }
}
