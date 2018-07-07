package kernel.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import kernel.Main;
import kernel.Session;
import kernel.models.Admin;
import kernel.models.CommonUser;
import kernel.utils.DialogUtils;
import kernel.utils.InputChecker;
import kernel.utils.JdbcUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginViewController {
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    @FXML
    private CheckBox isAdmin;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private VBox loginBox;
    @FXML
    private VBox signUpBox;
    @FXML
    private Main mainApp;
    @FXML
    private TextField commonUserUsername;
    @FXML
    private TextField commonUserPassword;
    @FXML
    private TextField commonUserTruename;
    @FXML
    private TextField commonUserSid;
    @FXML
    private TextField commonUserTel;
    @FXML
    private TextField commonUserAddress;
    @FXML
    private Button saveToDataBaseButton;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public Main getMainApp() {
        return mainApp;
    }

    /**
     * 这里我们在点击登录后，因为这里区分了管理员和普通用户， 所以之后的编程会相对简单一些.
     */
    @FXML
    private void handleLogin() throws Exception {
        // 调试用信息，之后可去掉
        System.out.println("登录按钮被点击了");

        //构造查询表单
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = null;
        if (isAdmin.isSelected()) {
            sql = "select * from `admin` where username = ? and password = ?";
        } else {
            sql = "select * from `commonUser` where username = ? and password = ?";
        }
        List<Object> params = new ArrayList<>();
        params.add(usernameField.getText());
        params.add(passwordField.getText());
        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());

        try {
            if (isAdmin.isSelected()) {
                Admin admin = jdbcUtils.findSimpleProResult(sql, params, Admin.class);
                if (admin == null) {
                    throw new NullPointerException("出错了，请您检查用户名或密码是否有误");
                }
                System.out.println(admin);
                mainApp.setUserNow(admin);
                Session.setUsername(admin.getUsername());
                Session.setType("admin");
                Session.setId(admin.getId());
            } else {
                CommonUser commonUser = jdbcUtils.findSimpleProResult(sql, params, CommonUser.class);
                if (commonUser == null) {
                    throw new NullPointerException("出错了，请您检查用户名或密码是否有误");
                }
                System.out.println(commonUser);
                mainApp.setUserNow(commonUser);
                Session.setUsername(commonUser.getUsername());
                Session.setType("commonUser");
                Session.setId(commonUser.getId());
            }

            //认证完成，装载对应页面
            FXMLLoader loader = new FXMLLoader();
            if (isAdmin.isSelected()) {
                loader.setLocation(Main.class.getResource("views/AdminView.fxml"));
            } else {
                loader.setLocation(Main.class.getResource("views/CommonUserView.fxml"));
            }
            /*
             *==================================================
             *                 !!important!!
             *     你必须先调用loader.load才能找到controller
             *==================================================
             */
            Parent newView = loader.load();
            if (isAdmin.isSelected()) {
                AdminVIewController adminVIewController = loader.getController();
                System.out.println(adminVIewController);
                System.out.println(mainApp);
                adminVIewController.setMainApp(mainApp);
            } else {
                CommonUserViewController commonUserViewController = loader.getController();
                System.out.println("mainApp: " + mainApp);
                commonUserViewController.setMainApp(mainApp);
            }
            mainApp.getPrimaryStage().setScene(new Scene(newView, 1152, 736));

        } catch (Exception e) {
            DialogUtils.tips(mainApp.getPrimaryStage(), e.getMessage());
            System.out.println("出现了一些问题，请查看提示信息");
            usernameField.setText("");
            passwordField.setText("");
            usernameField.requestFocus();
        }
    }

    @FXML
    private void handleSignUp() {
        //调试提示
        System.out.println("注册按钮被点击了");
        // 切换两个页面的显示
        loginBox.setVisible(false);
        signUpBox.setVisible(true);
    }

    @FXML
    private void handleNewUser() {
        //条件检查后，填入数据库，这里的检查包括拼写检查和判重检查
        String errorMessage = InputChecker.commonUserSignUpCheck(commonUserUsername.getText(),
                                                                 commonUserPassword.getText(),
                                                                 commonUserTruename.getText(),
                                                                 commonUserSid.getText(),
                                                                 commonUserTel.getText(),
                                                                 commonUserAddress.getText());
        if (errorMessage == null) {
            // 添加至数据库
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "insert into `commonUser`(username, password, trueName, sid, tel, address) " +
                    "values(?, ?, ?, ?, ?, ?)";
            List<Object> params = new ArrayList<>();
            params.add(commonUserUsername.getText());
            params.add(commonUserPassword.getText());
            params.add(commonUserTruename.getText());
            params.add(commonUserSid.getText());
            params.add(commonUserTel.getText());
            params.add(commonUserAddress.getText());
            try {
                //添加用户
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "添加用户成功");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 认证全局用户，跳转页面
            CommonUser newUser = new CommonUser(commonUserUsername.getText(), commonUserPassword.getText());
            mainApp.setUserNow(newUser);
            sql = "select * from `commonUser` where username = ? and password = ?";
            params.clear();
            params.add(commonUserUsername.getText());
            params.add(commonUserPassword.getText());
            try {
                CommonUser now = jdbcUtils.findSimpleProResult(sql, params, CommonUser.class);
                Session.setId(now.getId());
                Session.setUsername(now.getUsername());
                Session.setType("commonUser");
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("views/CommonUserView.fxml"));
                Parent commonUserView = loader.load();
                CommonUserViewController commonUserViewController = loader.getController();
                mainApp.getPrimaryStage().setScene(new Scene(commonUserView, 1152, 736));
                commonUserViewController.setMainApp(mainApp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    @FXML
    private void handleBack() {
        signUpBox.setVisible(false);
        loginBox.setVisible(true);
    }
}
