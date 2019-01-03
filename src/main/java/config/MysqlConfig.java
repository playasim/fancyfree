package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author Martin Ma
 * @Date 2019/1/3
 **/
public class MysqlConfig {

    private static final Logger logger = LoggerFactory.getLogger(MysqlConfig.class);


    private static String host;

    private static String username;

    private static String password ;

    private static Connection conn;



    private static void initConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            conn = DriverManager.getConnection(host, username, password);
            if (!conn.isClosed()) {
                logger.info("---------------------------------------------");
                logger.info("Successfully connected to mysql : {}, user : {}", host, username);
                logger.info("---------------------------------------------");
            }

        } catch (ClassNotFoundException e) {
            logger.error("-----------------------");
            logger.error("Unable to find connect to mysql database!");
            logger.error("mysql url:{}, username: {}", host, username);
            logger.error("-----------------------");
            e.printStackTrace();
        } catch (SQLException e) {
            logger.error("-----------------------");
            logger.error("Can not connect to mysql database!");
            logger.error("mysql url:{}, username: {}", host, username);
            logger.error("-----------------------");
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        if (conn == null)
            initConnection();
        return conn;
    }


    public static void close() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                logger.error("----------------------------");
                logger.error("Connection can not be closed/");
                logger.error("----------------------------");
                e.printStackTrace();
            }
        }
    }
}
