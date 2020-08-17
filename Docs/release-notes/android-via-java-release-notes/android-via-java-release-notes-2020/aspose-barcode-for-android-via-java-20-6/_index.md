---
title: Aspose.BarCode for Android via Java 20.6
type: docs
weight: 8
url: /java/aspose-barcode-for-android-via-java-20-6/
---

{{% alert color="primary" %}} 

This page contains release notes information for [Aspose.BarCode for Android via Java 20.6](https://downloads.aspose.com/barcode/androidjava/new-releases/aspose.barcode-for-android-via-java-20.6/).

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODEJAVA-951|Align the names of values of PatchFormat with Java-style|Enhancement|

## **Code Example**
{{< highlight java>}}
BarcodeGenerator bg = new BarcodeGenerator(EncodeTypes.PATCH_CODE, "Patch I");
bg.getParameters().getBarcode().getPatchCode().setPatchFormat(PatchFormat.US_LETTER);
BarcodeGenerator bg = new BarcodeGenerator(EncodeTypes.PATCH_CODE, "Patch I");
bg.getParameters().getBarcode().getPatchCode().setPatchFormat(PatchFormat.US_LETTER_LANDSCAPE);
{{< /highlight >}}

# **Public API and Backward Incompatible Changes**
- removed field com.aspose.barcode.generation.PatchFormat.US_Letter
- removed field com.aspose.barcode.generation.PatchFormat.US_Letter_LANDSCAPE
- added field com.aspose.barcode.generation.PatchFormat.US_LETTER
- added field com.aspose.barcode.generation.PatchFormat.US_LETTER_LANDSCAPE