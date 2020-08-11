---
title: 1.2.1.6. Generating QR Code for an Event
type: docs
weight: 50
url: /java/1-2-1-6-generating-qr-code-for-an-event/
---

## **How to generate QR Code for an Event?**
To generate a QR Code for a URL, please refer to the following steps:

1. Select the Type: **Event**
1. Fill out the content section: Event
1. Customize QR Code Settings (Optional)
1. Click **Generate Preview** button

That's It. You will see the generated QR Code under the preview section.
## **How it works?**
**Event Format**

BEGIN:VEVENT
SUMMARY:<Event Summary>
DTSTART:<Event Start Date Time>
DTEND:<Event End Date Time>
END:VEVENT
#### **API**
{{< highlight java >}}

 GET /qrcodegen/api/qrcode/generate?data=<SMSTO:Phone Number:MESSAGE>&foreColor=&bgColor=&ecc=&size=&format=

Sample Event Message

\------------------

BEGIN:VEVENT

SUMMARY:Independence Day Parades

DTSTART:20150323T090000

DTEND:20150323T110000

END:VEVENT


API Example:

\------------

http://localhost:8080/qrcodegen/api/qrcode/generate?data=BEGIN%3AVEVENT%0D%0ASUMMARY%3AIndependence Day Parades %0D%0ADTSTART%3A20150323T090000%0D%0ADTEND%3A20150323T110000%0D%0AEND%3AVEVENT&foreColor=&bgColor=&ecc=&size=&format=


{{< /highlight >}}

**Generated SMS QR Code**

|<p>![todo:image_alt_text](1-2-1-6-generating-qr-code-for-an-event_1.png)</p><p></p>|
| :- |
#### **Java Script**
JS code spinet to generate Event format from content input fields.

{{< highlight java >}}

  generateEvent : function(){

    	 var beginEvent = "BEGIN:VEVENT\r\n";

		 var endEvent = "END:VEVENT";

		 var eventDesc = "SUMMARY:" + this.$('.input-event-description').val() + "\r\n";

		 var eventStart = "DTSTART:" + this.$('.input-event-fromdate').val() + "\r\n";

		 var eventEnd = "DTEND:" + this.$('.input-event-todate').val() + "\r\n";

		 var generatedEvent = beginEvent + eventDesc + eventStart + eventEnd + endEvent;

		 return generatedEvent;

    },

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
