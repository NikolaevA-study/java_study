package org.example;

public  class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private Entry<K, V>[] table;
    private int size;

    public CustomHashMap() {
        table = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int hash = hash(key);
        int index = hash % table.length;

        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        Entry<K, V> newEntry = new Entry<>(key, value, table[index]);
        table[index] = newEntry;
        size++;

        // Перераспределение при достижении порога загрузки
        if ((float) size / table.length >= 0.75f) {
            resize();
        }
    }

    public V get(K key) {
        int hash = hash(key);
        int index = hash % table.length;

        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    private void resize() {
        Entry<K, V>[] newTable = new Entry[table.length * 2];

        for (int i = 0; i < table.length; i++) {
            Entry<K, V> current = table[i];
            while (current != null) {
                int newIndex = hash(current.key) % newTable.length;
                Entry<K, V> next = current.next;
                current.next = newTable[newIndex];
                newTable[newIndex] = current;
                current = next;
            }
            table[i] = null; // Сброс старых ссылок
        }
        table = newTable;
    }

    // Простая хеш-функция для ключей
    private int hash(K key) {
        return key.hashCode() & 0x7FFFFFFF;
    }

    static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}