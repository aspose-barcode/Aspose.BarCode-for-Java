---
title: Installation
type: docs
weight: 60
url: /java/installation/
---

## **Installing Aspose.BarCode for Java from Maven Repository**
Aspose hosts all Java APIs on [Maven repository](https://repository.aspose.com/webapp/#/artifacts/browse/tree/General/repo/com/aspose/aspose-barcode). You can easily use [Aspose.BarCode for Java](https://repository.aspose.com/webapp/#/artifacts/browse/tree/General/repo/com/aspose/aspose-barcode) API directly in your Maven Projects with simple configurations.
### **Specify Maven Repository Configuration**
First, you need to specify Aspose Maven Repository configuration/location in your Maven **pom.xml** as follows:

{{< highlight java >}}

 <repositories>

    <repository>

        <id>AsposeJavaAPI</id>

        <name>Aspose Java API</name>

        <url>http://repository.aspose.com/repo/</url>

    </repository>

</repositories>

{{< /highlight >}}
### **Define Aspose.BarCode for Java API Dependency**
Then define Aspose.BarCode for Java API dependency in your **pom.xml** as follows:

{{< highlight java >}}

 <dependencies>

    <dependency>

        <groupId>com.aspose</groupId>

        <artifactId>aspose-barcode</artifactId>

        <version>20.4</version>

		<classifier>jdk17</classifier>

    </dependency>

</dependencies>

{{< /highlight >}}

After performing the above steps, Aspose.BarCode for Java dependency will finally be defined in your Maven Project.
