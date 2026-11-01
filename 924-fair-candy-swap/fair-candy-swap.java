import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sumA = 0;
        int sumB = 0;
        Set<Integer> bobSet = new HashSet<>();
        
        // Calculate total candies for Alice
        for (int x : aliceSizes) {
            sumA += x;
        }
        
        // Calculate total candies for Bob and populate the set
        for (int y : bobSizes) {
            sumB += y;
            bobSet.add(y);
        }
        
        // Target difference to balance the equations
        int delta = (sumB - sumA) / 2;
        
        // Find the matching pair
        for (int x : aliceSizes) {
            int targetY = x + delta;
            if (bobSet.contains(targetY)) {
                return new int[]{x, targetY};
            }
        }
        
        return new int[0]; // Guaranteed to have an answer per constraints
    }
}
