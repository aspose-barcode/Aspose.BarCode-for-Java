---
title: Aspose.BarCode for Java 7.6.0 Release Notes
type: docs
weight: 100
url: /java/aspose-barcode-for-java-7-6-0-release-notes/
---

New Features

|**Key** |**Summary** |**Category** |
| :- | :- | :- |
|BARCODENET-34345 |Recognize datamatrix with decorated modules |New Features |
|BARCODENET-34315 |Implement recognition of dot peen datamatrix (rotated) |New Features |
|BARCODENET-13003 |Export / Import BarCode property to / from xml - file |New Features |
|BARCODENET-34337 |Add generatorBase parameter to the ReedSolomon encoder/decoder. |Enhancements |
|BARCODENET-34332 |Fake recognition of MicroPdf417 in picture with Code128 |Enhancements |
|BARCODENET-34328 |Unable to recognize QR barcode from Pdf file .Net |Enhancements |
|BARCODENET-34326 |Dashed dot peen datamatrix |Enhancements |
|BARCODENET-34314 |barcode Engine is not recognizing symbology : Code39Standard Barcode |Enhancements |
|BARCODENET-34306 |BarCodeReader Class GetAngle Method Returns inconsistent results, Some time returns integer value and some time float |Enhancements |
|BARCODENET-34287 |Can't recognize Datamatrix code from the PNG image |Enhancements |
|BARCODENET-34286 |Can't recognize Datamatrix code from the PNG image |Enhancements |
|BARCODENET-33842 |Can't recognize all DataMatrix/Code128 codes from a tif file |Enhancements |
|BARCODENET-34345 |Recognize datamatrix with decorated modules |Example |
|BARCODENET-34315 |Implement recognition of dot peen datamatrix (rotated) |Example |
|BARCODENET-13003 |Export / Import BarCode property to / from xml - file |Example |
|BARCODENET-34326 |Dashed dot peen datamatrix |Example |
|BARCODENET-34332|Fake recognition of MicroPdf417 in picture with Code128|Example|
|BARCODENET-34306 |BarCodeReader Class GetAngle Method Returns inconsistent results, Some time returns integer value and some time float |Example |
|BARCODENET-34287|Can't recognize Datamatrix code from the PNG image|Example|
|BARCODENET-34286|Can't recognize Datamatrix code from the PNG image|Example|
### **Usage examples:**
{{< highlight java >}}

 Code sample


 String filename = "datamatrix-stars.png";

 BarCodeReader reader = new BarCodeReader(filename, BarCodeReadType.DataMatrix);

 reader.setRecognitionMode(RecognitionMode.ManualHints);

 reader.setManualHints( ManualHint.SpecialFormOfCells);

 while (reader.read())

 {

   String actual = reader.getReadTypeName() + ":" + reader.getCodeText();

   System.out.println(actual);

 }

result:

DataMatrix:StarDatamatrix


{{< /highlight >}}

Now datamatrix is recognized by "MaxQuality" mode or "SpecialFormOfCells" manual hints

{{< highlight java >}}

 Code sample:


 String filename = "test2.png";

 BarCodeReader reader = new BarCodeReader(filename, BarCodeReadType.DataMatrix);

 reader.setRecognitionMode(RecognitionMode.ManualHints);

 reader.setManualHints(ManualHint.SpecialFormOfCells);

 while (reader.read())

 {

   System.out.println(reader.getReadType() + ":" + reader.getCodeText());

 }

result:

DataMatrix: BERMA Macchine Budrio (BO)

DataMatrix: BERMA Macchine Budrio (BO)

DataMatrix: BERMA Macchine Budrio (BO)

DataMatrix: BERMA Macchine Budrio (BO)

DataMatrix: BERMA Macchine Budrio (BO)

DataMatrix: BERMA Macchine Budrio (BO)

{{< /highlight >}}

ExportToXml(<The name of the file to export properties>)

Exports the BarCodeBuilder properties into XML-file with the name specified.
Returns true if the file has been successfully created; false otherwise;

{{< highlight java >}}

 Code sample:


BarCodeBuilder builder = new BarCodeBuilder("abcdefghijklmnopqrstuvwxyzabcdef", Symbology.DataMatrix);

builder.setBorderVisible(true);

builder.setImageQuality(ImageQualityMode.AntiAlias);

