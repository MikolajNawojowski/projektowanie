public class MainPageHTMLBuilder extends HTMLBuilder {

    public String getHTMLPage() {
        return  "<!DOCTYPE html>\n" +
                "<html>\n"+
                "<head>\n" +
                "</head>\n" +
                "<body>\n" +
                "</body>\n" +
                "</html>";
    }

    @Override
    public String getTemplateName() {
        return "mainPageTemplate.html";
    }
}