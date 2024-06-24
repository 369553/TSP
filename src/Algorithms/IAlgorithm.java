package Algorithms;

import Base.Problem;
import Base.Route;

public abstract class IAlgorithm {
    String strName;
    Problem problem;

    public IAlgorithm(Problem problem){
        this.problem = problem;
    }

//İŞLEM YÖNTEMLERİ:
    public abstract Route runAlgorithm();

//ERİŞİM YÖNTEMLERİ:
    public String getAlgorithmName() {
        return strName;
    }
}
