import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

abstract class HTMLBuilder implements Builder {
    String templateName;

    abstract String getHTMLPage();

    public void buildTemplate() {
        File htmlTemplateFile = new File(getTemplateName());
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(htmlTemplateFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            String htmlPage = this.getHTMLPage();
            bufferedWriter.write(htmlPage);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
