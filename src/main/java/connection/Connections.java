package connection;

import java.sql.*;

public class Connections
{
        public static Connection connectToSql()
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","Admin@123");
                return c;
            }
            catch (ClassNotFoundException | SQLException cs)
            {
                cs.printStackTrace();
            }
            return null;
        }
}


