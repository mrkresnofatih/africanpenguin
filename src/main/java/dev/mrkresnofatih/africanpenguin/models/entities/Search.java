package dev.mrkresnofatih.africanpenguin.models.entities;

import dev.mrkresnofatih.africanpenguin.models.dtos.SearchGetDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Search extends DynamoEntity {
    public Search() {
    }

    public Search(String primaryKey, String sortKey) {
        super(primaryKey, sortKey);
    }

    public Search(String primaryKey, String sortKey, String partnerId, Boolean running) {
        super(primaryKey, sortKey);
        PartnerId = partnerId;
        Running = running;
    }

    @NotNull
    @NotBlank
    private String PartnerId;

    @NotNull
    private Boolean Running;

    public String getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(String partnerId) {
        PartnerId = partnerId;
    }

    public Boolean getRunning() {
        return Running;
    }

    public void setRunning(Boolean running) {
        Running = running;
    }

    public SearchGetDto ToSearchGetDto() {
        return new SearchGetDto(PartnerId, Running);
    }
}
