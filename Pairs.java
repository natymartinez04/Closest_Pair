/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closestpair_nataliamartinez;

/**
 *
 * @author natymartinez04
 */
public final class Pairs {
    //Pair of coordinates that possibly have the minimun distance
    
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
