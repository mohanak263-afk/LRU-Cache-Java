# LRU Cache Implementation in Java

This project implements a **Least Recently Used (LRU) Cache** using Java.

The cache supports `get` and `put` operations in **O(1) time** by combining a **HashMap** with a **Doubly Linked List**.


## What is an LRU Cache?

An LRU Cache removes the **least recently used** item when the cache reaches its capacity.

- Recently accessed items stay in the cache
- Items that are not used for a long time are removed first

This type of cache is commonly used in real systems to manage limited memory efficiently.


## Approach Used

- A **HashMap** is used to store key → node mappings  
  - This allows constant time access to cache entries
- A **Doubly Linked List** is used to maintain usage order  
  - Most recently used item is kept at the front  
  - Least recently used item is kept at the end

### How operations work

- **get(key)**
  - If the key exists, move the node to the front
  - Return the value
- **put(key, value)**
  - If the key already exists, update value and move it to the front
  - If the cache is full, remove the least recently used node
  - Insert the new node at the front


## Features

- O(1) time complexity for both `get` and `put`
- Proper eviction of least recently used entries
- Clean separation of cache logic and node structure
- Handles update of existing keys correctly


## Time and Space Complexity

- **Time Complexity:**  
  - `get` → O(1)  
  - `put` → O(1)

- **Space Complexity:**  
  - O(capacity)

## How to Run

1. Compile the program:
   ```bash
   javac LRUCache.java
