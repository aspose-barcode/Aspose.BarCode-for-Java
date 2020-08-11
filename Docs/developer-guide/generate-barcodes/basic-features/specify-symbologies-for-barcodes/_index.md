---
title: Specify Symbologies for Barcodes
type: docs
weight: 10
url: /java/specify-symbologies-for-barcodes/
---

### **What are Barcodes?**
A barcode is a system for automatic identification of items, such as books in a library, by means of printed bars of different widths which represent numbers. Barcodes are used for many different things. For example, to speed up check out, to track sales and to help with inventory. The first set of numbers in a barcode is the manufacturer code, and the second set of numbers the product code. The barcode was introduced in the early 80s and was often printed on record sleeves only, sometimes also on the center record paper label.
### **Barcode Symbologies**
The barcode symbology is the protocol that defines a standard for arranging the bars and the spaces between the bars that comprise a particular type of barcode, such as UPCA, EAN, Code128 etc.
### **Aspose.BarCode and Barcode Symbologies**
[Aspose.BarCode](https://www.aspose.com/products/barcode/java) supports nearly all popular barcode symbologies. The [BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator) class constructor gets EncodeType parameter to set the barcode symbology.

Developers can pass any symbology to the constructor of BarCodeGenerator class from the list of pre-defined symbologies in the [EncodeTypes](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes) enumeration.

Below is a list of the symbologies pre-defined:

|**Symbologies**|**Description**|
| :- | :- |
|[CODABAR](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#CODABAR)|Specifies that the data should be encoded with Codabar barcode specification|
|[CODE_11](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#CODE_11)|Specifies that the data should be encoded with Code11 barcode specification|
|[CODE_128](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#CODE_128)|Specifies that the data should be encoded with Code128 barcode specification|
|[CODE_39_STANDARD](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#CODE_39_STANDARD)|Specifies that the data should be encoded with Standard Code39 barcode specification|
|[CODE_39_EXTENDED](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#CODE_39_EXTENDED)|Specifies that the data should be encoded with Extended Code39 barcode specification|
|[CODE_93_STANDARD](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#CODE_93_STANDARD)|Specifies that the data should be encoded with Standard Code93 barcode specification|
|[CODE_93_EXTENDED](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#CODE_93_EXTENDED)|Specifies that the data should be encoded with Extended Code93 barcode specification|
|[EAN_13](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#EAN_13)|Specifies that the data should be encoded with EAN-13 barcode specification|
|[EAN_8](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#EAN_13)|Specifies that the data should be encoded with EAN-8 barcode specification|
|[EAN_14](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#EAN_14)|Specifies that the data should be encoded with EAN-14 barcode specification|
|[INTERLEAVED_2_OF_5](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#INTERLEAVED_2_OF_5)|Specifies that the data should be encoded with Interleaved 2 of 5 barcode specification|
|[MSI](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#MSI)|Specifies that the data should be encoded with MSI Plessey barcode specification|
|[STANDARD_2_OF_5](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#STANDARD_2_OF_5)|Specifies that the data should be encoded with Standard 2 of 5 barcode specification|
|[UPCA](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#UPCA)|Specifies that the data should be encoded with UPC-A barcode specification|
|[UPCE](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#UPCE)|Specifies that the data should be encoded with UPC-E barcode specification|
|[POSTNET](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#POSTNET)|Specifies that the data should be encoded with Postnet barcode specification|
|[PLANET](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#PLANET)|Specifies that the data should be encoded with Planet barcode specification|
|[PDF_417](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#PDF_417)|Specifies that the data should be encoded with Pdf417 barcode specification|
|[DATA_MATRIX](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#DATA_MATRIX)|Specifies that the data should be encoded with Datamatrix barcode specification|
|[QR](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#QR)|Specifies that the data should be encoded with QR barcode specification|
|[AZTEC](https://apireference.aspose.com/java/barcode/com.aspose.barcode/EncodeTypes#AZTEC)|Specifies that the data should be encoded with Aztec barcode specification|
### **Code Sample: Creating a Barcode**

#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode-basic_features-SpecifySymbology-SpecifySymbology.java" >}}
