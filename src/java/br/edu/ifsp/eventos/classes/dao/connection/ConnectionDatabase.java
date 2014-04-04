package br.edu.ifsp.eventos.classes.dao.connection;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDatabase {

    private static ConnectionDatabase instance;

    public static ConnectionDatabase getInstance() {
        if (instance == null) {
            try {
                instance = start();
            } catch (Exception ex) {
                Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }
    private Connection connection = null;

    public static ConnectionDatabase start() throws Exception {
        String classJDBC = "com.mysql.jdbc.Driver";
        String serverName = "localhost:3306";    //caminho do servidor do BD
        String mydatabase = "eventos";      //nome do seu banco de dados
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String username = "root";           //nome de um usuário de seu BD 
        String password = "";               //sua senha de acesso 
        return new ConnectionDatabase(classJDBC, url, username, password);

//       String classJDBC = "org.sqlite.JDBC";        
//       String url = "jdbc:sqlite:D:\\"+ mydatabase + ".db"; 
//        
    }

    private ConnectionDatabase(String classJDBC, String url, String username, String password) throws Exception {
        Class.forName(classJDBC);
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public ResultSet executeQuery(String sql, Object args[][]) throws SQLException {
        ResultSet rs = null;
        Statement stat = connection.createStatement();
        if (args == null) {
            rs = stat.executeQuery(sql);
        }
        return rs;
    }

    public void executeUpdate(String sql) throws SQLException {
        executeUpdate(sql, null);
    }

    public void executeUpdate(String sql, Object args[][]) throws SQLException {
        if (args == null) {
            Statement stat = connection.createStatement();
            stat.executeUpdate(sql);
        }
    }

    public void executeBatch(String sql, Object args[][]) throws SQLException, FileNotFoundException {
        PreparedStatement prep = connection.prepareStatement(sql);
        int i;
        for (Object[] a : args) {
            i = 1;
            for (Object b : a) {
                if (b instanceof String) {
                    prep.setString(i, (String) b);
                }
                if (b instanceof Integer) {
                    prep.setInt(i, (Integer) b);
                }
                if (b instanceof File) {
                    FileInputStream fis = new FileInputStream((File) b);
                    prep.setBinaryStream(i, fis);
                }
                if (b instanceof FileInputStream) {
                    prep.setBinaryStream(i, (InputStream) b);
                }
                if (b instanceof BufferedImage) {
                    byte[] buffer = ((DataBufferByte) ((BufferedImage) b).getRaster().getDataBuffer()).getData();
                    InputStream ist = new ByteArrayInputStream(buffer);
                    prep.setBinaryStream(i, ist);
                }
                i++;
            }
            prep.addBatch();
        }

        connection.setAutoCommit(false);
        prep.executeBatch();
        connection.setAutoCommit(true);
    }
}
