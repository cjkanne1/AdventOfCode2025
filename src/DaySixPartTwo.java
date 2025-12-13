import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DaySixPartTwo {
    private ArrayList<String> lines;
    private ArrayList<ArrayList<Long>> rows;
    private ArrayList<Character> operations;
    private ArrayList<Integer> oppIndices = new ArrayList<>();
    public static void main(String[] args) {
        DaySixPartTwo verticalMath = new DaySixPartTwo("testInputDay6.txt");
        ArrayList<ArrayList<String>> chunks = new ArrayList<>();
        for(int i = 0; i < verticalMath.oppIndices.size(); i++)
        {
            ArrayList<String> actual = verticalMath.chunk(verticalMath.oppIndices.get(i));
            chunks.add(actual);
        }

        for(ArrayList<String> chunk : chunks)
        {
            System.out.println(chunk);
        }
    }

    public DaySixPartTwo(String fileName)
    {
        try {
            Scanner sc = new Scanner(new File(fileName));
            lines = new ArrayList<>();
            while (sc.hasNext()) {
                lines.add(sc.nextLine());
            }
            rows = new ArrayList<>();
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
            operations = new ArrayList<>();
            for(int i = 0; i < lines.getLast().length(); i++)
            {
                if(lines.getLast().charAt(i) != ' ')
                {
                    operations.add(lines.getLast().charAt(i));
                }
            }
            for(int i = 0; i < lines.getLast().length(); i++)
            {
                if(lines.getLast().charAt(i) != ' ')
                {
                    oppIndices.add(i);
                }
            }
            System.out.println("Rows: ");
            for(ArrayList<Long> row : rows)
            {
                System.out.println(row);
            }
            System.out.println(oppIndices);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
    public int maxLength(int index)
    {
        String valInFirstRow = String.valueOf(lines.getFirst().charAt(index));
        int length = valInFirstRow.length();
        for(int i = 1; i < lines.getFirst().length(); i++)
        {
            String currentVal = String.valueOf(lines.get(i).charAt(index));
            if(currentVal.length() > length)
            {
                length = currentVal.length();
            }
        }
        return length;
    }

    public ArrayList<String> actualNums(int elementNum)
    {
        ArrayList<String> actual = new ArrayList<>();
        for(int i = operationIndex(elementNum) + maxLength(elementNum); i >= operationIndex(elementNum) ; i--)
        {
            String current = "";
            if(!columnEmpty(i))
            {
                for(int j = 0; j < lines.size() - 1; j++)
                {
                    if(lines.get(j).charAt(i) != ' ')
                    {
                        current += lines.get(j).charAt(i);
                    }
                }
            }
            System.out.println(current);
            if(!current.equalsIgnoreCase(""))
                actual.add(current);
        }
        return actual;
    }

    public boolean columnEmpty(int index)
    {
        boolean empty = true;
        for(int i = 0; i < rows.size(); i++)
        {
            if(lines.get(i).charAt(index) != ' ')
            {
                empty = false;
                break;
            }
        }
        return empty;
    }

    public int operationIndex(int elementNum)
    {
        int counter = 0;
        for(int i = 0; i < lines.getLast().length(); i++)
        {
            if(lines.getLast().charAt(i) != ' ')
            {
                counter++;
            }
            if(counter == elementNum)
            {
                break;
            }
        }
        return counter;
    }

    public ArrayList<String> chunk(int index)
    {
        ArrayList<String> chunk = new ArrayList<>();
        for(int i = 0; i < lines.size(); i++)
        {
            String layer = "";
            for(int j = index; j < index + maxLength(index); j++)
            {
                layer += lines.get(i).charAt(j);
            }
            chunk.add(layer);
        }
        return chunk;
    }
}
