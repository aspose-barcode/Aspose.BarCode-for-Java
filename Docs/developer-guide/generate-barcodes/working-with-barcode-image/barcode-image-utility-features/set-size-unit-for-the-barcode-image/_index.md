---
title: Set Size Unit for the Barcode Image
type: docs
weight: 20
url: /java/set-size-unit-for-the-barcode-image/
---

{{% alert color="primary" %}} 

To change the measuring unit of a barcode image, Aspos.BarCode provides set and get methods for each Unit.

{{% /alert %}} 

The pre-defined measuring units are here:

|**Measuring Units**|**Description**|
| :- | :- |
|Document|Specifies the document unit (1/300 inch) as the unit of measure|
|Inch|Specifies the inch as the unit of measure|
|Millimeter|Specifies the millimeter as the unit of measure|
|Pixel|Specifies a device pixel as the unit of measure|
|Point|Specifies a printer's point (1/72 inch) as the unit of measure|
The default size measurement unit for barcode images is millimeter.

The example below changes the bar height of the barcode but the measuring unit being used for the bar height is Point.

|![todo:image_alt_text](http://i.imgur.com/yAiVqPE.jpg)|
| :- |
|**Figure: Barcode with customized bar height in points**|
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_image-utility_features-SetSizeUnitForBarcodeImage-SetSizeUnitForBarcodeImage.java" >}}
