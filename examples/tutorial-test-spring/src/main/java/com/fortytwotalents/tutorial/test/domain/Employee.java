package com.fortytwotalents.tutorial.test.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

}