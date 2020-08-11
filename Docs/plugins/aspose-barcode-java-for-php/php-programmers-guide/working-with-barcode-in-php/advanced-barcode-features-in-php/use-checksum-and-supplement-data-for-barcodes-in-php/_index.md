---
title: Use Checksum and Supplement Data for Barcodes in PHP
type: docs
weight: 60
url: /java/use-checksum-and-supplement-data-for-barcodes-in-php/
---

## **Aspose.BarCode - Use Checksum and Supplement Data for Barcodes**
To Use Checksum and Supplement Data for Barcodes using **Aspose.Barcode Java for PHP**, simply invoke **UseChecksumAndSupplementData** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$bb = new BarCodeBuilder();

\# Set the Code text for the barcode

$bb->setCodeText("1234567");

\# Set the symbology type to Code39

$symbology=new Symbology();

$bb->setSymbologyType($symbology->Code39Standard);

\# Make the checksum to be visible on the barcode

$enableChecksum=new EnableChecksum();

$bb->setEnableChecksum($enableChecksum->Default);

\# Save the image to disk in JPG format

$bb->save($dataDir . "barcode.jpg");

\# Print message

print "Barcode image generated successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Use Checksum and Supplement Data for Barcodes (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/UseChecksumAndSupplementData.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/UseChecksumAndSupplementData.php)
