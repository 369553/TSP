package Algorithms;

import Base.Problem;
import Base.Route;
import java.util.ArrayList;
//Rota'ya costs gönderilebilir; ama costs doğru hesâplanmıyor gibi görünüyor; hatâyı bul bi iznillâh
public class MinimumCostIsFirst extends IAlgorithm{
    private Route route;
    private int[] orderList;
//    private float[] costs;
    float[][] costMatrix;
    int visitedNumber = 0;
    ArrayList<Integer> visiteds;

    public MinimumCostIsFirst(Problem problem) {
        super(problem);
        this.strName = "EnAzMaliyetEnÖnce";
        visiteds = new ArrayList<Integer>();
    }

//İŞLEM YÖNTEMLERİ:
    @Override
    public Route runAlgorithm(){
        orderList = new int[problem.getCities().length + 1];
//        costs = new float[orderList.length - 1];
        orderList[0] = problem.getStartCity().getId();
        costMatrix = problem.getCostTable();
        for(int index = 0; index < problem.getCityIDs().length; index++){
            int[] cityAndcityOrder = findMinimumCostForCurrentStep(problem.getCityOrder(orderList[index]));
            orderList[index + 1] = cityAndcityOrder[0];
            visiteds.add(cityAndcityOrder[1]);
        }
        orderList[orderList.length - 1] = problem.getStartCity().getId();
//        costs[costs.length - 1] = costMatrix[problem.getCityOrder(orderList[orderList.length - 2])][problem.getCityOrder(problem.getStartCity().getId())];
        return new Route(problem, orderList);
    }
    //ARKAPLAN İŞLEM YÖNTEMLERİ:
    private int[] findMinimumCostForCurrentStep(int currentStep){
        float min = Integer.MAX_VALUE;
        int indexOfMin = 0;
        int cityID = problem.getStartCity().getId();
        for(int index2 = 0; index2 < costMatrix[currentStep].length; index2++){
            if(currentStep == index2)
                continue;
            if(index2 == problem.getCityOrder(problem.getStartCity().getId()))
                continue;
            if(UsefulMethods.checkIsVisited(visiteds, index2))
                continue;
            if(costMatrix[currentStep][index2] < min){
                min = costMatrix[currentStep][index2];
                cityID = problem.getCities()[index2].getId();
                indexOfMin = index2;
            }
        }
//        costs[currentStep] = min;
//        System.out.println("costs[" + currentStep + "] = " + min + "\nindexOfMin : " + indexOfMin + "\nBelde no : " + cityID);
        return new int[]{cityID, indexOfMin};
    }

//ERİŞİM YÖNTEMLERİ:
}