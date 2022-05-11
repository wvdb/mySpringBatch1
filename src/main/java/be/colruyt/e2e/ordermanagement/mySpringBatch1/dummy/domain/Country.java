package be.colruyt.e2e.ordermanagement.mySpringBatch1.dummy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    private String code;
    private String name;
    private String continent;
    private int population;
    private List<String> languages = new ArrayList<>();

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###,###,##0");

        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", population=" + df.format(population) +
                '}';
    }
}
