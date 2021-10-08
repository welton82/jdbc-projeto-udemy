package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InsercaoComRecuperacaoId {
    public static void main(String[] args) {

        Connection cnn = null;
        PreparedStatement st = null;

        try{
            cnn = DB.getConection();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            /*
            st = cnn.prepareStatement(
                    "INSERT INTO seller " +
                            "(name, email, birthdate, basesalary, departmentid)" +
                            "VALUES" +
                            "(?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Joel Rodrigues");
            st.setString(2, "joel@yahoo.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1982").getTime()));
            st.setDouble(4, 3000.0);
            st.setInt(5,4);
            */

            st = cnn.prepareStatement(
                    "INSERT INTO department (name) values ('D1'), ('D2')",
                    Statement.RETURN_GENERATED_KEYS);
            int linhasAlteradas = st.executeUpdate();
            if(linhasAlteradas > 0){
                ResultSet rs = st.getGeneratedKeys();
                while(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Id = " + id);
                }
            }else
                System.out.println("Nenhuma linha alterada (no Rown affected!");

        }catch(SQLException e){
            e.printStackTrace();
        } finally{
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
