/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp_vehicle_accessories;

import static fyp_vehicle_accessories.ImageUtils.toBufferedImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;

public class SQLDatabaseConnection {

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public Connection getConnection(String sqlServer, String db, String user, String password) {

        String connectionUrl
                = "jdbc:sqlserver://" + sqlServer + ":1433;"
                + "database=" + db + ";"
                + "user=" + user + ";"
                + "password=" + password + ";"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";

        try {
            // Code here.
            Connection connection = DriverManager.getConnection(connectionUrl);

            System.out.println("Success Connect to " + db);
            return connection;

        } // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        SQLDatabaseConnection SQLDatabaseConnection = new SQLDatabaseConnection();
        Connection con = SQLDatabaseConnection.getConnection("vehicle-accessories-server-2.database.windows.net",
                "vehicle-accessories",
                "TeeZhuoXuan",
                "oS6*3y57SnP2lv7tkBehY5Y6YbmR7n");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from test_tbl_9");
        while (rs.next()) {
            // decode byte[] from the encoded string
            byte[] bytesFromDecode = rs.getBytes(3);

            // convert the byte[] back to BufferedImage
            BufferedImage newBi = toBufferedImage(bytesFromDecode);

            // save it somewhere
            ImageIO.write(newBi, "png", new File("C:\\Users\\Tee Zhuo Xuan\\OneDrive - Tunku Abdul Rahman University College\\Desktop\\happy.png"));
        }

//        BufferedImage bi = ImageIO.read(new File("C:\\Users\\Tee Zhuo Xuan\\Downloads\\Human-Being-Free-Download-PNG.png"));
//        byte[] customerImg = ImageUtils.toByteArray(bi, "png");
//        String bytesBase64Img = Base64.encodeBase64String(customerImg);
//        System.out.println(customerImg.length);
//        PreparedStatement prepsInsertPerson = null;
//        String customerID = "C001";
//        String customerName = "Tee Zhuo Xuan";
//        BufferedImage bi = ImageIO.read(new File("C:\\Users\\Tee Zhuo Xuan\\Downloads\\Human-Being-Free-Download-PNG.png"));
//        byte[] customerImg = ImageUtils.toByteArray(bi, "png");
//        String bytesBase64Img = Base64.encodeBase64String(customerImg);
//        System.out.println(bytesBase64Img);
//
//        LocalDateTime myDateObj = LocalDateTime.now();
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
//        String formattedDate = myDateObj.format(myFormatObj);
//
//        SqlParameter byteParam = new SqlParameter("@image_byte_array", SqlDbType.VarBinary);
//
//        String insertSql = "INSERT INTO test_tbl_9 (ID, Strring, Img, datetime) VALUES "
//                + "(?,?,?,?)";
//        //"('" + customerID + "', '" + customerName + "', " + customerImg + ", '" + myFormatObj + "')";
//        prepsInsertPerson = con.prepareStatement(insertSql);
//        prepsInsertPerson.setInt(1, 13);
//        prepsInsertPerson.setString(2, "wabalaba");
//        prepsInsertPerson.setBytes(3, customerImg);
//        prepsInsertPerson.setString(4, formattedDate);
//
//        prepsInsertPerson.execute();
//        System.out.println("Inserted successsssssssssss");
    }
}
