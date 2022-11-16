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
    
    
    ArrayList<Pairs> pairs = new ArrayList();
    long comparaciones = 0;

    public ArrayList<Pairs> getPairs() {
        return pairs;
    }
    
  
    public long getComparasiones() {
        return comparaciones;
    }

    public void setComparaciones(long comparaciones) {
        this.comparaciones = comparaciones;
    }
    
    
    //Brute force function: Finds the minimun distance between
    public double bruteForce(ArrayList<Coordinate> coordinates,double dmin){ 
        int n = coordinates.size();
        for (int i = 0; i<n;i++){
           for (int j = 0; j<n;j++){
               if (i!=j){
                  if (distance(coordinates.get(i), coordinates.get(j))<=dmin){
                    dmin = distance(coordinates.get(i), coordinates.get(j));
                    pairs.add(new Pairs(coordinates.get(i),coordinates.get(j),dmin));
                    
                  }
               } 
               comparaciones++;
            } 
        }
        
        return dmin;
    }
    
   
    //Divide and Conquer function: Recursively finds the minimun distance between two points
    public double closestRecursive(ArrayList<Coordinate> coordinates,double dmin){
        int n = coordinates.size();
        comparaciones++;
        if (n<=3){
            return bruteForce(coordinates,dmin);
        }else{
            int mid = n/2;
            Coordinate Cmid = (Coordinate) coordinates.get(mid);
            double dl = closestRecursive(subArray(coordinates,0,mid),dmin);
            double dr = closestRecursive(subArray(coordinates,mid,n),dmin);    
            dmin = Math.min(dl,dr);
            ArrayList<Coordinate> strip = new ArrayList<>();
            coordinates = Strip(strip,coordinates,Cmid,0,dmin);
        }
        
        return bruteForce(coordinates,dmin); 
    }


    public ArrayList<Coordinate> Strip(ArrayList<Coordinate> strip,ArrayList<Coordinate> coordinates,Coordinate Cmid,int i,double dmin){
       while (i<coordinates.size()){
            if (Math.abs(coordinates.get(i).getX() - Cmid.getX()) < dmin) {
                strip.add((Coordinate) coordinates.get(i));
            }
            i++;
        }
        return strip;
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

class Pairs {
    
    Coordinate coordinate1;
    Coordinate coordinate2;
    double minDistance;

    public Pairs(Coordinate coordinate1, Coordinate coordinate2, double minDistance) {
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
        this.minDistance = minDistance;
    }

    public Coordinate getCoordinate1() {
        return coordinate1;
    }

    public Coordinate getCoordinate2() {
        return coordinate2;
    }

    public double getMinDistance() {
        return minDistance;
    }

    
}
