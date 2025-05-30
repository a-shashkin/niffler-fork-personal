package niffler.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class SpendCategoryModel {

    private String id;
    private String name;
    private String username;
    private boolean archived;
}
