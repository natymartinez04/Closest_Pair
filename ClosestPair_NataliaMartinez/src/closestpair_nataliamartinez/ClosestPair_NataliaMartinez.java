/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closestpair_nataliamartinez;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author natymartinez04
 */
public class ClosestPair_NataliaMartinez{
    
    

    public static void main(String[] args){
        
        long numberC,startSort,endSort;
        int iteraciones = 0;
        Long tiemposBrute[] = new Long[16];
        Long tiemposDivide[] = new Long[16];
        Long comparacionesBrute[] = new Long[16];
        Long comparacionesDivide[] = new Long[16];
        Brute brute = new Brute();
        ArrayList<Coordinate> coordinates = new ArrayList();
        TextFile text = new TextFile();
        
        while (iteraciones < 16){
            numberC = (long) Math.pow(2, iteraciones+3);
            System.out.println("Amount of coordinates:"+numberC);
            System.out.println(" ");
            
            coordinates = GenerateCoordinates(coordinates,numberC);
            startSort = System.nanoTime();
            coordinates = sortArray(coordinates,brute);
            endSort = System.nanoTime();
         
            
            tiemposBrute = getTime(coordinates,tiemposBrute,iteraciones,brute,endSort,startSort,false);
            comparacionesBrute[iteraciones] = brute.getComparasiones();
            brute.setComparaciones(0);
            
            tiemposDivide = getTime(coordinates,tiemposDivide,iteraciones,brute,endSort,startSort,true);
            comparacionesDivide[iteraciones] = brute.getComparasiones();
            brute.setComparaciones(0);
            
            coordinates.removeAll(coordinates);
            iteraciones++;
        }
        
        text.writeTime("dataBruteForce.txt",tiemposBrute,iteraciones,comparacionesBrute);
        text.writeTime("dataDivideAndConquer.txt",tiemposBrute,iteraciones,comparacionesDivide);

        
    }
    
    
    public static Long[] getTime(ArrayList<Coordinate> coordinates,Long[] tiempos,int iteraciones,Brute brute,long endSort,long startSort,boolean sw){
        long start,end;
        double dmin;
        if (sw == false){
            System.out.println("By brute force: ");
            start = System.nanoTime();
            dmin = brute.bruteForce(coordinates, 100000000);
            end = System.nanoTime();
            tiempos[iteraciones] = end-start + endSort-startSort; 
            Pairs pair = findPair(brute,dmin);
            System.out.println("La Coordenada "+pair.coordinate1.getName()+"("+
                    pair.coordinate1.getX()+","+pair.coordinate1.getY()+")"
                    +" y "+" la coordenada "+pair.coordinate2.getName()+"("+
                    pair.coordinate2.getX()+","+pair.coordinate2.getY()+")"
                    + " tienen la distancia mínima que es igual a:");
            System.out.println(dmin);
            System.out.println(" ");
        }else{
            System.out.println("Divide and Conquer: ");
            start = System.nanoTime();
            dmin = brute.closestRecursive(coordinates,10000000);
            end = System.nanoTime();
            tiempos[iteraciones] = end-start + endSort-startSort;
            Pairs pair = findPair(brute,dmin);
            System.out.println("La Coordenada "+pair.coordinate1.getName()+"("+
                    pair.coordinate1.getX()+","+pair.coordinate1.getY()+")"
                    +" y "+" la coordenada "+pair.coordinate2.getName()+"("+
                    pair.coordinate2.getX()+","+pair.coordinate2.getY()+")"
                    + " tienen la distancia mínima que es igual a:");
            System.out.println(dmin);
            System.out.println(" ");
        }
        brute.getPairs().removeAll(brute.getPairs());
        return tiempos;
    }
    
    public static Pairs findPair(Brute brute,double dmin){
        for (int i = 0;i<brute.pairs.size();i++){
            if (brute.pairs.get(i).getMinDistance() == dmin){
                return brute.pairs.get(i);
            }
        }
        return null;
    }
    
    
    //Method that sorts array of coordinates in ascending order (for the x coordinate)
    public static ArrayList<Coordinate> sortArray(ArrayList<Coordinate> coordinates,Brute brute){
        //Sorts array in ascending order (x value)
        Collections.sort(coordinates,new Comparator<Coordinate>(){
            public int compare(Coordinate x1, Coordinate x2){
                brute.setComparaciones(brute.comparaciones + 1);
                return Integer.valueOf(x1.getX()).compareTo(x2.getX());
                
            }   
        });
        return coordinates;
    }
    
 
    //Method that generates the coordinates (x,y)
    public static ArrayList<Coordinate> GenerateCoordinates(ArrayList<Coordinate> coordinates,long numberC){
        int x;
        int y;
        Random random = new Random();
        for (int i = 0; i<numberC;i++){
            x = random.nextInt((int) Math.pow(5, i));
            y = random.nextInt(500);
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

