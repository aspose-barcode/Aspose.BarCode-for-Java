---
title: Aspose.BarCode for Java 19.12 Release Notes
type: docs
weight: 10
url: /java/aspose-barcode-for-java-19-12-release-notes/
---

{{% alert color="primary" %}} 

This page contains release notes information for [Aspose.BarCode for Java 19.12](https://downloads.aspose.com/barcode/java/new-releases/aspose.barcode-for-java-19.12/).

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-37334|Create a new barcode detector|New Feature|
|BARCODENET-37303|Implement XML serialization for BarcodeGenerator|New Feature|
|BARCODENET-37302|Add changes to BarCodeReader interface|New Feature|
|BARCODENET-37316|Barcode with rotation is incorrect in SVG|Bug|
|BARCODENET-36973|Not able to read all barcodes from TIFF images|Bug|
|BARCODENET-37034|Region Points and Image Accuracy|Bug|
# **Public API and Backward Incompatible Changes**
Following members have been added:

- Method com.aspose.barcode.generation.BarcodeGenerator.exportToXml(java.lang.String)
- Method com.aspose.barcode.generation.BarcodeGenerator.importFromXml(java.lang.String)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getHighQualityDetection()
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getMaxQualityDetection()
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getUseOldBarcodeDetector()
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setUseOldBarcodeDetector(boolean)
- Method com.aspose.barcode.barcoderecognition.QualitySettings.getDetectorSettings()
- Method com.aspose.barcode.barcoderecognition.QualitySettings.setDetectorSettings(com.aspose.barcode.barcoderecognition.BarcodeSvmDetectorSettings)
- Method com.aspose.barcode.generation.Unit.getResolution()
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.getFoundBarCodes()
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.getFoundCount()
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.readBarCodes()
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.getDetectEncoding()
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.setDetectEncoding(boolean)
- Method com.aspose.barcode.barcoderecognition.BarCodeReader.setBarCodeReadType(com.aspose.barcode.barcoderecognition.SingleDecodeType[])
- Class com.aspose.barcode.barcoderecognition.BarCodeConfidence
- Field com.aspose.barcode.barcoderecognition.BarCodeConfidence.NONE
- Field com.aspose.barcode.barcoderecognition.BarCodeConfidence.MODERATE
- Field com.aspose.barcode.barcoderecognition.BarCodeConfidence.STRONG
- Class com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters
- Method com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters.getOneD()
- Method com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters.getCode128()
- Method com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters.getQR()
- Method com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters.getPdf417()
- Method com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters.equals(java.lang.Object)
- Method com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters.op_Equality(com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters,com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters)
- Method com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters.op_Inequality(com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters,com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters)
- Method com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters.hashCode()
- Method com.aspose.barcode.barcoderecognition.BarCodeExtendedParameters.toString()
- Class com.aspose.barcode.barcoderecognition.BarCodeRegionParameters
- Method com.aspose.barcode.barcoderecognition.BarCodeRegionParameters.getQuadrangle()
- Method com.aspose.barcode.barcoderecognition.BarCodeRegionParameters.getAngle()
- Method com.aspose.barcode.barcoderecognition.BarCodeRegionParameters.getPoints()
- Method com.aspose.barcode.barcoderecognition.BarCodeRegionParameters.getRectangle()
- Method com.aspose.barcode.barcoderecognition.BarCodeRegionParameters.equals(java.lang.Object)
- Method com.aspose.barcode.barcoderecognition.BarCodeRegionParameters.op_Equality(com.aspose.barcode.barcoderecognition.BarCodeRegionParameters,com.aspose.barcode.barcoderecognition.BarCodeRegionParameters)
- Method com.aspose.barcode.barcoderecognition.BarCodeRegionParameters.op_Inequality(com.aspose.barcode.barcoderecognition.BarCodeRegionParameters,com.aspose.barcode.barcoderecognition.BarCodeRegionParameters)
- Method com.aspose.barcode.barcoderecognition.BarCodeRegionParameters.hashCode()
- Method com.aspose.barcode.barcoderecognition.BarCodeRegionParameters.toString()
- Class com.aspose.barcode.barcoderecognition.BarCodeResult
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.getReadingQuality()
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.getConfidence()
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.getCodeText()
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.getCodeBytes()
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.getCodeType()
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.getCodeTypeName()
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.getRegion()
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.getExtended()
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.getCodeText(java.nio.charset.Charset)
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.equals(com.aspose.barcode.barcoderecognition.BarCodeResult)
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.equals(java.lang.Object)
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.op_Equality(com.aspose.barcode.barcoderecognition.BarCodeResult,com.aspose.barcode.barcoderecognition.BarCodeResult)
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.op_Inequality(com.aspose.barcode.barcoderecognition.BarCodeResult,com.aspose.barcode.barcoderecognition.BarCodeResult)
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.hashCode()
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.toString()
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.deepClone()
- Method com.aspose.barcode.barcoderecognition.BarCodeResult.#ctor(com.aspose.barcode.barcoderecognition.BarCodeResult)
- Class com.aspose.barcode.barcoderecognition.BaseExtendedParameters
- Method com.aspose.barcode.barcoderecognition.BaseExtendedParameters.isEmpty()
- Class com.aspose.barcode.barcoderecognition.OneDExtendedParameters
- Method com.aspose.barcode.barcoderecognition.OneDExtendedParameters.getValue()
- Method com.aspose.barcode.barcoderecognition.OneDExtendedParameters.getCheckSum()
- Method com.aspose.barcode.barcoderecognition.OneDExtendedParameters.equals(java.lang.Object)
- Method com.aspose.barcode.barcoderecognition.OneDExtendedParameters.op_Equality(com.aspose.barcode.barcoderecognition.OneDExtendedParameters,com.aspose.barcode.barcoderecognition.OneDExtendedParameters)
- Method com.aspose.barcode.barcoderecognition.OneDExtendedParameters.op_Inequality(com.aspose.barcode.barcoderecognition.OneDExtendedParameters,com.aspose.barcode.barcoderecognition.OneDExtendedParameters)
- Method com.aspose.barcode.barcoderecognition.OneDExtendedParameters.hashCode()
- Method com.aspose.barcode.barcoderecognition.OneDExtendedParameters.toString()
- Class com.aspose.barcode.barcoderecognition.Pdf417ExtendedParameters
- Method com.aspose.barcode.barcoderecognition.Pdf417ExtendedParameters.getMacroPdf417FileID()
- Method com.aspose.barcode.barcoderecognition.Pdf417ExtendedParameters.getMacroPdf417SegmentID()
- Method com.aspose.barcode.barcoderecognition.Pdf417ExtendedParameters.getMacroPdf417SegmentsCount()
- Method com.aspose.barcode.barcoderecognition.Pdf417ExtendedParameters.equals(java.lang.Object)
- Method com.aspose.barcode.barcoderecognition.Pdf417ExtendedParameters.op_Equality(com.aspose.barcode.barcoderecognition.Pdf417ExtendedParameters,com.aspose.barcode.barcoderecognition.Pdf417ExtendedParameters)
- Method com.aspose.barcode.barcoderecognition.Pdf417ExtendedParameters.op_Inequality(com.aspose.barcode.barcoderecognition.Pdf417ExtendedParameters,com.aspose.barcode.barcoderecognition.Pdf417ExtendedParameters)
- Method com.aspose.barcode.barcoderecognition.Pdf417ExtendedParameters.hashCode()
- Method com.aspose.barcode.barcoderecognition.Pdf417ExtendedParameters.toString()
- Class com.aspose.barcode.barcoderecognition.QRExtendedParameters
- Method com.aspose.barcode.barcoderecognition.QRExtendedParameters.getQRStructuredAppendModeBarCodesQuantity()
- Method com.aspose.barcode.barcoderecognition.QRExtendedParameters.getQRStructuredAppendModeBarCodeIndex()
- Method com.aspose.barcode.barcoderecognition.QRExtendedParameters.getQRStructuredAppendModeParityData()
- Method com.aspose.barcode.barcoderecognition.QRExtendedParameters.equals(java.lang.Object)
- Method com.aspose.barcode.barcoderecognition.QRExtendedParameters.op_Equality(com.aspose.barcode.barcoderecognition.QRExtendedParameters,com.aspose.barcode.barcoderecognition.QRExtendedParameters)
- Method com.aspose.barcode.barcoderecognition.QRExtendedParameters.op_Inequality(com.aspose.barcode.barcoderecognition.QRExtendedParameters,com.aspose.barcode.barcoderecognition.QRExtendedParameters)
- Method com.aspose.barcode.barcoderecognition.QRExtendedParameters.hashCode()
- Method com.aspose.barcode.barcoderecognition.QRExtendedParameters.toString()
- Class com.aspose.barcode.barcoderecognition.Quadrangle
- Method com.aspose.barcode.barcoderecognition.Quadrangle.#ctor()
- Method com.aspose.barcode.barcoderecognition.Quadrangle.#ctor(java.awt.Point,java.awt.Point,java.awt.Point,java.awt.Point)
- Method com.aspose.barcode.barcoderecognition.Quadrangle.getLeftTop()
- Method com.aspose.barcode.barcoderecognition.Quadrangle.setLeftTop(java.awt.Point)
- Method com.aspose.barcode.barcoderecognition.Quadrangle.getRightTop()
- Method com.aspose.barcode.barcoderecognition.Quadrangle.setRightTop(java.awt.Point)
- Method com.aspose.barcode.barcoderecognition.Quadrangle.getRightBottom()
- Method com.aspose.barcode.barcoderecognition.Quadrangle.setRightBottom(java.awt.Point)
- Method com.aspose.barcode.barcoderecognition.Quadrangle.getLeftBottom()
- Method com.aspose.barcode.barcoderecognition.Quadrangle.setLeftBottom(java.awt.Point)
- Method com.aspose.barcode.barcoderecognition.Quadrangle.isEmpty()
- Method com.aspose.barcode.barcoderecognition.Quadrangle.contains(java.awt.Point)
- Method com.aspose.barcode.barcoderecognition.Quadrangle.contains(int,int)
- Method com.aspose.barcode.barcoderecognition.Quadrangle.contains(com.aspose.barcode.barcoderecognition.Quadrangle)
- Method com.aspose.barcode.barcoderecognition.Quadrangle.contains(java.awt.Rectangle)
- Method com.aspose.barcode.barcoderecognition.Quadrangle.equals(com.aspose.barcode.barcoderecognition.Quadrangle)
- Method com.aspose.barcode.barcoderecognition.Quadrangle.equals(java.lang.Object)
- Method com.aspose.barcode.barcoderecognition.Quadrangle.op_Equality(com.aspose.barcode.barcoderecognition.Quadrangle,com.aspose.barcode.barcoderecognition.Quadrangle)
- Method com.aspose.barcode.barcoderecognition.Quadrangle.op_Inequality(com.aspose.barcode.barcoderecognition.Quadrangle,com.aspose.barcode.barcoderecognition.Quadrangle)
- Method com.aspose.barcode.barcoderecognition.Quadrangle.hashCode()
- Method com.aspose.barcode.barcoderecognition.Quadrangle.toString()
- Method com.aspose.barcode.barcoderecognition.Quadrangle.getBoundingRectangle()
- Method com.aspose.barcode.barcoderecognition.Quadrangle.clone()
- Method com.aspose.barcode.barcoderecognition.Quadrangle.equals(com.aspose.barcode.barcoderecognition.Quadrangle,com.aspose.barcode.barcoderecognition.Quadrangle)
- Field com.aspose.barcode.barcoderecognition.Quadrangle.EMPTY
- Class com.aspose.barcode.barcoderecognition.Code128ExtendedParameters
- Method com.aspose.barcode.barcoderecognition.Code128ExtendedParameters.getCode128DataPortions()
- Method com.aspose.barcode.barcoderecognition.Code128ExtendedParameters.equals(java.lang.Object)
- Method com.aspose.barcode.barcoderecognition.Code128ExtendedParameters.op_Equality(com.aspose.barcode.barcoderecognition.Code128ExtendedParameters,com.aspose.barcode.barcoderecognition.Code128ExtendedParameters)
- Method com.aspose.barcode.barcoderecognition.Code128ExtendedParameters.op_Inequality(com.aspose.barcode.barcoderecognition.Code128ExtendedParameters,com.aspose.barcode.barcoderecognition.Code128ExtendedParameters)
- Method com.aspose.barcode.barcoderecognition.Code128ExtendedParameters.hashCode()
- Method com.aspose.barcode.barcoderecognition.Code128ExtendedParameters.toString()
