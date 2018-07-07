package kernel.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import kernel.Session;
import kernel.models.Order;
import sun.jvm.hotspot.compiler.OopMapStream;

import java.awt.event.PaintEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 统计工具为项目提供向数据库请求的所有细节，统一向主程序返回Data数据集
 * @author ShiJiahuan
 * @author www.github.com/Haut-Stone
 */
public class StatisticHelper {

    public static ObservableList<PieChart.Data> makeNameOrderData() {
        return makeCommonOrderData("select  c.`name` as mName from `order` a, commonUser b, magazine c where a.uid = b.id and a.mid = c.id and b.id = ?;");
    }

    public static ObservableList<PieChart.Data> makeClassOrderData() {
        return makeCommonOrderData("select d.className as mName from `order` a, commonUser b, magazine c, mClass d where a.uid = b.id and a.mid = c.id and d.id = c.classNumber and b.id = ?;");
    }

    private static ObservableList<PieChart.Data> makeCommonOrderData(String sql) {
        //从数据库查询相关信息
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        List<Object> params = new ArrayList<>();
        params.add(Session.getId());
        Map<String, Integer> nameOrderData = new HashMap<String, Integer>();
        try {
            List<Map<String, Object>> names = jdbcUtils.findModeResult(sql, params);

            for (Map<String, Object> name : names) {
                String keyName = name.get("mName").toString();
                if (nameOrderData.containsKey(keyName)) {
                    nameOrderData.put(keyName, nameOrderData.get(keyName)+1);
                } else {
                    nameOrderData.put(keyName, 1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(nameOrderData);
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        for (String s : nameOrderData.keySet()) {
            data.add(new PieChart.Data(s, nameOrderData.get(s)));
        }
        return data;
    }

    private static ObservableList<PieChart.Data> makeCommonPriceData(String sql, String uname) {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        List<Object> params = new ArrayList<>();
        params.add(uname);
        Map<String, Integer> nameOrderData = new HashMap<String, Integer>();
        try {
            List<Map<String, Object>> infos = jdbcUtils.findModeResult(sql, params);
            for (Map<String, Object> info : infos) {
                String keyName = info.get("name").toString();
                if (nameOrderData.containsKey(keyName)) {
                    nameOrderData.put(keyName, nameOrderData.get(keyName) + (Integer)info.get("price"));
                } else {
                    nameOrderData.put(keyName, (Integer)info.get("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(nameOrderData);
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        for (String s : nameOrderData.keySet()) {
            data.add(new PieChart.Data(s, nameOrderData.get(s)));
        }
        return data;
    }

    public static ObservableList<PieChart.Data> makeNamePriceData(String name) {
        return makeCommonPriceData("select c.`name` as name, a.totalPrice as price  from `order` a, commonUser b, magazine c, mClass d where a.uid = b.id and a.mid = c.id and d.id = c.classNumber and b.username = ?;", name);
    }

    public static ObservableList<PieChart.Data> makeClassPriceData(String name) {
        return makeCommonPriceData("select d.className as name, a.totalPrice as price  from `order` a, commonUser b, magazine c, mClass d where a.uid = b.id and a.mid = c.id and d.id = c.classNumber and b.username = ?;", name);
    }

    public static Map<String, Object> mostRichCommonUser() {
        return commonMostquery("select * from seuserriview as t where t.totalPrice = (select max(t1.totalPrice) from seuserriview as t1)");
    }

    public static Map<String, Object> mostOrderNumUser() {
        return commonMostquery("select * from seuserriview as t where t.orderNum = (select max(t1.orderNum) from seuserriview as t1) ");

    }

    public static Map<String, Object> mostPopularMazagine() {
        return commonMostquery("select * from semagazinereview as t where t.totalCopiesNum = (select max(t1.totalCopiesNum) from semagazinereview as t1) ");

    }

    public static Map<String, Object> mostPopularClass() {
        return commonMostquery("select * from seclassreview as t where t.copiesNum = (select max(t1.copiesNum) from seclassreview as t1) ");

    }

    public static Map<String, Object> userOrderTotalPriceLabel(String name) {
        return commonLableQuery("select * from userreview where userName = ?", name);
    }

    public static Map<String, Object> magazineOrderTotalPriceLabel(String name) {
        return commonLableQuery("select * from magazinereview where magazeinName = ?", name);
    }

    /**
     * 最重统计数据通用查询
     */
    public static Map<String, Object> commonMostquery(String sql) {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        List<Object> params = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        try {
            data = jdbcUtils.findSimpleResult(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 两个总金额label的统计
     */
    public static Map<String, Object> commonLableQuery(String sql, String name) {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        List<Object> params = new ArrayList<>();
        params.add(name);
        Map<String, Object> data = new HashMap<>();
        try {
            data = jdbcUtils.findSimpleResult(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 对条形图所需要的数据进行获取。
     */
    public static List<Map<String, Object>> commonBarChartQuery(String sql) {
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        List<Object> params = new ArrayList<>();
        List<Map<String, Object>> data = new ArrayList<>();
        try {
            data = jdbcUtils.findModeResult(sql, params);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
