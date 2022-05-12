package be.colruyt.e2e.ordermanagement.mySpringBatch1.dummy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CostCalculator <T extends Size> {
    private T size;

    public int calculateCost() {
        int factor = 0;
        if (size instanceof SmallSize) {
            factor = 1;
        }
        if (size instanceof MediumSize) {
            factor = 2;
        }
        if (size instanceof LargeSize) {
            factor = 3;
        }
        return size.getDepth() * size.getHeight() * size.getWidth() * factor;
    }

}
