package niffler.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class SpendModel {

    private String id;
    private String spendDate;
    private SpendCategoryModel category;
    private String currency;
    private Float amount;
    private String description;
    private String username;
}
