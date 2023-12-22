package com.tiny.world.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class User {

	private String name;

	private Integer age;

	private String email;

}
