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
    
    public static ArrayList<Coordinate> coordinates = new ArrayList<>();
    
    public static void main(String[] args){
        int numberC;
        Scanner leer = new Scanner(System.in);
        System.out.println("Amount of coordinates:");
        numberC = leer.nextInt();
        GenerateCoordinates(numberC);
        sortArray();
        double dmin=1000000;
        closestRecursive(null,0,0,coordinates.size(),dmin);
    }
    
    //Method that generates the coordinates (x,y)
    public static void GenerateCoordinates(int numberC){
        int x;
        int y;
        Random random = new Random();
        for (int i = 0; i<numberC;i++){
            x = random.nextInt(10);
            y = random.nextInt(10);
            coordinates.add(new Coordinate(Integer.toString(i),x,y)); 
        }
        printCoordinates();
    }
    
    public static void printCoordinates(){
         for (Coordinate c : coordinates) {
           System.out.println("Coordinate "+c.getName()+": "+c.getX()+" "+c.getY());
         }
    }
    
    public static double closestRecursive(Coordinate mid,int startIndex, int midIndex, int endIndex,double dmin){
        int n = endIndex-startIndex;
        if (n<=3){
            dmin = bruteForce(n,dmin);
        }else{
            midIndex = (startIndex+n)/2;
            mid = coordinates.get(midIndex);
            double dLeft = closestRecursive(mid,startIndex,midIndex,endIndex,dmin);
            double dRight = closestRecursive(mid,midIndex,midIndex,endIndex,dmin);
            double d = Math.min(dLeft, dRight);
            Coordinate[] strip = new Coordinate[coordinates.size()];
            int j = 0;
            
            //Coordenadas dentro la distancia D
            for (int i = 0; i < coordinates.size(); i++) {
                if (Math.abs(coordinates.get(i).getX() - mid.getX()) < d) {
                    strip[j] = coordinates.get(i);
                    j++;
                }
            }
  
        }
        System.out.println("La distancia mínima es: "+dmin);
        return dmin;
    }
    public static double bruteForce(int numberC,double dmin){ 
        int c1 = 0;
        int c2 = 0;
        for (int i = 0; i<numberC;i++){
           for (int j = i+1; j<numberC;j++){
               System.out.println(distance(coordinates.get(i), coordinates.get(j)));
               if (distance(coordinates.get(i), coordinates.get(j))<dmin){
                   c1 = i;
                   c2 = j;
                   dmin = distance(coordinates.get(i), coordinates.get(j));
               }
           } 
        }
        System.out.println("Punto "+coordinates.get(c1).name + " y " + "Punto "+coordinates.get(c2).name+" tienen la distancia mínima.");
        return dmin;
    }
    public static double distance(Coordinate i,Coordinate j){
        return Math.sqrt(Math.pow(i.getX()-j.getX(),2)+Math.pow(i.getY()-j.getY(),2));
    }
    public static void sortArray(){
        //Sorts array in ascending order (x value)
        Collections.sort(coordinates,new Comparator<Coordinate>(){
            public int compare(Coordinate x1, Coordinate x2){
                return Integer.valueOf(x1.getX()).compareTo(x2.getX());
            }   
        });
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
