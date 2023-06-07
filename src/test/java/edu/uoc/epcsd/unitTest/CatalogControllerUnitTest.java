package edu.uoc.epcsd.unitTest;


import java.util.ArrayList;
import java.util.List;
import org.glassfish.jersey.servlet.WebConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import edu.uoc.epcsd.showcatalog.application.rest.CatalogRESTController;
import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.service.CatalogService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(value = CatalogRESTController.class)
@ContextConfiguration(classes = {CatalogRESTController.class, WebConfig.class})
class CatalogControllerUnitTest {
	
	static String CATEGORY_TEST_NAME = "EXAMPLE_TEST";

	@Autowired
	private MockMvc mvc;
    @MockBean
    private CatalogService service;
	@Test
	public void rest_call_returns_data() throws Exception{
		//GIVEN
        given(service.findAllCategories()).willReturn(CreateTestCategoryList());
        
        //WHEN
        MvcResult result = mvc.perform(get("/categories")).andDo(print()).andExpect(status().isOk()).andReturn();
        
        //THEN
        assertThat(result.getResponse().getContentAsString()).contains(CATEGORY_TEST_NAME);
	}
	
	public List<Category> CreateTestCategoryList(){
		List<Category> category = new ArrayList<Category>();
		Category example = new Category();
		example.setName(CATEGORY_TEST_NAME);
		category.add(example);
		return category;
	}

}
