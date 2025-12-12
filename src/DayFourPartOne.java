import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFourPartOne {
    private char[][] floorplan;
    private char[][]  withX;
    public static void main(String[] args)
    {
        DayFourPartOne finder = new DayFourPartOne("realInputDay4.txt");
        finder.printFloorplan();
        for(int r = 0; r < finder.floorplan.length; r++)
        {
            for(int c = 0; c < finder.floorplan[0].length; c++) {
                if(finder.floorplan[r][c] == '@')
                {
                    if(finder.accessible(finder.floorplan[r], c, r))
                    {
                        finder.withX[r][c] = 'X';
                    }
                    else
                    {
                        finder.withX[r][c] = '@';
                    }
                }
                else
                {
                    finder.withX[r][c] = '.';
                }
            }
        }
        finder.printFloorplan();
        System.out.println();
        finder.printX();
        System.out.println(finder.countX());
    }

    public DayFourPartOne(String file)
    {
        try{
            Scanner sc = new Scanner(new File(file));
            ArrayList<String> lines = new ArrayList<>();
            lines.add(sc.nextLine());
            int colNum = lines.getFirst().length();
            int rowNum = 1;
            while(sc.hasNext())
            {
                rowNum++;
                lines.add(sc.nextLine());
            }

            floorplan = new char[rowNum][colNum];
            for(int r = 0; r < rowNum; r++)
            {
                String row  = lines.get(r);
                for(int c = 0; c < colNum; c++)
                {
                    floorplan[r][c] = row.charAt(c);
                }
            }
            withX = new char[floorplan.length][floorplan[0].length];
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }

    public boolean accessible(char[] row, int placement, int rowNum)
    {
        System.out.println("Row: " + rowNum + "[" + placement + "]");
        int counter = 0;
        if(rowNum == 0)
        {
            System.out.println("Top Row");
            if(placement == 0)
            {
                System.out.println("Left Corner");
                return true;
            }
            else if(placement == row.length - 1)
            {
                return true;
            }
            else
            {
                if(floorplan[rowNum][placement - 1] == '@')
                {
                    System.out.println("Left is @");
                    counter++;
                }
                if(floorplan[rowNum][placement + 1] == '@')
                {
                    System.out.println("Right is @");
                    counter++;
                }
                for(int i = -1; i < 2; i++)
                {
                    if(floorplan[rowNum + 1][i + placement] == '@')
                    {
                        System.out.println((i + placement)+ " is @");
                        counter++;
                    }
                }
            }
        }
        else if(rowNum == floorplan.length - 1)
        {
            if(placement == 0)
            {
                return true;
            }
            else if(placement == row.length - 1)
            {
                return true;
            }
            else {
                if (floorplan[rowNum][placement - 1] == '@') {
                    counter++;
                }
                if (floorplan[rowNum][placement + 1] == '@') {
                    counter++;
                }
                for(int i = -1; i < 2; i++)
                {
                    if(floorplan[rowNum - 1][i + placement] == '@')
                    {
                        counter++;
                    }
                }
            }
        }
        else
        {
            if(placement == 0)
            {
                for (int i = 0; i < 2; i++) {
                    if (floorplan[rowNum - 1][i + placement] == '@') {
                        counter++;
                    }
                    if (floorplan[rowNum + 1][i + placement] == '@') {
                        counter++;
                    }
                }
                if(floorplan[rowNum][placement + 1] == '@')
                {
                    counter++;
                }
            }
            else if(placement == row.length - 1)
            {
                for (int i = -1; i < 1; i++) {
                    if (floorplan[rowNum - 1][i + placement] == '@') {
                        counter++;
                    }
                    if (floorplan[rowNum + 1][i + placement] == '@') {
                        counter++;
                    }
                }
                if(floorplan[rowNum][placement - 1] == '@')
                {
                    counter++;
                }
            }
            else
            {

                for (int i = -1; i < 2; i++) {
                    if (floorplan[rowNum - 1][i + placement] == '@') {
                        counter++;
                    }
                    if (floorplan[rowNum + 1][i + placement] == '@') {
                        counter++;
                    }
                }
                if (floorplan[rowNum][placement - 1] == '@') {
                    counter++;
                }
                if (floorplan[rowNum][placement + 1] == '@') {
                    counter++;
                }
            }
            System.out.println(counter);
        }
        if (counter >= 4)
        {
            return false;
        } else {
            return true;
        }
    }

    public void printFloorplan()
    {
        for(int r = 0; r < floorplan.length; r++)
        {
            for(int c = 0; c < floorplan[0].length; c++)
            {
                System.out.print(floorplan[r][c]);
            }
            System.out.println();
        }
    }

    public void printX()
    {
        for(int r = 0; r < withX.length; r++)
        {
            for(int c = 0; c < withX[0].length; c++)
            {
                System.out.print(withX[r][c]);
            }
            System.out.println();
        }
    }

    public int countX()
    {
        int count = 0;
        for(int r = 0; r < withX.length; r++)
        {
            for(int c = 0; c < withX[0].length; c++)
            {
                if(withX[r][c] == 'X')
                {
                    count++;
                }
            }
        }
        return count;
    }
}
