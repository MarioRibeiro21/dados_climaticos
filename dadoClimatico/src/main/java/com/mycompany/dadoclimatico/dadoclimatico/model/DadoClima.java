/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dadoclimatico.dadoclimatico.model;

import java.time.LocalDate;

/**
 *
 * @author Mario e Álvaro
 */
public class DadoClima {
    
   private float temperatura;
   private float umidade;
   private float pressao;
   private LocalDate data;

   public DadoClima(float temperatura, float umidade, float pressao, LocalDate data) {
       this.temperatura = temperatura;
       this.umidade = umidade;
       this.pressao = pressao;
       this.data = data;
   }

    public DadoClima() {
    }
   

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getUmidade() {
        return umidade;
    }

    public void setUmidade(float umidade) {
        this.umidade = umidade;
    }

    public float getPressao() {
        return pressao;
    }

    public void setPressao(float pressao) {
        this.pressao = pressao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

}
