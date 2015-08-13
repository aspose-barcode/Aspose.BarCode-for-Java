module Asposebarcodejava
  module AustraliaPostBarcode
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        symbology = Rjb::import('com.aspose.barcode.Symbology')
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("1234567890", symbology.AustraliaPost)

        # Set format control code to standard
        builder.setAustraliaPostFormatControlCode(Rjb::import('com.aspose.barcode.AustraliaPostFormatControlCode').Standard)

        # Save the image to your system and set its image format to Jpeg
        builder.save(data_dir + "AusraliaPost-Barcode.jpg")

        # Display Status
        puts "Generated Austrailia Post Barcode Successfully."
    end
  end
end
