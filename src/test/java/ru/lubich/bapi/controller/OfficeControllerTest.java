package ru.lubich.bapi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
public class OfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList() throws Exception {
        String jsonBodyFilter = "{\"orgId\": 2}";
        mockMvc.perform(post("/api/office/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id", is(2)))
                .andExpect(jsonPath("$.data[0].name", is("Производственная база")))
                .andExpect(jsonPath("$.data[0].isActive", is(true)))
                .andExpect(jsonPath("$.data[1].id", is(3)))
                .andExpect(jsonPath("$.data[1].name", is("Розничный магазин \"Молот\"")))
                .andExpect(jsonPath("$.data[1].isActive", is(true)));
    }

    @Test
    public void testListError() throws Exception {
        String jsonBodyFilter = "{\"orgId\": 300}";
        mockMvc.perform(post("/api/office/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/api/office/3"))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(3)))
                .andExpect(jsonPath("$.data.name", is("Розничный магазин \"Молот\"")))
                .andExpect(jsonPath("$.data.address", is("Россия, г.Москва, проспект Октября, д.70")))
                .andExpect(jsonPath("$.data.phone", is("+7 347 234 51 10")))
                .andExpect(jsonPath("$.data.isActive", is(true)));
    }

    @Test
    public void testGetByIdError() throws Exception {
        mockMvc.perform(get("/api/office/20"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testUpdate() throws Exception {
        String jsonBodyFilter = "{\"id\": 6, \"name\": \"ООО Оптовый центр продаж\", \"address\": \"Россия, г.Уфа, ул. Рижская, д.5\"}";
        mockMvc.perform(post("/api/office/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testUpdateError() throws Exception {
        String jsonBodyFilter = "{\"id\": 666, \"name\": \"ООО Оптовый центр продаж\", \"address\": \"Россия, г.Уфа, ул. Рижская, д.5\"}";
        mockMvc.perform(post("/api/office/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void testSave() throws Exception {
        String jsonBodyFilter = "{\"orgId\": 3, \"name\": \"ООО Олимп\", \"address\": \"Россия, г.Москва, ул. Победы, д.666\"}";
        mockMvc.perform(post("/api/office/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testSaveError() throws Exception {
        String jsonBodyFilter = "{\"name\": \"ООО Олимп\", \"address\": \"Россия, г.Москва, ул. Победы, д.666\"}";
        mockMvc.perform(post("/api/office/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", notNullValue()));
    }
}

