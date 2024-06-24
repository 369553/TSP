package Views;

import Base.City;
import Base.Route;
import Control.ActOnControlPanel;
import Control.GUISeeming;
import Control.IDARE;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultListModel;
import java.awt.GridBagLayout;
import javax.swing.SwingConstants;

public class TSPControlPanel extends JPanel{
    JLabel lblCities, lblcurrentRoute, lblAlgorithms, lblExplanationOfPanel;
    JScrollPane scrpaneCities;
    JButton btnFindRoute, btnAddDeleteCity, btnChooseAsStartCity;
    JComboBox<String> cmboxAlgorithms;
    RoutePanel routePanel;
    JList liCities;
    DefaultListModel<String> modelCitiesList;
    GridBagLayout compOrder;
    City startCity;
    ActOnControlPanel acts;

    public TSPControlPanel() {
        setLayout(getCompOrder());
        Add.addComp(this, getLblExplanationOfPanel(), 0, 0, 2, 1, GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL, 1.0, 0.1);
        Add.addComp(this, getLblCities(), 0, 1, 1, 1, GridBagConstraints.CENTER);
        Add.addComp(this, getScrpaneCities(), 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1.0, 0.6);
        Add.addComp(this, getLblAlgorithms(), 0, 2, 1, 1, GridBagConstraints.CENTER);
        Add.addComp(this, getCmboxAlgorithms(), 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Add.addComp(this, getBtnChooseAsStartCity(), 0, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Add.addComp(this, getBtnFindRoute(), 0, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Add.addComp(this, getBtnAddDeleteCity(), 0, 5, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        Add.addComp(this, getLblcurrentRoute(), 0, 6, 2, 1, GridBagConstraints.PAGE_END, GridBagConstraints.CENTER, 1.0, 0.0);
        Add.addComp(this, getRoutePanel(), 0, 7, 2, 2, GridBagConstraints.PAGE_END, GridBagConstraints.BOTH, 1.0, 0.5);
        setPreferredSize(new Dimension(MainFrame.getFrame_Main().getWidth() / 4, MainFrame.getFrame_Main().getHeight()));
        GUISeeming.appGUI(this);
    }

//İŞLEM YÖNTEMLERİ:
    public void updateInfo(){
        getModelCitiesList().removeAllElements();
        for(String str : IDARE.getIdare().getCityNames()){
            modelCitiesList.addElement(str);
        }
        /*SİSTEME YENİ BİR ALGORİTMA EKLENMİYOR; BUNA GEREK YOK
        getCmboxAlgorithms().removeAllItems();
        for(String str : IDARE.getIdare().getAlgorithmNames()){
            cmboxAlgorithms.addItem(str);
        }*/
        getRoutePanel().updateInfo();
    }
    public void setRouteOfRoutePanel(Route route){
        getRoutePanel().setRoute(route);
        routePanel.updateInfo();
    }
    public void setStartCity(City startCity){
        this.startCity = startCity;
    }

//ERİŞİM YÖNTEMLERİ:
    public JLabel getLblCities() {
        if(lblCities == null){
            lblCities = new JLabel("Beldeler :");
        }
        return lblCities;
    }
    public JLabel getLblcurrentRoute() {
        if(lblcurrentRoute == null){
            lblcurrentRoute = new JLabel("Güzergâh :");
        }
        return lblcurrentRoute;
    }
    public JLabel getLblAlgorithms() {
        if(lblAlgorithms == null){
            lblAlgorithms = new JLabel("Algoritmalar :");
        }
        return lblAlgorithms;
    }
    public JScrollPane getScrpaneCities() {
        if(scrpaneCities == null){
            scrpaneCities = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrpaneCities.setViewportView(getLiCities());
            scrpaneCities.setPreferredSize(new Dimension(65, 70));
        }
        return scrpaneCities;
    }
    public JButton getBtnFindRoute() {
        if(btnFindRoute == null){
            btnFindRoute = new JButton("Güzergâh bul");
            btnFindRoute.addActionListener(getActs());
        }
        return btnFindRoute;
    }
    public JComboBox getCmboxAlgorithms() {
        if(cmboxAlgorithms == null){
            cmboxAlgorithms = new JComboBox<String>(IDARE.getIdare().getAlgorithmNames());
            cmboxAlgorithms.setSelectedIndex(0);
            cmboxAlgorithms.addActionListener(getActs());
        }
        return cmboxAlgorithms;
    }
    public RoutePanel getRoutePanel() {
        if(routePanel == null){
            routePanel = new RoutePanel();
        }
        return routePanel;
    }
    public JList getLiCities(){
        if(liCities == null){
            liCities = new JList();
            liCities.setModel(getModelCitiesList());
        }
        return liCities;
    }
    public DefaultListModel<String> getModelCitiesList(){
        if(modelCitiesList == null){
            modelCitiesList = new DefaultListModel<String>();
            String[] strCityNames = IDARE.getIdare().getCityNames();
            for(String str : strCityNames){
                modelCitiesList.addElement(str);
            }
    //        System.out.println("şehir sayısı : " + modelCitiesList.size());
        }
        return modelCitiesList;
    }
    public GridBagLayout getCompOrder() {
        if(compOrder == null){
            compOrder = new GridBagLayout();
        }
        return compOrder;
    }
    public JLabel getLblExplanationOfPanel() {
        if(lblExplanationOfPanel == null){
            lblExplanationOfPanel = new JLabel("İDÂRE ALANI");
            lblExplanationOfPanel.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return lblExplanationOfPanel;
    }
    public JButton getBtnAddDeleteCity(){
        if(btnAddDeleteCity == null){
            btnAddDeleteCity = new JButton("EKLE - ÇIKAR");
            btnAddDeleteCity.addActionListener(getActs());
        }
        return btnAddDeleteCity;
    }

    public ActOnControlPanel getActs(){
        if(acts == null){
            acts = new ActOnControlPanel(this);
        }
        return acts;
    }
    public JButton getBtnChooseAsStartCity(){
        if(btnChooseAsStartCity == null){
            btnChooseAsStartCity = new JButton("Bu şehirden başla");
            btnChooseAsStartCity.addActionListener(getActs());
        }
        return btnChooseAsStartCity;
    }
    public City getStartCity(){
        return startCity;
    }
}