import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static class LRUCache {

        class Node {
            int key, value;
            Node prev, next;

            Node(int k, int v) {
                key = k;
                value = v;
            }
        }

        private int capacity;
        private HashMap<Integer, Node> map;
        private Node head, tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();

            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            remove(node);
            addToFront(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
                remove(node);
                addToFront(node);
            } else {
                if (map.size() == capacity) {
                    Node lru = tail.prev;
                    remove(lru);
                    map.remove(lru.key);
                }
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addToFront(newNode);
            }
        }
        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        private void addToFront(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        // Display cache from MRU to LRU
        public void displayCache() {
            Node curr = head.next;
            System.out.print("Cache (MRU → LRU): ");
            while (curr != tail) {
                System.out.print("[" + curr.key + ":" + curr.value + "] ");
                curr = curr.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter cache capacity: ");
        int capacity = sc.nextInt();

        LRUCache cache = new LRUCache(capacity);

        while (true) {
            System.out.println("\n1. Put");
            System.out.println("2. Get");
            System.out.println("3. Display Cache");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter key: ");
                int key = sc.nextInt();
                System.out.print("Enter value: ");
                int value = sc.nextInt();
                cache.put(key, value);
                System.out.println("Inserted (" + key + ", " + value + ")");
            } 
            else if (choice == 2) {
                System.out.print("Enter key: ");
                int key = sc.nextInt();
                int result = cache.get(key);
                if (result == -1) {
                    System.out.println("Key not found");
                } else {
                    System.out.println("Value = " + result);
                }
            } 
            else if (choice == 3) {
                cache.displayCache();
            } 
            else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } 
            else {
                System.out.println("Invalid choice");
            }
        }

        sc.close();
    }
}
