package main.resources.tp2.repository;

public interface JPARepository<T> {

    public void save(T t);

}
