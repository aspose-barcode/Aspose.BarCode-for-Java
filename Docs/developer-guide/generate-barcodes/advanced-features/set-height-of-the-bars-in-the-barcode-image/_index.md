---
title: Set Height of the Bars in the Barcode Image
type: docs
weight: 10
url: /java/set-height-of-the-bars-in-the-barcode-image/
---

{{% alert color="primary" %}} 

To control the height of a barcode's bars, Aspose.BarCode's [BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator) has the getBarHeight() method. The getBarHeight() method takes a float value to set the bar height and its measuring unit is millimeter by default.

{{% /alert %}} 

|![todo:image_alt_text](http://i.imgur.com/KjGYQXT.jpg)|
| :- |
|**Figure: A barcode with different bar heights**|
### **Graphics Unit**
To change the measuring unit of the bar height, use any of the following:

|**Measuring Units**|**Description**|
| :- | :- |
|[setDocument()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/Unit#setDocument\(\))|Specifies the document unit (1/300 inch) as the unit of measure|
|[setInches()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/Unit#setInches\(\))|Specifies the inch as the unit of measure|
|[setMillimeters()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/Unit#setMillimeters\(\))|Specifies the millimeter as the unit of measure|
|[setPixels()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/Unit#setPixels\(\))|Specifies a device pixel as the unit of measure|
|[setPoints()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/Unit#setPoint\(\))|Specifies a printer's point (1/72 inch) as the unit of measure|
The image below shows the output from the code in this example.

|![todo:image_alt_text](http://i.imgur.com/ybKZlvL.jpg)|
| :- |
|**Figure: The output barcode image**|
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode-advanced_features-SetBarsHeight-SetBarsHeight.java" >}}



In two dimensional barcodes, set getxDimension() decides each module's width.

|**xDimension = 6**|
| :- |
|![todo:image_alt_text](http://i.imgur.com/t1R6u54.jpg)|
The following code snippet shows the implementation of xDimension.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode-advanced_features-ManageXAndYDimension-setXDimension.java" >}}
