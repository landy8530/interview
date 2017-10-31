package lyx.demo.hashmap;

/**
 * Created by landyChris on 2017/10/29.
 */
public interface Map<K,V> {

    V put(K key, V value);

    V get(K key);

    int size();

    public interface  Entry<K,V> {
        K getKey();

        V getValue();
    }
}
