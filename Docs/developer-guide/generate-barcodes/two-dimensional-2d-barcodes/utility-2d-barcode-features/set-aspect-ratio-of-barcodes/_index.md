---
title: Set Aspect Ratio of Barcodes
type: docs
weight: 30
url: /java/set-aspect-ratio-of-barcodes/
---

{{% alert color="primary" %}} 

A barcode's aspect ratio is the width: height ratio. We can control how tall or wide a barcode is using the setAspectRatio method.

{{% /alert %}} 

An aspect ratio of 3:2 means the barcode is 1.5 (or 3/2) times wider than it is tall. That is, the width of the barcode is 1.5 times larger than the height.

|![todo:image_alt_text](http://i.imgur.com/yhZhOHY.png)|
| :- |
|**Figure: Pdf417 barcode with an aspect ratio of 1.5**|
An aspect ratio of 2 means that the barcode width is two times greater than the height. Below is a Pdf417 barcode with an aspect ratio of 2.

|![todo:image_alt_text](http://i.imgur.com/0p0zPr6.png)|
| :- |
|**Figure: Pdf417 barcode with an aspect ratio of 2**|
The code below shows how to set the aspect ratio.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-utility_features-SetAspectRatioOfBarcodes-SetAspectRatioOfBarcodes.java" >}}
