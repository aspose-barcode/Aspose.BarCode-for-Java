---
title: Aspose.BarCode for Java 7.5.0 Release Notes
type: docs
weight: 110
url: /java/aspose-barcode-for-java-7-5-0-release-notes/
---

Aspose.BarCode for Java has been updated to version 7.5.0 and we are pleased to announce it.
## **New Features**
BARCODENET-34310 Implement GS1 QR decoding Enhancements 
BARCODJAVA-33592 Implement getting readable type name from 
BarcodeReadType BARCODJAVA-33507 Can't recognize Aztec code from the JPG image 
BARCODJAVA-33506 Can't recognize Aztec code from the JPG image
BARCODENET-34309 Reorganize the logic of recognition in manual hint mode to speed up the detection
BARCODENET-33812 Add support to encode GS1QR code
BARCODENET-34319 DataBarStackedOmniDirectional encoding, BarCodeBuilder.BarHeight property does not work properly
BARCODENET-34311 Unwanted White Space at the bottom of Barcode Image 
BARCODENET-34295 AI Codes Not Accepting Valid Values for GS1DataMatrix
BARCODENET-34294 AI Codes Not Accepting Valid Values for GS1Code128 
BARCODENET-34292 Barcode Reader skips a binary character 
BARCODENET-34163 Can't recognize Aztec code from the JPG image 
BARCODENET-34162 Can't recognize Aztec code from the JPG image
BARCODENET-33843 Can't recognize all DataMatrix/Code128 codes from a tif file 
BARCODENET-33827 Can't recognize DataMarix code and incorrect text detection of Code 128 
BARCODENET-33250 Implement a BarCodeReader constructor that could accept a list of Rectangles
## **Usage examples:**
BARCODJAVA-33592 Implement getting readable type name from BarcodeReadType

Implements method representing BarcodeReadType name as follows:

{{< highlight java >}}

 'BarCode type : BarCode text'


long expectedType = BarCodeReadType.DataMatrix;

String expectedResult =

"doc:3f820419c87d4a878bafd17d2add8d2d009"

;

BarCodeReader reader = new BarCodeReader(folder +

"/image0.jpg"

, BarCodeReadType.DataMatrix);

while (reader.read())

{

   System.out.println(reader.getReadTypeName() +

" : "

\+ reader.getCodeText());

}

DataMatrix : doc:3f820419c87d4a878bafd17d2add8d2d009

 &nbsp;


{{< /highlight >}}

BARCODENET-33812 Add support to encode GS1QR code

Code sample:

{{< highlight java >}}

 String text =

"(01)04912345123459(15)970331(30)128(10)ABC123"

;

BarCodeBuilder builder = new BarCodeBuilder(text, Symbology.GS1QR);

builder.save(

"GS1QR.png"

, BarCodeImageFormat.Png);

{{< /highlight >}}

BARCODENET-34310 Implement GS1 QR decoding

Code sample

{{< highlight java >}}



String expectedResult =

"(01)04912345123459(15)970331(30)128(10)ABC123"

;

BarCodeReader reader = new BarCodeReader(

"GS1QR.png"

, BarCodeReadType.GS1QR);

reader.read();

Result:

GS1QR: (01)04912345123459(15)970331(30)128(10)ABC123

{{< /highlight >}}

BARCODENET-34319 DataBarStackedOmniDirectional encoding, BarCodeBuilder.BarHeight property does not work properly

Code sample:

{{< highlight java >}}



BarCodeBuilder b = new BarCodeBuilder(

"(01)90013670000396(3200)15(11)150819"

);

b.setSymbologyType(Symbology.DatabarStackedOmniDirectional);

b.setxDimension(0.330f);

b.setBarHeight(27.77f);

b.setResolution(new Resolution(1200.0f, 1200.0f, ResolutionMode.Customized));

b.save(

"Barheight.png"

, BarCodeImageFormat.Png);

Results in case of resolution 1200:

xDimension: 0.264, BarHeight: 18.22 --&gt; actual BarHeight: 18.20

xDimension: 0.264, BarHeight: 18.24 --&gt; actual BarHeight: 18.25

xDimension: 0.330, BarHeight: 27.77 --&gt; actual BarHeight: 27.77

xDimension: 0.330, BarHeight: 27.78 --&gt; actual BarHeight: 27.77

xDimension: 0.495, BarHeight: 34.16 --&gt; actual BarHeight: 34.14

xDimension: 0.660, BarHeight: 45.54 --&gt; actual BarHeight: 45.53

xDimension: 0.660, BarHeight: 62.70 --&gt; actual BarHeight: 62.72

Offset can be 1px to hold equal height of rows, and 0.5 px is a rounding error.


Max offset 1.5px = 0.03mm for 1200 DPI.

 1mm - 48px


1mm - 48px



1px - 0.02 mm


1px - 0.02 mm



1.5px - 0.03mm


1.5px - 0.03mm

{{< /highlight >}}

BARCODENET-34311 Unwanted White Space at the bottom of Barcode Image

Code sample

{{< highlight java >}}



BarCodeBuilder builder = new BarCodeBuilder();

