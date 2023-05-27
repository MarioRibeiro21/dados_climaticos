/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dadoclimatico.dadoclimatico.model;

import com.mycompany.dadoclimatico.dadoclimatico.model.DadoClima;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario
 */
public class MaximasMinimasObserver implements IPainel {

    private List<DadoClima> dadosClima;
    private float maximaTemperatura;
    private float minimaTemperatura;
    private float maximaPressao;
    private float minimaPressao;
    private float maximaUmidade;
    private float minimaUmidade;
    private static MaximasMinimasObserver maximaMinima = null;

    
    public static MaximasMinimasObserver getMaximaMinimaClimaObserver() {
        if (maximaMinima == null) {
            maximaMinima = new MaximasMinimasObserver( 0, 0,0,0,0,0);
        }
        return maximaMinima;
    }

    public MaximasMinimasObserver(float maximaTemperatura, float minimaTemperatura, float maximaPressao, float minimaPressao, float maximaUmidade, float minimaUmidade) {
        this.maximaTemperatura = maximaTemperatura;
        this.minimaTemperatura = minimaTemperatura;
        this.maximaPressao = maximaPressao;
        this.minimaPressao = minimaPressao;
        this.maximaUmidade = maximaUmidade;
        this.minimaUmidade = minimaUmidade;
    }

    

    public MaximasMinimasObserver() {
        dadosClima = new ArrayList<>();
    }

    @Override
    public void atualizar(DadoClima dadoClima) {
        dadosClima.add(dadoClima);
        calcularMaximasMinimas();
    }

    private void calcularMaximasMinimas() {

        for (DadoClima dado : dadosClima) {
            if (dado.getTemperatura() > maximaTemperatura) {
                this.maximaTemperatura = dado.getTemperatura();
            }
            if (dado.getTemperatura() < minimaTemperatura) {
                this.minimaTemperatura = dado.getTemperatura();
            }
            if (dado.getPressao()> maximaPressao) {
                this.maximaPressao = dado.getPressao();
            }
            if (dado.getPressao() < minimaPressao) {
                this.minimaPressao = dado.getPressao();
            }
             if (dado.getPressao()> maximaUmidade) {
                this.maximaUmidade = dado.getPressao();
            }
            if (dado.getUmidade() < minimaUmidade) {
                this.minimaUmidade = dado.getUmidade();
            }
        }
        maximaMinima.setMaximaPressao(this.maximaPressao);
        maximaMinima.setMinimaPressao(this.minimaPressao);
        maximaMinima.setMaximaTemperatura(this.maximaTemperatura);
        maximaMinima.setMinimaTemperatura(this.minimaTemperatura);
        maximaMinima.setMaximaUmidade(this.maximaUmidade);
        maximaMinima.setMinimaUmidade(this.minimaUmidade);
    }

    public float getMaximaTemperatura() {
        return maximaTemperatura;
    }

    public void setMaximaTemperatura(float maximaTemperatura) {
        this.maximaTemperatura = maximaTemperatura;
    }

    public float getMinimaTemperatura() {
        return minimaTemperatura;
    }

    public void setMinimaTemperatura(float minimaTemperatura) {
        this.minimaTemperatura = minimaTemperatura;
    }

    public float getMaximaPressao() {
        return maximaPressao;
    }

    public void setMaximaPressao(float maximaPressao) {
        this.maximaPressao = maximaPressao;
    }

    public float getMinimaPressao() {
        return minimaPressao;
    }

    public void setMinimaPressao(float minimaPressao) {
        this.minimaPressao = minimaPressao;
    }

    public float getMaximaUmidade() {
        return maximaUmidade;
    }

    public void setMaximaUmidade(float maximaUmidade) {
        this.maximaUmidade = maximaUmidade;
    }

    public float getMinimaUmidade() {
        return minimaUmidade;
    }

    public void setMinimaUmidade(float minimaUmidade) {
        this.minimaUmidade = minimaUmidade;
    }

    public static MaximasMinimasObserver getMaximaMinima() {
        return maximaMinima;
    }

    public static void setMaximaMinima(MaximasMinimasObserver maximaMinima) {
        MaximasMinimasObserver.maximaMinima = maximaMinima;
    }

}
