import org.json.simple.parser.ParseException;

import java.io.IOException;

public class HTMLMaster {
    private HTMLBuilder htmlBuilder;

    public HTMLMaster(HTMLBuilder htmlBuilder) {
        this.htmlBuilder = htmlBuilder;
    }

    public void setHTMLBuilder(HTMLBuilder htmlBuilder) {
        this.htmlBuilder = htmlBuilder;
    }

    public HTMLTemplate getHTMLBuilder() throws IOException, ParseException  {
        return htmlBuilder.build();
    }

}
