package fr.diginami.spring.core.model;

public class Disease {

    private String name;
    private Integer lethality;
    private Integer contagion;

    public Disease(String name, Integer lethality, Integer contagion) {
        this.name = name;
        this.lethality = lethality;
        this.contagion = contagion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLethality() {
        return lethality;
    }

    public void setLethality(Integer lethality) {
        this.lethality = lethality;
    }

    public Integer getContagion() {
        return contagion;
    }

    public void setContagion(Integer contagion) {
        this.contagion = contagion;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "name='" + name + '\'' +
                ", lethality=" + lethality +
                ", contagion=" + contagion +
                '}';
    }
}
