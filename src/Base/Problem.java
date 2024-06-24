package Base;

import Algorithms.IAlgorithm;
import Algorithms.LineerAlgorithm;
import Algorithms.UsefulMethods;

public class Problem{
    City[] cities;
    City startCity = null;
    int[] cityIDs;
    float[][] costTable;
    boolean isAppSensity = true, calculateWithDistMatrix = true, isCalculated = false;
    int sensity = 3;
    IAlgorithm algorithm;
    private Route lastRoute;

//ÜRETİM YÖNTEMLERİ:
    public Problem(City[] cities, City startCity, boolean isAppSensity, int sensity){
        this.cities = cities;
        this.startCity = startCity;
        this.isAppSensity = isAppSensity;
        this.sensity = sensity;
        assignCityIDs();
        assignCostTable();
    }
    public Problem(City[] cities, City startCity){
        this(cities, startCity, false, 3);
    }
    public Problem(City[] cities, City startCity, float[][] costTable, boolean isAppSensity, int sensity){
        this.cities = cities;
        this.startCity = startCity;
        this.isAppSensity = isAppSensity;
        this.sensity = sensity;
        this.costTable = costTable;
        assignCityIDs();
    }
    public Problem(City[] cities, City startCity, float[][] costTable){
        this(cities, startCity, costTable, false, 3);
    }

//İŞLEM YÖNTEMLERİ:
    //DÜZENLENMELi:
    public void setStartCity(City startCity){
        if(this.startCity != null)
            if(startCity == this.startCity)
                return;
        if(this.startCity != null){
            cityIDs = UsefulMethods.addVariableToArray(cityIDs, this.startCity.getId(), getCityOrder(this.startCity.getId()));
        }
        cityIDs = UsefulMethods.extractVariableFromArray(cityIDs, startCity.getId());
        this.startCity = startCity;
        isCalculated = false;
        lastRoute = null;
    }
    public void setAlgorithm(IAlgorithm algorithm){
        if(algorithm == this.algorithm)
            return;
        this.algorithm = algorithm;
        lastRoute = null;
    }
    public void setSensity(int sensity){
        if(sensity == this.sensity)
            return;
        this.sensity = sensity;
        lastRoute = null;
    }
    public void setIsAppSensity(boolean isAppSensity){
        if(isAppSensity == this.isAppSensity)
            return;
        this.isAppSensity = isAppSensity;
        lastRoute = null;
    }
    public void addCity(City city){
        cities = UsefulMethods.addVariableToArray(cities, city, cities.length);
        cityIDs = (int[]) UsefulMethods.addVariableToArray(cityIDs, city.getId(), cityIDs.length);
        float[] distancesToOther = new float[costTable.length], distancesToThis = new float[costTable.length];
        for(int index = 0; index < cities.length - 1; index++){
            if(isAppSensity)
                distancesToOther[index] = city.distanceTo(cities[index], sensity);
            else
                distancesToOther[index] = city.distanceTo(cities[index]);
        }
        distancesToThis = distancesToOther;
        costTable = UsefulMethods.addNewColToMatrixWithAddingToOtherRows(costTable, distancesToOther, distancesToThis);
        lastRoute = null;
    }
    public void takeOutCity(City city){
        boolean setNewStartCity = false;
        City[] citiesNew = UsefulMethods.extractVariableFromArray(cities, city);
        if(citiesNew == null)
            return;
        if(!(city.getId() == startCity.getId()))
            cityIDs = UsefulMethods.extractVariableFromArray(cityIDs, city.getId());
        else
            setNewStartCity = true;
        costTable = UsefulMethods.removeLineAndColumnFromMatrix(costTable, getCityOrder(city.getId()));
        cities = citiesNew;
        if(setNewStartCity){
            startCity = null;
            setStartCity(cities[0]);
        }
        lastRoute = null;
    }
    public Route findRoute(){
        if(lastRoute != null)
            return lastRoute;
        else
            return getAlgorithm().runAlgorithm();
    }
    public City findByID(int orderNo){//İl ID'sini alıp ili City tipiyle döndürüyor.
        for (int index = 0; index < cities.length; index++){
            if(cities[index].getId() == orderNo)
                return cities[index];
        }
        System.out.println(orderNo + " numaralı il listede yok!");
        return null;
    }
    public int getCityOrder(int cityID){
//        System.out.println("belde no : " + cityID);
        for(int index = 0; index < cities.length; index++){
            if(cities[index].getId() == cityID)
                return index;
        }
        return -1;
    }
    //ARKAPLAN İŞLEM YÖNTEMLERİ:
    private void assignCityIDs(){
        int sayac = 0;
        cityIDs = new int[cities.length - 1];
    //    System.out.println("Sıralaması karıştırılacak belde sayısı : " + cityIDs.length);
        for(int index = 0; index < cities.length; index++){
            if(cities[index].getId() == startCity.getId()){
                continue;
            }
            cityIDs[sayac] = cities[index].getId();
            sayac++;
        }
    }
    private void assignCostTable(){
        costTable = new float[cities.length][cities.length];
        for(int index = 0; index < cities.length; index++){
            for(int index2 = 0; index2 < cities.length; index2++){
                if(index == index2){
                    costTable[index][index2] = 0;
                    continue;
                }
                if(index > index2){
                    costTable[index][index2] = costTable[index2][index];
                    continue;
                }
                if(isAppSensity)
                    costTable[index][index2] = cities[index].distanceTo(cities[index2], sensity);
                else
                    costTable[index][index2] = cities[index].distanceTo(cities[index2]);
            }
        }
//        UsefulMethods.print_Matrix(costTable);
//        System.out.println("Ortalama : " + recognize());
    }
    /*private float recognize(){
        int wayCounter;
        float meanForHeat, total = 0;
        for(int sayac = 0; sayac < this.getCostTable().length; sayac++){
            for(float f : this.getCostTable()[sayac])
                total += f;
        }
        wayCounter =  (this.getCostTable().length * this.getCostTable().length) - this.getCostTable().length;
        meanForHeat = total / wayCounter;//Ortalama mesâfe
        return meanForHeat;
    }*/

//ERİŞİM YÖNTEMLERİ:
    public City[] getCities(){
        return cities;
    }
    public City getStartCity(){
        return startCity;
    }
    public float[][] getCostTable(){
        return costTable;
    }
    public boolean isIsAppSensity(){
        return isAppSensity;
    }
    public int getSensity(){
        return sensity;
    }
    public IAlgorithm getAlgorithm(){
        if(algorithm == null){
            if(isAppSensity)
                algorithm = new LineerAlgorithm(this);
        }
        return algorithm;
    }
    public int[] getCityIDs() {
        return cityIDs;
    }
    public Route getLastRoute() {
        return lastRoute;
    }
}