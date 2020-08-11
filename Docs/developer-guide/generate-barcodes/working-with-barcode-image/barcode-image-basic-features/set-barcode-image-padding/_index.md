---
title: Set Barcode Image Padding
type: docs
weight: 50
url: /java/set-barcode-image-padding/
---

{{% alert color="primary" %}} 

This article demonstrates how [Aspose.BarCode](https://apireference.aspose.com/java/barcode) allows developers to handle image padding area.

{{% /alert %}} 

The padding is the area between the border and the area captured by barcode bars and code text. Developers can set the padding by calling the getPadding() method provided under [BaseGenerationParameters ](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BaseGenerationParameters)class of BarcodeGenerator class. The getPadding() method takes an instance of the [Padding ](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/Padding)class that provides the getLeft(), getRight(), getTop() and getBottom() methods used to set padding by further calling setPixels() method.

All concepts about border margins can be visualised from the figure below:

|![todo:image_alt_text](set-barcode-image-padding_1.jpg)|
| :- |
|**Figure: Graphical demonstration padding**|

The following code snippet shows you how to set the padding for barcode image.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_image-basic_features-BarcodeImageMargins-BarcodeImageMargins.java" >}}
### **Combining Various Settings**
The following picture explains how various settings work together.

|![todo:image_alt_text](http://i.imgur.com/BTFaeji.png)|
| :- |
|**Figure: Barcode**|


|![todo:image_alt_text](set-barcode-image-padding_2.png)|
| :- |
|**Figure: Graphical demonstration of image margins**|

