/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dadoclimatico.dadoclimatico.view;

import com.mycompany.dadoclimatico.dadoclimatico.model.DadoClima;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario
 */
public class EstacaoClimatica {
    private List<IPainel> paineis;

   public EstacaoClimatica() {
       paineis = new ArrayList<>();
       paineis.add(new EstatisticaClimaObserver());
       paineis.add(new MaximasMinimasObserver());
   }

   public void registrarPainel(IPainel painel) {
       paineis.add(painel);
   }

   public void removerPainel(IPainel painel) {
       paineis.remove(painel);
   }

   public void atualizarMedicoes(float temperatura, float umidade, float pressao, LocalDate data) {
       DadoClima dadoClima = new DadoClima(temperatura, umidade, pressao, data);
       notificarPaineis(dadoClima);
   }

   private void notificarPaineis(DadoClima dadoClima) {
       for (IPainel painel : paineis) {
           painel.atualizar(dadoClima);
       }
   }

}
