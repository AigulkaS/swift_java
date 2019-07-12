package com.work1.services;

import com.work1.dao.HiberDAO;
import com.work1.model.Available;
import com.work1.model.Product;
import com.work1.model.Storage;
import com.work1.model.UsersOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacyService {

    @Autowired
    HiberDAO dao;

    public PharmacyService() {
    }

    @Transactional
    public void saleProd(long hidpr, int amount, String userName) {
        Product product = dao.getProductById(hidpr);
        dao.addOrder(userName, product, amount);
    }

    public String[] calcSumAndCount(List<UsersOrder> usersOrders) {
        String[] info = new String[2];
        double sum = 0;
        int count = 0;
        for (UsersOrder order: usersOrders) {
            sum += order.getProduct().getPrice() * order.getAmount();
            count += order.getAmount();
        }
        info[0] = String.valueOf(sum);
        info[1] = String.valueOf(count);
        return info;
    }

    @Transactional
    public List<UsersOrder> getOrdersToBuy(List<UsersOrder> usersOrders, boolean toChange) {
        List<UsersOrder> orders = new ArrayList<>();
        for (UsersOrder usersOrder: usersOrders) {
            List<Available> availables = dao.getAvailableByIdProd(usersOrder.getProduct().getId());
            int count = 0;
            for (Available available: availables) {
                count += available.getAmount();
            }
            if (count < usersOrder.getAmount()) {
                if (toChange) usersOrder.setAmount(usersOrder.getAmount() - count);
                orders.add(usersOrder);
            }
        }
        return orders;
    }

    public String getNameOrdersToBuy(List<UsersOrder> usersOrders) {
        List<UsersOrder> orders = getOrdersToBuy(usersOrders, false);
        if (orders.size() != 0) {
            String names = "Необходимо закупить ";
            for (UsersOrder order: orders) {
                names += order.getProduct().getName() + "; ";
            }
            return names;
        } else return "";
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void confirmData(long ord) {
        UsersOrder usersOrder = dao.getUserOrderById(ord);
        List<Available> availables = dao.getAvailableByIdProd(usersOrder.getProduct().getId());
        for (Available available : availables) {
            if (available.getAmount() > usersOrder.getAmount()) {
                dao.updateAvailableById(available.getId(), available.getAmount() - usersOrder.getAmount());
                dao.delUserOrderById(usersOrder.getId());
                dao.addSale(available.getProduct(), available.getStorage(), usersOrder.getAmount());
                break;
            } else if (available.getAmount() == usersOrder.getAmount()) {
                dao.delAvailableById(available.getId());
                dao.delUserOrderById(usersOrder.getId());
                dao.addSale(available.getProduct(), available.getStorage(), usersOrder.getAmount());
                break;
            } else {
                dao.delAvailableById(available.getId());
                dao.addSale(available.getProduct(), available.getStorage(), available.getAmount());
                dao.updateUserOrderById(usersOrder.getId(), usersOrder.getAmount() - available.getAmount());

            }
        }
    }

    @Transactional
    public void addDelivery(long pr, long st, int amount) {
        Product product = dao.getProductById(pr);
        Storage storage = dao.getStorageById(st);
        dao.addDelivery(product, storage, amount);
        List<Available> availables = dao.getAvailablesToUpdate(product, storage);
        if (availables.size() != 0) {
            availables.get(0).setAmount(availables.get(0).getAmount() + amount);
        } else {
            Available available = new Available(storage, product, amount);
            dao.addAvailable(available);
        }
    }
}
