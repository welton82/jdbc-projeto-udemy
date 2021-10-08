package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    //Obj para se conectar ao banco de dados
    private static Connection cnn = null;



    //Método para conectar ao SGDB
    public static Connection getConection(){

                if(cnn == null){
                    try {

                        Properties prop = loadProperties();
                        String url = prop.getProperty("dburl");//dburl está definido no arquivo .properties
                        cnn = DriverManager.getConnection(url, prop);//para obter conexão com o banco de dados
                        if(cnn!= null)
                            System.out.println("Connected!!!");
                        else
                            System.out.println("Dont Connected");
                    } catch (SQLException e) {
                        throw new DbException(e.getMessage());
                    }
                }

        return cnn;
    }

    public static void closeConnection(){
        if(cnn != null){
            try {
                cnn.close();
            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    //lê o arquivo .properties
    //É um método auxiliar do tipo Properties que retornará um obj do tipo Properties
    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.Properties")){
            Properties prop = new Properties();
            prop.load(fs);
            return prop;

        }  catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st){
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    public static void closeResultset(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }


        }
    }
}