package com.example.humanresources.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    @GetMapping("/getall")
    public String getAll(){
        return "Hello World";
    }
}
