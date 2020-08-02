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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList() throws Exception {
        String jsonBodyFilter = "{\"name\": \"OOO ФАВОРИТ\"}";
        mockMvc.perform(post("/api/organization/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("OOO ФАВОРИТ")))
                .andExpect(jsonPath("$.data[0].isActive", is(true)));
    }

    @Test
    public void testListError() throws Exception {
        String jsonBodyFilter = "{\"name\": \"Абракадабра\"}";
        mockMvc.perform(post("/api/organization/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/api/organization/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("OOO ФАВОРИТ")))
                .andExpect(jsonPath("$.data.fullName", is("Общество с ограниченной ответственностью \"ФАВОРИТ\"")))
                .andExpect(jsonPath("$.data.inn", is("1174574545")))
                .andExpect(jsonPath("$.data.kpp", is("775782780")))
                .andExpect(jsonPath("$.data.address", is("Россия, г.Москва, ул. Красная, д.1")))
                .andExpect(jsonPath("$.data.phone", is("+7 964 228 69 90")))
                .andExpect(jsonPath("$.data.isActive", is(true)));
    }

    @Test
    public void testGetByIdError() throws Exception {
        mockMvc.perform(get("/api/organization/5"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testUpdate() throws Exception {
        String jsonBodyFilter = "{\"id\": 3, \"name\": \"ООО Скорбим\",\"fullName\": \"Общество с ограниченной ответственностью Скорбим\", \"inn\": \"0123456789\", " +
                "\"kpp\": \"123456789\", \"address\": \"Россия, г.Уфа, ул. Рижская, д.5\"}";
        mockMvc.perform(post("/api/organization/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testUpdateError() throws Exception {
        String jsonBodyFilter = "{\"id\": 3, \"name\": \"ООО Скорбим\",\"fullName\": \"Общество с ограниченной ответственностью Скорбим\", \"inn\": \"0123456789\", " +
                "\"kpp\": \"123456789\"}";
        mockMvc.perform(post("/api/organization/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void testSave() throws Exception {
        String jsonBodyFilter = "{\"name\": \"ОАО Уралсиб\",\"fullName\": \"Открытое акционерно общество Уралсиб\", \"inn\": \"0123455559\", " +
                "\"kpp\": \"012345678\", \"address\": \"Россия, г.Москва, ул. Ефремова, д.8\", \"isActive\": true}";
        mockMvc.perform(post("/api/organization/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testSaveError() throws Exception {
        String jsonBodyFilter = "{\"name\": \"ОOО Сбербанк\",\"fullName\": \"Открытое акционерно общество Сюербанк\", \"inn\": \"0123445559\"}";
        mockMvc.perform(post("/api/organization/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", notNullValue()));
    }
}
