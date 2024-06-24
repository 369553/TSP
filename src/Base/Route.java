package Base;

import Algorithms.UsefulMethods;


public class Route{
    private int[] route;
    private int[] routeAsOrders;
    private final Problem problem;
    float[] costs;
    float totalCost;

    public Route(Problem problem, int[] orderList) {
        this.problem = problem;
        route = orderList;
        assignRouteAsOrders();
        calculateTotalCost();
    }
    public Route(Problem problem, City[] orderList) {
        this.problem = problem;
        assignRoute(orderList);
        assignRouteAsOrders();
        calculateTotalCost();
    }
    public Route(Problem problem, int[] orderList, float[] costs) {
        this.problem = problem;
        route = orderList;
        assignRouteAsOrders();
        this.costs = costs;
        calculateTotalCost(costs);
    }

//İŞLEM YÖNTEMLERİ:
    //ARKAPLAN İŞLEM YÖNTEMLERİ:
    private void assignRouteAsOrders(){
        routeAsOrders = new int[route.length];
        for(int index = 0; index < routeAsOrders.length; index++){
            routeAsOrders[index] = problem.getCityOrder(route[index]);
        }
    }
    private void assignRoute (City[] orderList){
        route = new int[orderList.length];
        for(int index = 0; index < orderList.length; index++){
            route[index] = orderList[index].getId();
        }
    }
    private float getCost(City city1, City city2){//İki il arasındaki mâliyeti hesâplayıp döndürür
        if(problem.isAppSensity)
            return city1.distanceTo(city2, problem.sensity);
        else
            return city1.distanceTo(city2);
    }
    private float getCost(int city1Index, int city2Index){//İki il arasındaki mâliyet tablosundan alıp, döndürür
        return problem.costTable[city1Index][city2Index];
    }
    private void calculateTotalCost(){
        totalCost = 0;
        costs = new float[route.length - 1];
        for (int index = 1; index < route.length; index++){
            if(problem.calculateWithDistMatrix)
                costs[index - 1] = getCost(routeAsOrders[index - 1], routeAsOrders[index]);
            else
                costs[index - 1] = getCost(problem.findByID(route[index - 1]), problem.findByID(route[index]));
            totalCost += costs[index - 1];
        }
        if(problem.isAppSensity)
            totalCost = UsefulMethods.roundNumber(totalCost, problem.sensity);
    }
    private void calculateTotalCost(float[] costs){
        totalCost = 0;
        for(int index = 0; index < costs.length; index++){
//            System.out.println("costs[" + index + "] = " + costs[index]);
            totalCost += costs[index];
        }
    }
    public void showRoute(){//Rotayı illerin isimlerini yazarak gösterir
        System.out.println("Ziyâretin başlangıç noktası: " + problem.findByID(route[0]) + "isimli il");
        for(int index = 1; index < route.length; index++){
            System.out.println(index + ". ziyâret noktası: " + problem.findByID(route[index]) + "isimli il");
        }
    }

//ERİŞİM YÖNTEMLERİ:
    public float getTotalCost() {
        return totalCost;
    }
    public City[] getRoute(){
        City[] cities = new City[route.length];
        for(int index = 0; index < route.length; index++){
            cities[index] = problem.findByID(route[index]);
        }
        return cities;
    }
    public float getCost(int step){
        if(step < costs.length)
            return costs[step];
        else
            return 0;
    }
    public float getSubCost(int startStep, int finishStep){
        if(startStep < 0 || finishStep > costs.length)
            return 0;
        float selectedCost = 0;
        for(int index = startStep; index < finishStep; index++){
            selectedCost += costs[index];
        }
        if(problem.isAppSensity)
            selectedCost = UsefulMethods.roundNumber(selectedCost, problem.sensity);
        return selectedCost;
    }
    public int getStepNumber(){
        return route.length;
    }
}