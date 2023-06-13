import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
    public static boolean containsDuplicate(int[] array) {
        Arrays.sort(array);
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == array[i + 1]) {
                return true;
            }
        }
        return false;
    }
    // T.C: O(n*log(n)), S.C: O(1)
    public static int[] twoNumberSum(int[] array, int target) {
        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int currSum = array[left] + array[right];
            if (currSum == target) {
                return new int[]{array[left], array[right]};
            } else if (currSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }
    // T.C: O(n), S.C: O(n)
    public static int[] twoNumberSum2(int[] array, int target) {
        List<Integer> myList = new ArrayList<>();
        for(int i = 0; i < array.length; i++){
            int y = target - array[i];
            if(myList.contains(y)){
                return new int[]{y, array[i]};
            }
            myList.add(array[i]);
        }
        return new int[0];
    }

    public static String isPalindrome(String word) {
        if (word == null || word.length() == 0) {
            return "No";
        }
        word = word.toLowerCase();
        for (int i = 0; i <= word.length() / 2; i++) {
            char start = word.charAt(i);
            char end = word.charAt(word.length() - 1 - i);

            if (start != end) {
                return "No";
            }
        }
        return "Yes";

    }
    // T.C: O(n*log(n)) S.C: O(n)
    // T.C has n*log(n) because we are using Arrays.sort method
    public static int[] sortedSquaredArray(int[] array){
        int[] sortedSquaredArray = new int[array.length];
        for(int i = 0; i < array.length; i++){
            int value = array[i];
            sortedSquaredArray[i] = value*value;
        }
        Arrays.sort(sortedSquaredArray);
        return sortedSquaredArray;
    }
    // T.C: O(n), S.C: O(n)
    public static int[] sortedSquaredArrayOptimal(int[] array){
        int[] sortedArray = new int[array.length];
        int start = 0;
        int end = array.length-1;
        for(int i = array.length-1; i >= 0; i--){
            int startValue = array[start];
            int endValue = array[end];
            if(Math.abs(startValue) > Math.abs(endValue)){
                sortedArray[i] = startValue*startValue;
                start++;
            }else{
                sortedArray[i] = endValue*endValue;
                end--;
            }
        }
        return sortedArray;
    }
    // Valid Parenthesis
    // T.C: O(n) && S.C: O(n)
    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for(char ch: s.toCharArray()){
            if(ch == '{' || ch == '[' || ch == '('){
               stack.add(ch);
            }else{
                if(ch == '}'){
                    if(stack.isEmpty() || stack.pop() != '{'){
                        return false;
                    }
                }if(ch == ']'){
                    if(stack.isEmpty() || stack.pop() != ']'){
                        return false;
                    }
                }if(ch == ')'){
                    if(stack.isEmpty() || stack.pop() != '('){
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    //Minimum add to make parenthesis valid
    public int minAddToMakeValid(String s){
        Stack<Character> stack = new Stack<>();
        for(char ch: s.toCharArray()){
            if(ch == ')'){
                if(!stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                }else{
                    stack.push(ch);
                }
            }else{
                stack.push(ch);
            }
        }
        return stack.size();
    }
    //Find Peak in Mountain Array
    public int peakInMountain(int[] arr){
        int start = 0;
        int end = arr.length-1;
        while(start < end){
            int mid = start + (end-start)/2;
            if(arr[mid] > arr[mid+1]){
                end = mid;
            }else{
                start = mid+1;
            }
        }
        return start;
    }
    //Longest Common Subsequence
    public static List<Character> LongestCommonSubsequence(String str1, String str2){
        ArrayList<Character> list = new ArrayList<>();

        String subsequence = LCS(str1, str2);
        for(int i = 0; i < subsequence.length(); i++){
            list.add(subsequence.charAt(i));
        }
        return list;
    }

    // T.C: O(n*m * min(n*m)) && S.C:O(n*m * min(n*m))
    private static String LCS(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        String[][] dp = new String[m+1][n+1];
        for(int i = 0; i < m+1; i++){
            for(int j = 0; j < n+1; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = "";
                }else if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + str1.charAt(i-1);
                }else{
                    dp[i][j] = dp[i-1][j].length() > dp[i][j-1].length() ? dp[i-1][j]: dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }
    //Iterating from the start:Top-Down Approach
    //TC: O(m*n)
    // Refer Abdul Bari's lecture on this
    public int lenghthOfLongestCommonSubsequence(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {

                char c1 = s1.charAt(i - 1);
                char c2 = s2.charAt(j - 1);

                if (c1 == c2) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
    // Iterating from bottom AKA Bottom-Up Approach
    public int longestCommonSubsequence(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {

                char c1 = s1.charAt(i - 1);
                char c2 = s2.charAt(j - 1);

                if (c1 == c2) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
    //Search Suggestion System(Leetcode)
    public List<List<String>> suggestedProducts(String[] products, String search) {
        Arrays.sort(products); // Sort the products array lexicographically
        List<List<String>> suggestedProducts = new ArrayList<>(); // List to store the suggested products

        StringBuilder prefix = new StringBuilder();
        for (char c : search.toCharArray()) {
            prefix.append(c);
            List<String> suggestions = new ArrayList<>();
            int count = 0;
            for (String product : products) {
                if (product.startsWith(prefix.toString())) {
                    suggestions.add(product);
                    count++;
                    if (count == 3) {
                        break;
                    }
                }
            }
            suggestedProducts.add(suggestions);
        }

        return suggestedProducts;
    }
    // Alternate solution with alternate parameters.
        public static List<List<String>> getProductSuggestions(List<String> products, String search) {
            Collections.sort(products); // Sort the products list lexicographically
            List<List<String>> suggestedProducts = new ArrayList<>(); // List to store the suggested products

            StringBuilder prefix = new StringBuilder();
            for (char c : search.toCharArray()) {
                prefix.append(c);
                List<String> suggestions = new ArrayList<>();
                int count = 0;
                for (String product : products) {
                    if (product.startsWith(prefix.toString())) {
                        suggestions.add(product);
                        count++;
                        if (count == 3) {
                            break;
                        }
                    }
                }
                suggestedProducts.add(suggestions);
            }
            return suggestedProducts;
        }
        //Alternate and easy to understand solution
       //T.C: O(m * n * log k), SC: O(m)
       public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        PriorityQueue<String> priority = new PriorityQueue<>(3, (s1, s2) -> s1.compareTo(s2));
        List<List<String>> list = new ArrayList<>();

        for (int i = 1; i <= searchWord.length(); i++) {
            String temp = searchWord.substring(0, i);
            for (String s : products) {
                if (s.startsWith(temp)) {
                    priority.offer(s);
                }
            }
            List<String> temp_list = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                if (priority.peek() != null) {
                    temp_list.add(priority.poll());
                }
            }
            priority.clear();
            list.add(temp_list);
        }
        return list;
    }
    // This method returns the most common number in three arrays
    private int[] commonNumber(int[] num1, int[] num2, int[] num3){
        return new int[]{-1,-1};
    }

    // Space & Time complexity of above code is explained below.
    /*The time and space complexity of the provided code are as follows:

Time Complexity:

The outer for loop runs for searchWord.length() iterations, which is O(m), where m is the length of the searchWord.
Within the outer loop, there is an inner for-each loop that iterates over the products array. In the worst case, it iterates over all the products, so its time complexity is O(n), where n is the number of products.
Inside the inner loop, the priority.offer() operation is called for each product that matches the substring. The offer() operation in a PriorityQueue has a time complexity of O(log k), where k is the current size of the queue (maximum of 3 in this case).
The second inner loop runs for a fixed number of iterations (3 in this case), so its time complexity is O(1).
Overall, the time complexity of the code is O(m * n * log k), where m is the length of the search word, n is the number of products, and k is the maximum size of the priority queue (3 in this case).
Space Complexity:

The space complexity is determined by the additional data structures used in the code.
The PriorityQueue priority has a maximum size of 3, so its space complexity is O(3) or O(1).
The List<List<String>> list will contain at most m lists, each of which can have a maximum size of 3. So, the space complexity of list is O(m * 3) or O(m).
Other temporary variables like temp and temp_list have space complexity proportional to the length of the search word, which is O(m).
Overall, the space complexity of the code is O(m) since it dominates the space usage.
In summary, the time complexity of the code is O(m * n * log k), and the space complexity is O(m).
*/
        /* Testing for above code
        public static void main(String[] args) {
            List<String> products = new ArrayList<>();
            products.add("carpet");
            products.add("car");
            products.add("camera");
            products.add("crate");
            String search = "camera";
            List<List<String>> suggestedProducts = getProductSuggestions(products, search);
            System.out.println(suggestedProducts);
        }*/

}
