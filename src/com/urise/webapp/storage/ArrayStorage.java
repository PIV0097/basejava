package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 * Хранилище на основе массива для резюме
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void update(Resume r) {
        if (r == null)
            throw new IllegalArgumentException("Resume can't be null");

        int index = getIndex(r.getUuid());

        if (index == -1)
            System.out.println("Resume not updated, missing from database");
        else
            storage[index] = r;
    }

    public void save(Resume r) {
        if (r == null)
            throw new IllegalArgumentException("Resume can't be null");

        if (size >= STORAGE_LIMIT) {
            throw new IllegalArgumentException("Storage is full");
        }

        if (getIndex(r.getUuid()) == -1) {
            storage[size] = r;
            size++;
        } else {
            throw new IllegalArgumentException("Resume with uuid" + r.getUuid() + " already exists in the database");
        }
    }

    public void delete(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID can't be null");
        }

        int index = getIndex(uuid);
        if (index == -1)
            throw new IllegalArgumentException("Resume with uuid " + uuid +
                    " already exists in the database");
        else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
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
