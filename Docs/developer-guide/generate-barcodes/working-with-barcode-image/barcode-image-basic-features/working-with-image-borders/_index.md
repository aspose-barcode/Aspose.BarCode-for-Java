---
title: Working with Image Borders
type: docs
weight: 10
url: /java/working-with-image-borders/
---

{{% alert color="primary" %}} 

This article demonstrates how [Aspose.BarCode](https://apireference.aspose.com/java/barcode) allows developers to handle image borders.

{{% /alert %}} 
### **Border Width**
A very simple but handy feature is to set the border width of the barcode image. The border can be made thick or thin by setPixels() method and calling with the getParameters().getBorder().getWidth() method of the [BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator) class.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_image-basic_features-BarcodeImageBorders-SetBorderWidth.java" >}}
### **Enable Image Border**
Developers may also decide whether to show or hide an image border by passing true to the setBorderVisible() method of the BarcodeGenerator class.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_image-basic_features-BarcodeImageBorders-EnableImageBorder.java" >}}



A complete example that handles barcode image borders is given below. The output barcode is shown below.

**Output barcode** 

![todo:image_alt_text](working-with-image-borders_1.png)


#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_image-basic_features-BarcodeImageBorders-BarcodeImageBorders.java" >}}
