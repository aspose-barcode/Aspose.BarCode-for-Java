---
title: Switch BarCode Recognition Modes According to the Requirement in Ruby
type: docs
weight: 80
url: /java/switch-barcode-recognition-modes-according-to-the-requirement-in-ruby/
---

## **Aspose.BarCode - Switch Barcode Recognition Modes According to the Requirement**
To Switch Barcode Recognition Modes According to the Requirement using **Aspose.Barcode Java for Ruby**, simply invoke **SwitchBarcodeRecognitionModes** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

img = data_dir + "barcode.jpg"

\# initialize barcode reader

barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')

reader = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.Code39Standard)

\# Set recognition mode

reader.setRecognitionMode(Rjb::import('com.aspose.barcoderecognition.RecognitionMode').ManualHints)

\# Set manual hints

reader.setManualHints(Rjb::import('com.aspose.barcoderecognition.ManualHint').InvertImage || Rjb::import('com.aspose.barcoderecognition.ManualHint').IncorrectBarcodes)

\# Call read method

while reader.read()

    puts "Barcode CodeText: " + reader.getCodeText().to_s

end

\# Close reader

reader.close()

{{< /highlight >}}
## **Download Running Code**
Download **Switch Barcode Recognition Modes According to the Requirement (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeRecognition/switchbarcoderecognitionmodes.rb)
