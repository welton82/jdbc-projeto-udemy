package application;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InserirDados {

    public static void main(String[] args) {

        Connection cnn = null;
        PreparedStatement st = null;

        try{
            cnn = DB.getConection();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            st = cnn.prepareStatement(
                    "INSERT INTO seller " +
                            "(name, email, birthdate, basesalary, departmentid)" +
                            "VALUES" +
                            "(?, ?, ?, ?, ?);");

            st.setString(1, "Joel Rodrigues");
            st.setString(2, "joel@yahoo.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1982").getTime()));
            st.setDouble(4, 3000.0);
            st.setInt(5,4);
            int linhasAlteradas = st.executeUpdate();
            System.out.println("Alterada: " + linhasAlteradas);

        }catch(SQLException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }finally{
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
