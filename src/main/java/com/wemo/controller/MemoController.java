package com.wemo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.wemo.domain.Member;
import com.wemo.domain.Memo;
import com.wemo.service.MemberService;
import com.wemo.service.MemoService;

@Controller
public class MemoController {
	
	@Autowired
	private MemoService mService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/Memolist", method = RequestMethod.GET)
	public ModelAndView getWeMoList(@RequestParam(value = "MEMO_SUB", defaultValue = "STUDY", required = false) String MEMO_SUB, 
									ModelAndView mv, HttpSession session){
		String id = (String) session.getAttribute("USER_EMAIL");
		
		mv.addObject("USER_EMAIL", id);
		mv.addObject("MEMO_SUB", MEMO_SUB);
		mv.setViewName("WeMo_Main");
		return mv;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "getSectionMemoList", method =RequestMethod.POST)
	public void getSectionMemoList(Memo memoObj, HttpServletResponse resp) {
		PrintWriter out = null;
		try {
			String MEMO_SUB = memoObj.getMEMO_SUB();
			List<Memo> mList = null;
			if(MEMO_SUB.equals("IMPORTANT"))
				mList = mService.getFavMemoList(memoObj);
			else if(MEMO_SUB.equals("TRASH"))
				mList = mService.getTraMemoList(memoObj);
			else
				mList = mService.getMemoList(memoObj);
			
			resp.setCharacterEncoding("UTF-8");
			out = resp.getWriter();
			if (mList.size() >0) {
				String jsonMemolist = new Gson().toJson(mList);
				out.println(jsonMemolist);
			} else {
				out.println("false");
			}
				
		} catch(Exception e) { 
			e.getStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/newMemo", method = RequestMethod.POST)
	public String newMemo(Memo memoObj, HttpServletResponse resp) {
		
		PrintWriter out = null;
		try {	
			if(mService.newMemo(memoObj)) {
				// newMemo�� return���� boolean
				memoObj = mService.getLatestMemoInfo(memoObj);
					String jsonNewMemo = new Gson().toJson(memoObj);
					int latestMEMO_NUM = memoObj.getMEMO_NUM();
					// �� �������� �߰��� �޸��� ������ �������� �� �������� �޸� ��ȣ�� ����
					int countMemolist = mService.getCountMemolist(memoObj);
					System.out.println("�޸� ����Ʈ ���� : " + countMemolist);
					// �޸� ����Ʈ ������ ���ؼ� 1���� ũ�ٸ� z-index�� ó���ϱ�
					if(countMemolist > 1) {
						memoObj.setMEMO_NUM(latestMEMO_NUM);
						if(mService.adjustMemoboxzindex(memoObj)) {
							// �ش� �޸��ȣ�� ������ �������� z-index���� -1ó��	
							System.out.println("�ش� �޸��ȣ�� ������ �������� z-index���� -1ó��");
							return jsonNewMemo;
						} else {
							out = resp.getWriter();
							out.println("<script>alert('�� �޸� �߰��ϴ� ���� ������ �߻��߽��ϴ�. �ٽ� �õ����ּ���.')</script>");
							return null;
						}
					} else {
						// countMemolist = 1�̶�� ��� �߰��� �޸�ۿ� �����Ƿ� ó���� �ʿ� ���� out�ݰ� memo�� ����
						return jsonNewMemo;
					}
					
			} else {
				out = resp.getWriter();
				out.println("<script>alert('�� �޸� �߰��ϴµ� �����Ͽ����ϴ�. �ٽ� �õ����ּ���.');</script>");
				return null;
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (out != null)
				out.close();
		}
		
		return null;

	}
	
	@ResponseBody
	@RequestMapping(value = "/sectionChange", method = RequestMethod.POST)
	public void sectionChange(Memo memoObj, Member member, HttpServletResponse resp) throws IOException{
		System.out.println("USER_EMAIL:" + memoObj.getUSER_EMAIL() +" Section :" + memoObj.getMEMO_SUB());
		PrintWriter out = null;
		try {
			// request�� �޾ƿ� ������ member��ü�� memo��ü�� ���� ����
			member.setUSER_EMAIL(memoObj.getUSER_EMAIL());
			member.setUSER_SUB(memoObj.getMEMO_SUB());
			String Section = memoObj.getMEMO_SUB();
			// member ���̺��� USER_SUB�� ������Ʈ �ϰ� ������Ʈ�� �����ϸ� �ش� ������ �޸� ����Ʈ�� �޾ƿ�
		if(memberService.updateLastSection(member)) {
			List<Memo> mList = null;
			if(Section.equals("IMPORTANT")) {
				memoObj.setMEMO_SUB("MEMO_FAV");
				mList = mService.getFavMemoList(memoObj);
			} else if (Section.equals("TRASH")) {
				memoObj.setMEMO_SUB("MEMO_TRA");
				mList = mService.getTraMemoList(memoObj);
			} else
				mList = mService.getMemoList(memoObj);
			
			resp.setCharacterEncoding("UTF-8");
			out = resp.getWriter();
			
			if(mList != null){	
				out.write(new Gson().toJson(mList));
				System.out.println("������ ����");
			} else {			
				out.println("<script>alert('������ ���濡 ������ �߻��Ͽ����ϴ�. ��� �Ŀ� �ٽ� �õ��� �ּ���.')</script>");
			}
			
		} else {
			System.out.println("memberService.updateLastSection()���� ������ �߻��Ͽ����ϴ�");
			out = resp.getWriter();
			out.println("<script>alert('������ ���� ���߿� ������ �߻��Ͽ����ϴ�. ��� �Ŀ� �ٽ� �õ��� �ּ���.')</script>");
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/sectionAnalysis", method = RequestMethod.POST)
	public void sectionAnalysis(Memo memoObj, HttpServletResponse resp) {
		System.out.println("url = /sectionAnalysis, USER_EMAIL = "+memoObj.getUSER_EMAIL());
		PrintWriter out = null;
		try {
			resp.setCharacterEncoding("UTF-8");
			out = resp.getWriter();
			Map<String, Object> cntMap = null;
			if (memoObj.getMEMO_SUB().equals("ANALYSIS")) {
				cntMap = mService.getCountSectionlist(memoObj.getUSER_EMAIL());
				if (cntMap != null) {
					String jsonSecCnt = new Gson().toJson(cntMap);
					out.println(jsonSecCnt);
				}
			} 
		} catch (Exception e) { 
			e.getStackTrace();
		} finally {
			if(out != null)
			   out.close();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/moveToTrashBackAndForth", method = RequestMethod.POST)
	public void moveToTrash(Memo memoObj, HttpServletResponse resp) {
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			
			if(mService.moveToTrashBackAndForth(memoObj)) {
				resp.setCharacterEncoding("UTF-8");
				out.println("true");
			} else {
				resp.setCharacterEncoding("UTF-8");
				out.println("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out!=null)
				out.close();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteMemo", method = RequestMethod.POST)
	public void deleteMemo(Memo memoObj, HttpServletResponse resp) {
		PrintWriter out = null;
		try {
			resp.setCharacterEncoding("UTF-8");
			out = resp.getWriter();
			
			if(mService.deleteMemo(memoObj)) {				
				out.println("true");
			} else {
				out.println("false");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveMemoProperties", method = RequestMethod.POST)
	public void saveMemoProperties(Memo memoObj, HttpServletResponse resp) throws IOException {
		PrintWriter out = null;
		try {
		out = resp.getWriter();
		if(mService.saveMemoProperties(memoObj))
			out.print("true");
		else
			out.print("false");
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/saveListedMemoProperties", method = RequestMethod.POST)
	public void saveListedMemoProperties(Memo memoObj, HttpServletResponse resp) throws IOException {
		PrintWriter out = null;
		try {
		out = resp.getWriter();
		if(mService.saveListedMemoProperties(memoObj))
			out.print("true");
		else
			out.print("false");
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/setMemoColor", method = RequestMethod.POST)
	public void setMemoColor(Memo memoObj, HttpServletResponse resp) {
		PrintWriter out = null;
		System.out.println("USER_EMAIL:"+memoObj.getUSER_EMAIL()+" MEMO_NUM:"+memoObj.getMEMO_NUM()+" MEMO_COLOR:"+memoObj.getMEMO_COLOR());
		try {
			resp.setCharacterEncoding("UTF-8");
			out = resp.getWriter();
			if(mService.updateMemoColor(memoObj))
				out.print("true");
			else 
				out.println("false");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/setMemoLockAndUnlock", method = RequestMethod.POST)
	public void setMemoLockAndUnlock(Memo memoObj, HttpServletResponse resp) {
		PrintWriter out = null;
		try {
			resp.setCharacterEncoding("UTF-8");
			out = resp.getWriter();			
			if(memoObj.getMEMO_LOC().equals("Y")) {
				if (mService.updateMemoLockAndUnlock(memoObj))
					out.print("true");
			} else {
				String id = memoObj.getUSER_EMAIL();
				String pass = memoObj.getMEMO_KEYW();
				if(memberService.isId(id, pass)) {
					memoObj.setMEMO_KEYW("��� Ű���� �ʱ�ȭ");
					if(mService.updateMemoLockAndUnlock(memoObj)) {
						String memoContent = mService.getMemoContent(memoObj).getMEMO_TEX();
						String jsonContent = new Gson().toJson(memoContent);
						System.out.println(jsonContent.length());
						out.write(jsonContent);
					} else
						out.print("�Ͻ����� ������ �޸� ������ �ҷ����� ���߽��ϴ�");
				} else {
					out.print("��й�ȣ�� Ʋ���ϴ�");
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if(out != null)
			   out.close();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/setMemoFavorite", method = RequestMethod.POST)
	public void setMemoFavorite(Memo memoObj, HttpServletResponse resp) {
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			if (mService.updateMemoFavorite(memoObj))
				out.print("true");
			else
				out.print("false");
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if(out != null)
			   out.close();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchMemo", method = RequestMethod.POST)
	public void searchMemo(Memo memoObj, HttpServletResponse resp) {
		PrintWriter out = null;
		try {
			List<Memo> mList = mService.searchMemoList(memoObj);
			if (mList.size() > 0) {
				resp.setCharacterEncoding("UTF-8");
				out = resp.getWriter();
				out.println(new Gson().toJson(mList));
			} else {
				resp.setCharacterEncoding("UTF-8");
				out = resp.getWriter();
				out.println("No Result");
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
		
	}
	
	
}
