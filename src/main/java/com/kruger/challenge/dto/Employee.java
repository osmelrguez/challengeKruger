package com.kruger.challenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kruger.challenge.enums.Vaccine;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @Pattern(regexp = "[0-9]{10}", message = "Id not valid")
    private String id;
    @NotNull(message = "Not null")
    @NotBlank(message = "Name is required")
    @Pattern(regexp =  "[a-zA-Z]+", message = "Only letters")
    private String names;
    @NotNull(message = "Not null")
    @NotBlank(message = "Last Name is required")
    @Pattern(regexp =  "[a-zA-Z]+", message = "Only letters")
    private String lastName;
    @NotNull @NotBlank
    @Email(message = "Email not valid")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String address;
    private int phone;
    private boolean vaccinate;
    private Vaccine vaccine;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date vaccinateDate;
    private int dose;

}
