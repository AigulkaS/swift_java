package com.work1.controllers;

import com.work1.dao.HiberDAO;
import com.work1.model.Delivery;
import com.work1.model.Product;
import com.work1.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainRestController {

    @Autowired
    HiberDAO dao;

    @RequestMapping(value = "/allsales", method = RequestMethod.GET, produces = {"application/json"})
    List<Sale> getAllSales() {
        return dao.getAllSales();
    }

    @RequestMapping(value = "/alldeliveries", method = RequestMethod.GET, produces = {"application/json"})
    List<Delivery> getAllDeliveries() {
        return dao.getAllDeliveries();
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = {"application/json"})
    Product getProducrO(@PathVariable("id") long id) {
        return dao.getProductById(id);
    }
}
