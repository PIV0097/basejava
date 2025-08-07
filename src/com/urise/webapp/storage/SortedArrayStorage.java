package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

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

    @Override
    public void save(Resume r) {
        if (r == null)
            throw new IllegalArgumentException("Resume can't be null");

        if (size >= STORAGE_LIMIT) {
            throw new IllegalArgumentException("Storage is full");
        }

        int insertionPoint = getIndex(r.getUuid());

        if (insertionPoint >= 0)
            throw new IllegalArgumentException("Resume with uuid" + r.getUuid()
                    + " already exists in the database");
        else {
            int from = Math.abs(insertionPoint) - 1;

            if (from != size) {
                for (int i = from; i < size; i++) {
                    storage[from + 1] = storage[from];
                }
            }
            storage[from] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
