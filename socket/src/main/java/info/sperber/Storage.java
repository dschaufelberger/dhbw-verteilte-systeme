package info.sperber;

import info.sperber.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Dodo on 08.03.2017.
 */
public class Storage {
    private EntityManager manager;

    public Storage( EntityManager manager) {
        this.manager = manager;
    }

    public boolean contains(Product product){
        this.manager.getTransaction().begin();
        boolean containsProduct = this.manager.contains(product);
        this.manager.getTransaction().commit();

        return containsProduct;
    }

    public void insert(Product product) {
        this.manager.getTransaction().begin();
        this.manager.persist(product);
        this.manager.getTransaction().commit();
    }

    public Product update(Product product) {
        this.manager.getTransaction().begin();
        Product toUpdate = this.manager.find(Product.class, product.getId());
        toUpdate.setQuantity(product.getQuantity());
        this.manager.getTransaction().commit();
        return toUpdate;
    }

    public Product find(String id) {
        Product result = this.manager.find(Product.class, id);
        return result;
    }
}
