package Views;

import java.awt.BorderLayout;
import java.util.Random;
/*BİLGİLER: Bu sınıf noktaların oluşturulmasını, çizim için gereken ortamının hazırlanması görevlerini üstleniyor
RandomPosition() : Noktaların birbirleriyle çakışmayacak şekilde ekrana yerleştirilmesi için gereken koordinatları rastgele olarak üretir.

*/
public class MainView {
    Point[] points;
    int[][] coordinates;
    
    public MainView(){
        //coordinates = RandomPosition(22);//RandomPosition algoritması yeniden denenmeli = Ekran dışına taşıyor ve birbirlerinin üzerine geliyor
        
        MainFrame.getFrame_Main().setVisible(true);
    }
    
    
    /*
    Fonksiyonun dinamik olması gereken özellikleri:
    1- Verilen nokta sayısına göre noktaların boyutları ayarlanmalı
    2- Ekran boyutuna göre noktaların boyutu ayarlanmalı
    */
    public int[][] RandomPosition(int number){
        int totalX, totalY, total, xPos, yPos/*, size, minSize, minDistance = 10*/;
        Random r = new Random();
        int[] posXs = new int[number];
        int[] posYs = new int[number];
        totalX = MainFrame.getFrame_Main().getWidth() - 10;//Çizim yapılacak panel genişliği, DAHA SONRA BU HESAPLAMA DEĞİŞTİRİLMELİ
        totalY = MainFrame.getFrame_Main().getHeight() - 10;//Çizim yapılacak panel yüksekliği, DAHA SONRA BU HESAPLAMA DEĞİŞTİRİLMELİ
        for (int index = 0; index < number ; index++){
            do{
                xPos = r.nextInt(totalX);
                posXs[index] = xPos;
            }
            while (xPos < 10 || checkNumber (xPos, posXs, index) == true/* || xPos>totalX*/);
        }
        for (int index = 0; index < number ; index++){
            do{
                yPos = r.nextInt(totalY);
                posYs[index] = yPos;
            }
            while (yPos < 10 || checkNumber (yPos, posYs, index) == true/* || yPos>totalY*/);
        }
        int[][] coordinates = new int[number][2];
        for (int index = 0; index < number; index++){
            coordinates[index][0] = posXs[index];
            coordinates[index][1] = posYs[index];
            //System.out.println("coordinates[" + index + "][0] = " + posXs[index] + "\ncoordinates[" + index + "][1] = " + posYs[index] + "\n\n");
        }
        return coordinates;
        
        /*if (totalX <= totalY)
            total = totalX;
        else
            total = totalY;*/
        /*minSize = ((number * 2) + 1) * 10;
        size = total - minSize;
        size = (int) (size / (number + (number * 0.3) ));
        minDistance = (int) (minDistance + (size * 0.3));
        size = 10 + size;
        System.out.println("size : " + size + "\nminDistance : " + minDistance);*/
    }
    
    public boolean checkNumber (int Pos, int[] PosArray, int lastPoint){
        for (int index = 0; index < lastPoint; index++){
              if (PosArray[index] == Pos)//POsArray[index]'in kapsama alanı, Pos'un kapsama alanınan eşit mi? kontrolünü sağlayacak bir fonksiyon lazım
                  return true;
        }
        return false;
    }
    
    public Point[] getPoints(){
        points = new Point[getCoordinates().length];
        for (int index = 0; index < points.length; index++){
            points[index] = new Point(coordinates[index][0],coordinates[index][1],"A");
        }
        return points;
    }

    public int[][] getCoordinates() {
        if(coordinates == null){
            coordinates = new int[3][2];
            coordinates[0][0] = 400;
            coordinates[0][1] = 420;
            coordinates[1][0] = 550;
            coordinates[0][1] = 540;
            coordinates[2][0] = 750;
            coordinates[2][1] = 740;
        }
        return coordinates;
    }

    public void setCoordinates(int[][] coordinates) {
        this.coordinates = coordinates;
    }
}
