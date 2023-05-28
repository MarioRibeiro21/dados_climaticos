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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JTable;
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
            if (frame.getTxtInclude_Data() != null && frame.getTxtInclude_Temperatura() != null && frame.getTxtInclude_Umidade() != null && frame.getTxtInclude_Pressao() != null) {
                incluirDados();
            }
        });
        
        frame.getBtnRemove().addActionListener((e) -> {
            removerDados();
        });
    }
    
    private void removerDados(){
        JTable tabela = frame.getTblRegistros();
        LocalDate data = DataUtil.stringToData(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
        Float temperatura = Float.parseFloat(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
        Float umidade = Float.parseFloat(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
        Float pressao = Float.parseFloat(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
        estacao.removerMedicoes(temperatura, umidade, pressao, data);
    }

    private void incluirDados() {

        estacao.inserirMedicoes(frame.getDado().getTemperatura(), frame.getDado().getUmidade(), frame.getDado().getPressao(), frame.getDado().getData());

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

        tabela.addRow(new Object[]{DataUtil.dataToString(d.getData()), d.getTemperatura(), d.getUmidade(), d.getPressao()});

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
