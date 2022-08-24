package com.kh.petlab.adminnotice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.kh.petlab.adminnotice.model.dto.AdminNoticeAttachment;
import com.kh.petlab.adminnotice.model.dto.AdminNotice;

@Mapper
public interface AdminNoticeDao {

	List<AdminNotice> selectAdminNoticeList(RowBounds rowBounds);

	@Select("select count(*) from qa_notice")
	int selectTotalContent();

	int insertAdminNotice(AdminNotice adminNotice);

	@Insert("insert into attachment (no, notice_no, original_filename, renamed_filename) "
		  + "values (seq_attachment_no.nextval, #{noticeNo}, #{originalFilename}, #{renamedFilename})")
	int insertAttachment(AdminNoticeAttachment attach);

	@Select("select * from qa_notice where no = #{notice_no}")
	AdminNotice selectOneAdminNotice(int no);

	@Select("select * from attachment where notice_no = #{VNo}")
	List<AdminNoticeAttachment> selectAttchmentListBynoticeNo(int noticeNo);
	
	AdminNotice selectOneAdminNoticeCollection(int no);

	@Select("select * from attachment where no = #{attachNo}")
	AdminNoticeAttachment selectOneAttachment(int attachNo);

	@Delete("delete from attachment where no = #{attachNo}")
	int deleteAttachment(int attachNo);

	@Update("update qa_notice set title = #{notice_title}, content = #{content}, updated_at = sysdate where no = #{notice_no}")
	int updateAdminNotice(AdminNotice AdminNotice);

}