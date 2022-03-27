package com.ecommerce.book.config;

import com.ecommerce.book.entity.Country;
import com.ecommerce.book.entity.Product;
import com.ecommerce.book.entity.ProductCategory;
import com.ecommerce.book.entity.State;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class BookDataRestConfig implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    public BookDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        HttpMethod[] theUnsupportedActions = {HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};

        // Disable HTTP methods for Product: PUT, POST and DELETE
        disableSomeHttpMethods(Product.class, config, theUnsupportedActions);
        // Disable HTTP methods for ProductCategory: PUT, POST and DELETE
        disableSomeHttpMethods(ProductCategory.class, config, theUnsupportedActions);
        // Disable HTTP methods for Country: PUT, POST and DELETE
        disableSomeHttpMethods(Country.class, config, theUnsupportedActions);
        // Disable HTTP methods for State: PUT, POST and DELETE
        disableSomeHttpMethods(State.class, config, theUnsupportedActions);

        // call an internal helper method
        exposeIds(config);
    }

    /*
     * Disable HTTP methods for Classes: PUT, POST and DELETE
     */
    private void disableSomeHttpMethods(Class<?> theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        /* expose entity Ids */
        // - get a list of all entity classes drom entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // - create an array of the entity types
        List<Class<?>> entityClass = new ArrayList<>();

        // - get the entity types for the entities
        for (EntityType<?> tempEntityType : entities) {
            entityClass.add(tempEntityType.getJavaType());
        }

        // - expose the entity Ids for the array of entity/domain types
        Class<?>[] domainTypes = entityClass.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
