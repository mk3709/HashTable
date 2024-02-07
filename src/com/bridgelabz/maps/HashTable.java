package com.bridgelabz.maps;

import java.util.HashMap;
import java.util.LinkedList;

// MyMapNode class to represent a key-value pair
class MyMapNode {
    String key;
    Integer value;

    public MyMapNode(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
}

// HashTable class with LinkedList implementation
class MyHashTable {
    private final int size;
    private final LinkedList<MyMapNode>[] buckets;

    public MyHashTable(int size) {
        this.size = size;
        this.buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    // Hash function to determine the index in the array
    private int getBucketIndex(String key) {
        int hashCode = key.hashCode();
        return hashCode % size;
    }

    // Insert or update the frequency of a word in the hash table
    public void insertOrUpdate(String key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<MyMapNode> bucket = buckets[bucketIndex];

        // Check if the word is already present in the linked list
        for (MyMapNode node : bucket) {
            if (node.key.equals(key)) {
                // Word found, update frequency
                node.value++;
                return;
            }
        }

        // If word not found, add a new node to the linked list
        bucket.add(new MyMapNode(key, 1));
    }

    // Get the frequency of a word in the hash table
    public Integer getFrequency(String key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<MyMapNode> bucket = buckets[bucketIndex];

        // Check if the word is present in the linked list
        for (MyMapNode node : bucket) {
            if (node.key.equals(key)) {
                // Word found, return its frequency
                return node.value;
            }
        }

        // If word not found, return 0
        return 0;
    }
}

public class HashTable {
    public static void main(String[] args) {
        String sentence = "To be or not to be";
        String[] words = sentence.split("\\s+");

        // Create a hash table
        MyHashTable hashTable = new MyHashTable(10);

        // Insert words into the hash table
        for (String word : words) {
            hashTable.insertOrUpdate(word.toLowerCase()); // Convert to lowercase for case-insensitivity
        }

        // Print the frequency of each word
        for (String word : words) {
            System.out.println("Frequency of '" + word + "': " + hashTable.getFrequency(word.toLowerCase()));
        }
    }
}
