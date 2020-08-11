---
title: Wide Narrow Ratio
type: docs
weight: 40
url: /java/wide-narrow-ratio/
---

{{% alert color="primary" %}} 

Some barcode symbologies like Code39, Interleaved2of5 and Standard2of5 allow you to set the ratio of wide to narrow elements (bar or space) of the barcode. A barcode bar can be a printed black bar or the white space between the bars. Conceptually, a barcode is composed of two kinds of bars: black bars and white bars.

{{% /alert %}} 

In such barcodes, some bars are wider than others. For example, in Code39 (sometimes called Code 3 from 9) symbology, each character is represented by 9 bars - 3 of which are wider than the others and the ratio of the bar widths can range from 2.2:1 to 3:1. To read the encoded information in a barcode reliably, the decoder must be able to differentiate between wide and narrow bars.

To manage bar widths in a barcode, wide to narrow ratio is configured.

[Aspose.BarCode](https://apireference.aspose.com/java/barcode) allows developers to use [setWideNarrowRatio()](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeParameters#setWideNarrowRatio-float-) method of a barcode class to control the ratio of wide and narrow elements in the barcode. To specify the ratio, pass a float value to the setWideNarrowRatio() method.

|![todo:image_alt_text](http://i.imgur.com/DwBiPLn.jpg)|
| :- |
|**Figure: Different views of a barcode with different ratios**|
Wide to the narrow ratio for Interleaved2of5 and Standard2of5 symbologies is configured in the same way as that of Code39.

A complete example is given below to demonstrate the use of the WideNarrowRatio property.

|![todo:image_alt_text](http://i.imgur.com/Fu1FO4q.jpg)|
| :- |
|**Figure: The output barcode image generated after example code execution**|
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode-advanced_features-WideNarrowRatio-WideNarrowRatio.java" >}}
