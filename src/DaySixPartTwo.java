import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DaySixPartTwo {
    private ArrayList<String> lines;
    private ArrayList<String> manipLines;
    private ArrayList<ArrayList<Long>> rows;
    private ArrayList<Character> operations;
    private ArrayList<Integer> oppIndices = new ArrayList<>();
    private ArrayList<ArrayList<Long>> vertRows = new ArrayList<>();
    public static void main(String[] args) {
        DaySixPartTwo verticalMath = new DaySixPartTwo("testInputDay6.txt");
        ArrayList<ArrayList<String>> chunks = new ArrayList<>();
        for(int i = 0; i < verticalMath.oppIndices.size(); i++)
        {
            System.out.println("Index: " + verticalMath.oppIndices.get(i));
            ArrayList<String> actual = verticalMath.chunk(verticalMath.oppIndices.get(i));
            chunks.add(actual);
        }

        for(ArrayList<String> chunk : chunks)
        {
            for(String s : chunk)
            {
                System.out.println(s);
            }
            System.out.println();
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
            for(int i = 0; i < rows.getFirst().size(); i++)
            {
                ArrayList<Long> vertrow = new ArrayList<>(rows.getFirst().size());
                for(int j = 0; j < rows.size(); j++)
                {
                    vertrow.add(rows.get(j).get(i));
                }
                vertRows.add(vertrow);
            }
            System.out.println("Rows: ");
            for(ArrayList<Long> row : rows)
            {
                System.out.println(row);
            }
            System.out.println(oppIndices);
            System.out.println("Vert: ");
            for(ArrayList<Long> vertrow : vertRows)
            {
                System.out.println(vertrow);
            }
            manipLines = lines;
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
    public int maxLength(int index)
    {
        String valInFirstRow = String.valueOf(rows.getFirst().get(index));
        int length = valInFirstRow.length();
        for(int i = 1; i < rows.size(); i++)
        {
            String currentVal = String.valueOf(rows.get(i).get(index));
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
        ArrayList<String> temp = new ArrayList<>(lines);
        System.out.println(maxLength(index));
        System.out.println(index + maxLength(index));
        for(int i = 0; i < lines.size(); i++)
        {
            String layer = "";
            for(int j = index; j < index + maxLength(index); j++)
            {

                layer += lines.get(i).charAt(j);
            }
            chunk.add(layer);
        }
        for(int i = 0; i < temp.size(); i++)
        {
            temp.set(i, temp.get(i).substring(index + maxLength(index)));
        }
        for(String s : temp)
        {
            System.out.println(s);
        }
        return chunk;
    }

    public int nextEmptyIndex()
    {
        for(int i = 0; i < lines.getFirst().length(); i++)
        {
            if(columnEmpty(i))
            {
                return i;
            }
        }
        return - 1;
    }
}
