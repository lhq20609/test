package lhq.test;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU算法
 */
public class LRUCacheDemo {
    private final int DEFAULT_INITIAL_CAPACITY = 5;
    private int cacheSize;
    Map<Integer,Node<Integer,Integer>> map;
    DoubleLinkedList<Integer,Integer> doubleLinkedList;

    public LRUCacheDemo(int cacheSize){
        this.cacheSize=cacheSize;
        map = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }
    public LRUCacheDemo(){
        this.cacheSize = DEFAULT_INITIAL_CAPACITY;
        map = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }
    public int getKey(int key){
        if(!map.containsKey(key))
            return -1;
        Node<Integer, Integer> node = map.get(key);
        doubleLinkedList.removeNode(node); //先把该节点在原来的位置删除
        doubleLinkedList.addHead(node);//再重新放到头节点的后面
        return node.value;
    }
    //保存或者更新
    public void put(int key,int value){
        if(map.containsKey(key)){
            Node<Integer, Integer> node = map.get(key);
            node.value=value;
            map.put(key,node);
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        }else{
            if(map.size() == cacheSize){
                Node<Integer, Integer> lastNode = doubleLinkedList.getLastNode();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }
            Node<Integer, Integer> newNode = new Node<>(key, value);
            map.put(key,newNode);
            doubleLinkedList.addHead(newNode);
        }
    }
    static class Node<K,V>{
        K key;
        V value;
        Node<K,V> next;
        Node<K,V> pre;
        public Node(){
            next=pre=null;
        }
        public Node(K key,V value){
            this.key=key;
            this.value=value;
            next=pre=null;
        }
    }
    static class DoubleLinkedList<K,V>{
        Node<K,V> head;
        Node<K,V> tail;
        public DoubleLinkedList(){
            head = new Node<>();
            tail = new Node<>();
            head.next=tail;
            tail.pre=head;
        }

        //添加头节点,插入到head和tail中间
        public void addHead(Node<K,V> node){
            node.next=head.next;
            node.pre=head;
            head.next.pre=node;
            head.next=node;
        }
        //删除节点
        public void removeNode(Node<K,V> node){
            node.next.pre=node.pre;
            node.pre.next=node.next;
            node.pre=null;
            node.next=null;
        }

        //获取最后一个节点
        public Node<K,V> getLastNode(){
            return tail.pre;
        }
    }

    public static void main(String[] args) {
        LRUCacheDemo cacheDemo = new LRUCacheDemo(3);
        cacheDemo.put(1,1);
        cacheDemo.put(2,2);
        cacheDemo.put(3,3);
        System.out.println(cacheDemo.map.keySet());
        cacheDemo.put(4,4);
        cacheDemo.put(3,3);
        System.out.println(cacheDemo.map.keySet());
    }
}
