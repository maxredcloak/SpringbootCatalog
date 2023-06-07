package edu.uoc.epcsd.unitTest;

import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import edu.uoc.epcsd.showcatalog.domain.Show;
import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.showcatalog.domain.repository.ShowRepository;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogService;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogServiceImpl;
import edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa.CategoryRepositoryImpl;
import edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa.ShowRepositoryImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootConfiguration
@SpringBootTest
class CatalogServiceUnitTest {
    @TestConfiguration
    static class TestConfig {
        @Bean
        public CatalogService catalogService() {
        	ShowRepository sr = mock(ShowRepositoryImpl.class);
        	CategoryRepository cr = mock(CategoryRepositoryImpl.class);
        	@SuppressWarnings("unchecked")
			KafkaTemplate<String, Show> kt = mock(KafkaTemplate.class);
        	
            Show showTest = new Show();
        	given(sr.findShowById(1L)).willReturn(Optional.of(showTest));
            given(sr.findShowById(2L)).willReturn(Optional.empty());
            return new CatalogServiceImpl(sr, cr, kt);
        }
    }

    @Autowired
    private CatalogService catalogService;

    @Test
    void FindShowById_ok(){
        //GIVEN
        long id = 1L;
        
        //WHEN
        Optional<Show> showFinded = catalogService.findShowById(id);
        
        //THEN
        assertThat(showFinded.isPresent())
            .isEqualTo(true);       
    }
    @Test
    void FindShowById_ko(){
        //GIVEN
        long id = 2L;
  
        //WHEN
        Optional<Show> showFinded = catalogService.findShowById(id);
        
        //THEN
        assertThat(showFinded.isPresent())
            .isEqualTo(false);   
    }

}
