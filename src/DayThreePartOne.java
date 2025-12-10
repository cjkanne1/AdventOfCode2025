import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThreePartOne {
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
            ArrayList<Integer> joltages = new ArrayList<>();
            for(String s :  batteryBanks)
            {
                int maxTens = Integer.parseInt(s.substring(0, 1));
                int maxTenIndex = 0;
                //first index to second to last index
                for(int i = 0; i < s.length() - 1; i++)
                {
                    int digit = Integer.parseInt(s.substring(i, i + 1));
                    if(digit > maxTens)
                    {
                        maxTens = digit;
                        maxTenIndex = i;
                    }
                }
                int maxOnes =  Integer.parseInt(s.substring(maxTenIndex + 1, maxTenIndex + 2));
                //index after the max to last index
                for(int i = maxTenIndex + 1; i < s.length(); i++)
                {
                    int digit = Integer.parseInt(s.substring(i, i + 1));
                    if(digit > maxOnes)
                    {
                        maxOnes = digit;
                    }
                }
                int joltage = (maxTens * 10) + maxOnes;
                System.out.println(joltage);
                joltages.add(joltage);
            }
            int cumulativeJoltage = 0;
            for(int i = 0; i < joltages.size(); i++)
            {
                cumulativeJoltage += joltages.get(i);
            }
            System.out.print(joltages.getFirst());
            for(int i = 1; i < joltages.size(); i++)
            {
                System.out.print(" + " + joltages.get(i));
            }
            System.out.print(" = ");
            System.out.println(cumulativeJoltage);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
}
