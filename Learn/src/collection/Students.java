package collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Students {
    private List<Student> studentList;
    private Map<String, Double> cache;

    public Students(List<Student> studentList) {
        this.studentList = studentList;
        cache = new HashMap<>();
    }

    public double getScore(String name) {
        //先在Map中查找
        Double score = cache.get(name);
        //如果找不到再到List中查找
        if (score == null){
            score = findInList(name);
            //如果在List中找到了，就更新到Map中
            if (score != null){
                cache.put(name,score);
            }
        }
        return score==null? -1:score;
    }

    private Double findInList(String name) {
        for (Student student : studentList) {
            if (student.getName().equals(name)){
                return student.getScore();
            }
        }
        return null;
    }
}
