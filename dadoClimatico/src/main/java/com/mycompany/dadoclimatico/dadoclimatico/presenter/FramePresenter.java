package com.mycompany.dadoclimatico.dadoclimatico.presenter;

import com.mycompany.dadoclimatico.dadoclimatico.model.AdapterExportJson;
import com.mycompany.dadoclimatico.dadoclimatico.model.DadoClima;
import com.mycompany.dadoclimatico.dadoclimatico.utils.DataUtil;
import com.mycompany.dadoclimatico.dadoclimatico.view.ClimaFrame;
import com.mycompany.dadoclimatico.dadoclimatico.view.EstacaoClimatica;
import com.mycompany.dadoclimatico.dadoclimatico.model.EstatisticaClimaObserver;
import com.mycompany.dadoclimatico.dadoclimatico.model.IAdapterExport;
import com.mycompany.dadoclimatico.dadoclimatico.model.MaximasMinimasObserver;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author Álvaro Moret
 */
public class FramePresenter {

    private ClimaFrame frame = new ClimaFrame();
    private EstacaoClimatica estacao = new EstacaoClimatica();
    private EstatisticaClimaObserver estatistica = EstatisticaClimaObserver.getEstatisticaClimaObserver();
    private MaximasMinimasObserver maximaMinima = MaximasMinimasObserver.getMaximaMinimaClimaObserver();
    private IAdapterExport adapter;

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

        AdicionarTabela();
        
        plotGrafico();

       // exportarLog(estatistica.getDadosClima());

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

    private void AdicionarTabela() {
        DefaultTableModel tabela = (DefaultTableModel) frame.getTblRegistros().getModel();

        DadoClima d = frame.getDado();

        tabela.addRow(new Object[]{d.getData(), d.getTemperatura(), d.getUmidade(), d.getPressao()});

    }

    private void exportarLog(List<DadoClima> dado) {
        switch (1) {
            case 1 -> {
                try {
                    adapter = new AdapterExportJson();
                    adapter.escrever(dado);
                } catch (IOException e) {
                }
            }
            case 2 -> {

            }
        }
    }
    
    private void plotGrafico(){
        System.out.println("auqi" + maximaMinima.getMaximaTemperatura()+ maximaMinima.getMinimaTemperatura());
       DefaultCategoryDataset barra = new DefaultCategoryDataset();
       barra.setValue(maximaMinima.getMaximaTemperatura(), "Temperatura", "");
       barra.setValue(maximaMinima.getMinimaTemperatura(), "Temperatura", "");
       barra.setValue(maximaMinima.getMinimaPressao(), "Pressão", "");
       barra.setValue(maximaMinima.getMinimaPressao(), "Pressão", "");
       barra.setValue(maximaMinima.getMinimaUmidade(), "Umidade", "");
       barra.setValue(maximaMinima.getMinimaUmidade(), "Umidade", "");
       
       JFreeChart grafico = ChartFactory.createBarChart("Dados Médios", "Dados climáticos", "temperatura", barra, PlotOrientation.VERTICAL,true,true,false);
       ChartPanel painel = new ChartPanel(grafico);
    }
}
