package com.lightit.automated.qa.test.api.models.orderModel.postOrderModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostOrderModelResponse{

    @JsonProperty("owner")
    private Integer owner;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("cost")
    private String cost;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("bid_type")
    private Integer bidType;

    @JsonProperty("status")
    private Integer status;
}