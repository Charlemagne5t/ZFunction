import java.util.ArrayList;
import java.util.List;

public class ZFunction {
    String text;
    String pattern;
    String concat;
    int l;
    int[] zArray;

    public ZFunction(String text, String pattern) {
        this.text = text;
        this.pattern = pattern;
        this.concat = pattern + "$" + text;
        l = concat.length();
        zArray = new int[l];
        createZarr();
    }

    public List<Integer> find() {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < l; ++i) {
            if (zArray[i] == pattern.length()) {
                indexes.add(i - pattern.length() - 1);
            }
        }
        return indexes;
    }

    private void createZarr() {
        int left = 0;
        int right = 0;
        for (int i = 1; i < l; ++i) {
            if (i > right) {
                left = right = i;
                while (right < l && concat.charAt(right - left) == concat.charAt(right)) {
                    right++;
                }
                zArray[i] = right - left;
                right--;
            } else {
                int k = i - left;
                if (zArray[k] < right - i + 1) {
                    zArray[i] = zArray[k];
                } else {
                    left = i;
                    while (right < l && concat.charAt(right - left) == concat.charAt(right)) {
                        right++;
                    }

                    zArray[i] = right - left;
                    right--;
                }
            }
        }
    }
}
