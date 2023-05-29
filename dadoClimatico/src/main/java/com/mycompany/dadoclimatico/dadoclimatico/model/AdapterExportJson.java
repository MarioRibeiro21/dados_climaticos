/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dadoclimatico.dadoclimatico.model;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.time.*;
import org.jfree.data.json.impl.JSONObject;

/**
 *
 * @author Mario
 */
public class AdapterExportJson implements IAdapterExport {

    private static final String caminho = "dados_climaticos.json";
    private FileWriter writeFile;

    public AdapterExportJson() throws IOException {
        this.writeFile = new FileWriter(caminho);
    }

    @Override
    public void escrever(List<DadoClima> dadosClima) throws IOException {
        //Cria um Objeto JSON
        JSONObject jsonObject = new JSONObject();
        for (DadoClima d : dadosClima) {
            //Armazena dados em um Objeto JSON
            jsonObject.put("temperatura", d.getTemperatura());
            jsonObject.put("umidade", d.getUmidade());
            jsonObject.put("pressao", d.getPressao());
            jsonObject.put("data", d.getData());
            writeFile.write(jsonObject.toJSONString());

        }
        writeFile.close();

    }
}
