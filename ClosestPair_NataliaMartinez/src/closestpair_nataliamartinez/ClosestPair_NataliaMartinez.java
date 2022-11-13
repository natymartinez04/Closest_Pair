/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closestpair_nataliamartinez;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author natymartinez04
 */
public class ClosestPair_NataliaMartinez{
    

    public static void main(String[] args){
        int numberC;
        Scanner leer = new Scanner(System.in);
        System.out.println("Amount of coordinates:");
        numberC = leer.nextInt();
        ArrayList<Coordinate> coordinates = new ArrayList();
        coordinates = GenerateCoordinates(coordinates,numberC);
        coordinates = sortArray(coordinates);
        
        System.out.println("By brute force: ");
        Brute brute = new Brute();
        double dmin = brute.bruteForce(coordinates, 100000000);
        System.out.println(dmin);
        System.out.println("Divide and Conquer: ");
        dmin = brute.closestRecursive(coordinates,10000000);
        System.out.println(dmin);
    }
    
    //ArrayList operations
    
    public static ArrayList<Coordinate> sortArray(ArrayList<Coordinate> coordinates){
        //Sorts array in ascending order (x value)
        Collections.sort(coordinates,new Comparator<Coordinate>(){
            public int compare(Coordinate x1, Coordinate x2){
                return Integer.valueOf(x1.getX()).compareTo(x2.getX());
            }   
        });
        return coordinates;
    }
    
    public static void printArray(ArrayList<Coordinate> coordinates){
        for (int i = 0; i<coordinates.size(); i++){
            System.out.println(coordinates.get(i));
            
        }
        System.out.println("done");
    }
    
    //Coordinates Operations
    
    public static void printCoordinates(ArrayList<Coordinate> coordinates){
         for (Coordinate c : coordinates) {
           System.out.println("Coordinate "+c.getName()+": "+c.getX()+" "+c.getY());
         }
    }
    
        
    //Method that generates the coordinates (x,y)
    public static ArrayList<Coordinate> GenerateCoordinates(ArrayList<Coordinate> coordinates,int numberC){
        int x;
        int y;
        Random random = new Random();
        for (int i = 0; i<numberC;i++){
            x = random.nextInt(1000);
            y = random.nextInt(1000);
            coordinates.add(new Coordinate(Integer.toString(i),x,y)); 
        } 
        return coordinates;
    }
    
    
    
    
    
    
    
}

class Coordinate {
    String name;
    int x;
    int y;

    public Coordinate(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}


class Pair{
    Coordinate Coor1;
    Coordinate Coor2;
    double distance;
    
    public Pair(Coordinate Coor1,Coordinate Coor2, Double distance) {
        this.Coor1 = Coor1;
        this.Coor2 = Coor2;
        this.distance = distance;
    }

    public Coordinate getCoor1() {
        return Coor1;
    }

    public Coordinate getCoor2() {
        return Coor2;
    }

    public double getDistance() {
        return distance;
    }
    
}

