---
title: Aspose.BarCode for Java 17.5 - Release Notes
type: docs
weight: 80
url: /java/aspose-barcode-for-java-17-5-release-notes/
---

{{% alert color="primary" %}} 

Aspose.BarCode for Java has been updated to version 17.5 and we are pleased to announce it.
The following is a list of changes in this version of Aspose.BarCode.

{{% /alert %}} 
### **Features and Improvements**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-36531|Add support for generate DataMatrix with Text encodation scheme|Feature|
|BARCODENET-36525|Add support for generate DataMatrix with C40 encodation scheme|Feature|
|BARCODEJAVA-249|Ability to generate the QR barcode with a logo/image/picture inside it|Feature|
|BARCODENET-36557|Unable to get the supplement code text from EAN13 coded barcode (supplement barcode is bit blurred)|Bug|
|BARCODENET-36547|Aspose.BarCode is not producing correct output after reading UPCA barcode|Bug|
|BARCODENET-36546|Different recognition result with DecodeType.AllSupportedTypes and BarCodeReadType.AllSupportedTypes|Bug|
|BARCODENET-36536|Aspose.BarCode is unable to extract barcode from PDF|Bug|
|BARCODENET-36516|Aspose Barcode is not reading DataMatrix coded barcode correctly|Bug|
### **Public API and Backward Incompatible Changes**
##### **New public value Text has been added to the DataMatrixEncodeMode enum. It allows to generate DataMatrix with Text encodation scheme.**
BARCODENET-36531 Add support for generate DataMatrix with Text encodation scheme

Code sample:

{{< highlight java >}}

 BarCodeBuilder barCodeBuilder = new BarCodeBuilder("abcdef123456", EncodeTypes.DATA_MATRIX);

barCodeBuilder.setDataMatrixEncodeMode(DataMatrixEncodeMode.TEXT);

barCodeBuilder.save("dataMatrixText.png");

{{< /highlight >}}

Result:

picture "dataMatrixText.png".
##### **New public value C40 has been added to the DataMatrixEncodeMode enum. It allows to generate DataMatrix with C40 encodation scheme.**
BARCODENET-36525 Add support for generate DataMatrix with C40 encodation scheme

Code sample:

{{< highlight java >}}

 BarCodeBuilder barCodeBuilder = new BarCodeBuilder("ABCDEF123456", EncodeTypes.DATA_MATRIX);

barCodeBuilder.setDataMatrixEncodeMode(DataMatrixEncodeMode.C40);

barCodeBuilder.save("dataMatrixC40.png");

{{< /highlight >}}

Result:

picture "dataMatrixC40.png".
