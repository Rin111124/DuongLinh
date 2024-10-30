/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lm;

import java.sql.Connection;
import java.sql.DriverManager;


public class SQLConnection {

    public static Connection mycon() {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=LibraryManagement","new","l12345");

            return c;

        } catch (Exception e) {
        }
        return null;
    }
}
