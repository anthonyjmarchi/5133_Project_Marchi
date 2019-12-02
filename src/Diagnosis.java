public class Diagnosis {

    int id = 0;
    String name = "";
    int count = 0;

    public Diagnosis(int id, String name, int count) {
        id = this.id;
        name = this.name;
        count = this.count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
