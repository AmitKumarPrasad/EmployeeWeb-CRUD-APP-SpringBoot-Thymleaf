package com.amit.dev.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@JsonProperty("id")
	private long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
	@JsonProperty("country")
	private String country;
	@JsonProperty("website")
	private String website;

}
