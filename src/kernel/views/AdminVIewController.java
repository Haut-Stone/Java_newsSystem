package kernel.views;

import com.sun.tools.corba.se.idl.constExpr.Or;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import kernel.Main;
import kernel.Session;
import kernel.models.*;
import kernel.utils.*;

import javax.jws.Oneway;
import javax.swing.text.TabExpander;
import java.io.*;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminVIewController {
    @FXML
    private Main mainApp;
    @FXML
    private Label toStatisticButton;
    @FXML
    private Label toMagazineButton;
    @FXML
    private Label toCommonUserButton;
    @FXML
    private Label toAdminButton;
    @FXML
    private Label logOutButton;
    @FXML
    private BorderPane adminPane;
    @FXML
    private BorderPane commonUserPane;
    @FXML
    private BorderPane magazinPane;
    @FXML
    private BorderPane statisticPane;
    @FXML
    private BorderPane searchPane;
    @FXML
    private BorderPane orderAndMagazineClassPane;
    @FXML
    private TableView<Admin> adminTableView;
    @FXML
    private TableColumn<Admin, Integer> adminIdColumn;
    @FXML
    private TableColumn<Admin, String> adminNameColumn;
    @FXML
    private TableColumn<Admin, String> adminPasswordColumn;
    @FXML
    private Label adminIdLabel;
    @FXML
    private Label adminNameLabel;
    @FXML
    private Label adminPasswordLabel;
    @FXML
    private TextField adminIdField;
    @FXML
    private TextField adminNameField;
    @FXML
    private TextField adminPasswordField;
    @FXML
    private TableView<CommonUser> commonUserTableView;
    @FXML
    private TableColumn<CommonUser, Integer> commonUserIdColumn;
    @FXML
    private TableColumn<CommonUser, String> commonUserNameColumn;
    @FXML
    private TextField commonUserIdField;
    @FXML
    private TextField commonUserNameField;
    @FXML
    private TextField commonUserpassField;
    @FXML
    private TextField commonUserTrueNameField;
    @FXML
    private TextField commonUserSidField;
    @FXML
    private TextField commonUserTelField;
    @FXML
    private TextField commonUserAddrField;
    @FXML
    private TableView<Magazine> magazineTableView;
    @FXML
    private TableColumn<Magazine, Integer> magazineIdColumn;
    @FXML
    private TableColumn<Magazine, String> magazineNameColumn;
    @FXML
    private TextField magazineIdField;
    @FXML
    private TextField magazineNameField;
    @FXML
    private TextField magazineOfficeField;
    @FXML
    private TextField magazineCycleField;
    @FXML
    private TextField magazineMonthPriceField;
    @FXML
    private ChoiceBox<String> magazineClassNameBox;
    @FXML
    private TextArea magazineIntroArea;
    @FXML
    private ImageView magazineCover;
    @FXML
    private TableView<Order> seTableView;
    @FXML
    private TableColumn<Order, Integer> seOrderIdColumn;
    @FXML
    private TableColumn<Order, String> seMagaNameColumn;
    @FXML
    private TableColumn<Order, String> seUserNameColumn;
    @FXML
    private TableColumn<Order, Integer> seCopiesNumColumn;
    @FXML
    private TableColumn<Order, Integer> seMonthColumn;
    @FXML
    private TableColumn<Order, Integer> seTotalPriceColumn;
    @FXML
    private ComboBox<String> seMagazineNameBox;
    @FXML
    private ComboBox<String> seUserNameBox;
    @FXML
    private ComboBox<String> seCopiesNumBox;
    @FXML
    private ComboBox<String> seMonthBox;
    @FXML
    private ComboBox<String> seMagazineClassBox;
    @FXML
    private ComboBox<String> sePriceIntervalBox;
    @FXML
    private Label mostRichUser;
    @FXML
    private Label mostRichUsersTotalPrice;
    @FXML
    private Label mostOrderNumUser;
    @FXML
    private Label mostOrderNumUsersOrderNum;
    @FXML
    private Label bestMagazine;
    @FXML
    private Label bestMagazineCopiesNum;
    @FXML
    private Label bestClass;
    @FXML
    private Label bestClassOrderNum;
    @FXML
    private ComboBox<String> anUserNameBox;
    @FXML
    private ComboBox<String> anMagazineNameBox;
    @FXML
    private PieChart statisticPriceChart;
    @FXML
    private Label anUserNameLabel;
    @FXML
    private Label anMagazineNameLabel;
    @FXML
    CategoryAxis xAxis = new CategoryAxis();
    @FXML
    private BarChart<String, Integer> magazineBarChart;
    @FXML
    private Rectangle imageBorder;
    @FXML
    private Label userNameLabel;
    // 这里实在是不知道该起什么名字好了
    @FXML
    private TableView<Order> fooOrderTableView;
    @FXML
    private TableColumn<Order, Integer> fooOrderIdColumn;
    @FXML
    private TableColumn<Order, String> fooUserNameColumn;
    @FXML
    private TableColumn<Order, String>  fooMagazineNameColumn;
    @FXML
    private TableColumn<Order, Integer> fooTotalPriceColumn;
    @FXML
    private TableView<MagazineClass> fooMagazineClassTableView;
    @FXML
    private TableColumn<MagazineClass, Integer> fooMagazineClassIdColumn;
    @FXML
    private TableColumn<MagazineClass, String> fooMagazineClassNameColumn;
    @FXML
    private TextField fooMagazineClassIdField;
    @FXML
    private TextField fooMagazineClassNameField;
    @FXML
    private TextField fooOrderIdField;
    @FXML
    private ComboBox<String> fooOrderUsernameBox;
    @FXML
    private ComboBox<String> fooOrderMagazineNameBox;
    @FXML
    private ChoiceBox<String> fooMonthBox;
    @FXML
    private ChoiceBox<String> fooCopiesNumBox;
    @FXML
    private TextField fooTotalPriceField;
    

    private ObservableList<Admin> adminData = FXCollections.observableArrayList();
    private ObservableList<Magazine> magazineData = FXCollections.observableArrayList();
    private ObservableList<CommonUser> commonUsersData = FXCollections.observableArrayList();
    private ObservableList<Order> seOrderData = FXCollections.observableArrayList();
    private ObservableList<Order> fooOrderData = FXCollections.observableArrayList();
    private ObservableList<MagazineClass> fooMagazineClassData = FXCollections.observableArrayList();

    // 各种选项盒子的数据
    private ObservableList<String> choiceItems = FXCollections.observableArrayList();
    private ObservableList<String> seMagazineNames = FXCollections.observableArrayList();
    private ObservableList<String> seMagazineClasses = FXCollections.observableArrayList();
    private ObservableList<String> seUserNames = FXCollections.observableArrayList();
    private ObservableList<String> anUserNames = FXCollections.observableArrayList();
    private ObservableList<String> anMagazineNames = FXCollections.observableArrayList();
    private ObservableList<String> fooOrderUsernames = FXCollections.observableArrayList();
    private ObservableList<String> fooOrderMagazineNames = FXCollections.observableArrayList();

    //杂志封面临时文件
    private File imageFile;

    public AdminVIewController() {
    }

    public Main getMainApp() {
        return mainApp;
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        adminIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        adminNameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        adminPasswordColumn.setCellValueFactory(cellDate -> cellDate.getValue().passwordProperty());
        adminTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateAdminDetail(newValue)
        );
        commonUserIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        commonUserNameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        commonUserTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateCommonDetail(newValue)
        );

        magazineIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        magazineNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        magazineTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateMagazineDetail(newValue)
        );

        seOrderIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        seMagaNameColumn.setCellValueFactory(cellData -> cellData.getValue().magazineNameProperty());
        seUserNameColumn.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
        seCopiesNumColumn.setCellValueFactory(cellData -> cellData.getValue().copiesNumProperty().asObject());
        seMonthColumn.setCellValueFactory(cellData -> cellData.getValue().monthProperty().asObject());
        seTotalPriceColumn.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty().asObject());
        anUserNameBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateAnUserNameLabel()
        );
        anMagazineNameBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateAnMagazineLabel()
        );

        fooOrderIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        fooUserNameColumn.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
        fooMagazineNameColumn.setCellValueFactory(cellData -> cellData.getValue().magazineNameProperty());
        fooTotalPriceColumn.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty().asObject());
        fooOrderTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateOrderDetail(newValue)
        );

        fooMagazineClassIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        fooMagazineClassNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        fooMagazineClassTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateMagazineClassDetail(newValue)
        );

        initAdminTable();
        initCommonUserTable();
        initMagazineTable();
        initSearchTable();
        initStatisticView();
        initUserNameLabel();
        initMagazineClassTableAndOrderTable();
    }

    public void initUserNameLabel() {
        if (Session.getType().equals("commonUser")) {
            userNameLabel.setText(Session.getUsername());
        } else if (Session.getType().equals("admin")) {
            userNameLabel.setText(Session.getUsername());
        }
    }

    public void initAdminTable() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `admin`";
        List<Object> params = new ArrayList<>();
        try {
            List<Admin> admins = jdbcUtils.findMoreProResult(sql, params, Admin.class);
            adminData.clear();
            adminData.addAll(admins);
            adminTableView.setItems(adminData);
            adminTableView.getSelectionModel().selectFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化杂志分类和订单列表
     */
    public void initMagazineClassTableAndOrderTable() {

        initFooUsernameBox();
        initFooMagazineNameBox();
        initFooMonthBox();
        initFooCopiesNumBox();

        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select  a.id as id, a.uid, b.username as userName, a.mid, c.`name` as magazineName, a.`month`, a.copiesNum, a.totalPrice  from `order` a, commonUser b, magazine c where a.uid = b.id and a.mid = c.id;";
        List<Object> params = new ArrayList<>();
        try {
            List<Order> orders = jdbcUtils.findMoreProResult(sql, params, Order.class);
            fooOrderData.clear();
            fooOrderData.addAll(orders);
            fooOrderTableView.setItems(fooOrderData);
            fooOrderTableView.getSelectionModel().selectFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sql = "select id, className as name from `mClass`";
        try {
            List<MagazineClass> magazineClasses = jdbcUtils.findMoreProResult(sql, params, MagazineClass.class);
            fooMagazineClassData.clear();
            fooMagazineClassData.addAll(magazineClasses);
            fooMagazineClassTableView.setItems(fooMagazineClassData);
            fooMagazineClassTableView.getSelectionModel().selectFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initStatisticView() {
        initMostRichMan();
        initMostPopularClass();
        initMostOrderUser();
        initMostPopularMagazine();
        initAnMagazineNameBox();
        initAnUserNameBox();
    }

    private void initCommonUserTable() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `commonUser`;";
        List<Object> params = new ArrayList<>();
        try {
            List<CommonUser> commonUsers = jdbcUtils.findMoreProResult(sql, params, CommonUser.class);
            commonUsersData.clear();
            commonUsersData.addAll(commonUsers);
            commonUserTableView.setItems(commonUsersData);
            commonUserTableView.getSelectionModel().selectFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initMagazineTable() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select a.id, a.coverPath, a.`name`, a.office, a.cycle, a.price, a.intro, b.className from `magazine` a, `mClass` b where a.classNumber = b.id;";
        List<Object> params = new ArrayList<>();
        try {
            List<Magazine> magazines = jdbcUtils.findMoreProResult(sql, params, Magazine.class);
            magazineData.clear();
            magazineData.addAll(magazines);
            magazineTableView.setItems(magazineData);
            magazineTableView.getSelectionModel().selectFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSearchTable() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select  a.id as id, a.uid, b.username as userName, a.mid, c.`name` as magazineName, a.`month`, a.copiesNum, a.totalPrice  from `order` a, commonUser b, magazine c where a.uid = b.id and a.mid = c.id;";
        List<Object> params = new ArrayList<>();
        try {
            List<Order> orders = jdbcUtils.findMoreProResult(sql, params, Order.class);
            seOrderData.clear();
            seOrderData.addAll(orders);
            seTableView.setItems(seOrderData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initSeMagazineNameBox();
        initSeUserNameBox();
        initSeCopiesNumBox();
        initSeMonthBox();
        initSeMagazineClassBox();
        initSePriceIntervalBox();

    }

    /**
     * 获得全部的杂志名信息到组合选择框中,以备日后正则筛选
     */
    private void initSeMagazineNameBox() {
        initBox("select name from `magazine`", seMagazineNameBox, seMagazineNames);
    }

    private void initSeUserNameBox() {
        initBox("select username as name from `commonUser`", seUserNameBox, seUserNames);
    }
    private void initFooUsernameBox() {
        initBox("select username as name from `commonUser`", fooOrderUsernameBox, fooOrderUsernames);
    }
    private void initFooMagazineNameBox() {
        initBox("select name from `magazine`", fooOrderMagazineNameBox, fooOrderMagazineNames);
    }
    private void initFooCopiesNumBox() {
        ObservableList<String> temp = FXCollections.observableArrayList();
        for (int i = 1; i <= 12; i++) {
            temp.add(Integer.toString(i));
        }
        fooCopiesNumBox.setItems(temp);
    }
    private void initSeCopiesNumBox() {
        ObservableList<String> temp = FXCollections.observableArrayList();
        temp.add("");
        for (int i = 1; i <= 12; i++) {
            temp.add(Integer.toString(i));
        }
        seCopiesNumBox.setItems(temp);
    }

    private void initFooMonthBox() {
        ObservableList<String> temp = FXCollections.observableArrayList();
        for (int i = 1; i <= 12; i++) {
            temp.add(Integer.toString(i));
        }
        fooMonthBox.setItems(temp);
    }
    private void initSeMonthBox() {
        ObservableList<String> temp = FXCollections.observableArrayList();
        temp.add("");
        for (int i = 1; i <= 12; i++) {
            temp.add(Integer.toString(i));
        }
        seMonthBox.setItems(temp);
    }
    private void initSeMagazineClassBox() {
        initBox("select className as name from `mClass`", seMagazineClassBox, seMagazineClasses);
    }

    private void initSePriceIntervalBox() {
        ObservableList<String> temp = FXCollections.observableArrayList(
                "",
                "0 --> 50",
                "51 -->  100",
                "100 --> 200",
                "200 --> 500",
                "500 --> 1000",
                "1000 --> 5000",
                "> 5000"
        );
        sePriceIntervalBox.setItems(temp);
    }

    private void initBox(String sql, ComboBox<String> box, ObservableList<String> items) {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        List<Object> params = new ArrayList<>();
        try {
            items.clear();
            List<Map<String, Object>> names = jdbcUtils.findModeResult(sql, params);
            for (Map<String, Object> name : names) {
                items.add(name.get("name").toString());
            }
            box.setItems(items);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAdminDetail(Admin admin) {
        if (admin != null) {
            adminIdField.setText(Integer.toString(admin.getId()));
            adminNameField.setText(admin.getUsername());
            adminPasswordField.setText(admin.getPassword());
        }
    }

    private void updateCommonDetail(CommonUser solo) {
        if (solo != null) {
            commonUserIdField.setText(Integer.toString(solo.getId()));
            commonUserNameField.setText(solo.getUsername());
            commonUserpassField.setText(solo.getPassword());
            commonUserTrueNameField.setText(solo.getTrueName());
            commonUserSidField.setText(solo.getSid());
            commonUserTelField.setText(solo.getTel());
            commonUserAddrField.setText(solo.getAddress());
        }
    }

    private void updateOrderDetail(Order solo) {
        if (solo != null) {
            fooOrderIdField.setText(Integer.toString(solo.getId()));
            fooOrderUsernameBox.getEditor().setText(solo.getUserName());
            fooOrderMagazineNameBox.getEditor().setText(solo.getMagazineName());
            fooMonthBox.setValue(Integer.toString(solo.getMonth()));
            fooCopiesNumBox.setValue(Integer.toString(solo.getCopiesNum()));
            fooTotalPriceField.setText(Integer.toString(solo.getTotalPrice()));
        }
    }

    private void updateMagazineClassDetail(MagazineClass solo) {
        if (solo != null) {
            fooMagazineClassIdField.setText(Integer.toString(solo.getId()));
            fooMagazineClassNameField.setText(solo.getName());
        }
    }

    private void updateMagazineDetail(Magazine solo) {
        if (solo != null) {
            magazineIdField.setText(Integer.toString(solo.getId()));
            magazineNameField.setText(solo.getName());
            magazineOfficeField.setText(solo.getOffice());
            magazineCycleField.setText(solo.getCycle());
            magazineMonthPriceField.setText(solo.getPrice());

            //处理ChoiceBox的相关问题
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select className from `mClass`;";
            List<Object> params = new ArrayList<>();

            try {
                List<Map<String, Object>> classNames = jdbcUtils.findModeResult(sql, params);
                System.out.println(classNames);
                choiceItems.clear();
                for (Map<String, Object> className : classNames) {
                    choiceItems.add(className.get("className").toString());
                }
                magazineClassNameBox.setItems(choiceItems);
                magazineClassNameBox.setValue(solo.getClassName());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            magazineIntroArea.setText(solo.getIntro());
            File newFile = new File("src/kernel/statics/images/" + solo.getCoverPath());
            /*
             *==================================================
             *                 !!important!!
             *     不明白这里的图片为什么不能读相对路径，而必须这样写
             *     如果写相对路径的话，新添加的图片在下次重启前，无法
             *     显示
             *     参考：https://blog.csdn.net/major_out/article/details/66971188?utm_source=debugrun&utm_medium=referral
             *==================================================
             */
            magazineCover.setImage(new Image(newFile.getAbsoluteFile().toURI().toString()));
            UIAdjistUtils.adjustImageBorder(magazineCover, imageBorder);
            imageFile = new File("kernel/statics/images/" + solo.getCoverPath());
            System.out.println(imageFile.getAbsolutePath());
        }
    }

    /**
     * 在用户更改输入文字和选择可选项是都会触发action
     * 从而语允许可以实时筛选和用户输入具有关联性的信息
     */
    @FXML
    private void updateSeMagazineNameBox() {
        updateBox(seMagazineNameBox, seMagazineNames);
    }

    @FXML
    private void updateSeUserNameBox() {
        updateBox(seUserNameBox, seUserNames);
    }

    @FXML
    private void updateSeMagezineClassBox() {
        updateBox(seMagazineClassBox, seMagazineClasses);
    }

    private void updateBox(ComboBox<String> box, ObservableList<String> items) {
        /*
         *==================================================
         *                 !!important!!
         *     注意这里Editor和value的文本不是实时传递的
         *==================================================
         */
        String pattern = box.getEditor().getText();
        ObservableList<String> temp = FXCollections.observableArrayList();
        System.out.println(items);
        for (String item: items) {
            try {
                // 这里要统一编码格式，才能支持中文
                if (new String(item.getBytes(),"utf-8").contains(new String(pattern.getBytes(),"utf-8"))) {
                    System.out.println(item + "被添加入集合中");
                    temp.add(item);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        box.setItems(temp);
        // TODO: 2018/6/28 这里还有一些在选择选项后，值自动清空的bug
        box.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/LoginView.fxml"));
            Parent loginView = loader.load();
            LoginViewController loginViewController = loader.getController();
            mainApp.getPrimaryStage().setScene(new Scene(loginView, 1152, 640));
            mainApp.getPrimaryStage().show();
            loginViewController.setMainApp(mainApp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleToStatistic() {
        magazinPane.setVisible(false);
        adminPane.setVisible(false);
        commonUserPane.setVisible(false);
        searchPane.setVisible(false);
        orderAndMagazineClassPane.setVisible(false);
        initStatisticView();
        statisticPane.setVisible(true);
    }

    @FXML
    private void handleToCommonUser() {
        statisticPane.setVisible(false);
        magazinPane.setVisible(false);
        adminPane.setVisible(false);
        searchPane.setVisible(false);
        orderAndMagazineClassPane.setVisible(false);
        initCommonUserTable();
        commonUserPane.setVisible(true);
    }

    @FXML
    private void handleToAdmin() {
        statisticPane.setVisible(false);
        magazinPane.setVisible(false);
        commonUserPane.setVisible(false);
        orderAndMagazineClassPane.setVisible(false);
        searchPane.setVisible(false);
        initAdminTable();
        adminPane.setVisible(true);
    }

    @FXML
    private void handleTomagazine() {
        statisticPane.setVisible(false);
        commonUserPane.setVisible(false);
        orderAndMagazineClassPane.setVisible(false);
        adminPane.setVisible(false);
        searchPane.setVisible(false);
        initMagazineTable();
        magazinPane.setVisible(true);
    }

    @FXML
    private void handleToSearch() {
        statisticPane.setVisible(false);
        commonUserPane.setVisible(false);
        orderAndMagazineClassPane.setVisible(false);
        adminPane.setVisible(false);
        magazinPane.setVisible(false);
        initSearchTable();
        searchPane.setVisible(true);
    }
    @FXML
    private void handleToOrderAndMagazineClass() {
        statisticPane.setVisible(false);
        commonUserPane.setVisible(false);
        searchPane.setVisible(false);
        adminPane.setVisible(false);
        magazinPane.setVisible(false);
        initMagazineClassTableAndOrderTable();
        orderAndMagazineClassPane.setVisible(true);
    }

    @FXML
    private void handleSubmitAdmin() {
        Admin oldInfo = adminTableView.getSelectionModel().getSelectedItem();
        String id = adminIdField.getText();
        if (id == null || id.length() == 0) {
            DialogUtils.tips(mainApp.getPrimaryStage(), "id为空");
            return;
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                DialogUtils.tips(mainApp.getPrimaryStage(), "id应只含有数字");
                return;
            }
        }
        Admin newInfo = new Admin(Integer.parseInt(adminIdField.getText()), adminNameField.getText(), adminPasswordField.getText());
        String errorMessage = InputChecker.adminInfoUpdateCheck(oldInfo, newInfo);
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "update `admin` set username = ?, password = ? where id = ?";
            List<Object> params = new ArrayList<>();
            params.add(newInfo.getUsername());
            params.add(newInfo.getPassword());
            params.add(newInfo.getId());
            try {
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "更新成功");
                // 更新一下现在在线的用户
                if (Session.getId() == newInfo.getId()) {
                    Session.setUsername(newInfo.getUsername());
                    initUserNameLabel();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            initAdminTable();
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    @FXML
    private void handleNewAdmin() {
        String errorMessage = InputChecker.adminSignUpCheck(adminIdField.getText(), adminNameField.getText(), adminPasswordField.getText());
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "insert into `admin`(id, username, password) values(?, ?, ?)";
            List<Object> params = new ArrayList<>();
            params.add(Integer.parseInt(adminIdField.getText()));
            params.add(adminNameField.getText());
            params.add(adminPasswordField.getText());

            try {
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "添加成功");
                initAdminTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    /**
     * 删除所选项，将该管理员删除
     */
    @FXML
    private void handleDeleteAdmin() {
        int index = adminTableView.getSelectionModel().getFocusedIndex();
        Admin admin = adminTableView.getSelectionModel().getSelectedItem();
        if (admin != null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "delete from `admin` where id = ?";
            List<Object> params = new ArrayList<>();
            params.add(admin.getId());

            try {
                if (DialogUtils.confirm(mainApp.getPrimaryStage(), "您真的要删掉该管理员吗？")) {
                    jdbcUtils.updateByPreparedStatement(sql, params);
                    adminTableView.getItems().remove(index);
                    DialogUtils.good(mainApp.getPrimaryStage(), "管理员删除成功");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleNewOrder() {
        String errorMessage = InputChecker.orderSignUpCheck(
                fooOrderIdField.getText(),
                fooOrderUsernameBox.getEditor().getText(),
                fooOrderMagazineNameBox.getEditor().getText(),
                fooMonthBox.getValue(),
                fooCopiesNumBox.getValue(),
                fooTotalPriceField.getText()
        );
        if (errorMessage == null) {
            Integer uid = null;
            Integer mid = null;

            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();

            String sql = "select id from `commonUser` where username = ?";
            List<Object> params = new ArrayList<>();
            params.add(fooOrderUsernameBox.getEditor().getText());
            try {
                Map<String, Object> result = jdbcUtils.findSimpleResult(sql, params);
                uid = (Integer) result.get("id");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sql = "select id from `magazine` where name = ?";
            params.clear();
            params.add(fooOrderMagazineNameBox.getEditor().getText());
            try {
                Map<String, Object> result = jdbcUtils.findSimpleResult(sql, params);
                mid = (Integer) result.get("id");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sql = "insert into `order`(id, uid, mid, month, copiesNum, totalPrice) values(?, ?, ?, ?, ?, ?)";
            params.clear();
            params.add(Integer.parseInt(fooOrderIdField.getText()));
            params.add(uid);
            params.add(mid);
            params.add(fooMonthBox.getValue());
            params.add(fooCopiesNumBox.getValue());
            params.add(Integer.parseInt(fooTotalPriceField.getText()));
            try {
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "订单添加成功");
                initMagazineClassTableAndOrderTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    @FXML
    private void handleNewMagazineClass() {
        String errorMessage = InputChecker.magazineClassSignUpCheck(
                fooMagazineClassIdField.getText(),
                fooMagazineClassNameField.getText()
        );
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();

            String sql = "insert into `mClass`(id, className) values(?, ?)";
            List<Object> params = new ArrayList<>();
            params.add(Integer.parseInt(fooMagazineClassIdField.getText()));
            params.add(fooMagazineClassNameField.getText());
            try {
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "杂志添加成功");
                initMagazineClassTableAndOrderTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    @FXML
    private void handleUpdateOrder() {
        Order oldInfo = fooOrderTableView.getSelectionModel().getSelectedItem();
        String errorMessage = InputChecker.orderUpdateCheck(
                oldInfo,
                fooOrderIdField.getText(),
                fooOrderUsernameBox.getEditor().getText(),
                fooOrderMagazineNameBox.getEditor().getText(),
                fooMonthBox.getValue(),
                fooCopiesNumBox.getValue(),
                fooTotalPriceField.getText()
        );
        if (errorMessage == null) {
            Integer uid = null;
            Integer mid = null;

            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();

            String sql = "select id from `commonUser` where username = ?";
            List<Object> params = new ArrayList<>();
            params.add(fooOrderUsernameBox.getEditor().getText());
            try {
                Map<String, Object> result = jdbcUtils.findSimpleResult(sql, params);
                uid = (Integer) result.get("id");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sql = "select id from `magazine` where name = ?";
            params.clear();
            params.add(fooOrderMagazineNameBox.getEditor().getText());
            try {
                Map<String, Object> result = jdbcUtils.findSimpleResult(sql, params);
                mid = (Integer) result.get("id");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sql = "update `order` set uid = ?, mid = ?, month = ?, copiesNum = ?, totalPrice = ? where id = ?";
            params.clear();
            params.add(uid);
            params.add(mid);
            params.add(fooMonthBox.getValue());
            params.add(fooCopiesNumBox.getValue());
            params.add(Integer.parseInt(fooTotalPriceField.getText()));
            params.add(fooOrderIdField.getText());
            try {
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "更新订单信息成功");
                initMagazineClassTableAndOrderTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    @FXML
    private void handleUpdateMagazineClass() {
        MagazineClass oldInfo = fooMagazineClassTableView.getSelectionModel().getSelectedItem();
        String errorMessage = InputChecker.magazineClassUpdateCheck(
                oldInfo,
                fooMagazineClassIdField.getText(),
                fooMagazineClassNameField.getId()
        );
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "update `mClass` set className = ? where id = ?";
            List<Object> params = new ArrayList<>();
            params.add(fooMagazineClassNameField.getText());
            params.add(Integer.parseInt(fooMagazineClassIdField.getText()));
            try {
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "更新分类成功");
                initMagazineClassTableAndOrderTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    @FXML
    private void handleNewCommonUser() {
        String errorMessage = InputChecker.commonUserSignUpCheck(commonUserIdField.getText(),
                                                                 commonUserNameField.getText(),
                                                                 commonUserpassField.getText(),
                                                                 commonUserTrueNameField.getText(),
                                                                 commonUserSidField.getText(),
                                                                 commonUserTelField.getText(),
                                                                 commonUserAddrField.getText());
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "insert into `commonUser`(id, username, password, trueName, sid, tel, address) values(?, ?, ?, ?, ?, ?, ?)";
            List<Object> params = new ArrayList<>();
            params.add(Integer.parseInt(commonUserIdField.getText()));
            params.add(commonUserNameField.getText());
            params.add(commonUserpassField.getText());
            params.add(commonUserTrueNameField.getText());
            params.add(commonUserSidField.getText());
            params.add(commonUserTelField.getText());
            params.add(commonUserAddrField.getText());

            try {
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "添加成功");
                initCommonUserTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    @FXML
    private void handleDeleteCommonUser() {
        int index = commonUserTableView.getSelectionModel().getFocusedIndex();
        CommonUser commonUser = commonUserTableView.getSelectionModel().getSelectedItem();
        if (commonUser != null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "delete from `commonUser` where id = ?";
            List<Object> params = new ArrayList<>();
            params.add(commonUser.getId());

            try {
                if (DialogUtils.confirm(mainApp.getPrimaryStage(), "请确认您要删掉这个用户，该用户的订单也会被一并删掉。")){
                    jdbcUtils.updateByPreparedStatement(sql, params);
                    commonUserTableView.getItems().remove(index);
                    DialogUtils.good(mainApp.getPrimaryStage(), "删除成功");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleDeleteOrder() {
        int index = fooOrderTableView.getSelectionModel().getFocusedIndex();
        Order order = fooOrderTableView.getSelectionModel().getSelectedItem();
        if (order != null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "delete from `order` where id = ?";
            List<Object> params = new ArrayList<>();
            params.add(order.getId());

            try {
                if (DialogUtils.confirm(mainApp.getPrimaryStage(), "请确认您要删除这个订单")) {
                    jdbcUtils.updateByPreparedStatement(sql, params);
                    DialogUtils.good(mainApp.getPrimaryStage(), "删除订单成功");
                    fooOrderTableView.getItems().remove(index);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleDeleteMagazineClass() {
        int index = fooMagazineClassTableView.getSelectionModel().getFocusedIndex();
        MagazineClass magazineClass = fooMagazineClassTableView.getSelectionModel().getSelectedItem();
        if (magazineClass != null) {
            //检查是否时默认分类
            if (magazineClass.getName().equals("未分类")) {
                DialogUtils.tips(mainApp.getPrimaryStage(), "这是一个系统默认分类，您没有权限对其进行任何形式的修改");
                return;
            }
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();

            List<Object> params = new ArrayList<>();
            String sql = "delete from `mClass` where id = ?";
            params.add(magazineClass.getId());
            try {
                if (DialogUtils.confirm(mainApp.getPrimaryStage(), "请确认您要删除这个分类")) {
                    jdbcUtils.updateByPreparedStatement(sql, params);
                    fooMagazineClassTableView.getItems().remove(index);

                    sql = "update `magazine` set classNumber = ? where classNumber = ?";
                    params.clear();
                    params.add(2333);
                    params.add(magazineClass.getId());
                    try {
                        jdbcUtils.updateByPreparedStatement(sql, params);
                        DialogUtils.good(mainApp.getPrimaryStage(), "删除分类成功");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleUpdateCommonUser() {
        CommonUser oldInfo = commonUserTableView.getSelectionModel().getSelectedItem();
        String errorMessage;
        String id = commonUserIdField.getText();
        if (id == null || id.length() == 0) {
            DialogUtils.tips(mainApp.getPrimaryStage(), "id为空");
            return;
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                DialogUtils.tips(mainApp.getPrimaryStage(), "id应只含有数字");
                return;
            }
        }
        CommonUser newInfo = new CommonUser(Integer.parseInt(commonUserIdField.getText()),
                                                             commonUserNameField.getText(),
                                                             commonUserpassField.getText(),
                                                             commonUserTrueNameField.getText(),
                                                             commonUserSidField.getText(),
                                                             commonUserTelField.getText(),
                                                             commonUserAddrField.getText());
        errorMessage = InputChecker.commonUserUpdateCheck(oldInfo, newInfo);
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "update `commonUser` set username = ?, password = ?, trueName = ?, sid = ?, tel = ?, address = ? where id = ?;";
            List<Object> params = new ArrayList<>();
            params.add(newInfo.getUsername());
            params.add(newInfo.getPassword());
            params.add(newInfo.getTrueName());
            params.add(newInfo.getSid());
            params.add(newInfo.getTel());
            params.add(newInfo.getAddress());
            params.add(newInfo.getId());
            try {
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "更新成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            initCommonUserTable();
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    /**
     * 添加的时候要同时将封面添加到static文件夹中。注意要用特殊的路径
     * 才能读取新添加的文件。这一点很重要！
     */
    @FXML
    private void handleNewMagazine() {
        String errorMessage = InputChecker.magazineSignUpCheck(magazineIdField.getText(),
                                                               imageFile,
                                                               magazineNameField.getText(),
                                                               magazineOfficeField.getText(),
                                                               magazineCycleField.getText(),
                                                               magazineMonthPriceField.getText(),
                                                               magazineClassNameBox.getValue(),
                                                               magazineIntroArea.getText());
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select id from `mClass` where className = ?";
            List<Object> params = new ArrayList<>();
            params.add(magazineClassNameBox.getValue());
            try {
                Map<String, Object> id = jdbcUtils.findSimpleResult(sql, params);
                sql = "insert into `magazine`(id, coverPath, name, office, cycle, price, intro, classNumber) values(?, ?, ?, ?, ?, ?, ?, ?)";
                params.clear();
                params.add(magazineIdField.getText());
                params.add(imageFile.getName());
                params.add(magazineNameField.getText());
                params.add(magazineOfficeField.getText());
                params.add(magazineCycleField.getText());
                params.add(magazineMonthPriceField.getText());
                params.add(magazineIntroArea.getText());
                params.add(id.get("id"));
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "添加成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //复制文件到static文件夹
            ImageUtils.save(imageFile);
            initMagazineTable();
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    /**
     * 删除杂志数据的同时删除static中的图片文件。
     */
    @FXML
    private void handleDeleteMagezine() {
        // 获取到所选项
        int index = magazineTableView.getSelectionModel().getFocusedIndex();
        Magazine magazine = magazineTableView.getSelectionModel().getSelectedItem();

        // 进行删除操作
        if (magazine != null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "delete from `magazine` where id = ?";
            List<Object> params = new ArrayList<>();
            params.add(magazine.getId());

            try {
                if (DialogUtils.confirm(mainApp.getPrimaryStage(), "您确认要删掉这个杂志吗？")) {
                    jdbcUtils.updateByPreparedStatement(sql, params);
                    /*
                     *==================================================
                     *                 !!important!!
                     *     注意这里移动后会进行一个update所以不能用
                     *     imageFile.getPath()
                     *==================================================
                     */
                    magazineTableView.getItems().remove(index);
                    ImageUtils.delete(magazine.getCoverPath());
                    DialogUtils.good(mainApp.getPrimaryStage(), "删除成功");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 杂志更新的同时要记得保存新图片和删除老图片
     */
    @FXML
    private void handleUpdateMagazine() {
        Magazine oldInfo = magazineTableView.getSelectionModel().getSelectedItem();
        String errorMessage;
        String foo = magazineIdField.getText();
        if (foo == null || foo.length() == 0) {
            DialogUtils.tips(mainApp.getPrimaryStage(), "id为空");
            return;
        } else {
            try {
                Integer.parseInt(foo);
            } catch (Exception e) {
                DialogUtils.tips(mainApp.getPrimaryStage(), "id应只含有数字");
                return;
            }
        }

        if (imageFile == null) {
            DialogUtils.tips(mainApp.getPrimaryStage(), "该图片已被使用");
            return;
        }

        Magazine newInfo = new Magazine(Integer.parseInt(magazineIdField.getText()),
                                                         imageFile.getName(),
                                                         magazineNameField.getText(),
                                                         magazineOfficeField.getText(),
                                                         magazineCycleField.getText(),
                                                         magazineMonthPriceField.getText(),
                                                         magazineIntroArea.getText(),
                                                         magazineClassNameBox.getValue());
        errorMessage = InputChecker.magazineUpdateCheck(oldInfo, newInfo);

        //更新数据库
        if (errorMessage == null) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select id from `mClass` where className = ?";
            List<Object> params = new ArrayList<>();
            params.add(newInfo.getClassName());
            System.out.println(newInfo.getClassName());

            try {
                Map<String, Object> id = jdbcUtils.findSimpleResult(sql, params);
                sql = "update `magazine` set coverPath = ?, name = ?, office = ?, cycle = ?, price = ?, intro = ?, classNumber = ? where id = ?;";
                params.clear();
                params.add(newInfo.getCoverPath());
                params.add(newInfo.getName());
                params.add(newInfo.getOffice());
                params.add(newInfo.getCycle());
                params.add(newInfo.getPrice());
                params.add(newInfo.getIntro());
                params.add(id.get("id"));
                params.add(newInfo.getId());
                jdbcUtils.updateByPreparedStatement(sql, params);
                DialogUtils.good(mainApp.getPrimaryStage(), "更新成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            /*
             *==================================================
             *                 !!important!!
             *     注意这里为了防止在新图片在static的情况，一定
             *     要注意顺序，好吧实际上这种情况并不会出现，如果
             *     不是用户手动向static文件夹中添加图片
             *==================================================
             */
            //添加新图片,删除旧图片.
            // TODO: 2018/6/30 但是这个问题先放着吧
            if (!oldInfo.getCoverPath().equals(newInfo.getCoverPath())){
                ImageUtils.save(imageFile);
                ImageUtils.delete(oldInfo.getCoverPath());
            }

            initMagazineTable();
        } else {
            DialogUtils.tips(mainApp.getPrimaryStage(), errorMessage);
        }
    }

    /**
     * 向服务器上传图片，涉及到文件操作，比较重要的一个函数
     */
    @FXML
    private void handleSwitchImage() {
        imageFile = ImageUtils.choose(mainApp);
        // 这里会输出该图片的路径
        System.out.println();
        // 替换图片
        try {
            if (imageFile != null) {
                String localUrl = imageFile.toURI().toURL().toString();
                magazineCover.setImage(new Image(localUrl, true));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 进行精确的查找
     */
    @FXML
    private void handleSearch() {
        ObservableList<Order> filtedData = handleFilter();
        String mName = seMagazineNameBox.getEditor().getText();
        String uName = seUserNameBox.getEditor().getText();
        String copiesNum = seCopiesNumBox.getValue();
        String month = seMonthBox.getValue();

//        System.out.println("杂志名称: " + mName + "用户名称: " + uName + "份数: " + copiesNum + "月数: " + month);

        //进行搜索的过滤
        ObservableList<Order> searchedData = FXCollections.observableArrayList();
        if (mName.length() == 0 && uName.length() == 0 && (copiesNum == null || copiesNum.length() == 0) && (month == null || month.length() == 0)) {
            searchedData = filtedData;
        } else {
            for (Order order : filtedData) {
                if (mName.length() != 0 && !order.getMagazineName().equals(mName)) {
                    continue;
                }
                if (uName.length() != 0 && !order.getUserName().equals(uName)) {
                    continue;
                }
                if (copiesNum != null && copiesNum.length() != 0 && !(order.getCopiesNum() == Integer.parseInt(copiesNum))) {
                    continue;
                }
                if (month != null &&  month.length() != 0 && !(order.getMonth() == Integer.parseInt(month))) {
                    continue;
                }
                searchedData.add(order);
            }
        }
        seTableView.setItems(searchedData);
    }

    /**
     * 进行范围宽泛的过滤查找
     */
    @FXML
    private ObservableList<Order> handleFilter() {

        String mClass = seMagazineClassBox.getEditor().getText();
        String price = sePriceIntervalBox.getValue();

        Map<String, String> nameWithClass = new HashMap<>();
        Integer begin = 0;
        Integer end = 0;

        //开始过滤搜索
        ObservableList<Order> filtedData = FXCollections.observableArrayList();
        if (mClass.length() == 0 && (price == null || price.length() == 0)) {
            filtedData = seOrderData;
        } else {

            // 如果分类被选择了
            if (mClass.length() != 0) {
                // 制造报刊名称->分类名称字典
                JdbcUtils jdbcUtils = new JdbcUtils();
                jdbcUtils.getConnection();
                String sql = "select a.`name` as name ,b.className as class from magazine a, mClass b where a.classNumber = b.id;";
                List<Object> params = new ArrayList<>();

                try {
                    List<Map<String, Object>> data = jdbcUtils.findModeResult(sql, params);

                    for (Map<String, Object> map : data) {
                        // 如果没有含有该杂志名，就添加杂志名和相关的分类
                        if (!nameWithClass.containsKey(map.get("name").toString())) {
                            nameWithClass.put(map.get("name").toString(), map.get("class").toString());
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            // 如果价格区间被选择了, 用正则将价格区间分离出来
            if (price != null && price.length() > 0) {
                String numbers[] = price.split("\\D+");
                begin = Integer.parseInt(numbers[0]);
                if (begin != 5000) {
                    end = Integer.parseInt(numbers[1]);
                } else {
                    end = Integer.MAX_VALUE;
                }
            }

            for (Order order : seOrderData) {
                if (mClass.length() != 0 && !nameWithClass.get(order.getMagazineName()).equals(mClass)) {
                    continue;
                }
                if (price != null && price.length() > 0 && (order.getTotalPrice() < begin || order.getTotalPrice() > end)) {
                    continue;
                }
                filtedData.add(order);
            }
        }
        seTableView.setItems(filtedData);
        return filtedData;
    }

    private void initMostRichMan() {
       Map<String, Object> man = StatisticHelper.mostRichCommonUser();
       mostRichUser.setText(man.get("userName").toString());
       mostRichUsersTotalPrice.setText(man.get("totalPrice").toString() + "$");
    }

    private void initMostOrderUser() {
        Map<String, Object> foo = StatisticHelper.mostOrderNumUser();
        mostOrderNumUser.setText(foo.get("userName").toString());
        mostOrderNumUsersOrderNum.setText(foo.get("orderNum").toString());
    }

    private void initMostPopularMagazine() {
        Map<String, Object> foo = StatisticHelper.mostPopularMazagine();
        bestMagazine.setText(foo.get("magazeinName").toString());
        bestMagazineCopiesNum.setText(foo.get("totalCopiesNum").toString());
    }

    private void initMostPopularClass() {
        Map<String, Object> foo = StatisticHelper.mostPopularClass();
        bestClass.setText(foo.get("className").toString());
        bestClassOrderNum.setText(foo.get("copiesNum").toString());
    }

    private void initAnMagazineNameBox() {
        initBox("select name from `magazine`", anMagazineNameBox, anMagazineNames);
    }

    private void initAnUserNameBox() {
        initBox("select username as name from `commonUser`", anUserNameBox, anUserNames);
    }

    @FXML
    private void updateAnUserNameBox() {
        updateBox(anUserNameBox, anUserNames);
    }

    @FXML
    private void updateAnMagazineNameBox() {
        updateBox(anMagazineNameBox, anMagazineNames);
    }

    @FXML
    private void handleClassPricePie() {
        statisticPriceChart.setData(StatisticHelper.makeClassPriceData(anUserNameBox.getValue()));
    }

    @FXML
    private void handleNamePricePie() {
        statisticPriceChart.setData(StatisticHelper.makeNamePriceData(anUserNameBox.getValue()));
    }

    private void updateAnUserNameLabel() {
        Map<String, Object> foo = StatisticHelper.userOrderTotalPriceLabel(anUserNameBox.getValue());
        if (!foo.isEmpty()) {
            anUserNameLabel.setText(foo.get("totalPrice").toString() + "$");
        } else {
            anUserNameLabel.setText("0" + "$");
        }

    }

    private void updateAnMagazineLabel() {
        Map<String, Object> foo = StatisticHelper.magazineOrderTotalPriceLabel(anMagazineNameBox.getValue());
        if (!foo.isEmpty()) {
            anMagazineNameLabel.setText(foo.get("totalPrice").toString() + "$");
        } else {
            anMagazineNameLabel.setText("0" + "$");
        }
    }

    @FXML
    private void handleMagazinePriceBarChart() {
        magazineBarChart.getData().clear();
        List<Map<String, Object>> foo = StatisticHelper.commonBarChartQuery("select * from magazinereview");
        ObservableList<String> names = FXCollections.observableArrayList();
        XYChart.Series series1 = new XYChart.Series();
        for (Map<String, Object> map : foo) {
            names.add(map.get("magazeinName").toString());
            series1.getData().add(new XYChart.Data<>(map.get("magazeinName"), map.get("totalPrice")));
        }
        xAxis.getCategories().clear();
        xAxis.setCategories(names);
        magazineBarChart.getData().addAll(series1);
    }

    @FXML
    private void handleMagezineCopiesNumberBarChart() {
        magazineBarChart.getData().clear();
        List<Map<String, Object>> foo = StatisticHelper.commonBarChartQuery("select * from classreview");
        ObservableList<String> names = FXCollections.observableArrayList();
        XYChart.Series series1 = new XYChart.Series();
        for (Map<String, Object> map : foo) {
            names.add(map.get("className").toString());
            series1.getData().add(new XYChart.Data<>(map.get("className"), map.get("totalPrice")));
        }
        System.out.println(names);
        xAxis.getCategories().clear();
        xAxis.setCategories(names);
        magazineBarChart.getData().addAll(series1);
    }
}
