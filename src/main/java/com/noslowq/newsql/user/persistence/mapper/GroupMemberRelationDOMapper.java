package com.noslowq.newsql.user.persistence.mapper;

import com.noslowq.newsql.user.persistence.ddl.GroupMemberRelationDO;
import com.noslowq.newsql.user.persistence.ddl.GroupMemberRelationDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GroupMemberRelationDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_member_relation
     *
     * @mbg.generated
     */
    long countByExample(GroupMemberRelationDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_member_relation
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_member_relation
     *
     * @mbg.generated
     */
    int insert(GroupMemberRelationDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_member_relation
     *
     * @mbg.generated
     */
    int insertSelective(GroupMemberRelationDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_member_relation
     *
     * @mbg.generated
     */
    List<GroupMemberRelationDO> selectByExampleWithRowbounds(GroupMemberRelationDOExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_member_relation
     *
     * @mbg.generated
     */
    List<GroupMemberRelationDO> selectByExample(GroupMemberRelationDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_member_relation
     *
     * @mbg.generated
     */
    GroupMemberRelationDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_member_relation
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") GroupMemberRelationDO record, @Param("example") GroupMemberRelationDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_member_relation
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") GroupMemberRelationDO record, @Param("example") GroupMemberRelationDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_member_relation
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GroupMemberRelationDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_member_relation
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GroupMemberRelationDO record);
}