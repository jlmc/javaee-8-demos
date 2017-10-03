package org.jcosta;

import org.jcosta.store.BookStore;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;

public class App {

    public static void main(String[] args) {

        // To create a new CDI container we need a initialize instance
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();

        // then we can create a container using the initializer
        // Note that the SeContainer is AutoCloseble
        try (SeContainer seContainer = initializer.initialize()) {

            Instance<BookStore> lazyBookStores = seContainer.select(BookStore.class);

            String result = lazyBookStores.get().createPurchase("product-id", 16);

            System.out.println(result);
        }
    }
}
