package infra.dto.ip;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryIp {
    private String country_name;

    public CountryIp() {
    }

    public String getCountryName() {
        return country_name;
    }

    public void setCountryName(String country_name) {
        this.country_name = country_name;
    }

    @Override
    public String toString() {
        return "CountryIp{" + "CountryName='" + getCountryName() + '\'' +
                '}';
    }
}