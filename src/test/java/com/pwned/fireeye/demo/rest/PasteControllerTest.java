package com.pwned.fireeye.demo.rest;

import com.pwned.fireeye.demo.beans.PasteEntity;
import com.pwned.fireeye.demo.beans.PasteEntityResponse;
import com.pwned.fireeye.demo.service.IPasteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
@AutoConfigureMockMvc(secure = false)
@WebMvcTest(PasteController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class PasteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPasteService pasteService;


    @Test
    public void shouldReturnPastes() throws Exception {
        String comments = "Pwned Service successfully responded with the following pastes";
        PasteEntityResponse pasteEntityResponse = new PasteEntityResponse();
        pasteEntityResponse.setComments(comments);
        PasteEntity pasteEntity1 = new PasteEntity();
        pasteEntity1.setSource("Pastebin");
        pasteEntity1.setId("01ywCrGV");
        pasteEntity1.setTitle("AnonLeague hack leak email stratfor.com in July-17-2013");
        pasteEntity1.setDate("2013-07-18T04:07:00Z");
        pasteEntity1.setEmailCount(57);

        PasteEntity pasteEntity2 = new PasteEntity();
        pasteEntity2.setSource("QuickLeak");
        pasteEntity2.setId("QtPly6aE");
        pasteEntity2.setTitle("Cyber Resistance Hacked blogs.perl.org");
        pasteEntity2.setDate("2014-01-22T00:00:00Z");
        pasteEntity2.setEmailCount(2363);
        pasteEntityResponse.setPasteEntities(Arrays.asList(new PasteEntity[]{pasteEntity1, pasteEntity2}));

        String account = "test@example.com";
        Mockito.when(pasteService.getPasteAccounts(account)).thenReturn(pasteEntityResponse);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/pastes/{account}", account))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(comments)))
                .andDo(document("paste",
                        pathParameters(
                                parameterWithName("account").description("AccountId to search for pastes. It is a required parameter")),
                        getResponseFields()
                ));

    }

    private ResponseFieldsSnippet getResponseFields() {
        return responseFields(
                fieldWithPath("comments").type(JsonFieldType.STRING).description("Comments for the returned response; important especially to show problems"),
                fieldWithPath("pasteEntities[].Source").type(JsonFieldType.STRING).description("The paste service the record was retrieved from. Current values are: Pastebin, Pastie, Slexy, Ghostbin, QuickLeak, JustPaste, AdHocUrl, PermanentOptOut, OptOut"),
                fieldWithPath("pasteEntities[].Id").type(JsonFieldType.STRING).description("The ID of the paste as it was given at the source service. Combined with the \"Source\" attribute, this can be used to resolve the URL of the paste."),
                fieldWithPath("pasteEntities[].Title").type(JsonFieldType.STRING).description("The title of the paste as observed on the source site. This may be null and if so will be omitted from the response."),
                fieldWithPath("pasteEntities[].Date").type(JsonFieldType.STRING).description("The date and time (precision to the second) that the paste was posted. This is taken directly from the paste site when this information is available but may be null if no date is published.").optional(),
                fieldWithPath("pasteEntities[].EmailCount").type(JsonFieldType.NUMBER).description("The number of emails that were found when processing the paste. Emails are extracted by using the regular expression \\b+(?!^.{256})[a-zA-Z0-9\\.\\-_\\+]+@[a-zA-Z0-9\\.\\-_]+\\.[a-zA-Z]+\\b")
        );
    }
}
