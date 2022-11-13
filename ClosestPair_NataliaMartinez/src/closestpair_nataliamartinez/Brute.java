/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closestpair_nataliamartinez;

    

import java.util.ArrayList;

/**
 *
 * @author natymartinez04
 */
public class Brute {

    
    public double bruteForce(ArrayList<Coordinate> coordinates,double dmin){ 
        int n = coordinates.size();
        for (int i = 0; i<n;i++){
           for (int j = 0; j<n;j++){
               if (i!=j){
                  if (distance(coordinates.get(i), coordinates.get(j))<dmin){
                    dmin = distance(coordinates.get(i), coordinates.get(j));
                  } 
               }
               
            } 
        }
        return dmin;
    }
    
    public double closestRecursive(ArrayList<Coordinate> coordinates,double dmin){
        int n = coordinates.size();
        if (n<=3){
            dmin = bruteForce(coordinates,dmin);
        }else{
            int mid = n/2;
            Coordinate Cmid = (Coordinate) coordinates.get(mid);
            double dl = closestRecursive(subArray(coordinates,0,mid),dmin);
            double dr = closestRecursive(subArray(coordinates,mid,n),dmin);     
            dmin = Math.min(dl,dr);
            ArrayList<Coordinate> strip = new ArrayList<>();
            //Coordenadas dentro la distancia D
            for (int i = 0; i < n; i++) {
                Coordinate C = (Coordinate) coordinates.get(i);
                if (Math.abs(C.getX() - Cmid.getX()) < dmin) {
                    strip.add((Coordinate) coordinates.get(i));
                }
            }
            coordinates = strip;
   
        }
        return Math.min(dmin, bruteForce(coordinates,dmin));
    }
    
    public ArrayList<Coordinate> subArray(ArrayList<Coordinate> coordinates, int start, int end){
        ArrayList<Coordinate> coordinatesX = new ArrayList<>();
        int j = 0;
        for (int i = start; i<end; i++){
            coordinatesX.add(j, (Coordinate) coordinates.get(i));
            j++;
        }
        return coordinatesX;    
    }
    
    public double distance(Coordinate i,Coordinate j){
        return Math.sqrt(Math.pow(i.getX()-j.getX(),2)+Math.pow(i.getY()-j.getY(),2));
    }

  

   
}
