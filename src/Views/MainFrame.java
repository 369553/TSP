package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
    public static MainFrame Frame_Main;
    public static BorderLayout layout;

    private MainFrame (){
    }

    public static MainFrame getFrame_Main (){
        if ( Frame_Main == null ){
            Frame_Main = new MainFrame();
            layout = new BorderLayout();
            Frame_Main.setLayout( layout );
            Frame_Main.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//            Frame_Main.setBounds( 140, 15, 1080, 720 );//ESKİ AYAR
            Frame_Main.setBounds( 260, 100, 810, 540 );
            //Frame_Main.setBounds( 400, 140, 615, 500 );
            Frame_Main.setMinimumSize( new Dimension (600, 400));
            //Gerek yok, çünkü frame de boşlu yok Frame_Main.setBackground( Color.decode (SystemSettings.getSettings().getCurrentTheme().getBackgroundColor() ) );
            //Frame_Main.setMinimumSize( new Dimension (600, 400) ) ;
        }
        return Frame_Main ;
    }
    public void meaningFrame(Dimension screenDimension){
        int startX = ((screenDimension.width - this.getMinimumSize().width) / 2) - 10;
//        System.out.println("screenDimension.width ("  + screenDimension.width + ") - minimumSize.width(" + Frame_Main.getMinimumSize().width + ") / 2 = " + startX);
        int startY = ((screenDimension.height - this.getMinimumSize().height) / 2) - 7;
        this.setBounds(startX, startY, this.getMinimumSize().width, this.getMinimumSize().height);
    }
}
