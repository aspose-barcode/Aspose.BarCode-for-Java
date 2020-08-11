---
title: Creating an Aztec Barcode
type: docs
weight: 50
url: /java/creating-an-aztec-barcode/
---

Aztec is a square grid, high density, two-dimensional barcode symbology that can encode up to 3,000 alphanumeric characters. 
This article explains how to

1. Create an Aztec barcode.
1. Set error correction.

To create an Aztec barcode:

1. Instantiate a [BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator).
1. Call the setSymbologyType() method and pass Symbology.AZTEC.
1. Call the setCodeText() method to set the data you want to encode.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAnAztecBarcode-createAnAztecBarcode.java" >}}

|![todo:image_alt_text](http://i.imgur.com/0PCmJzY.png)|
| :- |
|**Figure: Output Aztec barcode**|
#### **Error Correction**
An Aztec barcode allows code recovery when the barcode image is partly damaged. The error correction level ranges from 10% to 90%.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAnAztecBarcode-errorCorrection.java" >}}

|![todo:image_alt_text](http://i.imgur.com/DmUG1B4.png)|
| :- |
|**Figure: Comparison of error correction levels**|

