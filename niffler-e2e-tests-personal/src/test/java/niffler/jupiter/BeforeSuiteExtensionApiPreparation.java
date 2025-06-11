package niffler.jupiter;

import com.fasterxml.jackson.databind.ObjectMapper;
import niffler.api.NifflerSpendClient;
import niffler.dto.SpendJson;

import java.io.File;
import java.io.IOException;

public class BeforeSuiteExtensionApiPreparation implements AroundAllTestsExtension {

    private NifflerSpendClient nifflerSpendClient = new NifflerSpendClient();
    private ObjectMapper objectMapper = new ObjectMapper();
    private SpendJson testSpend = objectMapper
            .readValue(new File("src/test/resources/data/JsonSamples/AddSpendingSample1.json"), SpendJson.class);

    public BeforeSuiteExtensionApiPreparation() throws IOException {
    }

    @Override
    public void beforeAllTests() {
        try {
            nifflerSpendClient.createSpend(testSpend);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a test spend: " + e);
        }
    }

    @Override
    public void afterAllTests() {

    }
}
