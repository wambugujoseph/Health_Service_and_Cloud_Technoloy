package com.cloudHealth.desktopapp.uiControls.uiHelper;

import com.jfoenix.controls.JFXTreeView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.scene.control.TreeItem;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Monday
 * Date: 1/20/2020
 * Time: 10:35 PM
 * Project: desktop-app
 */

@Component
public class HomeControllerHelper {

    private JFXTreeView<?> activityTreeView;

    public TreeItem getActivityTreeViewItems(){

        TreeItem root = getTreeItem(getMainActivityText("Activities"), new FontAwesomeIconView(FontAwesomeIcon.NAVICON),
                "30", true);
        TreeItem accountRootItem = getTreeItem(getActivityMediumText("Account"), new FontAwesomeIconView(FontAwesomeIcon.USERS),
                "25", true);
        TreeItem myAccount =  getTreeItem(getActivitySmallText("My Account"), new FontAwesomeIconView(FontAwesomeIcon.USER),
                "20", true);
        TreeItem client = getTreeItem(getActivitySmallText("Clients"), new MaterialDesignIconView(MaterialDesignIcon.ACCOUNT_CIRCLE),
                "20", true);
        accountRootItem.getChildren().addAll(myAccount, client);

        TreeItem patientRootItem = getTreeItem(getActivityMediumText("Patient"),new MaterialDesignIconView(MaterialDesignIcon.STETHOSCOPE),
                "25", true);
        TreeItem profile = getTreeItem(getActivitySmallText("Profile"), new MaterialDesignIconView(MaterialDesignIcon.TAG_FACES),
                "20", true);
        TreeItem addRecord = getTreeItem(getActivitySmallText("Add Records"), new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT),
                "20", true);
        TreeItem getRecord = getTreeItem(getActivitySmallText("Get Records"), new MaterialDesignIconView(MaterialDesignIcon.FILE_FIND),
                "20", true);
        patientRootItem.getChildren().addAll(profile,addRecord, getRecord);

        TreeItem managementRootItem = getTreeItem(getActivityMediumText("Management"), new FontAwesomeIconView(FontAwesomeIcon.BRIEFCASE),
                "25",true);
        TreeItem practitioner = getTreeItem(getActivitySmallText("Practitioner"), new FontAwesomeIconView(FontAwesomeIcon.USER_MD),
                "20", true);
        TreeItem healthUnit =getTreeItem(getActivitySmallText("Health Unit"), new MaterialDesignIconView(MaterialDesignIcon.AMBULANCE),
                "20",true);
        managementRootItem.getChildren().addAll(healthUnit,practitioner);
        root.getChildren().addAll(accountRootItem,patientRootItem,managementRootItem);

        return root;
    }

    private TreeItem getTreeItem(Text title, FontAwesomeIconView fontAwesomeIconView, String fontSize, boolean isExpanded){
        TreeItem  treeItem = new TreeItem();
        treeItem.setValue(title);
        treeItem.setExpanded(isExpanded);
        fontAwesomeIconView.setSize(fontSize);
        treeItem.setGraphic(fontAwesomeIconView);
        return treeItem;
    }

    private TreeItem getTreeItem(Text title, MaterialDesignIconView materialDesignIconView, String fontSize,Boolean isExpanded){
        TreeItem  treeItem = new TreeItem();
        treeItem.setValue(title);
        treeItem.setExpanded(isExpanded);
        materialDesignIconView.setSize(fontSize);
        treeItem.setGraphic(materialDesignIconView);
        return treeItem;
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
