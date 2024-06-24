package Control;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.Border;

public class GUISeeming{
    protected static GUISeeming seeming = GUISeeming.OrderProducer("Standard");
    String seemingName, hexBackgroundColor, hexButtonColor, hexTextColor,
            hextButtonTextColor, hexBorderColor, hexTextAreaColor, hexTextAreaTextColor,
            hexTextAreaSelectedColor, hexTextAreaSelectedTextColor, hexCursorColor, hexMenuColor,
            hexDrawPanelBackgroundColor, hexDrawPanelTextColor, hexDrawPanelLineColor, hexDrawPanelSelectedLineColor,
            hexMouseOnButtonColor, hexMouseOnButtonTextColor;
    Font fontUI = new Font("Liberation Serif", Font.BOLD | Font.ITALIC, 14);

    public GUISeeming(String seemingName, String hexBackgroundColor, String hexButtonColor, String hexTextColor,
            String hextButtonTextColor, String hexBorderColor, String hexTextAreaColor, String hexTextAreaTextColor,
            String hexTextAreaSelectedColor, String hexTextAreaSelectedTextColor, String hexCursorColor, String hexMenuColor,
            String hexDrawPanelBackgroundColor, String hexDrawPanelTextColor, String hexDrawPanelLineColor, String hexDrawPanelSelectedLineColor,
            String hexMouseOnButtonColor, String hexMouseOnButtonTextColor) {
        this.seemingName = seemingName;
        this.hexBackgroundColor = hexBackgroundColor;
        this.hexButtonColor = hexButtonColor;
        this.hexTextColor = hexTextColor;
        this.hextButtonTextColor = hextButtonTextColor;
        this.hexBorderColor = hexBorderColor;
        this.hexTextAreaColor = hexTextAreaColor;
        this.hexTextAreaTextColor = hexTextAreaTextColor;
        this.hexTextAreaSelectedColor = hexTextAreaSelectedColor;
        this.hexTextAreaSelectedTextColor = hexTextAreaSelectedTextColor;
        this.hexCursorColor = hexCursorColor;
        this.hexMenuColor = hexMenuColor;
        this.hexDrawPanelBackgroundColor = hexDrawPanelBackgroundColor;
        this.hexDrawPanelTextColor = hexDrawPanelTextColor;
        this.hexDrawPanelLineColor = hexDrawPanelLineColor;
        this.hexDrawPanelSelectedLineColor = hexDrawPanelSelectedLineColor;
        this.hexMouseOnButtonColor = hexMouseOnButtonColor;
        this.hexMouseOnButtonTextColor = hexMouseOnButtonTextColor;
    }

//İŞLEM YÖNTEMLERİ:
    protected static void appGUI(Component comp){
        if(appGUIForSpecialContainers(comp))
            return;
        switch(comp.getClass().getName()){
            case "javax.swing.JButton" :{
                JButton button = (JButton) comp;
                button.setBackground(Color.decode(seeming.hexButtonColor));
                button.setForeground(Color.decode(seeming.hextButtonTextColor));
                button.setBorderPainted(true);
                button.setBorder(GUISeeming.getComponentBorder(true, true, true, true, 1));
                button.setFont(seeming.fontUI);
                button.addMouseListener(new Movements(button));
                button.setFocusPainted(false);
                return;
            }
            case "javax.swing.JList" :{
                JList list = (JList) comp;
                list.setBackground(Color.decode(seeming.hexBackgroundColor));
                list.setForeground(Color.decode(seeming.hexTextColor));
                list.setSelectionBackground(Color.decode(seeming.hexButtonColor));
                list.setSelectionForeground(Color.decode(seeming.hextButtonTextColor));
                list.setBorder(GUISeeming.getComponentBorder(true, true, true, true, 1));
                return;
            }
            case "javax.swing.JTextArea" :{
                JTextArea txtArea = (JTextArea) comp;
                txtArea.setBackground(Color.decode(seeming.hexTextAreaColor));
                txtArea.setForeground(Color.decode(seeming.hexTextAreaTextColor));
                txtArea.setCaretColor(Color.decode(seeming.hexCursorColor));
                txtArea.setSelectionColor(Color.decode(seeming.hexTextAreaSelectedColor));
                txtArea.setSelectedTextColor(Color.decode(seeming.hexTextAreaSelectedTextColor));
                txtArea.setBorder(GUISeeming.getComponentBorder(true, true, true, true));
                txtArea.setFont(seeming.fontUI);
                /*if(txtArea.getBorder() != null){
                    txtArea.setBorder(getComponentBorder(true, true, true, true));
                }*/
                return;
            }
            case "javax.swing.JTextField" :{
                JTextField txtArea = (JTextField) comp;
                txtArea.setBackground(Color.decode(seeming.hexTextAreaColor));
                txtArea.setForeground(Color.decode(seeming.hexTextAreaTextColor));
                txtArea.setCaretColor(Color.decode(seeming.hexCursorColor));
                txtArea.setSelectionColor(Color.decode(seeming.hexTextAreaSelectedColor));
                txtArea.setSelectedTextColor(Color.decode(seeming.hexTextAreaSelectedTextColor));
                txtArea.setBorder(GUISeeming.getComponentBorder(true, true, true, true));
                txtArea.setFont(seeming.fontUI);
                return;
            }
            case "javax.swing.JComboBox":{
                JComboBox cmbox = (JComboBox) comp;
                cmbox.setFont(seeming.fontUI);
                cmbox.setBackground(Color.decode(seeming.hexButtonColor));
                cmbox.setForeground(Color.decode(seeming.hexButtonColor));
                if(cmbox.getRenderer().getClass().getName().contains("ComboBoxRenderer")){
                    cmbox.setRenderer(ListCellRenderStandard.getConfiguredRenderer());
                }
                return;
            }
            case "javax.swing.JScrollPane" :{
                JScrollPane scrPane= (JScrollPane) comp;
                GUISeeming.appGUI((Component) scrPane.getViewport());
                scrPane.setViewportBorder(GUISeeming.getComponentBorder(true, true, true, true, 1));
                scrPane.getViewport().setBackground(Color.decode(seeming.hexBackgroundColor));
                scrPane.setBorder(GUISeeming.getComponentBorder(true, true, true, true, 1));
                /*scrPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);*/
                scrPane.getHorizontalScrollBar().setBackground(Color.decode(seeming.hexBackgroundColor));
                scrPane.getVerticalScrollBar().setBackground(Color.decode(seeming.hexBackgroundColor));
                scrPane.getHorizontalScrollBar().setBorder(GUISeeming.getComponentBorder(true, true, true, true, 1));
                scrPane.getVerticalScrollBar().setBorder(GUISeeming.getComponentBorder(true, true, true, true, 1));
                scrPane.getHorizontalScrollBar().setUnitIncrement(16);
                scrPane.getVerticalScrollBar().setUnitIncrement(16);
                scrPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 40));
                scrPane.getHorizontalScrollBar().setPreferredSize(new Dimension(10, 10));
                for(Component component : scrPane.getVerticalScrollBar().getComponents()){
                    component.setBackground(Color.decode(seeming.hexButtonColor));
                    component.addMouseListener(new Movements(component));
                }
                for(Component component : scrPane.getHorizontalScrollBar().getComponents()){
                    component.setBackground(Color.decode(seeming.hexButtonColor));
                    component.addMouseListener(new Movements(component));
                }
                return;
            }
            case "javax.swing.JViewport" :{
                JViewport view = (JViewport) comp;
                if(view.getView() != null)
                    GUISeeming.appGUI(view.getView());
                return;
            }
            case "javax.swing.JSpinner" :{
                JSpinner spinComp  =(JSpinner) comp;
                spinComp.setBackground(Color.decode(seeming.hexBackgroundColor));
                spinComp.setForeground(Color.decode(seeming.hexTextColor));
                spinComp.setBorder(GUISeeming.getComponentBorder(true, true, true, true, 1));
                for(int index = 0; index < spinComp.getComponentCount(); index++){
                    if(spinComp.getComponent(index).getClass().getName().equals("javax.swing.plaf.basic.BasicArrowButton")){
                        javax.swing.plaf.basic.BasicArrowButton btnChanger = (javax.swing.plaf.basic.BasicArrowButton) spinComp.getComponent(index);
                        btnChanger.setBackground(Color.decode(seeming.hexButtonColor));
                        btnChanger.setForeground(Color.decode(seeming.hextButtonTextColor) );
                        btnChanger.addMouseListener(new Movements(btnChanger));
                        btnChanger.setBorderPainted(false);
                        btnChanger.setBorder(GUISeeming.getComponentBorder(true, true, true, true, 1));
                        btnChanger.setBorderPainted(true);
                    }
                }
                if(spinComp.getEditor().getComponent(0).getClass().getName().equals("javax.swing.JFormattedTextField")){
                    JFormattedTextField txtEditor = (JFormattedTextField) spinComp.getEditor().getComponent(0);
                    txtEditor.setBackground(Color.decode(seeming.hexTextAreaColor));
                    txtEditor.setForeground(Color.decode(seeming.hexTextAreaTextColor));
                    txtEditor.setSelectionColor(Color.decode(seeming.hexTextAreaSelectedColor));
                    txtEditor.setSelectedTextColor(Color.decode(seeming.hexTextAreaSelectedTextColor));
                }
                return;
            }
            default :{
                comp.setBackground(Color.decode(seeming.hexBackgroundColor));
                comp.setForeground(Color.decode(seeming.hexTextColor));
                comp.setFont(seeming.fontUI);
            }
        }
    }
    public static void appGUI(Container container){
        container.setBackground(Color.decode(seeming.hexBackgroundColor));
        container.setForeground(Color.decode(seeming.hexTextColor));
        container.setFont(seeming.fontUI);
        for(Component component : container.getComponents()){
            appGUI(component);
        }
    }
    public static HashMap<String, Color> getColorsForDrawPanel(){
        HashMap<String, Color> colors = new HashMap<String, Color>();
        colors.put("backgroundColor", Color.decode(seeming.hexDrawPanelBackgroundColor));
        colors.put("textColor", Color.decode(seeming.hexDrawPanelTextColor));
        colors.put("lineColor", Color.decode(seeming.hexDrawPanelLineColor));
        colors.put("selectedLineColor", Color.decode(seeming.hexDrawPanelSelectedLineColor));
        return colors;
    }
    public static Color[] getColorsForRenderer(){
        Color[] clrColors = new Color[4];
        clrColors[0] = Color.decode(seeming.hexButtonColor);
        clrColors[1] = Color.decode(seeming.hextButtonTextColor);
        clrColors[2] = Color.decode(seeming.hexMouseOnButtonColor);
        clrColors[3] = Color.decode(seeming.hexMouseOnButtonTextColor);
        return clrColors;
    }
    public static String[] getColorsForContactPanel(){
        String[] strColors = new String[6];
        strColors[0] = seeming.hexBackgroundColor;
        switch(seeming.seemingName){
            case "Standard" :{
                strColors[1] = "#e1625e";//Warning
                strColors[2] = "#44d794";//Succesful
                strColors[3] = "#e1c5bd";//LittlePoint
                strColors[4] = "#778edd";//Info
                strColors[5] = "#fac35b";//Advice
                break;
            }
            case "Pink" :{
                strColors[1] = "#f25701";//Warning
                strColors[2] = "#8fb701";//Succesful
                strColors[3] = "#ffa38d";//LittlePoint
                strColors[4] = "#76a3ee";//Info
                strColors[5] = "#ebc172";//Advice
                break;
            }
            case "Blue" :{
                strColors[1] = "#eb4b72";//Warning
                strColors[2] = "#5ef59c";//Succesful
                strColors[3] = "#e18c8f";//LittlePoint
                strColors[4] = "#9d98fa";//Info
                strColors[5] = "#f89885";//Advice
                break;
            }
            case "Dark" :{
                strColors[1] = "#53060a";//Warning
                strColors[2] = "#041800";//Succesful
                strColors[3] = "#320b1d";//LittlePoint
                strColors[4] = "#070657";//Info
                strColors[5] = "#5e2213";//Advice
                break;
            }
        }
        return strColors;
    }
    public static void mouseOnMovementRefresh(Component comp){
        switch(comp.getClass().getName()){
            default:{
                comp.setBackground(Color.decode(seeming.hexMouseOnButtonColor));
                comp.setForeground(Color.decode(seeming.hexMouseOnButtonTextColor));
            }
        }
    }
    public static void mouseOffMovementRefresh(Component comp){
        switch(comp.getClass().getName()){
            default:{
                comp.setBackground(Color.decode(seeming.hexButtonColor));
                comp.setForeground(Color.decode(seeming.hextButtonTextColor));
            }
        }
    }
    //ARKAPLAN İŞLEM YÖNTEMLERİ:
    private static Border getComponentBorder(boolean isTop, boolean isLeft, boolean isBottom, boolean isRight, int size){
        int top = 0, right = 0, bottom = 0, left = 0;
        if(isTop)
            top = size;
        if(isRight)
            right = size;
        if(isBottom)
            bottom = size;
        if(isLeft)
            left = size;
        return BorderFactory.createMatteBorder(top, right, bottom, left, Color.decode(seeming.hexBorderColor));
    }
    private static Border getComponentBorder(boolean isTop, boolean isLeft, boolean isBottom, boolean isRight){
        return getComponentBorder(isTop, isRight, isBottom, isLeft, 2);
    }
    private static boolean appGUIForSpecialContainers(Component component){
        switch(component.getClass().getName()){
            case "View.DrawPanel" :{
                GUISeeming.appGUI((Container) component);
                return true;
            }
            case "View.RoutePanel" :{
                GUISeeming.appGUI((Container) component);
                return true;
            }
            default:{
                return false;
            }
        }
    }

