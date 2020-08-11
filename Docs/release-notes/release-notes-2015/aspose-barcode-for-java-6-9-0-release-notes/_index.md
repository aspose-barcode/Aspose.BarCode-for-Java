---
title: Aspose.BarCode for Java 6.9.0 Release Notes
type: docs
weight: 60
url: /java/aspose-barcode-for-java-6-9-0-release-notes/
---

Aspose.BarCode for Java has been updated to version 6.9.0 and we are pleased to announce it.

The following is a list of changes in this version of Aspose.BarCode.
## **New Features**
BARCODJAVA-33429 Port BarCodeControl to Java
BARCODENET-34136 [Public API](/pages/createpage.action?spaceKey=barcodejava&title=Public+API&linkCreation=true&fromPageId=13205626) Public API changes with new QR/MicroQR decoder
BARCODENET-34060 Add decoding support of Micro QR code
BARCODENET-34054 Add support of Micro PDF417 code
BARCODENET-25063 Is it possible to generate a barcode like the attached image?
## **Enhancements**
BARCODJAVA-33499 Incorrect recognition of Code128 code from the jpg image
BARCODJAVA-33498 Unable to recognize all Code128 bar codes from the multipage tiff file format
BARCODJAVA-33497 Incorrect recognition of Code128 barcodes in tif file format
BARCODJAVA-33492 BarCodeReader(String,BarCodeReadType) constructor throws exception for certain image
BARCODJAVA-33491 Method BarCodeReader.read() returns false for certain image file
BARCODJAVA-33490 BarCodeReader.read() throws ClassCastException for certain image
BARCODJAVA-33489 BarcodeBuilder.generateBarCodeImageInternal() throws exception for certain string
BARCODJAVA-33463 Add support of GS1 Databar coupon code generation
BARCODJAVA-33456 getCodeTextFont from BarcodeBuilder does not return what was passed into setCodeTextFont
BARCODJAVA-33451 Incorrect recognition of Code39 from the PDF file
BARCODJAVA-33441 Incorrect recognition of the QR code from PNG image
BARCODJAVA-33440 Can't recognize QR code from jpg image without orientation angle
BARCODJAVA-33437 Can't recognize QR code from jpg image
BARCODJAVA-33414 Error message when reading a specific area of tif image
BARCODJAVA-33405 Illegal Argument exception occurred when tried to recognize Interleaved2of5 barcode from Tiff image format
BARCODJAVA-33401 Raise a proper error message when exceeding code text of Pharmacode
BARCODJAVA-33400 Incorrect Pharmacode generation
BARCODJAVA-33329 Unable to recognize all Code128 bar codes from the multipage tiff file format
BARCODJAVA-33145 Cannot recognize Code128 from Jpg
BARCODJAVA-33415 Performance issue when reading Code39 froma a tif file
BARCODJAVA-33464 Can't recognize Code128 code from the JPG image
BARCODJAVA-33513 Incomplete code text when generated a Code128 barcode image
BARCODJAVA-33534 Fake recognition of MicroPdf417 in Java
BARCODENET-34153 Extra letters in codetext when checksum off
BARCODENET-34149 Stack overflow error while reading Code128 code from the tif image
BARCODENET-34122 Can't recognize QR code from a JPG file
BARCODENET-34121 ??ode128 recognizer confuses f and "FNC1".
BARCODENET-34115 Can't recognize QR codes using error correction level
BARCODENET-34113 Can't recognize PDF417 codeS from the PDF document
BARCODENET-34112 Can't recognize Code39 code form the PDF document
BARCODENET-34109 Can't recognize Ean13 code from the JPG image
BARCODENET-34108 Incorrect recognition of Code128 code from the tif image
BARCODENET-34107 Incorrect recognition of Code128 code from the tif image
BARCODENET-34106 Incorrect recognition of Code128 code from the tif image
BARCODENET-34105 Incorrect recognition of Code128 code from the tif image
BARCODENET-34104 Incorrect recognition of Code128 code from the tif image
BARCODENET-34099 Incorrect encoding punctuaion in PDF417 and MicroPDF417 barcodes
BARCODENET-34092 Can't recognize a QR code from PDF document
BARCODENET-34084 Incorrect recognition of Code128 code from the tif image
BARCODENET-34080 Can't recognize DataMatrix code from the TIF file
BARCODENET-34070 Can't recognize QR code without Orientation Hints settings
BARCODENET-34068 Incorrect recognition of Pdf417 code from the tif image
BARCODENET-34017 Recognizing two Code128 codes instead of one
BARCODENET-34003 Can't recognize all QR codes from the JPG image
BARCODENET-33990 Index out of range error message while generating a Code128 symbology
BARCODENET-33984 Can't recognize QR code from the TIF image
BARCODENET-33954 Implement recognition rotated DataMatrix
BARCODENET-33919 Can't create Code128 barcode with standard 'f' character
BARCODENET-33831 Can't recognize DataMatric code from a tif file
BARCODENET-33810 Can't recognize DataMatrix code from png image
BARCODENET-33657 Unable to recognize Datamatrix bar codes from PNG files
BARCODENET-22846 Code39 barcode could not be recognized from a scanned image
## **Usage Examples:**
BARCODENET-34136 Public API changes with new QR/MicroQR decoder
New API:

{{< highlight java >}}

  Method BarCodeReader.getDetectEncoding Method BarCodeReader.setDetectEncoding(Boolean) In case the flag is enabled the barcode engine returns Unicode text while tries to detect the encoding of the barcode. The barcode may be encoded using one of the following encodings: UTF8 BOM_UTF8 BOM_UTF16BE BOM_UTF16LE The flag is enabled by default. In case the flag is disabled the engine returns plain text without encoding detection. The flag works for QR/Micro QR at the moment. BarCodeBuilder bb = new BarCodeBuilder(); bb.setCodeText("?????"); bb.setSymbologyType(Symbology.QR); bb.setCodeTextEncoding(StandardCharsets.UTF_8); bb.save(destFile, BarCodeImageFormat.Png); BarCodeBuilder bb = new BarCodeBuilder(); bb.setCodeText("?????"); bb.setSymbologyType(Symbology.QR); bb.setCodeTextEncoding(StandardCharsets.UTF_8); bb.save(destFile, BarCodeImageFormat.Png); BarCodeReader reader = new BarCodeReader(destFile, BarCodeReadType.QR); reader.setDetectEncoding(true); if (reader.read()) { System.out.println(reader.getCodeText()); //"?????" } BarCodeBuilder bb = new BarCodeBuilder(); bb.setCodeText("?????"); bb.setSymbologyType(Symbology.QR); bb.setCodeTextEncoding(StandardCharsets.UTF_8); bb.save(destFile, BarCodeImageFormat.Png); BarCodeReader reader = new BarCodeReader(destFile, BarCodeReadType.QR); reader.setDetectEncoding(false); if (reader.read()) { System.out.println(reader.getCodeText(StandardCharsets.UTF_8)); //"?????" }

BARCODENET-34054 Add support of Micro PDF417 code

BarCodeBuilder bb = new BarCodeBuilder("Abc_D1234", Symbology.MicroPdf417); bb.setxDimension(bb.getxDimension() * 2); bb.setyDimension(bb.getyDimension() * 2); bb.save(destFolder + "34054Abc_D1234.png");

BARCODENET-25063 Is it possible to generate a barcode like the attached image?

BarCodeBuilder builder = new BarCodeBuilder("TEXT", Symbology.Code128); builder.setFilledBars(false); builder.save(destFolder + "25063.png"); 

{{< /highlight >}}
