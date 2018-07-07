package kernel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kernel.models.User;
import kernel.views.LoginViewController;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private User userNow;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("报刊订阅系统");
        showLoginView();
    }

    public void showLoginView() {
        try {
            // 载入登录页面
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/LoginView.fxml"));
            Parent root = loader.load();
            LoginViewController loginViewController = loader.getController();
            primaryStage.setScene(new Scene(root, 1152, 640));
            primaryStage.show();
            
            // 传递主函数
            System.out.println(loginViewController);
            loginViewController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserNow(User userNow) {
        this.userNow = userNow;
    }

    public User getUserNow() {
        return userNow;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
