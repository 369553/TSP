package Algorithms;

import Base.City;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class UsefulMethods {
    private static NumberFormat setFormat = NumberFormat.getInstance(Locale.US);

    private UsefulMethods(){}

//İŞLEM YÖNTEMLERİ:
    public static int[] addVariableToArray(int[] array, int variable, int order){
        int[] value = new int[array.length + 1];
        value[order] = variable;
        //Eğer order < array.length ise order sırasından sonrakilerin atamasının yapılması gerekir
        for(int index = order + 1; index < value.length; index++){
            value[index] = array[index - 1];
        }
        //Eğer order > 0 ise order'a kadar olan atamaların yapılması gerekir.
        for(int index = 0; index < order; index++){
            value[index] = array[index];
        }
        return value;
    }
    public static Object[] addVariableToArray(Object[] array, Object variable, int order){
        Object[] value = new Object[array.length + 1];
        value[order] = variable;
        //Eğer order < array.length ise order sırasından sonrakilerin atamasının yapılması gerekir
        for(int index = order + 1; index < value.length; index++){
            value[index] = array[index - 1];
        }
        //Eğer order > 0 ise order'a kadar olan atamaların yapılması gerekir.
        for(int index = 0; index < order; index++){
            value[index] = array[index];
        }
        return value;
    }
    public static City[] addVariableToArray(City[] array, City variable, int order){
        City[] value = new City[array.length + 1];
        value[order] = variable;
        //Eğer order < array.length ise order sırasından sonrakilerin atamasının yapılması gerekir
        for(int index = order + 1; index < value.length; index++){
            value[index] = array[index - 1];
        }
        //Eğer order > 0 ise order'a kadar olan atamaların yapılması gerekir.
        for(int index = 0; index < order; index++){
            value[index] = array[index];
        }
        return value;
    }
    public static void print_Matrix(int[][] matrix){
        int index, index2;
        for ( index = 0 ; index < matrix.length ; index++ ) {
            System.out.print("\n");
            for ( index2 = 0 ; index2 < matrix[0].length ; index2++ ) {
                if(index2 == 0)
                    System.out.print("[");
                //   System.out.println("matris[" + index + "][" + index2 + "] = " + matrix[index][index2] ) ;
                System.out.print(matrix[index][index2]);
                if(index2 != matrix[0].length - 1)
                    System.out.print("\t");
                if(index2 == matrix[0].length - 1)
                    System.out.print("]");
            }
            System.out.print("\n");
        }
    }
    public static void print_Matrix(double[][] matrix){
        int index, index2;
        for ( index = 0 ; index < matrix.length ; index++ ) {
            System.out.print("\n");
            for ( index2 = 0 ; index2 < matrix[0].length ; index2++ ) {
                if(index2 == 0)
                    System.out.print("[");
                //   System.out.println("matris[" + index + "][" + index2 + "] = " + matrix[index][index2] ) ;
                System.out.print(matrix[index][index2]);
                if(index2 != matrix[0].length - 1)
                    System.out.print("\t");
                if(index2 == matrix[0].length - 1)
                    System.out.print("]");
            }
            System.out.print("\n");
        }
    }
    public static void print_Matrix(float[][] matrix){
        int index, index2;
        for ( index = 0 ; index < matrix.length ; index++ ) {
            System.out.print("\n");
            for ( index2 = 0 ; index2 < matrix[0].length ; index2++ ) {
                if(index2 == 0)
                    System.out.print("[");
                //   System.out.println("matris[" + index + "][" + index2 + "] = " + matrix[index][index2] ) ;
                System.out.print(matrix[index][index2]);
                if(index2 != matrix[0].length - 1)
                    System.out.print("\t");
                if(index2 == matrix[0].length - 1)
                    System.out.print("]");
            }
            System.out.print("\n");
        }
    }
    public static void printMatrixLikeList(Object[][] matrix){
        for(int index = 0; index < matrix.length; index++){
            for(int index2 = 0; index2 < matrix[0].length; index2++){
                System.out.println("mâliyet[" + index + "][" + index2 + "] = " + matrix[index][index2]);
            }
        }
    }
    public static void printMatrixLikeList(float[][] matrix){
        for(int index = 0; index < matrix.length; index++){
            for(int index2 = 0; index2 < matrix[0].length; index2++){
                System.out.println("mâliyet[" + index + "][" + index2 + "] = " + matrix[index][index2]);
            }
        }
    }
    public static void print_Array(int[] array){
        System.out.print("\n");
       for(int index = 0 ; index < array.length ; index++){
           System.out.print(array[index] + "\t");
       }
        System.out.print("\n");
   }
    public static void print_Array(float[] array){
        System.out.print("\n");
       for(int index = 0 ; index < array.length ; index++){
           System.out.print(array[index] + "\t");
       }
        System.out.print("\n");
   }
    public static int[] takeSubArray(int[] array, int startPoint){
        int[] value = new int[array.length - startPoint];
        for(int index = 0; index < value.length; index++){
            value[index] = array[index + startPoint];
        }
        return value;
    }
    public static float roundNumber(float number, int numberAfterDot){
        /*int decimal = (int) number;
        float fractional = number - decimal;
        for(int index = 0; index < fractional; index++){
        }*/
        setFormat.setMaximumFractionDigits(numberAfterDot);
        setFormat.setGroupingUsed(false);
        float value = number;
        String ready = setFormat.format(number); 
        value = Float.parseFloat(ready);
        //System.out.println("value : " + value);
        return value;
    }
    public static double roundNumber(double number, int numberAfterDot){
        /*int decimal = (int) number;
        float fractional = number - decimal;
        for(int index = 0; index < fractional; index++){
        }*/
        setFormat.setMaximumFractionDigits(numberAfterDot);
        setFormat.setGroupingUsed(false);
        double value = number;
        String ready = setFormat.format(number); 
        value = Double.parseDouble(ready);
        //System.out.println("value : " + value);
        return value;
    }
    public static int calculateFactoriel(int number){
        int total = 1;
        for(int index = number; index > 0; index--){
            total *= index;
        }
        return total;
    }
    public static float[][] removeLineAndColumnFromMatrix(float[][] matrix, int colRowNumber){//Matristen bir satırı(ve aynı sıradaki sütunu) çıkar
        float[][] value = new float[matrix.length - 1][matrix.length - 1];
        int sayac = 0, sayac2;
        for(int index = 0; index < matrix.length; index++){
            if(index == colRowNumber)
                continue;
            sayac2 = 0;
            for(int index2 = 0; index2 < matrix[0].length; index2++){
                if(index2 == colRowNumber)
                    continue;
                value[sayac][sayac2] = matrix[index][index2];
                sayac2++;
            }
            sayac++;
        }
        return value;
    }
    public static float[][] addNewColToMatrixWithAddingToOtherRows(float[][] mainMatrix, float[] addNewRow, float[] addNewCol){
        float[][] value = new float[mainMatrix.length + 1][mainMatrix[0].length + 1];
        //ÖNCEKİ DEĞERLERİ AKTAR
        for(int index = 0; index < mainMatrix.length; index++){
            for(int index2 = 0; index2 < mainMatrix[index].length; index2++){
                value[index][index2] = mainMatrix[index][index2];
            }
        }
        //HER SATIR SONUNA EKLE
        for(int index = 0; index < addNewCol.length; index++){
            value[index][value[index].length - 1] = addNewCol[index];
        }
        //EN SONA YENİ SATIR EKLE
        value[value.length - 1] = addNewRow;
        //YENİ SATIRI TÂNE TÂNE EKLE (UZUNLUKLAR EŞİT DEĞİLSE DE ÇALIŞIR Bİ İZNİLLÂH)
        /*int lastRowNumber = value.length - 1;
        for(int index = 0; index < value[lastRowNumber].length; index++){
            value[lastRowNumber][index] = addNewRow[index];
        }*/
        return value;
    }
    public static City[] extractVariableFromArray(City[] array, City variable){
        boolean isInclude = false;
        int sayac = 0;
        City[] value = new City[array.length - 1];
        for(int index = 0; index < array.length; index++){
            if(array[index] == variable){
                continue;
            }
            value[sayac] = array[index];
            sayac++;
        }
        return value;
    }
    public static int[] extractVariableFromArray(int[] array, int variable){
        boolean isInclude = false;
        int sayac = 0;
        int[] value = new int[array.length - 1];
        for(int index = 0; index < array.length; index++){
            if(array[index] == variable){
                continue;
            }
            value[sayac] = array[index];
            sayac++;
        }
        return value;
    }
    public static boolean checkIsVisited(ArrayList<Integer> visiteds, int order){
        if (visiteds.stream().anyMatch((orderNo) -> (orderNo == order))){//predicate'i öğren
            return true;
        }
        return false;
    }
}
