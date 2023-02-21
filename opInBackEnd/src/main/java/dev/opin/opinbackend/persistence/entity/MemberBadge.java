package dev.opin.opinbackend.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Entity
@Getter
public class MemberBadge {
	@Id
	@Column(name = "MEMBER_BADGE_ID")
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Badge badge;
}
