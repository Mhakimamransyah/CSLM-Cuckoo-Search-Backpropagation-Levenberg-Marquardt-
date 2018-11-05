/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.NeuralNetwork;
import Jama.Matrix;
/**
 *
 * @author M.Hakim Amransyah
 */
public class Matriks {
    
    public double[][] getMatriksHessian(double jacobian[][]){
        return this.perkalianMatriks(this.transposeMatriks(jacobian), jacobian);
    }
    
    public double[][] getMatriksDumping(double hessian[][], double learning_rate){
        return this.perkalianMatriksSkalar(this.getMatriksIdentitas(hessian.length),learning_rate);
    }
    
    public double[][] getMatriksGradient(double jacobian[][],double error[][]){
        return this.perkalianMatriks(this.transposeMatriks(jacobian), error);   
    }
    
    public double[][] getMatriksInvers(double hessian[][], double dumping[][]){
       return this.inverseMatriks(this.penjumlahanMatriks(hessian, dumping));
    }
    
    public double[][] getMatriksDelta( double matriks_invers[][], double gradient[][]){
       return this.perkalianMatriks(matriks_invers,gradient);
    }
    
    public double[][] kosongkanMatriks(double B[][]){
        for(int i=0;i<B.length;i++){
            for(int j=0;j<B[0].length;j++){
                B[i][j] = 0;
            }
        }
        return B;
    }
    
    public double[][] inverseMatriks(double B[][]){
        Matrix X = new Matrix(B);
        return X.inverse().getArray();
    }
    
     public double[][] perkalianMatriks(double A[][],double B[][]){
         double res[][] = new double[A.length][B[0].length];
         double X1[][] = this.copyMatriks(A);
         double X2[][] = this.copyMatriks(B);
         double temp;
         
         for(int i=0;i<X1.length;i++){
             for(int j=0;j<X2[0].length;j++){
                 temp = 0;
                 for(int k=0;k<X2.length;k++){
                    temp = temp + (X1[i][k]*X2[k][j]); 
                 }
                 res[i][j] = temp;
             }
         }
         return res;
     }
       
    public double[][] getMatriksIdentitas(int dimensi){
        double C[][] = new double[dimensi][dimensi];
        for(int i=0;i<C.length;i++){
            for(int j=0;j<C[0].length;j++){
                if(i == j){
                    C[i][j] = 1;
                }
            }
        }
        return C;
    } 
     
    public double[][] transposeMatriks(double[][] A){
       double matriks[][] = this.copyMatriks(A);
       double transpose[][] = new double[matriks[0].length][matriks.length];
       for(int i=0;i<matriks.length;i++){
           for(int j=0;j<matriks[0].length;j++){
               transpose[j][i] = matriks[i][j];
           }
       }    
       return transpose;
    }
     
     public double[][] penjumlahanMatriks(double A[][],double B[][]){
         double res[][] = new double[A.length][A[0].length];
         double X1[][] = this.copyMatriks(A);
         double X2[][] = this.copyMatriks(B);
         
         for(int i=0;i<res.length;i++){
             for(int j=0;j<res[0].length;j++){
                 res[i][j] = A[i][j] + B[i][j];
             }
         }
         
         return res;
     }
      
    public double[][] perkalianMatriksSkalar(double B[][],double X){
        double A[][] = this.copyMatriks(B);
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                A[i][j] = A[i][j] * X;
            }
        }
        return A;
    }
    
     private double[][] copyMatriks(double B[][]){
        double A[][] = new double[B.length][B[0].length];
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
               A[i][j] = B[i][j];
            }
        }
        return A;
    }
    
    public void cetakMatriks(double A[][]){
        System.out.println("");
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                System.out.print(A[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
