package Views;

import java.awt.Color;

/*BİLGİLER: Bu sınıf bir noktayı modellemektedir.
x : noktanın x düzlemindeki koordinatı
y : noktanın y düzlemindeki koordinatı
name : noktanın ismi
color : noktanın rengi
*/
public class Point {
    int x;
    int y;
    String name;
    Color color;

//BAŞLANGIÇ YÖNTEMLERİ:
    public Point (int x, int y, String name, String color){
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = Color.decode(color);
    }
    public Point (int x, int y, String name, Color color){
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = color;
    }
    public Point (int x, int y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = Color.decode("#e67a7a");
    }

//İŞLEM YÖNTEMLERİ:
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void setColor(String color) {
        this.color = Color.decode(color);
    }

//ERİŞİM YÖNTEMLERİ:
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getName() {
        if (name == null){
            name = new String("");
        }
        return name;
    }
    public Color getColor() {
        if (color == null){
            color = Color.decode("#DC143C");
        }
        return color;
    }
}
