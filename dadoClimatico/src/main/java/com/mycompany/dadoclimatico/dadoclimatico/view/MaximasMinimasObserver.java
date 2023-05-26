/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dadoclimatico.dadoclimatico.view;

import com.mycompany.dadoclimatico.dadoclimatico.model.DadoClima;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario
 */
public class MaximasMinimasObserver implements IPainel {

    private List<DadoClima> dadosClima;
    private float maxima;
    private float minima;

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
            if (dado.getTemperatura() > maxima) {
                this.maxima = dado.getTemperatura();
            }
            if (dado.getTemperatura() < minima) {
                this.minima = dado.getTemperatura();
            }
        }
    }

    public float getMaxima() {
        return maxima;
    }

    public void setMaxima(float maxima) {
        this.maxima = maxima;
    }

    public float getMinima() {
        return minima;
    }

    public void setMinima(float minima) {
        this.minima = minima;
    }

}
