package com.wemo.service;

import com.wemo.domain.Member;

public interface MemberService {
	
	public boolean idcheck(String USER_EMAIL);
	// ȸ������ �� �� ���̵� �ߺ� �˻�
	public boolean isId(String USER_EMAIL, String USER_PASS);
	// �α��� �� �� ID/PW �˻�
	public boolean insertMember(Member member);
	// ȸ������ ���ν� insert
	public Member getUserAutoForm(Member member);
	// ���� ���� �ڵ��ϼ� �� �������� ���
	public boolean updateLastSection(Member member);
	public Member getMemberDetail(String id);
	public boolean saveUserSetting(Member member);
	
	/* SNS �α��ο� �޼��� */
	public boolean kakaoJoin(String kemail);
	public boolean naverJoin(String nemail);	

}
