package Algorithms;

import Base.Problem;
import Base.Route;
import java.util.ArrayList;

public class SimulatedAnnealing extends IAlgorithm{
    float heat, meanDistance, maxHeat, stepDegree;//heat = ısı, meanDistance = ortalama mesâfe, maxHeat = 'heat' için azamî değer, stepDegree = Her adımdaki soğuma derecesi
    float[][] cost = problem.getCostTable();
    //ArrayList<MiniRoute> possibleRoutes;//Muhtemel güzergâharı saklayan değişken
    MiniRoute mainRoute;//Hangi adımda isek o adımdaki en kısa güzergâh

    public SimulatedAnnealing(Problem problem){
        super(problem);
        this.strName = "BenzetimliTavlama";
        recognizeProblem();
        System.out.println("meanDistance (ortalama mesâfe) : " + meanDistance);
        System.out.println("maxHeat (azamî ısı değeri) : " + maxHeat);
        System.out.println("heat (Mevcut ısı): " + heat);
        System.out.println("stepDegree (Her adımdaki soğuma miktarı) : " + stepDegree);
        System.out.println("\n\n\n****************************************\n\n\n");
//        UsefulMethods.print_Matrix(cost);
    }

//İŞLEM YÖNTEMLERİ:
    @Override
    public Route runAlgorithm(){
        MiniRoute route = new MiniRoute(problem.getStartCity().getId());
        ArrayList<MiniRoute> possibleRoutes = new ArrayList<MiniRoute>();
        possibleRoutes.add(route);
        for(int sayac = 0; sayac < problem.getCityIDs().length; sayac++){
            ArrayList<MiniRoute> possibleNew = new ArrayList<MiniRoute>();
            for(int s2 = 0; s2 < possibleRoutes.size(); s2++){
                ArrayList<MiniRoute> possibleNewForOne = runAlgorithmForOneIteration(possibleRoutes.get(s2));
                for(MiniRoute r : possibleNewForOne){
                    possibleNew.add(r);
                }
                if(s2 == possibleRoutes.size() - 1){
                    findBestMiniRoute(possibleNew);
                    possibleRoutes = eliminatePossibleRoutes(possibleNew);
//                    System.out.println("Döngüdeki en iyi bulunmuş." + possibleRoutes.size());
                    break;
                }
            }
            cooling();
            if(sayac == problem.getCityIDs().length - 1){//Tüm işlemler bittiğinde, döngüden çıkmadan önce
                findBestMiniRoute(possibleRoutes);
            }
        }
        mainRoute.print();
        return null;
    }
    public void setStepDegree(int stepDegree){
        if(stepDegree > maxHeat)//Her adımdaki soğuma derecesi azamî ısı miktarından fazla olamaz
            return;
        this.stepDegree = stepDegree;
    }
    //ARKAPLAN İŞLEM YÖNTEMLERİ:
    private ArrayList<MiniRoute> findNextStepWithTolerance(MiniRoute route){
        ArrayList<MiniRoute> possibleRoutes = new ArrayList<MiniRoute>();
        float minCost = Float.MAX_VALUE;
        int minCostCityID = -1;
        int currentStep = route.getLastCityID();
//        System.out.println("route.getLastCityID() : " + route.getLastCityID());
        MiniRoute firstRoute = route;//Şimdi route'u klonladım mı?
//        System.out.println("firstRoute.adres : " + firstRoute.hashCode());
//        System.out.println("route.adres : " + route.hashCode());
        for(int sayac = 0; sayac < cost.length; sayac++){
            if(sayac == currentStep)
                continue;
            if(cost[currentStep][sayac] < minCost && isNotVisited(sayac, firstRoute)){
//                System.out.println("İstenilen yere gelindi");
                minCost = cost[currentStep][sayac];
                minCostCityID = sayac;
                System.out.println("minCostCityID " + minCostCityID + "yapıldı; currentStep : " + currentStep);
            }
        }
        if(firstRoute.addNewVisit(minCostCityID, cost[currentStep][minCostCityID])){
//            System.out.println("firstRoute'a yeni belde eklendi");
            possibleRoutes.add(firstRoute);
            minCost = cost[currentStep][minCostCityID];
            //Aşağıdaki koda gerek var mı ki?
            /*if(mainRoute.getTotalCost() > firstRoute.getTotalCost())
                mainRoute = firstRoute;*/
        }
        for(int sayac = 0; sayac < cost.length; sayac++){
            if(sayac == currentStep)
                continue;
            if(cost[currentStep][sayac] < minCost + heat && isNotVisited(sayac, firstRoute)){
                MiniRoute r = new MiniRoute(route);
                r.addNewVisit(sayac, cost[currentStep][sayac]);
                possibleRoutes.add(r);
            }
        }
//        System.out.println("possibleRoutes.size : " + possibleRoutes.size());
        return possibleRoutes;
    }
    private MiniRoute findBestMiniRoute(ArrayList<MiniRoute> routes){
        MiniRoute[] rs = new MiniRoute[routes.size()];
        routes.toArray(rs);
        mainRoute = rs[0];
        for(int s2 = 1; s2 < rs.length; s2++){
            if(rs[s2].getTotalCost() < mainRoute.getTotalCost())
                mainRoute = rs[s2];
        }
        return mainRoute;
    }
    private boolean isNotVisited(int cityID, MiniRoute route){
        for(int sayac = 0; sayac < route.getRoute().length; sayac++){
            if(route.getRoute()[sayac] == cityID)
                return false;
        }
        return true;
    }
    private ArrayList<MiniRoute> runAlgorithmForOneIteration(MiniRoute route){
        ArrayList<MiniRoute> extractedNewPossibleRoutes = new ArrayList<MiniRoute>();
        extractedNewPossibleRoutes = findNextStepWithTolerance(route);
//        System.out.println("extractedNewPossibleRoutes.size : " + extractedNewPossibleRoutes.size());
//        if(extractedNewPossibleRoutes.size() > 0)
//            System.out.println("Çıkartılan bir güzergâh : " + extractedNewPossibleRoutes.get(0).route);
        return extractedNewPossibleRoutes;
    }
    private ArrayList<MiniRoute> eliminatePossibleRoutes(ArrayList<MiniRoute> main){
        float min = mainRoute.getTotalCost() + (heat * mainRoute.route.length);
        ArrayList<MiniRoute> choosedRoutes = new ArrayList<MiniRoute>();
        for(MiniRoute route : main){
            if(route.getTotalCost() <= min)
                choosedRoutes.add(route);
        }
        return choosedRoutes;
    }
    private void heating(int number){
        if(number < maxHeat)
            return;
        heat += number;
    }
    private void recognizeProblem(){
        int wayCounter;
        float meanForHeat, total = 0;
        for(int sayac = 0; sayac < problem.getCostTable().length; sayac++){
            for(float f : problem.getCostTable()[sayac])
                total += f;
        }
        wayCounter =  (problem.getCostTable().length * problem.getCostTable().length) - problem.getCostTable().length;
        meanDistance = total / wayCounter;//Ortalama mesâfe
        maxHeat = meanDistance / 2;
        heat = maxHeat;
        stepDegree = heat / 10;
    }
    private void cooling(){
        heat -= stepDegree;
    }
    private void cooling(int stepNumber){
        for(int sayac = 0; sayac < stepNumber; sayac++){
            if(heat > 0)
                cooling();
        }
    }

//ERİŞİM YÖNTEMLERİ:
    public float getHeat(){
        return heat;
    }
    public float getMaxHeat(){
        return maxHeat;
    }
    public float getStepDegree(){
        return stepDegree;
    }
    
