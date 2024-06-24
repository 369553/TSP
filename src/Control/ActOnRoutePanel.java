package Control;

import Views.RoutePanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ActOnRoutePanel implements ListSelectionListener{
    private RoutePanel pnl;

    public ActOnRoutePanel(RoutePanel routePanel){
        this.pnl = routePanel;
    }

//İŞLEM YÖNTEMLERİ:

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource() == pnl.getLiRouteDetails()){
           IDARE.getIdare().chooseRouteParts(pnl.getLiRouteDetails().getSelectedIndices());
        }
    }
    
}
