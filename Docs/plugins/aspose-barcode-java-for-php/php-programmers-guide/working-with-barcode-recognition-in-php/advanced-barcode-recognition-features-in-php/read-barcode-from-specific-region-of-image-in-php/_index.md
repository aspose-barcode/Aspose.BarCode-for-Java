---
title: Read Barcode from Specific Region of Image in PHP
type: docs
weight: 50
url: /java/read-barcode-from-specific-region-of-image-in-php/
---

## **Aspose.BarCode - Read Barcode from Specific Region of Image**
To Read Barcode from Specific Region of Image using **Aspose.Barcode Java for PHP**, simply invoke **ReadBarcodeFromSpecificRegion** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Open the stream. Read only access is enough for Aspose.BarCode to load an image.

$stream = new FileInputStream($dataDir . "test.png");

\# Create an instance of BarCodeReader class

\# and specify an area to look for the barcode

$barcode_reader_type = new BarCodeReadType();

$reader = new BarCodeReader($stream, new Rectangle(0, 0, 10, 50), $barcode_reader_type->Code39Standard);

\# TRead all barcodes in the provided area

while (java_values($reader->read())){

\# Display the codetext and symbology type of the barcode found

print "Codetext: " . (string)$reader->getCodeText() + " Symbology: " . (string)$reader->getReadType().PHP_EOL;

}

\# Close reader

$reader->close();

{{< /highlight >}}
## **Download Running Code**
Download **Read Barcode from Specific Region of Image (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/ReadBarcodeFromSpecificRegion.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/ReadBarcodeFromSpecificRegion.php)
