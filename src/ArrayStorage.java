import java.util.Arrays;

/**
 * Array based storage for Resumes
 * Хранилище на основе массива для резюме
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (r == null)
            throw new IllegalArgumentException("Resume не может быть null");
        if (size >= storage.length) {
            throw new IllegalArgumentException("Хранилище переполнено");
        }
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID не может быть null");
        }

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID не может быть пустым");
        }

        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                return;
            }
        }
        throw new IllegalArgumentException("Резюме с UUID '" + uuid + "' не найдено");
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

}