    //ALT SINIF:
    private class MiniRoute{
        int[] route;//Ziyâret sırasına göre beldelerin 'ID'lerini saklar
        float totalCost;//'route' değişkeninin toplâm mâliyyeti
        int lastCityID;//Ziyâret edilen son beldenin belde numarası
        
        public MiniRoute(int startCityID){
            route = new int[1];
            route[0] = startCityID;
            lastCityID = startCityID;
        }
        public MiniRoute(MiniRoute route){
            this.route = route.route;
            this.totalCost = route.getTotalCost();
            this.lastCityID = route.getLastCityID();
        }

    //İŞLEM YÖNTEMLERİ:
        public void setRoute(int[] route){
            this.route = route;
        }
        public boolean addNewVisit(int cityID, float costFromLastCity){
            if(costFromLastCity <= 0.0){
                System.out.println("Mâliyyet gerçekçi olmadığından eklenemedi");
                return false;
            }
            for(int sayac = 0; sayac < route.length; sayac++){
                if(route[sayac] == cityID){//Ziyâret listesindeki beldenin yeniden ziyâret listesine eklenmesine izin verme
                    System.out.println("Daha önce ziyaret tespit edildiğinden eklenemedi");
                    return false;
                }
            }
            int[] vList = new int[route.length +  1];//Dizinin uzunluğunu bir arttır
            for(int sayac = 0; sayac < route.length; sayac++){//Ziyâret edilen beldeleri yeni diziye aktar
                vList[sayac] = route[sayac];
            }
            vList[route.length] = cityID;//Yeni beldeyi ziyâret dizisine ekle
            lastCityID = cityID;//Ziyâret edilen son beldenin numarasını güncelle
            totalCost += costFromLastCity;//Toplam mâliyyeti güncelle
            return true;
        }
        public void print(){
            System.out.println("Güzergâh:");
            for(int sayac = 0; sayac < route.length; sayac++){
                System.out.println("[" + route[sayac] + "] ");
            }
            System.out.println("-----------------------------------------------------------");
        }

    //ERİŞİM YÖNTEMLERİ:
        public int[] getRoute(){
            return route;
        }
        public float getTotalCost(){
            return totalCost;
        }
        public int getLastCityID(){
            return lastCityID;
        }
    }
}