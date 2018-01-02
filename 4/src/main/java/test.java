import org.json.simple.parser.ParseException;

import java.io.IOException;

public class test {
    public static void main(String args[]) throws IOException, ParseException {
        //TemplateJSON temp = new TemplateJSON();
        //temp.writeConfig();
        HTMLMaster master = new HTMLMaster(new FirstPage());
        System.out.println(master.getHTMLBuilder().toString());
    }
}
