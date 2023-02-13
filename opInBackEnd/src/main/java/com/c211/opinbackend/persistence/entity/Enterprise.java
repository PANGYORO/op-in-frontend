package com.c211.opinbackend.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "Enterprise")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise {
	@Id
	@GeneratedValue
	@Column(name = "ENTERPRISE_ID")
	private Long id;

	@NotNull
	private String title;
}
