package Base;

import Control.IDARE;
import Views.MainFrame;
//DÜZENLENMELİ
public class Test {

    public Test() {
//        idaredenBagimsizTest();
        sistemTesti();
    }

//TEST YÖNTEMLERİ:
    public void idaredenBagimsizTest(){
        Map map = new Map("Küçük harita", 500, 300);
        City ct01 = new City("Tokat", 220.0, 100.0);
        City ct02 = new City("Ankara", 140.0, 170.0);
        City ct03 = new City("İstanbul", 20.0, 20.0);
        City ct04 = new City("Of", 260.0, 15.0);
        City ct05 = new City("Osmaniye", 400.0, 280.0);
        City ct06 = new City("Bursa", 40.0, 40.0);
        City ct07 = new City("Çanakkale", 2.0, 30.0);
        IDARE.startIDARE(map);
        IDARE.getIdare().showNotify(IDARE.getIdare(), "İlk mesaj", "LittlePoint");

        /*
        City a = new City("A", 15, 17);
        City b = new City("B", 19, 20);
        */

        IDARE.getIdare().addCity(ct01);
        IDARE.getIdare().addCity(ct02);
        IDARE.getIdare().addCity(ct03);
        IDARE.getIdare().addCity(ct04);
        IDARE.getIdare().addCity(ct05);
        IDARE.getIdare().addCity(ct06);
        IDARE.getIdare().addCity(ct07);
        IDARE.getIdare().startSystem();

        IDARE.getIdare().updateIDARE();

        Problem problem = new Problem(new City[]{ct01, ct02, ct03, ct04, ct05, ct06, ct07}, ct01, true, 3);
//        problem.takeOutCity(ct01);
//        problem.takeOutCity(ct07);
        Route bestRoute = problem.findRoute();
//        problem.setSensity(1);
//        bestRoute = problem.findRoute();
//        problem.addCity(ct07);
//        bestRoute = problem.findRoute();
//        problem.setAlgorithm(new MinimumCostIsFirst(problem));
//        problem.setStartCity(ct07);
//        Route heuristicRoute = problem.findRoute();
        System.out.println("Toplam maliyet : " + bestRoute.getTotalCost());
        IDARE.getIdare().showRoute(bestRoute);
//        double sayi = 93.941 + 196.023 + 164.012 + 28.284 + 460.435 + 254.558;
//        sayi = UsefulMethods.roundNumber(sayi, 3);
//        System.out.println("sayi : " + sayi);
        MainFrame.getFrame_Main().repaint();
    }
    public void sistemTesti(){
        
    }
}
