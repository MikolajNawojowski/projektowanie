public class ArticlePageHTMLBuilder extends HTMLBuilder {

    public String getHTMLPage() {
        return  "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <style type=\"text/css\">\n" +
                "        body { font-family: sans-serif; }\n" +
                "        header {\n" +
                "            font-family: serif;\n" +
                "            font-size: 34px;\n" +
                "            margin-bottom: 0;\n" +
                "        }\n" +
                "        h1 {\n" +
                "            font-family: serif;\n" +
                "            margin-bottom: 0;\n" +
                "        }\n" +
                "        article {\n" +
                "            width: 600px;\n" +
                "            margin-left: auto;\n" +
                "            margin-right: auto;\n" +
                "        }\n" +
                "        .article-meta {\n" +
                "            font-family: sans-serif;\n" +
                "            color: #aaa;\n" +
                "            font-size: 12px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<script></script>\n" +
                "<header></header>\n" +
                "<article>\n" +
                "    <h1></h1>\n" +
                "    <p></p>\n" +
                "</article>\n" +
                "</body>\n" +
                "</html>";
    }
    @Override
    public String getTemplateName() {
        return "articlePageTemplate.html";
    }
}
