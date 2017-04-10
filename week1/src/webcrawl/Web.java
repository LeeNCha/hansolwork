package webcrawl;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 
 
public class Web {
 
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        //String url = "https://ko.wikipedia.org/wiki/%EC%9E%90%EB%B0%94_(%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D_%EC%96%B8%EC%96%B4)";
        //ArrayList<String> list = getSource(url);
    //  for(String s : list)
        //  System.out.println(s);
 
        //디렉토리를 미리 만들어놓아야
        String saveDir ="/Users/HANSOL/Documents/WebCrawlerDownload/";
        //아무 링크
        String downloadUrl = "http://www.naver.com";
        String typeRgx = "(jpg)";
 
        Web.getTypedFileDown(downloadUrl, saveDir, typeRgx);
 
    }
 
    public static String getSource(String url) throws MalformedURLException, IOException {
    	//  ArrayList<String> output = new ArrayList<>();
        String output="";
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream(),"utf-8"));
        String line;
        while ((line = br.readLine()) != null) {
            //output.add(line);
            output += line;
        }
        return output;
    }
 
    public static ArrayList<String> getTypedFile(String text, String typeRegex) {
        String regex = "\"(http://|https://)[^<>\"]+[.]" + typeRegex + "\"";
        /*
        1. "(인용부호)로 시작하고, http://나 "https://가 그 다음에 나온다.
		2. 문자열 중간에 < 이나 > 또는 "(인용부호)가 들어가지 않는다.
		3. 그리고 .typeRegex가 붙은 뒤 "로 끝난다.
         */
        
        Matcher m = Pattern.compile(regex).matcher(text);
        ArrayList<String> output = new ArrayList<>();
        while (m.find()) {
            output.add(m.group().replace("\"", ""));
        }
        return output;
    }
 
    public static void FileDownload(String address, String saveDir){
        try{
            URL u = new URL(address);
            FileOutputStream fos = new FileOutputStream(saveDir);
            InputStream is = u.openStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf)) > 0) {
                fos.write(buf, 0, len);
            }
            fos.close();
            is.close();}catch(IOException e){
                e.printStackTrace();
                System.exit(0);
            }
    }
 
    public static String getWebFileName(String filePath) {
        String[] parts = filePath.split("[/]");
        return parts[parts.length - 1];
    }
 
    public static void getTypedFileDown(String url, String saveDir, String typeRegex) throws IOException {
        String source = getSource(url); //source 가져오기
        ArrayList<String> urls = getTypedFile(source, typeRegex); //내가 원하는 파일의 주소값 찾기
        System.out.println("다운로드를 시작합니다.");
        int size = urls.size();
        int k = 1;
        for (String i : urls) {
            System.out.println();
            System.out.println("//--------------------------------------");
            System.out.println("//   다운로드 중... : " + i);
            String save = saveDir + "\\" + getWebFileName(i);
            System.out.println("//    다운로드 위치 : " + save);
            FileDownload(i, save); //파일 다운로드
            System.out.println("//   다운로드 진행률 : " + (k * 100 / size) + "%");
            System.out.println("//--------------------------------------");
            System.out.println();
            k++;
        }
        System.out.println("총 " + urls.size() + "개의 파일을 다운로드하였습니다.");
    }
 
}
 