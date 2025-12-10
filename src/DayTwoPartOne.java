import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwoPartOne {

   public static void main(String[] args) {
       try{
           Scanner rangeFinder = new Scanner(new File("realInputDay2.txt"));
           ArrayList<String> ranges = new ArrayList<>();
           rangeFinder.useDelimiter(",");
           ArrayList<Long> invalids = new ArrayList<>();
           while (rangeFinder.hasNext())
           {
               ranges.add(rangeFinder.next());
           }
           for (String id : ranges) {
               long lower = Long.parseLong(id.substring(0, id.indexOf('-')));
               long upper = Long.parseLong(id.substring(id.indexOf('-') + 1));
               System.out.println("Lower: " + lower);
               System.out.println("Upper: " + upper + "\n");
               for (long j = lower; j <= upper; j++)
               {
                   String currentID = Long.toString(j);
                   if (currentID.length() % 2 == 0) {
                       String firstHalf = currentID.substring(0, currentID.length() / 2);
                       String secondHalf = currentID.substring(currentID.length() / 2);
                       if (firstHalf.equals(secondHalf)) {
                           invalids.add(j);
                       }
                   }
               }
           }
           long invalidSum = 0;
           for(Long i : invalids)
           {
               invalidSum += i;
           }
           System.out.println(invalidSum);
       }catch(FileNotFoundException e)
       {
           System.out.println("File not found");
       }
   }
}
