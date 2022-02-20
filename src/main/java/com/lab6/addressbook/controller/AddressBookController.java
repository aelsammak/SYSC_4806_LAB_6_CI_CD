package com.lab6.addressbook.controller;

import com.lab6.addressbook.model.BuddyInfoModel;
import com.lab6.addressbook.service.AddressBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v0/addressbook")
public class AddressBookController {

    private final AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @PostMapping("/{addressBookName}/buddies")
    public ResponseEntity<?> create(@PathVariable("addressBookName") String addressBookName,
                                    @RequestBody BuddyInfoModel buddyInfoModel) {
        try {
            addressBookService.saveBuddy(addressBookName, buddyInfoModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v0/addressbook").toUriString());
        return ResponseEntity.created(uri).body(buddyInfoModel);
    }

    @GetMapping("/{addressBookName}/buddies")
    public ResponseEntity<?> get(@PathVariable("addressBookName") String addressBookName) {
        List<BuddyInfoModel> buddies;
        try {
            buddies = addressBookService.getBuddies(addressBookName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No buddies found for AddressBook with name of " + addressBookName);
        }
        return ResponseEntity.ok().body(buddies);
    }

    @PutMapping("/{addressBookName}/buddies/{buddyName}")
    public ResponseEntity<?> update(@PathVariable("addressBookName") String addressBookName,
                                    @PathVariable("buddyName") String buddyName,
                                    @RequestBody Map<String, String> values) {
        try {
            addressBookService.updateBuddyAddress(addressBookName, buddyName, values.get("newAddress"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("New Address has been updated to buddy named " + buddyName);
    }

    @DeleteMapping("/{addressBookName}/buddies/{buddyName}")
    public ResponseEntity<?> delete(@PathVariable("addressBookName") String addressBookName,
                                    @PathVariable("buddyName") String buddyName) {
        try {
            addressBookService.removeBuddy(addressBookName, buddyName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Buddy named " + buddyName + " has been deleted!");
    }


}
