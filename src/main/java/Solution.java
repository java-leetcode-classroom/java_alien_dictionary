import java.util.*;

public class Solution {
  /**
   * @param words: a list of words
   * @return: a string which is correct order
   */
  public String alienOrder(String[] words) {
    // Write your code here
    int wordsLen = words.length;
    if (wordsLen == 0 || words == null) {
      return "";
    }
    HashMap<Character, Integer> inDegree = new HashMap<>();
    HashMap<Character, List<Character>> graph = new HashMap<>();
    // init graph
    for (String word: words) {
      int wordLen = word.length();
      for (int idx = 0; idx < wordLen; idx++) {
        Character ch = word.charAt(idx);
        inDegree.put(ch, 0);
        graph.put(ch, new ArrayList<>());
      }
    }
    // build graph
    for (int idx = 0; idx < wordsLen -1; idx++) {
      String word1 = words[idx];
      String word2 = words[idx+1];
      int word1Len = word1.length();
      int word2Len = word2.length();
      int minLen = Math.min(word1Len, word2Len);
      if (word1Len > word2Len && word1.startsWith(word2)) {
        return "";
      }
      for (int j = 0; j < minLen; j++) {
        char parent = word1.charAt(j);
        char child = word2.charAt(j);
        if (parent != child) {
          graph.get(parent).add(child);
          inDegree.put(child, inDegree.get(child) + 1);
          break;
        }
      }
    }
    PriorityQueue<Character> queue = new PriorityQueue<>(Comparator.comparingInt(Character::charValue));
    for (Map.Entry<Character, Integer> entry: inDegree.entrySet()) {
      if (entry.getValue() == 0){
        queue.add(entry.getKey());
      }
    }
    StringBuilder sortedOrder = new StringBuilder();
    while(queue.size() != 0) {
      Character ch = queue.poll();
      sortedOrder.append(ch);
      for (char c: graph.get(ch)) {
        inDegree.put(c, inDegree.get(c) - 1);
        if (inDegree.get(c) == 0) {
          queue.add(c);
        }
      }
    }
    if (sortedOrder.length() != inDegree.size()) {
      return "";
    }
    return sortedOrder.toString();
  }
}
