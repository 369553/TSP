package Algorithms;

import Base.Problem;
import Base.Route;

public class LineerAlgorithm extends IAlgorithm{
    private Route[] routes;
    float[] costsOfRoutes;

    public LineerAlgorithm(Problem problem) {
        super(problem);
        strName = "Doğrusal";
    }

//İŞLEM YÖNTEMLERİ:
    @Override
    public Route runAlgorithm(){
        int[][] combines = combinateArray(problem.getCityIDs());
        routes = new Route[combines.length];
        for(int index = 0; index < combines.length; index++){
            int[] ready = UsefulMethods.addVariableToArray(UsefulMethods.addVariableToArray(combines[index], problem.getStartCity().getId(), 0), problem.getStartCity().getId(), problem.getCities().length);
                routes[index] = new Route(problem, ready);
        }
        calculateCosts();
        return findBestRoute();
    }
    //ARKAPLAN İŞLEM YÖNTEMLERİ:
    private int[][] combinateArray(int[] array){
        if(array.length == 1)
            return new int[][]{array};
        else{
            int[][] subArrays = combinateArray(UsefulMethods.takeSubArray(array, 1));
            int[][] value = new int[UsefulMethods.calculateFactoriel(array.length)][array.length];
            int sayac = 0;
            for (int[] subArray : subArrays) {
                for (int index2 = 0; index2 < array.length; index2++) {
                    value[sayac] = UsefulMethods.addVariableToArray(subArray, array[0], index2);
                    sayac++;
                    /*
                    1   2   3   ->  2   1   3   ->  2   3   1
                    1   3   2   ->  3   1   2   ->  3   2   1
                    */
                }
            }
            return value;
        }
    }
    private void calculateCosts(){
        costsOfRoutes = new float[routes.length];
        for(int index = 0; index < costsOfRoutes.length; index++){
            costsOfRoutes[index] = routes[index].getTotalCost();
        }
    }
    private Route findBestRoute(){
        double min = costsOfRoutes[0];
        int indexOfMinRoute = 0;
        for(int index = 1; index < costsOfRoutes.length; index++){
            if(costsOfRoutes[index] < min){
                min = costsOfRoutes[index];
                indexOfMinRoute = index;
            }
        }
        return routes[indexOfMinRoute];
    }

//ERİŞİM YÖNTEMLERİ:
}
