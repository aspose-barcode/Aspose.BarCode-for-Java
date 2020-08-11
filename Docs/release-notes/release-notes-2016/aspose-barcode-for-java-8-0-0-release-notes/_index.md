---
title: Aspose.BarCode for Java 8.0.0 Release Notes
type: docs
weight: 60
url: /java/aspose-barcode-for-java-8-0-0-release-notes/
---

Aspose.BarCode for Java has been updated to version 8.0.0 and we are pleased to announce it. The following is a list of changes in this version of Aspose.BarCode.
### **Features and Improvements**

|**Key**|**Summary**|**Category**|
| :- | :- | :- |
|BARCODENET-34365|Add new BarCode type: DutchKix|New Feature|
|BARCODENET-34361|Add new BarCode type: DotCode|New Feature|
|BARCODENET-36213|Barcode not reconginzed if image is not rotated|Enhancement|
|BARCODENET-36212|Unable to Read the barcode from a PNG image having 100 multiple barcodes in it|Enhancement|
|BARCODENET-36210|BarCodeReader not detecting the barcodes with default settings|Enhancement|
|BARCODENET-36208|Support recognition of invasion in the quiet zone for data matrix|Enhancement|
|BARCODENET-36205|Incorrect 3D-distorted Aztec code recognition|Enhancement|
|BARCODENET-36201|Incorrect recognition of the Aztec code|Enhancement|
|BARCODENET-36196|Recognize poor quality code128 with histogram method|Enhancement|
|BARCODENET-36195|Program hangs on Aztec code|Enhancement|
|BARCODENET-36188|Can't recognize 3D-distorted Aztec code from the JPG image|Enhancement|
|BARCODENET-36186|Several consecutive recognitions give different results|Enhancement|
**BARCODENET-34361 Add new BarCode type: DotCode** 
Encoder code sample

{{< highlight xml >}}

 BarCodeBuilder builder = new BarCodeBuilder(@"!AA-11;", Symbology.DotCode);

builder.save("DotCode.png");

{{< /highlight >}}

Decoder code sample

{{< highlight xml >}}

 string filename = "DotCode.png";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.DOT_CODE);

while (reader.read()

{

   System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight xml >}}

 DotCode: !AA-11;

{{< /highlight >}}

**BARCODENET-34365 Add new BarCode type:  DutchKix** 
Support the new symbology DutchKIX has been added to the BarCode API.
Code sample (Encoder)

{{< highlight xml >}}

 BarCodeBuilder builder = new BarCodeBuilder("258369AA", Symbology.DutchKIX);

builder.save("DutchKIX.png");

{{< /highlight >}}

Code sample (Decoder)

{{< highlight xml >}}

 BarCodeReader reader = new BarCodeReader("DutchKIX.png", DecodeType.DUTCH_KIX);

while (reader.read())

{

  System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}


{{< /highlight >}}

Result:

{{< highlight xml >}}

 DutchKIX: 258369AA

{{< /highlight >}}

**BARCODENET-36213 Barcode not reconginzed if image is not rotated** 
Code sample

{{< highlight xml >}}

 BarCodeReader reader = new BarCodeReader("300DPI2.bmp", DecodeType.QR);

while (reader.read())

{

   System.out.println("BarcodeType = " + reader.getCodeType() + ", BarcodeValue = " + reader.getCodeText());

}

reader.close();

{{< /highlight >}}

Result:

{{< highlight xml >}}

 BarcodeType = QR, BarcodeValue = PODD1

{{< /highlight >}}

**BARCODENET-36212 Unable to Read the barcode from a PNG image having 100 multiple barcodes in it [.Net]** 
Code sample

{{< highlight xml >}}

 String filename = "Many_Barcodes_example.png";

int count = 0;

BarCodeReader reader = new BarCodeReader(filename, DecodeType.CODE_128);

while (reader.read())

{

   count++;

   System.out.println(count + " " + reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight xml >}}

 1 Code128: JM100041

2 Code128: JM100047

3 Code128: JM100011

4 Code128: JM100016

5 Code128: JM100045

6 Code128: JM100042

7 Code128: JM100048

8 Code128: JM100012

9 Code128: JM100017

10 Code128: JM100009

11 Code128: JM100043

12 Code128: JM100049

13 Code128: JM100013

14 Code128: JM100018

15 Code128: JM100015

16 Code128: JM100044

17 Code128: JM100008

18 Code128: JM100014

19 Code128: JM100019

20 Code128: JM100020

21 Code128: JM100021

22 Code128: JM100022

23 Code128: JM100023

24 Code128: JM234196

25 Code128: JP100093

26 Code128: RT100099

27 Code128: AE100090

28 Code128: BS100096

29 Code128: GO100092

30 Code128: LG100091

31 Code128: JS100050

