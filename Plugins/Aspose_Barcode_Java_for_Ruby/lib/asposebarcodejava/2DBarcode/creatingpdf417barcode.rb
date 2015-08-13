module Asposebarcodejava
  module CreatingPdf417Barcode
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Pdf417)

        builder.setCodeText("12345")

        # Save the image to your system and set its image format to Jpeg
        builder.save(data_dir + "CreatingPdf417Barcode.jpg")

        # Display Status
        puts "Created Pdf417 Barcode Successfully."
    end
  end
end
