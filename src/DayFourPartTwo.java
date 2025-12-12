import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFourPartTwo {
    private char[][] floorplan;
    private char[][] dynamic;
    private boolean[][] removables;
    public static void main(String[] args)
    {
        DayFourPartTwo remover = new DayFourPartTwo("realInputDay4.txt");
        remover.printFloorplan();
        while(remover.anyAccessible(remover.dynamic)) {
            for (int r = 0; r < remover.floorplan.length; r++) {
                for (int c = 0; c < remover.floorplan[0].length; c++) {
                    if (remover.floorplan[r][c] == '@') {
                        if (remover.accessibleOne(remover.floorplan[r], c, r)) {
                            remover.removables[r][c] = true;
                        } else {
                            remover.removables[r][c] = false;
                        }
                    } else {
                        remover.removables[r][c] = false;
                    }
                }
            }
            remover.printX();
            for (int r = 0; r < remover.dynamic.length; r++) {
                for (int c = 0; c < remover.dynamic[0].length; c++) {
                    if (remover.removables[r][c]) {
                        remover.dynamic[r][c] = 'X';
                    }
                }
            }
        }
        remover.printFloorplan();
        System.out.println();
        remover.printX();
        System.out.println(remover.countX());
    }

    public DayFourPartTwo(String file)
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
            dynamic = floorplan;
            removables = new boolean[floorplan.length][floorplan[0].length];
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }

    public boolean accessibleOne(char[] row, int placement, int rowNum)
    {
        System.out.println("Row: " + rowNum + "[" + placement + "]");
        int counter = 0;
        if(rowNum == 0)
        {
            if(placement == 0)
            {
                return true;
            }
            else if(placement == row.length - 1)
            {
                return true;
            }
            else
            {
                if(dynamic[rowNum][placement - 1] == '@')
                {
                    counter++;
                }
                if(dynamic[rowNum][placement + 1] == '@')
                {
                    counter++;
                }
                for(int i = -1; i < 2; i++)
                {
                    if(dynamic[rowNum + 1][i + placement] == '@')
                    {
                        counter++;
                    }
                }
            }
        }
        else if(rowNum == dynamic.length - 1)
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
                if (dynamic[rowNum][placement - 1] == '@') {
                    counter++;
                }
                if (dynamic[rowNum][placement + 1] == '@') {
                    counter++;
                }
                for(int i = -1; i < 2; i++)
                {
                    if(dynamic[rowNum - 1][i + placement] == '@')
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
        }
        return counter < 4;
    }

    public boolean anyAccessible(char[][] chars)
    {
        boolean any = false;
        for(int r = 0; r < chars.length; r++)
        {
            for(int c = 0; c < chars[0].length; c++) {
                if(chars[r][c] == '@')
                {
                    if(accessibleOne(chars[r], c, r))
                    {
                        any = true;
                    }
                }
            }
        }
        return any;
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
        for(int r = 0; r < dynamic.length; r++)
        {
            for(int c = 0; c < dynamic[0].length; c++)
            {
                System.out.print(dynamic[r][c]);
            }
            System.out.println();
        }
    }

    public int countX()
    {
        int count = 0;
        for(int r = 0; r < dynamic.length; r++)
        {
            for(int c = 0; c < dynamic[0].length; c++)
            {
                if(dynamic[r][c] == 'X')
                {
                    count++;
                }
            }
        }
        return count;
    }
}
