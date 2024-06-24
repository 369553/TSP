package Base;

import Algorithms.UsefulMethods;
import Control.IDARE;

public class City {
    public double x, y;
    public String name;
    private int id;
    
    public City(String name, double xCoordinat, double yCoordinate){
        this.x = xCoordinat;
        this.y = yCoordinate;
        this.name = name;
        id = takeIDFromIDARE();
    }
    private int takeIDFromIDARE(){
        return IDARE.takeNewIDForNewCity();
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public float distanceTo(City targetCity){//Verilen il ile arasında mesâfeyi döndürür (Öklit uzaklığı, kuş bakışı uzaklık)
        double distance = 0.0;
        distance = Math.abs(Math.pow((getX() - targetCity.getX()),2)) + Math.abs(Math.pow((getY() - targetCity.getY()),2));
        distance = Math.sqrt(distance);
        return (float) distance;
    }
    public float distanceTo(City targetCity, int sensity){//Verilen il ile arasında mesâfeyi döndürür (Öklit uzaklığı, kuş bakışı uzaklık)
        if(targetCity == this)
            return (float) 0.0;
        double distance = 0.0;
        distance = Math.abs(Math.pow((getX() - targetCity.getX()),2)) + Math.abs(Math.pow((getY() - targetCity.getY()),2));
        distance = Math.sqrt(distance);
        return UsefulMethods.roundNumber((float)distance, sensity);
    }
}
