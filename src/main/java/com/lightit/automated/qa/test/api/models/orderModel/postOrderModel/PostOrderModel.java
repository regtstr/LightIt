package com.lightit.automated.qa.test.api.models.orderModel.postOrderModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import static com.lightit.automated.qa.test.api.utilits.dataGenerator.UserDataGeneratorV3.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostOrderModel{

    @JsonProperty("owner")
    private Integer owner;

    @JsonProperty("quantity")
    private Object quantity;

    @JsonProperty("cost")
    private Object cost;

    @JsonProperty("name")
    private String name;

    @JsonProperty("bid_type")
    private Integer bidType;

    @JsonProperty("status")
    private Integer status;

    public PostOrderModel randomize(){
        this.quantity = getFakerRanddomDigit();
        this.cost = getFakerRandomDoubleNumberBetween(2,1, 99);
        this.name = getRandomMacAddress();
        this.status = getFakerRandomIntNumberBetween(1, 3);
        this.bidType = getFakerRandomIntNumberBetween(1, 2);

        return this;
    }

}