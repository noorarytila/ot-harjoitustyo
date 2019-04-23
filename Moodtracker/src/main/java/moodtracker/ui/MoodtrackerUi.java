//to-do sovellusnäkymän scene
package moodtracker.ui;

import java.time.LocalDate;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import moodtracker.dao.FileMoodDao;
import moodtracker.dao.FileUserDao;
import moodtracker.domain.Mood;
import moodtracker.domain.MoodtrackerActions;
import moodtracker.domain.User;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;


public class MoodtrackerUi extends Application {
    
    private MoodtrackerActions moodtrackerActions;
    
    private Scene startScene;
    private Scene newUserScene;
    private Scene mainScene;
    private Scene piechartScene;
    private User currentlyLoggedIn;
    private LocalDate localdate;
    
    public List<Mood> moods;
    

    @Override
    public void init() throws Exception{
        Properties properties = new Properties();
        
        FileInputStream inputstream = new FileInputStream("config.properties");
        
        properties.load(inputstream);
        
        String userFile = properties.getProperty("userFile");
        String moodFile = properties.getProperty("moodFile");
        
        FileUserDao userDao = new FileUserDao(userFile);
        FileMoodDao moodDao = new FileMoodDao(moodFile, userDao);
        
        moodtrackerActions = new MoodtrackerActions(userDao, moodDao);
        
        currentlyLoggedIn = moodtrackerActions.currentlyLoggedIn;
        
    } 
    
    
    

    
    @Override
    public void start(Stage stage) {
        
        //todo log out
        //todo go back from creating new user
        //todo clear new user input fields
        //todo new user fieldien koko
        //todo user created tekstin hienosäätö
        //mood saved teksti pois muutaman sekunnin päästä
        //kuka on kirjautunu?
        //piirakasta paluu go back
        
        
        VBox loginPane = new VBox(15);
        VBox startPane = new VBox(30);
        VBox newuserPane = new VBox(15);
        VBox mainPane = new VBox(15);
        HBox moodButtonPane = new HBox(15);
        VBox piechartPane = new VBox(30);
        
        loginPane.setPadding(new Insets(10));
        startPane.setPadding(new Insets(20));
        Label appName = new Label("MoodTracker");
        
        Label usernameLabel = new Label("Username");
        TextField usernameInput = new TextField();
        Button login = new Button("LOGIN");
        Button newUser = new Button("Create new user");
        
        Label userCreated = new Label("");
        
        
        //todo MoodTracker labelin kustomointi
        //todo username kentän koko
        //
        
        Label mainTitle = new Label("");
        Label addNewMood = new Label("Add new mood, 1 = lowest, 10 = highest");
        
        //todo radiobuttoneille moodeja vastaavat värit
        
        
        ToggleGroup group = new ToggleGroup();
        RadioButton m1 = new RadioButton("1");
        RadioButton m2 = new RadioButton("2");
        RadioButton m3 = new RadioButton("3");
        RadioButton m4 = new RadioButton("4");
        RadioButton m5 = new RadioButton("5");
        RadioButton m6 = new RadioButton("6");
        RadioButton m7 = new RadioButton("7");
        RadioButton m8 = new RadioButton("8");
        RadioButton m9 = new RadioButton("9");
        RadioButton m10 = new RadioButton("10");
        m1.setToggleGroup(group);
        //m1.setSelected(true);
        m2.setToggleGroup(group);
        m3.setToggleGroup(group);
        m4.setToggleGroup(group);
        m5.setToggleGroup(group);
        m6.setToggleGroup(group);
        m7.setToggleGroup(group);
        m8.setToggleGroup(group);
        m9.setToggleGroup(group);
        m10.setToggleGroup(group);
        m1.setUserData(m9);
        
        Button add = new Button("Add mood");
        Label moodCreated = new Label("");
        
        Button showPieChart = new Button("Show moods in piechart");
        //todo xyAxis chart

        
        loginPane.getChildren().addAll(usernameInput, usernameLabel, login);
        startPane.getChildren().addAll(appName, loginPane, newUser, userCreated);
        moodButtonPane.getChildren().addAll(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10);
        mainPane.getChildren().addAll(mainTitle, addNewMood, moodButtonPane, add, moodCreated, showPieChart);
        
        login.setOnAction(e ->{
            String inputUsername = usernameInput.getText();
            
            //todo viesti jos käyttäjää ei löydy
            
            if(moodtrackerActions.login(inputUsername)) {
                mainTitle.setText("Welcome to MoodTracker! ");
                stage.setScene(mainScene);
            }
        });
        
        newUser.setOnAction(e->{
            usernameInput.setText("");
            stage.setScene(newUserScene);
        });
        
        TextField newUsernameField = new TextField();
        Label newUsername = new Label("username");
        TextField newNameField = new TextField();
        Label newName = new Label("name");
        Label errorMessage = new Label("");
        
        Button createNewUserButton = new Button("CREATE");
        
        newuserPane.getChildren().addAll(newUsernameField, newUsername, newNameField, newName, createNewUserButton, errorMessage);
        
        createNewUserButton.setOnAction(e->{
            String username = newUsernameField.getText();
            String name = newNameField.getText();
            
            // todo: jos käyttäjätunnus on varattu ilmoitus siitä
   
            if ( username.length()==2 || name.length()<2 ) {
                errorMessage.setText("name or username too short");
            } else if (moodtrackerActions.createUser(username, name)) {
                userCreated.setText("user created");
                stage.setScene(startScene);      
            } 
 
        });
        
        add.setOnAction(e ->{
            
            RadioButton rb = (RadioButton)group.getSelectedToggle();
            String sv = rb.getText();
            Integer moodvalue = Integer.parseInt(sv);
            
            moodtrackerActions.createMood(moodvalue);
            
            moodCreated.setText("Mood saved! :)");
            rb.setSelected(false);
            
            
            
        });
        
        showPieChart.setOnAction(e -> {
            
            showPieChart(stage);
            
   
        });
        
        
        
        startScene = new Scene(startPane, 600, 500);
        newUserScene = new Scene(newuserPane, 600, 500);
        mainScene = new Scene(mainPane, 600, 500);
        
        
        
        //setup start stage
        
        stage.setTitle("Moods");
        stage.setScene(startScene);
        stage.show();
        
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private void showPieChart(Stage stage) {
        int value1 = 0;
        int value2 = 0;
        int value3 =0;
        int value4 =0;
        int value5 =0;
        int value6 =0;
        int value7 =0;
        int value8 =0;
        int value9 =0;
        int value10 =0;
        
            try {
                
                System.out.println(moodtrackerActions.usersMoods());
                
                HashMap<Integer, Integer> moodmap = moodtrackerActions.usersMoods();
                
                if(moodmap.containsKey(1)) {
                    value1 = moodmap.get(1);
                } 
                
                if(moodmap.containsKey(2)) {
                    value2 = moodmap.get(2);
                }
                
                if(moodmap.containsKey(3)) {
                    value3 = moodmap.get(3);
                }
                
                if(moodmap.containsKey(4)) {
                    value4 = moodmap.get(4);
                }
                
                if(moodmap.containsKey(5)) {
                    value5 = moodmap.get(5);
                }
                
                if(moodmap.containsKey(6)) {
                    value2 = moodmap.get(6);
                }
                
                if(moodmap.containsKey(7)) {
                    value2 = moodmap.get(7);
                }
                
                if(moodmap.containsKey(8)) {
                    value2 = moodmap.get(8);
                }
                if(moodmap.containsKey(9)) {
                    value2 = moodmap.get(9);
                }
                
                if(moodmap.containsKey(10)) {
                    value2 = moodmap.get(10);
                }
                
                
            } catch (Exception ex) {
                Logger.getLogger(MoodtrackerUi.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //todo kaavion numeroille oikeat värit
            
             ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            
                new PieChart.Data("1", value1),
                new PieChart.Data("2", value2),
                new PieChart.Data("3", value3),
                new PieChart.Data("4", value4),
                new PieChart.Data("5", value5),
                new PieChart.Data("6", value6),
                new PieChart.Data("7", value7),
                new PieChart.Data("8", value8),
                new PieChart.Data("9", value9),
                new PieChart.Data("10", value10));
                
            
             
                 PieChart pieChart = new PieChart(pieChartData);
                 
                
                 
                 
                 Group root = new Group(pieChart);
                 
                 piechartScene = new Scene(root, 600, 500);
                 stage.setScene(piechartScene);
    }
    
}
