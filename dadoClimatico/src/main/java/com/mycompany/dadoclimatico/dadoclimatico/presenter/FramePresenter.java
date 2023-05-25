package com.mycompany.dadoclimatico.dadoclimatico.presenter;

import com.mycompany.dadoclimatico.dadoclimatico.utils.DataUtil;
import com.mycompany.dadoclimatico.dadoclimatico.view.ClimaFrame;
import com.mycompany.dadoclimatico.dadoclimatico.view.EstacaoClimatica;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Álvaro Moret
 */

public class FramePresenter {
    
    
        ClimaFrame frame = new ClimaFrame();
        EstacaoClimatica estacao = new EstacaoClimatica();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public FramePresenter(){
        
        frame.getBtnIncluir().addActionListener((e) -> {
            System.out.println("cheguei");
             if( frame.getTxtInclude_Data() !=null && frame.getTxtInclude_Temperatura() !=null && frame.getTxtInclude_Umidade() !=null && frame.getTxtInclude_Pressao() !=null ){
                 incluirDados();                
             }
        }); 
    }
    
    
    
    private void incluirDados(){
        System.out.println("entrou no incluir");
        String temperatura = frame.getTxtInclude_Temperatura().getText();
        String umidade = frame.getTxtInclude_Umidade().getText();
        String pressao = frame.getTxtInclude_Pressao().getText();
        LocalDate data = DataUtil.stringToData(frame.getTxtInclude_Data().getText());
        estacao.atualizarMedicoes(Float.parseFloat(temperatura), Float.parseFloat(umidade), Float.parseFloat(pressao), data);
        frame.getLblUltimo_Temperatura().setText(temperatura);
        frame.getLblUltimo_Umidade().setText(umidade);
        frame.getLblUltimo_Pressao().setText(pressao);
        frame.getLblUltimo_Data().setText(DataUtil.dataToString(data));
    }
}
