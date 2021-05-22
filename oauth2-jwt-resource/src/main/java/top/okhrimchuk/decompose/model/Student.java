package top.okhrimchuk.decompose.model;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(callSuper = true)
public class Student extends Person  {
    @NotBlank
    @Size(min = 1, max = 100)
    private String contract;

    @NotBlank
    @Size(min = 1, max = 1000)
    private String description;

    @Builder
    public Student(int id,String firstName, String lastName, String contract, String description){
        super(id,firstName, lastName);
        this.contract = contract;
        this.description = description;
    }
}
