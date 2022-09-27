
public interface IHeap<KeyType extends Comparable<KeyType>> {
	void insert(KeyType v);
	KeyType delete();
	boolean isEmpty();
	int size();
}
