package com.example.Assignment2.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class Admin {
    // ObjectId field serialized using ToStringSerializer to convert to String in JSON

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String name;
    private String password;
    private String email;

    // Custom toString method to represent Admin object as a string

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
