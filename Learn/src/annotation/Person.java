package annotation;


class Person {
    @Range(min = 2,max = 20)
    private String name;
    @Range(min = 1,max = 199)
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

}
