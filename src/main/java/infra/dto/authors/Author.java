package infra.dto.authors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    private String authorfirst;
    private String authorlast;
    private Works works;

    public Author() {
    }

    public String getAuthorfirst() {
        return authorfirst;
    }

    public void setAuthorfirst(String authorfirst) {
        this.authorfirst = authorfirst;
    }

    public String getAuthorlast() {
        return authorlast;
    }

    public void setAuthorlast(String authorlast) {
        this.authorlast = authorlast;
    }

    public Works getWorks() {
        return works;
    }

    public void setWorks(Works works) {
        this.works = works;
    }
}