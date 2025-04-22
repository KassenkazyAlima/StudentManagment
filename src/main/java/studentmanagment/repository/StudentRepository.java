package studentmanagment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import studentmanagment.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
