package com.work1.dao;

import com.work1.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class HiberDAO {

    @PersistenceContext
    EntityManager em;

   @Transactional
   public void init() {
       Storage storage;
       Product product;
       Available available;

       em.createQuery("delete from Users users where users.id > 0").executeUpdate();
       em.createQuery("delete from Available available where available.id > 0").executeUpdate();
       em.createQuery("delete from Storage storage where storage.id > 0").executeUpdate();
       em.createQuery("delete from Product product where product.id > 0").executeUpdate();

       Users user;
       user = new Users("admin", "admin");
       em.persist(user);
       user = new Users("aigul", "ai123");
       em.persist(user);
       user = new Users("timur", "tim456");
       em.persist(user);

       storage = new Storage("Store 650", "str. Basil 5");
       product = new Product("analgin", 25.00);
       available = new Available(storage, product, 20);

       storage.getAvailables().add(available);
       product.getAvailables().add(available);
       em.persist(storage);
       em.persist(product);

       storage = new Storage("Store 651", "str. Lenina 41");
       product = new Product("aspirin", 50.50);
       available = new Available(storage, product, 10);
       storage.getAvailables().add(available);
       product.getAvailables().add(available);
       em.persist(storage);
       em.persist(product);

       product = new Product("dr.Mom", 350.20);
       available = new Available(storage, product, 5);
       storage.getAvailables().add(available);
       product.getAvailables().add(available);
       em.persist(storage);
       em.persist(product);

       storage = new Storage("Store 652", "str. Pushkin 31");
       product = new Product("mukaltin", 35.30);
       available = new Available(storage, product, 15);
       storage.getAvailables().add(available);
       product.getAvailables().add(available);
       em.persist(storage);
       em.persist(product);

       product = new Product("sirop", 500.50);
       available = new Available(storage, product, 3);
       storage.getAvailables().add(available);
       product.getAvailables().add(available);
       em.persist(storage);
       em.persist(product);
   }

    @Transactional
    public List<Product> getAllProducts() {
        List<Product> products = em.createQuery("select product from Product product",Product.class).getResultList();
        return products;
    }

    @Transactional
    public List<Storage> getAllStorages() {
        List<Storage> storages = em.createQuery("select storages from Storage storages",Storage.class).getResultList();
        return storages;
    }

    @Transactional
    public Storage getStorageById(long id) {
        Storage storage = em.find(Storage.class, id);
        return storage;
    }

    @Transactional
    public List<Available> getAvailableProducts() {
        List<Available> availables = em.createQuery("select availables from Available availables",Available.class).getResultList();
        return availables;
    }

    @Transactional
    public Product getProductById(long id) {
        Product product = em.find(Product.class, id);
        return product;
    }

    @Transactional
    public void addOrder(String userName, Product product, int amount) {
        UsersOrder usersOrder = new UsersOrder(userName, product, amount);
        em.persist(usersOrder);
    }

    @Transactional
    public List<UsersOrder> getUserOrderByName(String userName) {
        Query query = em.createQuery("SELECT p FROM UsersOrder p WHERE p.userName = :paramName and p.purchase = :paramPurch");
        query.setParameter("paramName", userName);
        query.setParameter("paramPurch", false);
        List<UsersOrder> usersOrders = query.getResultList();
        return usersOrders;
    }

    @Transactional
    public UsersOrder getUserOrderById(long id) {
        UsersOrder order = em.find(UsersOrder.class, id);
        return order;
    }

    @Transactional
    public void doPurchase(String userName) {
        for (UsersOrder order: getUserOrderByName(userName)) {
            order.setPurchase(true);
        }
    }

    @Transactional
    public int getUserOrderExpectCount(String userName) {
        Query query = em.createQuery("SELECT p FROM UsersOrder p WHERE p.userName = :paramName " +
                "and p.confirmed = :paramConf " +
                "and p.purchase = :paramPurch");
        query.setParameter("paramName", userName);
        query.setParameter("paramConf", false);
        query.setParameter("paramPurch", true);
        List<UsersOrder> usersOrders = query.getResultList();
        return usersOrders.size();
    }

    @Transactional
    public List<UsersOrder> getUserOrderExpect() {
        Query query = em.createQuery("SELECT p FROM UsersOrder p WHERE p.confirmed = :paramConf " +
                "and p.purchase = :paramPurch");
        query.setParameter("paramConf", false);
        query.setParameter("paramPurch", true);
        return query.getResultList();
    }

    @Transactional
    public void updateUserOrderById(long id, int amount) {
        UsersOrder order = em.find(UsersOrder.class, id);
        order.setAmount(amount);
    }

    @Transactional
    public void updateAvailableById(long id, int amount) {
        Available available = em.find(Available.class, id);
        available.setAmount(amount);
    }

    @Transactional
    public void delUserOrderById(long id) {
        UsersOrder order = em.find(UsersOrder.class, id);
        em.remove(order);
    }

    @Transactional
    public void addProduct(String name, double price) {
        Product product = new Product(name, price);
        em.persist(product);
    }

    @Transactional
    public void delProduct(long id) {
        Product product = em.find(Product.class, id);
        em.remove(product);
    }

    @Transactional
    public void addStorage(String name, String address) {
        Storage storage = new Storage(name, address);
        em.persist(storage);
    }

    @Transactional
    public void delStorage(long id) {
        Storage storage = em.find(Storage.class, id);
        em.remove(storage);
    }

    @Transactional
    public List<Available> getAvailableByIdProd(long id) {
        Query query = em.createQuery("SELECT a FROM Available a WHERE a.product.id= :paramProd");
        query.setParameter("paramProd", id);
        return query.getResultList();
    }

    @Transactional
    public void delAvailableById(long id) {
        Available available = em.find(Available.class, id);
        em.remove(available);
    }

    @Transactional
    public void addSale(Product product, Storage storage, int amount) {
        Sale sale = new Sale(product, storage, amount);
        em.persist(sale);
    }

    @Transactional
    public void addDelivery(Product product, Storage storage, int amount) {
        Delivery delivery = new Delivery(product, storage, amount);
        em.persist(delivery);
    }

    @Transactional
    public List<Available> getAvailablesToUpdate(Product product, Storage storage) {
        TypedQuery<Available> query = em.createQuery("SELECT a FROM Available a WHERE a.product= :paramProd and a.storage = :paramStor", Available.class);
        query.setParameter("paramProd", product);
        query.setParameter("paramStor", storage);
        return query.getResultList();
    }

    @Transactional
    public void addAvailable(Available available) {
        em.persist(available);
    }

    @Transactional
    public List<Sale> getAllSales() {
        List<Sale> res = em.createQuery("select s from Sale s left join fetch s.product left join fetch s.storage",Sale.class).getResultList();
        return res;
    }

    @Transactional
    public List<Delivery> getAllDeliveries() {
        List<Delivery> res = em.createQuery("select d from Delivery d left join fetch d.product left join fetch d.storage",Delivery.class).getResultList();
        return res;
    }

    @Transactional
    public List<Users> getUserByLogin(String login) {
        TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.login= :paramLogin", Users.class);
        query.setParameter("paramLogin", login);
        return query.getResultList();
    }
}
