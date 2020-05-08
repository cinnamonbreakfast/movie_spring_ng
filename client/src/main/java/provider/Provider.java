package provider;

public interface Provider<T> {
//    T being a DTO
    void save(T DTO);
    void update(T DTO);
    T getByID(Long ID);
    void delete(Long ID);
}
