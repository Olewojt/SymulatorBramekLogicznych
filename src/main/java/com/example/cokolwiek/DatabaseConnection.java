package com.example.cokolwiek;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnection{
        private final static String address = "jdbc:mysql://localhost:3306/aplikacja";
        private final static String user = "root";
        private final static String password = "root";
//        private final static String driver = "com.mysql.jdbc.Driver";
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
                ResultSet rs;
                try {
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

        public ObservableList<inputModel> getTruthTable(String name){
                ObservableList<inputModel> result = FXCollections.observableArrayList();
                ResultSet rs;
                try {
                        Statement st = this.conn.createStatement();
                        rs = st.executeQuery("SELECT * from "+name);
                        int size = rs.getMetaData().getColumnCount();
//                        System.out.println(size);
                        int inputSize = size-2;
                        int [] mD = new int[12];
                        while (rs.next()) {
                                for(int i=0; i<size-1; i++){
                                        mD[i] = rs.getInt(i+1);
//                                        System.out.print(mD+ "");
                                }
                                mD[11] = rs.getInt(size);
                                inputModel obj = new inputModel(mD[0], mD[1],mD[2],mD[3],mD[4],mD[5],mD[6],mD[7],mD[8],mD[9],mD[10],mD[11]);
                                obj.setSize(inputSize);
                                result.add(obj);
                                mD = new int[12];
                        }
                        System.out.println();
                } catch (SQLException e){
                        e.printStackTrace();
                }
                return result;
        }

        public void deleteRecord(int id){
                try {
                        Statement st = this.conn.createStatement();
                        st.executeUpdate("DELETE FROM Bramki WHERE ID="+id);
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

        public void addRecord(String name, int inputs){
                try {
                        Statement st = this.conn.createStatement();
                        String query = "INSERT INTO Bramki (Name, Inputs, Map_Name) VALUES ";
                        String values = String.format("('%s', %s, '%s')", name, inputs, name+"_OutputMap");
                        st.executeUpdate(query + values);
                } catch (SQLException e){
                        e.printStackTrace();
                }
        }

        public void addTruthTable(String name, ObservableList<inputModel> data){
                int size = data.get(0).getSize()+1;
                try {
                        String id = "ID int NOT NULL AUTO_INCREMENT,";
                        String insert = "(";
                        String columns = "(";
                        columns+=id;
                        for(int i=1; i<size; i++){
                                String in = "input"+i;
                                insert+=in+",";
                                columns+=in+" int NOT NULL,";
                        }
                        String primary = "PRIMARY KEY (ID))";
                        String output = "output int NOT NULL,"+primary;
                        columns+=output;
                        insert+="output)";

                        System.out.println(columns);
                        System.out.println(insert);
                        Statement st = this.conn.createStatement();
                        String mapName = name+"_OutputMap";
                        String truthTable = "CREATE TABLE "+mapName+columns;
                        st.executeUpdate(truthTable);

                        String query = "INSERT INTO "+mapName+insert+" VALUES ";

                        String values = "(";

                        for(int row = 0; row<data.size(); row++){
                                for( int item : data.get(row).getInputs() ){
                                        values+=item+",";
                                }
                                values+=data.get(row).getOutput()+")";
                                st.executeUpdate(query+values);
//                                System.out.print(data.get(row).getOutput());
//                                System.out.println();

                                values="(";
                        }

                } catch (SQLException e){
                        e.printStackTrace();
                }

        }

}
