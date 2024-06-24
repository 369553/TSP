package Views;

import Control.GUISeeming;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import com.sun.javafx.geom.Line2D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

/*BİLGİLER: Bu sınıf noktaların çizimini yapmaktadır
draw : çizim aracı
points : noktaların bilgileri
pointShapes : grafiksel nokta şekilleri listesi
paintComponent : çizimin yapıldığı fonksiyondur
 */
public class DrawPanel extends JPanel{
    Graphics2D draw;
    Point[] points;
    Shape[] pointShapes;
    ArrayList<Line2D> lines;
    ArrayList<DrawStr> texts;
    HashMap<String, Color> colors = GUISeeming.getColorsForDrawPanel();
    int[] selectedRoutePartNumbers = null;
    int dotSize = 10;

    public DrawPanel(int width, int height){
        setPreferredSize(new Dimension(width, height));
        GUISeeming.appGUI(this);
    }
    public DrawPanel (Point[] points){
        this.points = points;
        pointShapes = new Shape[points.length];
        GUISeeming.appGUI(this);
    }
    public DrawPanel (Point[] points, Dimension dimension){
        this.points = points;
        pointShapes = new Shape[points.length];
        setPreferredSize(dimension);
        GUISeeming.appGUI(this);
    }
    public DrawPanel (Point point){
        this.points = new Point[1];
        this.points[0] = point;
        pointShapes = new Shape[1];
        GUISeeming.appGUI(this);
    }

//İŞLEM YÖNTEMLERİ:
    @Override
    public void paintComponent(Graphics gr){
        super.paintComponent(gr);
        draw = (Graphics2D) gr;
    //    System.out.println("Ekranda kaç defa bu yazı çıktı?");
        draw.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        drawLines();
        drawPoints();
        drawTexts();
        getParent().repaint();
    }
    public void selectLines(int[] stepNumbers){
        for(int stepper : stepNumbers){
        if(stepper >= getLines().size())
            return;
        }
        selectedRoutePartNumbers = null;//BUNA GEREK YOK ASLINDA
        selectedRoutePartNumbers = stepNumbers;
    }
    public void setSelectedColor(Color clrSelectedLine){
        colors.put("selectedTextColor", clrSelectedLine);
    }
    public void addString(String text, float xCoordinate, float yCoordinate){
        getTexts().add(new DrawStr(text, xCoordinate, yCoordinate));
    }
    public void addLineToDrawPanel(double x1, double y1, double x2, double y2){
        getLines().add(new Line2D((float)x1, (float)y1, (float)x2, (float)y2));
    }
    public void updateDrawPanel(Point[] points){
        this.points = points;
        this.pointShapes = new Shape[points.length];
        this.lines = null;
    }
    public void updateDrawPanel(Point[] points, ArrayList<Line2D> lines){
        updateDrawPanel(points);
        this.lines = lines;
        this.texts = null;
    }
    public void updateDrawPanel(Point[] points, ArrayList<Line2D> lines, ArrayList<DrawStr> texts){
        updateDrawPanel(points, lines);
        this.texts = texts;
    }
    public void changeColorOfText(Color textColor){
        colors.put("textColor", textColor);
    }
    public void setDotSize(int dotSize) {
        this.dotSize = dotSize;
    }
    public void setLines(ArrayList<Line2D> lines){
        this.lines = lines;
    }
    //ARKAPLAN İŞLEM YÖNTEMLERİ:
    private void drawPoints(){
        if(points == null)
            return;
        for(int index = 0; index < points.length; index++){
//            System.out.println("points[" + index + "].getX() : " + points[index].getX() + "\n" + "points[" + index + "].getY() : " + points[index].getY());
//            pointShapes = null;
            pointShapes[index] = new Ellipse2D.Double(points[index].getX(), points[index].getY(), dotSize, dotSize);
            draw.setPaint(points[index].getColor());
            draw.fill(pointShapes[index]);
            //draw.draw(pointShapes[index]);
        }
    }
    private void drawLines(){
        if(lines == null)
            return;
        getDraw().setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Line2D[] currentLines = new Line2D[getLines().size()];
        getLines().toArray(currentLines);
        boolean choosed = false;
        if(selectedRoutePartNumbers != null)
            choosed = true;
        for(int index = 0; index < lines.size(); index++){
            draw.setPaint(colors.get("lineColor"));
            if(choosed){
                for(int index2 = 0; index2 < selectedRoutePartNumbers.length; index2++){
                    if(index == selectedRoutePartNumbers[index2]){
                        draw.setPaint(colors.get("selectedLineColor"));
                    }
                }
            }
            draw.drawLine((int) currentLines[index].x1,
                    (int) currentLines[index].y1,
                    (int) currentLines[index].x2,
                    (int) currentLines[index].y2);
        }
    }
    private void drawTexts(){
        if(texts == null)
            return;
        for(int index = 0; index < texts.size(); index++){
            draw.setPaint(colors.get("textColor"));
            draw.drawString(texts.get(index).text, texts.get(index).xCoordinate, texts.get(index).yCoordinate);
        }
    }

//ERŞİŞM YÖNTEMLERİ:
    public Graphics2D getDraw() {
        if(draw == null){
            draw = (Graphics2D) this.getGraphics();
        }
        return draw;
    }
    public ArrayList<Line2D> getLines() {
        if(lines == null){
            lines = new ArrayList<Line2D>();
        }
        return lines;
    }
    public ArrayList<DrawStr> getTexts() {
        if(texts == null){
            texts = new ArrayList<DrawStr>();
        }
        return texts;
    }
    public HashMap<String, Color> getColors() {
        if(colors == null){
            colors = new HashMap<String, Color>();
            colors.put("backgroundColor", Color.decode("#F5D5DC"));
            colors.put("textColor", Color.decode("#DBDF00"));
            colors.put("lineColor", Color.decode("#DB6FF0"));
            colors.put("selectedLineColor", Color.decode("#1B9FF0"));
        }
        return colors;
    }
    public int getDotSize() {
        return dotSize;
    }
    
}