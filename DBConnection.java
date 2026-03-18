import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;

public class DBConnection {

    public static void main(String[] args) {

        String url      = "jdbc:mariadb://localhost:3306/" + args[2]; // uses command line arguments
        String user     = args[0];
        String password = args[1];

        ArrayList <Sales> salesList = new ArrayList<>(); // creates the array list to store sales objects

        //connects to mariadb and create a statement to execute SQL queries
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt  = conn.createStatement();

            System.out.println("Connection to MariaDB established successfully!");

            //sql query joining the three tables to get data to create sales objects
            String sql = "SELECT o.order_no, c.customer_name, c.city, s.name AS salesman_name, o.purchase_amt, s.commission " + 
                         "FROM orders o " +
                         "JOIN customer c ON o.customer_id = c.customer_id " +
                         "JOIN salesman s ON o.salesman_id = s.salesman_id " +
                         "ORDER BY o.order_no";

            //executes statement and stores results
            ResultSet rs = stmt.executeQuery(sql);

            //loop through each row in result set
            while (rs.next()) {
                int orderNumber = rs.getInt("order_no");
                String customerName = rs.getString("customer_name");
                String customerCity = rs.getString("city");
                String salesmanName = rs.getString("salesman_name");
                double amount = rs.getDouble("purchase_amt");
                double commission = rs.getDouble("commission");
                double commissionAmt = amount * commission; //calculation for commission
                
                //create sales object with data from current row and stores it to list
                salesList.add(new Sales(orderNumber, customerName, 
                    customerCity, salesmanName, amount, commissionAmt));

            }
            //column names for toString so it looks nice :)
            System.out.println("\nOrder | Customer              | City            | Salesman          | Amount   | Commission");
            System.out.println("-".repeat(90));

            //Iterate through ArrayList and print each Sales object
            for (Sales s : salesList) {
                System.out.println(s);
            }

        } catch (SQLException e) {
            System.err.println("Database connection failed:");
            e.printStackTrace();
        }
    }
}
//Sales class 
class Sales {
    int orderNumber;
    String customerName;
    String customerCity;
    String salesmanName;
    double amount; 
    double commissionAmount;

    //constructor used to create Sales object from database data
    Sales(int orderNumber, String customerName, String customerCity, String salesmanName, double amount, double commissionAmount) {
            this.orderNumber = orderNumber;
            this.customerName = customerName;
            this.customerCity = customerCity;
            this.salesmanName = salesmanName;
            this.amount = amount;
            this.commissionAmount = commissionAmount;       
    }

    //toString so my results are printed in an easy to understand table :)
    public String toString() {
    return String.format("#%-4d | %-20s | %-15s | %-18s | $%7.2f | $%6.2f",
        orderNumber, customerName, customerCity, salesmanName, amount, commissionAmount);

    }
}