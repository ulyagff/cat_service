package Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
@Entity
public class Cat {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    private String name;
    @Getter
    private LocalDate birthday;
    @Getter
    private String breed;
    @Getter
    @Enumerated(EnumType.STRING)
    private ColorType color;
    @Getter @Setter
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Cat() {
    }

    public Cat(String name, LocalDate birthday, String breed, ColorType color, Owner owner) {
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
    }
    public enum ColorType {white, black, gray, multicolor}
}
