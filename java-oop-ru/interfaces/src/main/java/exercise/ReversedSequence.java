package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    String string;

    public ReversedSequence(String abcdef) {
        this.string = abcdef;
    }

    @Override
    public int length() {
        return string.length();
    }

    @Override
    public char charAt(int i) {
        return new StringBuilder(this.string).reverse().charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return new StringBuilder(this.string).reverse().substring(i, i1);
    }

    public String toString() {
        return new StringBuilder(this.string).reverse().toString();
    }
}
// END
