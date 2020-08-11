---
title: Save Barcode Images to and Retrieve from MySQL Database
type: docs
weight: 60
url: /java/save-barcode-images-to-and-retrieve-from-mysql-database/
---

{{% alert color="primary" %}} 

This article uses [Aspose.BarCode for Java](http://www.aspose.com/api/java/barcode) to generate a barcode image and save it to a MySQL database. It also shows how to retrieve the barcode image from the database and then recognize code text from it.

Aspose.BarCode for Java has built-in support for saving barcode images to disk. But it is also possible to store and retrieve barcode images to and from a MySQL database using Java.

{{% /alert %}} 
#### **MySQL Database Preparation**
To use the code snippets in this tutorial, you need access to a MySQL database. To enable Java to connect to MySQL, install MySQL JDBC drivers.

Create a new schema and table in MySQL using the following SQL statement:

**SQL**

{{< highlight csharp >}}

 DROP TABLE IF EXISTS test.product;

CREATE TABLE  test.product (

  ID int(10) unsigned NOT NULL AUTO_INCREMENT,

  ProductNumber varchar(45) NOT NULL,

  ProductName varchar(45) NOT NULL,

  BarCodeImage blob,

  PRIMARY KEY (ID)

) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;



{{< /highlight >}}

The above SQL statement creates a new table called **product** in the test schema. The table has the following columns:

1. ID (Integer)
1. ProductNumber (Varchar) stores the product number.
1. ProductName (Varchar)
1. BarCodeImage (Blob) stores the barcode image in the binary form.
#### **Generate Barcode and Store in Database**
Create a Java program that will generate a barcode image and then store the image in the product table's **BarCodeImage** column. 
The data type of this column is blob so that it can contain binary data.

Below is the full listing of the program.


#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-Src-main-java-com-aspose-barcode-examples-barcodemysql-Common-Common.java" >}}


#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-Src-main-java-com-aspose-barcode-examples-barcodemysql-GenerateAndSaveBarCode-GenerateAndSaveBarCode.java" >}}

In the above code snippet, a class called {{GenerateAndSaveBarCode} is created. It contains two methods:

- **PerformInsertExample** – Insert barcode image in MySQL database. This method first uses Aspose.BarCode for Java to generate a Code39Standard barcode image and save it in the disk. Then it creates a connection with the MySQL database using MySQL JDBC drivers. After that, we created an object of type PreparedStatement and supplies the “INSERT” SQL query for creating a new record in the table. Then we create a stream from the file (saved image) and set the value of the **BarCodeImage** column to this stream. Since the data type is a blob, it can store a stream. Finally, the statement is executed and the database connection closed.
- **PerformUpdateExample** – Update barcode image to MySQL database. This method also generates a barcode image and saves it to disk. Then it uses the UPDATE SQL statement to update the record in the table.
##### **Retrieve Barcode Image from Database and Recognize Barcode**
Now, fetch all the records from the product table and construct an image from the **BarCodeImage** column. Then, pass the image to Aspose.BarCode for Java to recognize CodeText. Below is the code snippet for barcode recognition.


#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-Src-main-java-com-aspose-barcode-examples-barcodemysql-FetchAndRecognizeBarCode-FetchAndRecognizeBarCode.java" >}}

The above code snippet creates a connection to the MySQL database and executes the SELECT SQL statement on the **product** table. It then loops through the Resultset and calls the ResultSet.getBinaryStream() method on the **BarCodeImage** column. Because it is a blob type column, the method returns an object of the type InputStream. Use this stream to write the data to a new file (the image file) and save the file to disk. When the data is saved, pass the path and file name to the BarCodeReader class to recognize the CodeText

All the above code snippets are separate class files. We need another class with a main()}] method to use them. Below is the code snippet of the {{Main class.




#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-Src-main-java-com-aspose-barcode-examples-barcodemysql-Main-Main.java" >}}
