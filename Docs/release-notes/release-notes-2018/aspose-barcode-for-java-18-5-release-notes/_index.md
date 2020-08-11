---
title: Aspose.BarCode for Java 18.5 - Release Notes
type: docs
weight: 80
url: /java/aspose-barcode-for-java-18-5-release-notes/
---

{{% alert color="primary" %}} 

This page contains release notes for Aspose.BarCode for Java 18.5.

{{% /alert %}} 

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-36759|Remove obsolete properties from BarCodeReader|Enhancement|
|BARCODENET-36856|GetCustomSizeBarCodeImage method is discarding check digit when generate EAN13 coded barcode|Bug|
|BARCODENET-36869|Fake recognition of MicrE13B barcodes|Bug|
## **Public API and Backward Incompatible Changes**
This section lists public API changes that were introduced in Aspose.BarCode for Java 18.5. It includes not only new and obsoleted public methods, but also a description of any changes in the behavior behind the scenes in Aspose.BarCode for Java which may affect existing code. Any behavior introduced that could be seen as a regression and modifies existing behavior is especially important and is documented here.
### **Obsolete APIs**
Following APIs are marked as obsolete. Do not use them anymore.

|**Type**|**Details**|
| :- | :- |
|Method|<p>**getAllPossibleBarCodes** has been removed from **com.aspose.barcode.barcoderecognition.BarCodeReader** class.</p><p>**RecognitionMode.MaxBarCodes** and **getIsDeniable** method can be used.</p>|
|Property|**getExpectedBarCodeCount**/**setExpectedBarCodeCount** has been removed from **com.aspose.barcode.barcoderecognition.BarCodeReader** class.|
|Property|**expectedBarCodeCount** doesn't have an influence on recognition speed.|
|Property|<p>**getOrientationHints**/**setOrientationHints** and **getOrientationHintsName** methods have been removed from **com.aspose.barcode.barcoderecognition.BarCodeReader** class.</p><p>Barcode orientation is detected automatically.</p>|
|Enum|**com.aspose.barcode.barcoderecognition.RecognitionHints** has been removed|
|Class|**com.aspose.barcode.barcoderecognition.PossibleBarCode** has been removed|

