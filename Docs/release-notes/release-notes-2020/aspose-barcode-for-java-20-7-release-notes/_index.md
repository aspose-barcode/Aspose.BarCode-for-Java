---
title: Aspose.BarCode for Java 20.7 Release Notes
type: docs
weight: 9
url: /java/aspose-barcode-for-java-20-7-release-notes/
---

{{% alert color="primary" %}} 

This page contains release notes information for [Aspose.BarCode for Java 20.7](https://downloads.aspose.com/barcode/java/new-releases/aspose.barcode-for-java-20.7/).

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-37514|Add check digit calculation to ITF-6|Enhancement|
|BARCODENET-37515|Add check digit recognition to ITF-6|Enhancement|
|BARCODENET-37537|Remove obsolete API from BarcodeReader|Enhancement|
|BARCODENET-37474|Investigate addition ECI modes to PDF417 encoder|Enhancement|
|BARCODENET-37473|Investigate addition of input stream compaction mode PDF417 encoder|Enhancement|
|BARCODENET-37477|Investigate addition of new decoding modes to PDF417 processor|Enhancement|
|BARCODENET-37464|Aspose.Barcode 20.04 Unexpected recognition of barcode on image without it|Bug|

# **Public API and Backward Incompatible Changes**
- added method com.aspose.barcode.generation.Pdf417Parameters.getPdf417ECIEncoding
- added method com.aspose.barcode.generation.Pdf417Parameters.setPdf417ECIEncoding(int)
- added field com.aspose.barcode.ECIEncodings.NONE
- removed methodcom.aspose.barcode.barcoderecognition.BarCodeReader.getCodeType
- removed methodcom.aspose.barcode.barcoderecognition.BarCodeReader.getCodeTypeName
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.close
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.read
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getCodeText
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getCodeText(java.nio.charset.Charset)
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getCodeText(boolean)
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getCheckSum
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getAngle
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getRecognitionQuality
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getCodeBytes
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getMacroPdf417FileID
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getMacroPdf417SegmentID
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getMacroPdf417SegmentsCount
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getQRStructuredAppendModeBarCodesQuantity
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getQRStructuredAppendModeBarCodeIndex
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getQRStructuredAppendModeParityData
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getRegion
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getCode128DataPortions
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getIsDeniable
- removed class com.aspose.barcode.barcoderecognition.BarCodeRegion
- removed method com.aspose.barcode.barcoderecognition.BarCodeRegion.getPoints
- removed method com.aspose.barcode.barcoderecognition.BarCodeRegion.drawBarCodeEdges(java.awt.Graphics,java.awt.Color)
- removed method com.aspose.barcode.barcoderecognition.BarCodeRegion.drawBarCodeEdges(java.awt.Graphics,java.awt.Color)
- removed method com.aspose.barcode.barcoderecognition.BarCodeRegion.fillBarCodeRegion(java.awt.Graphics,java.awt.Color)