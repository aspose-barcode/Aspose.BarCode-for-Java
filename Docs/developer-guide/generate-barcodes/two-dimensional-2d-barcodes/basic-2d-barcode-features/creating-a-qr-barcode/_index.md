---
title: Creating a QR Barcode
type: docs
weight: 40
url: /java/creating-a-qr-barcode/
---

QR is a two-dimensional barcode symbology developed in Japan. QR barcode has the following features:

- High capacity encoding of data, up to 7000 numeric digits or 4000 alphanumeric data.
- Dirt and damage resistant, a maximum 30% of codewords can be restored.
- Readable from all directions.

This article shows how to use Aspose.BarCode to

- Create a QR barcode.
- Set error correction.
- Rotate a QR barcode.

To create a QR barcode:

1. Instantiate a [BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator).
1. Call the setSymbologyType() method and pass QR.
1. Call the setCodeText() method to set the data you want to encode.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAQRBarcode-createAQRBarcode.java" >}}

|![todo:image_alt_text](http://i.imgur.com/bw46URS.png)|
| :- |
|**Figure: Output QR barcode**|
#### **Error Correction**
QR barcode can withstand a certain amount of damage and still be decoded normally. This is decided by the QR barcode's error correction level during encoding. There are four levels of error correction, from low to high:

- **LevelL**: allows recovery of 7% of the code text.
- **LevelM**: allows recovery of 15% of the code text.
- **LevelQ**: allows recovery of 25% of the code text.
- **LEVEL_H**: allows recovery of 30% of the code text.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAQRBarcode-errorCorrection.java" >}}

|![todo:image_alt_text](http://i.imgur.com/Nb0uqbL.png)|
| :- |
|**Figure: Comparison of error correction**|
#### **Rotation**
QR barcodes can be read from any direction. The following samples show valid QR barcodes with different rotation angles.
#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAQRBarcode-rotation.java" >}}

|![todo:image_alt_text](http://i.imgur.com/R5bvM2r.png)|
| :- |
|**Figure: Rotated QR barcodes**|
#### **Create QR Barcode with Logo**
Aspose.BarCode now supports generating QR barcode with logo/other images inside it. The following code snippet shows how a QR barcode can be created with logo/other images inside it.

{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAQRBarcode-QRBarcodeWithImage.java" >}}
#### **How to set QR version**
Aspose.BarCode for Java allows developers to set version of QR barcode while generating the barcode. A property **setQRVersion** has been introduced in the **BarcodeGenerator** class to achieve the goal. Following is the sample code demonstration of how to set a version of QR barcode before generating the actual barcode image.

{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAQRBarcode-set_QR_version.java" >}}
#### **QR Code Encoding in the ECI Mode**
ECI (Extended Channel Interpretation) enables QR Code to encode multiple character sets (e.g. Arabic, Cyrillic, Greek, Hebrew) and other data interpretations or industry-specific requirements to be represented, into one QR Code symbol.

Added the new property ECIEncoding to [BarcodeGenerator](https://apireference.aspose.com/java/barcode/com.aspose.barcode.generation/BarcodeGenerator) class. It is used to tell the barcode reader details about the used references for encoding the data in the symbol by the Extended Channel Interpretation Identifiers. The current implementation consists of all well-known charset encodings. Currently, it is used only for QR 2D barcode.

Enum ECIEncodings:

1. **ISO_8859_1** - ISO/IEC 8859-1 Latin alphabet No. 1 encoding. ECI Id:"\000003".
1. **ISO_8859_2** - ISO/IEC 8859-2 Latin alphabet No. 2 encoding. ECI Id:"\000004".
1. **ISO_8859_3** - ISO/IEC 8859-3 Latin alphabet No. 3 encoding. ECI Id:"\000005".
1. **ISO_8859_4** - ISO/IEC 8859-4 Latin alphabet No. 4 encoding. ECI Id:"\000006".
1. **ISO_8859_5** - ISO/IEC 8859-5 Latin/Cyrillic alphabet encoding. ECI Id:"\000007".
1. **ISO_8859_6** - ISO/IEC 8859-6 Latin/Arabic alphabet encoding. ECI Id:"\000008".
1. **ISO_8859_7** - ISO/IEC 8859-7 Latin/Greek alphabet encoding. ECI Id:"\000009".
1. **ISO_8859_8** - ISO/IEC 8859-8 Latin/Hebrew alphabet encoding. ECI Id:"\000010".
1. **ISO_8859_9** - ISO/IEC 8859-9 Latin alphabet No. 5 encoding. ECI Id:"\000011".
1. **ISO_8859_10** - ISO/IEC 8859-10 Latin alphabet No. 6 encoding. ECI Id:"\000012".
1. **ISO_8859_11** - ISO/IEC 8859-11 Latin/Thai alphabet encoding. ECI Id:"\000013".
1. **ISO_8859_13** - ISO/IEC 8859-13 Latin alphabet No. 7 (Baltic Rim) encoding. ECI Id:"\000015".
1. **ISO_8859_14** - ISO/IEC 8859-14 Latin alphabet No. 8 (Celtic) encoding. ECI Id:"\000016".
1. **ISO_8859_15** - ISO/IEC 8859-15 Latin alphabet No. 9 encoding. ECI Id:"\000017".
1. **ISO_8859_16** - ISO/IEC 8859-16 Latin alphabet No. 10 encoding. ECI Id:"\000018".
1. **Shift_JIS** - Shift JIS (JIS X 0208 Annex 1 + JIS X 0201) encoding. ECI Id:"\000020".
1. **Win1250** - Windows 1250 Latin 2 (Central Europe) encoding. ECI Id:"\000021".
1. **Win1251** - Windows 1251 Cyrillic encoding. ECI Id:"\000022".
1. **Win1252** - Windows 1252 Latin 1 encoding. ECI Id:"\000023".
1. **Win1256** - Windows 1256 Arabic encoding. ECI Id:"\000024".
1. **UTF16BE** - ISO/IEC 10646 UCS-2 (High order byte first) encoding. ECI Id:"\000025".
1. **UTF8** - ISO/IEC 10646 UTF-8 encoding. ECI Id:"\000026".
1. **US_ASCII** - ISO/IEC 646:1991 International Reference Version of ISO 7-bit coded character set encoding. ECI Id:"\000027".
1. **Big5** - Big 5 (Taiwan) Chinese Character Set encoding. ECI Id:"\000028".
1. **GB18030** - GB (PRC) Chinese Character Set encoding. ECI Id:"\000029".
1. **EUC_KR** - Korean Character Set encoding. ECI Id:"\000030".

Instantiate a BarcodeGenerator class object and set its EncodeType to be the QR, and set its CodeText property, QREncodeMode to ECIEncoding, QREncodeType to ForceQR, ECIEncoding to UTF8 and set error correction level for the data you want to encode. Below is an example to generate a QR barcode with Encoding QR Code In ECI Mode.



{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAQRBarcode-EncodeQRCodEInECIMode.java" >}}
