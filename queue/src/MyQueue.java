public class MyQueue{

    private int[] array;//队列数组
    private int size;//队列长度
    private int front;//队头
    private int rear;//队尾

    public MyQueue(int size){
        this.array = new int[size];
        this.size = array.length;//队列长度
        this.front = 0;//队头初始位置
        this.rear = 0;//队尾初始位置
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public boolean isFull(){
        return (rear + 1) % size == front;
    }
    //入队操作
    public void enQueue(int value) throws Exception{
        if(isFull()){
            throw new Exception("Queue is full");
        }
        array[rear] = value;
        rear = (rear + 1) % size;

    }
    //出队操作
    public int deQueue() throws Exception{
        if(isEmpty()){
            throw new Exception("Queue is empty");
        }
        int value = array[front];
        front = (front + 1) % size;
        return value;
    }

    //获取队头元素
    public int getFront() throws Exception{
        if(isEmpty()){
            throw new Exception("Queue is empty");
        }
        return array[front];
    }

    //获取队尾元素
    public int getRear() throws Exception{
        if(isEmpty()){
            throw new Exception("Queue is empty");
        }
        return array[rear];
    }

    //获取队列长度
    public int getSize() throws Exception{
        return size;
    }

    //输出队列元素
    public void outQueue(){
        for(int i = front; i!=rear; i=(i+1)%size){
            System.out.print(array[i] + " ");
        }
    }
    //测试
    public static void main(String[] args) throws Exception{
        MyQueue myQueue = new MyQueue(5);
        myQueue.enQueue(1);
        myQueue.enQueue(2);
        myQueue.enQueue(3);
        myQueue.enQueue(4);
        myQueue.enQueue(5);
        myQueue.outQueue();
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.outQueue();
        myQueue.enQueue(6);
        myQueue.outQueue();
    }
}

