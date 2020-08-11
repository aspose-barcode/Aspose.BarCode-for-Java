---
title: 1D Barcodes with Optional Exception Message in Case of Wrong Code Text
type: docs
weight: 120
url: /java/1d-barcodes-with-optional-exception-message-in-case-of-wrong-code-text/
---

{{% alert color="primary" %}} 

The BarCodeBuilder.setThrowExceptionWhenCodeTextIncorrect method is only used for 1D barcodes. It allows developers to choose what happens if the code text used when generating a barcode is not valid. There are two choices:

- Filter the code text and generate with allowed characters.
- Throw an exception.

{{% /alert %}} 

If the code text is incorrect and the value for the ThrowExceptionWhenCodeTextIncorrect property is set to true, an exception is thrown. Otherwise, the code text is corrected to match the barcode's specification, with the following exceptions:

- An exception is always thrown for the Databar symbology if the code text is incorrect.
- An exception is never thrown for AustraliaPost, SingapurePost, Code39Extended, Code93Extended, Code16K, Code128 symbologies, even if the code text is incorrect.

The code below shows how to use the method.

|![todo:image_alt_text](http://i.imgur.com/P2zeeDn.png)|
| :- |
|**Figure: Results**|

#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-Src-main-java-com-aspose-barcode-examples-technical_articles-BarCodeWithOptionalExceptionMessage-BarCodeWithOptionalExceptionMessage.java" >}}
