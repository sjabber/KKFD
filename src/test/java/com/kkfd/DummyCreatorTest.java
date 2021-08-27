package com.kkfd;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DummyCreatorTest {
	public static void main(String[] args) { 
		int max = 100;
		
		for (int i = 0; i < max; i++) { 
			System.out.println(nNo3());
		}
		
		
		/*
		// 아이디 
		for (int i = 0; i < max; i++) { 
			System.out.println(nId()+nNo2());
		}
		//이메일
		for (int i = 0; i < max; i++) { 
			System.out.println(nId()+nNo2()+nEmail());
		}
		
		
		// 이름 
		for (int i = 0; i < max; i++) { 
			System.out.println(nName()); 
		} 
		// 닉네임 
		for (int i = 0; i < max; i++) { 
			System.out.println(nNick()+" "); 
		}
		
		//전화번호
		for (int i = 0; i < max; i++) { 
			System.out.println("010-"+nNo()+"-"+ nNo());
		}
		//출처: https://taetae0079.tistory.com/22 [TH블로그]
		 */
	}


	public static String nNick() { 
		List<String> nick = Arrays.asList("기분나쁜","기분좋은","신바람나는","상쾌한","짜릿한","그리운","자유로운","서운한","울적한","비참한","위축되는","긴장되는","두려운","당당한","배부른","수줍은","창피한","멋있는", "열받은","심심한","잘생긴","이쁜","시끄러운"); 
		List<String> name = Arrays.asList("사자","코끼리","호랑이","곰","여우","늑대","너구리","침팬치","고릴라","참새","고슴도치","강아지","고양이","거북이","토끼","앵무새","하이에나","돼지","하마","원숭이","물소","얼룩말","치타", "악어","기린","수달","염소","다람쥐","판다");
		Collections.shuffle(nick); 
		Collections.shuffle(name); 
		String text = nick.get(0)+name.get(0); 
		return text; 
	} 

	public static String nName() { 
		List<String> last= Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안", "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주", "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양", "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금", "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용"); 
		List<String> name = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다", "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박", "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순", "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위", "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준", "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형", "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비", "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼", "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠", "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련"); 
		Collections.shuffle(last); 
		Collections.shuffle(name); 
		return last.get(0) + name.get(0) + name.get(1); 
	}
	public static String nEmail() { 
		List<String> email= Arrays.asList("@naver.com", "@gmail.com", "@hanmail.com", "@hotmail.com", "@nate.com", "@daum.net"); 
		Collections.shuffle(email); 
		return email.get(0); 
	}  
	
    

	public static String nNo() { 
		return (int)(Math.random() * 8999)+1000 +""; 
	}
	public static String nNo2() { 
		return (int)(Math.random() * 99)+1 +""; 
	} 
	
	public static String nNo3() { 
		return (int)(Math.random() * 10)+1 +""; 
	} 

	public static String nId() { 
		String text = ""; 
		String ran = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; 
		for(int i = 0; i < 6; i++) { 
			text += ran.charAt((int)(Math.random() * ran.length())); 
		} 
		return text; 
	}
}


