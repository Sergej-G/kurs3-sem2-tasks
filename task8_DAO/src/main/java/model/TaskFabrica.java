
package model;

public class TaskFabrica {
    public static String H2="H2";

    public static String POS="PostgreSQL";

    private TaskDAO fabDao = null;

    public TaskDAO getFabDao(String shapeType) {
        if (shapeType.equalsIgnoreCase(H2)){
            fabDao = new TaskDAOImpl();
        } else if (shapeType.equalsIgnoreCase(POS)){
            fabDao = new TaskDAOPos();
        }
        return fabDao;
    }
}


