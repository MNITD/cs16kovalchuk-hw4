package ua.edu.ucu.collections.immutable;

public class ImmutableArrayList implements ImmutableList {

    private Object[] array;

    public ImmutableArrayList() {
        array = new Object[0];
    }

    // private constructor for add(Object e)
    private ImmutableArrayList(Object[] oldArray, Object e) {
        int len = oldArray.length + 1;
        array = new Object[len];
        for (int i = 0; i < oldArray.length; i++) {
            array[i] = oldArray[i];
        }
        array[len - 1] = e;
    }

    // private constructor for add(int index, Object e)
    private ImmutableArrayList(Object[] oldArray, int index, Object e) {
        int len = oldArray.length + 1;
        array = new Object[len];
        for (int i = 0; i < index; i++) {
            array[i] = oldArray[i];
        }
        array[index] = e;

        for (int i = index; i < oldArray.length; i++) {
            array[i + 1] = oldArray[i];
        }
    }

    // private constructor for add(Object[] c)
    private ImmutableArrayList(Object[] oldArray, Object[] c) {
        int len = oldArray.length + c.length;
        array = new Object[len];
        for (int i = 0; i < oldArray.length; i++) {
            array[i] = oldArray[i];
        }
        for (int i = 0; i < c.length; i++) {
            array[i + oldArray.length] = c[i];
        }
    }

    // private constructor for add(int index, Object[]c)
    private ImmutableArrayList(Object[] oldArray, int index, Object[] c) {
        int len = oldArray.length + c.length;
        array = new Object[len];
        for (int i = 0; i < index; i++) {
            array[i] = oldArray[i];
        }
        for (int i = 0; i < c.length; i++) {
            array[index + i] = c[i];
        }
        for (int i = index; i < oldArray.length; i++) {
            array[i + c.length] = oldArray[i];
        }
    }

    // private constructor for remove(int index)
    private ImmutableArrayList(Object[] oldArray, int index) {
        int len = oldArray.length - 1;
        array = new Object[len];
        for (int i = 0; i < index; i++) {
            array[i] = oldArray[i];
        }

        for (int i = index; i < oldArray.length - 1; i++) {
            array[i] = oldArray[i + 1];
        }
    }

    @Override
    public ImmutableArrayList add(Object e) {
        return new ImmutableArrayList(array, e);
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        try {
            if (index > array.length) {
                throw new Exception("Index is out of boundary");
            }
        } catch (Exception ex) {
            return null;
        }
        return new ImmutableArrayList(array, index, e);
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        return new ImmutableArrayList(array, c);
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        try {
            if (index > array.length) {
                throw new Exception("Index is out of boundary");
            }
        } catch (Exception ex) {
            return null;
        }
        return new ImmutableArrayList(array, index, c);
    }

    @Override
    public Object get(int index) {
        try {
            if (index >= array.length) {
                throw new Exception("Index is out of boundary");
            }
        } catch (Exception ex) {
            return null;
        }

        return array[index];
    }

    @Override
    public ImmutableArrayList remove(int index) {
        return new ImmutableArrayList(array, index);
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        return remove(index).add(index, e);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < array.length - 1; i++) {
            result += array[i].toString() + ", ";
        }
        result += array[array.length - 1].toString();
        return result;
    }
}
