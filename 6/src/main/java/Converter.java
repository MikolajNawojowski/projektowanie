import org.json.simple.JSONObject;
import java.sql.ResultSet;


public class Converter {
    public static JSONObject convertToJSON(ResultSet resultSet)
            throws Exception {
        JSONObject obj = new JSONObject();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < total_rows; i++) {
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
            }
        }
        return obj;
    }
}

