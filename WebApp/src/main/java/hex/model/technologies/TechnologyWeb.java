package hex.model.technologies;

public class TechnologyWeb {
    private String technologyName;

    TechnologyWeb(String technologyName) {
        this.technologyName = technologyName;
    }

    public TechnologyWeb() {}

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }
}
