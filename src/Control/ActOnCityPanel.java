package Control;

import Base.City;
import Views.CityPanel;
import Views.ContactPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ActOnCityPanel implements ActionListener{
    CityPanel panel;

    public ActOnCityPanel(CityPanel panel){
        this.panel = panel;
    }

//İŞLEM YÖNTEMLERİ:
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == panel.getBtnAdd()){
            String name = panel.getTxtName().getText();
            if(name.isEmpty()){
                ContactPanel.getContactPanel().showMessage("Bir şehir ismi girmelisiniz", "Warning");
                return;
            }
            double coordinateX = (double) panel.getSpinWidth().getValue();
            double coordinateY = (double) panel.getSpinHeight().getValue();
            City city = new City(name, coordinateX, coordinateY);
            if(IDARE.getIdare().addCity(city)){
                panel.getModelCitiesList().addElement(city.name);
                ContactPanel.getContactPanel().showMessage("Şehir haritaya başarıyla eklendi", "Successful");
            }
            panel.getTxtName().setText("");
            panel.getTxtName().requestFocus();
        }
        else if(e.getSource() == panel.getBtnDelete()){
            List selectedCities = panel.getLiCities().getSelectedValuesList();
            if(selectedCities.isEmpty())
                return;
            String[] selectedCityNames = new String[selectedCities.size()];
            selectedCities.toArray(selectedCityNames);
            for(String name: selectedCityNames){
                if(IDARE.getIdare().removeCity(IDARE.getIdare().getCityByName(name))){
                    panel.getModelCitiesList().removeElement(name);
                    ContactPanel.getContactPanel().showMessage("Şehir haritadan başarıyla kaldırıldı", "Successful");
                }
            }
        }
    }
}