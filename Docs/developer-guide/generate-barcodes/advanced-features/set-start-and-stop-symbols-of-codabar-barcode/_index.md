---
title: Set Start and Stop Symbols of Codabar Barcode
type: docs
weight: 60
url: /java/set-start-and-stop-symbols-of-codabar-barcode/
---

The Codabar barcode has the following structure:

1. Start symbol at the beginning (A, B, C or D)
1. Data to be encoded
1. End symbol (A, B, C or D)

{{% alert color="primary" %}} 

Using Aspose.BarCode for Java, you can customize the start and stop symbols using the BarCodeGenerator.getParameters().getBarcode().getCodabar().setCodabarStartSymbol() and BarCodeGenerator.getParameters().getBarcode().getCodabar().setCodabarStopSymbol() methods.

{{% /alert %}} 



The code below sets A as the start symbol and D as the stop symbol for the Codabar barcode.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode-advanced_features-SetStartAndStopSymbols-SetStartAndStopSymbols.java" >}}
