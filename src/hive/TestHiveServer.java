package hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestHiveServer
{
	
	
	
	
	public static void testUser() {

        try
        {
        	//!connect jdbc:hive2://132.228.248.122:10000/default hive hivehdp org.apache.hive.jdbc.HiveDriver
        	 String urlString = "jdbc:hive2://horton151:10000";
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            Connection conn = DriverManager.getConnection(urlString,"ddd","");
            Statement stmt = conn.createStatement();
            System.out.println("11111");
            
           // stmt.execute("use nocdb");
            String tableName = "testHiveDriverTable_ddd";
            stmt.execute("drop table if exists " + tableName);
            stmt.execute("create table " + tableName + " (key int, value string)");

//             show tables
            /* String sql = "show tables '" + tableName + "'";
            System.out.println("Running: " + sql);
           ResultSet res = stmt.executeQuery(sql);
            if (res.next()) {
              System.out.println(res.getString(1));
            }*/
            
            
            // describe table
           /*String sql = "select count(1) from  " + tableName;
            System.out.println("Running: " + sql);
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
              System.out.println(res.getString(0) + "\t" + res.getString(0));
            }*/
            
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
	}
	
	
	public static void testPut() throws Exception {
		String urlString = "jdbc:hive2://ods3:10000";
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        Connection conn = DriverManager.getConnection(urlString,"testNoYarn","");
        Statement stmt = conn.createStatement();
        System.out.println("11111");
        
       // stmt.execute("use nocdb");
        String tableName = "test_del";
        stmt.execute("load data local inpath '/b' into table test_del");
        
	}
	
	public static void testGet() throws Exception {
		String urlString = "jdbc:hive2://ods3:10000";
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        Connection conn = DriverManager.getConnection(urlString,"testNoYarn","");
        Statement stmt = conn.createStatement();
        System.out.println("11111");
        
       // stmt.execute("use nocdb");
        String tableName = "test_del";
        stmt.execute("INSERT OVERWRITE LOCAL DIRECTORY '~/get_test_del' select * from test_del");
        
	}
	
    public static void main(String[] args) throws Exception
    {
    	testUser();
    	 
    	
    }
}
