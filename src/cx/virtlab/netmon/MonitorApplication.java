package cx.virtlab.netmon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by bernt on 21.05.15.
 */
public class MonitorApplication extends Application {

    public MonitorApplication() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MonitorView.fxml"));
        primaryStage.setTitle("NetMon");
        primaryStage.setScene(new Scene(root,320,200));
        //primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css"));//.toExternalForm());
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        //AquaFx.style();
        primaryStage.show();

    }

    public static void main() {
        launch();
    }
}
