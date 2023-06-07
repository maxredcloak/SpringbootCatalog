package edu.uoc.epcsd.integrationTest;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.uoc.epcsd.showcatalog.ShowCatalogApplication;

import edu.uoc.epcsd.showcatalog.domain.Category;

import org.springframework.transaction.annotation.Transactional;

import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa.CategoryEntity;


@SpringBootTest(classes = ShowCatalogApplication.class)
class CatalogRepositoryIntegrationTest {
	@Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private EntityManager entityManager;
    
    @Test
    @Transactional
    void findAllCategories_properly_working(){
    	InitializeEntityManager();
    	
    	//GIVEN
		Category cat = CreateTestCategory();
        
        //WHEN
        List<Category> found = categoryRepository.findAllCategories();

        //THEN
        assertThat(found).anyMatch(category -> category.getName().equals(cat.getName()));
        entityManager.close();
   }
    
    private void InitializeEntityManager() {
    	entityManager = Persistence.createEntityManagerFactory("entityManager").createEntityManager(); 
		entityManager.getTransaction().begin();
    }
    private Category CreateTestCategory() {
		CategoryEntity newCategory = new CategoryEntity();
		Category cat = new Category();
		cat.setName("Test");
		newCategory = CategoryEntity.fromDomain(cat);
		
        entityManager.persist(newCategory);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return cat;
    }

}
