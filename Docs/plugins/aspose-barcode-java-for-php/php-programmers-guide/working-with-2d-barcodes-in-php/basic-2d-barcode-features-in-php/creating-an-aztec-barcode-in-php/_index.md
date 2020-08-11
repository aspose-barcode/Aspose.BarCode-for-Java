---
title: Creating an Aztec Barcode in PHP
type: docs
weight: 20
url: /java/creating-an-aztec-barcode-in-php/
---

## **Aspose.BarCode - Creating an Aztec Barcode**
To Create an Aztec Barcode using **Aspose.Barcode Java for PHP**, simply invoke **CreatingAztecBarcode** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$builder = new BarCodeBuilder();

$symbology=new Symbology();

$builder->setSymbologyType($symbology->Aztec);

$builder->setCodeText("1234567890");

\# Save the image to your system and set its image format to Jpeg

$builder->save($dataDir . "CreatingAztecBarcode.jpg");

\# Display Status

print "Created Aztec Barcode Successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Creating an Aztec Barcode (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingAztecBarcode.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingAztecBarcode.php)
