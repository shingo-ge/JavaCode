package collection;

class Client {
    private String name;
    private String level;

    public Client(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
