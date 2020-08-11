---
title: Aspose.BarCode for Java 18.10 Release Notes
type: docs
weight: 40
url: /java/aspose-barcode-for-java-18-10-release-notes/
---

{{% alert color="primary" %}} 

This page contains release notes information for Aspose.BarCode for Java 18.10.

{{% /alert %}} 
## **New Features**
- New architecture of barcodes recognition filters
- Implemented UpcaGs1Code128Coupon for new barcode generation API
- Implementation to read all barcodes from TIFF images 
- Deprecated BarCodeBuilder
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-36713|Investigate new architecture of barcodes recognition filters. |New Feature|
|BARCODENET-36940 |Implement UpcaGs1Code128Coupon for new barcode generation API |New Feature|
|BARCODENET-36973 |Not able to read all barcodes from TIFF images |New Feature|
|BARCODENET-36962 |Not able to read the barcode |New Feature|
|BARCODENET-37013 |Deprecate BarCodeBuilder|New Feature|
|BARCODENET-36795 |DPI settings works incorrectly with rotation |Bug|
|BARCODENET-36956 |Databars are generated incorrectly from GS1 string |Bug|
|BARCODENET-37008|ITF14 barcode fails GS1 Verification Process |Bug|
|BARCODENET-34141|Can't recognize DataMatrix codes from the JPG image |Bug|
|BARCODENET-36957|Text part isn't correct in ENA13 when change resolution |Bug|
|BARCODENET-36786|Aspose.BarCode is detecting only 1 out of 3 barcodes from PDF|Bug|
## **Public API and Backward Incompatible Changes**
Following members have been added:

- Method com.aspose.barcode.barcoderecognition.BarCodeReader.setQualitySettings(QualitySettings)
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.getQualitySettings():QualitySettings
- Class com.aspose.barcode.barcoderecognition.QualitySettings
- Method com.aspose.barcode.barcoderecognition.QualitySettings.HighPerformance
- Method com.aspose.barcode.barcoderecognition.QualitySettings.NormalQuality
- Method com.aspose.barcode.barcoderecognition.QualitySettings.HighQuality
- Method com.aspose.barcode.barcoderecognition.QualitySettings.MaxBarCodes
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowInvertImage
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowIncorrectBarcodes
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowComplexBackground
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowMedianSmoothing
- Method com.aspose.barcode.barcoderecognition.QualitySettings.MedianSmoothingWindowSize
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowRegularImage
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowDecreasedImage
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowWhiteSpotsRemoving
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowOneDAdditionalScan
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowOneDFastBarcodesDetector
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowMicroWhiteSpotsRemoving
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowSaltAndPaperFiltering
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowDetectScanGap
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowDatamatrixIndustrialBarcodes
- Method com.aspose.barcode.barcoderecognition.QualitySettings.AllowQRMicroQrRestoration
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.setBarCodeImage(BufferedImage,Rectangle[])
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.setBarCodeImage(BufferedImage,Rectangle)
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.setBarCodeImage(InputStream)

Following members have been marked as obsolete:

- Method com.aspose.barcode.barcoderecognition.BarCodeReader.setMedianSmoothingWindowSize(int)
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.getMedianSmoothingWindowSize():int
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.setRecognitionMode(int)
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.getRecognitionMode():int
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.setManualHints(int)
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.getManualHints():int
- Class com.aspose.barcode.barcoderecognition.RecognitionMode
- Class com.aspose.barcode.barcoderecognition.ManualHint

Also class BarCodeBuilder has been deprecated. Please use BarCodeGenerator instead.
