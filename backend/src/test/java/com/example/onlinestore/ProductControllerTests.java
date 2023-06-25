package com.example.onlinestore;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {
    private static Logger log = LoggerFactory.getLogger(ProductControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ProductRepository productRepository;

    @Test
    public void testGetProducts() throws Exception {
        // Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Laptop"));
        products.add(new Product(2L, "Mouse2"));

        final String expectedResponseContent = objectMapper.writeValueAsString(products);

        when(productRepository.findAll()).thenReturn(products);

        // Act and Assert
        ResultActions res = this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseContent));
    }
}
