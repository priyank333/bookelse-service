package com.bookelse.controller.vendor;

import com.bookelse.model.common.Contact;
import com.bookelse.model.common.EmailId;
import com.bookelse.model.common.PersonName;
import com.bookelse.model.id.VendorId;
import com.bookelse.model.vendor.Vendor;
import com.bookelse.payload.vendor.NewVendorRequestPayload;
import com.bookelse.service.VendorService;
import com.bookelse.util.datetime.DateTimeUtility;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vendor")
public class VendorController {

  VendorService vendorService;

  public VendorController(@Qualifier("VendorService") VendorService vendorService) {
    this.vendorService = vendorService;
  }

  @PostMapping("v1/new-vendor")
  public ResponseEntity<Vendor> addNewVendor(
      @RequestBody NewVendorRequestPayload newVendorRequestPayload) {
    Vendor vendor =
        new Vendor(
            new VendorId(),
            new PersonName(newVendorRequestPayload.firstName(), newVendorRequestPayload.lastName()),
            new Contact(newVendorRequestPayload.countryCode(), newVendorRequestPayload.contact()),
            new EmailId(newVendorRequestPayload.emailId()),
            newVendorRequestPayload.isActive(),
            DateTimeUtility.getCurrentUTCDateTime(),
            DateTimeUtility.getCurrentUTCDateTime());
    vendor = vendorService.addNewVendor(vendor);
    return ResponseEntity.status(HttpStatus.CREATED).body(vendor);
  }
}
