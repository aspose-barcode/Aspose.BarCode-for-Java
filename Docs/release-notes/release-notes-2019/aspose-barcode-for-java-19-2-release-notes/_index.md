---
title: Aspose.BarCode for Java 19.2 Release Notes
type: docs
weight: 110
url: /java/aspose-barcode-for-java-19-2-release-notes/
---

{{% alert color="primary" %}} 

This page contains release notes information for Aspose.BarCode for Java 19.2.

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-37039|Add termination by Timeout support for internal imaging and recognition filters|Enhancement|
|BARCODENET-37002|BarCodeReader exceded defined Timeout|Bug|
|BARCODENET-36680|BarCodeReader Timeout property is not working as expected|Bug|
# **Usage examples:**
**BARCODENET-37039 - Add termination by Timeout support for internal imaging and recognition filters**



BarCodeReader reader = new BarCodeReader(filename);
` `while(reader.read());
` `//other thread call code
` `reader.abort();
## **Public API and Backward Incompatible Changes**
Following members have been added:

- Method com.aspose.barcode.barcoderecognition.BarCodeReader.abort()
