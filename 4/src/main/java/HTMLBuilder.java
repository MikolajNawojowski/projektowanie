import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.regex.*;

public abstract class HTMLBuilder {
    protected HTMLTemplate htmlTemplate;

    public HTMLTemplate build() throws IOException, ParseException {
        return htmlTemplate;
    }

    public String fillTemplate(TemplateJSON templateJSON, String html) throws IOException, ParseException {
        final String scriptRegex = "<script(?:[^>]*src=['\"]([^'\"]*)['\"][^>]*>|[^>]*>([^<]*)</script>)";
        final String titleRegex = "(<title>)(</title>)";
        final String headerRegex = "(<header>)(</header>)";
        final String paragraphRegex = "(<p[0-9]*>)(</p[0-9]*>)";

        html = html.replaceAll(scriptRegex, "<script src=\""+templateJSON.getValue("script")+"\"></script>");
        html = html.replaceAll(titleRegex, "$1"+templateJSON.getValue("title")+"$2");
        html = html.replaceAll(headerRegex, "$1"+templateJSON.getValue("header")+"$2");
        html = html.replaceAll(paragraphRegex, "$1"+templateJSON.getValue("paragraph")+"$2");
        return html;
    }

    protected abstract void composeTemplate() throws IOException, ParseException;
}
