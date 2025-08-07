package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 * Хранилище на основе массива для резюме
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        if (r == null)
            throw new IllegalArgumentException("Resume не может быть null");

        int index = getIndex(r.getUuid());

        if (index == -1)
            System.out.println("Резюме не обновлено, отсутствует в базе данных");

        storage[index] = r;
    }

    public void save(Resume r) {
        if (r == null)
            throw new IllegalArgumentException("Resume не может быть null");

        if (size >= STORAGE_LIMIT) {
            throw new IllegalArgumentException("Хранилище переполнено");
        }

        if (getIndex(r.getUuid()) == -1) {
            storage[size] = r;
            size++;
        } else {
            throw new IllegalArgumentException("Резюме с uuid" + r.getUuid() + " уже есть в базе данных");
        }
    }

    public void delete(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID не может быть пустым");
        }

        int index = getIndex(uuid);
        if (index == -1)
            throw new IllegalArgumentException("Резюме с UUID '" + uuid + "' не найдено");
        else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    /**
     * Возвращает индекс найденного элемента, будет исвользоваться в методах класса для исключения дублирования кода
     *
     * @param uuid идентификатор резюме для поиска
     * @return индекс резюме в массиве storage или -1 если не найдено
     */
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                return i;
        }
        return -1;
    }
}
