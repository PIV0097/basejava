package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 * Хранилище на основе массива для резюме
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        if (r == null)
            throw new IllegalArgumentException("Resume не может быть null");

        for (int i = 0; i < size; i++) {
            if (r.getUuid().equals(storage[i].getUuid())) {
                storage[i] = r;
                return;
            }
        }
        System.out.println("Резюме не обновлено, отсутствует в базе данных");
    }

    public void save(Resume r) {
        if (r == null)
            throw new IllegalArgumentException("Resume не может быть null");
        if (size >= storage.length) {
            throw new IllegalArgumentException("Хранилище переполнено");
        }
        for (int i = 0; i < size; i++) {
            if (r.getUuid().equals(storage[i].getUuid())) {
                throw new IllegalArgumentException("Резюме с uuid" + r.getUuid() + " уже есть в базе данных");
            }
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID не может быть null");
        }

        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Резюме с uuid " + uuid + " нет в базе данных");
        return null;
    }

    public void delete(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID не может быть пустым");
        }

        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                return;
            }
        }
        throw new IllegalArgumentException("Резюме с UUID '" + uuid + "' не найдено");
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

}
