package cx.virtlab.netmon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by bernt on 20.05.15.
 */
public class NetMonApplication extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("NetMon.fxml"));
        primaryStage.setTitle("NetMon");
        primaryStage.setScene(new Scene(root,320,200));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
