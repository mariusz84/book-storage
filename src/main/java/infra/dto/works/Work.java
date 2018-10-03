package infra.dto.works;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Work {
    private String onsaledate;
    private String titleweb;
    private String workid;

    public Work() {
    }

    public String getOnsaledate() {
        return onsaledate;
    }

    public void setOnsaledate(String onsaledate) {
        this.onsaledate = onsaledate;
    }

    public String getTitleweb() {
        return titleweb;
    }

    public void setTitleweb(String titleweb) {
        this.titleweb = titleweb;
    }

    public String getWorkid() {
        return workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid;
    }

    @Override
    public String toString() {
        return "Work{" + "Title='" + titleweb + '\'' +
                ", OnSaleDate='" + onsaledate + '\'' +
                '}';
    }
}