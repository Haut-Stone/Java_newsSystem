package kernel;

public class Session {
    private static int id;
    private static String username;
    private static String type;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Session.username = username;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Session.type = type;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Session.id = id;
    }

}
