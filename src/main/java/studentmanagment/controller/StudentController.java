package studentmanagment.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import studentmanagment.model.Student;
import studentmanagment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")

public class StudentController {
    @Autowired
    private StudentRepository repository;

    @GetMapping
    public List<Student> getAll(){
        return repository.findAll();
    }

    @PostMapping
    public Student create(@RequestBody @Valid Student student) {
        return repository.save(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody @Valid Student newStudent){
        return repository.findById(id).map(student -> {
            student.setName(newStudent.getName());
            student.setEmail(newStudent.getEmail());
            student.setAge(newStudent.getAge());
            return ResponseEntity.ok(repository.save(student));
        })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
