---
title: BarCode Orientation in Ruby
type: docs
weight: 10
url: /java/barcode-orientation-in-ruby/
---

## **Aspose.BarCode - Barcode Orientation**
To do Barcode Orientation using **Aspose.Barcode Java for Ruby**, simply invoke **BarcodeOrientation** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

\# initialize barcode reader

img = data_dir + "orientation.jpg"

barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')

reader = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.Code39Standard)

\# Set orientation

reader.setOrientationHints(Rjb::import('com.aspose.barcoderecognition.RecognitionHints$Orientation').Rotate90)

\# Try to recognize all possible barcodes in the image

while reader.read()

    # print the code text, if barcode found

    puts "CodeText: " + reader.getCodeText().to_s

end

\# Close reader

reader.close()

{{< /highlight >}}
## **Download Running Code**
Download **Barcode Orientation (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeRecognition/barcodeorientation.rb)
