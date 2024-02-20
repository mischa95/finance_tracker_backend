package com.app.financetracker.controller;

import com.app.financetracker.persistence.Category;
import com.app.financetracker.persistence.Expense;
import com.app.financetracker.persistence.User;
import com.app.financetracker.repository.CategoryRepository;
import com.app.financetracker.repository.ExpenseRepository;
import com.app.financetracker.service.ExpenseService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExpenseControllerTest {

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @InjectMocks
    private ExpenseService expenseService;
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void whenLogin_thenResponse401() throws Exception {
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        this.mockMvc.perform(post("/auth/login"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void whenValidInput_thenResponse200() throws Exception {
        mockMvc.perform(get("/expenses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldCheckSizeOfExpenses() throws Exception {
        final int EXPECTED_SIZE = 10;

        this.mockMvc
                .perform((get("/expenses")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(EXPECTED_SIZE)));
    }

    @Test
    public void shouldCheckSizeOfExpensesByCategory() throws Exception {
        final int EXPECTED_SIZE = 4;
        final long CATEGORY_ID = 7;

        int expenseCount = expenseService.findExpensesByCategory(CATEGORY_ID).size();

        // Assertions
        assertEquals(EXPECTED_SIZE, expenseCount);
    }

    @Test
    public void shouldGetSingleExpense() throws Exception {
        final String EXPENSE_ID = "1";
        final String EXPECTED_DESC = "bus ticket";

        mockMvc.perform(get("/expenses/{id}", EXPENSE_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.description").value(EXPECTED_DESC));
    }
//    @Test
//    public void shouldAddExpense() {
//
//        Expense expense = new Expense(Long.valueOf(100), Double.valueOf(10), "EUR", "train ticket", new Category(), LocalDate.of(2024, 1, 8), new User());
//
//        HttpEntity<Expense> entity = new HttpEntity<>(expense, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                createURLWithPort("/expenses"),
//                HttpMethod.POST, entity, String.class);
//
//        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(port);
//
//        assertTrue(actual.contains("/expenses"));
//
//    }
//
//    private String createURLWithPort(String uri) {
//        return "http://localhost:" + port + uri;
//    }
}