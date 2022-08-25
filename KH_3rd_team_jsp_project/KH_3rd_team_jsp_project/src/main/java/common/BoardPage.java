package common;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage,
			int pageNum, String reqUrl) {
		String pagingStr="";
		
		//단계 3 : 전체 페이지 수 계산
		//ceil 무조건 올린다.
		int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));
		
		//단계 4 : '이전 페이지 블록 바로가기' 출력
		int pageTemp = (((pageNum -1)/blockPage) * blockPage) +1;
		if(pageTemp !=1) {
			pagingStr += "<a href='" + reqUrl + "?pageNum=1'> [첫 페이지] </a>";
			pagingStr += "&nbsp";
			pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp -1) +"'> [이전 블록] </a>";
		
		}
		
		//단계 5 : 각 페이지 번호 출력
		int blockCount=1;
		while(blockCount <= blockPage && pageTemp <= totalPages) {
			if(pageTemp == pageNum) {
				//현재 페이지는 링크를 걸지 않음
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			}else {
				//현재 페이지가 아닌 다른 페이지들은
				//만약 그 페이지의 숫자 즉 번호를 눌렀을 때 
				//해당 페이지의 reqUrl?pageNum=3 이런식으로 그 페이지로 넘어가게 된다.
				//1페이지엔 1~10, 2 페이지엔 11~20, 3페이지엔 21~30 이런식으로 게시물이 있겠지?
				pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" +pageTemp + "'>" +pageTemp+"</a>&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}
		
		//단계 6 : '다음 페이지 블록 바로가기' 출력
		if(pageTemp <= totalPages) {
			pagingStr += "<a href='" + reqUrl + "?pageNum=" +pageTemp + "'> [다음블록] </a>";
			pagingStr +="&nbsp;";
			pagingStr += "<a href='" + reqUrl + "?pageNum=" +totalPages + "'> [마지막 페이지] </a>";
		}
		return pagingStr;
	}
}
