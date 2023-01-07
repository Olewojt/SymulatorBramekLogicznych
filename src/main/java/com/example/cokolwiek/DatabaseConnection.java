package com.example.cokolwiek;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnection {
        private final static String address = "jdbc:mysql://localhost:3306/aplikacja";
        private final static String user = "root";
        private final static String password = "root";
        private final static String driver = "com.mysql.jdbc.Driver";
        private Connection conn;

        public DatabaseConnection() {
                try{
                        this.conn = DriverManager.getConnection(address,user,password);
                } catch ( SQLException e ){
                        e.printStackTrace();
                }
                getBramkiTable();
        }

        public ObservableList<Gate> getBramkiTable(){
                ObservableList<Gate> result = FXCollections.observableArrayList();
                ResultSet rs = null;
                try {
                        int size = 0;
                        Statement st = this.conn.createStatement();
                        rs = st.executeQuery("SELECT * from Bramki");
                        while (rs.next()) {
                                int id = rs.getInt(1);
                                String name = rs.getString(2);
                                int inputs = rs.getInt(3);
                                String map_name = rs.getString(4);
                                result.add(new Gate(id, name, inputs, map_name));
                        }
                } catch (SQLException e){
                        e.printStackTrace();
                }
                return result;
        }

        public void deleteRecord(int id){
                try {
                        Statement st = this.conn.createStatement();
                        st.executeUpdate("DELETE FROM Bramki WHERE ID="+id+";");
                } catch (SQLException e){
                        e.printStackTrace();
                }
        }

        public void deleteTable(String name){
                try {
                        Statement st = this.conn.createStatement();
                        st.executeUpdate("DROP TABLE "+name);
                } catch (SQLException e){
                        e.printStackTrace();
                }
        }
}
