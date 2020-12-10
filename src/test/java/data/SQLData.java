package data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLData {

    private static final String url = System.getProperty("db.url");
    private static final String login = System.getProperty("db.login");
    private static final String password = System.getProperty("db.password");
    private static Connection conn;

    private static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static String getStatusLastPaymentTransaction() {
        String status = null;
        try {
            val conn = getConnection();
            val codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
            val runner = new QueryRunner();
            val statusTransaction = runner.query(conn, codeSQL, new ScalarHandler<>());
            status = (String) statusTransaction;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static String getStatusLastCreditTransaction() {
        String status = null;
        try {
            val conn = getConnection();
            val codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
            val runner = new QueryRunner();
            val statusTransaction = runner.query(conn, codeSQL, new ScalarHandler<>());
            status = (String) statusTransaction;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static String getPaymentId() {
        String id = null;
        try {
            val conn = getConnection();
            val codeSQL = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1;";
            val runner = new QueryRunner();
            val paymentId = runner.query(conn, codeSQL, new ScalarHandler<>());
            id = (String) paymentId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static String getTransactionId() {
        String id = null;
        try {
            val conn = getConnection();
            val codeSQL = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1;";
            val runner = new QueryRunner();
            val transactionId = runner.query(conn, codeSQL, new ScalarHandler<>());
            id = (String) transactionId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static String getCreditId() {
        String id = null;
        try {
            val conn = getConnection();
            val codeSQL = "SELECT credit_id FROM order_entity ORDER BY created DESC LIMIT 1;";
            val runner = new QueryRunner();
            val creditId = runner.query(conn, codeSQL, new ScalarHandler<>());
            id = (String) creditId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static String getCreditRequestId() {
        String id = null;
        try {
            val conn = getConnection();
            val codeSQL = "SELECT id FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
            val runner = new QueryRunner();
            val creditRequestId = runner.query(conn, codeSQL, new ScalarHandler<>());
            id = (String) creditRequestId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
