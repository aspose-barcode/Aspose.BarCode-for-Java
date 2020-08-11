---
title: Generate Barcode with Empty Bars in PHP
type: docs
weight: 30
url: /java/generate-barcode-with-empty-bars-in-php/
---

## **Aspose.BarCode - Generate Barcode with Empty Bars**
To Generate Barcode with Empty Bars using **Aspose.Barcode Java for PHP**, simply invoke **GenerateBarcodeWithEmptyBars** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$symbology = new Symbology();

$bb = new BarCodeBuilder("TEXT", $symbology->Code128);

\# Set the FilledBars property to false

$bb->setFilledBars(false);

$bb->save($dataDir . "GenerateBarcodeWithEmptyBars.jpg");

\# Display Status.

print "Generate Barcode With Empty Bars, please check the output file.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Generate Barcode with Empty Bars (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/GenerateBarcodeWithEmptyBars.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/GenerateBarcodeWithEmptyBars.php)
