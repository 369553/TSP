package Control;
import Base.City;
import Base.Map;
import Base.Route;
import Views.ContactPanel;
import Views.MainFrame;
import Views.TSPControlPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class IDARE {
    private static IDARE idare;
    private ArrayList<City> cities = null;
//    private ArrayList<Route> routes;
    private static int IDCounterForCity = 1;
    private static ArrayList<Integer> uselessIDs = null;
    boolean flagSaveLog = false;
    private Map map = null;
    public int cityNumber = 0;
    private TSPDraw drawing;
    private final JFrame frame;
    private TSPControlPanel controlPanel;
    private String[] algorithmNames;
    private JScrollPane scrpaneDraw;
    private final Dimension screenDimension;
    Dimension maxDrawPanelDimension;
    Dimension minimumDimensionForSoftware;
    Dimension currentDimension;

    private IDARE(Map map, JFrame frame){
        this.map = map;
        drawing = new TSPDraw(map);
        screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame = frame;
        this.frame.setMinimumSize(getMinimumDimensionForSoftware());
        MainFrame.getFrame_Main().meaningFrame(screenDimension);
        currentDimension = MainFrame.getFrame_Main().getSize();
        GUISeeming.appGUI(getScrpaneDraw());
        MainFrame.getFrame_Main().setVisible(true);
    }

//İŞLEM YÖNTEMLERİ:
    //BAŞLANGIÇ YÖNTEMİ
    private static boolean startIDARE(Map map, JFrame frame){
        if(map == null)
            return false;
        if(map.getHeight() < 10 || map.getWidth() < 10)
            return false;
        else{
            idare = new IDARE(map, frame);
            return true;
        }
    }
    public static boolean startIDARE(Map map){
        if(map == null)
            return false;
        if(map.getHeight() <= 10 || map.getWidth() <= 10)
            return false;
        else{
            idare = new IDARE(map, MainFrame.getFrame_Main());
            return true;
        }
    }
    public void startSystem(){
        frame.add(getScrpaneDraw(), BorderLayout.CENTER);//frame.add(getDrawing().getDrawArea(), BorderLayout.CENTER);vv
        frame.add(ContactPanel.getContactPanel(), BorderLayout.SOUTH);
        openTSPControlPanel();
        frame.setVisible(true);
        ContactPanel.getContactPanel().setVisible(true);
        controlPanel.setVisible(true);
    }
    public void openTSPControlPanel(){
        frame.add(getControlPanel(), BorderLayout.WEST);
    }
    public boolean addCity(City city){
        if(getCityByName(city.getName()) != null){
            System.out.println("fsdfd");
            showNotifyMessage("Bu isimde bir belde zâten var", "Warning");
            if(flagSaveLog)
                writeLog("Aynı isimde bir belde eklenmeye çalışıldı", "Warning");
            IDCounterForCity--;
            return false;
        }
        if(getCityByID(city.getId()) != null){
            showNotifyMessage("Bu belde numaralı bir belde zâten var", "Warning");
            if(flagSaveLog)
                writeLog("Aynı numaralı bir belde eklenmeye çalışıldı", "Warning");
            IDCounterForCity--;
            return false;
        }
        if(!checkCanAddOnTheMap(city.getX(), city.getY())){
            showNotifyMessage("Bu nokta harita sınırlarında değil veyâ bu noktanın olduğu yerde belde var.", "Warning");
            IDCounterForCity--;
            if(flagSaveLog)
                writeLog("Harita sınırlarında olmayan veyâ haritada boş olmayan bir noktaya belde eklenmeye çalışıldı", "Warning");
            return false;
        }
        getCities().add(city);
        map.getArea()[(int)city.getY()][(int)city.getX()] = city.getId();
        updateSys();
        if(flagSaveLog)
            writeLog("Yeni bir belde eklendi (belde numarası : " + city.getId() + ", isim : " + city.getName() + ", x : " + city.getX() + ", y : " + city.getY() + ")", "DataChanging");
        return true;
    }
    public boolean removeCity(City city){
        if(getCityByName(city.getName()) == null){
            showNotifyMessage("Bu isimde bir belde sistemde kayıtlı değil", "Warning");
            if(flagSaveLog)
                writeLog("Sistemde kayıtlı olmayan bir belde sistemden silinmeye çalışıldı", "Warning");
            return false;
        }
        getCities().remove(city);
        getUselessIDs().add(city.getId());
        map.getArea()[(int) city.getY()][(int) city.getX()] = 0;
        updateSys();
        if(getControlPanel().getStartCity() == city)
            getControlPanel().setStartCity(null);
        if(flagSaveLog)
            writeLog("Bir belde silindi (belde numarası : " + city.getId() + ", isim : " + city.getName() + ", x : " + city.getX() + ", y : " + city.getY() + ")", "DataChanging");
        return true;
    }
    public City getCityByName(String cityName){
        for(City city : getCities()){
            if (city.getName().equals(cityName))
                return city;
        }
        return null;
    }
    public City getCityByID(int cityID){
        for(City city : getCities()){
            if(city.getId() == cityID)
                return city;
        }
        return null;
    }
    public City[] getCitiesByIDs(int[] cityIDs){
        City[] cityList = new City[cityIDs.length];
        for(int index = 0; index < cityList.length; index++){
            cityList[index] = getCityByID(cityIDs[index]);
        }
        return cityList;
    }
    public void showNotify(Object from, String message, String messageType){
        if(!isShowingFrom(from.getClass().getName()))
            return;
        showNotifyMessage(message, messageType);
      //  frame.setVisible(true);
    }
    public static int takeNewIDForNewCity(){
        IDCounterForCity++;
        return IDCounterForCity - 1;
    }
    public void updateIDARE(){
        if(cities == null)
            return;
        if(cities.isEmpty()){
            return;
        }
        cityNumber = cities.size();
        drawing.setCities(cities);
        getControlPanel().updateInfo();
        frame.setVisible(true);
    }
    public void showRoute(Route route){
        getDrawing().setRoute(route);
        drawing.deleteCurrentRoute();
        drawing.drawFullRoute();
        getControlPanel().setRouteOfRoutePanel(route);
        frame.setVisible(true);
    }
    public void chooseRouteParts(int[] routePartNumbers){
        getDrawing().chooseRouteSteps(routePartNumbers);
    }
    //ARKAPLAN YÖNTEMLERİ:
    private int getNewCityID(){
        IDCounterForCity++;
        return (IDCounterForCity - 1);
    }
    private void showNotifyMessage(String text, String messageType){
        ContactPanel.getContactPanel().showMessage(text, messageType);
    }
    private void writeLog(String text, String type){
        //...
    }
    private void updateSys(){
        updateIDARE();
        if(drawing.getDrawArea() != null)
            drawing.getDrawArea().repaint();
    }
    private boolean checkCanAddOnTheMap(double x, double y){
        if(x >= map.getWidth() || y >= map.getHeight())
            return false;
        return map.getArea()[(int)y][(int)x] == 0;
    }
    private boolean isShowingFrom(String nameOfClass){
        switch(nameOfClass){
            case "Views.TSPDraw" :{
                return true;
            }
            case "Views.TSPControlPanel" :{
                return true;
            }
            case "Control.IDARE" :{
                return true;
            }
            default: {return false;}
        }
    }

//ERİŞİM YÖNTEMLERİ:
    //ANA ERİŞİM YÖNTEMİ:
    public static IDARE getIdare() {
        return idare;
    }
    public ArrayList<City> getCities() {
        if(cities == null){
            cities = new ArrayList<City>();
        }
        return cities;
    }
    public City[] getCitiesAsArray() {
        if(getCities().isEmpty())
            return null;
        City[] cityArray = new City[cities.size()];
        cities.toArray(cityArray);
        System.out.println("cityArray[0].ism : " + cityArray[0].name);
        return cityArray;
    }
    public String[] getCityNames(){
        if(getCities().isEmpty())
            return new String[]{""};
        String[] value = new String[cities.size()];
        for(int index = 0; index < value.length; index++){
            value[index] = cities.get(index).name;
        }
        return value;
    }
    public int getIDCounterForCity() {
        return IDCounterForCity;
    }
    private static ArrayList<Integer> getUselessIDs() {
        if(uselessIDs == null){
            uselessIDs = new ArrayList<Integer>();
        }
        return uselessIDs;
    }
    public Map getMap() {
        return map;
    }
    public TSPControlPanel getControlPanel(){
        if(controlPanel == null){
           controlPanel = new TSPControlPanel();
        }
        return controlPanel;
    }
    public String[] getAlgorithmNames(){
        if(algorithmNames == null){
            algorithmNames = new String[3];
            algorithmNames[0] = "Doğrusal";
            algorithmNames[1] = "Açgözlü";
            algorithmNames[2] = "BenzetilmişTavlama";
        }
        return algorithmNames;
    }
    public TSPDraw getDrawing() {
        if(drawing == null){
            drawing = new TSPDraw(this.map);
        }
        return drawing;
    }
    public Dimension getMaxDrawPanelDimension(){
        if(maxDrawPanelDimension == null){
            int maxX = (int) (screenDimension.getWidth() - (screenDimension.getWidth() / 4) - 25);
            int maxY = (int) (screenDimension.getHeight() - (screenDimension.getHeight() / 7) - 40);
            maxDrawPanelDimension = new Dimension(maxX, maxY);
        }
    ///    System.out.println("azamî genişlik : " + maxX + " azamî uzunluk : " + maxY);
        return maxDrawPanelDimension;
    }
    public Dimension getMinimumDimensionForSoftware(){
        if(minimumDimensionForSoftware == null)
            minimumDimensionForSoftware = new Dimension();
        minimumDimensionForSoftware.width = getMap().getWidth() + 30 ;
        minimumDimensionForSoftware.width += (minimumDimensionForSoftware.width / 2);
        minimumDimensionForSoftware.height = getMap().getHeight() + 18;
        minimumDimensionForSoftware.height += (minimumDimensionForSoftware.height / 2);
        return minimumDimensionForSoftware;
    }
    public JScrollPane getScrpaneDraw(){
        if(scrpaneDraw == null){
            scrpaneDraw = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrpaneDraw.setViewportView(getDrawing().getDrawArea());
            Dimension maxDimension = getMaxDrawPanelDimension();
            if(getDrawing().getDrawArea().getPreferredSize().getWidth() > maxDimension.getWidth())
                scrpaneDraw.setPreferredSize(maxDimension);
        }
        return scrpaneDraw;
    }
    public Dimension getCurrentDimension() {
        if(currentDimension == null){
            currentDimension = MainFrame.getFrame_Main().getSize();
        }
        return currentDimension;
    }
}