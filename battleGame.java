

import java.util.*;

public class battlegame {
    
    public static void main(String [] args) {
        System.out.println("****Welcome to the BattleShip game****");
        System.out.println("Right now, the sea is empty");
        System.out.println();
        int[][] array = new int[10][10];
        
        oceanMap(array);
        
        
        // taking the positions of the ships of the user
        
        Scanner input = new Scanner(System.in);
        int[] xCord = new int[5];
        int[] yCord = new int[5];
        System.out.println("Deploy your ships");
        for(int i=0; i<5; i++) {
            System.out.println("Enter the x coordinate of your ship " + (i+1) +" :");
            int x = input.nextInt();
            while(x>10) {
                System.out.println("Enter the appropriate x coordinate");
                x = input.nextInt();
            }
            System.out.println("Enter the y coordinate of your ship " + (i+1) + " :");
            int y = input.nextInt();
            while(y>10) {
                System.out.println("Enter the appropriate y coordinate");
                y = input.nextInt();
            }
            
            for(int j=0; j<5; j++) {
                while(x== xCord[j] && y== yCord[j]  ) {
                    System.out.println("You have entered the same position of your ship twice");
                    System.out.println("Enter the appropriate x coordinate of your ship " + (i+1) +" :");
                    x = input.nextInt();
                    System.out.println("Enter the appropriate y coordinate of your ship " + (i+1) +" :");
                    y = input.nextInt();
                }
            }
            xCord[i] = x;
            yCord[i] = y;
        }
        
        // deploying the ships by the computer
        
        System.out.println("Computer is deploying ships");
        Random rand = new Random();
        int[] xComp = new int[5];
        int[] yComp = new int[5];
        
        
        for(int i=0; i<xCord.length; i++) {
            int x1 = rand.nextInt(9);
            int y1 = rand.nextInt(9);
            while(x1>10 || y1>10) {
                if(x1>10)
                    x1 = rand.nextInt(9);
                else if(y1>10)
                    y1 = rand.nextInt(9);
            }
            while(x1 == xCord[i] && y1 == yCord[i] || x1== xComp[i] && y1 == yComp[i]) {
                x1 = rand.nextInt(9);
                y1 = rand.nextInt(9);
            }
            xComp[i] = x1;
            yComp[i] = y1;
            System.out.println("Ship deployed");
            
        }
        
        
        // feeding coordinates in the grid
        
        for(int i=0; i<5; i++) {
            array[xCord[i]][yCord[i]] = 1;
            array[xComp[i]][yComp[i]] = 2;
        }
        oceanMap(array);
        
        
        // to continue the battle until either one wins
        int countP = 0;
        int countC = 0;
        while(countP <5 && countC < 5) {
            countP = deployment(array, countP);
            System.out.println("Your ships : " + countP +" Computer's ship : " +countC);
            countC = deployComputer(array, countC);
            System.out.println("Your ships : " + countP +" Computer's ship : " +countC);
            
        }
        if(countP == 5) {
            System.out.println("You have won the match . Computer lose!!!");
            System.out.println("Your ships :" + countP +" Computer's ship :" +countC);
        }
        else if(countC == 5) {
            System.out.println("Computer wins the match. You lose!!!!");
            System.out.println("Your ships :" + countP +" Computer's ship :" +countC);
        }
        
    }
    
    // method to print the oceanMap with the latest manipulation
    public static void oceanMap(int[][] array) {
        System.out.print("  ");
        for(int i=0; i<array.length; i++) {
            System.out.print(i + "");
        }
        System.out.println();
        for(int i=0; i<array.length; i++) {
            System.out.print(i +"|");
            for(int j=0; j<array[0].length; j++) {
                if(array[i][j] == 0)
                    System.out.print(" ");
                else if(array[i][j] == 1)
                    System.out.print("@");
                else if(array[i][j] == 2)
                    System.out.print("$");
                else if(array[i][j] == 3)
                    System.out.print("x");
            }
            System.out.println("|" +i);
        }
        System.out.print("  ");
        for(int i=0; i<array.length; i++) {
            System.out.print(i + "");
        }
        System.out.println();
        System.out.println();
        
    }
    
    // method to take the input of the ship of user during the battle
    public static int deployment( int[][] array, int countP) {
        Scanner input = new Scanner(System.in);
        System.out.println("Your turn");
        
        System.out.println("Enter the x coordinate");
        int x = input.nextInt();
        System.out.println("Enter the y coordinate");
        int y = input.nextInt();
        while(x>10 || y>10) {
            if(x>10) {
                System.out.println("Enter appropriate x coordinate");
                x = input.nextInt();
            }
            else if(y > 10) {
                System.out.println("Enter the appropriate y coordinate");
                y = input.nextInt();
            }
            
        }
        if(array[x][y] == 0) {
            System.out.println("You missed");
            array[x][y] = 3;
            oceanMap(array);
            return countP;
        }
        else if(array[x][y] == 1) {
            System.out.println("Wrong choice");
            oceanMap(array);
            return countP;
        }
        else if(array[x][y] == 2) {
            System.out.println("You hit the ship");
            array[x][y] = 3;
            oceanMap(array);
            return (countP+1);
        }
        else if(array[x][y] == 3) {
            System.out.println("You have guessed the same!!!!!");
            array[x][y] = 3;
            oceanMap(array);
            return countP;
        }
        return 0;
    }
    //method to generate the computer's input during the battle
    public static int deployComputer(int[][] array, int countC) {
        System.out.println("Computer's turn");
        Random rand = new Random();
        int x = rand.nextInt(9);
        int y = rand.nextInt(9);
        while(x>10 || y>10) {
            if(x>10)
                x = rand.nextInt(9);
            else if(y>10)
                y = rand.nextInt(9);
        }
        
        if(array[x][y] == 0) {
            System.out.println("Computer missed");
            array[x][y] = 3;
            oceanMap(array);
            return countC;
        }
        else if(array[x][y] == 1) {
            System.out.println("Computer hit the ship");
            array[x][y] = 3;
            oceanMap(array);
            return (countC+1);
        }
        else if(array[x][y] == 2) {
            System.out.println("Wrong choice");
            
            oceanMap(array);
            return countC;
        }
        else if(array[x][y] == 3) {
            System.out.println("Computer have guessed the same!!!!!");
            
            oceanMap(array);
            return countC;
        }
        return 0;
    }
}
