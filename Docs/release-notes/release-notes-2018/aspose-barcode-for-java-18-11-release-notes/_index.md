---
title: Aspose.BarCode for Java 18.11 Release Notes
type: docs
weight: 20
url: /java/aspose-barcode-for-java-18-11-release-notes/
---

{{% alert color="primary" %}} 

This page contains release notes information for Aspose.BarCode for Java 18.11.

{{% /alert %}} 
## **New Features**
- Bring enums names into line with Java naming conventions
- Add new barcode subsets to DecodeType
- Not able to detect the barcode
- Improve the recognition of a 3D-distorted and rotated 2D-barcodes
- Improve the recognition of a 3D-distorted QR code
- Add minor fixes to BarCodeReader
- GS1 DataBar Expanded Stacked barcode did not pass GS1 Canda barcode verification
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODEJAVA-607|Bring enums names into line with Java naming conventions|New Feature|
|BARCODENET-37019|Add new barcode subsets to DecodeType|New Feature|
|BARCODENET-36945|Not able to detect the barcode|New Feature|
|BARCODENET-34367|Improve the recognition of a 3D-distorted and rotated 2D-barcodes|New Feature|
|BARCODENET-36389|Improve the recognition of a 3D-distorted QR code|New Feature|
|BARCODENET-37020|Add minor fixes to BarCodeReader|New Feature|
|BARCODEJAVA-555|GS1 DataBar Expanded Stacked barcode did not pass GS1 Canda barcode verification|New Feature|
|BARCODENET-37010|Barcode CODE39 reading problem|Bug|
|BARCODENET-37000|Barcode Detection does not constrain to the defined region.|Bug|
|BARCODENET-37028|Add fix to MicrE13B for safe bitmap access|Bug|
|BARCODEJAVA-611|Obfuscated jar throws unreadable exception "class com.aspose.barcode.internal.cy.d: Wrong filename passed"|Bug|
|BARCODEJAVA-608|BarCodeGenerator throws NullPointerException exception|Bug|
|BARCODEJAVA-524|Not able to generate DATABAR_EXPANDED_STACKED with 7 rows|Bug|
|BARCODEJAVA-598|SetLicense Problem with Open JDK 11|Bug|
# **Usage examples:**
**BARCODENET-37019 - Add new barcode subsets to DecodeType**

Usage of the new DecodeType fields:

|<p>BarCodeReader reader = **new** BarCodeReader(folder + "Code11.png", DecodeType.TYPES_1D);</p><p>**while** (reader.read())</p><p>{</p><p>` `String codeType = reader.getCodeType().toString();</p><p>` `String codeText = reader.getCodeText();</p><p>` `System.out.println(codeType + ", " + codeText);</p><p>}</p><p>result: Code11, 012345</p><p> </p><p>BarCodeReader reader = **new** BarCodeReader(folder + "Postnet.png", DecodeType.POSTAL_TYPES);</p><p>**while** (reader.read())</p><p>{</p><p>` `String codeType = reader.getCodeType().toString();</p><p>` `String codeText = reader.getCodeText();</p><p>` `System.out.println(codeType + ", " + codeText);</p><p>}</p><p>result: Postnet, 012345</p><p> </p><p>BarCodeReader reader = **new** BarCodeReader(folder + "GS1Code128", DecodeType.MOST_COMMON_TYPES);</p><p>**while** (reader.read())</p><p>{</p><p>` `String codeType = reader.getCodeType().toString();</p><p>` `String codeText = reader.getCodeText();</p><p>` `System.out.println(codeType + ", " + codeText);</p><p>}</p><p>result : GS1Code128, 012345</p>|
| :- |

**BARCODEJAVA-607 - Bring enums names into line with Java naming conventions**

Use following names of constants:

