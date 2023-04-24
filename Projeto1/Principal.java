package Projeto1;

import java.util.Scanner;


public class Principal
{
    public static void main(String args[])
    {
        Scanner reader = new Scanner(System.in);


        Board player  = new Board();
        Board machine = new Board();

        player.startBoard();
        machine.startBoard();

        player.turn  = 0;
        machine.turn = 1;

    }
}
