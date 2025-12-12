import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class DayFivePartTwo {
    public static void main(String[] args)
    {
        try {
            Scanner sc = new Scanner(new File("testInputDay5.txt"));
            ArrayList<String> ranges = new ArrayList<>();
            while (sc.hasNextLine()) {
                String currentRange = sc.nextLine();
                if (!currentRange.contains("-")) {
                    break;
                } else {
                    ranges.add(currentRange);
                }
            }
            ArrayList<Long> lowers = new ArrayList<>();
            ArrayList<Long> uppers = new ArrayList<>();
            for(String range : ranges)
            {
                lowers.add(Long.parseLong(range.substring(0, range.indexOf("-"))));
                uppers.add(Long.parseLong(range.substring(range.indexOf("-") + 1)));
            }
            for(int i = 0; i < lowers.size(); i++)
            {
                System.out.println(lowers.get(i) + " - " + uppers.get(i));
            }
            for(int i = 0; i < ranges.size(); i++)
            {
                System.out.println(lowers.get(i) + " - " + uppers.get(i));
                long currentUpper = uppers.get(i);
                for(int j = 0; j < lowers.size(); j++)
                {
                    //TODO: fix faulty logic
                    if(lowers.get(j) < currentUpper && lowers.get(j) > lowers.get(i))
                    {
                        lowers.set(j, lowers.get(j) + (currentUpper - lowers.get(j) + 1));
                    }
                }
            }
            ArrayList<String> expandedRanges = new ArrayList<>();
            for(int i = 0; i < ranges.size(); i++)
            {
                expandedRanges.add(lowers.get(i) + "-" + uppers.get(i));
            }
            for(int i = 0; i < expandedRanges.size(); i++)
            {
                System.out.println(expandedRanges.get(i));
            }
//            if(true)
//            {
//                return;
//            }
//            else {
                long freshCount = 0;
                for (int i = 0; i < ranges.size(); i++) {
                    long lower = Long.parseLong(ranges.get(i).substring(0, ranges.get(i).indexOf("-")));
                    long upper = Long.parseLong(ranges.get(i).substring(ranges.get(i).indexOf("-") + 1));
                    freshCount += (upper - lower + 1);
                    System.out.println("Size: " + freshCount);
                }
                System.out.println(freshCount);
            //}
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
}
