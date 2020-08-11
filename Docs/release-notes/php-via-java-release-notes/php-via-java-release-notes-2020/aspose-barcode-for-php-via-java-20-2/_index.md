---
title: Aspose.BarCode for PHP via Java 20.2
type: docs
weight: 30
url: /java/aspose-barcode-for-php-via-java-20-2/
---

{{% alert color="primary" %}} 

This page contains release notes information for [Aspose.BarCode for PHP via Java 20.2](https://downloads.aspose.com/barcode/phpjava/new-releases/aspose.barcode-for-php-via-java-20.2/).

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-37237|Improve Pdf417 basic recognition quality|New Feature|
|BARCODENET-37164 |Investigate the multithreaded framework for zebra target detection|New Feature|
|BARCODEJAVA-772 |Generate QR Code with ISO 20022 (Swiss QR Code)|New Feature|
|BARCODENET-37310|Implement SwissQR support|New Feature|
|BARCODENET-37334|Create a new barcode detector|New Feature|
|BARCODENET-37302|Add changes to the BarCodeReader interface.|New Feature|
|BARCODENET-36825|Ability to recognize ID card and driving license from Colombia  (partially fixed)|Bug|
|BARCODENET-37198|Barcode reading image to text failed|Bug|
|BARCODENET-36825|Ability to recognize ID card and driving license from Colombia|Bug|
|BARCODENET-37235|Whole Barcode Length is not read|Bug|
|BARCODENET-37034|Region Points and Image Accuracy|Bug|
|BARCODEJAVA-875|The detection of the barcode region returns incorrect result|Bug|
|BARCODENET-37324|BarCodeReader reads 7 instead of 0 |Bug|
# **Public API and Backward Incompatible Changes**
class BarcodeReader
added functions:

- function getFoundBarCodes()
- function getFoundCount()
- function readBarCodes()

class QualitySettings
added functions

- function getHighQualityDetection()
- static function getMaxQualityDetection()
- function getDetectorSettings()
- function setDetectorSettings(BarcodeSvmDetectorSettings $value)

class DecodeType

- added static function containsAny(...$decodeTypes)
- added class Quadrangle
- added functions:
- function EMPTY()
- function getLeftTop()
- function setLeftTop(Point $value)
- function getRightTop()
- function setRightTop(Point $value)
- function getRightBottom()
- function setRightBottom(Point $value)
- function getLeftBottom()
- function setLeftBottom(Point $value)
- function isEmpty()
- function contains(Point $pt)
- function containsPoint($x, $y)
- function containsQuadrangle(Quadrangle $quad)
- function containsRectangle(Rectangle $rect)
- function equals(Quadrangle $other)
- static function op_Equality(Quadrangle $first, Quadrangle $second)
- static function op_Inequality(Quadrangle $first, Quadrangle $second)
- function toString()
- function hashCode()
- function getBoundingRectangle()
- static function isEqual(Quadrangle $first, Quadrangle $second)

added class QRExtendedParameters

added functions:

- function getQRStructuredAppendModeBarCodesQuantity()
- function getQRStructuredAppendModeBarCodeIndex()
- function getQRStructuredAppendModeParityData()
- function isEmpty()
- function equals($obj)
- static function op_Equality(QRExtendedParameters $first, QRExtendedParameters $second)
- static function op_Inequality(QRExtendedParameters $first, QRExtendedParameters $second)
- function hashCode()
- function toString()

added class Pdf417ExtendedParameters
added functions:

- function getMacroPdf417FileID()
- function getMacroPdf417SegmentID()
- function getMacroPdf417SegmentsCount()
- function isEmpty()
- function equals($obj)
- static function op_Equality(Pdf417ExtendedParameters $first, Pdf417ExtendedParameters $second)
- added static function op_Inequality(Pdf417ExtendedParameters $first, Pdf417ExtendedParameters $second)

added functions:

- function hashCode()
- function toString()
- static function isEqual(Pdf417ExtendedParameters $first, Pdf417ExtendedParameters $second)

added class OneDExtendedParameters
added functions:

- function getValue()
- function getCheckSum()
- function isEmpty()
- function equals($obj)
- static function op_Equality(OneDExtendedParameters $first, OneDExtendedParameters $second)
- static function op_Inequality(OneDExtendedParameters $first, OneDExtendedParameters $second)
- function hashCode()
- function toString()

added class Code128ExtendedParameters
added functions:

- function getCode128DataPortions()
- function isEmpty()
- function equals($obj)
- static function op_Equality(Code128ExtendedParameters $first, Code128ExtendedParameters $second)
- static function op_Inequality(Code128ExtendedParameters $first, Code128ExtendedParameters $second)
- function hashCode()
- function toString()

added class BarcodeSvmDetectorSettings
added functions:

- function getScanWindowSizes()
- function setScanWindowSizes($value)
- function getSimilarityCoef()
- function setSimilarityCoef($value)
- function getRegionLikelihoodThresholdPercent()
- function setRegionLikelihoodThresholdPercent($value)
- function getSkipDiagonalSearch()
- function setSkipDiagonalSearch($value)
- function getMedianFilterWindowSize()
- function setMedianFilterWindowSize($value)
- static function getHighPerformance()
- static function getNormalQuality()
- static function getHighQuality()
- static function getMaxQuality()

added class BarCodeResult
added functions:

- function getReadingQuality()
- function getConfidence()
- function getCodeText()
- function getCodeBytes()
- function getCodeType()
- function getCodeTypeName()
- function getRegion()
- function getExtended()
- function equals(BarCodeResult $other)
- static function op_Equality(BarCodeResult $first, BarCodeResult $second)
- static function op_Inequality(BarCodeResult $first, BarCodeResult $second)
- function hashCode()
- function toString()
- function deepClone()
- function __construct($javaClass)

added class BarCodeRegionParameters
added functions:

- function getQuadrangle()
- function getAngle()
- function getPoints()
- function getRectangle()
- function equals($obj)
- static function op_Equality(BarCodeRegionParameters $first, BarCodeRegionParameters $second)
- added static function op_Inequality(BarCodeRegionParameters $first, BarCodeRegionParameters $second)
- added function hashCode()
- added function toString()

added class BarCodeExtendedParameters
added functions:

- function __construct
- function getOneD()
- function getCode128()
- function getQR()
- function getPdf417()
- function equals(BarCodeExtendedParameters $obj)
- static function op_Equality(BarCodeExtendedParameters $first, BarCodeExtendedParameters $second)
- added static function op_Inequality(BarCodeExtendedParameters $first, BarCodeExtendedParameters $second)
- added function hashCode()
- added function toString()

class BarcodeParameters

- added function getComplexBarcode()

class SupplementParameters

- removed function setSupplementSpace(Unit $value)

added class Address
added functions:

- function getType()
- function getName()
- function setName($value)
- function getAddressLine1()
- function setAddressLine1($value)
- function getAddressLine2()
- function setAddressLine2($value)
- function getStreet()
- function setStreet($value)
- function getHouseNo()
- function setHouseNo($value)
- function getPostalCode()
- function setPostalCode($value)
- function getTown()
- function setTown($value)
- function getCountryCode()
- function setCountryCode($value)
- function clear()
- function equals($obj)
- function hashCode()
- added class AddressType
- added const UNDETERMINED
- added const STRUCTURED
- added const COMBINED_ELEMENTS
- added const CONFLICTING

added class AlternativeScheme
added functions:

- function getInstruction()
- function setInstruction($value)
- function equals($obj)
- function hashCode()

added class ComplexCodetextReader

- added static function tryDecodeSwissQR($encodedCodetext)

added class QrBillStandardVersion
added const V2_0

added class SwissQRBill
added functions:

- function getVersion()
- function setVersion($value)
- function getAmount()
- function setAmount($value)
- function getCurrency()
- function setCurrency($value)
- function getAccount()
- function setAccount($value)
- function getCreditor()
- function setCreditor($value)
- function getReference()
- function setReference($value)
- function createAndSetCreditorReference($rawReference)
- function getDebtor()
- function setDebtor($value)
- function getUnstructuredMessage()
- function setUnstructuredMessage($value)
- function getBillInformation()
- function setBillInformation($value)
- function getAlternativeSchemes()
- function setAlternativeSchemes($value)
- function addAlternativeScheme(AlternativeScheme $value)
- function equals($obj)
- function hashCode()

added class SwissQRCodetext

added functions:

- function getBill():SwissQRBill
- static function initSwissQRCodetext($arg)
- function getConstructedCodetext()
- function initFromString($constructedCodetext)
- function getBarcodeType()

added class ComplexBarcode
added functions:

- function getDrawSwissCross()
- function setDrawSwissCross($value)

added class ComplexBarcodeGenerator 
added functions:

- function getParameters()
- function generateBarcodeImage()
- function save($filePath)
- function saveImageFormat($filePath, $format_name)
