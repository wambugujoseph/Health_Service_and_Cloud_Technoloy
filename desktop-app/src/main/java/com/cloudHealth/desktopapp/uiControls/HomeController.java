package com.cloudHealth.desktopapp.uiControls;

import com.cloudHealth.desktopapp.service.AuthorizeUserService;
import com.cloudHealth.desktopapp.uiControls.uiHelper.HomeControllerHelper;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import sun.reflect.generics.tree.Tree;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Thursday
 * Date: 1/16/2020
 * Time: 12:09 AM
 * Project: desktop-app
 */

@Component
public class HomeController implements Initializable {

    @FXML private JFXTreeView<?> activityTreeView;
    @FXML private Button userAccountIcon;
    @FXML private AnchorPane actionBar;
    @FXML private AnchorPane activityAreaAnchorPane;
    @FXML private Label pageTitle;
    @FXML private FontAwesomeIconView userAccountICaret;



    @Autowired
    private HomeControllerHelper homeControllerHelper;
    @Autowired
    private AuthorizeUserService authorizeUserService;

    JFXButton btnLogOut = btnLogOut = new JFXButton("Sign Out");
    JFXButton your_profileButton = new JFXButton("Your Profile");

    private final Resource profilePage, profileHeaderAndTabPane, getPatientRecordPage, addPatientRecordPage, mngtPractitionerAdd,mngtHealthUnitAdd;
    private final ApplicationContext ac;
    HomeController(
            @Value("classpath:/templates/patientProfile.fxml") Resource resource,
            @Value("classpath:/templates/profileHeaderTabPane.fxml") Resource profileHeaderAndTabPane,
            @Value("classpath:/templates/getPatientRecordPage.fxml") Resource getPatientRecordPage,
            @Value("classpath:/templates/addPatientRecordPage.fxml") Resource addPatientRecordPage,
            @Value("classpath:/templates/practitioner.fxml") Resource mngtPractitionerAdd,
            @Value("classpath:/templates/healthUnit.fxml") Resource mngtHealthUnitAdd,
            ApplicationContext ac) {
        this.ac = ac;
        this.profilePage = resource;
        this.profileHeaderAndTabPane = profileHeaderAndTabPane;
        this.getPatientRecordPage = getPatientRecordPage;
        this.addPatientRecordPage = addPatientRecordPage;
        this.mngtPractitionerAdd = mngtPractitionerAdd;
        this.mngtHealthUnitAdd = mngtHealthUnitAdd;
    }

