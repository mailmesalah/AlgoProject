
public class MinPQ<KeyType extends Comparable<KeyType>> extends AbstractBinHeap<KeyType> {

	public MinPQ(int capacity) { super(capacity); }
	public MinPQ(KeyType[] array) { super(array); }
	
	@Override
	protected boolean shouldBeBelow(int top, int bottom) {
		// In a min-heap the larger elements are at the bottom,
		// so array[top] should be below if it is larger than
		// array[bottom].
		
		// Check array[top] > array[bottom].
		return array[top].compareTo(array[bottom]) > 0;
	}
}
