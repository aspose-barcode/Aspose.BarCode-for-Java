---
title: Aspose.BarCode for Android via Java 18.10
type: docs
weight: 40
url: /java/aspose-barcode-for-android-via-java-18-10/
---

{{% alert color="primary" %}} 

This page contains release notes information for Aspose.Barcode for Android via Java 18.10.

{{% /alert %}} 
## **New Features**
- Investigate new architecture of barcodes recognition filters.
- Implement UpcaGs1Code128Coupon for new barcode generation API.
- Implementation to read all barcodes from TIFF images.
- Deprecate BarCodeBuilder.
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-36713|Investigate new architecture of barcodes recognition filters.|New Feature|
|BARCODENET-36940|Implement UpcaGs1Code128Coupon for new barcode generation API.|New Feature|
|BARCODENET-36973|Implementation to read all barcodes from TIFF images.|New Feature|
|BARCODENET-36962|Not able to read the barcode.|New Feature|
|BARCODENET-37013|Deprecate BarCodeBuilder.|New Feature|
|BARCODENET-36795|DPI settings work incorrectly with rotation.|Bug|
|BARCODENET-36956|Databars are generated incorrectly from GS1 string.|Bug|
|BARCODENET-37008|ITF14 barcode fails GS1 Verification Process.|Bug|
|BARCODENET-34141|Can't recognize DataMatrix codes from the JPG image.|Bug|
|BARCODENET-36957|The text part isn't correct in ENA13 when the resolution is changed.|Bug|
|BARCODENET-36786|Aspose.BarCode is detecting only 1 out of 3 barcodes from PDF.|Bug|
## **Public API and Backward Incompatible Changes**
Following members have been added:

- Method com.aspose.barcode.barcoderecognition.BarCodeReader.setQualitySettings(QualitySettings)
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.getQualitySettings():QualitySettings
- Class com.aspose.barcode.barcoderecognition.QualitySettings
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getHighPerformance()
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getNormalQuality()
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getHighQuality()
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getMaxBarCodes()
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowInvertImage(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowIncorrectBarcodes(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getAllowIncorrectBarcodes():boolean
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowComplexBackground(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getAllowComplexBackground:boolean
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowMedianSmoothing(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getAllowMedianSmoothing:boolean
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setMedianSmoothingWindowSize(int)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getMedianSmoothingWindowSize:int
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowRegularImage(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getAllowRegularImage:boolean
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowDecreasedImage(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getAllowDecreasedImage:boolean
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowWhiteSpotsRemoving(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getAllowWhiteSpotsRemoving:boolean
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowOneDAdditionalScan(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getAllowOneDAdditionalScan:boolean
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowOneDFastBarcodesDetector(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getAllowOneDFastBarcodesDetector:boolean
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowMicroWhiteSpotsRemoving(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getAllowMicroWhiteSpotsRemoving:boolean
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowSaltAndPaperFiltering(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getAllowSaltAndPaperFiltering:boolean
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowDetectScanGap(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getAllowDetectScanGap:boolean
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowDatamatrixIndustrialBarcodes(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getAllowDatamatrixIndustrialBarcodes:boolean
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setAllowQRMicroQrRestoration(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getAllowQRMicroQrRestoration:boolean
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.setBarCodeImage(Bitmap,Rect[])
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.setBarCodeImage(Bitmap,Rect)
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
