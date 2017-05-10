import java.sql.*;

public class ODBCAccess2 
{
	public static void main(String []args)
	{
	        try 
	        {
		        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
		        
		        // Method 1: direct access to a microsoft access database file
	            String filename = "./demo2.accdb";//use the name of your database!
	            String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=";
	            database+= filename.trim() + ";DriverID=22;READONLY=true}"; // add on to the end 
	            // now we can get the connection from the DriverManager
	            Connection con = DriverManager.getConnection( database ,"",""); 
	            
		                    
		        // try and create a java.sql.Statement so we can run queries
		        Statement s = con.createStatement();
		        System.out.println("Query 1");
		        s.execute("select custname from customer where creditline>150"); // select the data from the table
		        ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query
		        if (rs != null) // if rs == null, then there is no ResultSet to view
			        while ( rs.next() ) // this will step through our data row-by-row
			            {
			            	/* the next line will get the first column in our current row's ResultSet 
			            	as a String ( getString( columnNumber) ) and output it to the screen */ 
			            	System.out.println(rs.getString(1) );
			            }
		        System.out.println("\nQuery 2");
		        s.execute("select custname, orderdate from customer c,custorder co where c.custid=co.ordercustomer and shippingnotes like 'gift'"); // select the data from the table"
		        rs = s.getResultSet(); // get any ResultSet that came from our query
		        if (rs != null) // if rs == null, then there is no ResultSet to view
			        while ( rs.next() ) // this will step through our data row-by-row
			            {
			            	/* the next line will get the first column in our current row's ResultSet 
			            	as a String ( getString( columnNumber) ) and output it to the screen */ 
			            	System.out.println(rs.getString(1)+"\t"+rs.getDate(2)  );
			            }
		        s.close(); // close the Statement to let the database know we're done with it
		    
		        Statement s2 = con.createStatement();
		        System.out.println("\nQuery 3");
		        s2.execute("select ordercustomer, sum(ordertotal) from custorder group by ordercustomer"); // select the data from the table
		        ResultSet rs2 = s2.getResultSet(); // get any ResultSet that came from our query
		        if (rs != null) 
		           	while ( rs2.next() )
			            {
			            	System.out.println(rs2.getString(1)+"\t"+rs2.getDouble(2) );
			            }
		        s2.close();
		        con.close(); // close the Connection to let the database know we're done with it
	        } 
	        catch (Exception err) {
	        System.out.println("ERROR: " + err);
	    }
	}

}