builder.setSupplementSpace(0);

builder.setCodeTextSpace(0);

builder.setMargins(new MarginsF(0, 0, 0, 0));

builder.setSymbologyType(Symbology.EAN13);

builder.setCodeText(

"000080283188"

);

builder.save(

"image.bmp"

, BarCodeImageFormat.Png);

 To set barcode height or width, please use BarHeight and xDimension properties.


Changing ImageHeight or ImageWidth will not affect the barcode size,


but Only image size will be affected, in case Autosize has been set to false.

{{< /highlight >}}

BARCODENET-34309 Reorganize the logic of recognition in manual hint mode to speed up the detection

The calculated time using manual hints must be lesser than using max quality mode.

Code sample (manual hints )

{{< highlight java >}}



long expectedBarcodeReadType = BarCodeReadType.DataMatrix;

String expectedCodeText =

":AAAAvGg0ByEJ9w|ac"

;

BarCodeReader reader = new BarCodeReader(

"Untitled22.jpg"

, BarCodeReadType.DataMatrix);

reader.setRecognitionMode(RecognitionMode.ManualHints);

reader.setManualHints(ManualHint.MedianSmoothing);

reader.read();

Result:

DataMatrix: :AAAAvGg0ByEJ9w|ac

Time: *2344ms*.

{{< /highlight >}}

BARCODENET-34295 AI Codes Not Accepting Valid Values for GS1DataMatrix

Code sample:

{{< highlight java >}}



BarCodeBuilder builder = new BarCodeBuilder(

"(01)90013670000396(3400)123456"

); // Or any of the above values

builder.setSymbologyType(Symbology.GS1DataMatrix);

builder.save(

"GS1DataMatrix.png"

, BarCodeImageFormat.Png);

BARCODENET-34294 AI Codes Not Accepting Valid Values for  GS1Code128


{{< /highlight >}}

Code sample:

{{< highlight java >}}



BarCodeBuilder b = new BarCodeBuilder(

"(01)90013670000396(3400)123456"

); // Or any of the above values

b.setSymbologyType(Symbology.GS1Code128); // Or GS1DataMatrix

b.save(

"OutGS1Code128.png"

, BarCodeImageFormat.Png);

{{< /highlight >}}

BARCODENET-34292 Barcode Reader skips a binary character

Code sample

{{< highlight java >}}



BarCodeReader reader = new BarCodeReader(

"TestY.jpg"

, BarCodeReadType.Pdf417);

reader.read();

Result:

30 33 32 35 37 32 31 35 38 31 00 00 00 00 00 00

00 00 00 00 00 00 00 00 50 75 62 44 53 4b 5f 31

00 00 00 00 00 00 00 00 31 34 33 38 33 35 34 31

31 30 31 33 35 38 38 39 38 36 4c 4f 4d 42 41 4e

41 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00

00 4d 41 52 54 49 4e 45 5a 00 00 00 00 00 00 00

00 00 00 00 00 00 00 00 4d 41 52 49 41 00 00 00

00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 59

49 4e 45 54 54 00 00 00 00 00 00 00 00 00 00 00

00 00 00 00 00 00 30 46 31 39 38 37 30 35 32 37

31 35 30 30 31 30 4f 2b 00 32 02 43 00 b6 54 ff

80 80 83 85 4d ab 74 cd 42 a1 3b 81 a9 83 b7 75

bc 6f a7 75 b5 66 b6 47 9c 78 a6 4f b0 5d 30 65

a8 5c 3a 61 91 41 94 54 4b 5c 56 62 5b 71 93 74

6c 4f 89 53 90 62 91 70 7e 72 58 3f 72 5a 83 bf

83 b7 57 ac 72 96 8c 9f 91 b2 8d 97 9b ae 2c a7

96 8d 9a bc 9c a3 a0 95 a5 c0 aa a7 49 8f 98 cf

ad 96 af ac 28 87 9f 88 ad bf b4 90 b5 ac c9 7a

30 81 b5 7e b9 63 3f 81 36 5e 5b 75 6f 10 49 5b

58 70 5b 58 66 54 7d 7b ef ba 1b a3 24 85 50 fe

57 48 2c a2 90 d4 25 e6 ff 00 37 02 43 00 d1 54

ff 80 80 63 a5 46 97 46 9f 86 83 50 92 38 96 30

81 94 a7 a3 ae d3 4e ac 7f ca 5d 9d 70 a4 6a ca

42 da 6f a2 5a e1 84 50 70 96 62 a2 4f 96 59 9d

41 89 4b 89 6a 8d 62 4f 5f 7d 3e 82 45 8c 5a 73

43 54 61 6a 62 7b 57 6f a9 78 b8 82 95 8c 83 50

9d 51 c3 60 9a 83 8c 8a b8 8e 8e 8e a0 32 9e 89

ad 9c a8 9f 94 9d a0 9b b6 a9 b3 b3 a7 97 c5 9b

81 a5 be a5 78 a7 86 b7 92 c9 76 dc 7e d3 74 3e

76 97 4d 68 4a 51 61 7a 75 6f 54 6a 62 49 55 42

