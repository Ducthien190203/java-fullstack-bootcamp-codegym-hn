package com.codegym.phonemanagementajaxwebservice;

import com.codegym.phonemanagementajaxwebservice.model.Smartphone;
import com.codegym.phonemanagementajaxwebservice.service.ISmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private ISmartphoneService smartphoneService;

    @PostConstruct
    public void init() {
        if (smartphoneService.findAll().spliterator().getExactSizeIfKnown() == 0) {
            smartphoneService.save(new Smartphone("Apple", "iPhone X", 999.99));
            smartphoneService.save(new Smartphone("Samsung", "Samsung Galaxy S21", 799.99));
            smartphoneService.save(new Smartphone("Google", "Google Pixel 6", 699.99));
        }
    }
}
