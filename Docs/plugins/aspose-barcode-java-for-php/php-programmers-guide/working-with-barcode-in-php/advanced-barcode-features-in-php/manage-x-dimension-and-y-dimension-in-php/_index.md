---
title: Manage X-Dimension and Y-Dimension in PHP
type: docs
weight: 30
url: /java/manage-x-dimension-and-y-dimension-in-php/
---

## **Aspose.BarCode - Manage X-Dimension and Y-Dimension**
To Manage X-Dimension and Y-Dimension using **Aspose.Barcode Java for PHP**, simply invoke **ManageDimension** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$bb = new BarCodeBuilder();

\# Set up code text (data to be encoded)

$bb->setCodeText("1234567");

\# Set the symbology type to Code128

$symbology=new Symbology();

$bb->setSymbologyType($symbology->Code128);

\# Save the image to file

$bb->save($dataDir . "barcode.jpg");

\# Set the x-dimension for the bars of the barcode

$bb->setxDimension(0.5);

\# Save the image to file

$bb->save($dataDir . "barcodeXDimensionChanged.jpg");


\# Instantiate barcode object

$bb1 = new BarCodeBuilder();

\# Set the code text of the barcode

$bb1->setCodeText("12345678");

\# Set the symbology type to code128

$bb1->setSymbologyType($symbology->Pdf417);

\# Set the x-dimension for the bars of the barcode

$bb1->setxDimension(0.5);

\# Save the image to file

$bb1->save($dataDir . "barcodeYDimensionChanged.jpg");

\# Display Status.

print "BarCodes with different dimensions have been created successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Manage X-Dimension and Y-Dimension (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/ManageDimension.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/ManageDimension.php)
