package com.cloudHealth.desktopapp.uiControls;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTreeView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

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
    @FXML private SplitPane workSpaceSplitPane;
    @FXML private FontAwesomeIconView userAccountIcon;
    @FXML private AnchorPane actionBar;

    private  JFXPopup userIconPopup = new JFXPopup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Starter Method Calls
        setActivityTreeViewItems();
        setUserAccountIcon();

    }

    private void  setActivityTreeViewItems(){

        TreeItem root = new TreeItem<>();
        FontAwesomeIconView rootIcon =new FontAwesomeIconView(FontAwesomeIcon.NAVICON);
        rootIcon.setSize("30");
        root.setValue(getMainActivityText("Activities"));
        root.setGraphic(rootIcon);
        root.setExpanded(true);

        TreeItem accountRootItem = new TreeItem<>();
        accountRootItem.setValue(getActivityMediumText("Account"));
        FontAwesomeIconView accountRootItemIcon =new FontAwesomeIconView(FontAwesomeIcon.USERS);
        accountRootItemIcon.setSize("25");
        accountRootItem.setGraphic(accountRootItemIcon);

        TreeItem myAccount = new TreeItem<>();
        myAccount.setValue(getActivitySmallText("My Account"));
        FontAwesomeIconView myAccountItemIcon =new FontAwesomeIconView(FontAwesomeIcon.USER);
        myAccountItemIcon.setSize("20");
        myAccount.setGraphic(myAccountItemIcon);
        accountRootItem.setExpanded(true);
        accountRootItem.getChildren().addAll(myAccount);

        TreeItem patientRootItem = new TreeItem<>();
        patientRootItem.setValue(getActivityMediumText("Patient"));
        MaterialDesignIconView patientItemIcon =new MaterialDesignIconView(MaterialDesignIcon.STETHOSCOPE);
        patientItemIcon.setSize("25");
        patientRootItem.setGraphic(patientItemIcon);

        TreeItem profile = new TreeItem<>();
        profile.setValue(getActivitySmallText("Profile"));
        MaterialDesignIconView profileItemIcon =new MaterialDesignIconView(MaterialDesignIcon.TAG_FACES);
        profileItemIcon.setSize("20");
        profile.setGraphic(profileItemIcon);


        TreeItem addRecord = new TreeItem<>();
        addRecord.setValue(getActivitySmallText("Add Records"));
        FontAwesomeIconView addRecordItemIcon =new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);
        addRecordItemIcon.setSize("20");
        addRecord.setGraphic(addRecordItemIcon);

        TreeItem getRecord = new TreeItem<>();
        getRecord.setValue(getActivitySmallText("Get Records"));
        MaterialDesignIconView getRecordItemIcon =new MaterialDesignIconView(MaterialDesignIcon.FILE_FIND);
        getRecordItemIcon.setSize("20");
        getRecord.setGraphic(getRecordItemIcon);

        TreeItem client = new TreeItem<>();
        client.setValue(getActivitySmallText("Clients"));
        MaterialDesignIconView clientItemIcon =new MaterialDesignIconView(MaterialDesignIcon.ACCOUNT_CIRCLE);
        clientItemIcon.setSize("20");
        client.setGraphic(clientItemIcon);
        patientRootItem.setExpanded(true);
        patientRootItem.getChildren().addAll(profile,addRecord, getRecord, client);

        TreeItem managementRootItem = new TreeItem<>();
        managementRootItem.setValue(getActivityMediumText("Management"));
        FontAwesomeIconView managementItemIcon =new FontAwesomeIconView(FontAwesomeIcon.BRIEFCASE);
        managementItemIcon.setSize("25");
        managementRootItem.setGraphic(managementItemIcon);

        TreeItem practitioner = new TreeItem<>();
        practitioner.setValue(getActivitySmallText("Practitioner"));
        FontAwesomeIconView practitionerItemIcon =new FontAwesomeIconView(FontAwesomeIcon.USER_MD);
        practitionerItemIcon.setSize("20");
        practitioner.setGraphic(practitionerItemIcon);

        TreeItem healthUnit = new TreeItem<>();
        healthUnit.setValue(getActivitySmallText("Health Unit"));
        MaterialDesignIconView healthUnitItemIcon =new MaterialDesignIconView(MaterialDesignIcon.AMBULANCE);
        healthUnitItemIcon.setSize("20");
        healthUnit.setGraphic(healthUnitItemIcon);
        managementRootItem.setExpanded(true);
        managementRootItem.getChildren().addAll(practitioner,healthUnit);

        root.getChildren().addAll(accountRootItem,patientRootItem,managementRootItem);
        activityTreeView.setRoot(root);
    }

    private void setUserAccountIcon(){
        userAccountIcon.setOnMouseMoved(event -> {
            JFXButton your_profileButton = new JFXButton("Your Profile");
            JFXButton btnLogOut = new JFXButton("Log Out");
            your_profileButton.setPadding(new Insets(10));
            btnLogOut.setPadding(new Insets(10));
            MaterialDesignIconView logOutIcon = new MaterialDesignIconView(MaterialDesignIcon.LOGOUT);
            logOutIcon.setSize("16");
            btnLogOut.setGraphic(logOutIcon);

            VBox vBox = new VBox(your_profileButton,btnLogOut);
            userIconPopup.setPopupContent(vBox);
            userIconPopup.show(actionBar,JFXPopup.PopupVPosition.TOP,JFXPopup.PopupHPosition.RIGHT,event.getY(),event.getX()+20);
        });
    }


    private Text getMainActivityText(String displayText){
        Text text = new Text(displayText);
        text.setId("activityText");
        return text;
    }
    private Text getActivityMediumText(String displayText){
        Text text = new Text(displayText);
        text.setId("activityMediumText");
        return text;
    }

    private Text getActivitySmallText(String displayText){
        Text text = new Text(displayText);
        text.setId("activitySmallText");
        return text;
    }
}
