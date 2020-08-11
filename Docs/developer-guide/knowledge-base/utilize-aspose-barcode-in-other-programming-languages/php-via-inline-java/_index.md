---
title: PHP via Inline Java
type: docs
weight: 40
url: /java/php-via-inline-java/
---

#### **Installation and Configuration**
PHP binaries for Windows are available from <http://www.php.net/>. You need to download and install it before using Aspose.BarCode.

Install and configure the PHP/Java Bridge to make use of Java from PHP. For more details about installing and configuring the PHP/Java Bridge see <http://www.php.net/manual/en/ref.java.php>.

Also you can download PHP/Java Bridge that doesn’t require a java extension from <http://php-java-bridge.sourceforge.net/pjb>.

Download the latest version of Aspose.BarCode for Java, extract files and copy the lib folder with jar files to the root folder of your project. 
#### **PHP Script to Generate Barcode via Inline Java**
The below PHP script will use JPype and use Aspose.BarCode for Java class BarCodeBuilder to generate barcode and save the barcode image to disk.

**PHP**

{{< highlight csharp >}}

 <?php



require_once("http://localhost:8080/JavaBridge/java/Java.inc");

java_require("lib\\Aspose.BarCode.jar;lib\\jaxen-1.1.jar");



// Create an instance of BarCodeBuilder

$builder = new Java("com.aspose.barcode.BarCodeBuilder");



// Set code text

$builder->setCodeText("test-123");

// Save the barcode image to disk

$builder->save("e:\\data\\aspose\\Temp\\testphpjava.png");

?>



{{< /highlight >}}
