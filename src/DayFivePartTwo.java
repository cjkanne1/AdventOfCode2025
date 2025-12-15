import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFivePartTwo {
    public static void main(String[] args)
    {
        try {
            Scanner sc = new Scanner(new File("realInputDay5.txt"));
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

            ArrayList<Long> sortedLowers = new ArrayList<>(lowers.size());
            ArrayList<Long> sortedUppers = new ArrayList<>(uppers.size());
            sortedLowers.add(lowers.getFirst());
            sortedUppers.add(uppers.getFirst());
            for(int i = 1; i < lowers.size(); i++)
            {
                if(lowers.get(i) > sortedLowers.getLast())
                {
                    sortedLowers.add(lowers.get(i));
                    sortedUppers.add(uppers.get(i));
                }
                else
                {
                    for(int j = 0; j < sortedLowers.size(); j++)
                    {
                        if(lowers.get(i) <  sortedLowers.get(j))
                        {
                            sortedLowers.add(j, lowers.get(i));
                            sortedUppers.add(j, uppers.get(i));
                            break;
                        }
                    }
                }
            }
            ArrayList<String> mergedRanges = new ArrayList<>();
            System.out.println(sortedLowers);
            System.out.println(sortedUppers);
            for(int i = 0; i < sortedLowers.size() - 1; i++)
            {
                System.out.println(sortedLowers.get(i + 1));
                System.out.println(sortedUppers.get(i));
                if(existsOverlap(i, sortedLowers, sortedUppers))
                {
                    mergedRanges.add(mergeUp(i, sortedLowers, sortedUppers));
                    System.out.println(mergedRanges.getLast());
                    sortedLowers.remove(i + 1);
                    sortedUppers.remove(i);
                    i--;
                }
                else
                {
                    mergedRanges.add(sortedLowers.get(i) + "-" + sortedUppers.get(i));
                    System.out.println(mergedRanges.getLast());
                }
                System.out.println(sortedLowers);
                System.out.println(sortedUppers);
            }
            System.out.println(mergedRanges);
            mergedRanges = removeAdditionalOverlap(mergedRanges);
            System.out.println(mergedRanges);
            long freshCount = 0;
            for(int i  = 0; i < mergedRanges.size(); i++)
            {
                long lower = Long.parseLong(mergedRanges.get(i).substring(0, mergedRanges.get(i).indexOf("-")));
                long upper = Long.parseLong(mergedRanges.get(i).substring(mergedRanges.get(i).indexOf("-") + 1));
                freshCount += (upper - lower) + 1;
            }
//            for(int i = 0; i < lowers.size(); i++)
//            {
//                expandedRanges.add(lowers.get(i) + "-" + uppers.get(i));
//            }
//            System.out.println("expandedRanges");
//            for(int i = 0; i < expandedRanges.size(); i++)
//            {
//                System.out.println(expandedRanges.get(i));
//            }
//            long freshCount = 0;
//            for (int i = 0; i < expandedRanges.size(); i++) {
//                long lower = Long.parseLong(expandedRanges.get(i).substring(0, expandedRanges.get(i).indexOf("-")));
//                long upper = Long.parseLong(expandedRanges.get(i).substring(expandedRanges.get(i).indexOf("-") + 1));
//                freshCount += (upper - lower + 1);
//                System.out.println("Count: " + freshCount);
//            }
            System.out.println(freshCount);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }

    public static boolean existsOverlap(int index, ArrayList<Long> lower, ArrayList<Long> upper)
    {
        System.out.println(lower.get(index + 1) + " and " + upper.get(index));
        if(lower.get(index + 1) < upper.get(index))
        {
            System.out.println("Yes");
            return true;
        }
        return false;
    }

    public static String mergeUp(int index, ArrayList<Long> lower, ArrayList<Long> upper)
    {
        return String.valueOf(lower.get(index)) + "-" +  String.valueOf(upper.get(index + 1));
    }

    public static ArrayList<String> removeAdditionalOverlap(ArrayList<String> ranges)
    {
        for(int i = 0; i < ranges.size() - 1; i++)
        {
            String current = ranges.get(i);
            String next = ranges.get(i + 1);
            if(current.substring(0, current.indexOf("-")).equals(next.substring(0, current.indexOf("-"))))
            {
                ranges.remove(i);
                i--;
            }
        }
        return ranges;
    }
}
