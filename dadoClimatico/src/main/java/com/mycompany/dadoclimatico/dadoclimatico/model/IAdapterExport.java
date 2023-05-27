/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dadoclimatico.dadoclimatico.model;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Mario
 */
public interface IAdapterExport {
     void escrever(List<DadoClima> dadosClima) throws IOException;
}
