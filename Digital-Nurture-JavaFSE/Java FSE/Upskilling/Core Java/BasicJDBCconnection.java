import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasicJDBCconnection {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:students.db";
        String createTableSql = "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, age INTEGER)";
        String selectSql = "SELECT id, name, age FROM students";

        StudentDAO studentDAO = new StudentDAO(url);

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found.");
            e.printStackTrace();
            return;
        }

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSql);

            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM students")) {
                if (rs.next() && rs.getInt(1) == 0) {
                    studentDAO.insertStudent(conn, "Alice", 20);
                    studentDAO.insertStudent(conn, "Bob", 22);
                }
            }

            studentDAO.updateStudent(conn, 1, "Alice Johnson", 21);

            try (ResultSet rs = stmt.executeQuery(selectSql)) {
                System.out.println("id\tname\tage");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    System.out.println(id + "\t" + name + "\t" + age);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error:");
            e.printStackTrace();
        }
    }
}

class StudentDAO {
    private final String url;
    private static final String INSERT_SQL = "INSERT INTO students(name, age) VALUES(?, ?)";
    private static final String UPDATE_SQL = "UPDATE students SET name = ?, age = ? WHERE id = ?";

    public StudentDAO(String url) {
        this.url = url;
    }

    public void insertStudent(Connection conn, String name, int age) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.executeUpdate();
        }
    }

    public void updateStudent(Connection conn, int id, String name, int age) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }
}
