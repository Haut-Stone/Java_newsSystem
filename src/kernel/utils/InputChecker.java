package kernel.utils;

import javafx.beans.property.StringProperty;
import kernel.models.*;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InputChecker {
    public static String commonUserInfoCheck(String uname, String pass, String tname, String sid, String tel, String addr) {
        String errorMessage = null;

        //检查地址
        if (addr.length() > 60) {
            errorMessage = "地址长度过长";
        }

        // 检查电话
        if (tel.length() > 60) {
            errorMessage = "电话号码过长";
        }

        //检查身份证号
        // TODO: 2018/6/24 这里以后会改成用正则匹配身份证号
        if (sid.length() > 60) {
            errorMessage = "身份证号过长";
        }

        //检查真实姓名
        if (tname.length() > 60) {
            errorMessage = "真实姓名过长？这不是真实姓名吧";
        }

        //密码是不是符合要求
        if (pass.length() == 0) {
            errorMessage = "密码为空";
        } else if (pass.length() > 60) {
            errorMessage = "密码过长";
        }

        //用户名是不是符合要求
        if (uname.length() == 0) {
            errorMessage = "用户名为空";
        } else if (uname.length() > 60) {
            errorMessage = "用户名过长";
        }

        return errorMessage;
    }

    public static String commonUserSignUpCheck(String uname, String pass, String tname, String sid, String tel, String addr) {
        String errorMessage = commonUserInfoCheck(uname, pass, tname, sid, tel, addr);

        //检查该用户是否已被注册
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `commonUser` where username = ?";
        List<Object> params = new ArrayList<>();
        params.add(uname);

        try {
            CommonUser sameUser = jdbcUtils.findSimpleProResult(sql, params, CommonUser.class);
            if (sameUser != null) {
                errorMessage = "该用户名已被注册";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return errorMessage;
    }

    public static String userUpdateCheck(String olduname,String uname, String pass, String tname, String sid, String tel, String addr) {
        String errorMessage = commonUserInfoCheck(uname, pass, tname, sid, tel, addr);

        //检查该用户是否已被注册
        if (!olduname.equals(uname)) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select * from `commonUser` where username = ?";
            List<Object> params = new ArrayList<>();
            params.add(uname);

            try {
                CommonUser sameUser = jdbcUtils.findSimpleProResult(sql, params, CommonUser.class);
                if (sameUser != null) {
                    errorMessage = "该用户名已被注册";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return errorMessage;
    }

    public static String commonUserSignUpCheck(String id, String uname, String pass, String tname, String sid, String tel, String addr) {
        String errorMessage = commonUserSignUpCheck(uname, pass, tname, sid, tel, addr);

        //在原来的基础上在检查id是否已经被注册
        JdbcUtils jdbcUtils= new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `commonUser` where id = ?";
        List<Object> params = new ArrayList<>();

        if (id == null || id.length() == 0) {
            return errorMessage = "id为空";
        } else if(id.length() > 11) {
            return  errorMessage = "您输入的id过长";
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                return errorMessage = "id应只含有数字";
            }
        }

        params.add(Integer.parseInt(id));

        try {
            CommonUser sameUser = jdbcUtils.findSimpleProResult(sql, params, CommonUser.class);
            if (sameUser != null) {
                errorMessage = "该Id已被注册";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorMessage;
    }

    public static String commonUserUpdateCheck(CommonUser oldInfo, CommonUser newInfo) {
        String errorMessage = commonUserInfoCheck(newInfo.getUsername(), newInfo.getPassword(), newInfo.getTrueName(), newInfo.getSid(), newInfo.getTel(), newInfo.getAddress());

        //如果没有改动id和用户名这两个不能重复的信息
        if (oldInfo.getId() != newInfo.getId()) {
            errorMessage = "您不能修改用户的ID";
        }

        if (!oldInfo.getUsername().equals(newInfo.getUsername())) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select * from `commonUser` where username = ?";
            List<Object> params = new ArrayList<>();
            params.add(newInfo.getUsername());
            try {
                CommonUser same = jdbcUtils.findSimpleProResult(sql, params, CommonUser.class);
                if (same != null) {
                    errorMessage = "该用户名已被占用";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return errorMessage;
    }


    public static String adminSignUpCheck(String id, String uname, String pass) {
        String errorMessage = null;

        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select * from `admin` where id = ?";
        List<Object> params = new ArrayList<>();

        if (id == null || id.length() == 0) {
            return errorMessage = "id为空";
        } else if(id.length() > 11) {
            return  errorMessage = "您输入的id过长";
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                return errorMessage = "id应只含有数字";
            }
        }

        if (uname == null || uname.length() == 0) {
            return errorMessage = "用户名为空";
        }


        params.add(Integer.parseInt(id));

        try {
            Admin sameUser = jdbcUtils.findSimpleProResult(sql, params, Admin.class);
            if (sameUser != null) {
                errorMessage = "该Id已被占用";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        sql = "select * from `admin` where username = ?";
        params.clear();
        params.add(uname);

        try {
            Admin sameUser = jdbcUtils.findSimpleProResult(sql, params, Admin.class);
            if (sameUser != null) {
                errorMessage = "该用户名已被占用";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (pass.length() > 60) {
            errorMessage = "密码超长";
        } else if (pass.length() == 0) {
            errorMessage = "请输入密码";
        }

        return errorMessage;
    }

    public static String adminInfoUpdateCheck(Admin oldInfo, Admin newInfo) {
        String errorMessage = null;

        //如果没有改动id和用户名这两个不能重复的信息
        if (oldInfo.getId() != newInfo.getId()) {
            errorMessage = "您不能修改用户的ID";
        }

        if (newInfo.getUsername() == null || newInfo.getUsername().length() == 0) {
            return errorMessage = "用户名为空";
        }

        if (newInfo.getPassword() == null || newInfo.getPassword().length() == 0) {
            return errorMessage = "密码为空";
        }


        if (!oldInfo.getUsername().equals(newInfo.getUsername())) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select * from `admin` where username = ?";
            List<Object> params = new ArrayList<>();
            params.add(newInfo.getUsername());
            try {
                Admin same = jdbcUtils.findSimpleProResult(sql, params, Admin.class);
                if (same != null) {
                    errorMessage = "该用户名已被占用";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return errorMessage;
    }

    public static String magazineSignUpCheck(String id, File image, String mname, String offic, String cycle, String price, String mclas, String intor) {
        String errorMessage = magazineRegularCheck(id, mname, offic, cycle, price, mclas, intor);

        //检查ID, 图片, 杂志名重复.
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select id, coverPath, name from `magazine`";
        List<Object> params = new ArrayList<>();
        try {
            List<Map<String, Object>> infos = jdbcUtils.findModeResult(sql, params);
            for (Map<String, Object> info : infos) {
//                System.out.println(image.getName() + "  " + info.get("coverPath"));
                /*
                 *==================================================
                 *                 !!important!!
                 *     字符串比较一定要用equals这里就是一个很好的例子
                 *==================================================
                 */
                if (image == null || image.getName().equals(info.get("coverPath"))) {
                    errorMessage = "该图片已被使用，请换一张";
                }
                if (mname.equals(info.get("name"))) {
                    errorMessage = "该杂志名已被使用";
                }
                if (id == null || id.length() == 0) {
                    return errorMessage = "id为空";
                } else {
                    try {
                        Integer.parseInt(id);
                    } catch (Exception e) {
                        return errorMessage = "id应只含有数字";
                    }
                }
                if (Integer.parseInt(id) == (Integer) info.get("id")) {
                    errorMessage = "该id已被注册";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return errorMessage;
    }

    public static String magazineUpdateCheck(Magazine oldInfo, Magazine newInfo) {
        String errorMessage = magazineRegularCheck(Integer.toString(newInfo.getId()), newInfo.getName(), newInfo.getOffice(), newInfo.getCycle(), newInfo.getPrice(), newInfo.getClassName(), newInfo.getIntro());
        //id不允许被修改
        if (oldInfo.getId() != newInfo.getId()) {
            errorMessage = "您不能修改杂志的ID";
        }

        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        List<Object> params = new ArrayList<>();

        // 检查改后的用户名
        if (!oldInfo.getName().equals(newInfo.getName())) {
            String sql = "select * from `magazine` where name = ?";
            params.add(newInfo.getName());
            try {
                Map<String, Object> same = jdbcUtils.findSimpleResult(sql, params);
                /*
                 *==================================================
                 *                 !!important!!
                 *     字典要判断空不空，而不是null
                 *==================================================
                 */
                if (!same.isEmpty()) {
                    errorMessage = "杂志名已被占用";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //检查改后的图片是否重复
        if (!oldInfo.getCoverPath().equals(newInfo.getCoverPath())) {
            String sql = "select coverPath from `magazine`";
            params.clear();
            try {
                List<Map<String, Object>> infos = jdbcUtils.findModeResult(sql, params);
                for (Map<String, Object> info : infos) {
                    if (newInfo.getCoverPath().equals(info.get("coverPath"))) {
                        errorMessage = "该图片已被使用，请换一张";
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return errorMessage;
    }

    public static String magazineRegularCheck(String id, String mname, String offic, String cycle, String price, String mclas, String intor) {
        String errorMessage = null;
        System.out.println("进入杂志检查");
        // 检查常规项
        if (offic.length() == 0) {
            errorMessage = "出版社为空";
        }
        if (cycle.length() == 0) {
            errorMessage = "出版周期为空";
        }
        if (price.length() == 0) {
            errorMessage = "价格为空";
        }
        if (mclas.length() == 0) {
            errorMessage = "没有选择分类";
        }
        if (intor.length() > 140) {
            errorMessage = "介绍过长";
        }
        if (id.length() > 11) {
            errorMessage = "id过长";
        }
        if (mname.length() > 60) {
            errorMessage = "用户名过长";
        }
        return errorMessage;
    }

    public static String orderSignUpCheck(String id, String userName, String magazineName, String month, String copiesNum, String totalPrice) {
        String errorMessage = null;

        // 检查ID
        if (id.length() == 0) {
            return errorMessage = "id不能为空";
        } else if(id.length() > 11) {
            return  errorMessage = "您输入的id过长";
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                return errorMessage = "id应只含有数字";
            }

            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select * from `order` where id = ?";
            List<Object> params = new ArrayList<>();
            params.add(Integer.parseInt(id));

            try {
                Map<String, Object> same = jdbcUtils.findSimpleResult(sql, params);
                if (!same.isEmpty()) {
                    return errorMessage = "该ID已被注册";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //检查用户姓名
        if (userName == null || userName.length() == 0) {
            return errorMessage = "姓名为空";
        } else {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            System.out.println(userName);
            String sql = "select * from `commonUser` where username = ?";
            List<Object> params = new ArrayList<>();
            params.add(userName);
            try {
                Map<String, Object> same = jdbcUtils.findSimpleResult(sql, params);
                if (same.isEmpty()) {
                    return errorMessage = "该用户名不存在";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //检查杂志名
        if (magazineName == null || magazineName.length() == 0) {
            return errorMessage = "杂志名为空";
        } else {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();

            System.out.println(magazineName);
            String sql = "select * from `magazine` where `name` = ?";
            List<Object> params = new ArrayList<>();
            params.add(magazineName);
            System.out.println(params);
            try {
                Map<String, Object> same = jdbcUtils.findSimpleResult(sql, params);
                if (same.isEmpty()) {
                    return errorMessage = "该杂志名不存在";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //检查月份

        //检查份数

        //检查总金额
        if (totalPrice == null || totalPrice.length() == 0) {
            errorMessage = "订单总价格为空";
        } else if(totalPrice.length() > 11) {
            return  errorMessage = "您输入的id过长";
        } else {
            try {
                Integer.parseInt(totalPrice);
            } catch (Exception e) {
                e.printStackTrace();
                errorMessage = "订单总价格只能包含数字";
            }
        }

        return errorMessage;

    }

    public static String magazineClassSignUpCheck(String id, String className) {
        String errorMessage = null;

        //检查ID;
        if (id == null || id.length() == 0) {
            return errorMessage = "id为空";
        } else if(id.length() > 11) {
            return  errorMessage = "您输入的id过长";
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                return  errorMessage = "id应是包含有数字";
            }
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();

            String sql = "select * from `mClass` where id = ?";
            List<Object> params = new ArrayList<>();
            params.add(Integer.parseInt(id));
            try {
                Map<String, Object> same = jdbcUtils.findSimpleResult(sql, params);
                if (!same.isEmpty()) {
                    return errorMessage = "该ID已被注册";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //检查分类名
        if (className == null || className.length() == 0) {
            errorMessage = "分类名为空";
        } else {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select * from `mClass` where className = ?";
            List<Object> params = new ArrayList<>();
            params.add(className);
            try {
                Map<String, Object> same = jdbcUtils.findSimpleResult(sql, params);
                if (!same.isEmpty()) {
                    errorMessage = "该分类名已被使用";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return errorMessage;
    }

    public static String orderUpdateCheck(Order old, String id, String userName, String magazineName, String month, String copiesNum, String totalPrice) {
        String errorMessage = null;

        // 检查id
        if (id == null || id.length() == 0){
            return errorMessage = "id为空";
        } else if(id.length() > 11) {
            return  errorMessage = "您输入的id过长";
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                e.printStackTrace();
                return errorMessage = "id只能包含数字";
            }
            if (old.getId() != Integer.parseInt(id)) {
                return errorMessage = "您不可以更改分类的ID,想要修改id可以考虑删除后，新建";
            }
        }

        // 检查用户名
        if (userName == null || userName.length() == 0) {
            return errorMessage = "用户名为空";
        } else {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();

            String sql = "select * from `commonUser` where username = ?";
            List<Object> params = new ArrayList<>();
            params.add(userName);
            try {
                Map<String, Object> same = jdbcUtils.findSimpleResult(sql, params);
                if (same.isEmpty()) {
                    return  errorMessage = "该用户不存在";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //检查杂志名
        if (magazineName == null || magazineName.length() == 0) {
            return errorMessage = "杂志名为空";
        } else {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();

            String sql = "select * from `magazine` where name = ?";
            List<Object> params = new ArrayList<>();
            params.add(magazineName);
            try {
                Map<String, Object> same = jdbcUtils.findSimpleResult(sql, params);
                if (same.isEmpty()) {
                    return errorMessage = "该杂志不存在";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 检查订单总金额
        if (totalPrice == null || totalPrice.length() == 0) {
            errorMessage = "订单总金额为空";
        } else if(totalPrice.length() > 11) {
            return  errorMessage = "您输入的金额过大";
        } else {
            try {
                Integer.parseInt(totalPrice);
            } catch (Exception e) {
                e.printStackTrace();
                errorMessage = "订单金额只能包含数字";
            }
        }
        return errorMessage;
    }

    public static String magazineClassUpdateCheck(MagazineClass old, String id, String className) {
        String errorMessage = null;


        // 检查id
        if (id == null || id.length() == 0) {
            return errorMessage = "id为空";
        } else if(id.length() > 11) {
            return  errorMessage = "您输入的id过长";
        } else {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                return errorMessage = "id只能包含数字";
            }

            if (old.getId() != Integer.parseInt(id)) {
                return errorMessage = "您不可以更改分类的ID,想要修改id可以考虑删除后，新建";
            }
        }


        // 检查分类名
        if (className == null || className.length() == 0) {
            errorMessage = "分类名为空";
        } else {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            String sql = "select * from `mClass` where id not in (?) and className = ?";
            List<Object> params = new ArrayList<>();
            params.add(old.getName());
            params.add(className);
            try {
                Map<String, Object> same = jdbcUtils.findSimpleResult(sql, params);
                if (!same.isEmpty()) {
                    errorMessage = "该分类名已被占用";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //检查是否时默认分类
        if (old.getName().equals("未分类")) {
            errorMessage = "这是一个系统默认分类，您没有权限对其进行任何形式的修改";
        }
        return errorMessage;
    }
}
