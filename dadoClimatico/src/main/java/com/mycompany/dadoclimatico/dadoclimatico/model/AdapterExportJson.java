/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dadoclimatico.dadoclimatico.model;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Mario
 */
public class AdapterExportJson implements IAdapterExport {
    
    private static final String caminho ="dados_climaticos";

    @Override
    public void escrever(List<DadoClima> dadosClima) throws IOException {
            Gson gson = new Gson();
            gson.toJson(dadosClima, new FileWriter(this.caminho));
    }
    
}
