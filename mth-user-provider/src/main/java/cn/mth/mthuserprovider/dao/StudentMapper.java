package cn.mth.mthuserprovider.dao;

import cn.mth.mthuserprovider.pojo.Student;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface StudentMapper {
    @Delete({
        "delete from student",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into student (Id, Name, ",
        "Age, Sex, Score)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{age,jdbcType=INTEGER}, #{sex,jdbcType=VARCHAR}, #{score,jdbcType=INTEGER})"
    })
    int insert(Student record);

    @Select({
        "select",
        "Id, Name, Age, Sex, Score",
        "from student",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="Name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="Age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="Sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="Score", property="score", jdbcType=JdbcType.INTEGER)
    })
    Student selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "Id, Name, Age, Sex, Score",
        "from student"
    })
    @Results({
        @Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="Name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="Age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="Sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="Score", property="score", jdbcType=JdbcType.INTEGER)
    })
    List<Student> selectAll();

    @Update({
        "update student",
        "set Name = #{name,jdbcType=VARCHAR},",
          "Age = #{age,jdbcType=INTEGER},",
          "Sex = #{sex,jdbcType=VARCHAR},",
          "Score = #{score,jdbcType=INTEGER}",
        "where Id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Student record);
}