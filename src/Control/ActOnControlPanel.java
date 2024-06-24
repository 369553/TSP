package Control;

import Algorithms.IAlgorithm;
import Algorithms.LineerAlgorithm;
import Algorithms.MinimumCostIsFirst;
import Algorithms.SimulatedAnnealing;
import Base.City;
import Base.Problem;
import Base.Route;
import Views.CityPanel;
import Views.ContactPanel;
import Views.MainFrame;
import Views.TSPControlPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ActOnControlPanel implements ActionListener{
    TSPControlPanel panel;
    JOptionPane optpane;
    IAlgorithm algorithm;

    public ActOnControlPanel(TSPControlPanel panel){
        this.panel = panel;
    }

//İŞLEM YÖNTEMLERİ:
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == panel.getBtnAddDeleteCity()){
            CityPanel pnl = new CityPanel();
            optpane = new JOptionPane("Şehir ekle / sil", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
            optpane.showOptionDialog(MainFrame.getFrame_Main(), "", "",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(),
                    new Object[]{pnl}, null);
        }
        else if(e.getSource() == panel.getBtnChooseAsStartCity()){
            Object selected = panel.getLiCities().getSelectedValue();
            if(selected == null)
                return;
            panel.setStartCity(IDARE.getIdare().getCityByName((String) selected));
            if(panel.getStartCity() != null)
                ContactPanel.getContactPanel().showMessage("Başlangıç şehri işâretlendi : " + panel.getStartCity().getName(), "Successful");
        }
        else if(e.getSource() == panel.getBtnFindRoute()){
            if(panel.getStartCity() == null){
                ContactPanel.getContactPanel().showMessage("Bir başlangıç şehri seçmelisiniz", "Warning");
                return;
            }
            Problem problem = new Problem(IDARE.getIdare().getCitiesAsArray(), panel.getStartCity(), true, 3);
            algorithm = findAlgorithm((String) panel.getCmboxAlgorithms().getSelectedItem(), problem);
            problem.setAlgorithm(algorithm);
            Route route = problem.findRoute();
            if(route != null)
                ContactPanel.getContactPanel().showMessage("Güzergâh başarıyla hesaplandı", "Successful");
            IDARE.getIdare().showRoute(route);
        }
        
    }
    private IAlgorithm findAlgorithm(String algorithmName, Problem problem){
        switch(algorithmName){
            /*case "Doğrusal" :{
                return new LineerAlgorithm(problem);
            }*/
            case "Açgözlü" :{
                return new MinimumCostIsFirst(problem);
            }
            case "BenzetilmişTavlama" :{
                return new SimulatedAnnealing(problem);
            }
            default:{
                return new LineerAlgorithm(problem);
            }
        }
    }
}