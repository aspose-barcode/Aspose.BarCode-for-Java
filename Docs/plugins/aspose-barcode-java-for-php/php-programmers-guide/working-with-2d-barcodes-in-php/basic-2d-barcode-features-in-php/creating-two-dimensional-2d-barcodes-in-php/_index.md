---
title: Creating Two Dimensional (2D) Barcodes in PHP
type: docs
weight: 50
url: /java/creating-two-dimensional-2d-barcodes-in-php/
---

## **Aspose.BarCode - Creating Two Dimensional (2D) Barcodes**
To Create Two Dimensional (2D) Barcodes using **Aspose.Barcode Java for PHP**, simply invoke **Creating2DBarcode** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$builder = new BarCodeBuilder();

$symbology=new Symbology();

$builder->setSymbologyType($symbology->Pdf417);

\# Width of each module

$builder->setxDimension(0.6);

\# Height of each module

$builder->setyDimension(1.2);

$builder->setCodeText("this is some test code text. \n Second line \n third line.");

\# Save the image to your system and set its image format to Jpeg

$builder->save($dataDir . "Creating2DBarcode.jpg");

\# Display Status

print "Created 2D Barcode Successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Creating Two Dimensional (2D) Barcodes (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/Creating2DBarcode.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/Creating2DBarcode.php)
