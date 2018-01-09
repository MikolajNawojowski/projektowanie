import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

public class Routing {
    private static Map<String, String> routing = new HashMap<String, String>();

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);
        BufferedReader br = new BufferedReader(new FileReader("/Users/mnawojow/Desktop/routing.csv"));
        String line =  null;
        while((line=br.readLine())!=null){
            String str[] = line.split(",");
            routing.put(str[1], str[0]);
        }
        server.createContext("/", new MainHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MainHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            URI uri = t.getRequestURI();
            String route = routing.get("MainHandler");
            if (uri.getPath().equals(route.substring(1,route.length()))) {
                TemplateJSON templateJSON = new TemplateJSON();
                templateJSON.writeConfig();
                HTMLMaster master = new HTMLMaster(new FirstPage());
                String response = "";
                try {
                    response = master.getHTMLBuilder().toString();
                } catch (ParseException e) {
                    System.out.println(e.getStackTrace());
                }
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
            else {
                new JSONReguestHandler().handle(t);
            }
        }
    }

    static class JSONReguestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            URI uri = t.getRequestURI();
            String route = routing.get("JSONHandler");
            if (uri.getPath().equals(route)) {
                Map<String, List<String>> parameters = new HashMap<String, List<String>>();
                DatabaseAdapter databaseHandler = new DatabaseAdapter(DatabaseAdapter.Database.sqlite);
                JSONArray response = new JSONArray();
                parameters = splitQuery(uri);
                if (t.getRequestMethod().equals("GET")) {
                    Iterator entries = parameters.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        String key = (String)entry.getKey();
                        List<String> values = ( List<String>)entry.getValue();
                        for (String argument : values) {
                            try {
                                response.add(databaseHandler.getOne(key, argument));
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                }
                else if (t.getRequestMethod().equals("POST")) {
                    for (String key : parameters.keySet())
                        try {
                            databaseHandler.update(Integer.parseInt(key), Integer.parseInt(parameters.get(key).toString()));
                        }
                        catch (Exception e) {
                            System.out.println(e);
                        }
                }
                else if (t.getRequestMethod().equals("PUT")) {
                    for (String key : parameters.keySet()) {
                        databaseHandler.insert(key, Integer.parseInt(parameters.get(key).toString()));
                    }
                }
                else if (t.getRequestMethod().equals("DELETE")) {
                    for (String key : parameters.keySet()) {
                        databaseHandler.delete(Integer.parseInt(parameters.get(key).toString()));
                    }
                }
                t.sendResponseHeaders(200, response.toString().length());
                OutputStream os = t.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            }
            else {
                new ErrorHandler().handle(t);
            }
        }
    }

    static class ErrorHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "404";
            t.sendResponseHeaders(404, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public static Map<String, List<String>> splitQuery(URI uri) throws UnsupportedEncodingException {
        final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
        final String[] pairs = uri.getQuery().split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            if (!query_pairs.containsKey(key)) {
                query_pairs.put(key, new LinkedList<String>());
            }
            final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            query_pairs.get(key).add(value);
        }
        return query_pairs;
    }

}
