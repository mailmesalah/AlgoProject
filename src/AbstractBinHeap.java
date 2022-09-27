
public abstract class AbstractBinHeap<KeyType extends Comparable<KeyType>> implements IHeap<KeyType> {

	protected int       N;
	protected KeyType[] array;
	
	@SuppressWarnings("unchecked")
	public AbstractBinHeap(int capacity) {
		N = 0;
		array = (KeyType[]) new Comparable[capacity];
	}
	
	public AbstractBinHeap(KeyType[] initialArray) {
		N = initialArray.length;
		array = initialArray;
		heapify();
	}
	
	// *****************
	// ABSTRACT FUNCTION
	// *****************
	
	// This function determines whether this is a min-heap or max-heap.
	protected abstract boolean shouldBeBelow(int top, int bottom);
	
	// *******************
	// INTERFACE FUNCTIONS
	// *******************
	
	public int size() { return N; }
	
	public boolean isEmpty() { return N == 0; }
	
	public void insert(KeyType v) {
		N++;
		if(N == array.length) this.resize(2 * array.length);
		array[N] = v;
		moveUp(N);
	}
	
	public KeyType delete() {
		KeyType root = array[1];   // Retrieve the root element.
		swap(1, N);                // Swap it with last item.
		array[N+1] = null; N--;    // Remove last item from array.
		moveDown(1);               // Move down the new root to its correct
		                           // position in the heap.
		return root;
	}
	
	// *****************
	// UTILITY FUNCTIONS
	// *****************
	
	protected void heapify()
	{
		// This function enforces heap order in an array that is initially
		// completely unordered. (From textbook p324.)
		
		// We enforce heap order on progressively larger heaps, from the
		// bottom up. We start at k=N/2, because the leaves alone are already
		// in heap order, and don't need to be looked at separately.
		
		for(int k = N/2; k >= 1; k--)
			moveDown(k);
	}
	
	protected void moveUp(int k)
	{
		// As long as the parent of k should move down
		// we swap k with its parent. (From textbook p316.)
		while(k > 1 && shouldBeBelow(k/2, k)) {
			swap(k/2, k);
			k = k/2;
		}
	}
	
	protected void moveDown(int k)
	{
		// While k is not one of the leaves, and needs to be moved down
		// we swap it with the smaller/larger child. (From textbook p316.)
		while(2*k <= N) {
			int j, leftChild = 2*k, rightChild = 2*k+1;
			// Pick the correct child with which to possibly swap k.
			if (leftChild < N && shouldBeBelow(leftChild, rightChild)) j = rightChild;
			else j = leftChild;
			// Check if k actually needs to be swapped.
			if (!shouldBeBelow(k, j)) break;
			else {
				swap(k, j);
				k = j;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void resize(int newcapacity)
	{	
		if(newcapacity >= array.length)
		{
			KeyType[] newarray = (KeyType[]) new Comparable[newcapacity];
			for(int i = 0; i < array.length; i++)
				newarray[i] = array[i];
			 array = newarray;
		}
	 }
	
	protected void swap(int i, int j)
	{
		KeyType tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}
