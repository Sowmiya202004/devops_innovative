import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;

class Course {
    int id;
    String name;
    String instructor;

    Course(int id, String name, String instructor) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
    }
}

public class CourseManagementServer {

    static List<Course> courses = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8085), 0);

        // Home Page
        server.createContext("/", exchange -> {

            String html = """
            <html>
            <body>
            <h2>Course Management System</h2>

            <h3>Add Course</h3>
            <form action="/add">
            Course ID: <input name="id"><br><br>
            Course Name: <input name="name"><br><br>
            Instructor: <input name="instructor"><br><br>
            <input type="submit" value="Add Course">
            </form>

            <br>
            <a href="/view">View Courses</a>

            <br><br>

            <h3>Delete Course</h3>
            <form action="/delete">
            Course ID: <input name="id"><br><br>
            <input type="submit" value="Delete Course">
            </form>

            </body>
            </html>
            """;

            exchange.getResponseHeaders().set("Content-Type","text/html");
            exchange.sendResponseHeaders(200, html.length());

            OutputStream os = exchange.getResponseBody();
            os.write(html.getBytes());
            os.close();
        });

        // Add Course
        server.createContext("/add", exchange -> {

            Map<String,String> params = parseQuery(exchange.getRequestURI().getQuery());

            int id = Integer.parseInt(params.get("id"));
            String name = params.get("name");
            String instructor = params.get("instructor");

            courses.add(new Course(id,name,instructor));

            String response = "<html><body><h3>Course Added</h3><a href='/'>Go Back</a></body></html>";

            exchange.getResponseHeaders().set("Content-Type","text/html");
            exchange.sendResponseHeaders(200,response.length());

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        // View Courses
        server.createContext("/view", exchange -> {

            StringBuilder html = new StringBuilder();
            html.append("<html><body><h2>Course List</h2>");

            for(Course c : courses){
                html.append("ID: ").append(c.id)
                    .append(" | Name: ").append(c.name)
                    .append(" | Instructor: ").append(c.instructor)
                    .append("<br>");
            }

            html.append("<br><a href='/'>Go Back</a></body></html>");

            exchange.getResponseHeaders().set("Content-Type","text/html");
            exchange.sendResponseHeaders(200, html.length());

            OutputStream os = exchange.getResponseBody();
            os.write(html.toString().getBytes());
            os.close();
        });

        // Delete Course
        server.createContext("/delete", exchange -> {

            Map<String,String> params = parseQuery(exchange.getRequestURI().getQuery());

            int id = Integer.parseInt(params.get("id"));

            courses.removeIf(c -> c.id == id);

            String response = "<html><body><h3>Course Deleted</h3><a href='/'>Go Back</a></body></html>";

            exchange.getResponseHeaders().set("Content-Type","text/html");
            exchange.sendResponseHeaders(200,response.length());

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();

        System.out.println("Server started at http://localhost:8085");
    }

    static Map<String,String> parseQuery(String query){

        Map<String,String> map = new HashMap<>();

        if(query == null) return map;

        for(String param : query.split("&")){
            String[] pair = param.split("=");
            map.put(pair[0], pair[1]);
        }

        return map;
    }
}
