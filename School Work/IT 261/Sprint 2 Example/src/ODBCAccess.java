import java.sql.*;

public class ODBCAccess 
{
	public static void main(String []args)
	{
	        try 
	        {
		        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
		        
		        // Method 1: direct access to a microsoft access database file
	            String filename = "./demo.accdb";//use the name of your database!
	            String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=";
	            database+= filename.trim() + ";DriverID=22;READONLY=true}"; // add on to the end 
	            // now we can get the connection from the DriverManager
	            Connection con = DriverManager.getConnection( database ,"",""); 
	            
		        // Method 2: Create a data connection through windows and set up a connection to it. This is standard on all Windows machines
	            	/*     String dataSourceName = "database";
		        	String dbURL = "jdbc:odbc:" + dataSourceName;
		        	Connection con = DriverManager.getConnection(dbURL, "",""); */
	            
		        // try and create a java.sql.Statement so we can run queries
		        Statement s = con.createStatement();
		        //s.execute("drop table Customer4");
		        s.execute("create table Customer4 ( fname char(50), lname char(50), tel integer, street char(50), city char(30), state char(2), zip integer, PRIMARY KEY(fname,lname,tel))"); // create a table
		        s.execute("insert into Customer4 values('Amit','Shesh',0103,'411 Rinney Dr.','Normal','IL',61761)"); // insert some data into the table 
		        s.execute("insert into Customer4 values('Hushrav','Mogal',7127,'718 University Avenue SE.','Minneapolis','MN',55414)"); // insert some data into the table
		        s.execute("insert into Customer4 values('Anoushka','Shesh',0103,'411 Rinney Dr.','Normal','IL',61761)"); // insert some data into the table
		        s.execute("select fname from Customer4 order by fname"); // select the data from the table
		        ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query
		        if (rs != null) // if rs == null, then there is no ResultSet to view
			        while ( rs.next() ) // this will step through our data row-by-row
			            {
			            	/* the next line will get the first column in our current row's ResultSet 
			            	as a String ( getString( columnNumber) ) and output it to the screen */ 
			            	System.out.println("Data from column_name: " + rs.getString(1) );
			            }
	
		        s.execute("drop table Customer4");
		        s.close(); // close the Statement to let the database know we're done with it
		        con.close(); // close the Connection to let the database know we're done with it
	        } 
	        catch (Exception err) {
	        System.out.println("ERROR: " + err);
	    }
	}

}
