import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwoPartTwo {
    public static void main(String[] args) {
        try{
            //read the file
            Scanner rangeFinder = new Scanner(new File("realInputDay2.txt"));
            ArrayList<String> ranges = new ArrayList<>();
            rangeFinder.useDelimiter(",");
            ArrayList<Long> invalids = new ArrayList<>();
            while (rangeFinder.hasNext())
            {
                ranges.add(rangeFinder.next());
            }
            for (String id : ranges) {
                //define the range
                long lower = Long.parseLong(id.substring(0, id.indexOf('-')));
                long upper = Long.parseLong(id.substring(id.indexOf('-') + 1));
//                System.out.println("Lower: " + lower);
//                System.out.println("Upper: " + upper + "\n");
                //for each value in the range...
                for(long i = lower; i <= upper; i++)
                {
                    String currentValue = String.valueOf(i);
//                    System.out.println("Current value: " + i);
                    for (int j = 1; j <= currentValue.length() / 2; j++) {
                        //take a substring of a variable length
                        String sub = currentValue.substring(0, j);
//                        System.out.println("Sub: " + sub);
                        boolean dupes = false;
                        //if the length isn't divisible by the sub length, ignore it
                        if (currentValue.length() % sub.length() == 0) {
                            dupes = true;
                            //if it is, ignore it if at any point a substring of id isn't the same as sub
                            for (int k = 0; k < currentValue.length(); k += sub.length()) {
                                String chunk =  currentValue.substring(k, k + sub.length());
//                                System.out.println("Chunk: " + chunk);
                                if (!chunk.equals(sub)) {
                                    dupes = false;
//                                    System.out.println(i + " is not a dupe");
                                    break;
                                }
                            }
                        }
                        if (dupes) {
                            boolean alreadyThere = false;
                            for(Long l : invalids) {
                                if(l == i)
                                {
                                    alreadyThere = true;
                                }

                            }
                            if(!alreadyThere)
                            {
//                                System.out.println("Dupe found! " + i);
                                invalids.add(i);
                            }
                        }
                    }
                }
            }
            long invalidSum = 0;
            //add the values
            for(Long i : invalids)
            {
                invalidSum += i;
            }
            //tell me what it is
            System.out.println(invalidSum);
        }catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
}
