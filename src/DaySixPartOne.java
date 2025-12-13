import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DaySixPartOne {
    public static void main(String[] args)
    {
        try {
            Scanner sc = new Scanner(new File("realInputDay6.txt"));
            ArrayList<String> lines = new ArrayList<>();
            while (sc.hasNext())
            {
                lines.add(sc.nextLine());
            }
            ArrayList<ArrayList<Long>> rows = new ArrayList<>();
            for(int i = 0; i < lines.size() - 1; i++)
            {
                ArrayList<Long> row = new ArrayList<>();
                Scanner rowReader = new Scanner(lines.get(i));
                while(rowReader.hasNext())
                {
                    row.add(rowReader.nextLong());
                }
                rows.add(row);
            }
            ArrayList<Character> operations = new ArrayList<>();
            for(int i = 0; i < lines.getLast().length(); i++)
            {
                if(lines.getLast().charAt(i) != ' ')
                {
                    operations.add(lines.getLast().charAt(i));
                }
            }
            System.out.println(rows.get(0).size() == operations.size());
            ArrayList<Long> results = new ArrayList<>();
            for(int i = 0; i < operations.size(); i++)
            {
                long result = 0;
                if(operations.get(i) == '+')
                {
                    for(int j = 0; j < rows.size(); j++)
                    {
                        result += rows.get(j).get(i);
                    }
                }
                else if(operations.get(i) == '-')
                {
                    for(int j = 0; j < rows.size(); j++)
                    {
                        result -= rows.get(j).get(i);
                    }
                }
                else if(operations.get(i) == '*')
                {
                    result = 1;
                    for(int j = 0; j < rows.size(); j++)
                    {
                        result *= rows.get(j).get(i);
                    }
                }
                results.add(result);
            }
            System.out.println(results);
            long grandTotal = 0;
            for(Long l : results)
            {
                grandTotal += l;
            }
            System.out.println(grandTotal);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
}
