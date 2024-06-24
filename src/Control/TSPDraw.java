package Control;
import java.util.ArrayList;
import java.util.HashMap;
import Base.City;
import Base.Map;
import Base.Route;
import Views.DrawPanel;
import Views.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

public class TSPDraw {
    private ArrayList<City> cities;
    private HashMap<String, Color> clrCities;
    private HashMap<String, Point> pointsOfCities;
    private Map map;
    private DrawPanel drawArea;
    private Route route;
    private boolean drawFullRoute = false;

    public TSPDraw(){
        
    }
    public TSPDraw(ArrayList<City> cities) {
        this.cities = cities;
        clrCities = new HashMap<String, Color>();
        pointsOfCities = new HashMap<String, Point>();
        producePoints();
        assignColorsToCities();
        drawArea = new DrawPanel((Point[]) pointsOfCities.values().toArray(), calculatePanelDimension());
    }
    public TSPDraw(ArrayList<City> cities, String[] cityColors) {
        this.cities = cities;
        clrCities = new HashMap<String, Color>();
        pointsOfCities = new HashMap<String, Point>();
        producePoints();
        assignColorsToCities(cityColors);
        drawArea = new DrawPanel((Point[]) pointsOfCities.values().toArray(), calculatePanelDimension());
    }
    public TSPDraw(Map map, ArrayList<City> cities/*, String code*/){
        this.map = map;
        this.cities = cities;
        clrCities = new HashMap<String, Color>();
        pointsOfCities = new HashMap<String, Point>();
        producePoints();
        assignColorsToCities();
        drawArea = new DrawPanel((Point[]) pointsOfCities.values().toArray(), calculatePanelDimension());
    }
    public TSPDraw(Map map, ArrayList<City> cities, String[] cityColors/*, String code*/){
        this.map = map;
        this.cities = cities;
        clrCities = new HashMap<String, Color>();
        pointsOfCities = new HashMap<String, Point>();
        producePoints();
        assignColorsToCities(cityColors);
        drawArea = new DrawPanel((Point[]) pointsOfCities.values().toArray(), calculatePanelDimension());
    }
    public TSPDraw(Map map/*, String code*/){
        this.map = map;
        drawArea = new DrawPanel(map.getWidth(), map.getHeight());
    }
    public void deleteCurrentRoute(){
        getDrawArea().setLines(null);
    }

//İŞLEM YÖNTEMLERİ:
    public void setCities(ArrayList<City> cities){
        this.cities = cities;
        assignColorsToCities();
        producePoints();
        Point[] points = new Point[pointsOfCities.size()];
        pointsOfCities.values().toArray(points);
        drawArea.updateDrawPanel(points);
    }
    public void setClrCities(HashMap<String, Color> clrCities) {
        this.clrCities = clrCities;
    }
    public void setPointsOfCities(HashMap<String, Point> pointsOfCities) {
        this.pointsOfCities = pointsOfCities;
    }
    public void setMap(Map map) {
        this.map = map;
    }
    public void setDrawArea(DrawPanel drawArea) {
        this.drawArea = drawArea;
    }
    public void setRoute(Route route){
        this.route = route;
    }
    //ÇİZİM İŞLEMLERİNİN YÖNTEMLERİ:
    public void drawFullRoute(){
        drawFullRoute = true;
        for(int index = 1; index < route.getStepNumber(); index++){
            drawStep(index);
        }
        drawFullRoute = false;
    }
    public void drawStep(int stepNumber){
        if(drawFullRoute){
            if(stepNumber == 1)
                drawArea.addString("Başlangıç", (float) route.getRoute()[0].x, (float) route.getRoute()[0].y);
            if(stepNumber == route.getStepNumber() - 1)
                drawArea.addString("Bitiş", (float) route.getRoute()[stepNumber].x, (float) route.getRoute()[stepNumber].y);
        }
        getDrawArea().addLineToDrawPanel(route.getRoute()[stepNumber - 1].x, route.getRoute()[stepNumber - 1].y, route.getRoute()[stepNumber].x, route.getRoute()[stepNumber].y);
        getDrawArea().repaint();
    }
    public void chooseRouteSteps(int[] stepNumbers){
        for(int stepper : stepNumbers){
        if(stepper >= route.getStepNumber())
            return;
        }
        getDrawArea().selectLines(stepNumbers);
        getDrawArea().repaint();
    }