builder.setCodeLocation(CodeLocation.Above);

builder.setColumns(4);

builder.setRows(3);

builder.setCaptionAbove(new Caption("{Caption ABOVE}"));

Caption captionAbove = builder.getCaptionAbove();

captionAbove.setTextAlign(StringAlignment.Center);

captionAbove.setVisible(true);

captionAbove.setForeColor(Color.green);

builder.setCaptionBelow(new Caption("{Caption BELOW}"));

Caption captionBellow = builder.getCaptionBelow();

captionBellow.setTextAlign(StringAlignment.Far);

captionBellow.setVisible(true);

captionBellow.setForeColor(Color.yellow);

builder.setCodeTextFont(new Font("Courier New",  Font.BOLD | Font.ITALIC, 24));

String fileName = "BarCodeBuilder.DataMatrix.Builder.Export.xml";

builder.exportToXml(fileName);


{{< /highlight >}}

{{< highlight java >}}

 This sample has resulted in:

<?xml version="1.0" encoding="utf-8"?>

<Aspose.BarCode.Builder.Properties

  SymbologyType="DataMatrix"

  BorderColor="Black"

  ForeColor="Black"

  BackColor="White"

  CodeTextColor="Black"

  ImageQualityMode="AntiAlias"

  CodeLocation="Above"

  CodabarStartSymbol="A"

  CodabarStopSymbol="A"

  Rotation="0"

  AutoSize="True">

  <CodeText>abcdefghijklmnopqrstuvwxyzabcdef</CodeText>

  <CodeTextEncoding>1251</CodeTextEncoding>

  <FilledBars>true</FilledBars>

  <BorderVisible>true</BorderVisible>

  <CodeTextFont>Courier New; 24pt; style=Bold, Italic</CodeTextFont>

  <Columns>4</Columns>

  <Rows>3</Rows>

  <ResolutionValue>

    <Resolution

      DpiX="96"

      DpiY="96"

      Mode="Graphics" />

  </ResolutionValue>

  <CaptionAbove>

    <Caption

      ForeColor="Green"

      Text="{Caption ABOVE}"

      Visible="True"

      TextAlign="Center"

      Space="1"

      Font="Microsoft Sans Serif; 8pt" />

  </CaptionAbove>

  <CaptionBelow>

    <Caption

      ForeColor="Yellow"

      Text="{Caption BELOW}"

      Visible="True"

      TextAlign="Far"

      Space="1"

      Font="Microsoft Sans Serif; 8pt" />

  </CaptionBelow>

  <Margins>

    <MarginsF

      Bottom="1"

      Left="4"

      Right="4"

      Top="1" />

  </Margins>

</Aspose.BarCode.Builder.Properties>

ImportFromXml(<The name of the file to import properties>)

{{< /highlight >}}

Imports BarCodeBuilder properties from the XML-file specified for further BarCode building.
Returns new instance of BarCodeBuilder;

{{< highlight java >}}

 Code sample:

 String fileNameImported = "BarCodeBuilder.DataMatrix.Builder.Import.xml";

 BarCodeBuilder imported = BarCodeBuilder.importFromXml(fileNameImported);

 BufferedImage bmp = imported.generateBarCodeImage();


{{< /highlight >}}

ExportToXml(<The name of the file to export properties>)

Exports the BarCodeReader properties into XML-file with the name specified.
Returns true if the file has been successfully created; false otherwise;

{{< highlight java >}}

 Code sample:


//TODO

string xmlFileNameTmp = Path.Combine("C:\TEMP", "BarCodeReader.DataMatrix.xml");

BarCodeReader reader = new BarCodeReader(imgFilename, BarCodeReadType.DataMatrix);

reader.ExpectedBarCodeCount = 1;

reader.ChecksumValidation = ChecksumValidation.Off;

reader.RecognitionMode = RecognitionMode.MaxQuality;

if (reader.ExportToXml(xmlFileNameTmp))

{

    Concolse.WriteLine("BarCodeReader.ExportToXml('file') export has failed.");

}

This sample has resulted in:

<?xml version="1.0" encoding="utf-8"?>

