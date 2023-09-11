package DataStructures;

import java.util.LinkedList;

public class HashMapCode {
   static class HashMap<K, V> {

      private class Node{
         K key;
         V value;

         public Node(K key, V value){
            this.key = key;
            this.value = value;
         }
      }

      private int n;
      private int N;
      private LinkedList<Node> buckets[];

      public HashMap() {
         this.N = 4;
         this.buckets = new LinkedList[4];

         for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
         }
      }

      private int searchInLinkedList(int bucketIndex, K key) {
         LinkedList<Node> bucket = buckets[bucketIndex];

         for (int i = 0; i < bucket.size(); i++) {
            if(bucket.get(i).key == key) {
               return i;
            }
         }
         return -1;
      }

      private void reHash() {
         LinkedList<Node> oldBuckets[] = buckets;
         buckets = new LinkedList[N * 2];
         for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
         }

         for (int i = 0; i < oldBuckets.length; i++) {
            LinkedList<Node> oldBucket = oldBuckets[i];
            for (int j = 0; j < oldBucket.size(); j++) {
               Node oldNode = oldBucket.get(j);
               put(oldNode.key, oldNode.value);
            }
         }
      }

      private int searchBucketIndex(K key) {
         int bucketIndexHash = key.hashCode();
         return Math.abs(bucketIndexHash) % N;
      }

      public void put(K key, V value) {
         int bucketIndex = searchBucketIndex(key);
         int dataIndex = searchInLinkedList(bucketIndex, key);

         if(dataIndex == -1) {
            buckets[bucketIndex].add(new Node(key, value));
            n++;
         } else {
            Node node = buckets[bucketIndex].get(dataIndex);
            node.value = value;
         }

         double lambda = (double) n/N;
         if(lambda > 2.0) {
            reHash();
         }
      }

      public boolean containsKey(K key) {
         int bucketIndex = searchBucketIndex(key);
         int dataIndex = searchInLinkedList(bucketIndex, key);

         if(dataIndex == -1) return false;
         else return true;
      }

      public V remove(K key) {
         int bucketIndex = searchBucketIndex(key);
         int dataIndex = searchInLinkedList(bucketIndex, key);

         if(dataIndex == -1) return null;
         else {
            Node node = buckets[bucketIndex].remove(dataIndex);
            n--;
            return node.value;
         }
      }

      public V get(K key) {
         int bucketIndex = searchBucketIndex(key);
         int dataIndex = searchInLinkedList(bucketIndex, key);

         if(dataIndex == -1) return null;
         else {
            Node node = buckets[bucketIndex].get(dataIndex);
            return node.value;
         }
      }

      public boolean isEmpty() {
         return n == 0;
      }
   }
}
