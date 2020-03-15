package hex.model.technologies;

public class TechnologyEnt {
    private String technologyName;

    TechnologyEnt(String technologyName) {
        this.technologyName = technologyName;
    }

    public TechnologyEnt() {}

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }
}