//ERİŞİM YÖNTEMLERİ:
    public static GUISeeming OrderProducer(String seemingName){
        switch(seemingName){
            default :{
                return new GUISeeming("Standard",
                        "#F5F5DC",//hexBackgroundColor : Arkaplan rengi
                        "#cfcfae",//hexButtonColor : Düğme rengi
                        "#7B3F00",//hexTextColor : Yazı rengi
                        "#800000",//hextButtonTextColor : Düğme yazı rengi
                        "#aca090",//hexBorderColor : Kenarlık rengi
                        "#300112",//hexTextAreaColor : Yazı alanı rengi
                        "#eaba55",//hexTextAreaTextColor : Yazı alanı yazı rengi
                        "#976c88",//hexTextAreaSelectedColor : Yazı alanı seçim rengi
                        "#000000",//hexTextAreaSelectedTextColor : Yazı alanı seçilen yazı rengi
                        "#9b241b",//hexCursorColor : Yazı alanı imleç rengi
                        "#fc8b8b",//hexMenuColor : Menü arkaplan rengi
                        "#F5D5DC",//hexDrawPanelBackgroundColor : Çizim paneli arkaplan rengi
                        "#DBDF00",//hexDrawPanelTextColor : Çizim paneli yazı rengi
                        "#1B9FF0",//hexDrawPanelLineColor : Çizim paneli çizgi rengi
                        "#FB9F20",//hexDrawPanelSelectedLineColor : Çizim paneli seçili çizgi rengi
                        "#d2d260",//hexMouseOnButtonColor : Fare üzerindeyken düğme rengi
                        "#30d260");//hexMouseOnButtonTextColor : Fare üzerindeyken düğme yazısı rengi
            }
        }
    }
}