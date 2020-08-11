---
title: Creating a Pdf417 Barcode
type: docs
weight: 20
url: /java/creating-a-pdf417-barcode/
---

Portable Data File 417 (PDF417) is a two-dimensional stacked barcode symbology capable of encoding over a kilobyte of data per label. It was developed by Symbol Technologies (1989-1992) and is currently maintained by ANSI/AIM USA.

This article explains how to:

1. Create a Pdf417 barcode.
1. Manage error correction levels.
1. Create a truncated Pdf417 barcode.
1. Set rows and columns.
1. Set the compaction mode.

To create a Pdf417 barcode:

1. Instantiate a [BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator).
1. Call the setSymbologyType() method and pass Pdf417.
1. Call the setCodeText() method to set the data you want to encode.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAPdf417Barcode-createAPdf417Barcode.java" >}}

|![todo:image_alt_text](http://i.imgur.com/61YdZBz.jpg)|
| :- |
|**Figure: Output Pdf417 barcode image**|
### **Pdf417 Error Correction Level**
Pdf417 uses Reed Solomon error correction instead of check digits. This error correction allows the symbol to withstand some damage without causing loss of data. AIM standards recommend a minimum error correction level of 2.

[BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator) with a higher error correction level produces a bigger image. The code below has the maximum error correction level.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAPdf417Barcode-pdf417ErrorCorrectionLevel.java" >}}



The following figure shows the output picture and some damage.

|![todo:image_alt_text](http://i.imgur.com/b1a12eq.jpg)|
| :- |
|**Figure: Original barcode and damaged barcode**|
The barcode on the right is totally decodable due to its high error correction level. The following picture is a snapshot of decoded output using BarCodeReader.

|![todo:image_alt_text](http://i.imgur.com/VQxZxp6.jpg)|
| :- |
|**Figure: Pdf417 withstands damage**|
### **Truncated Pdf417**
Aspose.BarCode supports truncated Pdf417 version which omits the right-hand side quiet zone to save space.

Setting the Pdf417Truncate property to true gives a smaller image:

|![todo:image_alt_text](http://i.imgur.com/l9xd0B0.jpg)|
| :- |
|**Figure: Normal Pdf417 and truncated Pdf417**|
### **Rows and Columns**
Row and column settings are aimed at general two-dimensional barcodes, applicable to Pdf417. A Pdf417 barcode consists of black and white rectangular modules.

|![todo:image_alt_text](http://i.imgur.com/Buaq1UH.jpg)|
| :- |
|**Figure: Rows and columns**|
If the settings for rows and columns are illegal, the encoder ignores them. Setting rows and columns to zero mean no row and column settings. The following images show Pdf417 barcodes with the same code text but different row and column settings.

|![todo:image_alt_text](http://i.imgur.com/8o8RUbN.jpg)|
| :- |
|**Figure: Different row and column settings**|
### **Pdf417 Compaction Mode**
Setting Pdf417 compaction mode controls how much data can be packed into each code. Below are the different compaction modes supported by the Pdf417 symbology and Aspose.BarCode.

- **Auto**: Automatically detect compaction mode.
- **Text**: Text compaction, suitable for text data.
- **Numeric**: Numeric compaction mode, suitable for numeric data.
- **Binary**: Binary compaction mode, suitable for binary data.

The below code snippet generates a Pdf417 barcode using the text compaction mode:


#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAPdf417Barcode-pdf417CompactionMode.java" >}}



Below is the barcode generated using the above code:

|![todo:image_alt_text](http://i.imgur.com/i2hzT4i.png)|
| :- |
|**Figure: Output with text compaction**|
However, if we set the compaction mode to Binary, it will generate the following barcode:

|![todo:image_alt_text](http://i.imgur.com/QGMz9sI.png)|
| :- |
|**Figure: Output with binary compaction**|
{{% alert color="primary" %}} 

Please note that the barcode size is bigger because we applied binary compaction mode to encode text data. You may set the compaction mode to auto to choose the best possible compaction mode or choose carefully based on the type of CodeText value.

{{% /alert %}}
