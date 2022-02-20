package com.lab6.addressbook;

import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab6.addressbook.model.BuddyInfoModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createBuddyTest() throws Exception {
        BuddyInfoModel buddyInfoModel = new BuddyInfoModel("adi", "194 Forestglade cr.", "613 456 5657");

        mockMvc.perform(post("/api/v0/addressbook/abm1/buddies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(buddyInfoModel))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("adi"))
                .andExpect(jsonPath("$.address").value("194 Forestglade cr."))
                .andExpect(jsonPath("$.number").value("613 456 5657"));
    }

    @Test
    public void getBuddiesTest() throws Exception {
        BuddyInfoModel buddyInfoModel = new BuddyInfoModel("adi", "194 Forestglade cr.", "613 456 5657");

        mockMvc.perform(post("/api/v0/addressbook/abm1/buddies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(buddyInfoModel))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


        mockMvc.perform(get("/api/v0/addressbook/abm1/buddies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateBuddyTest() throws Exception {
        BuddyInfoModel buddyInfoModel = new BuddyInfoModel("adi", "194 Forestglade cr.", "613 456 5657");

        mockMvc.perform(post("/api/v0/addressbook/abm1/buddies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(buddyInfoModel))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        Map<String, String> values = new HashMap<>();
        values.put("newAddress", "1741 Riverside dr.");

        mockMvc.perform(put("/api/v0/addressbook/abm1/buddies/adi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(values))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("New Address has been updated to buddy named adi")));
    }

    @Test
    public void deleteBuddyTest() throws Exception {
        BuddyInfoModel buddyInfoModel = new BuddyInfoModel("adi", "194 Forestglade cr.", "613 456 5657");

        mockMvc.perform(post("/api/v0/addressbook/abm1/buddies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(buddyInfoModel))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(delete("/api/v0/addressbook/abm1/buddies/adi")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Buddy named adi has been deleted!")));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
