import java.util.ArrayList;
import java.util.List;

public class KMP {
    List<Integer> searchSubstring(String text, String pattern) {
        int m = text.length(), n = pattern.length();
        var ans = new ArrayList<Integer>();
        int[] pi = new int[m];
        int left = 0, right = 1;
        while(right < m) {
            if(text.charAt(left) == text.charAt(right)) {
                pi[right++] = ++left;
            }
            else if(left != 0){
                left = pi[left-1];
            }
            else {
                right++;
            }
        }
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
        String pattern = "abcde";
        String text = "abcdabcdabcdabacdabdcdeeabcdeaddcvdeacvdeavcdeabcde";
        System.out.println(new KMP().searchSubstring(text, pattern));
    }
}
