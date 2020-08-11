---
title: 1.2.2.1. Resizing or Scaling QR Code
type: docs
weight: 10
url: /java/1-2-2-1-resizing-or-scaling-qr-code/
---

## **Resizing or Scaling QR Code**
To generate a QR Code with customize sized image, please refer to the following steps:

1. Select the Type
1. Input the QR Code content data
1. Use the size slider to set the size of the image under settings section
1. Click **Generate Preview** button

That's It. You will see the generated QR Code of your customize sized image under preview section.

Note: Resizing or Scaling is a paid feature of [Aspose.BarCode for Java](http://www.aspose.com/java/barcode-component.aspx) library. Please check [1.3.2. Apply Aspose License](/barcode/java/1-3-2-apply-aspose-license-html/) to apply aspose license in the project. 
## **How it works?**
#### **API**
{{< highlight java >}}

 GET /qrcodegen/api/qrcode/generate?data=<URL>&foreColor=&bgColor=&ecc=&size=150x150&format=

Example:

http://localhost:8080/qrcodegen/api/qrcode/generate?data=http://aspose.com&foreColor=&bgColor=&ecc=&size=150x150&format=


{{< /highlight >}}
#### **Java Script**
***addRequestSettings*** function is used to add customize QR Code settings options in the API request parameters. Here is the code spinet. 

{{< highlight java >}}

  addRequestSettings : function(requestString){

 requestString = requestString + "&ecc=" + this.$('.errorCorrectionCode option:selected').text();

 requestString = requestString + "&foreColor=" + encodeURIComponent(this.$('.input-fcolor').val());

 requestString = requestString + "&bgColor=" + encodeURIComponent(this.$('.input-bgcolor').val());

 if(this.$('.input-size').val() != "100x100")

 requestString = requestString + "&size=" + this.$('.input-size').val();

 return requestString;

 }

{{< /highlight >}}
#### **Java**
**Core API Method - QRCodeManagementController.generateQRCode** 

{{< highlight java >}}

 @RequestMapping(value = "generate", method = RequestMethod.GET,

    		produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType_IMAGE_TIFF_VALUE, MediaType_IMAGE_BMP_VALUE})

    @ApiOperation(value = "Generate QR Code.")

    public ResponseEntity<byte[]>  generateQRCode(

    		@ApiParam( value = "data", name="data" , required = true)

    		@RequestParam("data") String data,

        HttpServletRequest request,HttpServletResponse response,

        @ApiParam( value = "A user-chosen password that can be used with password-based encryption (PBE) Algo PBEWITHMD5AND128BITAES-CBC-OPENSSL)", name="passKey", required=false) @RequestParam(required=false, value="passKey") String passKey,

        @ApiParam( value = "ForeColor e.g #000000 (Black - RGB(hex))", name="foreColor", required=false) @RequestParam(required=false, value="foreColor") String foreColor,

        @ApiParam( value = "BackgroundColor e.g #FFFFFF (White - RGB(hex))", name="bgColor", required=false) @RequestParam(required=false, value="bgColor") String bgColor,

        @ApiParam( value = "L|M|Q|H - Reed-Solomon error correctionCode Level(from low to high) default=Low", name="ecc", required=false) @RequestParam(required=false, value="ecc") String ecc,

        @ApiParam( value = "Image Size e.g #150x150", name="size", required=false) @RequestParam(required=false, value="size") String size,

        @ApiParam( value = "jpeg|tiff|gif|png|bmp - default=png", name="format", required=false) @RequestParam(required=false, value="format") String format,

        @ApiParam( value = "true|false default=false", name="download", required=false) @RequestParam(required=false, value="download") boolean download,

        @ApiIgnore @Value("#{request.getHeader('" +  ACCEPT_HEADER + "')}") String acceptHeaderValue) throws Exception {


{{< /highlight >}}

**Resizing or Scaling Code Spinet** - **QRCodeManagementController.generateQRCode**

{{< highlight java >}}

 	        // Set graphics unit

		builder.setGraphicsUnit(GraphicsUnit.Millimeter);

		// Set margins

		builder.getMargins().set(0);

		builder.setForeColor(getColorValue(foreColor, "#000000"));

		builder.setBackColor(getColorValue(bgColor, "#FFFFFF"));

		logger.debug("builder.getGraphicsUnit() ::" + builder.getGraphicsUnit());

		// Get BufferedImage with exact bar code only

		BufferedImage img = builder.getOnlyBarCodeImage();

		logger.debug("img.getWidth() : :" + img.getWidth());

		logger.debug("img.getHeight() :: " + img.getHeight());

		if(imageDimention.getWidth() < img.getWidth())

		   imageDimention.width = img.getWidth();

		if(imageDimention.getHeight() < img.getHeight())

		 	imageDimention.height = img.getHeight();

	        BufferedImage img2 = builder.getCustomSizeBarCodeImage(imageDimention, true);

   	        MediaType responseType = responseImageTypeDto.getMediaType();

	        ImageIO.write(img2, responseType.getSubtype(), baos);

                baos.flush();

		imageInByte = baos.toByteArray();

		baos.close();


{{< /highlight >}}
