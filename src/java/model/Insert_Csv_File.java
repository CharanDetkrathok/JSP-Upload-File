/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.Db_Connection;
import java.io.InputStream;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import javax.servlet.http.Part;

/**
 *
 * @author RU-COM9
 */
public class Insert_Csv_File {

    public String insert_csv(String title,InputStream inputStream, Part filePart) throws SQLException {

        Db_Connection dbconn = new Db_Connection();
        Connection conn = dbconn.Connection();

        String sql = "INSERT INTO csv_file (title, file, insert_date) values (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, title);

        if (inputStream != null) {                    
            statement.setBinaryStream(2, inputStream, (int) filePart.getSize());

            Calendar calendar = Calendar.getInstance();
            java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());

            statement.setDate(3, ourJavaDateObject);
        }

        int row = statement.executeUpdate();
        if (row > 0) {
            out.println("File uploaded!!!");

            conn.close();

            return "1";
        } else {
            out.println("Couldn't upload your file!!!");

            conn.close();

           return "0";
        }
    }
}
