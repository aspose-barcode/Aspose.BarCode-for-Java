---
title: Creating a Pdf417 Barcode in PHP
type: docs
weight: 30
url: /java/creating-a-pdf417-barcode-in-php/
---

## **Aspose.BarCode - Creating a Pdf417 Barcode**
To Create a Pdf417 Barcode using **Aspose.Barcode Java for PHP**, simply invoke **CreatingPdf417Barcode** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$builder = new BarCodeBuilder();

$symbology=new Symbology();

$builder->setSymbologyType($symbology->Pdf417);

$builder->setCodeText("12345");

\# Save the image to your system and set its image format to Jpeg

$builder->save($dataDir . "CreatingPdf417Barcode.jpg");

\# Display Status

print "Created Pdf417 Barcode Successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Creating a Pdf417 Barcode (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingPdf417Barcode.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingPdf417Barcode.php)
