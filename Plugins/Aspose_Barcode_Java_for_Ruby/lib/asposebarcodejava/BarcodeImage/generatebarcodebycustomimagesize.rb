module Asposebarcodejava
  module GenerateBarcodeByCustomImageSize
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        symbology = Rjb::import('com.aspose.barcode.Symbology')
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("1234567890", symbology.Code39Standard)

        # Set auto size false
        builder.setAutoSize(false)

        # Set height
        builder.setImageHeight(50)

        # Set width
        builder.setImageWidth(120)

        builder.save(data_dir + "GenerateBarcodeByCustomImageSize.jpg")

        # Display Status.
        puts "Generate Barcode By Custom Image Size, please check the output file."
    end
  end
end
