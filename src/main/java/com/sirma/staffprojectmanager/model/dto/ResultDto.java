package com.sirma.staffprojectmanager.model.dto;

public class ResultDto {
	private Long emp1;
	private Long emp2;
	private Long projectId;
  private Integer overlapDays;

	public ResultDto() {
	}

	public ResultDto(Long emp1, Long emp2, Long projectId, Integer overlapDays) {
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
		return "ResultDto{" +
		       "emp1=" + emp1 +
		       ", emp2=" + emp2 +
		       ", projectId=" + projectId +
		       ", overlapDays=" + overlapDays +
		       '}';
	}
}
