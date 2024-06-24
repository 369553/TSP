package Views;
import Base.Route;
import Control.ActOnRoutePanel;
import Control.GUISeeming;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class RoutePanel extends JPanel{
    JLabel lblRouteName, lblTotalCost, lblTotalCostText, lblRouteNameText;
    JList liRouteDetails;
    JScrollPane scrpaneRouteDetails;
    Route route;
    DefaultListModel<String> modelDetailsList;
    GridBagLayout compOrder;
    String routeName;
    ActOnRoutePanel act;

    public RoutePanel() {
        setLayout(getCompOrder());
        Add.addComp(this, getLblRouteNameText(), 0, 0, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE, 0.0, 0.1);
        Add.addComp(this, getLblRouteName(), 1, 0, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, 1.0, 0.1);
        Add.addComp(this, getLblTotalCostText(), 0, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE, 0.0, 0.1);
        Add.addComp(this, getLblTotalCost(), 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, 1.0, 0.1, 5, 1, 5, 2);
        Add.addComp(this, getScrpaneRouteDetails(), 0, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1.0, 1.0);
    }
    public RoutePanel(Route route, String name){
        this();
        this.route = route;
        showRouteInformations();
    }

//İŞLEM YÖNTEMLERİ:
    public void showRouteInformations(){
        if(route == null)
            return;
        for(int index = 1; index < route.getStepNumber(); index++){
            String text = index + " : " + route.getRoute()[index - 1].getName() + "  -->  " + route.getRoute()[index].getName();
            getModelDetailsList().add(index - 1, text);
        }
        getLblRouteName().setText(route.getRoute()[0].name + " -> " + route.getRoute()[route.getStepNumber() - 1].getName());
        GUISeeming.appGUI(this);
        setVisible(true);
    }
    public void setRoute(Route route, String routeName) {
        this.route = route;
        this.routeName = routeName;
    }
    public void setRoute(Route route) {
        this.setRoute(route, "");
    }
    public void updateInfo(){
        if(route == null)
            return;
        modelDetailsList.removeAllElements();
        lblRouteName.setText(routeName);
        lblTotalCost.setText(String.valueOf(route.getTotalCost()));
        showRouteInformations();
    }
//ERİŞİM YÖNTEMLERİ:
    public JLabel getLblRouteName() {
        if(lblRouteName == null){
            if(routeName != null)
                lblRouteName  = new JLabel(routeName);
            else
                lblRouteName  = new JLabel("");
        }
        return lblRouteName;
    }
    public JLabel getLblTotalCost() {
        if(lblTotalCost == null){
            if(route != null)
                lblTotalCost  = new JLabel(String.valueOf(route.getTotalCost()));
            else
                lblTotalCost  = new JLabel("");
        }
        return lblTotalCost;
    }
    public JLabel getLblTotalCostText() {
        if(lblTotalCostText == null){
            lblTotalCostText  = new JLabel("Mesâfe :");
        }
        return lblTotalCostText;
    }
    public JList getLiRouteDetails() {
        if(liRouteDetails == null){
            liRouteDetails = new JList(getModelDetailsList());
            liRouteDetails.addListSelectionListener(getAct());
        }
        return liRouteDetails;
    }
    public Route getRoute() {
        return route;
    }
    public DefaultListModel<String> getModelDetailsList() {
        if(modelDetailsList == null){
            modelDetailsList = new DefaultListModel<String>();
        }
        return modelDetailsList;
    }
    public GridBagLayout getCompOrder() {
        if(compOrder == null){
            compOrder = new GridBagLayout();
        }
        return compOrder;
    }
    public JLabel getLblRouteNameText() {
        if(lblRouteNameText == null){
            lblRouteNameText = new JLabel("İsim :");
        }
        return lblRouteNameText;
    }
    public JScrollPane getScrpaneRouteDetails() {
        if(scrpaneRouteDetails == null){
            scrpaneRouteDetails = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrpaneRouteDetails.setViewportView(getLiRouteDetails());
            scrpaneRouteDetails.setPreferredSize(new Dimension(200, 100));
        }
        return scrpaneRouteDetails;
    }
    public ActOnRoutePanel getAct() {
        if(act == null){
            act = new ActOnRoutePanel(this);
        }
        return act;
    }
}