|<p>com.aspose.barcode.AztecSymbolMode.AUTO</p><p>com.aspose.barcode.AztecSymbolMode.COMPACT</p><p>com.aspose.barcode.AztecSymbolMode.FULL_RANGE</p><p>com.aspose.barcode.AztecSymbolMode.RUNE</p><p>com.aspose.barcode.BarcodeClassifications.NONE</p><p>com.aspose.barcode.BarcodeClassifications.TYPE_1D</p><p>com.aspose.barcode.BarcodeClassifications.TYPE_2D</p><p>com.aspose.barcode.BarcodeClassifications.POSTAL</p><p>com.aspose.barcode.BarcodeClassifications.DATABAR</p><p>com.aspose.barcode.BarcodeClassifications.COUPON</p><p>com.aspose.barcode.BarCodeImageFormat.BMP</p><p>com.aspose.barcode.BarCodeImageFormat.GIF</p><p>com.aspose.barcode.BarCodeImageFormat.JPEG</p><p>com.aspose.barcode.BarCodeImageFormat.PNG</p><p>com.aspose.barcode.BarCodeImageFormat.TIFF</p><p>com.aspose.barcode.BarCodeImageFormat.TIFF_IN_CMYK</p><p>com.aspose.barcode.BarCodeImageFormat.EMF</p><p>com.aspose.barcode.BarCodeImageFormat.SVG</p><p>com.aspose.barcode.BuildVersionInfo.ASSEMBLY_VERSION</p><p>com.aspose.barcode.BuildVersionInfo.PRODUCT</p><p>com.aspose.barcode.BuildVersionInfo.ReleaseDate</p><p>com.aspose.barcode.CodabarChecksumMode.MOD_10</p><p>com.aspose.barcode.CodabarChecksumMode.MOD_16</p><p>com.aspose.barcode.DataMatrixEccType.ECC_000</p><p>com.aspose.barcode.DataMatrixEccType.ECC_050</p><p>com.aspose.barcode.DataMatrixEccType.ECC_080</p><p>com.aspose.barcode.DataMatrixEccType.ECC_100</p><p>com.aspose.barcode.DataMatrixEccType.ECC_140</p><p>com.aspose.barcode.DataMatrixEccType.ECC_200</p><p>com.aspose.barcode.DataMatrixEccType.ECC_AUTO</p><p>com.aspose.barcode.DataMatrixEncodeMode.ASCII</p><p>com.aspose.barcode.DataMatrixEncodeMode.AUTO</p><p>com.aspose.barcode.DataMatrixEncodeMode.CUSTOM</p><p>com.aspose.barcode.DataMatrixEncodeMode.FULL</p><p>com.aspose.barcode.EnableChecksum.DEFAULT</p><p>com.aspose.barcode.EnableChecksum.NO</p><p>com.aspose.barcode.EnableChecksum.YES</p><p>com.aspose.barcode.GraphicsUnit.DISPLAY</p><p>com.aspose.barcode.GraphicsUnit.DOCUMENT</p><p>com.aspose.barcode.GraphicsUnit.INCH</p><p>com.aspose.barcode.GraphicsUnit.MILLIMETER</p><p>com.aspose.barcode.GraphicsUnit.PIXEL</p><p>com.aspose.barcode.GraphicsUnit.POINT</p><p>com.aspose.barcode.GraphicsUnit.WORLD</p><p>com.aspose.barcode.ITF14BorderType.BAR</p><p>com.aspose.barcode.ITF14BorderType.BAR_OUT</p><p>com.aspose.barcode.ITF14BorderType.FRAME</p><p>com.aspose.barcode.ITF14BorderType.FRAME_OUT</p><p>com.aspose.barcode.ITF14BorderType.NONE</p><p>com.aspose.barcode.ImageQualityMode.ANTI_ALIAS</p><p>com.aspose.barcode.ImageQualityMode.DEFAULT</p><p>com.aspose.barcode.Pdf417CompactionMode.AUTO</p><p>com.aspose.barcode.Pdf417CompactionMode.BINARY</p><p>com.aspose.barcode.Pdf417CompactionMode.NUMERIC</p><p>com.aspose.barcode.Pdf417CompactionMode.TEXT</p><p>com.aspose.barcode.Pdf417ErrorLevel.LEVEL_0</p><p>com.aspose.barcode.Pdf417ErrorLevel.LEVEL_1</p><p>com.aspose.barcode.Pdf417ErrorLevel.LEVEL_2</p><p>com.aspose.barcode.Pdf417ErrorLevel.LEVEL_3</p><p>com.aspose.barcode.Pdf417ErrorLevel.LEVEL_4</p><p>com.aspose.barcode.Pdf417ErrorLevel.LEVEL_5</p><p>com.aspose.barcode.Pdf417ErrorLevel.LEVEL_6</p><p>com.aspose.barcode.Pdf417ErrorLevel.LEVEL_7</p><p>com.aspose.barcode.Pdf417ErrorLevel.LEVEL_8</p><p>com.aspose.barcode.QREncodeMode.AUTO</p><p>com.aspose.barcode.QREncodeMode.BYTES</p><p>com.aspose.barcode.QREncodeMode.ECI_ENCODING</p><p>com.aspose.barcode.QREncodeMode.EXTENDED_CODETEXT</p><p>com.aspose.barcode.QREncodeMode.UTF_16_BEBOM</p><p>com.aspose.barcode.QREncodeMode.UTF_8_BOM</p><p>com.aspose.barcode.QREncodeType.AUTO</p><p>com.aspose.barcode.QREncodeType.FORCE_MICRO_QR</p><p>com.aspose.barcode.QREncodeType.FORCE_QR</p><p>com.aspose.barcode.QRErrorLevel.LEVEL_H</p><p>com.aspose.barcode.QRErrorLevel.LEVEL_L</p><p>com.aspose.barcode.QRErrorLevel.LEVEL_M</p><p>com.aspose.barcode.QRErrorLevel.LEVEL_Q</p><p>com.aspose.barcode.RenderFormat.BMP</p><p>com.aspose.barcode.RenderFormat.GIF</p><p>com.aspose.barcode.RenderFormat.JPEG</p><p>com.aspose.barcode.RenderFormat.PNG</p><p>com.aspose.barcode.ResolutionMode.CUSTOMIZED</p><p>com.aspose.barcode.ResolutionMode.GRAPHICS</p><p>com.aspose.barcode.ResolutionMode.PRINTER</p><p>com.aspose.barcode.RotationDirection.ANTI_CLOCKWISE</p><p>com.aspose.barcode.RotationDirection.CLOCKWISE</p><p>com.aspose.barcode.StringAlignment.CENTER</p><p>com.aspose.barcode.StringAlignment.FAR</p><p>com.aspose.barcode.StringAlignment.NEAR</p><p>com.aspose.barcode.TextRenderingHint.SYSTEM_DAFAULT</p><p>com.aspose.barcode.TextRenderingHint.SINGLE_BIIT_PER_PIXEL_GRID_FIT</p><p>com.aspose.barcode.TextRenderingHint.SINGLE_BIT_PER_PIXEL</p><p>com.aspose.barcode.TextRenderingHint.ANTI_ALIAS_GRID_FIT</p><p>com.aspose.barcode.TextRenderingHint.ANTI_ALIAS</p><p>com.aspose.barcode.TextRenderingHint.CLEAR_TYPE_GRID_FIT</p><p>com.aspose.barcode.generation.AutoSizeMode.NONE</p><p>com.aspose.barcode.generation.AutoSizeMode.NEAREST</p><p>com.aspose.barcode.generation.AutoSizeMode.INTERPOLATION</p><p>com.aspose.barcode.generation.FontStyle.BOLD</p><p>com.aspose.barcode.generation.FontStyle.ITALIC</p><p>com.aspose.barcode.generation.FontStyle.REGULAR</p><p>com.aspose.barcode.generation.FontStyle.STRIKEOUT</p><p>com.aspose.barcode.generation.FontStyle.UNDERLINE</p><p>com.aspose.barcode.barcoderecognition.ChecksumValidation.DEFAULT</p><p>com.aspose.barcode.barcoderecognition.ChecksumValidation.OFF</p><p>com.aspose.barcode.barcoderecognition.ChecksumValidation.ON</p><p>com.aspose.barcode.barcoderecognition.CustomerInformationInterpretingType.C_TABLE</p><p>com.aspose.barcode.barcoderecognition.CustomerInformationInterpretingType.N_TABLE</p><p>com.aspose.barcode.barcoderecognition.CustomerInformationInterpretingType.OTHER</p><p>com.aspose.barcode.barcoderecognition.ManualHint.COMPLEX_BACKGROUND</p><p>com.aspose.barcode.barcoderecognition.ManualHint.INCORRECT_BARCODES</p><p>com.aspose.barcode.barcoderecognition.ManualHint.INVERT_IMAGE</p><p>com.aspose.barcode.barcoderecognition.ManualHint.MEDIAN_SMOOTHING</p><p>com.aspose.barcode.barcoderecognition.ManualHint.NONE</p><p>com.aspose.barcode.barcoderecognition.ManualHint.SPECIAL_FORM_OF_CELLS</p><p>com.aspose.barcode.barcoderecognition.ManualHint.USE_REGULAR</p><p>com.aspose.barcode.barcoderecognition.ManualHint.USE_RESTORATION</p><p>com.aspose.barcode.barcoderecognition.ManualHint.SKIP_ROTATED_BARCODES</p><p>com.aspose.barcode.barcoderecognition.DecodeType.TYPES_1D</p><p>com.aspose.barcode.barcoderecognition.DecodeType.POSTAL_TYPES</p><p>com.aspose.barcode.barcoderecognition.DecodeType.MOST_COMMON_TYPES</p>|
| :- |
## **Public API and Backward Incompatible Changes**
Following members have been added:

- Field DecodeType.TYPES_1D;
- Field DecodeType.POSTAL_TYPES ;
- Field DecodeType.MOST_COMMON_TYPES

Constants throughout the project were renamed into line with Java naming convention.
