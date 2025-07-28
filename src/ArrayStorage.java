import java.util.Arrays;

/**
 * Array based storage for Resumes
 * Хранилище на основе массива для резюме
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume r) {//метод для сохранения резюме в массиве
        if (r == null)
            throw new IllegalArgumentException("Resume не может быть null");
        if (size() >= storage.length) {
            throw new IllegalArgumentException("Хранилище переполнено");
        }
        storage[size()] = r;
        size++;
    }


    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid == null) {
                throw new IllegalArgumentException("UUID не может быть null");
            }
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    //удаляет резюме из массива
    void delete(String uuid) {
        int indexToDelete = -1;

        if (uuid == null) {
            throw new IllegalArgumentException("UUID не может быть пустым");
        }

        for (int i = 0; i < size(); i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            System.out.println("Элемент не найден");
            return;
        }

        for (int i = indexToDelete; i < size() - 1; i++) {
            storage[i] = storage[i + 1];
        }

        storage[size() - 1] = null;
        size--;
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {//количесиво резюме в хранилище
        return size;
    }

}
