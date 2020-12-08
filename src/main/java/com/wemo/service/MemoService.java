package com.wemo.service;

import java.util.List;
import java.util.Map;

import com.wemo.domain.Member;
import com.wemo.domain.Memo;

public interface MemoService {

	public boolean memoForNewAccount(String USER_EMAIL);
	public List<Memo> getMemoList(Memo memo);

	public List<Memo> getFavMemoList(Memo memo);
	public List<Memo> getTraMemoList(Memo memo);

	public boolean newMemo(Memo memo);

	public Memo getLatestMemoInfo(Memo memo);

	public Memo getMemoContent(Memo memoObj);
	public boolean adjustMemoboxzindex(Memo memo);

	public int getCountMemolist(Memo memo);

	public Map<String, Object> getCountSectionlist(String USER_EMAIL);
	public boolean updateMemoLockAndUnlock(Memo memo);
	public boolean updateMemoFavorite(Memo memo);
	public boolean updateMemoColor(Memo memoObj);
	public boolean moveToTrashBackAndForth(Memo memo);
	public boolean deleteMemo(Memo memo);

	public List<Memo> searchMemoList(Memo memo);

	public boolean saveMemoProperties(Memo memoObj);
	public boolean saveListedMemoProperties(Memo memoObj);
	
	
	
	
	
}
