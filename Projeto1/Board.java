package Projeto1;

import java.util.Random;
import java.util.Arrays;

public class Board
{
    int turn;
    static int rows = 15;
    static int columns = 31;
    static String board[][] = new String[rows][columns];

    

    void startBoard()
    {
        String coordinate_y[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N"};
        String coordinate_x[] = {" 1 "," 2 "," 3 "," 4 "," 5 "," 6 "," 7 "," 8 "," 9 ","10 ","11 ","12 ","13 ","14 "};
        //Evitar trabalhar com ascii
        String display_state = " ";


        for(int l=0;l<rows;l++)
        {
            for(int c=0;c<columns;c++)
            {
                board[14][0]="   ";
                if(c==0)
                {
                    if(l<14)
                    {
                        board[l][c]=coordinate_y[l];
                    }
                }
                else
                {
                    if(c>=2 && c<=30 && c%2==0 && l<(rows-1))
                    {
                        board[l][c]="|";
                    }
                    else
                    {
                        if(l==(rows-1))
                        {
                            if(c>2 && c%2==1)
                            {
                                board[l][c]=coordinate_x[(c/2)-1];
                            }
                            else
                            {
                                board[l][c]=" ";
                            }
                        }
                        else
                        {
                            board[l][c]=" "+display_state+" ";
                        }
                    }
                }
            }
        }
    }
    void showBoard()
    {
        for(int l = 0; l < rows; l++)
        {   
            System.out.print("\t\t\t\t\t\t\t\t");
            for(int c = 0; c < columns; c++)
            {
                System.out.print(board[l][c]);
            }
            System.out.println();
        } 
    }

    static void placeShipsRandomly()
    {
        Random random = new Random();
        int field[][] = new int[rows-1][(columns/2)-1];

        for(int ship_size = 5; ship_size > 1; ship_size--)
        {
            int coordinate_x = random.nextInt(rows-1);
            int coordinate_y = random.nextInt((columns/2)-1);
            boolean vertical = random.nextBoolean();

            //corrigir a coordenada pro navio caber
            if(vertical)
            {
                if(coordinate_y + ship_size > rows-1)
                {
                    coordinate_y -= ship_size;
                }
            }
            else
            {
                if(coordinate_x + ship_size > (columns/2)-1)
                {
                    coordinate_x -= ship_size;
                }
            }

            //procurar por posições livres
            boolean isFree = true;

            if(vertical)
            {
                for(int l = coordinate_y;l < coordinate_y + ship_size; l++)
                {
                    if(field[l][coordinate_x] != 0)
                    {
                        isFree = false;
                        break;
                    }
                    
                }
            }
            else
            {
                for(int c = coordinate_x; c < coordinate_x + ship_size; c++)
                {
                    if(field[coordinate_y][c] != 0)
                    {
                        isFree = false;
                        break;
                    }
                    
                }
            }

            //caso não ache, tentar de novo
            if(!isFree)
            {
                ship_size++;
                continue;
            }

            //preencher posições adjacentes ao navio
            if (vertical) 
            {
                for (int c = Math.max(0, coordinate_x - 1); c < Math.min(14, coordinate_x + 2); c++) 
                {
                    for (int l = Math.max(0, coordinate_y - 1); l < Math.min(14, coordinate_y + ship_size + 1); l++) 
                    {
                        field[l][c] = 13;
                    }
                }
            } else 
            {
                for (int l = Math.max(0, coordinate_y - 1); l < Math.min(14, coordinate_y + 2); l++) 
                {
                    for (int c = Math.max(0, coordinate_x - 1); c < Math.min(14, coordinate_x + ship_size + 1); c++) 
                    {
                        field[l][c] = 13;
                    }
                }
            }
            
            //preencher as posições do navio
            for(int i = 0; i < ship_size; i++)
            {
                field[coordinate_y][coordinate_x] = ship_size;
                if(vertical)
                {
                    coordinate_y++;
                }
                else
                {
                    coordinate_x++;
                }
            }

            //
            for (int l = 0; l < rows - 1; l++) 
            {
                for (int c = 0; c < (columns/2) - 1; c++) 
                {
                    if(field[l][c]==0)
                    {
                        board[l][c] = " ";
                    }
                    else
                    {
                        board[l/* arrumar */][c/* arrumar */]= "*";
                    }
                }
            }
        }
    }
}
