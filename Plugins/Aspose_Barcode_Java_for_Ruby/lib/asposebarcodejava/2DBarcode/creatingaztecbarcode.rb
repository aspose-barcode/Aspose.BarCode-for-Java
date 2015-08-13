module Asposebarcodejava
  module CreatingAztecBarcode
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Aztec)

        builder.setCodeText("1234567890")

        # Save the image to your system and set its image format to Jpeg
        builder.save(data_dir + "CreatingAztecBarcode.jpg")

        # Display Status
        puts "Created Aztec Barcode Successfully."
    end
  end
end
