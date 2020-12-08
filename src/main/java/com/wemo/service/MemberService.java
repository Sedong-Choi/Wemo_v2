package com.wemo.service;

import com.wemo.domain.Member;

public interface MemberService {
	
	public boolean idcheck(String USER_EMAIL);

	public boolean isId(String USER_EMAIL, String USER_PASS);

	public boolean insertMember(Member member);

	public Member getUserAutoForm(Member member);

	public boolean updateLastSection(Member member);
	public Member getMemberDetail(String id);
	public boolean saveUserSetting(Member member);
	

	public boolean kakaoJoin(String kemail);
	public boolean naverJoin(String nemail);	

}
