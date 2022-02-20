package com.lab6.addressbook.controller;

import com.lab6.addressbook.model.AddressBookModel;
import com.lab6.addressbook.repository.AddressBookRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GUIController {

    private final AddressBookRepo addressBookRepo;

    public GUIController(AddressBookRepo addressBookRepo) {
        this.addressBookRepo = addressBookRepo;
    }

    @GetMapping
    public String getAddressBook(Model model) {
        AddressBookModel addressBookModel = addressBookRepo.findByName("abm1");
        if (addressBookModel == null) {
            addressBookModel = new AddressBookModel("abm1");
            addressBookRepo.save(addressBookModel);
        }
        model.addAttribute("AddressBookModel", addressBookModel);
        if (addressBookModel.getBuddies() != null) {
            model.addAttribute("Buddies", addressBookModel.getBuddies());
        }
        return "AddressBook";
    }
}
