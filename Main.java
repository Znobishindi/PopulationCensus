import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println("Несовершеннолетних граждан найдено: " + count + " человек(а)");

        List<String> conscripts = persons.stream()
                .filter(man -> man.getSex()
                        .equals(Sex.MAN))
                .filter(value -> value.getAge() >= 18 && value.getAge() < 27)
                .map(Person::getFamily).toList();
        System.out.println("Фамилии призывников: ");
        //  System.out.println(conscripts);

        List<Person> ableBodiedPeople = persons.stream()
                .filter(edu -> edu.getEducation().equals(Education.HIGHER))
                .filter(notYang -> notYang.getAge() >= 18)
                .filter(man -> man.getSex().equals(Sex.MAN) && man.getAge() <= 65
                        || man.getSex().equals(Sex.WOMAN) && man.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();
        System.out.println("Работоспособные граждане: ");
       // System.out.println(ableBodiedPeople);
    }
}
