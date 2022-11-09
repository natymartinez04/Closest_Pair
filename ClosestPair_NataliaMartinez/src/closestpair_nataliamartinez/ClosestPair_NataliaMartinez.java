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
    public static ArrayList<Pair> pairsMinDistance = new ArrayList<>();
    
    
    public static void main(String[] args){
        int numberC;
        Scanner leer = new Scanner(System.in);
        System.out.println("Amount of coordinates:");
        numberC = leer.nextInt();
        GenerateCoordinates(numberC);
        sortArray();
        double dmin=1000000;
        dmin = closestRecursive(coordinates,dmin);
        Pair pair = CoorMinDistance(dmin,pairsMinDistance.size());
        System.out.print("Las coordenadas "+pair.getCoor1().getName()+" y "+pair.getCoor2().getName()+" tienen la distancia mínima.");
        System.out.println(" ");
        System.out.println("Distancia mínima: "+dmin);
    }
    
    //ArrayList operations
    
    public static void sortArray(){
        //Sorts array in ascending order (x value)
        Collections.sort(coordinates,new Comparator<Coordinate>(){
            public int compare(Coordinate x1, Coordinate x2){
                return Integer.valueOf(x1.getX()).compareTo(x2.getX());
            }   
        });
    }
    public static ArrayList subArray(ArrayList coordinates, int start, int end){
        ArrayList<Coordinate> coordinatesX = new ArrayList<>();
        int j = 0;
        for (int i = start; i<end;i++){
            coordinatesX.add(j, (Coordinate) coordinates.get(i));
            j++;
        }
        return coordinatesX;    
    }
    
    //Coordinates Operations
    
    public static void printCoordinates(){
         for (Coordinate c : coordinates) {
           System.out.println("Coordinate "+c.getName()+": "+c.getX()+" "+c.getY());
         }
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
    
    public static double bruteForce(int numberC,double dmin){ 
        for (int i = 0; i<numberC;i++){
           for (int j = i+1; j<numberC;j++){
               System.out.println(distance(coordinates.get(i), coordinates.get(j)));
               if (distance(coordinates.get(i), coordinates.get(j))<dmin && !coordinates.get(i).name.equals(coordinates.get(j).name) ){
                   dmin = distance(coordinates.get(i), coordinates.get(j));
                   Pair pair = new Pair(coordinates.get(i),coordinates.get(j),dmin);
                   pairsMinDistance.add(pair);
               }
           } 
        }
        return dmin;
    }
    
    public static double closestRecursive(ArrayList coordinates,double dmin){
        int n = coordinates.size();
        if (n<=3){
            dmin = bruteForce(n,dmin);
        }else{
            int mid = n/2;
            Coordinate Cmid = (Coordinate) coordinates.get(mid);
            double dl = closestRecursive(subArray(coordinates,0,mid),dmin);
            double dr = closestRecursive(subArray(coordinates,mid+1,n),dmin);     
            dmin = Math.min(dl,dr);
            System.out.println("hola  "+  dmin);
            ArrayList<Coordinate> strip = new ArrayList<>();

            //Coordenadas dentro la distancia D
            for (int i = 0; i < n; i++) {
                Coordinate C = (Coordinate) coordinates.get(i);
                if (Math.abs(C.getX() - Cmid.getX()) < dmin) {
                    strip.add((Coordinate) coordinates.get(i));
                }
            }
            
        }
        return Math.min(dmin, bruteForce(n,dmin));
    }
    
    public static Pair CoorMinDistance(Double dminTemp,Integer n){
        for (int i = 0; i<n;i++){
            if (pairsMinDistance.get(i).getDistance() == dminTemp){
                return pairsMinDistance.get(i);
            }
        }
        return null;
    }
    
    
    public static double distance(Coordinate i,Coordinate j){
        return Math.sqrt(Math.pow(i.getX()-j.getX(),2)+Math.pow(i.getY()-j.getY(),2));
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
