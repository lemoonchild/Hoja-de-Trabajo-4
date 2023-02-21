package Model;
/**
 * @author MAAG
 * Tomado del reposotorio de la sección 10 creado por Moises Alonso 
 * Hoja de Trabajo #4
 */
public interface IList<T> {

    void InsertAtStart(T value);

    void InsertAtEnd(T value);

    void Insert(T value, int index);

    T Delete(int index);

    T DeleteAtStart();

    T DeleteAtEnd();

    T Get(int index);

    boolean IsEmpty();
    
    int Count();
}
