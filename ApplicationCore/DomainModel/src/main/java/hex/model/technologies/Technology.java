package hex.model.technologies;

public class Technology {
    private String technologyName;

    Technology(String technologyName) {
        this.technologyName = technologyName;
    }

    public Technology() {}

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }
}
