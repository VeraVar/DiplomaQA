package data;

import lombok.val;
import models.CreditRequestModel;
import models.OrderModel;
import models.PaymentModel;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLData {

    private static final String DB_URL = System.getProperty("db.url");
    private static final String DB_LOGIN = System.getProperty("db.login");
    private static final String DB_PASSWORD = System.getProperty("db.password");
    private static Connection conn;

    private static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void cleanDB() {
        val cleanCreditRequest = "DELETE FROM credit_request_entity";
        val cleanPayment = "DELETE FROM payment_entity";
        val cleanOrder = "DELETE FROM order_entity";
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            runner.update(conn, cleanCreditRequest);
            runner.update(conn, cleanPayment);
            runner.update(conn, cleanOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getStatusLastPaymentTransaction() {
        val selectStatus = "SELECT * FROM payment_entity";
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            val cardStatus = runner.query(conn, selectStatus, new BeanHandler<>(PaymentModel.class));
            return cardStatus.getStatus();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTransactionId() {
        val selectTransactionId = "SELECT * FROM payment_entity";
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            val transactionId = runner.query(conn, selectTransactionId, new BeanHandler<>(PaymentModel.class));
            return transactionId.getTransaction_id();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPaymentId() {
        val selectPaymentId = "SELECT * FROM order_entity";
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            val paymentId = runner.query(conn, selectPaymentId, new BeanHandler<>(OrderModel.class));
            return paymentId.getPayment_id();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCreditId() {
        val selectCreditId = "SELECT * FROM order_entity";
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            val creditId = runner.query(conn, selectCreditId, new BeanHandler<>(OrderModel.class));
            return creditId.getCredit_id();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getStatusLastCreditTransaction() {
        val selectStatus = "SELECT * FROM credit_request_entity";
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            val cardStatus = runner.query(conn, selectStatus, new BeanHandler<>(CreditRequestModel.class));
            return cardStatus.getStatus();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBankId() {
        val selectBankId = "SELECT * FROM credit_request_entity";
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            val bankId = runner.query(conn, selectBankId, new BeanHandler<>(CreditRequestModel.class));
            return bankId.getBank_id();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
