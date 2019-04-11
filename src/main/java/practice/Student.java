package practice;


import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")

    private Integer age;

    @Column(name = "gender")
    private String gender;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gruppa_id")
    private Group group;

    public Student() {
    }

    public Student(Integer id, String name, Integer age, String gender, Course course, Group group) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.course = course;
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", course=" + course +
                ", group=" + group +
                '}';
    }
}
