module Asposebarcodejava
  module MakingBarcodeRegions
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

        # initialize barcode reader
        img = data_dir + "barcode.jpg"
        barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')
        reader = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.Code39Standard)

        # Try to recognize all possible barcodes in the image
        while reader.read()
            # Display the symbology type
            puts "BarCode Type: " + reader.getReadType().to_s
            # Display the codetext
            puts "BarCode CodeText: " + reader.getCodeText().to_s
            # Get the barcode region
            region = reader.getRegion()
            
            if region != nil
                # Initialize an object of type BufferedImage to get the Graphics object
                bufferedImage = Rjb::import('javax.imageio.ImageIO').read(Rjb::import('java.io.File').new(img))
                
                # Initialize graphics object from the image
                g = bufferedImage.getGraphics()
                
                color = Rjb::import('java.awt.Color')
                # Initialize paint object
                p = Rjb::import('java.awt.GradientPaint').new(0, 0, color.red, 100, 100, color.pink, true)
                         
                region.drawBarCodeEdges(g, color.RED)
                         
                # Save the image
                Rjb::import('javax.imageio.ImageIO').write(bufferedImage, "png", Rjb::import('java.io.File').new(data_dir + "Code39StdOut.png"))
            end
        end

        # Close reader
        reader.close()
    end
  end
end
