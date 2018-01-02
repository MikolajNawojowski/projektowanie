import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TemplateHTML {

    public String getHTML() {
        String content = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader("template.html"));
            String str;
            while ((str = in.readLine()) != null) {
                content += str;
            }
            in.close();
        } catch (IOException e) {
        }
        return content;
    }
}
