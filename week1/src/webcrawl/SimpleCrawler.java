package webcrawl;

//import java.net.*;
//import java.io.*;
import java.sql.*;

public class SimpleCrawler{
	public static DB db=new DB();
	
    public static void main(String[] args) throws SQLException, Exception{
	    db.runSql2("TRUNCATE LIBINFOSYS.CRAWL_P;");
    	System.out.println("Start");
    	
    	
	    String url = "http://comic.naver.com/webtoon/weekdayList.nhn?week=";
	    String[] days={"mon","tue","wed","thu","fri","sat","sun"};
	    
	    for(int a=0;a<7;a++){
	    	beautifulsoup.WebtoonCrawl(url+days[a],days[a]);
	    }
	    
	    System.out.println("done");
	    
//    	int dataNum=10;
//    	db.runSql2("TRUNCATE LIBINFOSYS.book;");
//    	System.out.println("Start");
//    	
//    	
//	    String url = "http://book.naver.com/bookdb/book_detail.nhn?bid=";
//	    
//	    for(int a=1;a<=dataNum;a++){
//	    	beautifulsoup.BookCrawl(url+a);
//	    }
	    
	    
	    
    }
    
    public static void crawling(String url,String day)throws Exception{

	    int i=0,j=0,total=0, MAX = 1000;
	    int start=0, end=0,dt=0;
    	String tits[] = new String[1000];
    	String auts[] = new String[1000];
    	
	    String webpage = SimpleWeb.getWeb(url); //웹페이지 전체 코드 가져오기
	    
	    /* 참고
	     * http://buglab.tistory.com/74 
	     * */
	    
	    
	    
	    end = webpage.indexOf("daily_img");//indexOf("s") :s의 주소 숫자 출력. daily_img의 위치를 출력한다고 생각하면 됨/
	    for(i=total;i<MAX; i++, total++){
	    	//1000번 돌린다
	    	
	    	
	    	//title 찾기
	    	dt=webpage.indexOf("<dt>",end);//end 이후의 첫 s의 주소 숫자
	        start = webpage.indexOf("title=\"", dt);
	        
	        if(dt==-1 || start == -1){
	        	dt=0;
	            start = 0;
	            end = 0;
	            try{
//	                webpage = SimpleWeb.getWeb(tits[j++]);
	            	break;
	            }catch(Exception e){
	                System.out.println("******************");
	                System.out.println(tits[j-1]);
	                System.out.println("Exception caught \n"+e);
	            }
	
	            /* logic to fetch urls out of body of webpage only */
	            break;      
	        }
	        end = webpage.indexOf("\">", start);

	        String tit = webpage.substring(start,end).replace("title=\"", "");
	        tits[i] = tit;
	        
	        
	        
	        //author 찾기
	        dt=webpage.indexOf("desc",end);//end 이후의 첫 s의 주소 숫자
	        start = webpage.indexOf(")\">", dt);
	        
	        if(dt==-1 || start == -1){
	        	dt=0;
	            start = 0;
	            end = 0;
	            try{
//	                webpage = SimpleWeb.getWeb(auts[j++]);
	            	break;
	            }catch(Exception e){
	                System.out.println("******************");
	                System.out.println(auts[j-1]);
	                System.out.println("Exception caught \n"+e);
	            }
	
	            /* logic to fetch urls out of body of webpage only */
	            break;      
	        }
	        end = webpage.indexOf("</a>", start);

	        String aut = webpage.substring(start,end).replace(")\">", "");
	        auts[i] = aut;
	        
	        
	        
	        String sql = "insert into LIBINFOSYS.CRAWL_P (crawl_name,crawl_days,crawl_author) values (?, ?, ?)";
	        PreparedStatement stmt = db.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	        stmt.setString(1,tits[i]);
	        stmt.setString(2,day);
	        stmt.setString(3,auts[i]);
//	        System.out.println(stmt);
	        stmt.execute();
	        
	    }
        System.out.println("Total Webtoons Fetched are " + total);   
	    
    }
}

