import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Routing {
    private static Map<String, String> routing = new HashMap<String, String>();
    private static Strategy strategy =
    //DatabasePostgreSql.getInstance();
    //DatabaseMySql.getInstance();
    DatabaseSqlite.getInstance();
    private static Supervisor supervisor = new Supervisor();
    private static Page page;

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);
        BufferedReader br = new BufferedReader(new FileReader("/Users/mnawojow/Desktop/routing.csv"));
        String line =  null;
        while((line=br.readLine())!=null){
            String str[] = line.split(",");
            routing.put(str[0].trim(), str[1].trim());
        }
        server.createContext("/", new MainHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MainHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            URI uri = t.getRequestURI();
            JSONArray response = new JSONArray();
            if (routing.get(uri.getPath()) != null && routing.get(uri.getPath()).equals("MainHandler")) {
                supervisor.buildMainPage(response);
                response.add("Main");
                t.sendResponseHeaders(200, response.toString().length());
                OutputStream os = t.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            }
            else {
                new JSONRequestHandler().handle(t);
            }
        }
    }

    static class JSONRequestHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
           URI uri = t.getRequestURI();
           if (routing.get(uri.getPath()) != null && routing.get(uri.getPath()).equals("JSONHandler")) {
                Map<String, List<String>> parameters = new HashMap<String, List<String>>();
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
                                response.add(strategy.getOne(argument));
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                }
                else if (t.getRequestMethod().equals("POST")) {
                    Iterator entries = parameters.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        String key = (String) entry.getKey();
                        List<String> values = (List<String>) entry.getValue();
                        for (String argument : values) {
                            try {
                                strategy.update(key,argument);
                                JSONObject obj = new JSONObject();
                                obj.put("Result", "Posted " + key + " = " + argument);
                                response.add(obj);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                }
                else if (t.getRequestMethod().equals("PUT")) {
                    Iterator entries = parameters.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        String key = (String) entry.getKey();
                        List<String> values = (List<String>) entry.getValue();
                        for (String argument : values) {
                            try {
                                strategy.insert(key, argument);
                                JSONObject obj = new JSONObject();
                                obj.put("Result", "Putted " + key + " = " + argument);
                                response.add(obj);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                }
                else if (t.getRequestMethod().equals("DELETE")) {
                    Iterator entries = parameters.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        String key = (String) entry.getKey();
                        List<String> values = (List<String>) entry.getValue();
                        for (String argument : values) {
                            try {
                                strategy.delete(argument);
                                JSONObject obj = new JSONObject();
                                obj.put("Result", "Deleted " + key + " = " + argument);
                                response.add(obj);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                }
                t.sendResponseHeaders(200, response.toString().length());
                OutputStream os = t.getResponseBody();
                os.write(response.toString().getBytes());
                os.close();
            }
            else {
                new HTMLRequestHandler().handle(t);
            }
        }
    }


    static class HTMLRequestHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            URI uri = t.getRequestURI();
            if (routing.get(uri.getPath()) != null && routing.get(uri.getPath()).equals("HTMLHandler")) {
                Map<String, List<String>> parameters = new HashMap<String, List<String>>();
                JSONArray response = new JSONArray();
                String result;
                parameters = splitQuery(uri);
                if (t.getRequestMethod().equals("GET")) {
                    Iterator entries = parameters.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        String key = (String) entry.getKey();
                        List<String> values = (List<String>) entry.getValue();
                        for (String argument : values) {
                            try {
                                response.add(strategy.getOne(argument));
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                    supervisor.buildArticlePage(response);
                    result = (new ArticlePage(new Article())).getPage();
                    t.sendResponseHeaders(200, result.length());
                    OutputStream os = t.getResponseBody();
                    os.write(result.getBytes());
                    os.close();
                }
                else {
                        new ErrorHandler().handle(t);
                    }
            }
            else {
                new ErrorHandler().handle(t);
            }
        }
    }

    static class ErrorHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "404 error: website not found.\n" +
                    "Available urls: \n" +
                    "/apiHTML\n" +
                    "/apitJSON\n" +
                    "/main\n";
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
