package edu.sanekas.model;

import io.swagger.annotations.ApiModelProperty;

public class ClientRequest extends Client {

    @ApiModelProperty(hidden = true)
    @Override
    public void setId(String id) {
        super.setId(id);
    }
}
