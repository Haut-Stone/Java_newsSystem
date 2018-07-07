package kernel.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import kernel.Main;

import java.io.File;
import java.util.Optional;

public class DialogUtils {
    public static void tips(Stage stage, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        ImageView menhera = new ImageView(Main.class.getResource("statics/images/systemUse/tips.png").toString());
        menhera.setFitHeight(100);
        menhera.setPreserveRatio(true);
        alert.setGraphic(menhera);
        alert.setHeaderText("您可能有一些信息填写有误");
        alert.setContentText(message);
        alert.initOwner(stage);
        alert.show();
    }

    public static void good(Stage stage, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("祝贺");
        ImageView menhera = new ImageView(Main.class.getResource("statics/images/systemUse/good.png").toString());
        menhera.setFitHeight(100);
        menhera.setPreserveRatio(true);
        alert.setGraphic(menhera);
        alert.setHeaderText("您的操作如期运行了");
        alert.setContentText(message);
        alert.initOwner(stage);
        alert.show();
    }

    public static boolean confirm(Stage stage, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message ,new ButtonType("取消", ButtonBar.ButtonData.NO),
                new ButtonType("确定", ButtonBar.ButtonData.YES));
//        设置窗口的标题
        alert.setTitle("确认");
        ImageView menhera = new ImageView(Main.class.getResource("statics/images/systemUse/confirm.png").toString());
        menhera.setFitHeight(100);
        menhera.setPreserveRatio(true);
        alert.setGraphic(menhera);
        alert.setHeaderText("最后一次机会");
//        设置对话框的 icon 图标，参数是主窗口的 stage
        alert.initOwner(stage);
//        showAndWait() 将在对话框消失以前不会执行之后的代码
        Optional<ButtonType> buttonType = alert.showAndWait();
//        根据点击结果返回
        if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
            return true;
        } else {
            return false;
        }
    }
}
