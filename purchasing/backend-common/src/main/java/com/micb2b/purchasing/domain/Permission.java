package com.micb2b.purchasing.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jay
 * @date 2019-11-19
 */
@Entity
@Getter
@Setter
@Table(name = "permission")
public class Permission implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(groups = {Update.class})
	private Long id;

	@NotBlank
	private String name;

	/**
	 * 上级类目
	 */
	@NotNull
	@Column(name = "pid",nullable = false)
	private Long pid;

	@NotBlank
	private String alias;

	@JsonIgnore
	@ManyToMany(mappedBy = "permissions")
	private Set<Role> roles;

	@CreationTimestamp
	@Column(name = "create_time")
	private Timestamp createTime;

	@Override
	public String toString() {
		return "Permission{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pid=" + pid +
				", alias='" + alias + '\'' +
				", createTime=" + createTime +
				'}';
	}

	public interface Update{}
}
