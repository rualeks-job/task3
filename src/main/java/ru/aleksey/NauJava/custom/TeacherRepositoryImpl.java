package ru.aleksey.NauJava.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.aleksey.NauJava.objects.Classroom;
import ru.aleksey.NauJava.objects.Teacher;

@Repository
public class TeacherRepositoryImpl implements TeacherRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    public TeacherRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Teacher findByNameAndSurname(String name, String surname) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);

        Root<Teacher> teacherRoot = criteriaQuery.from(Teacher.class);
        Predicate namePredicate = criteriaBuilder.equal(teacherRoot.get("name"), name);
        Predicate surnamePredicate = criteriaBuilder.equal(teacherRoot.get("surname"), surname);
        Predicate finalPredicate = criteriaBuilder.and(namePredicate, surnamePredicate);
        criteriaQuery.select(teacherRoot).where(finalPredicate);

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public String findNameByClassroomTitle(String title) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);

        Root<Teacher> teacherRoot = criteriaQuery.from(Teacher.class);
        Join<Teacher, Classroom> classroomJoin = teacherRoot.join("classroom", JoinType.INNER);
        Predicate namePredicate = criteriaBuilder.equal(classroomJoin.get("title"), title);
        criteriaQuery.select(teacherRoot.get("name")).where(namePredicate);

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
