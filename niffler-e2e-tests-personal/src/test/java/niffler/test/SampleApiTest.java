package niffler.test;

import niffler.api.NifflerSpendClient;
import niffler.jupiter.Spend;
import niffler.dto.SpendJson;
import niffler.jupiter.SpendConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ValueSource;

public class SampleApiTest {

    private NifflerSpendClient nifflerSpendClient = new NifflerSpendClient();

    @ValueSource(strings = {
            "data/JsonSamples/AddSpendingSample2.json",
            "data/JsonSamples/AddSpendingSample3.json"
    })
    @ParameterizedTest
    void addSpend(@ConvertWith(SpendConverter.class) SpendJson spend) throws Exception {
        SpendJson created = nifflerSpendClient.createSpend(spend);
        Assertions.assertNotNull(created.id());
    }
}
