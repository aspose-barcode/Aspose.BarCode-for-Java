---
title: Aspose.BarCode for Java 19.11 Release Notes
type: docs
weight: 20
url: /java/aspose-barcode-for-java-19-11-release-notes/
---

{{% alert color="primary" %}} 

This page contains release notes information for [Aspose.BarCode for Java 19.11](https://downloads.aspose.com/barcode/java/new-releases/aspose.barcode-for-java-19.11/).

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODEJAVA-772 |Generate QR Code with ISO 20022 (Swiss QR Code)|New Feature|
|BARCODENET-37310|Implement SwissQR support|New Feature|
|BARCODENET-36825|Ability to recognize ID card and driving license from Colombia|Bug|
|BARCODENET-37235|Whole Barcode Length is not read|Bug|
# **Public API and Backward Incompatible Changes**
###### **Following members have been Added:**
- Class com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator
- Method com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator.#ctor(com.aspose.barcode.complexbarcode.IComplexCodetext)
- Method com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator.getParameters()
- Method com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator.generateBarCodeImage()
- Method com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator.save( java.io.OutputStream, com.aspose.barcode.BarCodeImageFormat)
- Method com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator.save(java.lang.String,com.aspose.barcode.BarCodeImageFormat)
- Method com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator.save(java.lang.String)
- Method com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator.dispose()
- Class com.aspose.barcode.complexbarcode.ComplexCodetextReader
- Method com.aspose.barcode.complexbarcode.ComplexCodetextReader.tryDecodeSwissQR(java.lang.String)
- Class com.aspose.barcode.complexbarcode.IComplexCodetext
- Method com.aspose.barcode.complexbarcode.IComplexCodetext.getConstructedCodetext()
- Method com.aspose.barcode.complexbarcode.IComplexCodetext.initFromString(java.lang.String)
- Method com.aspose.barcode.complexbarcode.IComplexCodetext.getBarcodeType()
- Class com.aspose.barcode.complexbarcode.SwissQRCodetext
- Method com.aspose.barcode.complexbarcode.SwissQRCodetext.#ctor(com.aspose.barcode.complexbarcode.SwissQRBill)
- Method com.aspose.barcode.complexbarcode.SwissQRCodetext.#ctor
- Method com.aspose.barcode.complexbarcode.SwissQRCodetext.getBill()
- Method com.aspose.barcode.complexbarcode.SwissQRCodetext.getConstructedCodetext()
- Method com.aspose.barcode.complexbarcode.SwissQRCodetext.initFromString(java.lang.String)
- Method com.aspose.barcode.complexbarcode.SwissQRCodetext.getBarcodeType()
- Class com.aspose.barcode.complexbarcode.SwissQRBill
- Method com.aspose.barcode.complexbarcode.SwissQRBill.getVersion()
- Method com.aspose.barcode.complexbarcode.SwissQRBill.setVersion(QrBillStandardVersion)
- Method com.aspose.barcode.complexbarcode.SwissQRBill.getAmount()
- Method com.aspose.barcode.complexbarcode.SwissQRBill.setAmount(long)
- Method com.aspose.barcode.complexbarcode.SwissQRBill.getCurrency()
- Method com.aspose.barcode.complexbarcode.SwissQRBill.setCurrency(String )
- Method com.aspose.barcode.complexbarcode.SwissQRBill.getAccount()
- Method com.aspose.barcode.complexbarcode.SwissQRBill.setAccount(String)
- Method com.aspose.barcode.complexbarcode.SwissQRBill.getCreditor()
- Method com.aspose.barcode.complexbarcode.SwissQRBill.setCreditor(Address)
- Method com.aspose.barcode.complexbarcode.SwissQRBill.getReference()
- Method com.aspose.barcode.complexbarcode.SwissQRBill.setReference(String)
- Method com.aspose.barcode.complexbarcode.SwissQRBill.getDebtor()
- Method com.aspose.barcode.complexbarcode.SwissQRBill.setDebtor(Address)
- Method com.aspose.barcode.complexbarcode.SwissQRBill.getUnstructuredMessage()
- Method com.aspose.barcode.complexbarcode.SwissQRBill.setUnstructuredMessage(String)
- Method com.aspose.barcode.complexbarcode.SwissQRBill.getBillInformation()
- Method com.aspose.barcode.complexbarcode.SwissQRBill.setBillInformation(String)
- Method com.aspose.barcode.complexbarcode.SwissQRBill.getAlternativeSchemes()
- Method com.aspose.barcode.complexbarcode.SwissQRBill.setAlternativeSchemes(List<AlternativeScheme>)
- Method com.aspose.barcode.complexbarcode.SwissQRBill.createAndSetCreditorReference(java.lang.String)
- Method com.aspose.barcode.complexbarcode.SwissQRBill.equals(System.Object)
- Method com.aspose.barcode.complexbarcode.SwissQRBill.equals(com.aspose.barcode.complexbarcode.SwissQRBill)
- Method com.aspose.barcode.complexbarcode.SwissQRBill.hashCode()
- Class com.aspose.barcode.complexbarcode.AlternativeScheme
- Method com.aspose.barcode.complexbarcode.AlternativeScheme.#ctor
- Method com.aspose.barcode.complexbarcode.AlternativeScheme.getInstruction()
- Method com.aspose.barcode.complexbarcode.AlternativeScheme.setInstruction(String)
- Method com.aspose.barcode.complexbarcode.AlternativeScheme.equals(System.Object)
- Method com.aspose.barcode.complexbarcode.AlternativeScheme.equals(com.aspose.barcode.complexbarcode.AlternativeScheme)
- Method com.aspose.barcode.complexbarcode.AlternativeScheme.hashCode()
- Enum com.aspose.barcode.complexbarcode.AddressType
- Field com.aspose.barcode.complexbarcode.AddressType.UNDETERMINED
- Field com.aspose.barcode.complexbarcode.AddressType.STRUCTURED
- Field com.aspose.barcode.complexbarcode.AddressType.COMBINED_ELEMENTS
- Field com.aspose.barcode.complexbarcode.AddressType.CONFLICTING
- Class com.aspose.barcode.complexbarcode.Address
- Method com.aspose.barcode.complexbarcode.Address.#ctor
- Method com.aspose.barcode.complexbarcode.Address.getType()
- Method com.aspose.barcode.complexbarcode.Address.setType(AddressType)
- Method com.aspose.barcode.complexbarcode.Address.getName()
- Method com.aspose.barcode.complexbarcode.Address.setName(String)
- Method com.aspose.barcode.complexbarcode.Address.getAddressLine1()
- Method com.aspose.barcode.complexbarcode.Address.setAddressLine1(String)
- Method com.aspose.barcode.complexbarcode.Address.getAddressLine2()
- Method com.aspose.barcode.complexbarcode.Address.setAddressLine2(String)
- Method com.aspose.barcode.complexbarcode.Address.getStreet()
- Method com.aspose.barcode.complexbarcode.Address.setStreet(String)
- Method com.aspose.barcode.complexbarcode.Address.getHouseNo()
- Method com.aspose.barcode.complexbarcode.Address.setHouseNo(String)
- Method com.aspose.barcode.complexbarcode.Address.getPostalCode()
- Method com.aspose.barcode.complexbarcode.Address.setPostalCode(String)
- Method com.aspose.barcode.complexbarcode.Address.getTown()
- Method com.aspose.barcode.complexbarcode.Address.setTown(String)
- Method com.aspose.barcode.complexbarcode.Address.setCountryCode(String)
- Method com.aspose.barcode.complexbarcode.Address.getCountryCode()
- Method com.aspose.barcode.complexbarcode.Address.clear()
- Method com.aspose.barcode.complexbarcode.Address.equals(Object)
- Method com.aspose.barcode.complexbarcode.Address.equals(com.aspose.barcode.complexbarcode.Address)
- Method com.aspose.barcode.complexbarcode.Address.hashCode()
- Enum com.aspose.barcode.complexbarcode.QrBillStandardVersion
- Field com.aspose.barcode.complexbarcode.QrBillStandardVersion.V2_0