32 Code128: JS100051

33 Code128: JS100052

34 Code128: JS100053

35 Code128: JS100054

36 Code128: JS100055

37 Code128: JS100056

38 Code128: JS100057

39 Code128: JS100058

40 Code128: JS100059

41 Code128: JB100147

42 Code128: JB100148

43 Code128: JB100149

44 Code128: JB100150

45 Code128: JB100151

46 Code128: JB100152

47 Code128: JB100153

48 Code128: JB234199

49 Code128: JB234200

50 Code128: DD100060

51 Code128: DD100066

52 Code128: DD100067

53 Code128: JS100036

54 Code128: JS100037

55 Code128: JS100038

56 Code128: JS100039

57 Code128: JS100029

58 Code128: JS100030

59 Code128: JS100031

60 Code128: RD100089

61 Code128: MC100116

62 Code128: MC100117

63 Code128: MC100118

64 Code128: MC100119

65 Code128: MC100120

66 Code128: MC100121

67 Code128: MC100122

68 Code128: MC100123

69 Code128: MC100124

70 Code128: MC100125

71 Code128: MC100126

72 Code128: MC100127

73 Code128: MC100128

74 Code128: MC100129

75 Code128: MC100130

76 Code128: MC100131

77 Code128: MC100132

78 Code128: MC100133

79 Code128: MC100134

80 Code128: MC100135

81 Code128: MC100136

82 Code128: MC100137

83 Code128: JS100032

84 Code128: JS100033

85 Code128: JS100034

86 Code128: JS100035

87 Code128: JS100026

88 Code128: JS100027

89 Code128: JS100028

90 Code128: DD100068

91 Code128: DD100069

92 Code128: DD100070

93 Code128: DD100071

94 Code128: DD100072

95 Code128: DD100073

96 Code128: DD100074

97 Code128: DD100075

98 Code128: DD100076

99 Code128: DD100077

100 Code128: DD100078

101 Code128: DD100079

102 Code128: DD100080

103 Code128: DD100081

104 Code128: DD100082

105 Code128: DD100083

106 Code128: DD100084

107 Code128: DD100085

108 Code128: KS100098

109 Code128: JW100100

110 Code128: BS100095

111 Code128: JR100094

112 Code128: MC100088

113 Code128: MC100113

114 Code128: MC100114

115 Code128: MC100115

116 Code128: PS100097

117 Code128: JS100024

118 Code128: JS100025

{{< /highlight >}}

**BARCODENET-36210 BarCodeReader not detecting the barcodes with default settings** 
Code sample.
Recognize 3 images

{{< highlight xml >}}

 String[] numbers = new String[3]{"18","21","67"};

for (int i = 0; i < 3; i++)

{

    String filename = "20160407-NW-" + numbers[i] + "-1.jpg";

    BarCodeReader reader = new BarCodeReader(filename, DecodeType.QR);

    {

        int found = 0;

        while (reader.read())

	{

            found++;

            System.out.println("BarcodeType = " + reader.getCodeType() + ", BarcodeValue = " + reader.getCodeText());

            System.out.println("Found " + found +" barcodes\r\n");

	}

}

{{< /highlight >}}

Result:

{{< highlight xml >}}

 BarcodeType = QR, BarcodeValue = 20160407-NW-18-1

Found 1 barcodes

BarcodeType = QR, BarcodeValue = 20160407-NW-21-1

Found 1 barcodes

BarcodeType = QR, BarcodeValue = 20160407-NW-67-1

Found 1 barcodes


{{< /highlight >}}

**BARCODENET-36208 Support recognition of invasion in the quiet zone for data matrix** 
Code sample

{{< highlight xml >}}

 String filename = "invasion_quiet_zone.png";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.DATA_MATRIX);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight xml >}}

 DataMatrix: 424D4E4F5631354252430084D6481B209E7DFE24B633BDA1BCB8E499DAD988087585A91B1311DBD8CC82

{{< /highlight >}}

**BARCODENET-36205 Incorrect 3D-distorted Aztec code recognition** 
Code sample:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader("Aztec3d_AaBbCc_3.png", DecodeType.AZTEC);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

Aztec: AaBbCc

**BARCODENET-36201 Incorrect recognition of the Aztec code** 
Code sample:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader("aztec.JPG", DecodeType.AZTEC);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

Aztec: Test code

**BARCODENET-36196 Recognize poor quality code128 with histogram method** 
Code sample

{{< highlight xml >}}

 BarCodeReader reader = new BarCodeReader("DOC00000010.tif", DecodeType.CODE_128);

reader.setRecognitionMode(RecognitionMode.ManualHints);

reader.setManualHints(ManualHint.UseRestoration | ManualHint.IncorrectBarcodes);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight xml >}}

 Must contain

