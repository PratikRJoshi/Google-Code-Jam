package _2016.jam.code.google;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  Bleatrix Trotter the sheep has devised a strategy that helps her fall asleep faster. First, she picks a number N. Then she starts naming N, 2 × N, 3 × N, and so on. Whenever she names a number, she thinks about all of the digits in that number. She keeps track of which digits (0, 1, 2, 3, 4, 5, 6, 7, 8, and 9) she has seen at least once so far as part of any number she has named. Once she has seen each of the ten digits at least once, she will fall asleep.

 Bleatrix must start with N and must always name (i + 1) × N directly after i × N. For example, suppose that Bleatrix picks N = 1692. She would count as follows:

 N = 1692. Now she has seen the digits 1, 2, 6, and 9.
 2N = 3384. Now she has seen the digits 1, 2, 3, 4, 6, 8, and 9.
 3N = 5076. Now she has seen all ten digits, and falls asleep.

 What is the last number that she will name before falling asleep? If she will count forever, print INSOMNIA instead.

 Input

 The first line of the input gives the number of test cases, T. T test cases follow. Each consists of one line with a single integer N, the number Bleatrix has chosen.
 Output

 For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y is the last number that Bleatrix will name before falling asleep, according to the rules described in the statement.

 */
public class GCodeJam16_Q1 {
    public static int lastSheep(int n) {
        Map<Integer, Boolean> visitedNumbers = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        int temp = 1, lastNumber = 0;
        while (true){
            lastNumber = temp * n;
            if (visitedNumbers.containsKey(lastNumber)){
                System.out.println("INSOMNIA");
                break;
            } else {
                visitedNumbers.put(lastNumber, true);
                getDigits(lastNumber, set);
                if (set.size() == 10){
                    return lastNumber;
                }
            }
            temp++;
        }
        return Integer.MAX_VALUE;
    }

    public static void getDigits(int number, Set<Integer> set) {
        while (number / 10 != 0){
            set.add(number % 10);
            number /= 10;
        }
        set.add(number);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String resourcesDir = args[1];
        String outputFile = String.valueOf(MethodHandles.lookup().lookupClass()
                .getCanonicalName());
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(resourcesDir+outputFile+".txt")));

        int nTests = Integer.parseInt(br.readLine());
        for (int i = 1; i <= nTests; i++){
            int input = Integer.parseInt(br.readLine());
            int result = lastSheep(input);
            if (result == Integer.MAX_VALUE){
                bw.write("Case #" + i + ": " + "INSOMNIA" + "\n");
            } else {
                bw.write("Case #" + i + ": " + result + "\n");
            }
        }
        bw.close();
    }
}
