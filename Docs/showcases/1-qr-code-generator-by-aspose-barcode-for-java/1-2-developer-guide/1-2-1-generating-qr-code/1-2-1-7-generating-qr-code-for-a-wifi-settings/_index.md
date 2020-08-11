---
title: 1.2.1.7. Generating QR Code for a Wifi Settings
type: docs
weight: 70
url: /java/1-2-1-7-generating-qr-code-for-a-wifi-settings/
---

## **How to generate QR Code for a Wifi Settings?**
To generate a QR Code for a wifi settings, please refer to the following steps:

1. Select the Type: **Wifi**
1. Fill out the content section: Wifi
1. Customize QR Code Settings (Optional)
1. Click **Generate Preview** button

That's It. You will see the generated QR Code under the preview section.
## **How it works?**
**Wifi Settings Format**

WIFI:T:<AUTHENTICATION TYPE>;S:<SSID>;P:<PASSWORD>;H:<TRUE|FALSE>;
#### **API**
{{< highlight java >}}

 GET /qrcodegen/api/qrcode/generate?data=<WIFI Settings Format>&foreColor=&bgColor=&ecc=&size=&format=

Sample Wifi Setting Message

\---------------------------

WIFI:T:WPA;S:ASPOSE_SSID;P:aspose;;

API Example:

\------------

http://localhost:8080/qrcodegen/api/qrcode/generate?data=WIFI%3AT%3AWPA%3BS%3AASPOSE_SSID%3BP%3Aaspose%3B%3B&foreColor=&bgColor=&ecc=&size=&format=


{{< /highlight >}}

**Generated SMS QR Code**

|<p>![todo:image_alt_text](1-2-1-7-generating-qr-code-for-a-wifi-settings_1.png)</p><p></p>|
| :- |
#### **Java Script**
JS code spinet to generate Wifi settings format from content input fields.

{{< highlight java >}}

  if(page == 'wifi'){



     var requestString= "WIFI:";

     requestString = requestString + "T:" + this.$('.input-wifi-encryption-type option:selected').val() + ";" + "S:" + this.$('.input-wifi-ssid').val() +  ";" + "P:"+ this.$('.input-wifi-pass').val() +";" ;



      if(this.$('.input-wifi-hidden').is(':checked'))

      requestString = requestString + "H:true;"

      else

      requestString = requestString + ";"



     requestString = encodeURIComponent(requestString);

     requestString = this.addRequestSettings(requestString)



     var that = this;



     this.$('.qrcode-preview-image').load(function(){

     that.$('.qrcode-preview-processing').addClass('hide');

     that.$('.qrcode-preview-image').removeClass('hide');

     })    

     .error(function(){

     that.$('.qrcode-preview-processing').attr('src', '/qrcodegen/resources/imgs/error.png');

     })

     .attr('src', '/qrcodegen/api/qrcode/generate?data='+ requestString);



     this.updateDownloadTag(requestString);

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