Code128: AZ000000014940

Code128: AZ000000014946

{{< /highlight >}}

{{< highlight xml >}}

 May also contain

Code128: 06191778268813212642

Code128: A,000000014935

Code128: A;:(000&01$9$3

Code128: 01302701192004

Code128: :$C4%4%%$G=$+

Code128: g8E?n35

Code128: :"RE?4BlU

{{< /highlight >}}

**BARCODENET-36195 Program hangs on Aztec code** 
Code sample:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader("badRs.png", DecodeType.AZTEC);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while (reader.read())

{

   System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

Aztec: AaBbCc

**BARCODENET-36188 Can't recognize 3D-distorted Aztec code from the JPG image** 
Code sample:

{{< highlight java >}}

 BarCodeReader reader = new BarCodeReader("aztec-code-in-stein.jpg", DecodeType.AZTEC);

reader.setRecognitionMode(RecognitionMode.MaxQuality);

while (reader.read())

{

   System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

Aztec: www.8qr.de/123aq

**BARCODENET-36186 Several consecutive recognitions give different results** 
Code sample:

{{< highlight java >}}

 for (int i = 0; i < 5; i++)

{

    BarCodeReader reader = new BarCodeReader("378abb43520d76c1a8fbaeb16de3765d_AllSupported.jpg", new MultyDecodeType(DecodeType.GS_1_CODE_128, DecodeType.CODABAR));

    while (reader.read())

    {

        System.out.println(i + " " + reader.getCodeType() + ": " + reader.getCodeText());

    }

}

{{< /highlight >}}

Result:

#0 GS1Code128: (10)7700096376

#1 GS1Code128: (10)7700096376

#2 GS1Code128: (10)7700096376

#3 GS1Code128: (10)7700096376

#4 GS1Code128: (10)7700096376

**BARCODENET-36182 Rows does not work for PDF417 barcode** 
Code sample:

{{< highlight xml >}}

 BarCodeBuilder lnetBarcodeBuilder = new BarCodeBuilder();

lnetBarcodeBuilder.setSymbologyType(Symbology.Pdf417);

lnetBarcodeBuilder.setCodeLocation(CodeLocation.None);

lnetBarcodeBuilder.setPdf417ErrorLevel(Pdf417ErrorLevel.Level5);

lnetBarcodeBuilder.setAspectRatio(3);

lnetBarcodeBuilder.setResolution(new Resolution(100, 100, ResolutionMode.Customized));

if (lnetBarcodeBuilder.getRows() < 30)

    lnetBarcodeBuilder.setRows(30);

lnetBarcodeBuilder.setxDimension(1);

lnetBarcodeBuilder.setyDimension(2.5f);

lnetBarcodeBuilder.setCodeText("ptxtContent");

lnetBarcodeBuilder.setAutoSize(true);

lnetBarcodeBuilder.save("30rows.jpeg", ImageFormat.getJpeg()); //'C:\Temp\Barcode_Output\Output.jpg'

{{< /highlight >}}

ImageWidth and ImageHeight must be increased to avoid small size exception in case AutoSize is set to false.

{{< highlight xml >}}

 lnetBarcodeBuilder.ImageWidth = 200;

lnetBarcodeBuilder.ImageHeight = 100;

{{< /highlight >}}

**BARCODENET-36175 Investigate and fix order of recognizing barcodes** 
Code sample:

{{< highlight java >}}

 String filename = "orderExample.png";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.DATA_MATRIX);

while (reader.read())

{

   String txt = reader.getCodeText();

   System.out.println("Text:" + txt);

}

{{< /highlight >}}

Result:

Text:1

Text:2

Text:3

Text:4

Text:5

Text:6

Text:7

**BARCODENET-36172 Improve dotcode regions recognition** 
The DotCode rotated about 1-5 degrees is recognized by Aspose.BarCodeReader
Code sample

{{< highlight xml >}}

 String filename = "dot-code-rotate1.png";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.DOT_CODE);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

Result:

{{< highlight xml >}}

 DotCode: AAAAA

{{< /highlight >}}

**BARCODENET-34428 Read the corrupted QR barcode** 
Code sample

{{< highlight xml >}}

 String filename = "Page#9.Jpeg";

BarCodeReader reader = new BarCodeReader(filename, DecodeType.QR);

while (reader.read())

{

    System.out.println(reader.getCodeType() + ": " + reader.getCodeText());

}

{{< /highlight >}}

result:

{{< highlight xml >}}

 QR: 1;3544dccb-05a1-4385-81b2-8f06d1863e5b;790ffc2f-1e8c-4cfc-bb01-e1f5bf6a0636;9;22e1678b-df4b-429b-b14b-de0ec2e852d0;a2270c95-a753-4613-87c0-24bc80143ae1

{{< /highlight >}}
