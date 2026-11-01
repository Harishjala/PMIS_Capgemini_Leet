class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int totTime = timeSeries.length * duration;
        int totOverlap = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            totOverlap += Math.max(0, timeSeries[i] + duration - timeSeries[i + 1]);
        }
        return totTime - totOverlap;
    }
}