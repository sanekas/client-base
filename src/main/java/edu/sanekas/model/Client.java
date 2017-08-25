package edu.sanekas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by sanekas on 14/05/2017.
 */

@JsonIgnoreProperties(allowGetters = true)
public class Client extends ResourceSupport {
    @Id
    private String dbId;

    @ApiModelProperty(required = true)
    private String name;

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return (dbId != null ? dbId.equals(client.dbId) : client.dbId == null) &&
                (name != null ? name.equals(client.name) : client.name == null);
    }

    @Override
    public int hashCode() {
        int result = dbId != null ? dbId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "dbId='" + dbId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
