import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void findMaxMinChar(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println("String is empty!");
            return;
        }

        HashMap<Character, Integer> freqMap = new HashMap<>();

        // Count character frequencies
        for (char c : str.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Find max and min occurring characters
        char maxChar = str.charAt(0), minChar = str.charAt(0);
        int maxFreq = Integer.MIN_VALUE, minFreq = Integer.MAX_VALUE;

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            int count = entry.getValue();
            char character = entry.getKey();

            if (count > maxFreq) {
                maxFreq = count;
                maxChar = character;
            }
            if (count < minFreq) {
                minFreq = count;
                minChar = character;
            }
        }

        System.out.println("Max occurring character: '" + maxChar + "' appears " + maxFreq + " times");
        System.out.println("Min occurring character: '" + minChar + "' appears " + minFreq + " times");
    }

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int size = sc.nextInt();

       int arr[] = new int[size];

       for (int i=0;i<size;i++) {
           arr[i] = sc.nextInt();
       }


    }
}
