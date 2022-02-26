package com.cg.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseDTO {

    @NotBlank(message = "Vui lòng nhập tên khoa")
    private String courseName;

    @NotNull(message = "Vui lòng nhập chủ nhiệm khoa ")
    private Long instructorId;

    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseName='" + courseName + '\'' +
                ", instructorId=" + instructorId +
                '}';
    }
}
