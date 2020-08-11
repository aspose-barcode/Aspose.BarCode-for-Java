---
title: Public API Changes in Aspose.BarCode 6.9.0
type: docs
weight: 10
url: /java/public-api-changes-in-aspose-barcode-6-9-0/
---

{{% alert color="primary" %}} 

This document describes changes to the Aspose.BarCode API from version 6.7.0 to 6.9.0, that may be of interest to module/application developers. It includes not only new and updated public methods, but also a description of any changes in the behavior behind the scenes in Aspose.BarCode. 

{{% /alert %}} 
### **Added Support for Various Recognition Modes**
The newly added APIs allow the developers to configure recognition accuracy and speed options manually in their appliction. For more information, please visit [Switch Barcode Recognition Modes According to the Requirement](/pages/createpage.action?spaceKey=barcodejava&title=Switch+Barcode+Recognition+Modes+According+to+the+Requirement&linkCreation=true&fromPageId=13205831).

Class com.aspose.barcoderecognition.RecognitionMode
Field/Enum com.aspose.barcoderecognition.RecognitionMode.MaxPerformance
Field/Enum com.aspose.barcoderecognition.RecognitionMode.MaxQuality
Field/Enum com.aspose.barcoderecognition.RecognitionMode.MaxBarCodes
Field/Enum com.aspose.barcoderecognition.RecognitionMode.ManualHints

Class com.aspose.barcoderecognition.ManualHint
Field/Enum com.aspose.barcoderecognition.ManualHint.None
Field/Enum com.aspose.barcoderecognition.ManualHint.InvertImage
Field/Enum com.aspose.barcoderecognition.ManualHint.IncorrectBarcodes
Field/Enum com.aspose.barcoderecognition.ManualHint.ComplexBackground
### **Enumeration Field Symbology.MicroPdf417 Added**
Aspose.BarCode for Java 6.9.0 has added the encoding support for Micro Pdf417, whereas decoding capability is already present.

{{< highlight java >}}

 BarCodeBuilder builder = new BarCodeBuilder("12345", Symbology.MicroPdf417);

builder.save(imageFilePath);

{{< /highlight >}}
### **Enumeration Field BarCodeReadType.MicroQR Added**
Aspose.BarCode for Java 6.9.0 has added the decoding support for Micro QR.

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader(imageFilePath, BarCodeReadType.MicroQR);

reader.read();

String result = reader.getCodeText();

reader.close();

{{< /highlight >}}
### **Property BarCodeBuilder.FilledBars Added**
Aspose.BarCode for Java 6.9.0 has exposed the BarCodeBuilder.FilledBars property that allows to generate filled or not filled bars. 

This property works only with 1D barcodes. Default value is true (filled).

The following is an example of the new API usage:

{{< highlight java >}}

 BarCodeBuilder builder = new BarCodeBuilder("TEXT", Symbology.Code128);

builder.setFilledBars(false);

builder.save(imageFilePath, BarCodeImageFormat.Png);

{{< /highlight >}}
### **Added Support for Detectetion of Unicode Text**
Added new APIs:
Method com.aspose.barcoderecognition.BarCodeReader.getDetectEncoding()
Method com.aspose.barcoderecognition.BarCodeReader.setDetectEncoding(Boolean)

Following help topic shows, how developers can detect unicode characters: [Detect the Unicode Encoding of the Barcode](/barcode/java/detect-the-unicode-encoding-of-the-barcode-html/).
