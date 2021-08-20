package com.kkfd.dto;

public class SearchDTO {//프로젝트 목록 조회 요청시 컨트롤러에서 @RequestBody searchDTO search을 위한 DTO(MVC가 아니므로 request만)
	private static final int CNT_PER_PAGE = 18; //3*6
	private String word;	//input name="word"
	private int category;	//selection name="category" 0:전체 1:캔들 2:조향 3:비누 4:도예 5:섬유,자수
	private int state;		//0:전체	1:진행예정 2:진행중 3:마감(성공+실패) 
	private int goal;		//0:전체	1:75%이하 2:75%~100% 3:100%이상
	private int standard;	//0:인기순 1:최신순 2:마감임박순
	private int currentPage;//input name="currentPage" type="hidden"
	/* [무한스크롤] 스크롤위치가 최하단에 위치할때 마다 ++page -> 현재페이지값으로 요청
	 	var page = 1;
		$(window).scroll(function() {
	    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
	      ++page;	
	      $(input[name=currentPage]).val(page);
	       폼데이터 ajax 요청 
	       divHtml+=<div>~~~
	      $("#section").append(divHtml);
		    }
		});
	*/
	public SearchDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SearchDTO [word=" + word + ", category=" + category + ", state=" + state + ", goal=" + goal
				+ ", standard=" + standard + ", currentPage=" + currentPage + "]";
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
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public static int getCntPerPage() {
		return CNT_PER_PAGE;
	}
	public SearchDTO(String word, int category, int state, int goal, int standard, int currentPage) {
		super();
		this.word = word;
		this.category = category;
		this.state = state;
		this.goal = goal;
		this.standard = standard;
		this.currentPage = currentPage;
	}
}
