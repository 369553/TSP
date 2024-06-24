package Base;

import Algorithms.SimulatedAnnealing;
import Algorithms.UsefulMethods;
import Views.EntryPage;


public class TSPSoftware{

    public static void main(String[] args){
        City[] cities = produceRandomCity(12);
        Problem problem = new Problem(cities, cities[0]);
        UsefulMethods.print_Matrix(problem.getCostTable());
        problem.setAlgorithm(new SimulatedAnnealing(problem));
        Route r = problem.findRoute();
//        r.showRoute();
//        problem.findRoute();
//        EntryPage page = new EntryPage();
//        page.setVisible(true);
    }
    
    public static City[] produceRandomCity(int cityNumber){
        City[] cities = new City[cityNumber];
        for(int sayac = 0; sayac < 12; sayac++){
            cities[sayac] = new City("A" + sayac, sayac + (Math.random() * 300), sayac + (Math.random() * 300));
        }
        return cities;
    }
}