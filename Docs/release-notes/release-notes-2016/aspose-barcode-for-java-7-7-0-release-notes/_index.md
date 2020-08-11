---
title: Aspose.BarCode for Java 7.7.0 Release Notes
type: docs
weight: 90
url: /java/aspose-barcode-for-java-7-7-0-release-notes/
---

Aspose.BarCode for Java has been updated to version 7.7.0 and we are pleased to announce it.
The following is a list of changes in this version of Aspose.BarCode.
### **Features and Improvements**

|**Key** |**Summary** |**Category** |
| :- | :- | :- |
|BARCODENET-3426 |Add MaxiCode implementation |New Feature |
|BARCODENET-34359 |Can't recognize Aztec code |Enhancement |
|BARCODENET-34352 |Recognize 1d barcodes with a heterogeneous brightness |Enhancement |
|BARCODENET-34324 |Unable to read barcode from image containing graphic in it, unless the graphic is removed from the file |Enhancement |
|BARCODENET-34223 |Incorrect code and type returned by Aspose.BarCode for Intelligent Mail barcodes |Enhancement |
|BARCODENET-34196 |Incorrect recognition of the Code128 from the tif image |Enhancement |
|BARCODENET-34064 |Can't recognize QR code from the PDF document |Enhancement |
|BARCODENET-33968 |Barcode recognition is returning more bytes |Enhancement |
|BARCODENET-33859 |Can't recognize unknown codes from tif image |Enhancement |
|BARCODENET-33636 |Unable to recognize Pdf417 barcode after correct perspective problem |Enhancement |
|BARCODENET-18901 |EAN and PZN barcodes are not recognized correctly |Enhancement |
|BARCODENET-15724 |cannot recognize code39 barcode |Enhancement |
|BARCODENET-3426 |Add MaxiCode implementation |Example |
|BARCODENET-34359 |Can't recognize Aztec code |Example |
|BARCODENET-34324 |Unable to read barcode from image containing graphic in it, unless the graphic is removed from the file |Example |
|BARCODENET-34196 |Incorrect recognitions of the Code128 from the tif image |Example |
|BARCODENET-34064 |Can't recognize QR code from the PDF document |Example |
|BARCODENET-33859 |Can't recognize unknown codes from tif image |Example |
|BARCODENET-33636 |Unable to recognize Pdf417 barcode after correct perspective problem |Example |
|BARCODENET-15724 |cannot recognize code39|barcode|
|BARCODENET-18901 |EAN and PZN barcodes are not recognized correctly |Example |
### **Public API and Backward Incompatible Changes**
###### **BARCODENET-3426 Add MaxiCode implementation**
###### **New methods getMaxiCodeEncodeMode() and setMaxiCodeEncodeMode(int value) has been added to the class BarCodeBuilder.**
It sets or gets the value indicating the encoding mode for the MaxiCode.


###### **New method  isOverridedDimensionX() has been added to the class BarCodeBuilder.**
It checks whether X-dimension has been specified by the user.

###### **New Symbology.MaxiCode and BarCodeReadType.MaxiCode have been added;**
Code sample

BarCodeBuilder builder = new BarCodeBuilder("MaxiCode (19 chars)", Symbology.MaxiCode);
builder.save("MaxiCode.png", BarCodeImageFormat.Png);
