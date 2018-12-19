package cn.mth.mthuserprovider.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 学生实体类
 */
@ApiModel(value = "Student",description = "学生实体类")
public class Student {
    @ApiModelProperty(value = "学生编号",dataType = "Integer")
    private Integer id;
    @ApiModelProperty(value = "学生姓名",dataType = "String")
    private String name;
    @ApiModelProperty(value = "学生年龄",dataType = "Integer")
    private Integer age;
    @ApiModelProperty(value = "学生性别",dataType = "String")
    private String sex;
    @ApiModelProperty(value = "学生分数",dataType = "Integer")
    private Integer score;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}