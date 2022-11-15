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
        long numberC,start,end,startSort,endSort,time;
        int iteraciones = 0;
        Long tiemposBrute[] = new Long[16];
        Long tiemposDivide[] = new Long[16];
        
        Long comparacionesBrute[] = new Long[16];
        Long comparacionesDivide[] = new Long[16];
        Scanner leer = new Scanner(System.in);
        Brute brute = new Brute();
        
        while (iteraciones < 10){
            System.out.println("Amount of coordinates:");
            numberC = (long) Math.pow(2, iteraciones+5);
            ArrayList<Coordinate> coordinates = new ArrayList();
            
            coordinates = GenerateCoordinates(coordinates,numberC);
            startSort = System.nanoTime();
            coordinates = sortArray(coordinates);
            endSort = System.nanoTime();
            
            System.out.println("By brute force: ");
            System.out.println(" ");
            start = System.nanoTime();
            double dmin = brute.bruteForce(coordinates, 100000000);
            end = System.nanoTime();
            tiemposBrute[iteraciones] = end-start + endSort-startSort;
            comparacionesBrute[iteraciones] = brute.getComparasiones();
            System.out.println("La Coordenada "+brute.getCoordinate1().getName()+" y "+" la coordenada "+brute.getCoordinate2().getName()+ " tienen la distancia mínima que es igual a:");
            System.out.println(dmin);
            
            brute.setComparaciones(0);
            System.out.println("Divide and Conquer: ");
            System.out.println(" ");
            start = System.nanoTime();
            dmin = brute.closestRecursive(coordinates,10000000);
            end = System.nanoTime();
            tiemposDivide[iteraciones] = end-start + endSort-startSort;
            comparacionesDivide[iteraciones] = brute.getComparasiones();
            System.out.println("La Coordenada "+brute.getCoordinate1().getName()+" y "+" la coordenada "+brute.getCoordinate2().getName()+ " tienen la distancia mínima que es igual a:");
            System.out.println(dmin);
            brute.setComparaciones(0);
            
            iteraciones++;
        }
        writeTime("dataBruteForce.txt",tiemposBrute,iteraciones,comparacionesBrute);
        writeTime("dataDivideAndConquer.txt",tiemposBrute,iteraciones,comparacionesDivide);

        
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
    
  
    
    //Coordinates Operations
    
    public static void printCoordinates(ArrayList<Coordinate> coordinates){
         for (Coordinate c : coordinates) {
           System.out.println("Coordinate "+c.getName()+": "+c.getX()+" "+c.getY());
         }
    }
    
        
    //Method that generates the coordinates (x,y)
    public static ArrayList<Coordinate> GenerateCoordinates(ArrayList<Coordinate> coordinates,long numberC){
        int x;
        int y;
        Random random = new Random();
        for (int i = 0; i<numberC;i++){
            x = random.nextInt(9999999);
            y = random.nextInt(9999999);
            coordinates.add(new Coordinate(Integer.toString(i),x,y)); 
        } 
        return coordinates;
    } 
    
    private static void create (String nombre){
	// creates a file
		try{
			// defines the filename
			String fname = (nombre);
			// creates a new File object
			File f = new File (fname);

			String msg = "creating file `" + fname + "' ... ";
			System.out.println();
			System.out.printf("%s", msg);
			// creates the new file
			f.createNewFile();
			System.out.println("done");
		}
		catch (IOException err)
		{
			// complains if there is an Input/Output Error
			err.printStackTrace();
		}

		return;
    }
    
    private static void writeTime(String nombre, Long[] tiempo, Integer iteraciones,Long[] comparaciones){
            //writes the amount of numbers in the file, the number of comparisons and time into a file.
            try
		{
                        create(nombre);
			// defines the filename
			String filename = (nombre);
			// creates new PrintWriter object for writing file
			PrintWriter out = new PrintWriter (filename);
                        for (int i = 0; i< iteraciones; i++){
                            out.printf((int) Math.pow(2, i+5)+"    "+comparaciones[i]+"    "+tiempo[i]+"\n"); 
                        }
			System.out.printf("closing file ... ");
			out.close();	// closes the output stream
			System.out.println("done");
		}
		catch (FileNotFoundException err){
			// complains if file does not exist
			err.printStackTrace();
		}
		return;
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

