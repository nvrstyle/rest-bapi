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
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList() throws Exception {
        String jsonBodyFilter = "{\"officeId\":5}";
        mockMvc.perform(post("/api/user/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id", is(9)))
                .andExpect(jsonPath("$.data[0].firstName", is("Иван")))
                .andExpect(jsonPath("$.data[0].secondName", is("Петров")))
                .andExpect(jsonPath("$.data[0].middleName", is("Ильдарович")))
                .andExpect(jsonPath("$.data[0].position", is("Маркетолог")))
                .andExpect(jsonPath("$.data[1].id", is(10)))
                .andExpect(jsonPath("$.data[1].firstName", is("Альберт")))
                .andExpect(jsonPath("$.data[1].secondName", is("Васичкин")))
                .andExpect(jsonPath("$.data[1].middleName", is("Мальбертович")))
                .andExpect(jsonPath("$.data[1].position", is("Каменщик")));
    }

    @Test
    public void testListError() throws Exception {
        String jsonBodyFilter = "{\"officeId\": 15}";
        mockMvc.perform(post("/api/user/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.firstName", is("Артем")))
                .andExpect(jsonPath("$.data.secondName", is("Андреев")))
                .andExpect(jsonPath("$.data.middleName", is("Петрович")))
                .andExpect(jsonPath("$.data.position", is("Генеральный директор")))
                .andExpect(jsonPath("$.data.docName", is("Паспорт гражданина РФ")))
                .andExpect(jsonPath("$.data.docNumber", is("8010 990807")))
                .andExpect(jsonPath("$.data.docDate", is("2013-10-01")))
                .andExpect(jsonPath("$.data.citizenshipName", is("Российская Федерация")))
                .andExpect(jsonPath("$.data.citizenshipCode", is("643")))
                .andExpect(jsonPath("$.data.isIdentified", is(true)));
    }

    @Test
    public void testGetByIdError() throws Exception {
        mockMvc.perform(get("/api/user/100"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testUpdate() throws Exception {
        String jsonBodyFilter = "{\n" +
                "  \"id\":\"1\",\n" +
                "  \"officeId\":\"1\", \n" +
                "  \"firstName\":\"Петя\",\n" +
                "  \"secondName\":\"Иванов\",\n" +
                "  \"middleName\":\"Иванович\",\n" +
                "  \"position\":\"Пчеловод\",\n" +
                "  \"phone\":\"\",\n" +
                "  \"docCode\":\"\",\n" +
                "  \"docName\":\"\",\n" +
                "  \"docNumber\":\"\",\n" +
                "  \"docDate\":\"\",\n" +
                "  \"citizenshipCode\":\"399\",\n" +
                "  \"isIdentified\":\"true\"\n" +
                "}";
        mockMvc.perform(post("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testUpdateError() throws Exception {
        String jsonBodyFilter = "{\"id\": 0, \"firstName\": \"Артем\", \"position\": \"Генеральный директор\", \"citizenshipCode\": \"399\", \"isIdentified\": true}";
        mockMvc.perform(post("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void testSave() throws Exception {
        String jsonBodyFilter = "{\n" +
                "  \"officeId\":\"1\", \n" +
                "  \"firstName\":\"Вася\",\n" +
                "  \"secondName\":\"Пупкин\",\n" +
                "  \"middleName\":\"Васильевич\",\n" +
                "  \"position\":\"Java Developer\",\n" +
                "  \"phone\":\"+79222323\",\n" +
                "  \"docCode\":\"\",\n" +
                "  \"docName\":\"\",\n" +
                "  \"docNumber\":\"\",\n" +
                "  \"docDate\":\"\",\n" +
                "  \"citizenshipCode\":\"578\",\n" +
                "  \"isIdentified\":true\n" +
                "}";
        mockMvc.perform(post("/api/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testSaveError() throws Exception {
        String jsonBodyFilter = "{\"officeId\": 3, \"firstName\": \"Вас**илий\"}";
        mockMvc.perform(post("/api/office/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyFilter))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", notNullValue()));
    }
}
