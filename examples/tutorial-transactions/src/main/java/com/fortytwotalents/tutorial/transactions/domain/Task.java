package com.fortytwotalents.tutorial.transactions.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Task {

	@NonNull
	private Long id;
	@NonNull
	private String name;
	@NonNull
	private String Description;

}