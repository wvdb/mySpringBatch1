package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.dummy.domain.Country;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

public class DummyApplication {
    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            System.out.println("argument missing. Byyyyyyye");
            System.exit(-1);
        }

        switch (args[0]) {
            case "streams1":
                myStreamMethod1();
                break;
            case "streams2":
                myStreamMethod2();
                break;
            case "streams3":
                mStreamMethod3();
                break;
            case "streams4":
                myStreamMethod4();
                break;
            case "streams5":
                myStreamMethod5();
                break;
            case "streams6":
                myStreamMethod6();
                break;
            case "streams7":
                myStreamMethod7();
                break;
            case "method1":
                myMethod1();
                break;
            case "myTokenMethod":
                myTokenMethod();
                break;
            case "myDateMethod1":
                myDateMethod1();
                break;
            default:
                System.out.println("Nossing to do ;-)");
        }
    }

    private static void myStreamMethod7() {
        List<Country> countries = getCountries();

        Map<Integer, Long> numberOfCountriesByNumberOfLanguage = new HashMap<>();

        SortedSet<Integer> sortedNumberOfLanguages = countries
                .stream()
                .map(country -> country.getLanguages().size()).collect(Collectors.toCollection(TreeSet::new));

        for (Integer numberOfLanguage : sortedNumberOfLanguages) {
            long numberOfCountriesWithThisNumberOfLanguages = countries.stream().filter(country -> country.getLanguages().size() == numberOfLanguage).count();
            numberOfCountriesByNumberOfLanguage.put(numberOfLanguage, numberOfCountriesWithThisNumberOfLanguages);
            System.out.printf("Number of countries with %d language = %d.%n", numberOfLanguage, numberOfCountriesWithThisNumberOfLanguages);
            System.out.printf("Countries with %d language = %s.%n", numberOfLanguage, countries.stream().filter(country -> country.getLanguages().size() == numberOfLanguage).collect(Collectors.toList()));
        }

    }

    private static void myDateMethod1() {
        for (Month m : Month.values()) {
            System.out.println(String.format("maand %s heeft %d dagen", m.getDisplayName(TextStyle.FULL, Locale.FRENCH), m.maxLength()));
        }
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            System.out.println(String.format("dag %s", dayOfWeek.getDisplayName(TextStyle.FULL, Locale.FRENCH)));
        }
    }

    private static void myTokenMethod() {
        StringTokenizer tokenizer = new StringTokenizer("Dit is een test" , " ");

        List<String> words1 = new ArrayList<>();
        while (tokenizer.hasMoreElements()) {
            words1.add(tokenizer.nextToken());
        }
        System.out.println(words1);
        System.out.println(">>>");

        List<String> words2 = new ArrayList<>();
        words2.addAll(Arrays.asList("Dit is nog een test".split(" ")));
        System.out.println(words2);
        System.out.println(">>>");

        List<String> words3 = new ArrayList<>();
        words3.addAll(Arrays.asList("Dit    is    nog een test".split("\\s+")));
        System.out.println(words3);
        System.out.println(">>>");
    }

    private static void myMethod1() {
        for (int i = 3; i<=100 ; i++) {
            boolean primer = true;
            for (int j = 2; j<i; j++) {
                if (i % j == 0) {
                    primer = false;
                    break;
                }
            }
            if (primer) {
                System.out.println("Figure " + i + " is a primer");
            }
        }
    }

    private static void myStreamMethod1() {
        List<Country> countries = getCountries();
        List<Country> noCountries = getEmptyCountries();

        Country maxCountry1 = countries.stream().max(Comparator.comparing(Country::getPopulation)).isPresent() ? countries.stream().max(Comparator.comparing(Country::getPopulation)).get() : null;
        Country maxCountry2 = noCountries.stream().max(Comparator.comparing(Country::getPopulation)).isPresent() ? noCountries.stream().max(Comparator.comparing(Country::getPopulation)).get() : null;

        System.out.println("MaxCountry = " + maxCountry1);
        System.out.println("MaxCountry = " + maxCountry2);
    }

    private static void myStreamMethod2() {
        List<Country> countries = getCountries();
        System.out.println("Number of European countries = " + countries.stream().filter(country -> country.getContinent().equals("Europe")).count());
    }

    private static void mStreamMethod3() {
        List<Country> countries = getCountries();

        List<Country> sortedCountries = countries.stream().sorted(Comparator.comparing(Country::getContinent).reversed().thenComparing(Country::getName)).collect(Collectors.toList());

        for (Country country : sortedCountries) {
            System.out.println("Sorted country = " + country.getContinent() + ", " + country.getName());
        }
    }

    private static void myStreamMethod4() {
        List<Country> countries = getCountries();

        List<Country> sortedCountries = countries.stream().sorted(Comparator.comparing(Country::getContinent).reversed().thenComparing(Country::getName)).collect(Collectors.toList());

        for (Country country : sortedCountries) {
            System.out.println(String.format("Sorted country = %s, %s, %s", country.getContinent(), country.getName(), country.getLanguages()));
        }
    }

    private static void myStreamMethod5() {
        List<Country> countries = getCountries();

        Map<String, Long> continentsWithNumberOfCountries =
                countries
                        .stream()
                        .collect(Collectors.groupingBy(Country::getContinent, Collectors.counting()));

        continentsWithNumberOfCountries.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(e -> {
            System.out.println(String.format("Continent %s has %d countries", e.getKey(), e.getValue()));
        });
    }

    private static void myStreamMethod6() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        System.out.println(numbers.stream().max(Comparator.comparing(integer -> integer)).get());
    }

    private static List<Country> getCountries() {
        Country country1 = new Country("BE", "Belgium", "Europe", 11_000_000, Arrays.asList("Dutch", "French"));
        Country country3 = new Country("USA", "USA", "America", 300_000_000, Arrays.asList("English", "Spanish"));
        Country country2 = new Country("NL", "Netherlands", "Europe", 18_000_000, Collections.singletonList("Dutch"));
        Country country4 = new Country("Mexico", "Mexico", "America", 150_000_000, Collections.singletonList("Spanish"));
        Country country5 = new Country("ZW", "Zwitserland", "Europe", 8_000_000, Arrays.asList("German", "French", "Schweiz", "Italian"));

        return Arrays.asList(country1, country2, country3, country4, country5);
    }

    private static List<Country> getEmptyCountries() {
        return Collections.emptyList();
    }
}
