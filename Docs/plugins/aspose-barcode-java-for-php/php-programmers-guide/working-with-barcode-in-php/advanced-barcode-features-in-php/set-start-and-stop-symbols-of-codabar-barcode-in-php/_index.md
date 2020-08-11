---
title: Set Start and Stop Symbols of Codabar Barcode in PHP
type: docs
weight: 50
url: /java/set-start-and-stop-symbols-of-codabar-barcode-in-php/
---

## **Aspose.BarCode - Set Start and Stop Symbols of Codabar Barcode**
To Set Start and Stop Symbols of Codabar Barcode using **Aspose.Barcode Java for PHP**, simply invoke **StartStopSymbol** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Create instance of BarCodeBuilder, specify codetext and symbology in the constructor

$symbology=new Symbology();

$builder = new BarCodeBuilder("$ 123:50", $symbology->Codabar);

\# Set the codabar start symbol to A

$codabarSymbol=new CodabarSymbol();

$builder->setCodabarStartSymbol($codabarSymbol->A);

\# Set the codabar stop symbol to D

$builder->setCodabarStopSymbol($codabarSymbol->D);

\# Save the image to disk in PNG format

$builder->save($dataDir . "barcode.out.png");

\# Print message

print "Barcode image generated successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Set Start and Stop Symbols of Codabar Barcode (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/StartStopSymbol.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/StartStopSymbol.php)
