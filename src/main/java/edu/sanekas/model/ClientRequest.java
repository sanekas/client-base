package edu.sanekas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.Link;

@JsonIgnoreProperties
public class ClientRequest extends Client {

    @ApiModelProperty(hidden = true)
    @Override
    public void setDbId(String dbId) {
        super.setDbId(dbId);
    }

    @ApiModelProperty(hidden = true)
    public void setLinks(final Link... links) {
        super.add(links);
    }
}
