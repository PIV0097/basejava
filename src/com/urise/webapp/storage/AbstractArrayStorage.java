package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Abstract class for implementing the template pattern
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (r == null)
            throw new IllegalArgumentException("Resume can't be null");

        if (size >= STORAGE_LIMIT) {
            throw new IllegalArgumentException("Storage is full");
        }

        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new IllegalArgumentException("Resume with uuid" + r.getUuid()
                    + " already exists in the database");
        } else {
            insertElement(r, index);
            size++;
        }
    }

    public void delete(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID can't be null");
        }

        int index = getIndex(uuid);
        if (index < 0)
            throw new IllegalArgumentException("Resume with uuid " + uuid +
                    " already exists in the database");
        else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    @Override
    public void update(Resume r) {
        if (r == null)
            throw new IllegalArgumentException("Resume can't be null");

        int index = getIndex(r.getUuid());

        if (index < 0)
            System.out.println("Resume not updated, missing from database");
        else
            storage[index] = r;
    }

    public Resume get(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID не может быть null");
        }
        int index = getIndex(uuid);
        if (index >= 0)
            return storage[index];

        System.out.println("Резюме с uuid " + uuid + " нет в базе данных");
        return null;
    }

    protected abstract int getIndex(String uuid);

    public int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}
