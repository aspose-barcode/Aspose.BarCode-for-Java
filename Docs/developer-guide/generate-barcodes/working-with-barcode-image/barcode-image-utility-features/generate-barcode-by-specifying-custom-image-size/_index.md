---
title: Generate Barcode by Specifying Custom Image Size
type: docs
weight: 40
url: /java/generate-barcode-by-specifying-custom-image-size/
---

{{% alert color="primary" %}} 

Aspose.BarCode for Java automatically adjusts the image size (width and height) according to the generated barcode's size. The image size can also be controlled by specifying the image height and width using the getImageHeight() and getImageWidth() methods. setAutoSizeMode() should be set to AutoSizeMode.NEAREST to customize the image size.

{{% /alert %}} 
## **Generate Barcode With Custom Image Size**
The example below generates a Code39Standard barcode with customized height and width.

|![todo:image_alt_text](http://i.imgur.com/kFN7Jpe.png)|
| :- |
|**Figure: Output barcode**|
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_image-utility_features-BarcodeCustomSize-BarcodeCustomSize.java" >}}
## **Get Minimum BarCode Size**
The minimum BarCode Height and width are 1 millimeter by default. Following is the simple code demonstration.

{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_image-utility_features-GetMinimumBarCodeSize-GetMinimumBarCodeSize.java" >}}
