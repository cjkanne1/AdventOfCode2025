import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThreePartTwo {
    public static void main(String[] args)
    {
        try{
            //Scanner sc =  new Scanner(new File("testInputDay3.txt"));
            Scanner sc =  new Scanner(new File("realInputDay3.txt"));
            ArrayList<String> batteryBanks =  new ArrayList<String>();
            while(sc.hasNext())
            {
                batteryBanks.add(sc.nextLine());
            }
            ArrayList<Long> joltages = new ArrayList<>();
            int counter = 0;
            for(String s : batteryBanks)
            {
                counter++;
                System.out.println("Round: " + counter + " " + s);
                int nextMax = returnMax(s, 11);
                int index = findValue(nextMax, s);
                String working = s;
                String joltage = String.valueOf(nextMax);
                for(int batt = 0; batt < 11; batt ++) {
                    working = working.substring(index + 1);
                    nextMax = returnMax(working, 10 - batt);
                    index = findValue(nextMax, working);
                    joltage += String.valueOf(nextMax);
                }
                System.out.println(joltage);
                joltages.add(Long.parseLong(joltage));
            }
            long cumulativeJoltage = 0;
            for(Long jolt : joltages)
            {
                cumulativeJoltage += jolt;
            }
            System.out.println(cumulativeJoltage);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }

    public static int returnMax(String s, int upperBuffer)
    {
        int max = 0;
        String manip = s.substring(0, s.length() - upperBuffer);
        System.out.println("Buffer: " + upperBuffer);
        for(int i = 0; i < manip.length(); i++)
        {
            if(Integer.parseInt(manip.substring(i, i + 1)) > max)
            {
                max = Integer.parseInt(manip.substring(i, i + 1));
            }
        }
        System.out.println("Max: " + max);
        return max;
    }

    public static int findValue(int integer, String s) {
        for (int i = 0; i < s.length(); i++) {
            if(Integer.parseInt(s.substring(i, i + 1)) == integer)
            {
                return i;
            }
        }
        return -1;
    }

    public static long returnNumMax(String s, int i)
    {
        String manipulate = s;
        if(i == 1)
        {
            return returnMax(s, i - 1);
        }
        else
        {
            return 0;
        }
    }
}
