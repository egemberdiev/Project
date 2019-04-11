package util;

import org.hibernate.Query;
import org.hibernate.Session;
import practice.Course;
import practice.Group;
import practice.Student;

import java.util.List;

public class JPA {

    public static void main(String[] args) {

        deleteAll("Student");
        deleteAll("Course");
        deleteAll("Group");

        Course course = new Course(1, "Java");
        Course course2 = new Course(2, "Python");
        Course course3 = new Course(3, "C#");
        createCourse(course);
        createCourse(course2);
        createCourse(course3);

        Group group = new Group(1, "Вечерняя группа");
        Group group2 = new Group(2, "Дневная группа");
        Group group3 = new Group(3, "Вечерняя группа");
        createGroup(group);
        createGroup(group2);
        createGroup(group3);
        Student student = new Student(1, "Alice", 22, "Female", course3, group3);
        Student student2 = new Student(2, "Mark", 20, "Male", course2, group);
        Student student3 = new Student(3, "Leo", 19, "Male", course, group2);
        createStudent(student);
        createStudent(student2);
        createStudent(student3);

        List<Student> list = read();
        list.stream().forEach(System.out::println);

        System.out.println(getStudentByAge(20));
        System.out.println(getStudentByGroup("Вечерняя группа"));
        System.out.println(getStudentByName("Mark"));
    }


    public static Integer createStudent(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created: " + student.toString());
        return student.getId();
    }

    public static Integer createCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(course);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created: " + course.toString());
        return course.getId();
    }

    public static Integer createGroup(Group group) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(group);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created: " + group.toString());
        return group.getId();

    }


    public static List<Student> read() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        //@SuppressWarnings("unchecked")
        List<Student> students = session.createQuery("From Student").list();
        session.close();
        System.out.println("Found " + students.size() + " students");
        return students;
    }

    public static void upDate(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Student st = (Student) session.load(Student.class, student.getId());
        st.setName(student.getName());
        st.setId(student.getId());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully changed " + student.toString());
    }

    public static void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Student s = findByID(id);
        session.delete(s);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + s.toString());
    }

    public static Student findByID(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Student s = (Student) session.load(Student.class, id);
        session.close();
        return s;
    }


    public static void deleteAll(String table) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM " + table);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("***** DELETED ALL *****");
    }

    public static List<Student> getStudentByAge(Integer age) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("From Student s where age >= :p_age").setParameter("p_age", age).setMaxResults(1).list();
        //List<Student> students = session.createQuery("From Student s where age >= :p_age").setParameter("p_age", age).list();
        session.close();
        return students;
    }

    public static List<Student> getStudentByGroup(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("From Student s where s.group.name like :p_name").setParameter("p_name", name).setMaxResults(1).list();
        session.close();
        return students;
    }

    public static List<Student> getStudentByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = session.createQuery("From Student s where name like :p_name").setParameter("p_name", name).list();
        session.close();
        return students;
    }
}
