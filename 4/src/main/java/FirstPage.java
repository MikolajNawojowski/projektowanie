import org.json.simple.parser.ParseException;
import java.io.IOException;

public class FirstPage extends HTMLBuilder {
    private HTMLTemplate htmlTemplate = new HTMLTemplate();

    public void composeTemplate() throws IOException, ParseException {
        TemplateJSON templateJSON = new TemplateJSON();
        TemplateHTML templateHTML = new TemplateHTML();
        this.htmlTemplate.setTemplate(fillTemplate(templateJSON, templateHTML.getHTML()));
    }

    public HTMLTemplate build() throws IOException, ParseException {
        composeTemplate();
        return this.htmlTemplate;
    }
}
