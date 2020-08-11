---
title: Set Code Text for Barcode
type: docs
weight: 20
url: /java/set-code-text-for-barcode/
---

{{% alert color="primary" %}} 

A barcode is the representation of characters in the form of bars and spaces between them. To create one, simply send characters to Aspose.BarCode that can then be converted to a barcode.

{{% /alert %}} 

Aspose.BarCode has the [BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator) class that creates barcodes for different kinds of applications. All characters to be encoded in a barcode are passed to the [BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator) class's constructor method.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode-basic_features-SetCodeText-SetCodeText.java" >}}
### **Code Text vs Symbology**
Developers can pass any set of characters to the setCodeText() method but only ones appropriate to the symbology type. For example, If the SymbologyType is EncodeType.EAN13, the valid character set of EAN13 specifies numeric values and 13 digits only. If you attempt to add a shorter Codetext to it, a zero will be added. If you set a longer CodeText, the outliers are truncated.

|![todo:image_alt_text](http://i.imgur.com/xr3gd72.jpg)|
| :- |
|**Figure: When CodeText is too long or too short**|
Some symbologies of barcode accept longer CodeText. If the CodeText is longer than the image width, it is wrapped to the next line.

|![todo:image_alt_text](http://i.imgur.com/TDECkVt.jpg)|
| :- |
|**Figure: Wrapping code text**|
Humanly readable CodeText is for humans only: it does not affect the machine scanning process. Some 2D barcodes can contain a huge chunk of data, up to thousands of characters. When that's the case, you might choose not to show the code text.