<Aspose.BarCode.Reader.Properties

  BarCodeReadType="DataMatrix"

  ImageFileName="C:\TEMP\BarCodeReader.DataMatrix.xml"

  RecognitionMode="MaxQuality"

  ManualHints="None"

  OrientationHints="NoRotate">

  <ChecksumValidation>Off</ChecksumValidation>

  <ExpectedBarCodeCount>1</ExpectedBarCodeCount>

  <MedianSmoothingWindowSize>3</MedianSmoothingWindowSize>

  <Timeout>0</Timeout>

  <StripFNC>false</StripFNC>

</Aspose.BarCode.Reader.Properties>

ImportFromXml(<The name of the file to export properties>)

Imports BarCodeReader properties from the XML-file specified for further BarCode reading.

Returns new instance of BarCodeReader;

{{< /highlight >}}

{{< highlight java >}}

 Code sample:


//TODO

string xmlFileNameTmp = Path.Combine("C:\TEMP", "BarCodeReader.DataMatrix.xml");

BarCodeReader imported = BarCodeReader.ImportFromXml(xmlFileNameTmp);

if (imported == null)

{

    Concolse.WriteLine("BarCodeReader.ImportFromXml('file') import has not created the instance correctly.");

    return;

}

while (imported.Read())

{

    Concolse.WriteLine("Type: {0}; BarCode Text: {1}", imported.GetReadType(), imported.GetCodeText());

}



Sample code:


 BarCodeReader reader = new BarCodeReader(filename);

 while (reader.read())

 {

     System.out.println(reader.getReadTypeName() + ":" + reader.getCodeText());

 }

Result:

Code128:CC10DAF

|| Key \\ || Summary \\ || Category \\ ||

|BARCODENET-34328| Unable to recognize QR barcode from Pdf file [.Net]|Example|

Code sample


   //TODO

Result:

#SDY-LABEL#IdPastaClassificacao=1;Natureza=3;IdTipoDocumento=6;Empresa=4;Ano=2;Seccao=5;TipoDocumento=11;NumDocumento=1500552;FileName-Label=ERP$005$EX 2015$2$VFAT$1500552

{{< /highlight >}}

{{< highlight java >}}

 Code sample:


  BarCodeReader reader = new BarCodeReader(filename, BarCodeReadType.DataMatrix);

  reader.setRecognitionMode(RecognitionMode.ManualHints);

  reader.setManualHints(ManualHint.SpecialFormOfCells);

  while (reader.read())

  {

    System.out.println(reader.getReadTypeName() + ":" + reader.getCodeText());

  }

Result:

DataMatrix:250623022021032007010300

|| Key \\ || Summary \\ || Category \\ ||

|BARCODENET-34314| barcode Engine is not recognizing symbology : Code39Standard Barcode|Example|

Code samle:


string filename = @"bar.png";

BarCodeReader reader = new BarCodeReader(filename, BarCodeReadType.Code39Standard | BarCodeReadType.Code39Extended);

while (reader.read())

{

   System.out.println(reader.getReadTypeName() + ": " + reader.getCodeText());

}

Result:

CodeText: 15-07672

{{< /highlight >}}

{{< highlight java >}}

 Code sample

 for (int iCunt = 0; iCunt <= 5; iCunt++)

 {

   String barcodeFile = folder + "getangle_test" + (iCunt + 1)  + ".tif";

   BarCodeReader reader = new BarCodeReader(barcodeFile, BarCodeReadType.QR);

   while (reader.read())

   {

     double rotationValueActual = reader.getAngle();

     System.out.println(rotationValueActual);

   }

   reader.close();

 }

Result:

0.927611470222473

3.39229941368103

90.0077056884766

180.480224609375

270.355438232422

187.2504119873047


Code sample

String filename = folder + "Laser2.png";

 BarCodeReader reader = new BarCodeReader(filename, BarCodeReadType.DataMatrix);

 reader.setRecognitionMode(RecognitionMode.MaxQuality);

 while (reader.read())

 {

    String actual = reader.getReadTypeName() + ":" + reader.getCodeText();

    System.out.println(actual);

 }

Result:

DataMatrix:D19-WQ9-F91046-0811

Code sample

String filename = folder + "Laser1.PNG";

BarCodeReader reader = new BarCodeReader(filename, BarCodeReadType.DataMatrix);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while (reader.read())

{

 String actual = reader.getReadTypeName() + ":" + reader.getCodeText();

 System.out.println(actual);

}

Result:

DataMatrix:D19-WQ9-F91046-0811

{{< /highlight >}}
