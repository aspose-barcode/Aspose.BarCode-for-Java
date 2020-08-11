---
title: Aspose.BarCode for PHP via Java 19.12
type: docs
weight: 10
url: /java/aspose-barcode-for-php-via-java-19-12/
---

{{% alert color="primary" %}} 

This page contains release notes information for [Aspose.BarCode for PHP via Java 19.12](https://downloads.aspose.com/barcode/phpjava/new-releases/aspose.barcode-for-php-via-java-19.12/).

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODEPHP-3|Design PHP API for barcode recognition functionality|New Feature|
|BARCODEPHP-15|Implement the proper processing of exceptions on PHP side that come from Java|New Feature|
|BARCODEPHP-17|Implement and cover by tests the main functionality of QualitySettings|New Feature|
|BARCODEPHP-50|Design PHP API for barcode generating functionality|New Feature|
|BARCODEPHP-51|Develop functionality of BarcodeGenerator class and related classes|New Feature|
|BARCODEPHP-52|Develop functionality of BarcodeReader class and related classes|New Feature|
# **Public API and Backward Incompatible Changes**
Following members have been added:

- class BarcodeReader
- function getFileName()
- function getTimeout()
- function setTimeout($value)
- function getChecksumValidation()
- function setChecksumValidation($value)
- function getStripFNC()
- function setStripFNC($value)
- function getCustomerInformationInterpretingType()
- function setCustomerInformationInterpretingType($value)
- function close()
- function abort()
- function read()
- function setQualitySettings(QualitySettings $value)
- function getCodeText($includeCheckSum)
- function getCheckSum()
- function getAngle()
- function getCodeBytes()
- function getMacroPdf417FileID()
- function getMacroPdf417SegmentID()
- function getMacroPdf417SegmentsCount()
- function getCode128DataPortions()
- function getQRStructuredAppendModeBarCodesQuantity()
- function getQRStructuredAppendModeBarCodeIndex()
- function getQRStructuredAppendModeParityData()
- function getIsDeniable()
- function getRegion()
- function getCodeType()
- function getCodeTypeName()
- function getRecognitionQuality()
- function getDetectEncoding()
- function setDetectEncoding($value)
- function setBarCodeImage($image, ...$areas)
- function setBarCodeReadType(...$types)
- function getBarCodeDecodeType()
- function exportToXml($xmlFile)



- class QualitySettings
- function getAllowInvertImage()
- function setAllowInvertImage($value)
- function getAllowIncorrectBarcodes()
- function setAllowIncorrectBarcodes($value)
- function getAllowComplexBackground()
- function setAllowComplexBackground($value)
- function getAllowMedianSmoothing()
- function setAllowMedianSmoothing($value)
- function getMedianSmoothingWindowSize()
- function setMedianSmoothingWindowSize($value)
- function getAllowRegularImage()
- function setAllowRegularImage($value)
- function getAllowDecreasedImage()
- function setAllowDecreasedImage($value)
- function getAllowWhiteSpotsRemoving()
- function setAllowWhiteSpotsRemoving($value)
- function getAllowOneDAdditionalScan()
- function setAllowOneDAdditionalScan($value)
- function getAllowOneDFastBarcodesDetector()
- function setAllowOneDFastBarcodesDetector($value)
- function getAllowMicroWhiteSpotsRemoving()
- function setAllowMicroWhiteSpotsRemoving($value)
- function getAllowSaltAndPaperFiltering()
- function setAllowSaltAndPaperFiltering($value)
- function getAllowDetectScanGap()
- function setAllowDetectScanGap($value)
- function getAllowDatamatrixIndustrialBarcodes()
- function setAllowDatamatrixIndustrialBarcodes($value)
- function getAllowQRMicroQrRestoration()
- function setAllowQRMicroQrRestoration($value)
- function getAllowOneDWipedBarsRestoration()
- function setAllowOneDWipedBarsRestoration($value)
- function applyAll(QualitySettings $Src)



- class BarCodeRegion
- function getPoints()



- class Code128DataPortion
- function getData()
- function setData($value)
- function getCode128SubType()
- function setCode128SubType($value)



- class DecodeType
- const NONE
- const CODABAR
- const CODE_11
- const CODE_39_STANDARD
- const CODE_39_EXTENDED
- const CODE_93_STANDARD
- const CODE_93_EXTENDED
- const CODE_128
- const GS_1_CODE_128
- const EAN_8
- const EAN_13
- const EAN_14
- const SCC_14
- const SSCC_18
- const UPCA
- const UPCE
- const ISBN
- const STANDARD_2_OF_5
- const INTERLEAVED_2_OF_5
- const MATRIX_2_OF_5
- const ITALIAN_POST_25
- const IATA_2_OF_5
- const ITF_14
- const ITF_6
- const MSI
- const VIN
- const DEUTSCHE_POST_IDENTCODE
- const DEUTSCHE_POST_LEITCODE
- const OPC
- const PZN
- const PHARMACODE
- const DATA_MATRIX
- const GS_1_DATA_MATRIX
- const QR
- const AZTEC
- const PDF_417
- const MACRO_PDF_417
- const MICRO_PDF_417
- const CODABLOCK_F
- const AUSTRALIA_POST
- const POSTNET
- const PLANET
- const ONE_CODE
- const RM_4_SCC
- const DATABAR_OMNI_DIRECTIONAL
- const DATABAR_TRUNCATED
- const DATABAR_LIMITED
- const DATABAR_EXPANDED
- const DATABAR_STACKED_OMNI_DIRECTIONAL
- const DATABAR_STACKED
- const DATABAR_EXPANDED_STACKED
- const PATCH_CODE
- const ISSN
- const ISMN
- const SUPPLEMENT
- const AUSTRALIAN_POSTE_PARCEL
- const SWISS_POST_PARCEL
- const CODE_16_K
- const MICRO_QR
- const COMPACT_PDF_417
- const GS_1_QR
- const MAXI_CODE
- const MICR_E_13_B
- const CODE_32
- const DATA_LOGIC_2_OF_5
- const DOT_CODE
- const DUTCH_KIX
- const ALL_SUPPORTED_TYPES
- const TYPES_1D
- const POSTAL_TYPES
- const MOST_COMMON_TYPES



- class Code128SubType
- const CODE_SET_A
- const CODE_SET_B
- const CODE_SET_C



- class  CustomerInformationInterpretingType
- const C_TABLE
- const N_TABLE



- class BarcodeGenerator
- function getParameters()
- function getBarcodeType()
- function setBarcodeType($value)
- function generateBarcodeImage()
- function save($filePath)
- function saveImageFormat($filePath, $format_name)
- function getCodeText()
- function setCodeText($value)



- class BarcodeParameters
- function getXDimension()
- function setXDimension(Unit $value)
- function getBarHeight()
- function setBarHeight(Unit $value)
- function getAutoSizeMode()
- function setAutoSizeMode($value)
- function getBarCodeHeight()
- function setBarCodeHeight(Unit $value)
- function getBarCodeWidth()
- function setBarCodeWidth(Unit $value)
- function getForeColor()
- function setForeColor($value)
- function getPadding()
- function getChecksumAlwaysShow()
- function setChecksumAlwaysShow($value)
- function isChecksumEnabled()
- function setChecksumEnabled($value)
- function getEnableEscape()
- function setEnableEscape($value)
- function getWideNarrowRatio()
- function setWideNarrowRatio($value)
- function getCodeTextParameters()
- function getFilledBars()
- function setFilledBars($value)
- function getPostal()
- function getAustralianPost()
- function getDataBar()
- function getCodablock()
- function getDataMatrix()
- function getCode16K()
- function getDotCode()
- function getITF()
- function getPdf417()
- function getQR()
- function getSupplement()
- function getMaxiCode()
- function getAztec()
- function getCodabar()
- function getCoupon()



- class BaseGenerationParameters
- function getBackColor()
- function setBackColor($hexValue)
- function getResolution()
- function setResolution($value)
- function getRotationAngle()
- function setRotationAngle($value)
- function getCaptionAbove()
- function setCaptionAbove(CaptionParameters $value)
- function getCaptionBelow()
- function getBarcode()
- function getBorder()



- class BorderParameters
- function getVisible()
- function setVisible($value)
- function getWidth()
- function setWidth(Unit $value)
- function toString()
- function getDashStyle()
- function setDashStyle($value)
- function getColor()
- function setColor($hexValue)



- class  ChecksumValidation
- const default
- const ON
- const OFF



- class CaptionParameters
- function getText()
- function setText($value)
- function getFont()
- function setFont(FontUnit $value)
- function getVisible()
- function setVisible($value)
- function getTextColor()
- function setTextColor($rgbValue)
- function getPadding()
- function setPadding(Padding $value)
- function getAlignment()
- function setAlignment($value)



- class Unit
- function getPixels()
- function setPixels($value)
- function getInches()
- function setInches($value)
- function getMillimeters()
- function setMillimeters($value)
- function getPoint()
- function setPoint($value)
- function getDocument()
- function setDocument($value)
- function toString()
- function equals($obj)



- class Padding
- function getTop()
- function setTop(Unit $value)
- function getBottom()
- function setBottom(Unit $value)
- function getRight()
- function setRight(Unit $value)
- function getLeft()
- function setLeft(Unit $value)
- function toString()



- class CodetextParameters
- function getTwoDDisplayText()
- function setTwoDDisplayText($value)
- function getFontMode()
- function setFontMode($value)
- function getFont()
- function setFont(FontUnit $value)
- function getSpace()
- function setSpace(Unit $value)
- function getAlignment()
- function setAlignment($value)
- function getColor()
- function setColor($value)
- function getLocation()
- function setLocation($value)
- function toString()



- class PostalParameters
- function getPostalShortBarHeight()
- function setPostalShortBarHeight(Unit $value)
- function toString()



- class AustralianPostParameters
- function getAustralianPostShortBarHeight()
- function setAustralianPostShortBarHeight(Unit $value)
- function getAustralianPostEncodingTable()
- function setAustralianPostEncodingTable($value)
- function toString()



- class CodablockParameters
- function getColumns()
- function setColumns($value)
- function getRows()
- function setRows($value)
- function getAspectRatio()
- function setAspectRatio($value)
- function toString()



- class DataBarParameters
- function getColumns()
- function setColumns($value)
- function getRows()
- function setRows($value)
- function getAspectRatio()
- function setAspectRatio($value)
- function toString()



- class DataMatrixParameters
- function getDataMatrixEcc()
- function setDataMatrixEcc($value)
- function getDataMatrixEncodeMode()
- function setDataMatrixEncodeMode($value)
- function getColumns()
- function setColumns($value)
- function getRows()
- function setRows($value)
- function getAspectRatio()
- function setAspectRatio($value)
- function getCodeTextEncoding()
- function setCodeTextEncoding($value)
- function toString()



- class Code16KParameters
- function getAspectRatio()
- function setAspectRatio($value)
- function getQuietZoneLeftCoef()
- function setQuietZoneLeftCoef($value)
- function getQuietZoneRightCoef()
- function setQuietZoneRightCoef($value)
- function toString()



- class DotCodeParameters
- function getDotCodeMask()
- function setDotCodeMask($value)
- function getAspectRatio()
- function setAspectRatio($value)
- function toString()



- class ITFParameters
- function getItfBorderThickness()
- function setItfBorderThickness(Unit $value)
- function getItfBorderType()
- function setItfBorderType($value)
- function getQuietZoneCoef()
- function setQuietZoneCoef($value)
- function toString()



- class QrParameters
- function getQrECIEncoding()
- function setQrECIEncoding($value)
- function getQrEncodeMode()
- function setQrEncodeMode($value)
- function getQrEncodeType()
- function setQrEncodeType($value)
- function getQrErrorLevel()
- function setQrErrorLevel($value)
- function getQrVersion()
- function setQrVersion($value)
- function getAspectRatio()
- function setAspectRatio($value)
- function getCodeTextEncoding()
- function setCodeTextEncoding($value)
- function toString()



- class Pdf417Parameters
- function getPdf417CompactionMode()
- function setPdf417CompactionMode($value)
- function getPdf417ErrorLevel()
- function setPdf417ErrorLevel($value)
- function getPdf417Truncate()
- function setPdf417Truncate($value)
- function getColumns()
- function setColumns($value)
- function getRows()
- function setRows($value)
- function getAspectRatio()
- function setAspectRatio($value)
- function getPdf417MacroFileID()
- function setPdf417MacroFileID($value)
- function getPdf417MacroSegmentID()
- function setPdf417MacroSegmentID($value)
- function getPdf417MacroSegmentsCount()
- function setPdf417MacroSegmentsCount($value)
- function getCodeTextEncoding()
- function setCodeTextEncoding($value)
- function toString()



- class SupplementParameters
- function getSupplementData()
- function setSupplementData($value)
- function getSupplementSpace()
- function setSupplementSpace(Unit $value)
- function toString()



- class MaxiCodeParameters
- function getMaxiCodeEncodeMode()
- function setMaxiCodeEncodeMode($value)
- function getAspectRatio()
- function setAspectRatio($value)
- function toString()



- class AztecParameters
- function getAztecErrorLevel()
- function setAztecErrorLevel($value)
- function getAztecSymbolMode()
- function setAztecSymbolMode($value)
- function getAspectRatio()
- function setAspectRatio($value)
- function getCodeTextEncoding()
- function setCodeTextEncoding($value)
- function toString()



- class CodabarParameters
- function getCodabarChecksumMode()
- function setCodabarChecksumMode($value)
- function getCodabarStartSymbol()
- function setCodabarStartSymbol($value)
- function getCodabarStopSymbol()
- function setCodabarStopSymbol($value)
- function toString()



- class CouponParameters
- function getSupplementSpace()
- function setSupplementSpace(Unit $value)
- function toString()



- class FontUnit
- function getFamilyName()
- function setFamilyName($value)
- function getStyle()
- function setStyle($value)
- function getSize()



- class FontStyle
- const BOLD
- const ITALIC
- const REGULAR
- const STRIKEOUT
- const UNDERLINE



- class CodabarSymbol
- const A
- const B
- const C
- const D



- class  DataMatrixEncodeMode
- const AUTO
- const ASCII
- const FULL
- const CUSTOM
- const C40
- const TEXT



- class BorderDashStyle
- const  SOLID
- const   DASH
- const  DOT
- const  DASH_DOT
- const  DASH_DOT_DOT



- class ITF14BorderType
- const NONE
- const FRAME
- const BAR
- const FRAME_OUT
- const BAR_OUT



- class QREncodeMode
- const AUTO
- const BYTES
- const UTF_8_BOM
- const UTF_16_BEBOM



- class  DataMatrixEccType
- const ECC_AUTO
- const ECC_000
- const ECC_050
- const ECC_080
- const ECC_100
- const ECC_140
- const ECC_200



- class QRVersion
- const AUTO
- const VERSION_01
- const VERSION_02
- const VERSION_03
- const VERSION_04
- const VERSION_05
- const VERSION_06
- const VERSION_07
- const VERSION_08
- const VERSION_09
- const VERSION_10
- const VERSION_11
- const VERSION_12
- const VERSION_13
- const VERSION_14
- const VERSION_15
- const VERSION_16
- const VERSION_17
- const VERSION_18
- const VERSION_19
- const VERSION_20
- const VERSION_21
- const VERSION_22
- const VERSION_23
- const VERSION_24
- const VERSION_25
- const VERSION_26
- const VERSION_27
- const VERSION_28
- const VERSION_29
- const VERSION_30
- const VERSION_31
- const VERSION_32
- const VERSION_33
- const VERSION_34
- const VERSION_35
- const VERSION_36
- const VERSION_37
- const VERSION_38
- const VERSION_39
- const VERSION_40
- const VERSION_M1
- const VERSION_M2
- const VERSION_M3
- const VERSION_M4



- class  AztecSymbolMode
- const AUTO
- const COMPACT
- const FULL_RANGE
- const RUNE



- class Pdf417ErrorLevel
- const LEVEL_0
- const LEVEL_1
- const LEVEL_2
- const LEVEL_3
- const LEVEL_4
- const LEVEL_5
- const LEVEL_6
- const LEVEL_7
- const LEVEL_8



- class  Pdf417CompactionMode
- const AUTO
- const TEXT
- const NUMERIC
- const BINARY
- class QRErrorLevel
- const LEVEL_L
- const LEVEL_M
- const LEVEL_Q
- const LEVEL_H



- class  QREncodeType
- const AUTO
- const FORCE_QR
- const FORCE_MICRO_QR



- class CodabarChecksumMode
- const MOD_10
- const MOD_16



- class  CodeLocation
- const BELOW
- const ABOVE
- const NONE



- class  FontMode
- const AUTO
- const MANUAL



- class  TextAlignment
- const LEFT
- const CENTER
- const RIGHT



- class  AutoSizeMode
- const NONE
- const NEAREST
- const INTERPOLATION



- class EncodeTypes
- const  NONE
- const  CODABAR
- const  CODE_11
- const  CODE_39_STANDARD
- const  CODE_39_EXTENDED
- const  CODE_93_STANDARD
- const  CODE_93_EXTENDED
- const  CODE_128
- const  GS_1_CODE_128
- const  EAN_8
- const  EAN_13
- const  EAN_14
- const  SCC_14
- const  SSCC_18
- const  UPCA
- const  UPCE
- const  ISBN
- const  ISSN
- const  ISMN
- const  STANDARD_2_OF_5
- const  INTERLEAVED_2_OF_5
- const  MATRIX_2_OF_5
- const  ITALIAN_POST_25
- const  IATA_2_OF_5
- const  ITF_14
- const  ITF_6
- const  MSI
- const  VIN
- const  DEUTSCHE_POST_IDENTCODE
- const  DEUTSCHE_POST_LEITCODE
- const  OPC
- const  PZN
- const  CODE_16_K
- const  PHARMACODE
- const  DATA_MATRIX
- const  QR
- const  AZTEC
- const  PDF_417
- const  MACRO_PDF_417
- const  GS_1_DATA_MATRIX
- const  MICRO_PDF_417
- const  GS_1_QR
- const  MAXI_CODE
- const  DOT_CODE
- const  AUSTRALIA_POST
- const  POSTNET
- const  PLANET
- const  ONE_CODE
- const  RM_4_SCC
- const  DATABAR_OMNI_DIRECTIONAL
- const  DATABAR_TRUNCATED
- const  DATABAR_LIMITED
- const  DATABAR_EXPANDED
- const  DATABAR_EXPANDED_STACKED
- const  DATABAR_STACKED
- const  DATABAR_STACKED_OMNI_DIRECTIONAL
- const  SINGAPORE_POST
- const  AUSTRALIAN_POSTE_PARCEL
- const  SWISS_POST_PARCEL
- const  PATCH_CODE
- const  CODE_32
- const  DATA_LOGIC_2_OF_5
- const  DUTCH_KIX
- const  UPCA_GS_1_CODE_128_COUPON
- const  UPCA_GS_1_DATABAR_COUPON
- const  CODABLOCK_F
- const  GS_1_CODABLOCK_F


