---
title: Aspose.BarCode for PHP via Java 20.6
type: docs
weight: 8
url: /java/aspose-barcode-for-php-via-java-20-6/
---

{{% alert color="primary" %}} 

This page contains release notes information for [Aspose.BarCode for PHP via Java 20.6](https://downloads.aspose.com/barcode/phpjava/new-releases/aspose.barcode-for-php-via-java-20.6/).

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODEJAVA-951|Align the names of values of PatchFormat with Java style|Enhancement|

# **Code Example**
{{< highlight php>}}
$bg = new BarcodeGenerator(EncodeTypes::PATCH_CODE, "Patch I");
$bg->getParameters()->getBarcode()->getPatchCode()->setPatchFormat(PatchFormat::US_LETTER);
$bg = new BarcodeGenerator(EncodeTypes::PATCH_CODE, "Patch I");
$bg->getParameters()->getBarcode()->getPatchCode()->setPatchFormat(PatchFormat::US_LETTER_LANDSCAPE);
{{< /highlight >}}

# **Public API and Backward Incompatible Changes**
- removed field Generator.PatchFormat.US_Letter
- removed field Generator.PatchFormat.US_Letter_LANDSCAPE
- added field Generator.PatchFormat.US_LETTER
- added field Generator.PatchFormat.US_LETTER_LANDSCAPE