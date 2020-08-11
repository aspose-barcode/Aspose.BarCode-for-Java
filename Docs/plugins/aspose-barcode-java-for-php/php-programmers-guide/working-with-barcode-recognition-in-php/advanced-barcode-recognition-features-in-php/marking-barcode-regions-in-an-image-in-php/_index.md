---
title: Marking Barcode Regions in an Image in PHP
type: docs
weight: 40
url: /java/marking-barcode-regions-in-an-image-in-php/
---

## **Aspose.BarCode - Marking Barcode Regions in an Image**
To Marke Barcode Regions in an Image using **Aspose.Barcode Java for PHP**, simply invoke **MakingBarcodeRegions** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # initialize barcode reader

$img = $dataDir . "barcode.jpg";

$barcode_reader_type = new BarCodeReadType();

$reader = new BarCodeReader($img, $barcode_reader_type->Code39Standard);

\# Try to recognize all possible barcodes in the image

while (java_values($reader->read())){

\# Display the symbology type

print "BarCode Type: " . (string)$reader->getReadType().PHP_EOL;

\# Display the codetext

print "BarCode CodeText: " . (string)$reader->getCodeText().PHP_EOL;

\# Get the barcode region

$region = $reader->getRegion();

if ($region != null){

\# Initialize an object of type BufferedImage to get the Graphics object

$imageIO=new ImageIO();

//                $file=new File();

$bufferedImage = $imageIO -> read(new File($img));

\# Initialize graphics object from the image

$g = $bufferedImage -> getGraphics();

$color = new Color();

\# Initialize paint object

$p = new GradientPaint(0, 0, $color->red, 100, 100, $color->pink, true);

$region->drawBarCodeEdges($g, $color->RED);

\# Save the image

$imageIO->write($bufferedImage, "png", new File($dataDir . "Code39StdOut.png"));

}

}

\# Close reader

$reader->close();

{{< /highlight >}}
## **Download Running Code**
Download **Marking Barcode Regions in an Image (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/MakingBarcodeRegions.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/MakingBarcodeRegions.php)
