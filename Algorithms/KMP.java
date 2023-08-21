import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMP {
    List<Integer> searchSubstring(String text, String pattern) {
        int m = text.length(), n = pattern.length();
        var ans = new ArrayList<Integer>();
        int[] pi = new int[n];
        int left = 0, right = 1;
        while(right < n) {
            if(pattern.charAt(left) == pattern.charAt(right)) {
                pi[right++] = ++left;
            }
            else if(left != 0){
                left = pi[left-1];
            }
            else {
                right++;
            }
        }
        System.out.println(Arrays.toString(pi));
        int txtPtr = 0, patPtr = 0;
        while(txtPtr < m) {
            if(text.charAt(txtPtr) == pattern.charAt(patPtr)) {
                txtPtr++;
                patPtr++;
                if(patPtr == n) {
                    ans.add(txtPtr-patPtr);
                    patPtr = pi[patPtr-1];
                }
            }
            else if(patPtr != 0){
                patPtr = pi[patPtr-1];
            }
            else txtPtr++;
        }
        return ans;
    }
    public static void main(String[] args) {
        String pattern = "aab";
        String text = "abcdabcdabcdabacdabdcdeeabcdeaddcvdeacvdeavcdeabcde";
        System.out.println(new KMP().searchSubstring(text, pattern));
    }
}