    //ARKAPLAN İŞLEM YÖNTEMLERİ:
    private void assignColorsToCities() {
        if(cities == null)
            return;
        for(int index = 0; index < getCities().size(); index++){
            getClrCities().put(cities.get(index).getName(), produceNewCityColor());
        }
    }
    private void assignColorsToCities(String[] cityColors){
        for(int index = 0; index < getCities().size(); index++){
            getClrCities().put(cities.get(index).getName(), Color.decode(cityColors[index]));
        }
    }
    private void assignColorsToCities(Color[] cityColors){
        for(int index = 0; index < getCities().size(); index++){
            getClrCities().put(cities.get(index).getName(), cityColors[index]);
        }
    }
    private Color produceNewCityColor(){
        Color color;
        Random r = new Random();
        int red = r.nextInt(255);
        int green = r.nextInt(255);
        int blue = r.nextInt(255);
        red = Math.abs(red);
        green = Math.abs(green);
        blue = Math.abs(blue);
        /*System.out.println("red : " + red);
        System.out.println("green : " + green);
        System.out.println("blue : " + blue);*/
        color = new Color(red, green, blue);
        if(checkColorForVisuality(color))
            return color;
        else
            return produceNewCityColor();
    }
    private boolean checkColorForVisuality(Color color){
        if(getClrCities().size() == 0)
            return true;
        for(Color indexColor : getClrCities().values()){
            int farForRed = Math.abs(indexColor.getRed() - color.getRed());
            int farForGreen = Math.abs(indexColor.getGreen() - color.getGreen());
            int farForBlue = Math.abs(indexColor.getBlue() - color.getBlue());
            short closer = 0;
            if(farForRed < 30)
                closer++;
            if(farForGreen < 30)
                closer++;
            if(farForBlue < 30)
                closer++;
            if(closer > 2)
                return false;
        }
        return true;
    }
    private void producePoints(){
       getPointsOfCities().clear();
        for(int index = 0 ; index < getCities().size(); index++){
            City currentCity = cities.get(index);
            pointsOfCities.put(currentCity.getName(), new Point((int)currentCity.getX(),
                    (int)currentCity.getY(), currentCity.getName(), getClrCities().get(currentCity.getName())));
        }
    }
    private Dimension calculatePanelDimension(){
        int maxX = 0, maxY = 0;
        for(Point p: pointsOfCities.values()){
            if(p.getX() > maxX)
                maxX = p.getX();
            if(p.getY() > maxY)
                maxY = p.getY();
        }
        return new Dimension(maxX + 20, maxY + 20);
    }
    /*public Map panelNormalizeChecking(Map map){
        boolean isSizeAcceptable = true;
        int width = map.getWidth();
        int height = map.getHeight();
        Dimension maxDimension = IDARE.getIdare().maximumDrawPanelDimension();
        return null;
    }
    private boolean normalize(Map map, boolean normalizeByBigging){
        
    }
    private Object[] isOverLappingInfos(Map map){
        return isOverLappingInfos(map, map.getPointsOnTheMap());
    }
    private Object[] isOverLappingInfos(Map map, Point[] pointsOnTheMap){
        Object[] values = new Object[2];
        Point[] points = pointsOnTheMap;
        values[1] = points;
        int dotSize = getDrawArea().getDotSize();
        for(int index = 0; index < points.length; index++){
            int xCoordinate = points[index].getX();
            int yCoordinate = points[index].getY();
            boolean isControledCols = false;
            for(int index2 = 1; index2 < dotSize; index2++){
                if(!isControledCols){
                    if(map.getArea()[yCoordinate + index2][xCoordinate] != 0){
                        values[0] = true;
                        return values;
                    }
                }
                else{
                    if(map.getArea()[yCoordinate][xCoordinate + index2] != 0){
                        values[0] = true;
                        return values;
                    }
                }
            }
                isControledCols = true;
        }
        values[0] = false;
        return values;
    }*/
    

//ERİŞİM YÖNTEMLERİ:
    public ArrayList<City> getCities() {
        if(cities == null){
            cities = new ArrayList<City>();
        }
        return cities;
    }
    public HashMap<String, Point> getPointsOfCities() {
        if(pointsOfCities == null){
            pointsOfCities = new HashMap<String, Point>();
        }
        return pointsOfCities;
    }
    public HashMap<String, Color> getClrCities() {
        if(clrCities == null){
            clrCities = new HashMap<String, Color>();
        }
        return clrCities;
    }
    public Map getMap(){
        return map;
    }
    public DrawPanel getDrawArea() {
        return drawArea;
    }
}