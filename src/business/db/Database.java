package business.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public Database() {

    }

    public void DatabaseConnection() {

        // Connect to database
        String hostName = "alphadb01";
        String dbName = "leilao";
        String user = "murilo@alphadb01";
        String password = "D@t@B@$3Azure2017!";
        String url = String.format("jdbc:sqlserver://%s.database.windows.net:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        //test direct connect...
        //String url = "jdbc:sqlserver://alphadb01.database.windows.net:1433;database=leilao;user=murilo@alphadb01;password=D@t@B@$3Azure2017!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection connection = null;


        try {
            connection = DriverManager.getConnection(url);
            String schema = connection.getSchema();


            System.out.println("Successful connection - Schema: " + schema);

            //System.out.println("Query data example:");
            System.out.println("=========================================");


            /*
            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT TOP 20 pc.Name as CategoryName, p.name as ProductName "
                    + "FROM [SalesLT].[ProductCategory] pc "
                    + "JOIN [SalesLT].[Product] p ON pc.productcategoryid = p.productcategoryid";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                // Print results from select statement
                System.out.println("Top 20 categories:");
                while (resultSet.next())
                {
                    System.out.println(resultSet.getString(1) + " "
                            + resultSet.getString(2));
                }
            }
            */
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}