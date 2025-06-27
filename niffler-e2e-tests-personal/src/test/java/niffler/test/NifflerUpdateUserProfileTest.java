package niffler.test;

import niffler.api.NifflerUserdataClient;
import niffler.dto.UserJson;
import niffler.jupiter.UserConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ValueSource;

public class NifflerUpdateUserProfileTest {

    private NifflerUserdataClient nifflerUserdataClient = new NifflerUserdataClient();

    @ValueSource(strings = {
            "data/JsonSamples/UpdateUserSample1.json",
            "data/JsonSamples/UpdateUserSample2.json"
    })
    @ParameterizedTest
    void updateProfile(@ConvertWith(UserConverter.class) UserJson userJson) throws Exception {
        UserJson updatedState = nifflerUserdataClient.updateUser(userJson);
        Assertions.assertEquals(updatedState.id(), userJson.id());
        Assertions.assertEquals(updatedState.fullname(), userJson.fullname());
    }
}
