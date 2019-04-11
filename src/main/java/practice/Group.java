package practice;


import javax.persistence.*;

@Entity
@Table(name = "gruppa")
public class Group {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;


    public Group() {
    }

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
