package com.sirma.staffprojectmanager.model.dto;

import org.hibernate.sql.ast.tree.expression.Over;

public class OverlapProjectsDto implements Comparable<OverlapProjectsDto> {
	private Long emp1;
	private Long emp2;
	private Long projectId;
  private Integer overlapDays;

	public OverlapProjectsDto()  {
	}

	public OverlapProjectsDto(Long emp1, Long emp2, Long projectId, Integer overlapDays) {
		this.emp1 = emp1;
		this.emp2 = emp2;
		this.projectId = projectId;
		this.overlapDays = overlapDays;
	}


	public Long getEmp1() {
		return emp1;
	}

	public void setEmp1(Long emp1) {
		this.emp1 = emp1;
	}

	public Long getEmp2() {
		return emp2;
	}

	public void setEmp2(Long emp2) {
		this.emp2 = emp2;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Integer getOverlapDays() {
		return overlapDays;
	}

	public void setOverlapDays(Integer overlapDays) {
		this.overlapDays = overlapDays;
	}


	@Override
	public String toString() {
		return "emp1=" + emp1 +
		       ", emp2=" + emp2 +
		       ", overlapDays=" + overlapDays;
	}

	@Override
	public int hashCode() {
		return emp1.hashCode()+emp2.hashCode();
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		OverlapProjectsDto other = (OverlapProjectsDto) obj;

		return emp1.equals(other.emp1) && emp2.equals(other.emp2);
	}

	@Override
	public int compareTo(OverlapProjectsDto dto) {
		return this.emp1.compareTo(dto.emp1) + this.emp2.compareTo(dto.emp2);
	}
}
