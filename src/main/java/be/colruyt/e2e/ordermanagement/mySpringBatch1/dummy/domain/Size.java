package be.colruyt.e2e.ordermanagement.mySpringBatch1.dummy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    private String name;
    private int height;
    private int width;
    private int depth;
}
