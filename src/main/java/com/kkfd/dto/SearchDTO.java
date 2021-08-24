package com.kkfd.dto;

public class SearchDTO {
	private String word;	//
	private int category;	// 0:전체 1:캔들 2:조향 3:비누 4:도예 5:섬유,자수
	private int state;		//0:전체	1:진행예정 2:진행중 3:마감(성공+실패) 
	private int goal;		//0:전체	1:75%이하 2:75%~100% 3:100%이상
	private int standard;	//0:인기순 1:최신순 2:마감임박순
	private int page;		//
	private String id;		//북마크여부확인 세션에서 id담아서 mybatis로
	private static final int CNT_PER_PAGE = 18; //3*6

	public SearchDTO() {}

	public SearchDTO(String word, int category, int state, int goal, int standard,int page, String id) {
		super();
		this.word = word;
		this.category = category;
		this.state = state;
		this.goal = goal;
		this.standard = standard;
		this.id = id;
		this.page = page;
	}
	
	public SearchDTO(String word, int category, int state, int goal, int standard, int page) {
		super();
		this.word = word;
		this.category = category;
		this.state = state;
		this.goal = goal;
		this.standard = standard;
		this.page = page;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getGoal() {
		return goal;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	public int getStandard() {
		return standard;
	}
	public void setStandard(int standard) {
		this.standard = standard;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public static int getCntPerPage() {
		return CNT_PER_PAGE;
	}
	@Override
	public String toString() {
		return "SearchDTO [id=" + id + ", word=" + word + ", category=" + category + ", state=" + state + ", goal="
				+ goal + ", standard=" + standard + ", page=" + page + "]";
	}
	


}

