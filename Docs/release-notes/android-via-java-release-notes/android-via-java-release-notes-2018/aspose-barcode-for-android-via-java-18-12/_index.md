---
title: Aspose.BarCode for Android via Java 18.12
type: docs
weight: 10
url: /java/aspose-barcode-for-android-via-java-18-12/
---

{{% alert color="primary" %}} 

This page contains release notes information for Aspose.BarCode for Android via Java 18.12.

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-37024|Not able to read dotted barcodes from TIFF images |Bug|
|BARCODEJAVA-623|Exception occurs for tests related to xml serialization |Bug|
|BARCODEJAVA-625|Unable to Read Complete BarCode Text|Bug|
## **Usage examples:**
**BARCODENET-37024 - Not able to read dotted barcodes from TIFF images**

Usage of the new DecodeType fields:



|<p>String fileName = "Scenario-1_page2.tiff";</p><p>BarCodeReader reader = new BarCodeReader(fileName, new MultyDecodeType(DecodeType.ALL_SUPPORTED_TYPES));</p><p>while (reader.read())</p><p>{</p><p>` `System.out.println(reader.getCodeText());</p><p>}</p>|
| :- |


**BARCODEJAVA-625 - Unable to Read Complete BarCode Text**

Usage of the new DecodeType fields:



|<p>String fileName = "test.jpg";</p><p>String expectedText = "690458960500";</p><p>String expectedCheckSum = "4";</p><p>BaseDecodeType expectedDecodeType = DecodeType.EAN_13;</p><p>BarCodeReader reader = new BarCodeReader(fileName);</p><p>reader.setChecksumValidation(ChecksumValidation.ON);</p><p>reader.read();</p><p>System.out.println(reader.getCodeText());</p><p>System.out.println(reader.getCheckSum());</p><p>System.out.println(reader.getCodeType());</p>|
| :- |

