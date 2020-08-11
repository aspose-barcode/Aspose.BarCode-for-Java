---
title: Colorize any Part of the Barcode Image
type: docs
weight: 20
url: /java/colorize-any-part-of-the-barcode-image/
---

{{% alert color="primary" %}} 

Normally, barcodes are generated and printed in black and white. With Aspose.BarCode, developers are not constrained to black and white but can create barcodes in any color they like. Each barcode element can be assigned its own color as explained in this article.

{{% /alert %}} 

Keeping this possible requirement in mind, [Aspose.BarCode](https://apireference.aspose.com/java/barcode/) allows developers to change the colors of:

- Barcode
- Code text
- Barcode background
- Barcode border
- Caption

The [BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator) class has the following methods that are used to colorize different parts of a barcode as follows:

- The setForeColor() method to assign any color to the barcode.
- The getCodeTextParameters().setColor() method to assign any color to the code text of the barcode.
- The setBackColor() method to set the background color of the barcode.
- The getBorder().setColor() method to set the border color of the barcode.

Developers can select any color using Java.awt.Color (part of Java) and then pass that color to any of the methods mentioned above.

|![todo:image_alt_text](http://i.imgur.com/4WlZwvs.jpg)|
| :- |
|**Figure: Output barcode: blue and red on yellow, with the grey border**|
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_image-basic_features-ColorizeBarcodeImage-ColorizeBarcodeImage.java" >}}
#### **Gallery**
Here are some barcode samples based on extreme color settings.

|![todo:image_alt_text](http://i.imgur.com/VU9ewax.jpg)|
| :- |
|**Figure: Barcode color gallery: yellow and green on black**|


|![todo:image_alt_text](http://i.imgur.com/ig1M4Jo.jpg)|
| :- |
|**Figure: Barcode color gallery: cyan on white**|


|![todo:image_alt_text](http://i.imgur.com/u7UpVRT.jpg)|
| :- |
|**Figure: Barcode color gallery: green and red on white, with grey border**|