f1 bf 12 10 15 29 d4 f6 75 ff 00 01 f1 f2 11 96

bd 20 e2 61 89 95 b8 ed 7e 79 aa 19 c8 26 17 79

36 e5 e5 e6 6f 89 69 f5 9f b1 a1 77 ec e7 c5 29

46 0d 33

{{< /highlight >}}

BARCODENET-34163, BARCODJAVA-33507 Can't recognize Aztec code from the JPG image

Code sample

{{< highlight java >}}



String filename =

"test1_orig.jpg"

;

BarCodeReader reader = new BarCodeReader(filename, BarCodeReadType.Aztec);

reader.read();

 Result


Result

Aztec: IwQAANtYAAJDAP8xAHwAQgBBAPtLADAAORr3NgA4Ar00KjHaCho19y4zIm8eUABSAP5FAFoAWQBE/w5OAFQAIPcATQAuCu9TAFQPvVeOUu8aWg5Xe058UrUWSe9aTF5Je9pBSt1CErsOQQFPBvcYAUsWbk5VAEz2g569T2ZM1lcBjr5LXzEOLjfgRtcPLRrtMgAy1xuHr1dba1I2Tt8zY1R7mlkG3lQ/zQND7x5SBkzfAExfMnvuMA7eRLY07QpCSnBe5w8bQUVa2DAD7zWuMg83uDIadD8tESveMCot9gMOg0fbwUH7d0u6BpODVGOHf0FOH9gnBaFzBmO8OLtaDzL4BzKtP5bzGrw59zAfSyMS0LpF8b9Ni0q75zh+3kMONQhzqDbS7DcANBNYoR8eZ4o1Qw2GDzLxO0+7fAA0YkvH8685JixvPzYAsTYX8UQAINgP8BP0DzQfpzMAsHz3cFNoUsCru05IANNr7k8m0wZCpkdBF0uXLSM3OdqXN0F9AzheQzjwvjbdjzJWQ+4DOAAwSwzLs0d8aydmA05C2TO+OQsyW3eCSSSSSoD/AA==f

{{< /highlight >}}

BARCODENET-34162, BARCODJAVA-33506 Can't recognize Aztec code from the JPG image

Code sample

{{< highlight java >}}



String filename =

"test_photo.jpg"

;

BarCodeReader reader = new BarCodeReader(filename, BarCodeReadType.Aztec);

reader.read();

result:

Aztec: abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

abcdefghijklmnopqrstuvwxyz

{{< /highlight >}}

BARCODENET-33843 Can't recognize all DataMatrix/Code128 codes from a tif file

Improved recognition.

Now we can recognize all DataMatrix barcode (without MaxQuality).
Sample code:

{{< highlight java >}}





BarCodeReader reader =

new

BarCodeReader(

"DOC00000010.tif"

, BarCodeReadType.DataMatrix);

while

(reader.read())

System

.out.println(reader.getCodeText());}

Result:

1 - Code Text: AZ000000014946 - Type: DataMatrix

2 - Code Text: AZ000000014932 - Type: DataMatrix

3 - Code Text: AZ000000014941 - Type: DataMatrix

4 - Code Text: AZ000000014944 - Type: DataMatrix

5 - Code Text: AZ000000014933 - Type: DataMatrix

6 - Code Text: AZ000000014934 - Type: DataMatrix

7 - Code Text: AZ000000014939 - Type: DataMatrix

8 - Code Text: AZ000000014940 - Type: DataMatrix

9 - Code Text: AZ000000014947 - Type: DataMatrix

10 - Code Text: AZ000000014936 - Type: DataMatrix

11 - Code Text: AZ000000014948 - Type: DataMatrix

12 - Code Text: AZ000000014942 - Type: DataMatrix

13 - Code Text: AZ000000014938 - Type: DataMatrix

14 - Code Text: AZ000000014935 - Type: DataMatrix

15 - Code Text: AZ000000014937 - Type: DataMatrix

16 - Code Text: AZ000000014945 - Type: DataMatrix

17 - Code Text: AZ000000014931 - Type: DataMatrix

18 - Code Text: AZ000000014930 - Type: DataMatrix

19 - Code Text: AZ000000014943 - Type: DataMatrix

{{< /highlight >}}

BARCODENET-33250 Implement a BarCodeReader constructor that could accept a list of Rectangles

Code sample

{{< highlight java >}}



String filename =

"Three_Barcodes.png"

;

BufferedImage img = null;

img = ImageIO.read(new File(filename));

Rectangle\[\] areas = new Rectangle[3];

areas[0] = new Rectangle(50, 50, 300, 300);

areas[1] = new Rectangle(400, 40, 350, 350);

areas[2] = new Rectangle(40, 500, 700, 150);

BarCodeReader reader = new BarCodeReader(img, areas, BarCodeReadType.AllSupportedTypes);

reader.read();

Result:

Interleaved2of5: 01234321

DataMatrix: Pegasus Imaging - BarcodeXpress 5 - 1D and 2D barcode reader/writer

OneCode: 44123123456123456789

{{< /highlight >}}
