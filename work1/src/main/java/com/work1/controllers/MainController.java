package com.work1.controllers;

import com.work1.dao.HiberDAO;
import com.work1.model.Available;
import com.work1.model.Product;
import com.work1.model.Storage;
import com.work1.model.UsersOrder;
import com.work1.services.LoginService;
import com.work1.services.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    HiberDAO dao;

    @Autowired
    PharmacyService svc;

    @Autowired
    LoginService loginService;

    @ModelAttribute("user")
    public String getUser() {
        return loginService.getUserName();
    }

    @RequestMapping("showForUser.do")
    public ModelAndView showForUser() {
        ModelAndView mv = new ModelAndView("userdata");
        mv.addObject("products", dao.getAllProducts());
        int userProdCount = dao.getUserOrderByName(getUser()).size();
        mv.addObject("userProdCount", "Корзина: " + userProdCount);
        int count = dao.getUserOrderExpectCount(getUser());
        mv.addObject("textInfo", count == 0 ? "" : "Ожидают подтверждения :"+count);
        return mv;
    }


    @RequestMapping("showForAdmin.do")
    public ModelAndView showForAdmin() {
        return showAll();
    }

    private ModelAndView showAll() {
        ModelAndView mv = new ModelAndView("admindata");
        mv.addObject("products", dao.getAllProducts());
        mv.addObject("storages", dao.getAllStorages());
        return mv;
    }


    @RequestMapping("addProduct.do")
    public ModelAndView addProd(String name, double price) {
        dao.addProduct(name, price);
        return showAll();
    }

    @RequestMapping("delProduct.do")
    public ModelAndView delProd(long id) {
        dao.delProduct(id);
        return showAll();
    }

    @RequestMapping("addStorage.do")
    public ModelAndView addStor(String name, String address) {
        dao.addStorage(name, address);
        return showAll();
    }

    @RequestMapping("delStorage.do")
    public ModelAndView delStor(long id) {
        dao.delStorage(id);
        return showAll();
    }


    @RequestMapping("setOrder.do")
    public ModelAndView saleSet(long hidpr) {
        Product product = dao.getProductById(hidpr);
        ModelAndView mv = new ModelAndView("orderData");
        mv.addObject("oneProduct", product);
        return mv;
    }

    @RequestMapping("toOrder.do")
    public String sale(long hidpr, int amount) {
        svc.saleProd(hidpr, amount, getUser());
        return "redirect:showForUser.do";
    }

    @RequestMapping("showBasket.do")
    public ModelAndView showBasket() {
        ModelAndView mv = new ModelAndView("userbasket");
        List<UsersOrder> usersOrders = dao.getUserOrderByName(getUser());
        String[] info = svc.calcSumAndCount(usersOrders);
        mv.addObject("userProducts", usersOrders);
        mv.addObject("sum", info[0]);
        mv.addObject("count", info[1]);
        return mv;
    }

    @RequestMapping("updateBask.do")
    public String updateBask(long id_ord, int amount) {
        dao.updateUserOrderById(id_ord, amount);
        return "redirect:showBasket.do";
    }


    @RequestMapping("delBask.do")
    public String delBask(long id_ord) {
        dao.delUserOrderById(id_ord);
        return "redirect:showBasket.do";
    }

    @RequestMapping("setPurchase.do")
    public ModelAndView purchase() {
        dao.doPurchase(getUser());
        ModelAndView mv = new ModelAndView("userdata");
        mv.addObject("products", dao.getAllProducts());
        mv.addObject("text", "Заказ оформлен!");
        int count = dao.getUserOrderExpectCount(getUser());
        mv.addObject("textInfo", "Ожидают подтверждения :"+count);
        return mv;
    }

    @RequestMapping("availableProd.do")
    public ModelAndView availableProd() {
        ModelAndView mv = new ModelAndView("availableData");
        mv.addObject("availableProducts", dao.getAvailableProducts());
        return mv;
    }

    @RequestMapping("checkProd.do")
    public ModelAndView checkProd(long prodId) {
        List<Available> availables = dao.getAvailableByIdProd(prodId);
        ModelAndView mv = new ModelAndView("availableData");
        mv.addObject("availableProducts", availables);
        return mv;
    }

    @RequestMapping("confirmList.do")
    public ModelAndView confirmList() {
        List<UsersOrder> usersOrders = dao.getUserOrderExpect();
        ModelAndView mv = new ModelAndView("confirmData");
        mv.addObject("usersOrders", usersOrders);
        mv.addObject("textInfo", svc.getNameOrdersToBuy(usersOrders));
        return mv;
    }

    @RequestMapping("doConfirm.do")
    public String doConfirm(long ord) {
        svc.confirmData(ord);
        return "redirect:confirmList.do";
    }

    @RequestMapping("showToPurchase.do")
    public ModelAndView showToPurchase() {
        ModelAndView mv = new ModelAndView("toPurchaseData");
        List<UsersOrder> usersOrders = dao.getUserOrderExpect();
        mv.addObject("usersOrders", svc.getOrdersToBuy(usersOrders, true)); //!!
        mv.addObject("products", dao.getAllProducts());
        mv.addObject("storages", dao.getAllStorages());
        return mv;
    }

    @RequestMapping("toPurchForAvail.do")
    public String toPurchForAvail(long pr, long st, int amount) {
        svc.addDelivery(pr,st,amount);
        return "redirect:showToPurchase.do";
    }
}
