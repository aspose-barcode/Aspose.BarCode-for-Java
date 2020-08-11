---
title: Control the Appearance of Code Text
type: docs
weight: 30
url: /java/control-the-appearance-of-code-text/
---

{{% alert color="primary" %}} 

[Aspose.BarCode](https://www.aspose.com/products/barcode/java) provides complete control over the appearance of the Code text in the barcode image. There are many settings that can be applied to the code text to customize its appearance as discussed below:

{{% /alert %}} 
### **Code Text Location**
Aspose.BarCode gives full power to developers to decide whether they want to display Code text or not. Moreover, it is also possible to customize that where to display the Code text (that is display above or below the barcode) as shown below.

|![todo:image_alt_text](http://i.imgur.com/B5FcjxY.jpg)|
| :- |
|**Figure: All possible locations of code text on the barcode image**|
BarcodeGenerator class provides [getParameters](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator#getParameters--)().[getBarcode](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BaseGenerationParameters#getBarcode--)().[getCodeTextParameters](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeParameters#getCodeTextParameters--)().[setLocation](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/CodetextParameters#setLocation-com.aspose.barcode.generation.CodeLocation-)([CodeLocation](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/CodeLocation "enum in com.aspose.barcode.generation") value) property method to set code location accepting any pre-defined value stored in the [Aspose.BarCode.Generation.CodeLocation](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/CodeLocation) enumeration, listed below.

|**Code Locations**|**Description**|
| :- | :- |
|Above|Specifies that the Code text is shown on the top of the bar code|
|Below|Specifies that the Code text is shown on the bottom of the bar code|
|None|Specifies that the Code text is hidden|
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode-basic_features-CodeTextLocation-CodeTextLocation.java" >}}
### **Code Text Foreground Color**
Developers who want to add colors to their barcodes may also change the color of Code text. Calling the setColor() method of the [getParameters](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator#getParameters--)().[getBarcode](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BaseGenerationParameters#getBarcode--)().[getCodeTextParameters](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeParameters#getCodeTextParameters--)() to any color can change the displaying text color of the code text as demonstrated below in a code snippet.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode-basic_features-CodeTextForegroundColor-CodeTextForegroundColor.java" >}}
