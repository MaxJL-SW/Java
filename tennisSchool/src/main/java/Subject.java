public class Subject {
    private int id;
    private String name;
    private double factor;
    
    public Subject(String name, double factor, int id) {
        this.name = name;
        this.factor = factor;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getfactor() {
        return factor;
    }

    public void setfactor(double factor) {
        this.factor = factor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}