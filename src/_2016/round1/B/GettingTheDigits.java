package _2016.round1.B;

import java.io.*;
import java.util.*;

/**
 * Problem
 * <p>
 * You just made a new friend at an international puzzle conference, and you asked for a way to keep in touch. You
 * found the following note slipped under your hotel room door the next day:
 * <p>
 * "Salutations, new friend! I have replaced every digit of my phone number with its spelled-out uppercase English
 * representation ("ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE" for the digits 0
 * through 9, in that order), and then reordered all of those letters in some way to produce a string S. It's up to
 * you to use S to figure out how many digits are in my phone number and what those digits are, but I will tell you
 * that my phone number consists of those digits in nondecreasing order. Give me a call... if you can!"
 * <p>
 * You would to like to call your friend to tell him that this is an obnoxious way to give someone a phone number,
 * but you need the phone number to do that! What is it?
 * Input
 * <p>
 * The first line of the input gives the number of test cases, T. T test cases follow. Each consists of one line with
 * a string S of uppercase English letters.
 * Output
 * <p>
 * For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y
 * is a string of digits: the phone number.
 * Limits
 * <p>
 * 1 ≤ T ≤ 100.
 * A unique answer is guaranteed to exist.
 * Small dataset
 * <p>
 * 3 ≤ length of S ≤ 20.
 * Large dataset
 * <p>
 * 3 ≤ length of S ≤ 2000.
 * Sample
 * <p>
 * Input
 * <p>
 * Output
 * <p>
 * <p>
 * 4
 * OZONETOWER
 * WEIGHFOXTOURIST
 * OURNEONFOE
 * ETHER
 * <p>
 * <p>
 * <p>
 * Case #1: 012
 * Case #2: 2468
 * Case #3: 114
 * Case #4: 3
 */
public class GettingTheDigits {
    public static void getDigits(String s, BufferedWriter bw) throws IOException {
        if (s == null || s.length() == 0)
            return;

        Map<Integer, String> intToStr = new HashMap<>();
        intToStr.put(0, "ZERO");
        intToStr.put(1, "ONE");
        intToStr.put(2, "TWO");
        intToStr.put(3, "THREE");
        intToStr.put(4, "FOUR");
        intToStr.put(5, "FIVE");
        intToStr.put(6, "SIX");
        intToStr.put(7, "SEVEN");
        intToStr.put(8, "EIGHT");
        intToStr.put(9, "NINE");

        Map<Character, Integer> specialMapping = new LinkedHashMap<>();
        specialMapping.put('Z', 0);
        specialMapping.put('W', 2);
        specialMapping.put('X', 6);
        specialMapping.put('V', 7);
        specialMapping.put('U', 4);
        specialMapping.put('G', 8);
        specialMapping.put('H', 3);
        specialMapping.put('O', 1);
        specialMapping.put('F', 5);
        specialMapping.put('I', 9);

        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'A']++;
        }
        List<Character> keyList = new ArrayList<>(specialMapping.keySet());

        for (int i = 0; i < keyList.size(); i++) {
            char c = keyList.get(i);
            if (s.indexOf(c) != -1 && count[c - 'A'] > 0) {
                int integerForChar = specialMapping.get(c);

                String strForInt = intToStr.get(integerForChar);
                for (int j = 0; j < strForInt.length(); j++) {
                    count[strForInt.charAt(j) - 'A']--;
                }
                sb.append(integerForChar);
                i = -1;
            }
        }

        char[] result = sb.toString().toCharArray();
        Arrays.sort(result);
        bw.write(String.valueOf(result)+"\n");
    }

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(args[1] + "_output.txt"));

        int numberOfTests = Integer.parseInt(br.readLine());
        for (int i = 1; i <= numberOfTests; i++) {
            String input = br.readLine();
            bw.write("Case #" + i + ": ");
            getDigits(input, bw);
        }

        br.close();
        bw.close();
    }
}
