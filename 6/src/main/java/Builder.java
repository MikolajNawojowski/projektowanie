import java.io.IOException;

interface Builder {
    void buildTemplate() throws IOException;
    String getTemplateName();
}
