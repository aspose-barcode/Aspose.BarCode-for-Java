module Asposebarcodejava
  module ManageCaption
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        # Set up code text (data to be encoded)
        bb.setCodeText("1234567")

        # Set the symbology type to Code128
        bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code128)

        caption = Rjb::import('com.aspose.barcode.Caption').new
        caption.setText("Captions")
        caption.setTextAlign(Rjb::import('com.aspose.barcode.Alignment').Middle)

        bb.setCaptionAbove(caption)
        bb.setCaptionBelow(caption)

        bb.getCaptionAbove().setTextAlign(Rjb::import('com.aspose.barcode.Alignment').Left)
        bb.getCaptionAbove().setText("Aspose")
        bb.getCaptionAbove().setVisible(true)
        #bb.getCaptionAbove().setFont(new java.awt.Font("Pristina", java.awt.Font.PLAIN, 14));
        #bb.getCaptionAbove().setForeColor(java.awt.Color.RED);

        bb.getCaptionBelow().setTextAlign(Rjb::import('com.aspose.barcode.Alignment').Right)
        bb.getCaptionBelow().setText("Aspose.BarCode Caption Below")
        bb.getCaptionBelow().setVisible(true)
        #bb.getCaptionBelow().setFont(new java.awt.Font("Pristina", Font.PLAIN, 14));
        #bb.getCaptionBelow().setForeColor(java.awt.Color.RED);

        # Save the image to your system and set its image format to Jpeg
        bb.save(data_dir + "barcode.jpg", Rjb::import('com.aspose.barcode.BarCodeImageFormat').Jpeg)

        # Display Status
        puts "Barcode with Captions saved successfully."
    end
  end
end
