---
title: Aspose.BarCode for Java 19.1 Release Notes
type: docs
weight: 120
url: /java/aspose-barcode-for-java-19-1-release-notes/
---

{{% alert color="primary" %}} 

This page contains release notes information for Aspose.BarCode for Java 19.1.

{{% /alert %}} 
## **All Changes**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-37045|Pdf417 throws Out Of Memory Exception when the CodeText has a closing parenthesis ")"|Bug|
|BARCODEJAVA-631|A specific image is being recognized much slower in Java than in .NET|Bug|
# **Usage examples:**
**BARCODENET-37045 - Pdf417 throws Out Of Memory Exception when CodeText has a closing parenthesis ")"**



String[] testCases = new String[]{"Sample.Code.Text(Example).20181203_150435",
`                `"Sample.Code.(Text(Example).20181203_150435",
`                `"Sample.Code.Text(Example).20181203_(150435",
`                `"Sample.Code(.Text(Example).20181203(_150435",
`                `"Sample.Code.Text(Example).(20181203_150435",
`                `"Sample.Code.Text(Example().20181203_150435",
`                `"Sample.Code.Text((Example).20181203_150435"};
for (String codetext : testCases)
{
`            `BarCodeGenerator b = new BarCodeGenerator(EncodeTypes.PDF_417);
`            `b.getPdf417().setCompactionMode(Pdf417CompactionMode.TEXT);
`            `b.getPdf417().setErrorLevel(Pdf417ErrorLevel.LEVEL_2);
`            `b.getD2().setColumns(4);
`            `b.getD2().setRows(12);
`            `b.getXDimension().setPixels(3);
`            `b.getD2().setAspectRatio(1.33F);
`            `b.setCodeText(codetext);

`            `ByteArrayOutputStream barcodeOutputStream = new ByteArrayOutputStream();
`            `b.save(barcodeOutputStream, BarCodeImageFormat.PNG);
`            `barcodeOutputStream.flush();

`            `byte[] buffer = barcodeOutputStream.toByteArray();

`            `ByteArrayInputStream barcodeInputStream = new ByteArrayInputStream(buffer);

`            `BarCodeReader reader = new BarCodeReader(barcodeInputStream, DecodeType.PDF_417);
`            `while (reader.read())
`            `{
`                `System.out.println("Expected => " + codetext + " , actual => " + reader.getCodeText());
`            `}
}

Result:
Expected => Sample.Code.Text(Example).20181203_150435 , actual => Sample.Code.Text(Example).20181203_150435
Expected => Sample.Code.(Text(Example).20181203_150435 , actual => Sample.Code.(Text(Example).20181203_150435
Expected => Sample.Code.Text(Example).20181203_(150435 , actual => Sample.Code.Text(Example).20181203_(150435
Expected => Sample.Code(.Text(Example).20181203(_150435 , actual => Sample.Code(.Text(Example).20181203(_150435
Expected => Sample.Code.Text(Example).(20181203_150435 , actual => Sample.Code.Text(Example).(20181203_150435
Expected => Sample.Code.Text(Example().20181203_150435 , actual => Sample.Code.Text(Example().20181203_150435
Expected => Sample.Code.Text((Example).20181203_150435 , actual => Sample.Code.Text((Example).20181203_150435
## **Public API and Backward Incompatible Changes**
No Changes


