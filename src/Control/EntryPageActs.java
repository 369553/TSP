package Control;

import Base.City;
import Base.Map;
import Views.ContactPanel;
import Views.EntryPage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EntryPageActs implements ActionListener, ChangeListener{
    EntryPage page;
    private int spinWidth, spinHeight;

    public EntryPageActs(EntryPage page){
        this.page = page;
        spinWidth = (int) page.getSpinnerMapWidth().getValue();
        spinHeight = (int) page.getSpinnerMapHeigth().getValue();
    }

//İŞLEM YÖNTEMLERİ:
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == page){
            
        }
        else if(e.getSource() == page.getBtnProduceMap()){
            if(spinWidth <= 10 || spinHeight <= 10){
                page.getBtnProduceMap().setBackground(Color.red);
//                page.setVisible(true);
                return;
            }
            Map map = new Map("", spinWidth, spinHeight);
            IDARE.startIDARE(map);
            IDARE.getIdare().startSystem();
            page.setEnabled(false);
            page.setVisible(false);
            IDARE.getIdare().updateIDARE();
        }
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == page.getSpinnerMapHeigth()){
            spinHeight = (int) page.getSpinnerMapHeigth().getValue();
        }
        else if(e.getSource() == page.getSpinnerMapWidth()){
            spinWidth = (int) page.getSpinnerMapWidth().getValue();
        }
    }

//ERİŞİM YÖNTEMLERİ:

    
}