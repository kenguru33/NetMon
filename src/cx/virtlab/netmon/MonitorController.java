package cx.virtlab.netmon;

import apple.laf.JRSUIUtils;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by bernt on 21.05.15.
 */
public class MonitorController implements Initializable {

    private ProbeServiceStore probeServiceStore;

    @FXML
    private ToggleButton toggleModeButton;

    @FXML
    private TextArea txtArea;

    @FXML
    private TreeView<String> serviceTreeView;

    @FXML
    private TableView<Probe> probeTableView;


    private Image serviceNodeImage = new Image(getClass().getResourceAsStream("resources/serviceGroup.png"));
    private Image probeNodeImage = new Image(getClass().getResourceAsStream("resources/probe.png"));

    public MonitorController() {

        probeServiceStore = ProbeServiceStore.createProbeServiceStore();
        probeServiceStore.load("filename");
        probeServiceStore.getProbeServiceList().stream().forEach((probeService -> {

            probeService.createTask();

            probeService.setOnSucceeded(event -> {
                System.out.println(probeService.getValue().getResponseDescription());

                //txtArea.appendText("\n" + probeService.getValue().getResponseDescription());
                probeService.restart();
            });

            probeService.setOnRunning(event -> {
                System.out.println("Task " + event.getSource().getTitle() + " running!");
            });

            probeService.setOnFailed(event -> {
                System.out.println("bla bla");
            });
            probeService.start();
        }));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // build treeview
        TreeItem<String> rootNode = new TreeItem<>("Probe Services");

        ImageView img = new ImageView(serviceNodeImage);
        img.setFitHeight(16);
        img.setFitWidth(16);

        ImageView imgp = new ImageView(probeNodeImage);
        imgp.setFitHeight(16);
        imgp.setFitWidth(16);

        TreeItem<String> probeServiceNode = new TreeItem<>("ServiceGroup1",img);
        rootNode.getChildren().add(probeServiceNode);
        TreeItem<String> probeNode = new TreeItem<>("Probe01",imgp);
        probeServiceNode.getChildren().add(probeNode);
        TreeItem<String> probeNode2 = new TreeItem<>("Probe02");
        probeServiceNode.getChildren().add(probeNode2);

        ImageView img2 = new ImageView(serviceNodeImage);
        img2.setFitHeight(16);
        img2.setFitWidth(16);
        TreeItem<String> probeServiceNode2 = new TreeItem<>("ServiceGroup2",img2);
        rootNode.getChildren().add(probeServiceNode2);
        TreeItem<String> probeNode11 = new TreeItem<>("Probe01");
        probeServiceNode2.getChildren().add(probeNode11);
        TreeItem<String> probeNode22 = new TreeItem<>("Probe01");
        probeServiceNode2.getChildren().add(probeNode22);

        this.serviceTreeView.setRoot(rootNode);
        this.serviceTreeView.setShowRoot(false);

        //build tableview

        TableColumn<Probe> probeUrl = new TableColumn()






    }

}
