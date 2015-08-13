module Asposebarcodejava
  module ReadBarcodeFromSpecificRegion
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

        # Open the stream. Read only access is enough for Aspose.BarCode to load an image.
        stream = Rjb::import('java.io.FileInputStream').new(data_dir + "test.png")

        # Create an instance of BarCodeReader class
        # and specify an area to look for the barcode   
        barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')
        reader = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(stream, Rjb::import('java.awt.Rectangle').new(0, 0, 10, 50), barcode_reader_type.Code39Standard)

        # TRead all barcodes in the provided area
        while reader.read() == true
            # Display the codetext and symbology type of the barcode found
            puts "Codetext: " + reader.getCodeText().to_s + " Symbology: " + reader.getReadType().to_s
        end

        # Close reader
        reader.close()
    end
  end
end