        @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Starter Method Calls
        setUserAccountIcon();
        setActivityTreeView();


    }

    private void  setActivityTreeView(){
        activityTreeView.setRoot(homeControllerHelper.getActivityTreeViewItems());
        activityTreeView.getSelectionModel().selectedItemProperty().addListener((observable,oldValue, newValue) ->{
            if(newValue != null && newValue != oldValue){
                Text text = (Text)newValue.getValue();
                String activitySelected =text.getText();

                if (activitySelected.equalsIgnoreCase("Profile")){
                    try {
                        setPatientProfileActivityUi();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(activitySelected.equalsIgnoreCase("Get Records")){

                    try {
                        setGetPatientRecordActivityUI();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(activitySelected.equalsIgnoreCase("Add Records")){
                    try {
                        setAddRecordsUi();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (activitySelected.equalsIgnoreCase("Practitioner")){
                    try{
                        setMngtPractitionerAdd();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                } else if(activitySelected.equalsIgnoreCase("Health Unit")) {
                    try{
                        setMngtHealthUnitAdd();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void setAddRecordsUi() throws IOException {
        URL addPatientRecordURL = this.addPatientRecordPage.getURL();
        FXMLLoader fxmlLoader = new FXMLLoader(addPatientRecordURL);
        fxmlLoader.setControllerFactory(ac::getBean);
        AnchorPane addPatientRecordAnchorPane = fxmlLoader.load();
        addPatientRecordAnchorPane.setPrefSize(activityAreaAnchorPane.getWidth(), activityAreaAnchorPane.getHeight());
        pageTitle.setText("Add Patient Records");
        clearActivityArea();
        activityAreaAnchorPane.getChildren().add(addPatientRecordAnchorPane);
    }

    private void setMngtPractitionerAdd(){
        try {
            URL mngtPractitionerAddURL = this.mngtPractitionerAdd.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(mngtPractitionerAddURL);
            fxmlLoader.setControllerFactory(ac::getBean);
            AnchorPane mgntPractitionerAnchorPane = fxmlLoader.load();
            mgntPractitionerAnchorPane.setPrefSize(activityAreaAnchorPane.getWidth(), activityAreaAnchorPane.getHeight());
            pageTitle.setText("Management Practitioner");
            clearActivityArea();
            activityAreaAnchorPane.getChildren().add(mgntPractitionerAnchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setMngtHealthUnitAdd(){
        try {
            URL mngtHealthUnitAddURL = this.mngtHealthUnitAdd.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(mngtHealthUnitAddURL);
            fxmlLoader.setControllerFactory(ac::getBean);
            AnchorPane mngtHealthUnitAddAnchorPane = fxmlLoader.load();
            mngtHealthUnitAddAnchorPane.setPrefSize(activityAreaAnchorPane.getWidth(), activityAreaAnchorPane.getHeight());
            pageTitle.setText("Management Health Unit");
            clearActivityArea();
            activityAreaAnchorPane.getChildren().add(mngtHealthUnitAddAnchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setGetPatientRecordActivityUI() throws IOException {
        URL getPatientRecordPageURL = this.getPatientRecordPage.getURL();
        FXMLLoader fxmlLoader = new FXMLLoader(getPatientRecordPageURL);
        fxmlLoader.setControllerFactory(ac::getBean);
        AnchorPane getPatientRecordAnchorPane = fxmlLoader.load();
        pageTitle.setText("Patient Cloud Medical Record Access ");
        getPatientRecordAnchorPane.setPrefSize(activityAreaAnchorPane.getWidth(), activityAreaAnchorPane.getHeight());
        clearActivityArea();
        activityAreaAnchorPane.getChildren().add(getPatientRecordAnchorPane);
    }

    private void setPatientProfileActivityUi() throws IOException {
        Tab profileTab = new Tab("profile");
        URL loginPageURL = this.profilePage.getURL();
        FXMLLoader fxmlLoader = new FXMLLoader(loginPageURL);
        fxmlLoader.setControllerFactory(ac::getBean);
        AnchorPane root = fxmlLoader.load();
        ScrollPane scrollPane = new ScrollPane();
        root.setPrefWidth(activityAreaAnchorPane.getWidth());
        scrollPane.setContent(root);

        profileTab.setContent(scrollPane);
        pageTitle.setText("Patient Profile");

        URL profileHeaderAndTabPaneURL = this.profileHeaderAndTabPane.getURL();
        FXMLLoader headerTabLoader = new FXMLLoader(profileHeaderAndTabPaneURL);
        headerTabLoader.setControllerFactory(ac::getBean);
        AnchorPane headerAndTabPane  = headerTabLoader.load();
        headerAndTabPane.setPrefSize(activityAreaAnchorPane.getWidth(),activityAreaAnchorPane.getHeight());
        JFXTabPane profileTabPane = (JFXTabPane) headerAndTabPane.lookup("#profileTabPane");
        profileTabPane.getTabs().add(profileTab);
        clearActivityArea();
        activityAreaAnchorPane.getChildren().add(headerAndTabPane);
    }

    private void clearActivityArea() {
        try {
            activityAreaAnchorPane.getChildren().remove(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setUserAccountIcon(){
        your_profileButton.setPadding(new Insets(10));
        btnLogOut.setPadding(new Insets(10));
        MaterialDesignIconView logOutIcon = new MaterialDesignIconView(MaterialDesignIcon.LOGOUT);
        logOutIcon.setSize("16");
        btnLogOut.setGraphic(logOutIcon);
        userAccountIcon.setOnMouseClicked(event -> {
            JFXPopup userIconPopup = new JFXPopup();
            if (userIconPopup.isFocused())
                userIconPopup.hide();
            VBox vBox = new VBox(your_profileButton,btnLogOut);
            userIconPopup.setPopupContent(vBox);
            //System.out.println("show");
            userIconPopup.show(actionBar,JFXPopup.PopupVPosition.TOP,JFXPopup.PopupHPosition.RIGHT,event.getX()-20,event.getY()+30);
        });

        btnLogOut.setOnAction(e-> {
            if (btnLogOut.getText().equalsIgnoreCase("Sign Out")) {
                boolean logedOut = authorizeUserService.logout();
                if(logedOut)
                    btnLogOut.setText("Sign In");
            }
        });
    }

}
