package Greedy.HuffmanCoding;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Huffman Coding Implementation in Java
You are given a set of characters along with their frequencies
of occurrence.Your task is to build a Huffman Tree and generate
Huffman codes for each character.Huffman coding is a lossless
data compression technique in which:
Characters with higher frequency are assigned shorter binary codes
Characters with lower frequency are assigned longer binary codes
The generated codes must be prefix-free (no code is a prefix of another)
 */
public class Huffman {

    // Step 1: Build frequency map
    private static Map<Character, Integer> buildFrequency(String text) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        return freq;
    }

    // Step 2: Build Huffman Tree
    private static Node buildTree(Map<Character, Integer> freq) {

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.freq));

        // push all characters into pq as leaf nodes
        for (var e : freq.entrySet()) {
            pq.add(new Node(e.getKey(), e.getValue()));
        }

        // merge two smallest till one root remains
        while (pq.size() > 1) {

            Node a = pq.poll();
            Node b = pq.poll();

            Node merged = new Node('\0', a.freq + b.freq);
            merged.left = a;
            merged.right = b;

            pq.add(merged);
        }

        return pq.poll();
    }

    // Step 3: Generate the codes by DFS traversal
    private static void generateCodes(Node root, String code, Map<Character, String> codes) {
        if (root == null) return;

        // If leaf node
        if (root.left == null && root.right == null) {
            codes.put(root.ch, code);
            return;
        }

        generateCodes(root.left, code + "0", codes);
        generateCodes(root.right, code + "1", codes);
    }

    // Step 4: Encode
    private static String encode(String text, Map<Character, String> codes) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(codes.get(c));
        }
        return sb.toString();
    }

    // Step 5: Decode
    private static String decode(String encoded, Node root) {
        StringBuilder sb = new StringBuilder();
        Node curr = root;

        for (char bit : encoded.toCharArray()) {
            curr = (bit == '0') ? curr.left : curr.right;

            // leaf reached
            if (curr.left == null && curr.right == null) {
                sb.append(curr.ch);
                curr = root;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {

        String text = "BCCABBDDAECCBBAEDDCC";  // same as in Abdul Bari’s example

        // 1. Frequencies
        Map<Character, Integer> freq = buildFrequency(text);
        System.out.println("Frequency: " + freq);

        // 2. Build Tree
        Node root = buildTree(freq);

        // 3. Build Code Table
        Map<Character, String> codes = new HashMap<>();
        generateCodes(root, "", codes);
        System.out.println("Codes: " + codes);

        // 4. Encode
        String encoded = encode(text, codes);
        System.out.println("Encoded: " + encoded);
        System.out.println("Bits: " + encoded.length());

        // 5. Decode
        String decoded = decode(encoded, root);
        System.out.println("Decoded: " + decoded);
    }
}
/*
O/p
Frequency: {A=3, B=5, C=6, D=4, E=2}
Codes: {A=011, B=10, C=11, D=00, E=010}
Encoded: 101111011101000000110101111101001101000001111
Bits: 45
Decoded: BCCABBDDAECCBBAEDDC
 */
