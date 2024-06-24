package Views;
import Control.ActOnCityPanel;
import Control.GUISeeming;
import Control.IDARE;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;

public class CityPanel extends JPanel{
    JButton btnAdd, btnDelete;
    JLabel lblCities, lblCityName, lblCityWidth, lblCityHeight;
    JList liCities;
    JTextField txtName;
    JSpinner spinWidth, spinHeight;
    JScrollPane scrpaneCities;
    DefaultListModel<String> modelCitiesList;
    ActOnCityPanel acts;
    GridBagLayout order;
    int maxWidth = IDARE.getIdare().getMap().getWidth() - 1;
    int maxHeight = IDARE.getIdare().getMap().getHeight()- 1;

    public CityPanel() {
        this.setLayout(getOrder());
        Add.addComp(this, getLblCities(), 0, 0, 1, 1, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, 0.2,0.0);
        Add.addComp(this, getScrpaneCities(), 0, 1, 2, 3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1.0, 1.0);
        Add.addComp(this, getBtnDelete(), 0, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0.2, 0.2);
        Add.addComp(this, getLblCityName(), 0, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0.0, 0.0);
        Add.addComp(this, getTxtName(), 1, 5, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1.0, 0.2);
        
        
        Add.addComp(this, getLblCityHeight(), 0, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0.0, 0.0);
        Add.addComp(this, getSpinHeight(), 1, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1.0, 0.2);
        
        
        Add.addComp(this, getLblCityWidth(), 0, 7, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 0.0, 0.0);
        Add.addComp(this, getSpinWidth(), 1, 7, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1.0, 0.2);
        
        Add.addComp(this, getBtnAdd(), 0, 8, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1.0, 0.2);
        this.setPreferredSize(new Dimension(MainFrame.getFrame_Main().getWidth() / 3, MainFrame.getFrame_Main().getHeight() / 2));
        GUISeeming.appGUI(this);
    }

//ERİŞİM YÖNTEMLERİ:
    public JButton getBtnAdd(){
        if(btnAdd == null){
            btnAdd = new JButton("EKLE");
            btnAdd.addActionListener(getActs());
            btnAdd.setPreferredSize(new Dimension(35, 35));
        }
        return btnAdd;
    }
    public JButton getBtnDelete(){
        if(btnDelete == null){
            btnDelete = new JButton("SİL");
            btnDelete.setPreferredSize(new Dimension(35, 35));
            btnDelete.addActionListener(getActs());
        }
        return btnDelete;
    }
    public JLabel getLblCities(){
        if(lblCities == null){
            lblCities = new JLabel("Beldeler:");
        }
        return lblCities;
    }
    public JList getLiCities(){
        if(liCities == null){
            liCities = new JList(getModelCitiesList());
            liCities.setPreferredSize(new Dimension(150, 90));
        }
        return liCities;
    }
    public DefaultListModel<String> getModelCitiesList(){
        if(modelCitiesList == null){
            modelCitiesList = new DefaultListModel<String>();
            String[] cityNames = IDARE.getIdare().getCityNames();
            for(int index = 0; index < cityNames.length; index++){
                modelCitiesList.addElement(cityNames[index]);
            }
        }
        return modelCitiesList;
    }
    public ActOnCityPanel getActs(){
        if(acts == null){
            acts = new ActOnCityPanel(this);
        }
        return acts;
    }
    public GridBagLayout getOrder(){
        if(order == null){
            order = new GridBagLayout();
        }
        return order;
    }
    public JTextField getTxtName(){
        if(txtName == null){
            txtName = new JTextField();
            txtName.setPreferredSize(new Dimension(35, 35));
        }
        return txtName;
    }
    public JSpinner getSpinWidth(){
        if(spinWidth == null){
            spinWidth = new JSpinner();
            spinWidth.setModel(new SpinnerNumberModel(11, 0, maxWidth, 0.1));
        }
        return spinWidth;
    }
    public JSpinner getSpinHeight(){
        if(spinHeight == null){
            spinHeight = new JSpinner();
            spinHeight.setModel(new SpinnerNumberModel(11, 0, maxHeight, 0.1));
        }
        return spinHeight;
    }
    public JLabel getLblCityName(){
        if(lblCityName == null){
            lblCityName = new JLabel("Belde ismi:");
        }
        return lblCityName;
    }
    public JLabel getLblCityWidth(){
        if(lblCityWidth == null){
            lblCityWidth = new JLabel("Belde x koordinatı:");
        }
        return lblCityWidth;
    }
    public JLabel getLblCityHeight(){
        if(lblCityHeight == null){
            lblCityHeight = new JLabel("Belde y koordinatı:");
        }
        return lblCityHeight;
    }
    public JScrollPane getScrpaneCities(){
        if(scrpaneCities == null){
            scrpaneCities = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrpaneCities.setViewportView(getLiCities());
            scrpaneCities.setPreferredSize(new Dimension(150, 90));
        }
        return scrpaneCities;
    }
}
