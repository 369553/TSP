package Views;

import Control.EntryPageActs;
import Control.GUISeeming;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class EntryPage extends JFrame{
    JButton btnProduceMap;
    JSpinner spinnerMapWidth, spinnerMapHeigth;
    JOptionPane optPaneAddCity;
    EntryPageActs actsAndSignals;
    GridBagLayout order;
    JLabel lblWidth, lblHeight;
    Dimension systemDimension = Toolkit.getDefaultToolkit().getScreenSize();

    public EntryPage() {
        order = new GridBagLayout();
        this.setPreferredSize(new Dimension(systemDimension.width / 2, systemDimension.height / 2));
        this.setBounds(systemDimension.width / 4, systemDimension.height / 4, systemDimension.width / 2, systemDimension.height / 2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(order);
        Add.addComp(this, getSpinnerMapHeigth(), 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1.0, 1.0, 0, 2, 5, 5, 15, 15);
        Add.addComp(this, getSpinnerMapWidth(), 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1.0, 1.0, 0, 5, 5, 2, 15, 15);
        
        Add.addComp(this, getLblHeight(), 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1.0, 1.0, 0, 2, 5, 5, 15, 15);
        Add.addComp(this, getLblWidth(), 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 1.0, 1.0, 0, 5, 5, 2, 15, 15);
        
        Add.addComp(this, getBtnProduceMap(), 0, 2, 2, 1, GridBagConstraints.PAGE_END, GridBagConstraints.BOTH, 1.0, 1.0, 0, 5, 5, 5, 15, 15);
        GUISeeming.appGUI(this.getContentPane());
        this.setVisible(true);
    }

//İŞLEM YÖNTEMLERİ:
    

//ERİŞİM YÖNTEMLERİ:
    public JButton getBtnProduceMap(){
        if(btnProduceMap == null){
            btnProduceMap = new JButton("Harita oluştur");
            btnProduceMap.addActionListener(getListenerForActsAndSignals());
        }
        return btnProduceMap;
    }
    public JSpinner getSpinnerMapWidth(){
        if(spinnerMapWidth == null){
            spinnerMapWidth = new JSpinner();
            spinnerMapWidth.setModel(new SpinnerNumberModel(608, 11, 100000, 1));
            spinnerMapWidth.addChangeListener(getListenerForActsAndSignals());
        }
        return spinnerMapWidth;
    }
    public JSpinner getSpinnerMapHeigth(){
        if(spinnerMapHeigth == null){
            spinnerMapHeigth = new JSpinner();
            spinnerMapHeigth.setModel(new SpinnerNumberModel(342, 11, 100000, 1));
            spinnerMapHeigth.addChangeListener(getListenerForActsAndSignals());
        }
        return spinnerMapHeigth;
    }
    public EntryPageActs getListenerForActsAndSignals(){
        if(actsAndSignals == null){
            actsAndSignals = new EntryPageActs(this);
        }
        return actsAndSignals;
    }
    public JLabel getLblWidth(){
        if(lblWidth == null){
            lblWidth = new JLabel("Genişlik");
        }
        return lblWidth;
    }
    public JLabel getLblHeight(){
        if(lblHeight == null){
            lblHeight = new JLabel("Uzunluk");
        }
        return lblHeight;
    }
}
