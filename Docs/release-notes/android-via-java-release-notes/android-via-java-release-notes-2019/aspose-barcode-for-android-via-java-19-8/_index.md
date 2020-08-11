---
title: Aspose.BarCode for Android via Java 19.8
type: docs
weight: 50
url: /java/aspose-barcode-for-android-via-java-19-8/
---

{{% alert color="primary" %}} 

This page contains release notes information for [Aspose.BarCode for Android via Java 19.8](https://downloads.aspose.com/barcode/androidjava/new-releases/aspose.barcode-for-android-via-java-19.8/).

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-37249|Remove obsolete code from BarcodeReader|New Feature|
|BARCODENET-37236|Issue when decoding ISMN and ISSN image file using BarCodeReader|Bug|
|BARCODEJAVA-741|The result of recognition is fragile and being changed from time to time|Bug|
# **Public API and Backward Incompatible Changes**
###### **Following members have been removed:**
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.setMedianSmoothingWindowSize(int)
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getMedianSmoothingWindowSize():int
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.setRecognitionMode(int)
- removed method com.aspose.barcode.barcoderecognition.BarCodeReader.getRecognitionMode():int
- removed Property com.aspose.barcode.barcoderecognition.BarCodeReader.setManualHints(int)
- removed Property com.aspose.barcode.barcoderecognition.BarCodeReader.getManualHints():int
- removed Class com.aspose.barcode.barcoderecognition.RecognitionMode
- removed Field com.aspose.barcode.barcoderecognition.RecognitionMode.MaxPerformance
- removed Field com.aspose.barcode.barcoderecognition.RecognitionMode.MaxQuality
- removed Field com.aspose.barcode.barcoderecognition.RecognitionMode.MaxBarCodes
- removed Field com.aspose.barcode.barcoderecognition.RecognitionMode.ManualHints
- removed class com.aspose.barcode.barcoderecognition.ManualHint
- removed Field com.aspose.barcode.barcoderecognition.ManualHint.None
- removed Field com.aspose.barcode.barcoderecognition.ManualHint.InvertImage
- removed Field com.aspose.barcode.barcoderecognition.ManualHint.IncorrectBarcodes
- removed Field com.aspose.barcode.barcoderecognition.ManualHint.ComplexBackground
- removed Field com.aspose.barcode.barcoderecognition.ManualHint.MedianSmoothing
- removed Field com.aspose.barcode.barcoderecognition.ManualHint.UseRestoration
- removed Field com.aspose.barcode.barcoderecognition.ManualHint.UseRegular
- removed Field com.aspose.barcode.barcoderecognition.ManualHint.SpecialFormOfCells
- removed Field com.aspose.barcode.barcoderecognition.ManualHint.SkipRotatedBarcodes
