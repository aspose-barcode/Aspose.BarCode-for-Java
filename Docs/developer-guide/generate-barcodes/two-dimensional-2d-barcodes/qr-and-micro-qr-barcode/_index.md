---
title: QR and Micro QR Barcode
type: docs
weight: 30
url: /java/qr-and-micro-qr-barcode/
---

## **Create a QR or Micro QR Barcode**
QR barcode is a two-dimensional barcode symbology developed in Japan. QR barcode has the following features:

- High capacity encoding of data, up to 7000 numeric digits or 4000 alphanumeric data
- Dirt and damage resistant, a maximum 30% of codewords can be restored
- Readable from all directions

Added a [QR ](https://apireference.aspose.com/barcode/java/com.aspose.barcode.generation/BarcodeParameters#getQR--)property in [BarcodeGenerator](https://apireference.aspose.com/barcode/java/com.aspose.barcode.generation/BarcodeGenerator) Class as [BarcodeParameter](https://apireference.aspose.com/barcode/java/com.aspose.barcode.generation/BarcodeParameters). This new property works as a QR / MicroQR selector. Select ForceQR (default) for standard QR symbols, Auto for MicroQR.

The following QR parameters can be set:

1. **QrECIEncoding** – Extended Channel Interpretation Identifiers. It is used to tell the barcode reader details about the used references for encoding the data in the symbol. The current implementation consists of all well-known charset encodings. Currently, it is used only for QR 2D barcode.
1. **QrEncodeMode** - QR symbology type of BarCode's encoding mode. The default mode is Auto.
1. **QrEncodeType** - QR / MicroQR selector mode. Select ForceQR for standard QR symbols, Auto for MicroQR.
1. **QrErrorLevel** - Level of Reed-Solomon error correction for QR barcode. From low to high: LevelL, LevelM, LevelQ, LevelH.
1. **QrVersion** - Version of QR Code. From Version1 to Version40 for QR code and from M1 to M4 for MicroQr. The default value is QRVersion.Auto.

[Enum QREncodeType](https://apireference.aspose.com/barcode/java/com.aspose.barcode/QREncodeType):

1. **Auto** - mode starts barcode version negotiation from MicroQR V1
1. **ForceQR** – mode starts barcode version negotiation from QR V1
1. **ForceMicroQR** - mode starts barcode version negotiation from MicroQR V1 to V4. If data cannot be encoded into MicroQR, an exception is thrown.

Added changes to Enum [QREncodeMode](https://apireference.aspose.com/barcode/java/com.aspose.barcode/QREncodeMode):

1. **Auto** - encode CodeText as is non-Unicode charset. If there is any Unicode character, the CodeText will be encoded with the value which is set in CodeTextEncoding.
1. **Bytes** - encode CodeText as plain bytes. If it detects any Unicode character, the character will be encoded as two bytes, lower byte first;
1. **Utf8BOM** - encode CodeText with UTF8 encoding with first ByteOfMark character;
1. **Utf16BEBOM** - encode CodeText with UTF8 encoding with first ByteOfMark character. It can be problems with some barcode scanners;
1. **ECIEncoding** - encode CodeText with the value set in the ECIEncoding property. It can be problems with some old (pre-2006) barcode scanners.
1. **ExtendedCodetext** - encode CodeText in Extended Channel mode which supports FNC1 first position, FNC1 second position, and multi ECI modes.
### **Encoding of a QR and Micro QR Barcode**
The micro QR code is a smaller version of the QR code standard for applications where symbol size is limited. There are 4 different versions (sizes) of Micro QR codes: the smallest is 11×11 modules; the largest can hold 35 numeric characters.
### **Error correction**
QR barcode can withstand certain damage and can still be decoded normally. This is decided by QR barcode's error correction level during encoding. There are four levels of error correction, from low to high:

- **LevelL**. Allows recovery of 7% of the code text
- **LevelM**. Allows recovery of 15% of the code text
- **LevelQ**. Allows recovery of 25% of the code text
- **LevelH**. Allows recovery of 30% of the code text
### **How to Encode QR Code**
Instantiate a BarcodeGenerator class object and set its EncodeType to QR, then set its CodeText property, QREncodeMode to auto, QREncodeType to ForceQR and set error correction level for the data you want to encode. The code example given below demonstrates how to generate a QR barcode.

{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAQRBarcode-EncodeQRCode.java" >}}
### **How to set QR version**
Aspose.BarCode allows developers to set a version of QR barcode while generating the barcode. A property **QR.Version** has been introduced in the **BarcodeGenerator** class to achieve the goal. The following code example demonstrates how to set the QR version before generating a barcode image.

{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAQRBarcode-set_QR_version.java" >}}
### **How to Encode MicroQR Code**
Instantiate a BarcodeGenerator class object and set its EncodeType to QR, then set its CodeText property, QREncodeMode to auto, QREncodeType to auto and set error correction level for the data you want to encode. The following code snippet shows you how to generate a MicroQR barcode.

{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAQRBarcode-EncodeMicroQRCode.java" >}}


## **QR Code Encoding in the ECI Mode**
ECI (Extended Channel Interpretation) enables QR Code to encode multiple character sets (e.g. Arabic, Cyrillic, Greek, Hebrew) and other data interpretations or industry-specific requirements to be represented, into one QR Code symbol.

Added the new property ECIEncoding to [BarcodeGenerator](https://apireference.aspose.com/barcode/java/com.aspose.barcode.generation/BarcodeGenerator) class. It is used to tell the barcode reader details about the used references for encoding the data in the symbol by the Extended Channel Interpretation Identifiers. The current implementation consists of all well-known charset encodings. Currently, it is used only for QR 2D barcode.

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
### **QR Code Encoding in the ECI Mode**
Instantiate a [BarcodeGenerator](https://apireference.aspose.com/barcode/java/com.aspose.barcode.generation/BarcodeGenerator) class object and set its EncodeType to QR, and set its CodeText property, QREncodeMode to ECIEncoding, QREncodeType to ForceQR, ECIEncoding to UTF8 and set error correction level for the data you want to encode. The following code example demonstrates how to generate a QR barcode.
#### **C#**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-TwoD_barcodes-basic_features-CreatingAQRBarcode-EncodeQRCodEInECIMode.java" >}}
