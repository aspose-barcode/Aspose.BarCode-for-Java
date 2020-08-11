---
title: Generate Multiple MacroPdf417 Barcodes for Large or Multiple Code Text Values
type: docs
weight: 40
url: /java/generate-multiple-macropdf417-barcodes-for-large-or-multiple-code-text-values/
---

{{% alert color="primary" %}} 

Multiple MacroPdf417 barcodes can be generated if when there are multiple code text values or a very large code text value. In case of a large code text value, break it into multiple smaller code text values and generate multiple MacroPdf417 barcodes. Each generated barcode maintains the FileID and SegmentID so that they are recognized in the correct series. The last segment flag is set in the last barcode.

{{% /alert %}} 

The below code sample generates 4 MacroPdf417 barcodes for 4 different CodeText values.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-utility_features-GenerateMultipleMacroPdf417-GenerateMultipleMacroPdf417.java" >}}



The following images are generated using the above code.
**Output barcodes**

|**The first barcode**|**Barcode 2**|
| :- | :- |
|![todo:image_alt_text](http://i.imgur.com/zIdWuZ8.png)|![todo:image_alt_text](http://i.imgur.com/5ZmRr2V.png)|


|**Barcode 3**|**The last barcode**|
| :- | :- |
|![todo:image_alt_text](http://i.imgur.com/sBgY1KL.png)|![todo:image_alt_text](http://i.imgur.com/OGc2f9f.png)|

