package Base;

import Views.Point;
import java.util.ArrayList;

public class Map {
    private String name;
    private int width, height;
    private int[][] area;

    public Map(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.area = new int[height][width];
    }

//İŞLEM YÖNTEMLERİ:
    public Point[] getPointsOnTheMap(){
        ArrayList<Point> mapPoints = new ArrayList<Point>();
        Point[] pointsArray;
        for(int index = 0; index < area.length; index++){
            for(int index2 = 0; index2 < area[index].length; index2++){
                if(area[index][index2] != 0)
                    mapPoints.add(new Point(index2, index, ""));
            }
        }
        pointsArray = new Point[mapPoints.size()];
        mapPoints.toArray(pointsArray);
        return pointsArray;
    }

//ERİŞİM YÖNTEMLERİ:
    public String getName() {
        return name;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int[][] getArea() {
        return area;
    }
}