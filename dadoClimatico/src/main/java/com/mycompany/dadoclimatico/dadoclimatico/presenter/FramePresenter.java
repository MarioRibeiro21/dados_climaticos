package com.mycompany.dadoclimatico.dadoclimatico.presenter;

import com.mycompany.dadoclimatico.dadoclimatico.model.DadoClima;
import com.mycompany.dadoclimatico.dadoclimatico.utils.DataUtil;
import com.mycompany.dadoclimatico.dadoclimatico.view.ClimaFrame;
import com.mycompany.dadoclimatico.dadoclimatico.view.EstacaoClimatica;
import com.mycompany.dadoclimatico.dadoclimatico.view.EstatisticaClimaObserver;
import java.time.format.DateTimeFormatter;

/**
 * @author Álvaro Moret
 */
public class FramePresenter {

    private ClimaFrame frame = new ClimaFrame();
    private EstacaoClimatica estacao = new EstacaoClimatica();
    private EstatisticaClimaObserver estatistica = EstatisticaClimaObserver.getEstatisticaClimaObserver();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public FramePresenter() {

        frame.getBtnIncluir().addActionListener((e) -> {
            System.out.println("cheguei");
            if (frame.getTxtInclude_Data() != null && frame.getTxtInclude_Temperatura() != null && frame.getTxtInclude_Umidade() != null && frame.getTxtInclude_Pressao() != null) {
                incluirDados();
            }
        });
    }

    private void incluirDados() {

        estacao.atualizarMedicoes(frame.getDado().getTemperatura(), frame.getDado().getUmidade(), frame.getDado().getPressao(), frame.getDado().getData());
        System.out.println("entrou no incluir");

        mapValorAtual(frame.getDado());

        mapMedia();

    }

    private void mapValorAtual(DadoClima dado) {
        frame.getLblUltimo_Temperatura().setText(Float.toString(frame.getDado().getTemperatura()));
        frame.getLblUltimo_Umidade().setText(Float.toString(frame.getDado().getUmidade()));
        frame.getLblUltimo_Pressao().setText(Float.toString(frame.getDado().getPressao()));
        frame.getLblUltimo_Data().setText(DataUtil.dataToString(frame.getDado().getData()));
    }

    private void mapMedia() {
        frame.getLblMedia_Temperatura().setText(Float.toString(estatistica.getMediaTemperatura()));
        frame.getLblMedia_Umidade().setText(Float.toString(estatistica.getMediaUmidade()));
        frame.getLblMedia_Pressao().setText(Float.toString(estatistica.getMediaPressao()));
        frame.getLblMedia_nrRegistros().setText(Float.toString(estatistica.getDadosClima().size()));
    }
}
