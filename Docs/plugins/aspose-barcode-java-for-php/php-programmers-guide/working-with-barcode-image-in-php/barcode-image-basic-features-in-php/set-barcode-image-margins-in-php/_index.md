---
title: Set Barcode Image Margins in PHP
type: docs
weight: 50
url: /java/set-barcode-image-margins-in-php/
---

## **Aspose.BarCode - Set Barcode Image Margins**
To Set Barcode Image Margins using **Aspose.Barcode Java for PHP**, simply invoke **SetBarcodeImageMargins** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$symbology = new Symbology();

$bb = new BarCodeBuilder("12345678", $symbology->Code128);

\# sets the top margin

$bb->getMargins()->setTop(4);

\# sets the bottom margin

$bb->getMargins()->setBottom(5);

\# sets the left margin

$bb->getMargins()->setLeft(2);

\# sets the right margin

$bb->getMargins()->setRight(3);

$bb->save($dataDir . "SetBarcodeImageMargins.jpg");

\# Display Status.

print "Set Barcode Image Margins, please check the output file.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Set Barcode Image Margins (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageMargins.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageMargins.php)
