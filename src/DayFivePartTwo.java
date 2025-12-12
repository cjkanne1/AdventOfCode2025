import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
                for(int j = 0; j < lowers.size(); j++)
                {
                    //TODO: fix faulty logic
                    if(lowers.get(j) < uppers.get(i) && lowers.get(j) > lowers.get(i))
                    {
                        System.out.println(lowers.get(i) + " < " + lowers.get(j) + " < " + uppers.get(i));
                        System.out.println(lowers.get(j) + " -> " + (uppers.get(i) + 1));
                        lowers.set(j, uppers.get(i) + 1);
                    }
                    else if(uppers.get(j) < uppers.get(i) && uppers.get(j) > lowers.get(i))
                    {
                        System.out.println(uppers.get(i) + " > " + uppers.get(j) + " > " + lowers.get(i));
                        System.out.println(uppers.get(j) + " -> " + (lowers.get(i) + 1));
                        uppers.set(j, lowers.get(i) + 1);
                    }
                }
            }
            for(int i = 0; i < lowers.size(); i++)
            {
                for(int j = 0; j < lowers.size(); j++) {
                    if (lowers.get(i) > lowers.get(j) && uppers.get(i) < uppers.get(j)) {
                        System.out.println("Remove " + lowers.get(i) + " - " + uppers.get(i));
                        lowers.remove(i);
                        uppers.remove(i);
                        j--;
                        i--;
                    }
                }
            }
            ArrayList<String> expandedRanges = new ArrayList<>();
            for(int i = 0; i < lowers.size(); i++)
            {
                expandedRanges.add(lowers.get(i) + "-" + uppers.get(i));
            }
            System.out.println("expandedRanges");
            for(int i = 0; i < expandedRanges.size(); i++)
            {
                System.out.println(expandedRanges.get(i));
            }
            long freshCount = 0;
            for (int i = 0; i < expandedRanges.size(); i++) {
                long lower = Long.parseLong(expandedRanges.get(i).substring(0, expandedRanges.get(i).indexOf("-")));
                long upper = Long.parseLong(expandedRanges.get(i).substring(expandedRanges.get(i).indexOf("-") + 1));
                freshCount += (upper - lower + 1);
                System.out.println("Count: " + freshCount);
            }
            System.out.println(freshCount);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
}
