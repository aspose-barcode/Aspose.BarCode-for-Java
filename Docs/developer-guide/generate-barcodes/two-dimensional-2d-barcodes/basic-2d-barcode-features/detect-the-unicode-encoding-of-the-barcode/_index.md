---
title: Detect the Unicode Encoding of the Barcode
type: docs
weight: 60
url: /java/detect-the-unicode-encoding-of-the-barcode/
---

{{% alert color="primary" %}} 

Aspose.BarCode API allows developers to detect the Unicode encoding.

{{% /alert %}} 

The flag works for QR/Micro QR at the moment.
### **How to Detect the Unicode Encoding**
In case, the detect encoding flag is enabled the barcode engine returns Unicode text while tries to detect the encoding of the barcode. The barcode may be encoded using one of the following encoding standards:

- UTF8
- BOM_UTF8
- BOM_UTF16BE
- BOM_UTF16LE

The flag is enabled by default. In case the flag is disabled the engine returns plain text without encoding detection.

The following code snippet encodes & decodes the barcode.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-DetectTheUnicodeEncoding-DetectTheUnicodeEncoding.java" >}}
