module Asposebarcodejava
  module CreatingQRBarcode
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').QR)

        builder.setCodeText("1234567890")

        # Hide code text
        builder.setCodeLocation(Rjb::import('com.aspose.barcode.CodeLocation').None)
        
        builder.setRotationAngleF(90)

        # Save the image to your system and set its image format to Jpeg
        builder.save(data_dir + "CreatingQRBarcode.jpg")

        # Display Status
        puts "Created QR Barcode Successfully."
    end
  end
end
