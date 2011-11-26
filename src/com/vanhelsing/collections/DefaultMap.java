package com.vanhelsing.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.vanhelsing.Classification;

public class DefaultMap<K, V> implements Map<K, V> {

	private final Map<K,V> map;
	private final DefaultFunction<V> defaultElement;

	public DefaultMap(Map<K,V> map,DefaultFunction<V> defaultElement) {
		this.map = map;
		this.defaultElement = defaultElement;
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return map.entrySet();
	}

	@Override
	public V get(Object key) {
		V element = map.get(key);
		if (element == null) {
			V dynamicallyInitializedDefaultValue = defaultElement.initialize();
			map.put((K) key, dynamicallyInitializedDefaultValue);
			return dynamicallyInitializedDefaultValue;
		}

		return element;

	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public V put(K key, V value) {
		return map.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		map.putAll(arg0);
	}

	@Override
	public V remove(Object key) {
		return map.remove(key);
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Collection<V> values() {
		return map.values();
	}
}
