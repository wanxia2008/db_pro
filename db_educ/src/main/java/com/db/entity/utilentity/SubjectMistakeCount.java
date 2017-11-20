package com.db.entity.utilentity;

public class SubjectMistakeCount {
	private Integer projectId;
	private String projectName;
	private Integer misCount;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getMisCount() {
		return misCount;
	}

	public void setMisCount(Integer misCount) {
		this.misCount = misCount;
	}

	@Override
	public String toString() {
		return "SubjectMistakeCount [projectId=" + projectId + ", projectName=" + projectName + ", misCount=" + misCount
				+ "]";
	}

}
