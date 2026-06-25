import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionHandling {
    private static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            createAccountsTable(conn);
            insertInitialAccounts(conn);

            System.out.println("Balances before transfer:");
            printBalances(conn);

            transfer(conn, 1, 2, 200.00);

            System.out.println("Balances after transfer:");
            printBalances(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createAccountsTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS accounts ("
                + "id INT PRIMARY KEY, "
                + "name VARCHAR(50), "
                + "balance DECIMAL(10,2)"
                + ")";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void insertInitialAccounts(Connection conn) throws SQLException {
        String deleteSql = "DELETE FROM accounts";
        String insertSql = "INSERT INTO accounts (id, name, balance) VALUES (?, ?, ?)";

        try (Statement deleteStmt = conn.createStatement()) {
            deleteStmt.executeUpdate(deleteSql);
        }

        try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setInt(1, 1);
            ps.setString(2, "Alice");
            ps.setBigDecimal(3, new java.math.BigDecimal("1000.00"));
            ps.executeUpdate();

            ps.setInt(1, 2);
            ps.setString(2, "Bob");
            ps.setBigDecimal(3, new java.math.BigDecimal("500.00"));
            ps.executeUpdate();
        }
    }

    private static void printBalances(Connection conn) throws SQLException {
        String sql = "SELECT id, name, balance FROM accounts ORDER BY id";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String balance = rs.getString("balance");
                System.out.printf("Account %d (%s): %s%n", id, name, balance);
            }
        }
    }

    private static void transfer(Connection conn, int fromId, int toId, double amount) throws SQLException {
        String selectSql = "SELECT balance FROM accounts WHERE id = ?";
        String debitSql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        String creditSql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";

        boolean previousAutoCommit = conn.getAutoCommit();
        try {
            conn.setAutoCommit(false);

            double fromBalance;
            try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                selectStmt.setInt(1, fromId);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Source account not found: " + fromId);
                    }
                    fromBalance = rs.getBigDecimal("balance").doubleValue();
                }
            }

            if (fromBalance < amount) {
                throw new SQLException("Insufficient balance in account " + fromId);
            }

            try (PreparedStatement debitStmt = conn.prepareStatement(debitSql);
                    PreparedStatement creditStmt = conn.prepareStatement(creditSql)) {
                debitStmt.setBigDecimal(1, new java.math.BigDecimal(Double.toString(amount)));
                debitStmt.setInt(2, fromId);
                int debitUpdated = debitStmt.executeUpdate();

                creditStmt.setBigDecimal(1, new java.math.BigDecimal(Double.toString(amount)));
                creditStmt.setInt(2, toId);
                int creditUpdated = creditStmt.executeUpdate();

                if (debitUpdated != 1 || creditUpdated != 1) {
                    throw new SQLException("Transfer failed: update count mismatch");
                }
            }

            conn.commit();
            System.out.println("Transfer completed successfully.");
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Transfer failed. Transaction rolled back.");
            throw e;
        } finally {
            conn.setAutoCommit(previousAutoCommit);
        }
    }
}
