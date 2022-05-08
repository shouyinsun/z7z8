package z7z8.z7z8Test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @author cash
 * @description
 * @date 2022/4/1 11:14 PM
 */
public class LruCache<K, V> {

    private int size = 0;
    private int capacity;
    private Node head;
    private Node tail;
    private Map<K, Node<K, V>> map = new HashMap();

    class Node<K, V> {
        Node pre;
        Node next;
        K key;
        V value;

        public Node() {
        }

        public Node(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }

    public LruCache(int capacity) {
        this.capacity = capacity;
        //头尾节点都是伪节点
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public V get(K key) {
        Node<K, V> node = map.get(key);
        if (null != node) {
            //先断链移除
            remove(node);
            moveToHead(node);
            return node.value;
        }
        return null;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            node.value = value;
            //先断链移除
            remove(node);
            moveToHead(node);
        } else {
            size++;
            Node node = new Node(key, value);
            map.put(key, node);
            moveToHead(node);
            if (size > capacity) {
                removeLast();
                size--;
            }
        }
    }

    private void moveToHead(Node node) {
        node.pre =head;
        node.next =head.next;
        head.next.pre =node;
        head.next =node;
    }

    private void removeLast() {
        Node last =tail.pre;
        //移除map
        map.remove(last.key);
        remove(last);
    }

    private void remove(Node node){
        node.pre.next =node.next;
        node.next.pre =node.pre;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        LruCache<Integer,Integer> lru =new LruCache(2);
        lru.put(1,1);
        lru.put(2,2);
        System.out.println(lru.get(2));
        System.out.println(lru.get(1));
        lru.put(3,3);
        System.out.println(lru.get(2));
        System.out.println(JSONObject.toJSONString(lru.map));
    }
}
