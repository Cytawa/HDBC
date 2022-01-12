package pl.sdacademy.java.adv.school.domain.student;

import pl.sdacademy.java.adv.school.domain.student.model.Student;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentsSortedByCityAndName() {

        throw new UnsupportedOperationException();
    }

    public List<Student> getStudentsSortedByAge() {

        List<Student> allStudentsAge = studentRepository.findAllStudents();

        return allStudentsAge.stream()
                .sorted(Comparator.comparing(Student::getBirthDate))
                .collect(Collectors.toUnmodifiableList());

    }

    public List<Student> getStudentsSortedByAgeDesc() {
        List<Student> allStudentsAge = studentRepository.findAllStudents();

        return allStudentsAge.stream()
                .sorted(Comparator.comparing(Student::getBirthDate).reversed())
                .collect(Collectors.toUnmodifiableList());
    }
}
