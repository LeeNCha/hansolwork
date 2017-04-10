package webcrawl;

import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class beautifulsoup {
	public static DB db=new DB();

	public static void WebtoonCrawl(String url, String day) throws Exception{

	  
	  String webpage = SimpleWeb.getWeb(url); //웹페이지 전체 코드 가져오기
      Document doc = Jsoup.parse(webpage);      
      String sql = "insert into LIBINFOSYS.CRAWL_P (crawl_name,crawl_days,crawl_author) values (?, ?, ?)";
      PreparedStatement stmt = db.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

      
      
      
      Elements rows = doc.select(".daily_img li"); //제목 태그
      for (Element row : rows) {
          stmt.setString(1,row.select("dt a").attr("title")); //제목 <a>태그 안에 있는 글은 줄임말이라서 attribute으로 가져옴
	     
	      
	      stmt.setString(2,day);//요일
	      
	      
	      /*만화가*/
    	  Iterator<Element> iterElem = row.getElementsByTag("dd").iterator();
	      StringBuilder builder = new StringBuilder();
	      String aut = builder.append(iterElem.next().text()).toString();
          stmt.setString(3,aut); //
	      
          //System.out.println(stmt);
	      stmt.execute();
      }
	}
	
	/*Book Crawler*/
//	public static void BookCrawl(String url) throws Exception{
//
//		  String webpage = SimpleWeb.getWeb(url); //웹페이지 전체 코드 가져오기
//	      Document doc = Jsoup.parse(webpage);      
//	      String sql = "insert into LIBINFOSYS.BOOK (book_ISBN,book_title,book_author,book_com) values (?, ?, ?, ?)";
//	      PreparedStatement stmt = db.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
//
//	      
//	      
//	      
//	      Elements rows = doc.select(".book_info"); //책 정보 있는 부분
//	      for (Element row : rows) {
//	    	  /*책제목*/
//	    	  Iterator<Element> iterElem = row.getElementsByTag("h2").iterator();
//		      StringBuilder builder = new StringBuilder();
//		      String tit = builder.append(iterElem.next().text()).toString();
//	          stmt.setString(1,tit); //
//		     
//		      
//		      stmt.setString(2,day);//요일
//		      
//		      
//		      /*만화가*/
//	    	  Iterator<Element> iterElem = row.getElementsByTag("dd").iterator();
//		      StringBuilder builder = new StringBuilder();
//		      String aut = builder.append(iterElem.next().text()).toString();
//	          stmt.setString(3,aut); //
//		      
//	          //System.out.println(stmt);
//		      stmt.execute();
//	      }
//	}
}
