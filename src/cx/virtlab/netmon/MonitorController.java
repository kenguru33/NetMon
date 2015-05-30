package cx.virtlab.netmon;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by bernt on 21.05.15.
 */
public class MonitorController implements Initializable {

    private ObservableMap<String, ProbeService> probeServiceMap = FXCollections.observableHashMap();

    private TreeItem<String> rootNode = new TreeItem<>();


    @FXML
    private ToggleButton toggleModeButton;

    @FXML
    private TextArea txtArea;

    @FXML
    private TreeView<String> serviceTreeView;

    @FXML
    private TableView<Probe> probeTableView;

    @FXML
    private Button addGroupButton;

    @FXML
    private Button addProbeButton;


    private Image serviceNodeImage = new Image(getClass().getResourceAsStream("resources/probe3.png"));
    private Image probeNodeImage = new Image(getClass().getResourceAsStream("resources/probe.png"));

    public MonitorController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.probeServiceMap.addListener((MapChangeListener<String, ProbeService>) change -> {

            System.out.println("ProbeService added to group.");

            String groupName = change.getKey();

            TreeItem<String> groupTreeItem = getServiceGroupTreeItem(groupName);

            if (groupTreeItem == null) {
                groupTreeItem = this.createProbeServiceGroupTreeItem(groupName);
                rootNode.getChildren().add(groupTreeItem);

            }
            TreeItem<String> item = this.createProbeServiceTreeItem(change.getValueAdded().getName());
            groupTreeItem.getChildren().add(item);
        });

        Probe probe = new PingProbe("vg.no", 1000);

        ProbeService probeService = new ProbeService(probe, 1000);
        probeService.setName("probe-04");

        this.probeServiceMap.put("Gruppe01", probeService);

        this.serviceTreeView.setRoot(this.rootNode);
        this.rootNode.setExpanded(true);
        this.serviceTreeView.showRootProperty().set(false);
    }

    @FXML
    private void handleAddProbeAction(ActionEvent event) {
        TreeItem<String> serviceGroupItem;
        TreeItem item = this.serviceTreeView.getSelectionModel().getSelectedItem();
        if (item.isLeaf()) {
            serviceGroupItem = item.getParent();
        } else {
            serviceGroupItem = item;
        }

        Probe probe = new PingProbe("vg.no",1000);
        ProbeService probeService = new ProbeService(probe,1000);
        probeService.setName("probe-XXX");
        this.probeServiceMap.put(serviceGroupItem.getValue(), probeService);
    }

    @FXML
    private void handleAddGroupAction(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("New Probe Group");
        dialog.setTitle("Probe Group");
        dialog.setHeaderText("Create New Probe Group");
        dialog.setContentText("Please enter group name:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            // check result.get first
            this.rootNode.getChildren().add(this.createProbeServiceGroupTreeItem(result.get()));
        }

// The Java 8 way to get the response value (with lambda expression).
//        result.ifPresent(name -> System.out.println("Your name: " + name));

    }


    public void addProbeService(ProbeService probeService) {

    }

    public void removeProbeService(ProbeService probeService) {

    }

    public void addProbeServiceGroup(String groupName, ProbeService probeService) {
        this.probeServiceMap.put(groupName, probeService);
    }

    public void removeProbeServiceGroup() {

    }

    public void addProbe(Probe probe) {

    }

    public void remove(Probe probe) {

    }

    private TreeItem<String> createProbeServiceGroupTreeItem(String name) {
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("resources/group.png")));
        //img.setFitWidth(16);
        //img.setFitHeight(16);

        return new TreeItem<String>(name, img) {
            @Override
            public boolean isLeaf() {
                return false;
            }
        };
    }

    private TreeItem<String> createProbeServiceGroupTreeItem(String name, TreeItem<String> parentNode) {
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("resources/group.png")));
        //img.setFitWidth(16);
        //img.setFitHeight(16);

        return new TreeItem<String>(name, img) {
            @Override
            public boolean isLeaf() {
                return false;
            }
        };
    }

    private TreeItem<String> createProbeServiceTreeItem(String name) {
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("resources/probe3.png")));
        img.setFitWidth(16);
        img.setFitHeight(16);
        return new TreeItem<String>(name, img) {
            @Override
            public boolean isLeaf() {
                return true;
            }
        };
    }

    private TreeItem<String> getServiceGroupTreeItem(String name) {
        TreeItem<String> parentNode = rootNode;
        for (TreeItem item : parentNode.getChildren()) {
            if (item.getValue() == name && !item.isLeaf()) {
                return item;
            }
        }
        return null;
    }

    private TreeItem<String> getServiceGroupTreeItem(String name, TreeItem<String> parentNode) {
        for (TreeItem<String> item : parentNode.getChildren()) {
            if (item.getValue() == name && !item.isLeaf()) {
                return item;
            } else {
                getServiceGroupTreeItem(name, item);
            }
        }
        return null;
    }
}
