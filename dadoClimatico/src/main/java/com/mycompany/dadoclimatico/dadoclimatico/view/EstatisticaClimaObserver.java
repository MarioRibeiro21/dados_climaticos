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
public class EstatisticaClimaObserver implements IPainel{
    
    private List<DadoClima> dadosClima;
    private float mediaTemperatura;
    private float mediaUmidade;
    private float mediaPressao;

   public EstatisticaClimaObserver() {
       dadosClima = new ArrayList<>();
   }

    @Override
    public void atualizar(DadoClima dadoClima) {
        dadosClima.add(dadoClima);
       calcularMedia();
    }
    
    private void calcularMedia() {
       float somaTemperaturas = 0;
       float somaUmidades = 0;
       float somaPressoes = 0;

       for (DadoClima dadoClima : dadosClima) {
           somaTemperaturas += dadoClima.getTemperatura();
           somaUmidades += dadoClima.getUmidade();
           somaPressoes += dadoClima.getPressao();
       }

       int tamanho = dadosClima.size();
       
       this.mediaPressao = somaPressoes/tamanho;
       this.mediaTemperatura = somaTemperaturas/tamanho;
       this.mediaUmidade =  somaUmidades/tamanho;
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
    
}
