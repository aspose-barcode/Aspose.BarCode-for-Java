---
title: Aspose.BarCode for PHP via Java 20.5
type: docs
weight: 9
url: /java/aspose-barcode-for-php-via-java-20-5/
---

{{% alert color="primary" %}} 

This page contains release notes information for [Aspose.BarCode for PHP via Java 20.5](https://downloads.aspose.com/barcode/phpjava/new-releases/aspose.barcode-for-php-via-java-20.5/).

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-37197|Option to set Barcode value while creating barcode of type PatchCode|Enhancement|
|BARCODEJAVA-928|Aspose.Barcode for Java 20.4-jdk17 gives exception on recognition|Bug|
|BARCODENET-37413|Investigate improvements to MBase 1D processor to improve recognition quality|Bug|
|BARCODENET-37417|Aspose.Barcode 20.01 Unexpected recognition on QR-Code with no image borders as DataLogic2of5|Bug|
|BARCODENET-37422|Unable to read barcodes from TIFF|Bug|
|BARCODENET-37425|Unable to read specific barcodes|Bug|
|BARCODENET-37435|Arabic text raises System.NullReferenceException|Bug|

# **Public API and Backward Incompatible Changes**
- Added function Generator.BarcodeParameters.getPatchCode():PatchCodeParameters
- Added class Generator.PatchCodeParameters
- Added function Generator.PatchCodeParameters.getExtraBarcodeText()
- Added function Generator.PatchCodeParameters.setExtraBarcodeText($value)
- Added function Generator.PatchCodeParameters.getPatchFormat()
- Added function Generator.PatchCodeParameters.setPatchFormat($value)
- Added function Generator.PatchCodeParameters.toString()
- Added class Generator.PatchFormat
- Added const Generator.PatchFormat.PATCH_ONLY
- Added const Generator.PatchFormat.A4
- Added const Generator.PatchFormat.A4_LANDSCAPE
- Added const Generator.PatchFormat.US_Letter
- Added const Generator.PatchFormat.US_Letter_LANDSCAPE