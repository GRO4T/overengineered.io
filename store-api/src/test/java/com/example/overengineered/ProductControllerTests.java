package com.example.overengineered;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        products.add(new Product(2L, "Mouse"));

        final String expectedResponseContent = objectMapper.writeValueAsString(products);

        when(productRepository.findAll()).thenReturn(products);

        // Act and Assert
        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseContent));
    }

    @Test
    public void testAddProduct() throws Exception {
        Product newProduct = new Product(1L, "Headphones");

        when(productRepository.save(newProduct)).thenReturn(newProduct);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .content(asJsonString(newProduct))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(newProduct)));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
