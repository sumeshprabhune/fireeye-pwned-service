package com.pwned.fireeye.demo.rest;

import com.pwned.fireeye.demo.beans.PasteEntity;
import com.pwned.fireeye.demo.beans.PasteEntityResponse;
import com.pwned.fireeye.demo.service.IPasswordService;
import com.pwned.fireeye.demo.service.IPasteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PasswordController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class PasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPasswordService passwordService;

    @Test
    public void shouldReturnPastes() throws Exception {
       String response = "003F292149C1BC6E313F59661DAA9BB7874:2\n" +
               "012A93CD6E5EE704FB1D2E3B238ED2D4A37:146\n" +
               "0159F8823B2BA480158D8F5656493741546:2\n" +
               "028DA52CF202EA546E9A7669C7614D64DCD:3";
        Mockito.when(passwordService.getPwnedPasswords("ABCDE")).thenReturn(response);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/password/{characters}", "ABCDE"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(response)))
                .andDo(document("password"));

    }
}
