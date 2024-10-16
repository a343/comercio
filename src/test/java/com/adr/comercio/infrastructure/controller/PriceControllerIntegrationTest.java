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
    void getPriceInfoAt10amOn14th() throws Exception {
       final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T10:00:00");

        mockMvc.perform(get("/price/35455/1")
                        .param("applicationDate", applicationDate.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"brandId\":1,\"productId\":35455,\"price\":35.50, \"startDate\":\"2020-06-14T00:00:00\", \"endDate\":\"2020-12-31T23:59:59\"}"));
    }

    @Test
    void getPriceInfoAt4pmOn14th() throws Exception {
        final  LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T16:00:00");

        mockMvc.perform(get("/price/35455/1")
                        .param("applicationDate", applicationDate.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"brandId\":1,\"productId\":35455,\"price\":25.45}, \"startDate\":\"2020-06-14T15:00:00\", \"endDate\":\"2020-06-14 18:30:00\"}"));
    }

    @Test
    void getPriceInfoAt9pmOn14th() throws Exception {
        final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T21:00:00");

        mockMvc.perform(get("/price/35455/1")
                        .param("applicationDate", applicationDate.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"brandId\":1,\"productId\":35455,\"price\":35.50, \"startDate\":\"2020-06-14T00:00:00\", \"endDate\":\"2020-12-31T23:59:59\"}"));
    }

    @Test
    void getPriceInfoAt10amOn15th() throws Exception {
        final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15T10:00:00");

        mockMvc.perform(get("/price/35455/1")
                        .param("applicationDate", applicationDate.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"brandId\":1,\"productId\":35455,\"price\":30.50 , \"startDate\":\"2020-06-15T00:00:00\", \"endDate\":\"2020-06-15T11:00:00\"}"));
    }

    @Test
    void getPriceInfoAt9pmOn16th() throws Exception {
        final LocalDateTime applicationDate = LocalDateTime.parse("2020-06-16T21:00:00");

        mockMvc.perform(get("/price/35455/1")
                        .param("applicationDate", applicationDate.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"brandId\":1,\"productId\":35455,\"price\":50.95, \"startDate\":\"2020-06-15T16:00:00\", \"endDate\":\"2020-12-31T23:59:59\"}"));
    }


    @Test
    void missingApplicationDateParameter() throws Exception {
        mockMvc.perform(get("/price/35455/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"Missing required parameter\",\"details\":\"The parameter 'applicationDate' is missing\"}"));
    }

    @Test
    void invalidDateFormat() throws Exception {
        mockMvc.perform(get("/price/35455/1")
                        .param("applicationDate", "2020-06-16 21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"Invalid parameter type\",\"details\":\"The date format should be 'yyyy-MM-dd'T'HH:mm:ss'\"}"));
    }

    @Test
    void invalidBrandIdType() throws Exception {
        mockMvc.perform(get("/price/35455/abc")
                        .param("applicationDate", "2020-06-16T21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"Invalid parameter type\",\"details\":\"The parameter 'brandId' should be of type 'Integer'\"}"));
    }

    @Test
    void invalidProductIdType() throws Exception {
        mockMvc.perform(get("/price/abc/1")
                        .param("applicationDate", "2020-06-16T21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"Invalid parameter type\",\"details\":\"The parameter 'productId' should be of type 'Integer'\"}"));
    }

    @Test
    void shouldReturnNotFoundPrices() throws Exception {
        mockMvc.perform(get("/price/3511455/1")
                        .param("applicationDate", "2020-06-16T21:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"There is no product with these characteristics\",\"details\": null}"));
    }
}
