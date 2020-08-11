---
title: Manage the Barcode Caption
type: docs
weight: 40
url: /java/manage-the-barcode-caption/
---

{{% alert color="primary" %}} 

Sometimes, developers need to display extra information on the barcode image, a caption. With [Aspose.BarCode](https://www.aspose.com/products/barcode/java), the caption can be placed above or below the barcode.

|![todo:image_alt_text](http://i.imgur.com/sWyENdD.jpg)|
| :- |
It is also possible to customize the text, alignment, font, color and space of the caption.

{{% /alert %}}

There are two methods, getCaptionAbove() and getCaptionBelow() to set the caption by calling with [getParameters](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator#getParameters--)().

Find below the example of barcode captions.

|![todo:image_alt_text](http://i.imgur.com/MhckkA2.jpg)|
| :- |
|**Figure: A barcode image generated after example code execution**|


|![todo:image_alt_text](http://i.imgur.com/pFG99la.png)|
| :- |
|**Figure: Resulting barcode rendering to Graphics object**|
### **Customizing the Caption**
Continuing the sample above, the following sample shows how to set the caption font and color.

|![todo:image_alt_text](http://i.imgur.com/hUZYAFk.jpg)|
| :- |
|**Figure: Customized caption**|
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode-basic_features-BarcodeCaption-BarcodeCaption.java" >}}
