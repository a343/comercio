package com.adr.comercio.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPriceInfoAt10amOn14th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T10:00:00");

        mockMvc.perform(get("/comercio/price/35455/1/")
                        .param("applicationDate", applicationDate.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"brandId\":\"1\",\"productId\":\"35455\",\"price\":35.50, \"startDate\":\"2020-06-14T00:00:00\", \"endDate\":\"2020-12-31T23:59:59\"}"));
    }

    @Test
    void testGetPriceInfoAt4pmOn14th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T16:00:00");

        mockMvc.perform(get("/comercio/price/35455/1/")
                        .param("applicationDate", applicationDate.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"brandId\":\"1\",\"productId\":\"35455\",\"price\":25.45}, \"startDate\":\"2020-06-14T15:00:00\", \"endDate\":\"2020-06-14 18:30:00\"}"));
    }

    @Test
    void testGetPriceInfoAt9pmOn14th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T21:00:00");

        mockMvc.perform(get("/comercio/price/35455/1/")
                        .param("applicationDate", applicationDate.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"brandId\":\"1\",\"productId\":\"35455\",\"price\":35.50, \"startDate\":\"2020-06-14T00:00:00\", \"endDate\":\"2020-12-31T23:59:59\"}"));
    }

    @Test
    void testGetPriceInfoAt10amOn15th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15T10:00:00");

        mockMvc.perform(get("/comercio/price/35455/1/")
                        .param("applicationDate", applicationDate.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"brandId\":\"1\",\"productId\":\"35455\",\"price\":30.50 , \"startDate\":\"2020-06-15T00:00:00\", \"endDate\":\"2020-06-15T11:00:00\"}"));
    }

    @Test
    void testGetPriceInfoAt9pmOn16th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-16T21:00:00");

        mockMvc.perform(get("/comercio/price/35455/1/")
                        .param("applicationDate", applicationDate.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"brandId\":\"1\",\"productId\":\"35455\",\"price\":50.95, \"startDate\":\"2020-06-15T16:00:00\", \"endDate\":\"2020-12-31T23:59:59\"}"));
    }
}
