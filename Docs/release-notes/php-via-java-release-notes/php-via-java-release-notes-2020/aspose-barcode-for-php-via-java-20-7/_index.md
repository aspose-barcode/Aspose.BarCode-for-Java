---
title: Aspose.BarCode for PHP via Java 20.7
type: docs
weight: 7
url: /java/aspose-barcode-for-php-via-java-20-7/
---

{{% alert color="primary" %}} 

This page contains release notes information for [Aspose.BarCode for PHP via Java 20.7](https://downloads.aspose.com/barcode/phpjava/new-releases/aspose.barcode-for-php-via-java-20.7/).

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-37514|Add check digit calculation to ITF-6|Enhancement|
|BARCODENET-37515|Add check digit recognition to ITF-6|Enhancement|
|BARCODENET-37537|Remove obsolete API from BarcodeReader|Enhancement|
|BARCODENET-37474|Investigate addition ECI modes to PDF417 encoder|Enhancement|
|BARCODENET-37473|Investigate addition of input stream compaction mode PDF417 encoder|Enhancement|
|BARCODENET-37477|Investigate the addition of new decoding modes to PDF417 processor|Enhancement|
|BARCODENET-37464|Aspose.Barcode 20.04 Unexpected recognition of barcode on the image without it|Bug|

# **Public API and Backward Incompatible Changes**
- added function Pdf417Parameters.getPdf417ECIEncoding : int
- added function Pdf417Parameters.setPdf417ECIEncoding($value) : void
- added field ECIEncodings.NONE
- removed function BarCodeReader.getCodeType
- removed function BarCodeReader.getCodeTypeName
- removed function BarCodeReader.close
- removed function BarCodeReader.read
- removed function BarCodeReader.getCodeText
- removed function BarCodeReader.getCodeText(java.nio.charset.Charset)
- removed function BarCodeReader.getCodeText(boolean)
- removed function BarCodeReader.getCheckSum
- removed function BarCodeReader.getAngle
- removed function BarCodeReader.getRecognitionQuality
- removed function BarCodeReader.getCodeBytes
- removed function BarCodeReader.getMacroPdf417FileID
- removed function BarCodeReader.getMacroPdf417SegmentID
- removed function BarCodeReader.getMacroPdf417SegmentsCount
- removed function BarCodeReader.getQRStructuredAppendModeBarCodesQuantity
- removed function BarCodeReader.getQRStructuredAppendModeBarCodeIndex
- removed function BarCodeReader.getQRStructuredAppendModeParityData
- removed function BarCodeReader.getRegion
- removed function BarCodeReader.getCode128DataPortions
- removed function BarCodeReader.getIsDeniable
- removed class BarCodeRegion
- removed function BarCodeRegion.getPoints
- removed function BarCodeRegion.drawBarCodeEdges(java.awt.Graphics,java.awt.Color)
- removed function BarCodeRegion.drawBarCodeEdges(java.awt.Graphics,java.awt.Color)
- removed function BarCodeRegion.fillBarCodeRegion(java.awt.Graphics,java.awt.Color)