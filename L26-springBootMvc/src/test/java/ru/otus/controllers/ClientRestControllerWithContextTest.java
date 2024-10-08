package ru.otus.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.domain.Client;
import ru.otus.services.ClientService;

@DisplayName("WithContextTest: REST-контроллер клиентов ")
// @WebMvcTest(ClientRestController.class)
// @Import(value = {ClientServiceImpl.class, ClientIdGeneratorImpl.class, ClientRepositoryImpl.class})
@SpringBootTest
@AutoConfigureMockMvc
class ClientRestControllerWithContextTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientService clientService;

    @DisplayName("должен возвращать корректного клиента по его id")
    @Test
    void shouldReturnExpectedClientById() throws Exception {
        Client expectedClient = new Client(1, "Крис Гир");

        given(clientService.findById(1L)).willReturn(expectedClient);

        Gson gson = new GsonBuilder().create();
        mvc.perform(get("/api/client/{id}", 1L).accept("application/json; charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(gson.toJson(expectedClient)));
    }
}
