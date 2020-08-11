---
title: Manage X-Dimension and Y-Dimension
type: docs
weight: 20
url: /java/manage-x-dimension-and-y-dimension/
---

{{% alert color="primary" %}} 

This article explains how barcodes use the x-dimension and y-dimension, how they are measured and how they can be used in code.

{{% /alert %}} 
## **What is x-dimension?**
All barcode symbologies have unique characteristics but there are some that are common to all: x-dimension is one such. All barcodes have an x-dimension.

The x-dimension is the narrowest bar or space in the barcode. Barcode symbologies usually specify a minimum x-dimension value to ensure compatibility between reading and printing equipment used in an open system (for example, a barcode label that will be read by scanners outside the issuing company).

The x-dimension determines the barcode's density. Density refers to the amount of information that can be stored within a specific amount of space. When the x-dimension is small, the area required for each character is smaller than when the x-dimension is large and the barcode can hold more information per linear inch - it has a higher density.

Increasing the width of the narrowest element (x-dimension) increases the space required for each character and reduces the number of characters per inch. Because the resulting code is often quite large, very low-density codes are often associated with applications such as warehousing that require bar codes to be read from a significant distance (3 to 30 feet).

|![todo:image_alt_text](http://i.imgur.com/Dtl5s5N.jpg)|
| :- |
|**Figure: Different views of the same barcode with different x-dimensions**|
X-Dimension is specified in mils (one thousand of an inch) but it can also be specified in inches and millimeters. For conversion purposes, use the following ratios:

|**1 Mil**|**=**|**0.001 Inches**|
| :- | :- | :- |
|**1 Inch**|**=**|**25.4 Millimeters**|
Some sample x-dimension values are listed below in mils, inches, and millimeters.

|**Mils**|**Inches**|**Millimeters**|
| :- | :- | :- |
|4|0.004|0.1016|
|6|0.006|0.1524|
|8|0.008|0.2032|
|10|0.01|0.254|
|20|0.02|0.508|
|40|0.04|1.016|
|80|0.08|2.032|
|160|0.16|4.064|
Other barcode elements are expressed as multiples of the x-dimension. For instance, to ensure accurate scanning, most bar codes have a quiet zone – the blank margin on either side of a barcode used to tell the barcode reader where a barcode starts and stops – that is 10X wide. (That is, ten times the barcode's x-dimension.) In general, the greater the x-dimension, the easier a barcode can be scanned.
### **Aspose.BarCode and X-Dimension**
[Aspose.BarCode](http://www.aspose.com/Products/Aspose.BarCode/Api) provides get methods in the BarCodeGenerator class that allows developers to set the x-dimension of the bars in a barcode. Normally, the x-dimension is configured in Mils but Aspose.BarCode uses millimeters by default. Developers can modify the measuring unit according to their choice.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode-advanced_features-ManageXAndYDimension-setXDimension.java" >}}

|![todo:image_alt_text](http://i.imgur.com/QKzNFjR.jpg)|
| :- |
|**Figure: Output barcode**|


YDimension is not a part of BarcodeGenerator API anymore. However, developers may use BarHeight instead of YDimension if needed.
