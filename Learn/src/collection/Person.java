package collection;

import java.util.Objects;

class Person {
    private String firstNamre;
    private String lastName;
    private int age;

    public Person(String firstNamre, String lastName, int age) {
        this.firstNamre = firstNamre;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstNamre() {
        return firstNamre;
    }

    public void setFirstNamre(String firstNamre) {
        this.firstNamre = firstNamre;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person){
            Person person = (Person) obj;
            return Objects.equals(this.firstNamre,person.firstNamre)&&
                    Objects.equals(this.lastName,person.lastName)&&
                    this.age==person.age;
        }
        return false;
    }
}
