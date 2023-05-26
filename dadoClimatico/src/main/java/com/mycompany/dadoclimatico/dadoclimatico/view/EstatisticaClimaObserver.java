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
public class EstatisticaClimaObserver implements IPainel {

    private List<DadoClima> dadosClima = new ArrayList();
    private float mediaTemperatura;
    private float mediaUmidade;
    private float mediaPressao;
    private static EstatisticaClimaObserver estatistica = null;

    public static EstatisticaClimaObserver getEstatisticaClimaObserver() {
        if (estatistica == null) {
            estatistica = new EstatisticaClimaObserver();
        }
        return estatistica;
    }

    @Override
    public void atualizar(DadoClima dadoClima) {
        if (estatistica.getDadosClima() == null) {
            dadosClima = new ArrayList();
        }
        estatistica.getDadosClima().add(dadoClima);
        calcularMedia();
    }

    private void calcularMedia() {
        float somaTemperaturas = 0;
        float somaUmidades = 0;
        float somaPressoes = 0;

        for (DadoClima dado : estatistica.getDadosClima()) {
            somaTemperaturas += dado.getTemperatura();
            somaUmidades += dado.getUmidade();
            somaPressoes += dado.getPressao();
        }

        int tamanho = estatistica.getDadosClima().size();

        this.mediaPressao = somaPressoes / tamanho;
        this.mediaTemperatura = somaTemperaturas / tamanho;
        this.mediaUmidade = somaUmidades / tamanho;

        estatistica.setMediaPressao(mediaPressao);
        estatistica.setMediaTemperatura(mediaTemperatura);
        estatistica.setMediaUmidade(mediaUmidade);
    }

    public float getMediaTemperatura() {
        return mediaTemperatura;
    }

    public void setMediaTemperatura(float mediaTemperatura) {
        this.mediaTemperatura = mediaTemperatura;
    }

    public float getMediaUmidade() {
        return mediaUmidade;
    }

    public void setMediaUmidade(float mediaUmidade) {
        this.mediaUmidade = mediaUmidade;
    }

    public float getMediaPressao() {
        return mediaPressao;
    }

    public void setMediaPressao(float mediaPressao) {
        this.mediaPressao = mediaPressao;
    }

    public List<DadoClima> getDadosClima() {
        return dadosClima;
    }

}
