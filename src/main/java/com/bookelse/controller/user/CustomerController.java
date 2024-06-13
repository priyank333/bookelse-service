package com.bookelse.controller.user;

import com.bookelse.model.common.Contact;
import com.bookelse.model.common.EmailId;
import com.bookelse.model.common.Password;
import com.bookelse.model.common.PersonName;
import com.bookelse.model.user.Customer;
import com.bookelse.payload.customer.CustomerBankAccountUpdateRequestPayload;
import com.bookelse.payload.customer.CustomerLoginRequestPayload;
import com.bookelse.payload.customer.CustomerPostalAddressUpdateRequestPayload;
import com.bookelse.payload.customer.CustomerRegisterRequestPayload;
import com.bookelse.service.CustomerService;
import com.bookelse.util.datetime.DateTimeUtility;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

  private static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
  private final CustomerService customerService;

  public CustomerController(@Qualifier("CustomerService") CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping("v1/register")
  public ResponseEntity<Customer> registerCustomer(
      @RequestBody CustomerRegisterRequestPayload customerRegisterRequestPayload) {
    if (customerService.isEmailExist(customerRegisterRequestPayload.emailId())) {
      return ResponseEntity.badRequest().body(null);
    }
    Customer customer =
        new Customer(
            Password.createNewPassword(customerRegisterRequestPayload.password()),
            new PersonName(
                customerRegisterRequestPayload.firstName(),
                customerRegisterRequestPayload.lastName()),
            new EmailId(customerRegisterRequestPayload.emailId()),
            new Contact(
                customerRegisterRequestPayload.countryCode(),
                customerRegisterRequestPayload.contact()),
            DateTimeUtility.getCurrentUTCDateTime(),
            DateTimeUtility.getCurrentUTCDateTime(),
            customerRegisterRequestPayload.zoneId());
    customer = customerService.registerUser(customer);
    return ResponseEntity.status(HttpStatus.OK).body(customer);
  }

  @GetMapping("v1/list")
  public ResponseEntity<List<Customer>> list() {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.listAllUsers());
  }

  @PostMapping("v1/login")
  public ResponseEntity<Boolean> login(
      @RequestBody CustomerLoginRequestPayload customerLoginRequestPayload) {
    Boolean result =
        customerService.validateUserCredentials(
            customerLoginRequestPayload.emailId(), customerLoginRequestPayload.password());
    if (result) return ResponseEntity.status(HttpStatus.OK).body(true);
    else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
  }

  @GetMapping("v1/list/{customerId}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") String id) {
    LOGGER.info("get customer by id: {}", id);
    Customer customer = customerService.getUserById(id);
    return ResponseEntity.status(HttpStatus.OK).body(customer);
  }

  @PutMapping("v1/update-postal-address")
  public ResponseEntity<Boolean> updatePostalAddress(
      @RequestBody
          CustomerPostalAddressUpdateRequestPayload customerPostalAddressUpdateRequestPayload) {
    Boolean isUpdated = false;
    if (customerService.isUserExist(customerPostalAddressUpdateRequestPayload.customerId())) {
      isUpdated = customerService.updatePostalAddress(customerPostalAddressUpdateRequestPayload);
    }
    return ResponseEntity.status(isUpdated ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
        .body(isUpdated);
  }

  @PutMapping("v1/update-bank-acc")
  public ResponseEntity<Boolean> updateBankAccount(
      @RequestBody
          CustomerBankAccountUpdateRequestPayload customerBankAccountUpdateRequestPayload) {
    Boolean isUpdated = false;
    if (customerService.isUserExist(customerBankAccountUpdateRequestPayload.customerId())) {
      isUpdated = customerService.updateBankAccount(customerBankAccountUpdateRequestPayload);
    }
    return ResponseEntity.status(isUpdated ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value())
        .body(isUpdated);
  }
}
