package com.hb.cda.thymeleafproject.data;

import com.hb.cda.thymeleafproject.entity.Product;
import com.hb.cda.thymeleafproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDataInitializer implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepo;

    @Override
    public void run(String... args) throws Exception {
        if (productRepo.count() == 0) { // pour éviter d'ajouter en double à chaque redémarrage
            Product p1 = new Product();
            p1.setName("Ordinateur portable");
            p1.setPrice(899.99);
            p1.setDescription("Laptop performant pour le travail et le jeu.");
            p1.setStock(25);

            Product p2 = new Product();
            p2.setName("Écouteurs Bluetooth");
            p2.setPrice(59.99);
            p2.setDescription("Son clair et autonomie de 12h.");
            p2.setStock(100);

            Product p3 = new Product();
            p3.setName("Souris gamer RGB");
            p3.setPrice(39.90);
            p3.setDescription("Souris ergonomique avec effets lumineux personnalisables.");
            p3.setStock(75);

            Product p4 = new Product();
            p4.setName("Tapis de souris XL");
            p4.setPrice(19.99);
            p4.setDescription("Surface étendue et antidérapante.");
            p4.setStock(150);

            Product p5 = new Product();
            p5.setName("Clavier mécanique");
            p5.setPrice(129.00);
            p5.setDescription("Clavier à switchs rouges silencieux avec rétroéclairage.");
            p5.setStock(60);

            productRepo.saveAll(List.of(p1, p2, p3, p4, p5));

            System.out.println("Produits ajoutés à la base de données.");
        } else {
            System.out.println("Produits déjà présents en base, aucun ajout effectué.");
        }
    }
}

