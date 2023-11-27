package org.example.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    private Long id;
    private String name;
    private String lastName;
    private byte age;

    @Override
   public String toString() {
        return "id: "+ id + "\nname: " + name + "\nlastName: " + lastName + "\nage: " + age + "\n";
    }
}
