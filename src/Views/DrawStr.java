package Views;

public class DrawStr{
    String text;
    float xCoordinate, yCoordinate;

    public DrawStr(String text, float xCoordinate, float yCoordinate){
        this.text = text;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

//İŞLEM YÖNTEMLERİ:
    public void setText(String text){
        this.text = text;
    }
    public void setxCoordinate(float xCoordinate){
        this.xCoordinate = xCoordinate;
    }
    public void setyCoordinate(float yCoordinate){
        this.yCoordinate = yCoordinate;
    }
    @Override
    public String toString(){
        return "DrawStr{" + "text=" + text + ", xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + '}';
    }

//ERİŞİM YÖNTEMLERİ:
    public String getText(){
        return text;
    }
    public float getxCoordinate(){
        return xCoordinate;
    }
    public float getyCoordinate(){
        return yCoordinate;
    }
}
