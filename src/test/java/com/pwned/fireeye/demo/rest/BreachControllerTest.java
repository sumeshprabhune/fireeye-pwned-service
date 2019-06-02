package com.pwned.fireeye.demo.rest;

import com.pwned.fireeye.demo.beans.Breach;
import com.pwned.fireeye.demo.beans.BreachResponse;
import com.pwned.fireeye.demo.service.IBreachService;
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
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(BreachController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class BreachControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBreachService breachService;

    @Test
    public void shouldReturnBreachResultForAccount() throws Exception {
        String comments = "Pwned Service successfully responded with the following breaches";
        BreachResponse breachResponse = new BreachResponse();
        breachResponse.setComments(comments);
        Breach breach = new Breach();
        breach.setName("Adobe");
        breach.setTitle("Adobe");
        breach.setDomain("adobe.com");
        breach.setBreachDate("2013-10-04");
        breach.setAddedDate("2013-12-04T00:00:00Z");
        breach.setModifiedDate("2013-12-04T00:00:00Z");
        breach.setPwnCount(14422);
        breach.setDescription("Adobe breach description");
        breach.setDataClasses(Arrays.asList(new String[]{"Email addresses", "Password hints", "Passwords", "Usernames"}));
        breach.setIsVerified(true);
        breach.setIsRetired(false);
        breach.setIsSensitive(false);
        breach.setIsSpamList(false);
        breach.setIsFabricated(false);
        breachResponse.setBreaches(Arrays.asList(new Breach[]{breach}));
        Mockito.when(breachService.getAllBreaches(Mockito.any())).thenReturn(breachResponse);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/breach/byAccount/{account}", "test@example.com")
                .param("domain", "Adobe.com").param("truncateResponse", "false").param("includeUnverified", "true"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(comments)))
                .andDo(document("account-breach",
                        pathParameters(
                                parameterWithName("account").description("AccountId to search for breaches. It is a required parameter")),
                        requestParameters(
                                parameterWithName("domain").description("Filters the result set to only breaches against the domain specified. It is possible that one site (and consequently domain), is compromised on multiple occasions."),
                                parameterWithName("truncateResponse").description("Returns only the name of the breach."),
                                parameterWithName("includeUnverified").description("Returns breaches that have been flagged as \"unverified\". By default, only verified breaches are returned when performing a search.")),
                        getBreachResponseFields()
                ));

    }

    @Test
    public void shouldReturnBreaches() throws Exception {

        String comments = "Pwned Service successfully responded with the following breaches";
        BreachResponse breachResponse = new BreachResponse();
        breachResponse.setComments(comments);
        Breach breach = new Breach();
        breach.setName("Adobe");
        breach.setTitle("Adobe");
        breach.setDomain("adobe.com");
        breach.setBreachDate("2013-10-04");
        breach.setAddedDate("2013-12-04T00:00:00Z");
        breach.setModifiedDate("2013-12-04T00:00:00Z");
        breach.setPwnCount(14422);
        breach.setDescription("Adobe breach description");
        breach.setDataClasses(Arrays.asList(new String[]{"Email addresses", "Password hints", "Passwords", "Usernames"}));
        breach.setIsVerified(true);
        breach.setIsRetired(false);
        breach.setIsSensitive(false);
        breach.setIsSpamList(false);
        breach.setIsFabricated(false);
        breachResponse.setBreaches(Arrays.asList(new Breach[]{breach}));
        Mockito.when(breachService.getAllBreaches(Mockito.any())).thenReturn(breachResponse);

        this.mockMvc.perform(get("/breach/getAll")
                .param("domain", "Adobe.com"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(comments)))
                .andDo(document("all-breach",
                        requestParameters(
                                parameterWithName("domain").description("Filters the result set to only breaches against the domain specified. It is possible that one site (and consequently domain), is compromised on multiple occasions.")),
                        getBreachResponseFields()
                ));

    }

    @Test
    public void shouldReturnBreachByName() throws Exception {

        String comments = "Pwned Service successfully responded with the following breaches";
        BreachResponse breachResponse = new BreachResponse();
        breachResponse.setComments(comments);
        Breach breach = new Breach();
        breach.setName("Adobe");
        breach.setTitle("Adobe");
        breach.setDomain("adobe.com");
        breach.setBreachDate("2013-10-04");
        breach.setAddedDate("2013-12-04T00:00:00Z");
        breach.setModifiedDate("2013-12-04T00:00:00Z");
        breach.setPwnCount(14422);
        breach.setDescription("Adobe breach description");
        breach.setDataClasses(Arrays.asList(new String[]{"Email addresses", "Password hints", "Passwords", "Usernames"}));
        breach.setIsVerified(true);
        breach.setIsRetired(false);
        breach.setIsSensitive(false);
        breach.setIsSpamList(false);
        breach.setIsFabricated(false);
        breachResponse.setBreaches(Arrays.asList(new Breach[]{breach}));
        Mockito.when(breachService.getBreach(Mockito.any())).thenReturn(breachResponse);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/breach/byName/{name}", "Adobe"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(comments)))
                .andDo(document("name-breach",
                        pathParameters(
                                parameterWithName("name").description("Name of the breach to search")),
                        getBreachResponseFields()
                ));
    }

    @Test
    public void shouldReturnBreachDataClasses() throws Exception {
        Mockito.when(breachService.getBreachDataClasses()).thenReturn(Arrays.asList(new String[]{"Email addresses", "Password hints", "Passwords", "Usernames"}));

        this.mockMvc.perform(get("/breach/dataClasses"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Email addresses")))
                .andDo(document("breach-dataclasses"));
    }


    private ResponseFieldsSnippet getBreachResponseFields() {
        return responseFields(
                fieldWithPath("comments").type(JsonFieldType.STRING).description("Comments for the returned response; important especially to show problems"),
                fieldWithPath("breaches[].Name").type(JsonFieldType.STRING).description("A Pascal-cased name representing the breach which is unique across all other breaches. This value never changes and may be used to name dependent assets (such as images) but should not be shown directly to end users (see the \"Title\" attribute instead)"),
                fieldWithPath("breaches[].Title").type(JsonFieldType.STRING).description("A descriptive title for the breach suitable for displaying to end users. It's unique across all breaches but individual values may change in the future (i.e. if another breach occurs against an organisation already in the system). If a stable value is required to reference the breach, refer to the \"Name\" attribute instead"),
                fieldWithPath("breaches[].Domain").type(JsonFieldType.STRING).description("The domain of the primary website the breach occurred on. This may be used for identifying other assets external systems may have for the site."),
                fieldWithPath("breaches[].BreachDate").type(JsonFieldType.STRING).description("The date (with no time) the breach originally occurred on in ISO 8601 format. This is not always accurate — frequently breaches are discovered and reported long after the original incident. Use this attribute as a guide only."),
                fieldWithPath("breaches[].AddedDate").type(JsonFieldType.STRING).description("The date and time (precision to the minute) the breach was added to the system in ISO 8601 format."),
                fieldWithPath("breaches[].ModifiedDate").type(JsonFieldType.STRING).description("The date and time (precision to the minute) the breach was modified in ISO 8601 format. This will only differ from the AddedDate attribute if other attributes represented here are changed or data in the breach itself is changed (i.e. additional data is identified and loaded). It is always either equal to or greater then the AddedDate attribute, never less than."),
                fieldWithPath("breaches[].PwnCount").type(JsonFieldType.NUMBER).description("The total number of accounts loaded into the system. This is usually less than the total number reported by the media due to duplication or other data integrity issues in the source data."),
                fieldWithPath("breaches[].Description").type(JsonFieldType.STRING).description("Contains an overview of the breach represented in HTML markup. The description may include markup such as emphasis and strong tags as well as hyperlinks."),
                fieldWithPath("breaches[].DataClasses").type(JsonFieldType.ARRAY).description("This attribute describes the nature of the data compromised in the breach and contains an alphabetically ordered string array of impacted data classes."),
                fieldWithPath("breaches[].IsVerified").type(JsonFieldType.BOOLEAN).description("Indicates that the breach is considered unverified. An unverified breach may not have been hacked from the indicated website. An unverified breach is still loaded into HIBP when there's sufficient confidence that a significant portion of the data is legitimate."),
                fieldWithPath("breaches[].IsFabricated").type(JsonFieldType.BOOLEAN).description("Indicates that the breach is considered fabricated. A fabricated breach is unlikely to have been hacked from the indicated website and usually contains a large amount of manufactured data. However, it still contains legitimate email addresses and asserts that the account owners were compromised in the alleged breach."),
                fieldWithPath("breaches[].IsSensitive").type(JsonFieldType.BOOLEAN).description("Indicates if the breach is considered sensitive. The public API will not return any accounts for a breach flagged as sensitive."),
                fieldWithPath("breaches[].IsRetired").type(JsonFieldType.BOOLEAN).description("Indicates if the breach has been retired. This data has been permanently removed and will not be returned by the API."),
                fieldWithPath("breaches[].IsSpamList").type(JsonFieldType.BOOLEAN).description("Indicates if the breach is considered a spam list. This flag has no impact on any other attributes but it means that the data has not come as a result of a security compromise."),
                fieldWithPath("breaches[].LogoPath").type(JsonFieldType.STRING).description("A URI that specifies where a logo for the breached service can be found. Logos are always in PNG format.").optional());
    }
}
