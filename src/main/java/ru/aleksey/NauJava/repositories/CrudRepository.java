package ru.aleksey.NauJava.repositories;

public interface CrudRepository<T, ID> {
    void create(T entry);

    T read(ID id);

    void update(T entry);

    void delete(ID id);
}
