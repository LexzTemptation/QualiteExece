/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author FELIPE.HERRERA
 */
public class modeloCalculadora {
    
    private int num1;
    private int num2;
    private int resultado;
    
    public modeloCalculadora(){}

    public modeloCalculadora(int num1, int num2, int resultado) {
        this.num1 = num1;
        this.num2 = num2;
        this.resultado = resultado;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }
    
    public int sumar(){
        this.resultado = this.num1 + this.num2;
        
        return this.resultado;
    }
    
    public int restar(){
        this.resultado = this.num1 - this.num2;
        
        return this.resultado;
    }
    
    public int multiplicar(){
        this.resultado = this.num1 * this.num2;
        
        return this.resultado;
    }
    
    public int dividir(){
        this.resultado = this.num1 / this.num2;
        
        return this.resultado;
    }
}
