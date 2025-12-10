import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayOnePartOne {
    public static void main(String[] args) {
        try {
            Scanner realInput = new Scanner(new File("testInputDay1.txt"));
            int dialPoint = 50;
            int zeroCount = 0;
            ArrayList<String> spins = new ArrayList<>();
            while(realInput.hasNextLine())
            {
                spins.add(realInput.nextLine());
            }
            for(int i = 0; i < spins.size(); i++)
            {
                if(dialPoint == 0)
                {
                    zeroCount++;
                }
                if(spins.get(i).charAt(0) == 'L')
                {
                    int spin = Integer.parseInt(spins.get(i).substring(1));
                    dialPoint = spinLeft(dialPoint, spin);
                }
                else
                {
                    int spin = Integer.parseInt(spins.get(i).substring(1));
                    dialPoint = spinRight(dialPoint, spin);
                }
                System.out.println("DialPoint: " + dialPoint);
                System.out.println("Zeroes: " + zeroCount);
            }
            System.out.println(dialPoint);
            System.out.println(zeroCount);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static int spinLeft(int dialPoint, int spin)
    {
        if(spin > 100)
        {
            spin %= 100;
        }
        dialPoint -= spin;
        if(dialPoint < 0)
        {
            dialPoint = 100 + dialPoint;
        }
        return dialPoint;
    }

    public static int spinRight(int dialPoint, int spin)
    {
        if(spin > 100)
        {
            spin %= 100;
            System.out.println("Mod spin: right " + spin);
        }
        dialPoint += spin;
        if(dialPoint > 100)
        {
            dialPoint = dialPoint - 100;
        }
        else if(dialPoint == 100)
        {
            dialPoint = 0;
        }
        return dialPoint;
    }
}
