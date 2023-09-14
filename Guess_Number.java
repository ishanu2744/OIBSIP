package com;
import java.util.Scanner;
import java.util.Random;

class Game{
    private int Number;

    private int GuessNumber;
    private int Attempt=0;

    Game(){
        Random rad= new Random();
        Number= rad.nextInt(100);
    }

    void takeUserInput(){
        System.out.print("Guess The Number: ");
        Scanner sc= new Scanner(System.in);
        GuessNumber= sc.nextInt();
    }
    Boolean isCorrectNumber(){
        Attempt++;
        if (GuessNumber==Number){
            System.out.println("Your Guess is Right..");
            System.out.printf("You Score is:  %d.",Attempt);
            return true;
        }
        else if (GuessNumber>Number){
            System.out.println();
            System.out.println("Guess Is Higher");
        }
        else if (GuessNumber<Number){
            System.out.println();
            System.out.println("Guess Is Lower");
        }
        return false;
    }
    void showNumber(){
        System.out.println("Right Number is: "+Number);
    }
}
public class Guess_Number {
    public static void main(String[] args) {
        int round=1;
        System.out.println("You have 2 Rounds !!!");
        while (round<3){
            System.out.println("Round:- "+round);
            Game gn= new Game();
            int c=5;
            boolean check=true;
            boolean b=true;
            while (c>0){
                System.out.printf("You have %d attempts..\n",c);
                gn.takeUserInput();
                b=gn.isCorrectNumber();
                c--;
                if (check==b)
                    break;
            }
            if (check==b){
                System.out.println();
                System.out.println("Keep Playing..");
            }
            else {
                gn.showNumber();
            }
            round++;
            System.out.println();
        }
    }
}
