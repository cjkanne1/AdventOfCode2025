import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayOnePartTwo {
    private int dialPoint;
    private int zeroCount = 0;
    private ArrayList<String> spins =  new ArrayList<>();

    public DayOnePartTwo(int dp, String fileName)
    {
        dialPoint = dp;
        try{
            Scanner input = new Scanner(new File(fileName));
            while(input.hasNextLine())
            {
                spins.add(input.nextLine());
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public static void main(String[] args)
    {
        DayOnePartTwo d = new DayOnePartTwo(50, "realInputDay1.txt");
        for(String s : d.spins)
        {

            int spin = Integer.parseInt(s.substring(1));
            if(s.charAt(0) == 'L')
            {
                d.spinLeft(spin);
            }
            else
            {
                d.spinRight(spin);
            }
            System.out.println("DialPoint: " + d.dialPoint);
            System.out.println("Zeroes: " + d.zeroCount);
        }
    }

    public void spinLeft(int spin)
    {
        while(spin > 0)
        {
            dialPoint--;
            if(dialPoint < 0)
            {
                dialPoint = 100 + dialPoint;
            }
            if(dialPoint == 100)
            {
                dialPoint = 0;
            }
            if(dialPoint == 0)
            {
                zeroCount++;
            }
            spin--;
        }
    }
    public void spinRight(int spin)
    {
        while(spin > 0)
        {
            dialPoint++;
            if(dialPoint > 100)
            {
                dialPoint = dialPoint - 100;
            }
            if(dialPoint == 100)
            {
                dialPoint = 0;
            }
            if(dialPoint == 0)
            {
                zeroCount++;
            }
            spin--;
        }
    }
}

