package com.aspose.barcode.examples.barcodemysql;

//ExStart: FetchAndRecognizeBarCode
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;

// This class will select the records from MySQL DB
// read a BLOB type column to fetch image stream from DB
// construct a file on disk and recognize the barcode from the image
public class FetchAndRecognizeBarCode {
	public void PerformRecognition() {
		try {
			String strBarCodeImage = "c:\\temp\\code39.jpg";
			// Step-1 - Select the record from the DB on the basis of ProductNumber
			// String strProductNumber = "NOK-E71";
			// Open a connection to the database
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(Common.HOST_URI, Common.USERNAME, Common.PASSWORD);

			// Create a statement to execute the SELECT SQL
			PreparedStatement st = con.prepareStatement("SELECT * FROM Product ");
			// st.setString(1, strProductNumber);
			st.executeQuery();

			// Get the resultset
			ResultSet rs = st.getResultSet();

			// Now check if we have any record in the resultset
			int nCount = 0;
			while (rs.next()) {
				// We got a record, read BLOB field and create image from it
				String len1 = rs.getString("BarCodeImage");
				int len = len1.length();
				byte[] b = new byte[len];
				// Create stream to read the image
				InputStream in = rs.getBinaryStream("BarCodeImage");
				// Write the stream to a file
				int index = in.read(b, 0, len);
				OutputStream outImgBarCode = new FileOutputStream(strBarCodeImage);
				while (index != -1) {
					// Write bytes to file
					outImgBarCode.write(b, 0, index);
					// Read next bytes
					index = in.read(b, 0, len);
				}
				// Close the stream and connection
				outImgBarCode.close();

				// Now that we have got the image from the database
				// read the barcode from the image
				BarCodeReader reader = new BarCodeReader(strBarCodeImage, DecodeType.CODE_39_STANDARD);
				while (reader.read()) {
					System.out.println("BarCode found: Code Text: " + reader.getCodeText());
				}

				nCount++;
			}
			System.out.println(nCount + " records found.");
			con.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
//ExEnd: FetchAndRecognizeBarCode