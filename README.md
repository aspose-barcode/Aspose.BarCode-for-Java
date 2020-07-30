# Barcode Generation & Recognition Library for Java Applications

[Aspose.BarCode for Java](https://products.aspose.com/barcode/java) is a robust and reliable barcode generation and recognition component, written in Java, it allows developers to quickly and easily add barcode generation and recognition functionality to their Java applications.

Directory | Description
--------- | -----------
[Examples](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/tree/master/Examples) | A collection of Java examples that help you learn the product features.
[Plugins](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/tree/master/Plugins) | Plugins that will demonstrate one or more features of Aspose.BarCode for Java.

<p align="center">
    <a title="Download complete Aspose.BarCode for Java source code" href="https://github.com/asposebarcode/Aspose_BarCode_Java/archive/master.zip">
    <img src="https://raw.github.com/AsposeExamples/java-examples-dashboard/master/images/downloadZip-Button-Large.png" />  </a>
</p>

## Barcode API Features

- Specify different kinds of symbologies.
- Set barcode Code text, appearance and other properties.
- Support of Checksum and Supplement Data.
- Print barcode images.

## Barcode Symbologies

**Numeric Only:** EAN13, EAN8, UPCA, UPCE, BooklandEAN, Interleaved2of5, Standard2of5, MSI, Code11, Codabar, Postnet, Planet, EAN14(SCC14), SSCC18, ITF14, Leticode, OPC\
**Alpha-Numeric:** Code128, EAN128, Code39 Extended, Code39 Standard, Code93, Extended, Code93 Standard, Matrix 2 of 5, PZN, Deutsche Post Identcode, VIN\
**2D Symbologies:** PDF417, DataMatrix, QR, Swiss QR (QR Bill)
**Encoding Only Support:** Australia Post, Aztec

## Barcode Generation & Recognition Formats

**Images:** JPEG, TIFF, PNG, BMP, GIF, EXIF

## Save BarCode Labels As

**Images:** JPEG, TIFF, PNG, BMP, GIF, EXIF, EMF, SVG

## Supported Environments

- **Microsoft Windows:** Windows Desktop & Server (x86, x64)
- **Java Runtime:** `JRE 1.6` or above
- **Java Development:** `J2SE 6.0 (1.6)`, `J2SE 7.0 (1.7)`, `J2SE 8.0 (1.8)`, or above.
- **J2ME Requirement:** Java Mobile Edition SDK 3.0

## Get Started with Aspose.BarCode for Java

Aspose hosts all Java APIs at the [Aspose Repository](https://repository.aspose.com/webapp/#/artifacts/browse/tree/General/repo/com/aspose/aspose-barcode). You can easily use Aspose.BarCode for Java API directly in your Maven projects with simple configurations. For the detailed instructions please visit [Installing Aspose.BarCode for Java from Maven Repository](https://docs.aspose.com/display/barcodejava/Installation#Installation-InstallingAspose.BarCodeforJavafromMavenRepository) documentation page.

## Scanning a Barcode from a Picture via Java

```java
// The path to the resource directory.
String dataDir = Utils.getDataDir(Barcode_Recognition.class) + "BarcodeReader/basic_features/";

// Initialize barcode reader
BarCodeReader reader = new BarCodeReader(dataDir + "CodeText.jpg");

// read barcode of type Code39Extended
for (BarCodeResult result : reader.readBarCodes()) {
    System.out.println("CodeText: " + result.getCodeText());
    System.out.println("Symbology type: " + result.getCodeType());
}
```

[Product Page](https://products.aspose.com/barcode/java) | [Docs](https://docs.aspose.com/display/barcodejava/Home) | [Demos](https://products.aspose.app/barcode/family) | [API Reference](https://apireference.aspose.com/java/barcode) | [Examples](https://github.com/aspose-barcode/Aspose.BarCode-for-Java) | [Blog](https://blog.aspose.com/category/barcode/) | [Free Support](https://forum.aspose.com/c/barcode) | [Temporary License](https://purchase.aspose.com/temporary-license)
