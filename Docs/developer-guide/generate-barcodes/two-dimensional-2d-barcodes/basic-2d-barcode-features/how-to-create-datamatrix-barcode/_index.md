---
title: How to create Datamatrix Barcode
type: docs
weight: 30
url: /java/how-to-create-datamatrix-barcode/
---

{{% alert color="primary" %}} 

DataMatrix is used to encode product and serial number information on electrical rating plates. For example, to mark surgical instruments in Japan, to identify lenses, circuit boards, and other items during manufacturing. DataMatrix barcodes can store up to 2000 characters.

{{% /alert %}} 

This article shows how to

1. Create a DataMatrix barcode.
1. Setting the encoding mode.

To create a DataMatrix barcode:

1. Instantiate a [BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator),
1. Call the setSymbologyType() method and pass Datamatrix.
1. Call setCodeText() method to set the data you want to encode.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreateDatamatrixBarcode-createADataMatrixBarcode.java" >}}



You can also pass the CodeText and Symbology type when creating the [BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator) object:
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreateDatamatrixBarcode-PassCodeTextAndSymbologyTypeInConstructor.java" >}}

|![todo:image_alt_text](http://i.imgur.com/EN88x6H.jpg)|
| :- |
|**Figure: Sample output DataMatrix barcode**|
#### **Encode Mode**
Aspose.BarCode supports several types of an encoding mode for DataMatrix. The default mode is Auto, indicating that the encoder will choose the best mode. The other supported DataMatrix Encode modes are given below:

|Auto |Automatically pick up the best encode mode for Datamatrix encoding|
| :- | :- |
|ASCII |Encodes one alphanumeric or two numeric characters per byte|
|Full |Encode 8 bit values|
|Custom |Encode with the encoding specified in BarCodeBuilder.CodeTextEncoding|
|C40 |Uses C40 encoding. Encodes Upper-case alphanumeric, Lower case and special characters|
|Text |Uses Text encoding. Encodes Lower-case alphanumeric, Upper case and special characters|


What is the best encode mode? The best encoding mode will likely produce the smallest picture under the same settings. What the encoder does is trying to encode 2 characters into one single byte.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreateDatamatrixBarcode-encodeModeForDataMatrix.java" >}}



The left side barcode was created in Auto encoding mode. The right side barcode uses ASCII encode mode.

|![todo:image_alt_text](http://i.imgur.com/9pB3vu0.jpg)|
| :- |
|**Figure: DataMatrix under different encoding modes**|
#### **Custom Encoding Mode**
Aspose.BarCode now supports the Custom encoding mode for DataMatrix in order to support Encoding standard like UTF8.

Below is an example of setting up a DataMatrix custom encode mode.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreateDatamatrixBarcode-customEncodingModeForDataMatrix.java" >}}
