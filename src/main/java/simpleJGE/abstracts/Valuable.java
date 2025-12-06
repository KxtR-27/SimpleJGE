package simpleJGE.abstracts;

@SuppressWarnings("unused")
// This warning is incorrect.
// at time of writing, this has 9 implementations.

public interface Valuable<T> {
	T getValue();
	void setValue(T valuable);
}
