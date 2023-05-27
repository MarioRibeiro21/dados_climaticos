/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dadoclimatico.dadoclimatico.view;

import com.mycompany.dadoclimatico.dadoclimatico.model.MaximasMinimasObserver;
import com.mycompany.dadoclimatico.dadoclimatico.model.EstatisticaClimaObserver;
import com.mycompany.dadoclimatico.dadoclimatico.model.IPainel;
import com.mycompany.dadoclimatico.dadoclimatico.model.DadoClima;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario e Álvaro
 */
public class EstacaoClimatica {

    private static List<IPainel> paineis;

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

    public void inserirMedicoes(Float temperatura, Float umidade, Float pressao, LocalDate data) {
        var dado = new DadoClima(temperatura, umidade, pressao, data);
        notificarInsercaoPaineis(dado);
    }

    private void notificarInsercaoPaineis(DadoClima dadoClima) {
        for (IPainel painel : paineis) {
            painel.inserir(dadoClima);
        }
    }
    
     public void removerMedicoes(Float temperatura, Float umidade, Float pressao, LocalDate data) {
        var dado = new DadoClima(temperatura, umidade, pressao, data);
        notificarInsercaoPaineis(dado);
    }
    
    private void notificarRemocaoPaineis(DadoClima dadoClima) {
        for (IPainel painel : paineis) {
            painel.remover(dadoClima);
        }
    }

}
