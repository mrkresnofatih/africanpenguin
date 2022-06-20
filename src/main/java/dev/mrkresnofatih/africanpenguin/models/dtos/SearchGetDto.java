package dev.mrkresnofatih.africanpenguin.models.dtos;

public class SearchGetDto {
    private String PartnerId;

    private Boolean Running;

    public SearchGetDto() {
    }

    public SearchGetDto(String partnerId, Boolean running) {
        PartnerId = partnerId;
        Running = running;
    }

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
}
