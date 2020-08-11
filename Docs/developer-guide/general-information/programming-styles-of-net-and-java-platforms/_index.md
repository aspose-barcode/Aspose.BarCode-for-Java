---
title: Programming Styles of .NET and Java Platforms
type: docs
weight: 10
url: /java/programming-styles-of-net-and-java-platforms/
---

{{% alert color="primary" %}} 

Aspose.BarCode is available for both .NET and Java platforms. The APIs of both .NET and Java versions are almost the same but they have few differences. And these differences are only because of the different programming styles or standards of .NET and Java platforms. Therefore, Aspose.BarCode for .NET follows programming guidelines widely accepted by .NET developers and Aspose.BarCode for Java follows guidelines accepted in the Java community.

{{% /alert %}} 
### **Namespaces vs Packages**
The names of packages in the Java version of Aspose.BarCode is different from the namespaces in the .NET version:

|**Namespace in Aspose.BarCode for .NET**|**Package in Aspose.BarCode for Java**|
| :- | :- |
|Aspose.BarCode|com.aspose.barcode|
### **Classes**
Class names are the same between the .NET and Java versions.

|**Class Name in Aspose.BarCode for .NET**|**Class Name in Aspose.BarCode for Java**|
| :- | :- |
|BarcodeGenerator|BarcodeGenerator|
|BarCodeReader|BarCodeReader|
|Margins|Margins|
### **Enumerations vs Integer Constants**
Enumerations in the .NET version were ported to Java as classes with public integer constants.

|**The enumeration in Aspose.BarCode for .NET**|**Constant in Aspose.BarCode for Java**|
| :- | :- |
|EncodeTypes.Code128|EncodeTypes.CODE_128|
All constants are integer values in Aspose.BarCode for Java whereas in the .NET version, a parameter, return value or property is of an enumerated type.

**.NET**

{{< highlight csharp >}}

 public void Save(string fileName, ImageFormat fileFormat)

{{< /highlight >}}

**Java**

{{< highlight csharp >}}

 public void save(java.lang.String fileName, int fileFormat)

{{< /highlight >}}

The main reason why we didn't use Java enum is to stay compatible with J2SE 1.4.x as Java enum appeared only in J2SE 5.0.
### **Methods**
Method names follow the accepted programming practices for each platform and therefore differ in the coding style.

|**Method Name in Aspose.BarCode for .NET**|**Method Name in Aspose.BarCode for Java**|
| :- | :- |
|BarCodeReader.Read()|BarCodeReader.read()|
|BarcodeGenerator.Save()|BarcodeGenerator.save()|
The methods are different in the casing only (method names in the Java version start with a lowercase letter).
### **Properties vs Get/Set Methods**
Properties of .NET classes are ported to Java as getter and setter methods. The original name of the method has got and set prefixes added to it.

|**Property Name in Aspose.BarCode for .NET**|**Getter and Setter in Aspose.BarCode for Java**|
| :- | :- |
|BarcodeGenerator.CodeText|BarcodeGenerator.setCodeText(), BarcodeGenerator.getCodeText()|
### **Indexed Properties**
Indexed properties in .NET are ported to Java as get() and set() methods in most cases.
