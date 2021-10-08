package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {

        Connection cnn = null;
        Statement st = null;
        ResultSet rs = null;


        try{
            cnn = DB.getConection();

            st = cnn.createStatement();

            rs = st.executeQuery("select * from department");

            while(rs.next()){
                System.out.println(rs.getInt("id") + ", nome: " + rs.getString("name"));

            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        finally {
            DB.closeResultset(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
