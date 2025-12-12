import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFivePartOne {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("realInputDay5.txt"));
            ArrayList<String> ranges = new ArrayList<>();
            ArrayList<Long> available =  new ArrayList<>();
            while(sc.hasNextLine())
            {
                String currentRange = sc.nextLine();
                if(!currentRange.contains("-"))
                {
                    break;
                }
                else
                {
                    ranges.add(currentRange);
                }
            }
            while(sc.hasNextLine())
            {
                available.add(Long.parseLong(sc.nextLine()));
            }
            ArrayList<Boolean> availableRange = new ArrayList<>(available.size());
            for(long i = 0; i < available.size(); i++)
            {
                availableRange.add(false);
            }
            for(int i = 0; i < ranges.size(); i++)
            {
                long lower = Long.parseLong(ranges.get(i).substring(0, ranges.get(i).indexOf("-")));
                long upper = Long.parseLong(ranges.get(i).substring(ranges.get(i).indexOf("-") + 1));
                for(int j = 0; j < available.size(); j++)
                {
                    boolean inRange = false;
                    if(lower <= available.get(j) && available.get(j) <= upper)
                    {
                        inRange = true;
                    }
                    if(!availableRange.get(j) && inRange)
                    {
                        availableRange.set(j, true);
                    }
                }
            }
            int freshCount = 0;
            for(Boolean b : availableRange)
            {
                if(b)
                {
                    freshCount++;
                }
            }
            System.out.println(freshCount);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
