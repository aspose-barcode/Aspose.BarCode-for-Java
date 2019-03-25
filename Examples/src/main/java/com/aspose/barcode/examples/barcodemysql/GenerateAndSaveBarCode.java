package com.aspose.barcode.examples.barcodemysql;
//ExStart: GenerateAndSaveBarCode
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.generation.BarCodeGenerator;
 
// This class will generate a barcode image, save it to file on disk
// then create the stream from file and insert the image stream into DB (BLOB type column)
public class GenerateAndSaveBarCode
{
    public void PerformInsertExample()
    {
        try
        {
            // Step-1 - Generate barcode and save temporarily in a file
            String strBarCodeImage = "c:\\temp\\code39.jpg";
            String strCodeText = "NOK-E71";
            BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.CODE_39_STANDARD);
            generator.setCodeText(strCodeText);
            generator.save(strBarCodeImage);
 
            // Step-2 - insert a new record in MySQL DB
            Connection con = null;
 
            // Open connection
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(Common.HOST_URI, Common.USERNAME, Common.PASSWORD);
 
            // Prepare statement
            PreparedStatement pre = con.prepareCall("Insert INTO Product (ProductNumber, ProductName, BarCodeImage) " +
                    "VALUES (?, ?, ?) ");
            // Set product number and product name
            pre.setString(1, "NOK-E71");
            pre.setString(2, "Nokia E Series - E71");
 
            // 3rd column is for barcode image. DB type is BLOB
            // for saving the image, we need to create stream from the image file
            File imgFile = new File(strBarCodeImage);
            FileInputStream fin = new FileInputStream(imgFile);
            pre.setBinaryStream(3, fin, (int)imgFile.length());
 
            // Now execute the statement
            pre.executeUpdate();
            System.out.println("Insertion successfull.");
 
            // Close connection
            pre.close();
            con.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
 
    public void PerformUpdateExample()
    {
        try
        {
            // Step-1 - Generate barcode and save temporarily in a file
            String strBarCodeImage = "c:\\temp\\code39.jpg";
            String strCodeText = "NOK-E71-UPDATED";
            BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.CODE_39_STANDARD);
            generator.setCodeText(strCodeText);
            generator.save(strBarCodeImage);
 
            // Step-2 - update the record in MySQL DB
            Connection con = null;
 
            // Open connection
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(Common.HOST_URI, Common.USERNAME, Common.PASSWORD);
 
            // Prepare statement
            PreparedStatement pre = con.prepareCall("UPDATE Product SET BarCodeImage = ? WHERE ProductNumber = ? ");
            // Barcode image column. DB type is BLOB
            // For saving the image, we need to create stream from the image file
            File imgFile = new File(strBarCodeImage);
            FileInputStream fin = new FileInputStream(imgFile);
            pre.setBinaryStream(1, fin, (int)imgFile.length());
 
            // 2nd column in where condition is the ProductNumber
            pre.setString(2, "NOK-E71");
 
            // Now execute the statement
            pre.executeUpdate();
            System.out.println("Update successfull.");
 
            // Close connection
            pre.close();
            con.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
//ExEnd: GenerateAndSaveBarCode